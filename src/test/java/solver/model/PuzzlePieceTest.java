package solver.model;

import org.junit.Test;
import solver.PuzzleTestUtils;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static solver.enums.PieceDirection.EAST;
import static solver.enums.PieceDirection.NORTH;
import static solver.enums.PieceDirection.SOUTH;
import static solver.enums.PieceDirection.WEST;
import static solver.enums.PiecePosition.FRONT;
import static solver.PuzzleTestUtils.PUZZLE_STRING;


public class PuzzlePieceTest {

    private static final String STRING_REPRESENTATION = "sample";
    private PuzzleTestUtils puzzleTestUtils = new PuzzleTestUtils();

    @Test
    public void initWithString(){
        PuzzlePiece puzzlePiece = new PuzzlePiece(PUZZLE_STRING);
        assertEquals(puzzlePiece.getStringRepresentation(),PUZZLE_STRING);
    }

    @Test
    public void init(){
        PuzzlePiece puzzlePiece = new PuzzlePiece();
        char[][] puzzlePieceArray = puzzleTestUtils.puzzlePieceArray();
        puzzlePiece.setPiecePosition(FRONT);
        puzzlePiece.setPiece(puzzlePieceArray);
        puzzlePiece.setId(1);
        assertArrayEquals(puzzlePieceArray, puzzlePiece.getPiece());
        assertEquals(FRONT, puzzlePiece.getPiecePosition());
        assertEquals(1, puzzlePiece.getId());
        assertEquals(NORTH, puzzlePiece.getPieceDirection());
    }

    @Test
    public void setPuzzlePiece(){
        PuzzlePiece puzzlePiece = new PuzzlePiece(PUZZLE_STRING);
        char[][] puzzlePieceArray = puzzleTestUtils.puzzlePieceArray();
        puzzlePiece.setPiece(puzzlePieceArray);
        assertArrayEquals(puzzlePieceArray, puzzlePiece.getPiece());
    }

    @Test
    public void testHashCode(){
        PuzzlePiece puzzlePiece = PuzzlePiece.newBuilder().build();
        Set<PuzzlePiece> set = new HashSet<>();
        set.add(puzzlePiece);
        set.add(puzzlePiece);
        assertEquals(1, set.size());
    }

    @Test
    public void testBuilder(){
        char[][] puzzlePieceArray = puzzleTestUtils.puzzlePieceArray();
        PuzzlePiece puzzlePiece = PuzzlePiece.newBuilder()
                .piece(puzzlePieceArray)
                .id(1)
                .pieceDirection(WEST)
                .piecePosition(FRONT)
                .stringRepresentation(STRING_REPRESENTATION)
                .build();

        PuzzlePiece expected = new PuzzlePiece();
        expected.setId(1);
        expected.setPiece(puzzlePieceArray);
        expected.setPiecePosition(FRONT);
        expected.setPieceDirection(WEST);
        expected.setStringRepresentation(STRING_REPRESENTATION);

        assertEquals(puzzlePiece, expected);
    }

    @Test
    public void rotatePiece(){
        PuzzlePiece puzzlePiece = PuzzlePiece.newBuilder().build();
        assertEquals(NORTH, puzzlePiece.getPieceDirection());
        puzzlePiece.rotatePieceDirectionRight();
        assertEquals(EAST, puzzlePiece.getPieceDirection());
        puzzlePiece.rotatePieceDirectionRight();
        assertEquals(SOUTH, puzzlePiece.getPieceDirection());
        puzzlePiece.rotatePieceDirectionRight();
        assertEquals(WEST, puzzlePiece.getPieceDirection());
    }



}