package solver.validator.impl;

import solver.exception.InvalidInputException;
import solver.model.Puzzle;
import solver.model.PuzzlePiece;
import solver.validator.PuzzleRules;

import java.util.List;

public class DefaultCubePuzzleRules implements PuzzleRules {

    private static int PUZZLE_LENGTH = 5;
    private static int NUMBER_OF_PIECES = 6;
    private static int TARGET_SIZE = 98;

    private static DefaultCubePuzzleRules instance = null;

    private DefaultCubePuzzleRules() {
    }

    public static DefaultCubePuzzleRules getInstance(){
        if(instance == null){
            instance = new DefaultCubePuzzleRules();
        }
        return instance;
    }


    @Override
    public void invoke(Puzzle puzzle) {
        puzzle.setTargetSize(TARGET_SIZE);
        puzzle.setPuzzleLength(PUZZLE_LENGTH);
        List<PuzzlePiece> puzzlePieces = puzzle.getPieces();
        if(NUMBER_OF_PIECES != puzzlePieces.size()){
            throw new InvalidInputException();
        }

        puzzlePieces.forEach(piece -> piece.setPiece(generatePuzzlePieceArray(piece.getStringRepresentation())));
    }

        private char[][] generatePuzzlePieceArray(String stringRepresentation){
        char[][] pieceArray = new char[PUZZLE_LENGTH][PUZZLE_LENGTH];

        int x = 0;
        int y = 0;

        for(char c : stringRepresentation.toCharArray()){
            if(('o' == c || ' ' == c) && x < PUZZLE_LENGTH){
                pieceArray[x][y] = c;
                y++;
            }
            else if('\n' == c && PUZZLE_LENGTH == y){
                y = 0;
                x++;
            }
            else{
                throw new InvalidInputException();
            }
        }
        if(x < PUZZLE_LENGTH - 1 || y < PUZZLE_LENGTH){
            throw new InvalidInputException();
        }
        return pieceArray;
    }


}
