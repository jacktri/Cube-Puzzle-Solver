package solver.service;

import solver.model.Puzzle;
import solver.model.PuzzlePiece;

public interface PuzzleSideFillerService {

    boolean fillSideWithInt(int input, Puzzle puzzle, PuzzlePiece puzzlePiece);

    boolean fillTopSide(Puzzle puzzle, PuzzlePiece puzzlePiece);

    boolean fillBottomSide(Puzzle puzzle, PuzzlePiece puzzlePiece);

    boolean fillLeftSide(Puzzle puzzle, PuzzlePiece puzzlePiece);

    boolean fillRightSide(Puzzle puzzle, PuzzlePiece puzzlePiece);

    boolean fillFrontSide(Puzzle puzzle, PuzzlePiece puzzlePiece);

    boolean fillBackSide(Puzzle puzzle, PuzzlePiece puzzlePiece);

}
