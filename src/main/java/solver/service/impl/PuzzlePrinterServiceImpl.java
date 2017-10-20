package solver.service.impl;

import solver.exception.InvalidInputException;
import solver.model.Puzzle;
import solver.model.PuzzlePiece;
import solver.service.PuzzlePrinterService;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static solver.constant.PuzzleConstants.BACK;
import static solver.constant.PuzzleConstants.BOTTOM;
import static solver.constant.PuzzleConstants.FRONT;
import static solver.constant.PuzzleConstants.LEFT;
import static solver.constant.PuzzleConstants.RIGHT;
import static solver.constant.PuzzleConstants.TOP;
import static solver.utils.FileWriter.writeToFile;

public class PuzzlePrinterServiceImpl implements PuzzlePrinterService {

    public static final String FILE_NAME = "output.txt";
    private static PuzzlePrinterServiceImpl instance = null;
    private static final int PIECE_COUNT = 6;
    private static final int PUZZLE_LENGTH = 5;
    private static final Logger logger = Logger.getLogger("PuzzlePrinterServiceImpl");

    private PuzzlePrinterServiceImpl() {
    }

    public static PuzzlePrinterServiceImpl getInstance() {
        if (instance == null) {
            instance = new PuzzlePrinterServiceImpl();
        }
        return instance;
    }

    @Override
    public Set<String> generateUnique2dImages(List<Puzzle> puzzles) {
        Set<String> setOfPrintableCubes = new LinkedHashSet<>();

        for(Puzzle puzzle : puzzles){
            String printableRepresentation = convertToPrintableFormat(puzzle);
            setOfPrintableCubes.add(printableRepresentation);
            puzzle.setPrintableRepresentation(printableRepresentation);
        }
        return setOfPrintableCubes;
    }

    private String convertToPrintableFormat(Puzzle puzzle) {
        StringBuilder stringBuilder = new StringBuilder();
        List<PuzzlePiece> pieces = puzzle.getFinishedPieces();
        if(null == pieces || pieces.size() != PIECE_COUNT){
            throw new InvalidInputException();
        }

        char[][] left = pieces.get(LEFT).getPiece();
        char[][] right = pieces.get(RIGHT).getPiece();
        char[][] top = pieces.get(TOP).getPiece();
        char[][] bottom = pieces.get(BOTTOM).getPiece();
        char[][] front = pieces.get(FRONT).getPiece();
        char[][] back = pieces.get(BACK).getPiece();

        if(null == left || null == right || null == top || null == bottom || null == front || null == back){
            throw new InvalidInputException();
        }

        for (int i = PUZZLE_LENGTH-1; i > -1; i--) {
            for (int j = PUZZLE_LENGTH-1; j > -1; j--) {
                stringBuilder.append(left[j][i]);
            }
            for (int j = 0; j < PUZZLE_LENGTH; j++) {
                stringBuilder.append(top[i][j]);
            }
            for (int j = 0; j < PUZZLE_LENGTH; j++) {
                stringBuilder.append(right[j][i]);
            }
            stringBuilder.append("\n");
        }

        addBackOrBottomToString(stringBuilder, back.length,back);
        addBackOrBottomToString(stringBuilder, bottom.length, bottom);
        addFrontToString(stringBuilder, front.length,front);
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    private void addFrontToString(StringBuilder stringBuilder, int length, char[][] puzzle) {
        for (int i = PUZZLE_LENGTH-1; i > -1; i--) {
            for (int j = 0; j < length; j++) {
                stringBuilder.append(' ');
            }
            for (int j = 0; j < PUZZLE_LENGTH; j++) {
                stringBuilder.append(puzzle[i][j]);
            }
            for (int j = 0; j < length; j++) {
                stringBuilder.append(' ');
            }
            stringBuilder.append("\n");
        }
    }

    private void addBackOrBottomToString(StringBuilder stringBuilder, int length, char[][] puzzle) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                stringBuilder.append(' ');
            }
            for (int j = 0; j < length; j++) {
                stringBuilder.append(puzzle[i][j]);
            }
            for (int j = 0; j < length; j++) {
                stringBuilder.append(' ');
            }
            stringBuilder.append("\n");
        }
    }

    public void printToFile(String result) {
        try {
            writeToFile(result, FILE_NAME);
        } catch (IOException exception) {
            logger.severe(exception.getMessage());
        }
    }
}
