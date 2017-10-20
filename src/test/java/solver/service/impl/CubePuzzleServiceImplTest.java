package solver.service.impl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import solver.model.Puzzle;
import solver.service.PuzzlePrinterService;
import solver.service.PuzzleSolverService;
import solver.validator.PuzzleValidator;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class CubePuzzleServiceImplTest {

    @InjectMocks
    private CubePuzzleServiceImpl testee = CubePuzzleServiceImpl.getInstance();

    @Mock
    private PuzzleValidator puzzleValidator;

    @Mock
    private PuzzleSolverService puzzleSolverService;

    @Mock
    private PuzzlePrinterService puzzlePrinterService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void solveNoSuccessfulAnswers(){
        Puzzle puzzle = Puzzle.newBuilder()
                .build();
        List<Puzzle> puzzles = new ArrayList<>();
        when(puzzleSolverService.solve(puzzle)).thenReturn(puzzles);

        testee.solvePuzzle(puzzle);
        Mockito.verifyZeroInteractions(puzzlePrinterService);
    }

    @Test
    public void solveShouldReturnString(){
        Puzzle puzzle = Puzzle.newBuilder()
                .build();
        List<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(Puzzle.newBuilder()
                .build());

        Set<String> setOfStrings = new LinkedHashSet<>();
        setOfStrings.add("sampleInput");
        setOfStrings.add("moreInput");
        when(puzzleSolverService.solve(puzzle)).thenReturn(puzzles);
        when(puzzlePrinterService.generateUnique2dImages(puzzles)).thenReturn(setOfStrings);

        String result = testee.solvePuzzle(puzzle);
        Mockito.verify(puzzlePrinterService).generateUnique2dImages(puzzles);
        assertEquals("sampleInputmoreInput",result);
    }

    @Test
    public void solveShouldReturnNoValidAnswersString(){
        Puzzle puzzle = Puzzle.newBuilder()
                .build();
        List<Puzzle> puzzles = new ArrayList<>();

        when(puzzleSolverService.solve(puzzle)).thenReturn(puzzles);

        String result = testee.solvePuzzle(puzzle);
        assertEquals("No valid answers",result);
    }


}