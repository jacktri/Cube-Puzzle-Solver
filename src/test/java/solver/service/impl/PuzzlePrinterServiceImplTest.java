package solver.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import solver.exception.InvalidInputException;
import solver.model.Puzzle;
import solver.model.PuzzlePiece;
import solver.PuzzleTestUtils;
import solver.service.PuzzlePrinterService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.script.ScriptEngine.FILENAME;
import static org.junit.Assert.assertEquals;


public class PuzzlePrinterServiceImplTest {

    @InjectMocks
    private PuzzlePrinterServiceImpl testee = PuzzlePrinterServiceImpl.getInstance();

    private PuzzleTestUtils puzzleTestUtils = new PuzzleTestUtils();

    private static final String SIMPLE_OUTPUT = "               \n" +
            " ooo  ooo  ooo \n" +
            " ooo  ooo  ooo \n" +
            " ooo  ooo  ooo \n" +
            "               \n" +
            "               \n" +
            "      ooo      \n" +
            "      ooo      \n" +
            "      ooo      \n" +
            "               \n" +
            "               \n" +
            "      ooo      \n" +
            "      ooo      \n" +
            "      ooo      \n" +
            "               \n" +
            "               \n" +
            "      ooo      \n" +
            "      ooo      \n" +
            "      ooo      \n" +
            "               \n" +
            "\n";

    private static final String PRINTER_OUTPUT = "               \n" +
            " o o   oo  o o \n" +
            " ooo   o   ooo \n" +
            "   o  ooo  o   \n" +
            "               \n" +
            "               \n" +
            "      ooo      \n" +
            "       o       \n" +
            "       oo      \n" +
            "               \n" +
            "               \n" +
            "      ooo      \n" +
            "       o       \n" +
            "       oo      \n" +
            "               \n" +
            "               \n" +
            "       oo      \n" +
            "       o       \n" +
            "      ooo      \n" +
            "               \n" +
            "\n";
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generateUniqueStringsNoPuzzles() {
        List<Puzzle> puzzles = new ArrayList<>();
        Set<String> result = testee.generateUnique2dImages(puzzles);
        assertEquals(0, result.size());
    }

    @Test(expected = InvalidInputException.class)
    public void generateUniqueStringsNoPieces() {
        List<Puzzle> puzzles = List.of(Puzzle.newBuilder().build(),
                Puzzle.newBuilder().build());
        testee.generateUnique2dImages(puzzles);
    }

    @Test(expected = InvalidInputException.class)
    public void generateUniqueStringsNullPieceArrays() {
        List<PuzzlePiece> pieces = List.of(PuzzlePiece.newBuilder().build(),
                PuzzlePiece.newBuilder()
                        .build(),
                PuzzlePiece.newBuilder()
                        .build(),
                PuzzlePiece.newBuilder()
                        .build(),
                PuzzlePiece.newBuilder()
                        .build(),
                PuzzlePiece.newBuilder()
                        .build());
        List<Puzzle> puzzles = List.of(Puzzle.newBuilder()
                        .pieces(pieces)
                        .build(),
                Puzzle.newBuilder()
                        .pieces(pieces)
                        .build());
        testee.generateUnique2dImages(puzzles);
    }

    @Test
    public void generateUniqueStrings() {
        List<PuzzlePiece> pieces = List.of(PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build(),
                PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build(),
                PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build(),
                PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build(),
                PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build(),
                PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build());

        List<Puzzle> puzzles = List.of(Puzzle.newBuilder()
                        .finishedPieces(pieces)
                        .build(),
                Puzzle.newBuilder()
                        .finishedPieces(pieces)
                        .build());

        Set<String> result = testee.generateUnique2dImages(puzzles);
        assertEquals(1, result.size());
        assertEquals(SIMPLE_OUTPUT, result.iterator().next());
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionWithTooFewPieces() {
        List<PuzzlePiece> pieces = List.of(PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build());

        List<Puzzle> puzzles = List.of(Puzzle.newBuilder()
                .finishedPieces(pieces)
                .build());

        testee.generateUnique2dImages(puzzles);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionWithTooManyPieces() {

        PuzzlePiece piece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.piece())
                .build();
        List<PuzzlePiece> pieces = List.of(piece, piece, piece, piece, piece, piece, piece);

        List<Puzzle> puzzles = List.of(Puzzle.newBuilder()
                .finishedPieces(pieces)
                .build());

        testee.generateUnique2dImages(puzzles);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionWithCharArray() {

        PuzzlePiece piece = PuzzlePiece.newBuilder()
                .build();
        List<PuzzlePiece> pieces = List.of(piece, piece, piece, piece, piece, piece);

        List<Puzzle> puzzles = List.of(Puzzle.newBuilder()
                .finishedPieces(pieces)
                .build());

        testee.generateUnique2dImages(puzzles);
    }

    @Test
    public void generateOutput() {
        PuzzlePiece leftPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        PuzzlePiece topPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        PuzzlePiece rightPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        PuzzlePiece backPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        PuzzlePiece bottomPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        PuzzlePiece frontPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        Puzzle puzzle = Puzzle.newBuilder()
                .finishedPieces(List.of(leftPiece,topPiece,rightPiece,backPiece,bottomPiece,frontPiece))
                .build();

        List<Puzzle> puzzles = List.of(puzzle);

        Set<String> result = testee.generateUnique2dImages(puzzles);
        assertEquals(Set.of(PRINTER_OUTPUT), result);

    }

    @Test
    public void generateOutputRemovesIdenticalStringsKeepsDifferentStrings() {
        PuzzlePiece leftPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        PuzzlePiece topPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        PuzzlePiece rightPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        PuzzlePiece backPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        PuzzlePiece bottomPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        PuzzlePiece frontPiece = PuzzlePiece.newBuilder()
                .piece(puzzleTestUtils.printablePiece())
                .build();

        Puzzle puzzle = Puzzle.newBuilder()
                .finishedPieces(List.of(leftPiece,topPiece,rightPiece,backPiece,bottomPiece,frontPiece))
                .build();

        List<PuzzlePiece> pieces = List.of(PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build(),
                PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build(),
                PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build(),
                PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build(),
                PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build(),
                PuzzlePiece.newBuilder()
                        .piece(puzzleTestUtils.piece())
                        .build());

        Puzzle otherPuzzle = Puzzle.newBuilder()
                .finishedPieces(pieces)
                .build();

        List<Puzzle> puzzles = List.of(puzzle, puzzle, otherPuzzle);

        Set<String> result = testee.generateUnique2dImages(puzzles);
        assertEquals(Set.of(PRINTER_OUTPUT, SIMPLE_OUTPUT), result);
    }

    @Test
    public void printToFile(){
        testee.printToFile("example");

        try (BufferedReader br = new BufferedReader(new FileReader(PuzzlePrinterServiceImpl.FILE_NAME))) {

            String sCurrentLine = br.readLine() ;
            assertEquals("example",sCurrentLine);

        } catch (IOException e) {
            e.printStackTrace();
        }

        testee.printToFile("newText");

        try (BufferedReader br = new BufferedReader(new FileReader(PuzzlePrinterServiceImpl.FILE_NAME))) {

            String sCurrentLine = br.readLine() ;
            assertEquals("newText",sCurrentLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}