package solver.service;

import solver.model.Puzzle;

import java.util.List;

@FunctionalInterface
public interface PuzzleSolverService {
    List<Puzzle> solve(Puzzle puzzle);
}
