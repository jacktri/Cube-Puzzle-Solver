package solver.service.impl;

import solver.enums.PuzzlePlane;
import solver.model.Puzzle;
import solver.model.PuzzlePiece;
import solver.service.PuzzleSideFillerService;

import static solver.enums.PuzzlePlane.X;
import static solver.enums.PuzzlePlane.Y;
import static solver.enums.PuzzlePlane.Z;

public class PuzzleSideFillerServiceServiceImpl implements PuzzleSideFillerService {

    private PuzzleSideFillerServiceServiceImpl() {

    }

    private static PuzzleSideFillerServiceServiceImpl instance = null;

    public static PuzzleSideFillerServiceServiceImpl getInstance() {
        if (instance == null) {
            instance = new PuzzleSideFillerServiceServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean fillSideWithInt(int input, Puzzle puzzle, PuzzlePiece puzzlePiece) {
        boolean successful = false;
        switch (input) {
            case 0:
                successful = fillLeftSide(puzzle, puzzlePiece);
                break;
            case 1:
                successful = fillTopSide(puzzle, puzzlePiece);
                break;
            case 2:
                successful = fillRightSide(puzzle, puzzlePiece);
                break;
            case 3:
                successful = fillBackSide(puzzle, puzzlePiece);
                break;
            case 4:
                successful = fillBottomSide(puzzle, puzzlePiece);
                break;
            case 5:
                successful = fillFrontSide(puzzle, puzzlePiece);
                break;
            default:
                break;

        }
        return successful;
    }

    @Override
    public boolean fillTopSide(Puzzle puzzle, PuzzlePiece puzzlePiece) {
        return fillSide(puzzle, puzzlePiece, 0, Z);
    }

    @Override
    public boolean fillBottomSide(Puzzle puzzle, PuzzlePiece puzzlePiece) {
        return fillSide(puzzle, puzzlePiece, puzzle.getPuzzleLength() - 1, Z);
    }

    @Override
    public boolean fillLeftSide(Puzzle puzzle, PuzzlePiece puzzlePiece) {
        return fillSide(puzzle, puzzlePiece, 0, Y);
    }

    @Override
    public boolean fillRightSide(Puzzle puzzle, PuzzlePiece puzzlePiece) {
        return fillSide(puzzle, puzzlePiece, puzzle.getPuzzleLength() - 1, Y);
    }

    @Override
    public boolean fillFrontSide(Puzzle puzzle, PuzzlePiece puzzlePiece) {
        return fillSide(puzzle, puzzlePiece, puzzle.getPuzzleLength() - 1, X);
    }

    @Override
    public boolean fillBackSide(Puzzle puzzle, PuzzlePiece puzzlePiece) {
        return fillSide(puzzle, puzzlePiece, 0, X);
    }

    private boolean fillSide(Puzzle puzzle, PuzzlePiece puzzlePiece, int inputPosition, PuzzlePlane puzzlePlane) {
        char[][] pieceArray = puzzlePiece.getPiece();
        for (int i = 0; i < pieceArray.length; i++) {
            for (int j = 0; j < pieceArray[0].length; j++) {
                if (X.equals(puzzlePlane)) {
                    if (!fillPosition(puzzle, pieceArray[i][j], inputPosition, j, i)) {
                        return false;
                    }
                } else if (Y.equals(puzzlePlane)) {
                    if (!fillPosition(puzzle, pieceArray[i][j], j, inputPosition, i)) {
                        return false;
                    }
                } else if (Z.equals(puzzlePlane)) {
                    if (!fillPosition(puzzle, pieceArray[i][j], i, j, inputPosition)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean fillPosition(Puzzle puzzle, char puzzlePiece, int a, int b, int c) {
        char[][][] completedPuzzle = puzzle.getFullPuzzle();
        if (completedPuzzle[a][b][c] != 'o') {
            completedPuzzle[a][b][c] = puzzlePiece;
            if (puzzlePiece == 'o') {
                puzzle.newSquare();
            }
            return true;
        } else if (puzzlePiece != 'o') {
            return true;
        }
        return false;
    }
}
