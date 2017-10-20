package solver.service.impl;

import solver.exception.InvalidInputException;
import solver.model.Puzzle;
import solver.model.PuzzlePiece;
import solver.service.PuzzleRotationService;
import solver.service.PuzzleSideFillerService;
import solver.service.PuzzleSolverService;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PuzzleSolverServiceImpl implements PuzzleSolverService {

    private static final int NUMBER_OF_DIRECTIONS = 4;
    private static final int PIECE_COUNT = 6;
    private static PuzzleSolverServiceImpl instance = null;
    private PuzzleSideFillerService puzzleSideFillerService = PuzzleSideFillerServiceServiceImpl.getInstance();
    private PuzzleRotationService puzzleRotationService = PuzzleRotationServiceImpl.getInstance();

    public static PuzzleSolverServiceImpl getInstance() {
        if (instance == null) {
            instance = new PuzzleSolverServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Puzzle> solve(Puzzle puzzle) {
        if (puzzle.getPieces().size() != PIECE_COUNT) {
            throw new InvalidInputException();
        }
        return solvePuzzle(puzzle);
    }

    private List<Puzzle> solvePuzzle(Puzzle puzzle) {
        puzzle.resetPuzzle();
        Set<Puzzle> validPuzzles = new LinkedHashSet<>();
        validPuzzles.add(puzzle.copyPuzzle());

        for (int i = 0; i < PIECE_COUNT; i++) {
            int fillPosition = i;
            removeIdenticalSolutions(validPuzzles);
            validPuzzles = validPuzzles.stream()
                    .map(validPuzzle -> generateValidPuzzles(fillPosition, validPuzzle))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
        }
        return validPuzzles.stream()
                .filter(p -> p.getFilledSquareCount() == p.getTargetSize())
                .collect(Collectors.toList());
    }

    private Set<Puzzle> generateValidPuzzles(int fillCount, Puzzle puzzle) {
        Set<Puzzle> validPuzzles = new LinkedHashSet<>();
        Puzzle tempPuzzle = puzzle.copyPuzzle();
        for (int i = 0; i < tempPuzzle.getPieces().size(); i++) {
            for(int j = 0; j < NUMBER_OF_DIRECTIONS; j++) {
            PuzzlePiece piece = tempPuzzle.getPieces().get(i);
            if (puzzleSideFillerService.fillSideWithInt(fillCount, tempPuzzle, piece)) {
                tempPuzzle.addFinishedPiece(piece);
                tempPuzzle.getPieces().remove(piece);
                validPuzzles.add(tempPuzzle);
            }
            tempPuzzle = puzzle.copyPuzzle();
                for(int k = j; k > -1; k--){
                    puzzleRotationService.rotateSideRight90Degrees(tempPuzzle.getPieces().get(i));
                }
            }
        }
        return validPuzzles;
    }

    private void removeIdenticalSolutions(Set<Puzzle> puzzles){
        if(puzzles.isEmpty()){
            return;
        }

        for(Puzzle puzzle : puzzles){
            Set<Puzzle> newPuzzles = searchAndRemovePuzzles(puzzles,puzzle);
            if(newPuzzles.equals(puzzles)){
                return;
            }
        }
    }

    private Set<Puzzle> searchAndRemovePuzzles(Set<Puzzle> puzzles, Puzzle puzzle){
        Puzzle puzzleToRemove = searchForIdenticalSolutions(puzzle, puzzles);
        while (null != puzzleToRemove) {
            puzzles.remove(puzzleToRemove);
            // prevents it getting stuck in a while loop if for some reason it can't remove a puzzle from the set
            if(!puzzles.remove(puzzleToRemove)){
                break;
            }
            puzzleToRemove = searchForIdenticalSolutions(puzzle, puzzles);
        }
        return puzzles;
    }

    private Puzzle searchForIdenticalSolutions(Puzzle puzzle, Set<Puzzle> puzzles){

        Puzzle puzzleToRemove = null;
        for(Puzzle comparedPuzzle : puzzles){
            if(!puzzle.equals(comparedPuzzle)){
                puzzleToRemove = comparePuzzle(puzzle,comparedPuzzle);
                if(puzzleToRemove != null){
                    return puzzleToRemove;
                }
            }
        }
        return puzzleToRemove;
    }

    private Puzzle comparePuzzle(Puzzle puzzle, Puzzle comparePuzzle) {
        List<PuzzlePiece> puzzlePieces = puzzle.getFinishedPieces();
        List<PuzzlePiece> comparedPieces = comparePuzzle.getFinishedPieces();

        List<char[][]> piecePositions = puzzlePieces.stream().map(PuzzlePiece::getPiece)
                .collect(Collectors.toList());
        List<char[][]> comparedPiecePositions = comparedPieces.stream().map(PuzzlePiece::getPiece)
                .collect(Collectors.toList());


        if(Arrays.deepEquals(piecePositions.toArray(),comparedPiecePositions.toArray())){
            return comparePuzzle;
        }
        return null;
    }
}
