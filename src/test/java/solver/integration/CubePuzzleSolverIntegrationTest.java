package solver.integration;

import org.junit.Ignore;
import org.junit.Test;
import solver.model.Puzzle;
import solver.model.PuzzlePiece;
import solver.service.CubePuzzleService;
import solver.service.impl.CubePuzzleServiceImpl;
import solver.validator.impl.DefaultCubePuzzleRules;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class CubePuzzleSolverIntegrationTest {

    // TODO
    // this works sometimes but other times the order is different. All hashsets are ordered
    // so this needs investigating
    @Ignore
    @Test
    public void fullTestShouldFindValidAnswer() throws Exception {
        PuzzlePiece p1 = new PuzzlePiece("  o  \n" +
                " ooo \n" +
                "ooooo\n" +
                " ooo \n" +
                "  o  ");
        PuzzlePiece p2 = new PuzzlePiece(" o oo\n" +
                "ooooo\n" +
                " ooo \n" +
                "ooooo\n" +
                "  o o");

        PuzzlePiece p3 = new PuzzlePiece("  o  \n" +
                " oooo\n" +
                "oooo \n" +
                " oooo\n" +
                "  o  ");

        PuzzlePiece p4 = new PuzzlePiece("o o  \n" +
                "ooooo\n" +
                " ooo \n" +
                "ooooo\n" +
                " o o ");

        PuzzlePiece p5 = new PuzzlePiece("o o o\n" +
                "ooooo\n" +
                " ooo \n" +
                "ooooo\n" +
                "o o o");

        PuzzlePiece p6 = new PuzzlePiece("oo o \n" +
                "oooo \n" +
                " oooo\n" +
                "oooo \n" +
                " o o ");
        List<PuzzlePiece> puzzlePieces = List.of(p1, p2, p3, p4, p5, p6);
        for (int i = 0; i < puzzlePieces.size(); i++) {
            puzzlePieces.get(i).setId(i + 1);
        }
        Puzzle puzzle = new Puzzle(puzzlePieces);
        puzzle.setPuzzleRules(Set.of(DefaultCubePuzzleRules.getInstance()));
        CubePuzzleService cubePuzzleService = CubePuzzleServiceImpl.getInstance();
        String result = cubePuzzleService.solvePuzzle(puzzle);

        StringBuilder expectedResult = new StringBuilder();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ValidOutput.txt").getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                expectedResult.append(line).append("\n");
            }
        }
        assertEquals(expectedResult.toString(), result);
    }

}
