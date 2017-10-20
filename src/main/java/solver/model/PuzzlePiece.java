package solver.model;

import solver.enums.PieceDirection;
import solver.enums.PiecePosition;

import java.util.Arrays;

import static solver.enums.PieceDirection.EAST;
import static solver.enums.PieceDirection.NORTH;
import static solver.enums.PieceDirection.SOUTH;
import static solver.enums.PieceDirection.WEST;
import static solver.utils.ArrayCopier.copyOf2dCharArray;


public class PuzzlePiece {
    private String stringRepresentation;
    private char[][] piece;
    private int id;
    private PieceDirection pieceDirection = NORTH;
    private PiecePosition piecePosition;

    public PuzzlePiece(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public PuzzlePiece() {

    }

    private PuzzlePiece(Builder builder) {
        if (builder.pieceDirection == null) {
            pieceDirection = NORTH;
        } else {
            pieceDirection = builder.pieceDirection;
        }
        stringRepresentation = builder.stringRepresentation;
        setPiece(builder.piece);
        setId(builder.id);
        piecePosition = builder.piecePosition;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public char[][] getPiece() {
        return piece;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PiecePosition getPiecePosition() {
        return piecePosition;
    }

    public void setPiecePosition(PiecePosition piecePosition) {
        this.piecePosition = piecePosition;
    }

    public PieceDirection getPieceDirection() {
        return pieceDirection;
    }

    public void setStringRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public void setPieceDirection(PieceDirection pieceDirection) {
        this.pieceDirection = pieceDirection;
    }

    public void rotatePieceDirectionRight() {
        if (NORTH.equals(pieceDirection)) {
            pieceDirection = EAST;
        } else if (EAST.equals(pieceDirection)) {
            pieceDirection = SOUTH;
        } else if (SOUTH.equals(pieceDirection)) {
            pieceDirection = WEST;
        } else if (WEST.equals(pieceDirection)) {
            pieceDirection = NORTH;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuzzlePiece)) return false;

        PuzzlePiece that = (PuzzlePiece) o;

        if (id != that.id) return false;
        if (stringRepresentation != null ? !stringRepresentation.equals(that.stringRepresentation) : that.stringRepresentation != null)
            return false;
        if (!Arrays.deepEquals(piece, that.piece)) return false;
        if (pieceDirection != that.pieceDirection) return false;
        return piecePosition == that.piecePosition;
    }

    @Override
    public int hashCode() {
        int result = stringRepresentation != null ? stringRepresentation.hashCode() : 0;
        result = 31 * result + Arrays.deepHashCode(piece);
        result = 31 * result + id;
        result = 31 * result + (pieceDirection != null ? pieceDirection.hashCode() : 0);
        result = 31 * result + (piecePosition != null ? piecePosition.hashCode() : 0);
        return result;
    }

    public void setPiece(char[][] piece) {
        this.piece = piece;
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    public static final class Builder {
        private String stringRepresentation;
        private char[][] piece;
        private int id;
        private PieceDirection pieceDirection;
        private PiecePosition piecePosition;

        private Builder() {
        }

        public Builder stringRepresentation(String val) {
            stringRepresentation = val;
            return this;
        }

        public Builder piece(char[][] val) {
            piece = val;
            return this;
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder pieceDirection(PieceDirection val) {
            pieceDirection = val;
            return this;
        }

        public Builder piecePosition(PiecePosition val) {
            piecePosition = val;
            return this;
        }

        public PuzzlePiece build() {
            return new PuzzlePiece(this);
        }
    }

    public PuzzlePiece copyPuzzlePiece() {
        return PuzzlePiece.newBuilder()
                .id(this.id)
                .piece(copyOf2dCharArray(this.piece))
                .pieceDirection(pieceDirection)
                .stringRepresentation(stringRepresentation)
                .piecePosition(piecePosition)
                .build();
    }
}
