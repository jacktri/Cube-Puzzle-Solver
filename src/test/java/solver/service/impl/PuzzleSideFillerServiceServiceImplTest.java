package solver.service.impl;

import org.junit.Test;
import solver.model.Puzzle;
import solver.model.PuzzlePiece;
import solver.PuzzleTestUtils;
import solver.validator.impl.DefaultCubePuzzleRules;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static solver.PuzzleTestUtils.FULL_PUZZLE_SIZE;
import static solver.PuzzleTestUtils.PUZZLE_SIZE;

public class PuzzleSideFillerServiceServiceImplTest {

    private PuzzleSideFillerServiceServiceImpl testee = PuzzleSideFillerServiceServiceImpl.getInstance();

    private PuzzleTestUtils puzzleTestUtils = new PuzzleTestUtils();

    @Test
    public void fillTopSide() throws Exception {
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());
        testee.fillTopSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForTop(), result);
    }

    @Test
    public void fillSideWithInt1ShouldFillTop(){
        int input = 1;
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());

        testee.fillSideWithInt(input,puzzle,puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForTop(), result);
    }

    @Test
    public void fillTopSideTwiceShouldNotReplaceExistingOs() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillTopSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(completedSideTopOrBottom(0, 'o'), result);
        testee.fillTopSide(puzzle, puzzle.getPieces().get(1));
        assertArrayEquals(completedSideTopOrBottom(0, 'o'), result);
    }

    @Test
    public void fillTopSideTwiceShouldReplaceSpaces() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillTopSide(puzzle, puzzle.getPieces().get(1));
        char[][][] result = puzzle.getFullPuzzle();
        assertNotEquals(completedSideTopOrBottom(0, 'o'), result);
        testee.fillTopSide(puzzle, puzzle.getPieces().get(0));
        assertArrayEquals(completedSideTopOrBottom(0, 'o'), result);
    }

    @Test
    public void fillBottomSide() throws Exception {
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());
        testee.fillBottomSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForBottom(), result);
    }

    @Test
    public void fillSideWithInt4ShouldFillBottom(){
        int input = 4;
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());

        testee.fillSideWithInt(input,puzzle,puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForBottom(), result);
    }

    @Test
    public void fillBottomSideShouldNotReplaceExistsingOs() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillBottomSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(completedSideTopOrBottom(PUZZLE_SIZE-1, 'o'), result);
        testee.fillBottomSide(puzzle, puzzle.getPieces().get(1));
        assertArrayEquals(completedSideTopOrBottom(PUZZLE_SIZE-1, 'o'), result);
    }

    @Test
    public void fillBottomSideTwiceShouldReplaceSpaces() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillBottomSide(puzzle, puzzle.getPieces().get(1));
        char[][][] result = puzzle.getFullPuzzle();
        assertNotEquals(completedSideTopOrBottom(PUZZLE_SIZE-1, 'o'), result);
        testee.fillBottomSide(puzzle, puzzle.getPieces().get(0));
        assertArrayEquals(completedSideTopOrBottom(PUZZLE_SIZE-1, 'o'), result);
    }

    @Test
    public void fillLeftSide() throws Exception {
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());
        testee.fillLeftSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForLeft(), result);
    }

    @Test
    public void fillSideWithInt0ShouldFillLeft(){
        int input = 0;
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());

        testee.fillSideWithInt(input,puzzle,puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForLeft(), result);
    }

    @Test
    public void fillLeftSideTwiceShouldNotReplaceExistingOs() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillLeftSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(completedSideLeftOrRight(0, 'o'), result);
        testee.fillLeftSide(puzzle, puzzle.getPieces().get(1));
        assertArrayEquals(completedSideLeftOrRight(0, 'o'), result);
    }

    @Test
    public void fillLeftSideTwiceShouldReplaceSpaces() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillLeftSide(puzzle, puzzle.getPieces().get(1));
        char[][][] result = puzzle.getFullPuzzle();
        assertNotEquals(completedSideLeftOrRight(0, 'o'), result);
        testee.fillLeftSide(puzzle, puzzle.getPieces().get(0));
        assertArrayEquals(completedSideLeftOrRight(0, 'o'), result);
    }

    @Test
    public void fillRightSide() throws Exception {
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());
        testee.fillRightSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForRight(), result);
    }

    @Test
    public void fillSideWithInt2ShouldFillRight(){
        int input = 2;
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());

        testee.fillSideWithInt(input,puzzle,puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForRight(), result);
    }

    @Test
    public void fillRightSideTwiceShouldNotReplaceExistingOs() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillRightSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(completedSideLeftOrRight(4, 'o'), result);
        testee.fillRightSide(puzzle, puzzle.getPieces().get(1));
        assertArrayEquals(completedSideLeftOrRight(4, 'o'), result);
    }

    @Test
    public void fillRightSideTwiceShouldReplaceSpaces() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillRightSide(puzzle, puzzle.getPieces().get(1));
        char[][][] result = puzzle.getFullPuzzle();
        assertNotEquals(completedSideLeftOrRight(4, 'o'), result);
        testee.fillRightSide(puzzle, puzzle.getPieces().get(0));
        assertArrayEquals(completedSideLeftOrRight(4, 'o'), result);
    }

    @Test
    public void fillBackSide() throws Exception {
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());
        testee.fillBackSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForBack(), result);
    }

    @Test
    public void fillSideWithInt3ShouldFillBack(){
        int input = 3;
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());

        testee.fillSideWithInt(input,puzzle,puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForBack(), result);
    }

    @Test
    public void fillBackSideTwiceShouldNotReplaceExistingOs() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillBackSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(completedSideFrontOrBack(0, 'o'), result);
        testee.fillBackSide(puzzle, puzzle.getPieces().get(1));
        assertArrayEquals(completedSideFrontOrBack(0, 'o'), result);
    }

    @Test
    public void fillBackSideTwiceShouldReplaceSpaces() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillBackSide(puzzle, puzzle.getPieces().get(1));
        char[][][] result = puzzle.getFullPuzzle();
        assertNotEquals(completedSideFrontOrBack(0, 'o'), result);
        testee.fillBackSide(puzzle, puzzle.getPieces().get(0));
        assertArrayEquals(completedSideFrontOrBack(0, 'o'), result);
    }

    @Test
    public void fillFrontSide() throws Exception {
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());
        testee.fillFrontSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForFront(), result);
    }

    @Test
    public void fillSideWithInt5ShouldFillFront(){
        int input = 5;
        Puzzle puzzle = puzzle();
        puzzle.getPieces().get(0).setPiece(puzzleTestUtils.piece());

        testee.fillSideWithInt(input,puzzle,puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(expectedMatrixForFront(), result);
    }

    @Test
    public void fillFrontSideTwiceShouldNotReplaceExistingOs() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillFrontSide(puzzle, puzzle.getPieces().get(0));
        char[][][] result = puzzle.getFullPuzzle();
        assertArrayEquals(completedSideFrontOrBack(PUZZLE_SIZE-1, 'o'), result);
        testee.fillFrontSide(puzzle, puzzle.getPieces().get(1));
        assertArrayEquals(completedSideFrontOrBack(PUZZLE_SIZE-1, 'o'), result);
    }

    @Test
    public void fillFrontSideTwiceShouldReplaceSpaces() throws Exception {
        Puzzle puzzle = puzzle();
        testee.fillFrontSide(puzzle, puzzle.getPieces().get(1));
        char[][][] result = puzzle.getFullPuzzle();
        assertNotEquals(completedSideFrontOrBack(PUZZLE_SIZE-1, 'o'), result);
        testee.fillFrontSide(puzzle, puzzle.getPieces().get(0));
        assertArrayEquals(completedSideFrontOrBack(PUZZLE_SIZE-1, 'o'), result);
    }

    @Test
    public void fillSidesShouldReturnFalse() throws Exception {
        Puzzle puzzle = Puzzle.newBuilder()
                .puzzleLength(PUZZLE_SIZE)
                .build();
        puzzle.resetPuzzle();
        PuzzlePiece left = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.leftPiece())
                .build();
        PuzzlePiece right = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.rightPiece())
                .build();
        PuzzlePiece top = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.diamondPiece())
                .build();
        PuzzlePiece back = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.randomPiece4())
                .build();
        testee.fillLeftSide(puzzle, left);
        testee.fillRightSide(puzzle, right);
        testee.fillTopSide(puzzle, top);
        boolean result = testee.fillBackSide(puzzle, back);
        assertFalse(result);
    }

    @Test
    public void fillSidesShouldReturnTrue() throws Exception {
        Puzzle puzzle = Puzzle.newBuilder()
                .puzzleLength(PUZZLE_SIZE)
                .build();
        puzzle.resetPuzzle();
        PuzzlePiece left = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build();
        PuzzlePiece right = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build();
        PuzzlePiece top = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build();
        PuzzlePiece back = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build();
        PuzzlePiece bottom = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build();
        PuzzlePiece front = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build();
        testee.fillLeftSide(puzzle, left);
        testee.fillRightSide(puzzle, right);
        testee.fillTopSide(puzzle, top);
        testee.fillBackSide(puzzle, back);
        testee.fillFrontSide(puzzle, front);
        boolean result = testee.fillBottomSide(puzzle, bottom);
        assertTrue(result);
        assertEquals(54, puzzle.getFilledSquareCount());

        PuzzlePiece mockPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.mockPiece())
                .build();

        PuzzlePiece mockPiece2 = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.mockPiece2())
                .build();
        testee.fillBottomSide(puzzle, mockPiece);
        result = testee.fillTopSide(puzzle, mockPiece);
        testee.fillFrontSide(puzzle, mockPiece2);
        testee.fillBackSide(puzzle, mockPiece2);
        assertTrue(result);
        assertEquals(FULL_PUZZLE_SIZE, puzzle.getFilledSquareCount());
    }

    @Test
    public void shouldReturnTrueForRealExample(){
        Puzzle puzzle = Puzzle.newBuilder()
                .puzzleLength(PUZZLE_SIZE)
                .build();
        puzzle.resetPuzzle();
        PuzzlePiece left = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.diamondPiece())
                .build();
        PuzzlePiece top = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.randomPiece1())
                .build();
        PuzzlePiece right = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.randomPiece2())
                .build();
        PuzzlePiece back = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.randomPiece3())
                .build();
        PuzzlePiece bottom = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.randomPiece4())
                .build();
        PuzzlePiece front = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.randomPiece5())
                .build();

        testee.fillLeftSide(puzzle, left);
        testee.fillRightSide(puzzle, right);
        testee.fillBottomSide(puzzle, bottom);
        testee.fillFrontSide(puzzle, front);
        testee.fillTopSide(puzzle, top);

        boolean result = testee.fillBackSide(puzzle, back);

        assertTrue(result);
        assertEquals(FULL_PUZZLE_SIZE, puzzle.getFilledSquareCount());
    }


    @Test
    public void fillBottomSideTwice(){
        Puzzle puzzle = Puzzle.newBuilder()
                .puzzleLength(PUZZLE_SIZE)
                .build();
        puzzle.resetPuzzle();
        PuzzlePiece bottom = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.randomPiece4())
                .build();

        testee.fillBottomSide(puzzle, bottom);
        boolean result = testee.fillBottomSide(puzzle, bottom);
        assertFalse(result);
    }

    @Test
    public void fillRightSideTwice(){
        Puzzle puzzle = Puzzle.newBuilder()
                .puzzleLength(PUZZLE_SIZE)
                .build();
        puzzle.resetPuzzle();
        PuzzlePiece right = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.randomPiece2())
                .build();

        testee.fillRightSide(puzzle, right);
        boolean result = testee.fillRightSide(puzzle, right);
        assertFalse(result);
    }

    private Puzzle puzzle() {

        Puzzle puzzle = Puzzle.newBuilder()
                .fullPuzzle(new char[PUZZLE_SIZE][PUZZLE_SIZE][PUZZLE_SIZE])
                .puzzleLength(PUZZLE_SIZE)
                .pieces(List.of(fullPuzzlePiece(), blankPuzzlePiece(), blankPuzzlePiece(), blankPuzzlePiece(), blankPuzzlePiece(), blankPuzzlePiece()))
                .puzzleRules(Set.of(DefaultCubePuzzleRules.getInstance()))
                .build();
        puzzle.getPuzzleRules()
                .iterator()
                .next()
                .invoke(puzzle);

        return puzzle;
    }

    private PuzzlePiece blankPuzzlePiece() {
        return new PuzzlePiece("     \n" +
                "     \n" +
                "     \n" +
                "     \n" +
                "     ");
    }

    private PuzzlePiece fullPuzzlePiece() {
        return new PuzzlePiece("ooooo\n" +
                "ooooo\n" +
                "ooooo\n" +
                "ooooo\n" +
                "ooooo");
    }

    private char[][][] completedSideTopOrBottom(int z, char input) {
        char[][][] puzzleArray = new char[PUZZLE_SIZE][PUZZLE_SIZE][PUZZLE_SIZE];
        puzzleArray[0][0][z] = input;
        puzzleArray[0][1][z] = input;
        puzzleArray[0][2][z] = input;
        puzzleArray[0][3][z] = input;
        puzzleArray[0][4][z] = input;

        puzzleArray[1][0][z] = input;
        puzzleArray[1][1][z] = input;
        puzzleArray[1][2][z] = input;
        puzzleArray[1][3][z] = input;
        puzzleArray[1][4][z] = input;

        puzzleArray[2][0][z] = input;
        puzzleArray[2][1][z] = input;
        puzzleArray[2][2][z] = input;
        puzzleArray[2][3][z] = input;
        puzzleArray[2][4][z] = input;

        puzzleArray[3][0][z] = input;
        puzzleArray[3][1][z] = input;
        puzzleArray[3][2][z] = input;
        puzzleArray[3][3][z] = input;
        puzzleArray[3][4][z] = input;

        puzzleArray[4][0][z] = input;
        puzzleArray[4][1][z] = input;
        puzzleArray[4][2][z] = input;
        puzzleArray[4][3][z] = input;
        puzzleArray[4][4][z] = input;
        return puzzleArray;
    }

    private char[][][] completedSideFrontOrBack(int x, char input) {
        char[][][] puzzleArray = new char[PUZZLE_SIZE][PUZZLE_SIZE][PUZZLE_SIZE];
        puzzleArray[x][0][0] = input;
        puzzleArray[x][1][0] = input;
        puzzleArray[x][2][0] = input;
        puzzleArray[x][3][0] = input;
        puzzleArray[x][4][0] = input;

        puzzleArray[x][0][1] = input;
        puzzleArray[x][1][1] = input;
        puzzleArray[x][2][1] = input;
        puzzleArray[x][3][1] = input;
        puzzleArray[x][4][1] = input;

        puzzleArray[x][0][2] = input;
        puzzleArray[x][1][2] = input;
        puzzleArray[x][2][2] = input;
        puzzleArray[x][3][2] = input;
        puzzleArray[x][4][2] = input;

        puzzleArray[x][0][3] = input;
        puzzleArray[x][1][3] = input;
        puzzleArray[x][2][3] = input;
        puzzleArray[x][3][3] = input;
        puzzleArray[x][4][3] = input;

        puzzleArray[x][0][4] = input;
        puzzleArray[x][1][4] = input;
        puzzleArray[x][2][4] = input;
        puzzleArray[x][3][4] = input;
        puzzleArray[x][4][4] = input;
        return puzzleArray;
    }

    private char[][][] completedSideLeftOrRight(int y, char input) {
        char[][][] puzzleArray = new char[PUZZLE_SIZE][PUZZLE_SIZE][PUZZLE_SIZE];
        puzzleArray[0][y][0] = input;
        puzzleArray[1][y][0] = input;
        puzzleArray[2][y][0] = input;
        puzzleArray[3][y][0] = input;
        puzzleArray[4][y][0] = input;

        puzzleArray[0][y][1] = input;
        puzzleArray[1][y][1] = input;
        puzzleArray[2][y][1] = input;
        puzzleArray[3][y][1] = input;
        puzzleArray[4][y][1] = input;

        puzzleArray[0][y][2] = input;
        puzzleArray[1][y][2] = input;
        puzzleArray[2][y][2] = input;
        puzzleArray[3][y][2] = input;
        puzzleArray[4][y][2] = input;

        puzzleArray[0][y][3] = input;
        puzzleArray[1][y][3] = input;
        puzzleArray[2][y][3] = input;
        puzzleArray[3][y][3] = input;
        puzzleArray[4][y][3] = input;

        puzzleArray[0][y][4] = input;
        puzzleArray[1][y][4] = input;
        puzzleArray[2][y][4] = input;
        puzzleArray[3][y][4] = input;
        puzzleArray[4][y][4] = input;
        return puzzleArray;
    }

    private char[][][] expectedMatrixForLeft() {
        return expectedMatrixLeftOrRight(0);
    }

    private char[][][] expectedMatrixForRight() {
        return expectedMatrixLeftOrRight(4);
    }

    private char[][][] expectedMatrixForBack() {
        return expectedMatrixFrontOrBack(0);
    }

    private char[][][] expectedMatrixForFront() {
        return expectedMatrixFrontOrBack(4);
    }

    private char[][][] expectedMatrixLeftOrRight(int y) {
        char[][][] matrix = new char[5][5][5];

        matrix[0][y][0] = ' ';
        matrix[1][y][0] = ' ';
        matrix[2][y][0] = ' ';
        matrix[3][y][0] = ' ';
        matrix[4][y][0] = ' ';

        matrix[0][y][1] = ' ';
        matrix[1][y][1] = 'o';
        matrix[2][y][1] = 'o';
        matrix[3][y][1] = 'o';
        matrix[4][y][1] = ' ';

        matrix[0][y][2] = ' ';
        matrix[1][y][2] = 'o';
        matrix[2][y][2] = 'o';
        matrix[3][y][2] = 'o';
        matrix[4][y][2] = ' ';

        matrix[0][y][3] = ' ';
        matrix[1][y][3] = 'o';
        matrix[2][y][3] = 'o';
        matrix[3][y][3] = 'o';
        matrix[4][y][3] = ' ';

        matrix[0][y][4] = ' ';
        matrix[1][y][4] = ' ';
        matrix[2][y][4] = ' ';
        matrix[3][y][4] = ' ';
        matrix[4][y][4] = ' ';
        return matrix;
    }

    private char[][][] expectedMatrixFrontOrBack(int x) {
        char[][][] matrix = new char[PUZZLE_SIZE][PUZZLE_SIZE][PUZZLE_SIZE];

        matrix[x][0][0] = ' ';
        matrix[x][1][0] = ' ';
        matrix[x][2][0] = ' ';
        matrix[x][3][0] = ' ';
        matrix[x][4][0] = ' ';

        matrix[x][0][1] = ' ';
        matrix[x][1][1] = 'o';
        matrix[x][2][1] = 'o';
        matrix[x][3][1] = 'o';
        matrix[x][4][1] = ' ';

        matrix[x][0][2] = ' ';
        matrix[x][1][2] = 'o';
        matrix[x][2][2] = 'o';
        matrix[x][3][2] = 'o';
        matrix[x][4][2] = ' ';

        matrix[x][0][3] = ' ';
        matrix[x][1][3] = 'o';
        matrix[x][2][3] = 'o';
        matrix[x][3][3] = 'o';
        matrix[x][4][3] = ' ';

        matrix[x][0][4] = ' ';
        matrix[x][1][4] = ' ';
        matrix[x][2][4] = ' ';
        matrix[x][3][4] = ' ';
        matrix[x][4][4] = ' ';
        return matrix;
    }

    private char[][][] expectedMatrixForTop() {
        return expectedMatrixTopOrBottom(0);
    }

    private char[][][] expectedMatrixForBottom() {
        return expectedMatrixTopOrBottom(4);
    }

    private char[][][] expectedMatrixTopOrBottom(int z) {
        char[][][] matrix = new char[PUZZLE_SIZE][PUZZLE_SIZE][PUZZLE_SIZE];

        matrix[0][0][z] = ' ';
        matrix[0][1][z] = ' ';
        matrix[0][2][z] = ' ';
        matrix[0][3][z] = ' ';
        matrix[0][4][z] = ' ';

        matrix[1][0][z] = ' ';
        matrix[1][1][z] = 'o';
        matrix[1][2][z] = 'o';
        matrix[1][3][z] = 'o';
        matrix[1][4][z] = ' ';

        matrix[2][0][z] = ' ';
        matrix[2][1][z] = 'o';
        matrix[2][2][z] = 'o';
        matrix[2][3][z] = 'o';
        matrix[2][4][z] = ' ';

        matrix[3][0][z] = ' ';
        matrix[3][1][z] = 'o';
        matrix[3][2][z] = 'o';
        matrix[3][3][z] = 'o';
        matrix[3][4][z] = ' ';

        matrix[4][0][z] = ' ';
        matrix[4][1][z] = ' ';
        matrix[4][2][z] = ' ';
        matrix[4][3][z] = ' ';
        matrix[4][4][z] = ' ';
        return matrix;
    }
}