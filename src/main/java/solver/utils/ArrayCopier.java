package solver.utils;

public class ArrayCopier {

    private ArrayCopier(){

    }

    public static char[][][] copyOf3dCharArray(char[][][] original) {
        if(original == null){
            return new char[0][0][0];
        }
        char[][][] copy = new char[original.length][original.length][original.length];
        for (int i = 0; i < original.length; i++) {
            for(int j = 0; j < original.length; j++) {
                System.arraycopy(original[i][j], 0, copy[i][j], 0, original.length);
            }
        }
        return copy;
    }

    public static char[][] copyOf2dCharArray(char[][] original) {
        if(original == null){
            return new char[0][0];
        }

        char[][] copy = new char[original.length][original.length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original.length);
        }

        return copy;
    }

}
