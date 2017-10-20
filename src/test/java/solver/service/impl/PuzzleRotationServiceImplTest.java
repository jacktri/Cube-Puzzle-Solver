package solver.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import solver.PuzzleTestUtils;
import solver.model.PuzzlePiece;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static solver.PuzzleTestUtils.PUZZLE_SIZE;
import static solver.enums.PieceDirection.EAST;
import static solver.enums.PieceDirection.SOUTH;
import static solver.enums.PieceDirection.WEST;

public class PuzzleRotationServiceImplTest {

    @InjectMocks
    private PuzzleRotationServiceImpl puzzleRotationService = PuzzleRotationServiceImpl.getInstance();

    private PuzzleTestUtils puzzleTestUtils = new PuzzleTestUtils();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRotationRight90Degrees(){
        char[][] expected = pieceRotatedRight90Degrees();
        char[][] piece = puzzleTestUtils.rightPiece();
        PuzzlePiece puzzlePiece = PuzzlePiece.newBuilder()
                .piece(piece)
                .build();
        puzzleRotationService.rotateSideRight90Degrees(puzzlePiece);
        assertArrayEquals(expected, piece);
        assertEquals(EAST,puzzlePiece.getPieceDirection());
    }

    @Test
    public void testRotation180Degrees(){
        char[][] expected = pieceRotated180Degrees();
        char[][] piece = puzzleTestUtils.rightPiece();
        PuzzlePiece puzzlePiece = PuzzlePiece.newBuilder()
                .piece(piece)
                .build();
        puzzleRotationService.rotateSideRight90Degrees(puzzlePiece);
        puzzleRotationService.rotateSideRight90Degrees(puzzlePiece);
        assertArrayEquals(expected, piece);
        assertEquals(SOUTH,puzzlePiece.getPieceDirection());
    }

    @Test
    public void testRotationRight270Degrees(){
        char[][] expected = pieceRotatedRight270Degrees();
        char[][] piece = puzzleTestUtils.rightPiece();
        PuzzlePiece puzzlePiece = PuzzlePiece.newBuilder()
                .piece(piece)
                .build();
        puzzleRotationService.rotateSideRight90Degrees(puzzlePiece);
        puzzleRotationService.rotateSideRight90Degrees(puzzlePiece);
        puzzleRotationService.rotateSideRight90Degrees(puzzlePiece);
        assertArrayEquals(expected, piece);
        assertEquals(WEST,puzzlePiece.getPieceDirection());
    }

    private char[][] pieceRotatedRight90Degrees() {
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = 'o';
        piece[0][1] = ' ';
        piece[0][2] = 'o';
        piece[0][3] = ' ';
        piece[0][4] = ' ';

        piece[1][0] = 'o';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = 'o';

        piece[2][0] = ' ';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = ' ';

        piece[3][0] = 'o';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = 'o';

        piece[4][0] = 'o';
        piece[4][1] = 'o';
        piece[4][2] = ' ';
        piece[4][3] = 'o';
        piece[4][4] = ' ';
        return piece;
    }

    private char[][] pieceRotated180Degrees(){
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = 'o';
        piece[0][1] = 'o';
        piece[0][2] = ' ';
        piece[0][3] = 'o';
        piece[0][4] = 'o';

        piece[1][0] = 'o';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = ' ';

        piece[2][0] = ' ';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = 'o';

        piece[3][0] = 'o';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = ' ';

        piece[4][0] = ' ';
        piece[4][1] = 'o';
        piece[4][2] = ' ';
        piece[4][3] = 'o';
        piece[4][4] = ' ';
        return piece;
    }

    private char[][] pieceRotatedRight270Degrees(){
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = ' ';
        piece[0][1] = 'o';
        piece[0][2] = ' ';
        piece[0][3] = 'o';
        piece[0][4] = 'o';

        piece[1][0] = 'o';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = 'o';

        piece[2][0] = ' ';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = ' ';

        piece[3][0] = 'o';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = 'o';

        piece[4][0] = ' ';
        piece[4][1] = ' ';
        piece[4][2] = 'o';
        piece[4][3] = ' ';
        piece[4][4] = 'o';
        return piece;
    }

}