package solver.service;

import solver.model.Puzzle;

import java.util.List;
import java.util.Set;

public interface PuzzlePrinterService {

    Set<String> generateUnique2dImages(List<Puzzle> puzzles);
    void printToFile(String result);
}
