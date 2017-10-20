package solver.service.impl;

import solver.exception.InvalidInputException;
import solver.model.PuzzlePiece;
import solver.service.PuzzleRotationService;

public class PuzzleRotationServiceImpl implements PuzzleRotationService {

    private PuzzleRotationServiceImpl() {

    }

    private static PuzzleRotationServiceImpl instance = null;

    public static PuzzleRotationServiceImpl getInstance(){
        if(instance == null){
            instance = new PuzzleRotationServiceImpl();
        }
        return instance;
    }

    // this will allow the puzzle pieces to be rotated right
    @Override
    public void rotateSideRight90Degrees(PuzzlePiece puzzlePiece) {
        char[][] matrix = puzzlePiece.getPiece();
        if (matrix == null) {
            throw new InvalidInputException();
        }
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                char top = matrix[first][i];

                // left to top
                matrix[first][i] = matrix[last - offset][first];

                // bottom to left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right to bottom
                matrix[last][last - offset] = matrix[i][last];

                // top to right
                matrix[i][last] = top;
            }
        }
        puzzlePiece.rotatePieceDirectionRight();
    }
}
