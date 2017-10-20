package solver.service;

import solver.model.Puzzle;

@FunctionalInterface
public interface CubePuzzleService {
    String solvePuzzle(Puzzle puzzle);
}
