package solver.service.impl;

import solver.model.Puzzle;
import solver.service.CubePuzzleService;
import solver.service.PuzzlePrinterService;
import solver.service.PuzzleSolverService;
import solver.validator.PuzzleValidator;
import solver.validator.impl.PuzzleValidatorImpl;

import java.util.List;
import java.util.Set;

public class CubePuzzleServiceImpl implements CubePuzzleService {

    private static CubePuzzleServiceImpl instance = null;
    private PuzzleValidator puzzleValidator = PuzzleValidatorImpl.getInstance();
    private PuzzleSolverService puzzleSolverService = PuzzleSolverServiceImpl.getInstance();
    private PuzzlePrinterService puzzlePrinterService = PuzzlePrinterServiceImpl.getInstance();

    private CubePuzzleServiceImpl() {
    }

    public static CubePuzzleServiceImpl getInstance() {
        if (instance == null) {
            instance = new CubePuzzleServiceImpl();
        }
        return instance;
    }

    @Override
    public String solvePuzzle(Puzzle puzzle) {
        puzzleValidator.validatePuzzle(puzzle);
        List<Puzzle> successfulPuzzleCombos = puzzleSolverService.solve(puzzle);
        if(!successfulPuzzleCombos.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();
            Set<String> unique2dStrings = puzzlePrinterService.generateUnique2dImages(successfulPuzzleCombos);
            unique2dStrings.forEach(stringBuilder::append);
            puzzlePrinterService.printToFile(stringBuilder.toString());
            return stringBuilder.toString();
        }
        return "No valid answers";
    }

}
