package solver.service;

import solver.model.PuzzlePiece;

@FunctionalInterface
public interface PuzzleRotationService {
    void rotateSideRight90Degrees(PuzzlePiece puzzlePiece);
}
