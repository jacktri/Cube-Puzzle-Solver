package solver.model;

import solver.validator.PuzzleRules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static solver.utils.ArrayCopier.copyOf3dCharArray;

public class Puzzle{
    private List<PuzzlePiece> pieces = new ArrayList<>();
    private List<PuzzlePiece> finishedPieces = new ArrayList<>();
    private char[][][] fullPuzzle;
    private int puzzleLength;
    private Set<PuzzleRules> puzzleRules = new HashSet<>();
    private int filledSquareCount = 0;
    private int targetSize = -1;
    private String printableRepresentation;

    public Puzzle(List<PuzzlePiece> pieces) {
        this.pieces = pieces;
    }

    public Puzzle(){

    }

    private Puzzle(Builder builder) {
        setPieces(builder.pieces);
        setFullPuzzle(builder.fullPuzzle);
        setPuzzleLength(builder.puzzleLength);
        setPuzzleRules(builder.puzzleRules);
        setFilledSquareCount(builder.filledSquareCount);
        setTargetSize(builder.targetSize);
        setFinishedPieces(builder.finishedPieces);
        setPrintableRepresentation(builder.printableRepresentation);
        if(builder.finishedPieces == null){
            finishedPieces = new ArrayList<>();
        }
        if(builder.pieces == null){
            pieces = new ArrayList<>();
        }
        if(builder.puzzleRules == null){
            puzzleRules = new HashSet<>();
        }
    }

    public String getPrintableRepresentation() {
        return printableRepresentation;
    }

    public void setPrintableRepresentation(String printableRepresentation) {
        this.printableRepresentation = printableRepresentation;
    }

    public void addFinishedPiece(PuzzlePiece puzzlePiece){
        finishedPieces.add(puzzlePiece);
    }

    public List<PuzzlePiece> getFinishedPieces() {
        return finishedPieces;
    }

    public void setFinishedPieces(List<PuzzlePiece> finishedPieces) {
        this.finishedPieces = finishedPieces;
    }

    public void setPuzzleRules(Set<PuzzleRules> puzzleRules) {
        this.puzzleRules = puzzleRules;
    }

    public void setFilledSquareCount(int filledSquareCount) {
        this.filledSquareCount = filledSquareCount;
    }

    public int getTargetSize() {
        return targetSize;
    }

    public void setTargetSize(int targetSize) {
        this.targetSize = targetSize;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void newSquare(){
        filledSquareCount++;
    }

    public void resetPuzzle(){
        filledSquareCount = 0;
        fullPuzzle = new char[puzzleLength][puzzleLength][puzzleLength];
    }

    public int getFilledSquareCount(){
        return filledSquareCount;
    }

    public List<PuzzlePiece> getPieces() {
        return pieces;
    }

    public char[][][] getFullPuzzle() {
        return fullPuzzle;
    }

    public void setFullPuzzle(char[][][] fullPuzzle) {
        this.fullPuzzle = fullPuzzle;
    }

    public int getPuzzleLength() {
        return puzzleLength;
    }

    public void setPuzzleLength(int puzzleLength) {
        this.puzzleLength = puzzleLength;
    }

    public void setPieces(List<PuzzlePiece> pieces) {
        this.pieces = pieces;
    }

    public Set<PuzzleRules> getPuzzleRules() {
        return puzzleRules;
    }

    public void addPuzzleRules(PuzzleRules puzzleRules) {
        this.puzzleRules.add(puzzleRules);
    }

    public Puzzle copyPuzzle(){
        Builder builder =  Puzzle.newBuilder()
                .puzzleRules(this.puzzleRules)
                .puzzleLength(this.puzzleLength)
                .fullPuzzle(copyOf3dCharArray(fullPuzzle))
                .targetSize(this.targetSize)
                .printableRepresentation(this.printableRepresentation)
                .filledSquareCount(this.filledSquareCount);

        if(pieces != null) {
            List<PuzzlePiece> pieces = new ArrayList<>();

            this.pieces.forEach(piece -> pieces.add(piece.copyPuzzlePiece()));
            builder = builder.pieces(pieces);
        }
        if(finishedPieces != null) {
            List<PuzzlePiece> finishedPieces = new ArrayList<>();
            this.finishedPieces.forEach(piece -> finishedPieces.add(piece.copyPuzzlePiece()));
            builder = builder.finishedPieces(finishedPieces);
        }
        return builder.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Puzzle)) return false;

        Puzzle puzzle = (Puzzle) o;

        if (puzzleLength != puzzle.puzzleLength) return false;
        if (filledSquareCount != puzzle.filledSquareCount) return false;
        if (targetSize != puzzle.targetSize) return false;
        if (pieces != null ? !pieces.equals(puzzle.pieces) : puzzle.pieces != null) return false;
        if (finishedPieces != null ? !finishedPieces.equals(puzzle.finishedPieces) : puzzle.finishedPieces != null)
            return false;
        if (!Arrays.deepEquals(fullPuzzle, puzzle.fullPuzzle)) return false;
        if (puzzleRules != null ? !puzzleRules.equals(puzzle.puzzleRules) : puzzle.puzzleRules != null) return false;
        return printableRepresentation != null ? printableRepresentation.equals(puzzle.printableRepresentation) : puzzle.printableRepresentation == null;
    }

    @Override
    public int hashCode() {
        int result = pieces != null ? pieces.hashCode() : 0;
        result = 31 * result + (finishedPieces != null ? finishedPieces.hashCode() : 0);
        result = 31 * result + Arrays.deepHashCode(fullPuzzle);
        result = 31 * result + puzzleLength;
        result = 31 * result + (puzzleRules != null ? puzzleRules.hashCode() : 0);
        result = 31 * result + filledSquareCount;
        result = 31 * result + targetSize;
        result = 31 * result + (printableRepresentation != null ? printableRepresentation.hashCode() : 0);
        return result;
    }

    public static final class Builder {
        private String printableRepresentation;
        private List<PuzzlePiece> pieces;
        private List<PuzzlePiece> finishedPieces;
        private char[][][] fullPuzzle;
        private int puzzleLength;
        private Set<PuzzleRules> puzzleRules;
        private int filledSquareCount;
        private int targetSize;

        private Builder() {
        }

        public Builder printableRepresentation(String val){
            printableRepresentation = val;
            return this;
        }

        public Builder pieces(List<PuzzlePiece> val) {
            pieces = val;
            return this;
        }

        public Builder finishedPieces(List<PuzzlePiece> val) {
            finishedPieces = val;
            return this;
        }

        public Builder fullPuzzle(char[][][] val) {
            fullPuzzle = val;
            return this;
        }

        public Builder puzzleLength(int val) {
            puzzleLength = val;
            return this;
        }

        public Builder puzzleRules(Set<PuzzleRules> val) {
            puzzleRules = val;
            return this;
        }

        public Builder filledSquareCount(int val) {
            filledSquareCount = val;
            return this;
        }

        public Builder targetSize(int val) {
            targetSize = val;
            return this;
        }

        public Puzzle build() {
            return new Puzzle(this);
        }
    }

}
