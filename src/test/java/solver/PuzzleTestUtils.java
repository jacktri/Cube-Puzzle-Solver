package solver;

public class PuzzleTestUtils {

    public static final int PUZZLE_SIZE = 5;
    public static final int FULL_PUZZLE_SIZE = 98;

    public static final String PUZZLE_STRING = "ooooo\n" +
            "ooooo\n" +
            "ooooo\n" +
            "ooooo\n" +
            "ooooo";


    public char[][] puzzlePieceArray(){
        char[][] puzzlePieceArray = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        for(int i = 0; i < PUZZLE_SIZE; i++){
            for(int j = 0; j < PUZZLE_SIZE; j++){
                puzzlePieceArray[i][j] = 'o';
            }
        }
        return puzzlePieceArray;
    }

    public char[][] piece() {
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = ' ';
        piece[0][1] = ' ';
        piece[0][2] = ' ';
        piece[0][3] = ' ';
        piece[0][4] = ' ';

        piece[1][0] = ' ';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = ' ';

        piece[2][0] = ' ';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = ' ';

        piece[3][0] = ' ';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = ' ';

        piece[4][0] = ' ';
        piece[4][1] = ' ';
        piece[4][2] = ' ';
        piece[4][3] = ' ';
        piece[4][4] = ' ';
        return piece;
    }

    public char[][] leftPiece() {
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = ' ';
        piece[0][1] = 'o';
        piece[0][2] = ' ';
        piece[0][3] = 'o';
        piece[0][4] = ' ';

        piece[1][0] = 'o';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = 'o';

        piece[2][0] = ' ';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = ' ';

        piece[3][0] = 'o';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = 'o';

        piece[4][0] = 'o';
        piece[4][1] = ' ';
        piece[4][2] = 'o';
        piece[4][3] = ' ';
        piece[4][4] = ' ';
        return piece;
    }

    public char[][] rightPiece() {
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = ' ';
        piece[0][1] = 'o';
        piece[0][2] = ' ';
        piece[0][3] = 'o';
        piece[0][4] = ' ';

        piece[1][0] = ' ';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = 'o';

        piece[2][0] = 'o';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = ' ';

        piece[3][0] = ' ';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = 'o';

        piece[4][0] = 'o';
        piece[4][1] = 'o';
        piece[4][2] = ' ';
        piece[4][3] = 'o';
        piece[4][4] = 'o';
        return piece;
    }

    public char[][] diamondPiece() {
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = ' ';
        piece[0][1] = ' ';
        piece[0][2] = 'o';
        piece[0][3] = ' ';
        piece[0][4] = ' ';

        piece[1][0] = ' ';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = ' ';

        piece[2][0] = 'o';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = 'o';

        piece[3][0] = ' ';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = ' ';

        piece[4][0] = ' ';
        piece[4][1] = ' ';
        piece[4][2] = 'o';
        piece[4][3] = ' ';
        piece[4][4] = ' ';
        return piece;
    }

    public char[][] mockPiece() {
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = 'o';
        piece[0][1] = 'o';
        piece[0][2] = 'o';
        piece[0][3] = 'o';
        piece[0][4] = 'o';

        piece[1][0] = 'o';
        piece[1][1] = ' ';
        piece[1][2] = ' ';
        piece[1][3] = ' ';
        piece[1][4] = 'o';

        piece[2][0] = 'o';
        piece[2][1] = ' ';
        piece[2][2] = ' ';
        piece[2][3] = ' ';
        piece[2][4] = 'o';

        piece[3][0] = 'o';
        piece[3][1] = ' ';
        piece[3][2] = ' ';
        piece[3][3] = ' ';
        piece[3][4] = 'o';

        piece[4][0] = 'o';
        piece[4][1] = 'o';
        piece[4][2] = 'o';
        piece[4][3] = 'o';
        piece[4][4] = 'o';
        return piece;
    }

    public char[][] mockPiece2() {
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = ' ';
        piece[0][1] = ' ';
        piece[0][2] = ' ';
        piece[0][3] = ' ';
        piece[0][4] = ' ';

        piece[1][0] = 'o';
        piece[1][1] = ' ';
        piece[1][2] = ' ';
        piece[1][3] = ' ';
        piece[1][4] = 'o';

        piece[2][0] = 'o';
        piece[2][1] = ' ';
        piece[2][2] = ' ';
        piece[2][3] = ' ';
        piece[2][4] = 'o';

        piece[3][0] = 'o';
        piece[3][1] = ' ';
        piece[3][2] = ' ';
        piece[3][3] = ' ';
        piece[3][4] = 'o';

        piece[4][0] = ' ';
        piece[4][1] = ' ';
        piece[4][2] = ' ';
        piece[4][3] = ' ';
        piece[4][4] = ' ';
        return piece;
    }

    public char[][] randomPiece1(){
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = ' ';
        piece[0][1] = 'o';
        piece[0][2] = ' ';
        piece[0][3] = 'o';
        piece[0][4] = 'o';

        piece[1][0] = 'o';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = 'o';

        piece[2][0] = ' ';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = ' ';

        piece[3][0] = 'o';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = 'o';

        piece[4][0] = ' ';
        piece[4][1] = ' ';
        piece[4][2] = 'o';
        piece[4][3] = ' ';
        piece[4][4] = 'o';
        return piece;
    }

    public char[][] randomPiece2(){
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = ' ';
        piece[0][1] = ' ';
        piece[0][2] = 'o';
        piece[0][3] = ' ';
        piece[0][4] = ' ';

        piece[1][0] = ' ';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = 'o';

        piece[2][0] = 'o';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = ' ';

        piece[3][0] = ' ';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = 'o';

        piece[4][0] = ' ';
        piece[4][1] = ' ';
        piece[4][2] = 'o';
        piece[4][3] = ' ';
        piece[4][4] = ' ';
        return piece;
    }

    public char[][] randomPiece3(){
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = 'o';
        piece[0][1] = ' ';
        piece[0][2] = 'o';
        piece[0][3] = ' ';
        piece[0][4] = ' ';

        piece[1][0] = 'o';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = 'o';

        piece[2][0] = ' ';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = ' ';

        piece[3][0] = 'o';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = 'o';

        piece[4][0] = ' ';
        piece[4][1] = 'o';
        piece[4][2] = ' ';
        piece[4][3] = 'o';
        piece[4][4] = ' ';
        return piece;
    }

    public char[][] randomPiece4(){
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = 'o';
        piece[0][1] = ' ';
        piece[0][2] = 'o';
        piece[0][3] = ' ';
        piece[0][4] = 'o';

        piece[1][0] = 'o';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = 'o';

        piece[2][0] = ' ';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = ' ';

        piece[3][0] = 'o';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = 'o';

        piece[4][0] = 'o';
        piece[4][1] = ' ';
        piece[4][2] = 'o';
        piece[4][3] = ' ';
        piece[4][4] = 'o';
        return piece;
    }

    public char[][] randomPiece5(){
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = 'o';
        piece[0][1] = 'o';
        piece[0][2] = ' ';
        piece[0][3] = 'o';
        piece[0][4] = ' ';

        piece[1][0] = 'o';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = ' ';

        piece[2][0] = ' ';
        piece[2][1] = 'o';
        piece[2][2] = 'o';
        piece[2][3] = 'o';
        piece[2][4] = 'o';

        piece[3][0] = 'o';
        piece[3][1] = 'o';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = ' ';

        piece[4][0] = ' ';
        piece[4][1] = 'o';
        piece[4][2] = ' ';
        piece[4][3] = 'o';
        piece[4][4] = ' ';
        return piece;
    }

    public char[][] printablePiece(){
        char[][] piece = new char[PUZZLE_SIZE][PUZZLE_SIZE];
        piece[0][0] = ' ';
        piece[0][1] = ' ';
        piece[0][2] = ' ';
        piece[0][3] = ' ';
        piece[0][4] = ' ';

        piece[1][0] = ' ';
        piece[1][1] = 'o';
        piece[1][2] = 'o';
        piece[1][3] = 'o';
        piece[1][4] = ' ';

        piece[2][0] = ' ';
        piece[2][1] = ' ';
        piece[2][2] = 'o';
        piece[2][3] = ' ';
        piece[2][4] = ' ';

        piece[3][0] = ' ';
        piece[3][1] = ' ';
        piece[3][2] = 'o';
        piece[3][3] = 'o';
        piece[3][4] = ' ';

        piece[4][0] = ' ';
        piece[4][1] = ' ';
        piece[4][2] = ' ';
        piece[4][3] = ' ';
        piece[4][4] = ' ';
        return piece;
    }
}
