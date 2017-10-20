package solver.validator.impl;

import org.junit.Test;
import solver.exception.InvalidInputException;
import solver.model.Puzzle;
import solver.model.PuzzlePiece;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static solver.PuzzleTestUtils.PUZZLE_SIZE;


public class DefaultCubePuzzleRulesTest {

    private DefaultCubePuzzleRules testee = DefaultCubePuzzleRules.getInstance();

    @Test
    public void generatePiece1() {
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(List.of(puzzlePiece1().copyPuzzlePiece(),
                        puzzlePiece1().copyPuzzlePiece(),
                        puzzlePiece1().copyPuzzlePiece(),
                        puzzlePiece1().copyPuzzlePiece(),
                        puzzlePiece1().copyPuzzlePiece(),
                        puzzlePiece1().copyPuzzlePiece()))
                .build();
        testee.invoke(puzzle);
        PuzzlePiece piece = puzzle.getPieces().get(0);
        assertArrayEquals(pieceRepresentation1(), piece.getPiece());
    }

    @Test
    public void generatePiece2() {
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(List.of(puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece()))
                .build();
        testee.invoke(puzzle);
        PuzzlePiece piece = puzzle.getPieces().get(0);
        assertArrayEquals(pieceRepresentation2(), piece.getPiece());
    }

    @Test
    public void generateAllPieces() {
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(List.of(puzzlePiece1().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece1().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece()))
                .build();
        testee.invoke(puzzle);
        PuzzlePiece piece = puzzle.getPieces().get(0);
        assertArrayEquals(pieceRepresentation1(), piece.getPiece());
        piece = puzzle.getPieces().get(1);
        assertArrayEquals(pieceRepresentation2(), piece.getPiece());
    }

    @Test(expected = InvalidInputException.class)
    public void tooFewPuzzlePiecesShouldThrowInvalidInputException(){
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(List.of(puzzlePiece1().copyPuzzlePiece()))
                .build();
        testee.invoke(puzzle);
    }

    @Test(expected = InvalidInputException.class)
    public void tooManyPuzzlePiecesShouldThrowInvalidInputException(){
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(List.of(puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece()))
                .build();
        testee.invoke(puzzle);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void invalidStringInputTooWide(){
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(List.of(new PuzzlePiece("oo   o\n" +
                                "ooooo\n" +
                                "ooooo\n" +
                                "ooooo\n" +
                                "o   o"),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece()))
                .build();
        testee.invoke(puzzle);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void invalidStringInputTooHigh(){
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(List.of(new PuzzlePiece("oo   o\n" +
                                "ooooo\n" +
                                "ooooo\n" +
                                "ooooo\n" +
                                "ooooo\n" +
                                "o   o"),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece()))
                .build();
        testee.invoke(puzzle);
    }

    @Test(expected = InvalidInputException.class)
    public void invalidStringInvalidCharacter(){
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(List.of(new PuzzlePiece("o   o\n" +
                                "roooo\n" +
                                "ooooo\n" +
                                "ooooo\n" +
                                "o   o"),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece()))
                .build();
        testee.invoke(puzzle);
    }

    @Test(expected = InvalidInputException.class)
    public void invalidStringHeightTooShort(){
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(List.of(new PuzzlePiece("o   o\n" +
                                "ooooo\n" +
                                "ooooo\n" +
                                "o   o"),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece()))
                .build();
        testee.invoke(puzzle);
    }

    @Test(expected = InvalidInputException.class)
    public void invalidStringWidthTooShort(){
        Puzzle puzzle = Puzzle.newBuilder()
                .pieces(List.of(new PuzzlePiece("o   o\n" +
                                "ooo\n" +
                                "ooooo\n" +
                                "ooooo\n" +
                                "o   o"),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece(),
                        puzzlePiece2().copyPuzzlePiece()))
                .build();
        testee.invoke(puzzle);
    }

    private PuzzlePiece puzzlePiece1() {
        return new PuzzlePiece("o   o\n" +
                "ooooo\n" +
                "ooooo\n" +
                "ooooo\n" +
                "o   o");
    }

    private char[][] pieceRepresentation1() {
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = 'o';
        piece[0][1] = ' ';
        piece[0][2] = ' ';
        piece[0][3] = ' ';
        piece[0][4] = 'o';

        for(int i = 1; i < 4; i++){
            for(int j = 0; j < 5; j++){
                piece[i][j] = 'o';
            }
        }
        piece[4][0] = 'o';
        piece[4][1] = ' ';
        piece[4][2] = ' ';
        piece[4][3] = ' ';
        piece[4][4] = 'o';
        return piece;
    }

    private PuzzlePiece puzzlePiece2() {
        return new PuzzlePiece("o  oo\n" +
                "ooooo\n" +
                " ooo \n" +
                " ooo \n" +
                "oo  o");
    }

    private char[][] pieceRepresentation2() {
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = 'o';
        piece[0][1] = ' ';
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

        piece[3][0] = ' ';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = ' ';

        piece[4][0] = 'o';
        piece[4][1] = 'o';
        piece[4][2] = ' ';
        piece[4][3] = ' ';
        piece[4][4] = 'o';
        return piece;
    }

}