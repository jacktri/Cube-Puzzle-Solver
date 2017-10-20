package solver;

import solver.model.Puzzle;
import solver.model.PuzzlePiece;
import solver.service.CubePuzzleService;
import solver.service.impl.CubePuzzleServiceImpl;
import solver.validator.impl.DefaultCubePuzzleRules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CubePuzzleSolver {

    public static void main(String[] args) {
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

        List<PuzzlePiece> puzzlePieces = new ArrayList<>();
        puzzlePieces.add(p1);
        puzzlePieces.add(p2);
        puzzlePieces.add(p3);
        puzzlePieces.add(p4);
        puzzlePieces.add(p5);
        puzzlePieces.add(p6);

        for(int i = 0; i < puzzlePieces.size(); i++){
            puzzlePieces.get(i).setId(i+1);
        }
        Puzzle puzzle = new Puzzle(puzzlePieces);
        puzzle.setPuzzleRules(Set.of(DefaultCubePuzzleRules.getInstance()));
        CubePuzzleService cubePuzzleService = CubePuzzleServiceImpl.getInstance();
        cubePuzzleService.solvePuzzle(puzzle);

    }
}
