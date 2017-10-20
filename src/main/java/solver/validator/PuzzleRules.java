package solver.validator;

import solver.model.Puzzle;

public interface PuzzleRules {
    void invoke(Puzzle puzzle);
}
