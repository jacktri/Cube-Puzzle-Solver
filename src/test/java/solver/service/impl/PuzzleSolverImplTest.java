package solver.service.impl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import solver.exception.InvalidInputException;
import solver.model.Puzzle;
import solver.model.PuzzlePiece;
import solver.PuzzleTestUtils;
import solver.service.PuzzleSideFillerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static solver.constant.PuzzleConstants.BACK;
import static solver.constant.PuzzleConstants.BOTTOM;
import static solver.constant.PuzzleConstants.FRONT;
import static solver.constant.PuzzleConstants.LEFT;
import static solver.constant.PuzzleConstants.RIGHT;
import static solver.constant.PuzzleConstants.TOP;

public class PuzzleSolverImplTest {

    @InjectMocks
    private PuzzleSolverServiceImpl testee = PuzzleSolverServiceImpl.getInstance();

    @Mock
    private PuzzleSideFillerService puzzleSideFillerService;

    private PuzzleTestUtils puzzleTestUtils = new PuzzleTestUtils();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = InvalidInputException.class)
    public void solveWithoutRightNumberOfPieces() {
        Puzzle puzzle = Puzzle.newBuilder().build();
        testee.solve(puzzle);
    }

    @Test(expected = InvalidInputException.class)
    public void solveWithoutMatrixInPieces() {
        List<PuzzlePiece> puzzlePieces = List.of(PuzzlePiece.newBuilder().build(),
                PuzzlePiece.newBuilder().build(),
                PuzzlePiece.newBuilder().build(),
                PuzzlePiece.newBuilder().build(),
                PuzzlePiece.newBuilder().build(),
                PuzzlePiece.newBuilder().build());
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(puzzlePieces)
                .build();
        testee.solve(puzzle);
    }

    @Test
    public void solveUnsuccessfully() {

        List<PuzzlePiece> puzzlePieces = new ArrayList<>();
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build());
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build());
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build());
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build());
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build());
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build());

        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(puzzlePieces)
                .build();

        when(puzzleSideFillerService.fillSideWithInt(0, puzzle, puzzlePieces.get(LEFT))).thenReturn(true);

        List<Puzzle> result = testee.solve(puzzle);
        assertEquals(0, result.size());
    }

    @Test
    public void solveSuccessfully() {
        List<PuzzlePiece> puzzlePieces = new ArrayList<>();
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.diamondPiece())
                .build());
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.diamondPiece())
                .build());
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.diamondPiece())
                .build());
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.diamondPiece())
                .build());
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.diamondPiece())
                .build());
        puzzlePieces.add(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.diamondPiece())
                .build());
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(puzzlePieces)
                .build();

        when(puzzleSideFillerService.fillSideWithInt(0, puzzle, puzzlePieces.get(LEFT))).thenReturn(true);
        when(puzzleSideFillerService.fillSideWithInt(anyInt(), any(Puzzle.class), any(PuzzlePiece.class))).thenReturn(true);
        when(puzzleSideFillerService.fillSideWithInt(anyInt(), any(Puzzle.class), any(PuzzlePiece.class))).thenReturn(true);
        when(puzzleSideFillerService.fillSideWithInt(anyInt(), any(Puzzle.class), any(PuzzlePiece.class))).thenReturn(true);
        when(puzzleSideFillerService.fillSideWithInt(anyInt(), any(Puzzle.class), any(PuzzlePiece.class))).thenReturn(true);
        when(puzzleSideFillerService.fillSideWithInt(anyInt(), any(Puzzle.class), any(PuzzlePiece.class))).thenReturn(true);

        List<Puzzle> result = testee.solve(puzzle);
        assertEquals(2732, result.size());
    }

}