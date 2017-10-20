package solver.model;

import org.junit.Test;
import solver.validator.PuzzleRules;
import solver.validator.impl.DefaultCubePuzzleRules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static solver.PuzzleTestUtils.PUZZLE_SIZE;
import static solver.PuzzleTestUtils.PUZZLE_STRING;

public class PuzzleTest {

    @Test
    public void initWithString(){
        PuzzlePiece puzzlePiece = new PuzzlePiece(PUZZLE_STRING);
        List<PuzzlePiece> puzzlePieces = List.of(puzzlePiece);
        Puzzle puzzle = new Puzzle(puzzlePieces);
        assertEquals(puzzlePieces, puzzle.getPieces());
    }

    @Test
    public void init(){
        Puzzle puzzle = new Puzzle();
        assertNotNull(puzzle);
    }

    @Test
    public void testBuilder(){
        char[][][] fullPuzzle = new char[PUZZLE_SIZE][PUZZLE_SIZE][PUZZLE_SIZE];
        Set<PuzzleRules> rules = Set.of(DefaultCubePuzzleRules.getInstance());
        List<PuzzlePiece> pieces = List.of(PuzzlePiece.newBuilder().build());

        Puzzle puzzle = Puzzle.newBuilder()
                .puzzleLength(0)
                .fullPuzzle(fullPuzzle)
                .pieces(pieces)
                .puzzleRules(rules)
                .filledSquareCount(0)
                .targetSize(0)
                .build();

        Puzzle expected = new Puzzle();

        expected.setTargetSize(0);
        expected.setPieces(pieces);
        expected.setPuzzleRules(rules);
        expected.setFullPuzzle(fullPuzzle);
        expected.setFilledSquareCount(0);
        expected.setPuzzleLength(0);

        assertEquals(expected, puzzle);
    }

    @Test
    public void testResetCount(){
        Puzzle puzzle = Puzzle.newBuilder()
                .filledSquareCount(PUZZLE_SIZE)
                .build();
        assertEquals(PUZZLE_SIZE, puzzle.getFilledSquareCount());

        puzzle.resetPuzzle();
        assertEquals(0, puzzle.getFilledSquareCount());
    }

    @Test
    public void addRules(){
        Puzzle puzzle = Puzzle.newBuilder()
                .build();

        puzzle.addPuzzleRules(DefaultCubePuzzleRules.getInstance());
        puzzle.addPuzzleRules(DefaultCubePuzzleRules.getInstance());
        assertEquals(DefaultCubePuzzleRules.getInstance(), puzzle.getPuzzleRules().iterator().next());
        assertEquals(1, puzzle.getPuzzleRules().size());
    }

    @Test
    public void getRules(){
        Puzzle puzzle = Puzzle.newBuilder()
                .build();

        assertEquals(0, puzzle.getPuzzleRules().size());
    }

    @Test
    public void copyPuzzle(){
        Puzzle puzzle = Puzzle.newBuilder()
                .build();
        Puzzle puzzleCopy = puzzle.copyPuzzle();
        assertEquals(puzzle, puzzleCopy);
        puzzle.setTargetSize(PUZZLE_SIZE);
        assertNotEquals(PUZZLE_SIZE, puzzleCopy.getTargetSize());
    }

    @Test
    public void testHashCode(){
        Puzzle puzzle = Puzzle.newBuilder()
                .build();
        Set<Puzzle> set = new HashSet<>();
        set.add(puzzle);
        set.add(puzzle);
        assertEquals(1, set.size());
    }

}