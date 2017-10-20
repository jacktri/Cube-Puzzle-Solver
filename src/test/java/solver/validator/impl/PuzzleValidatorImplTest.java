package solver.validator.impl;

import org.junit.Test;
import solver.exception.NoPuzzleRulesException;
import solver.model.Puzzle;
import solver.validator.PuzzleRules;

import java.util.Set;

import static org.junit.Assert.*;

public class PuzzleValidatorImplTest {

    private PuzzleValidatorImpl testee = PuzzleValidatorImpl.getInstance();

    @Test(expected = NoPuzzleRulesException.class)
    public void validatePuzzleWithoutRules() {
        testee.validatePuzzle(Puzzle.newBuilder()
                .build());
    }

    @Test
    public void validatePuzzle() {
        Puzzle puzzle = Puzzle.newBuilder()
                .puzzleRules(Set.of(new MockRules()))
                .build();
        testee.validatePuzzle(puzzle);
        assertEquals(-987, puzzle.getTargetSize());

    }

    private static class MockRules implements PuzzleRules {

        @Override
        public void invoke(Puzzle puzzle) {
            puzzle.setTargetSize(-987);
        }
    }

}