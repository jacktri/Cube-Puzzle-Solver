package solver.validator.impl;

import solver.exception.NoPuzzleRulesException;
import solver.model.Puzzle;
import solver.validator.PuzzleRules;
import solver.validator.PuzzleValidator;

import java.util.Set;

public class PuzzleValidatorImpl implements PuzzleValidator {

    private static PuzzleValidatorImpl instance = null;

    private PuzzleValidatorImpl() {
    }

    public static PuzzleValidatorImpl getInstance(){
        if(instance == null){
            instance = new PuzzleValidatorImpl();
        }
        return instance;
    }

    @Override
    public void validatePuzzle(Puzzle puzzle) {
        Set<PuzzleRules> rules = puzzle.getPuzzleRules();
        if(null == rules || rules.isEmpty()){
            throw new NoPuzzleRulesException();
        }
        rules.forEach(puzzleRule -> puzzleRule.invoke(puzzle));
    }
}
