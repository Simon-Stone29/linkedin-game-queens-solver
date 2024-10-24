import java.util.Arrays;
import java.util.Scanner;
public class Solver {
    static String[][] board;
    static String[][] resetBoard;
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter your board's dimensions: ");
        int width = scnr.nextInt();
       
        int height = width;
        board = new String[height][width];
        resetBoard = new String[height][width];
       
        int colorsLength = width;
        System.out.println("Please enter the colors that you used for your Queens board: ");
        String[] colors = new String[colorsLength];
        for(int i = 0; i<colorsLength+1; i++){
            String x = scnr.nextLine();
            if(x!=null && !x.equals("")){
                colors[i-1] = x;
            }
        }

        System.out.println("Enter the colors in the Queens board row by row: ");
         for (int i = 0; i < board.length; i ++){
            String row = scnr.nextLine();
            for(int j = 0; j<board[i].length; j++){
                String square = "" + row.charAt(j);
                board[i][j] = square;
                resetBoard[i][j] = square;
            }
         }
        scnr.close();
        System.out.println();
        System.out.println("Inputted Board: ");
        print(board);
        count(colors, board);
        System.out.println();
        System.out.println("Solved Board: ");
        board = (solve(board, colors));
        print(board);
    }
   
    public static void print(String[][] arr){
        System.out.println();
        for (int i = 0; i<arr.length; i++){
            for(int j = 0; j<arr[i].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    
    public static String[][] solve(String[][] board2, String[] colors){
        int[] before = count(colors, board2);
        int[] index = new int[board2[0].length];
        boolean done = false;
        String[][] board = board2;
        while(!done){
        board = randomize(board, index);
        int[] after = count(colors, board);
        done = oneInEach(before, after) && noneAdj(board);
        }
        return board;
    }


    public static boolean oneInEach(int[] before, int[] after){
        for(int i = 0; i<before.length; i++){
            if(after[i] != before[i]-1){
                return false;
            }
        }
        return true;
    }
    public static boolean noneAdj(String[][] board){
        boolean none = true;
        for (int i = 1; i<board.length-1; i++){
            for(int j = 1; j<board[i].length-1; j++){
                if(board[i][j].equals("Q")){
                if(board[i][j].equals(board[i+1][j+1]) || board[i][j].equals(board[i+1][j-1]) || board[i][j].equals(board[i-1][j+1]) || board[i][j].equals(board[i-1][j-1])){
                    none = false;
                    }
                }
            }
        }
        return none;
    }
    public static String[][] reset(){
        return resetBoard;
    }
    public static boolean contains(int x, int[] index){
        for(int i = 0; i<index.length; i++){
            if(index[i] == x){
                return true;
            }
        }
        return false;
    }

    public static String[][] randomize(String[][] board, int[] index){
        board = new String[resetBoard.length][resetBoard[1].length];
         
        for (int i = 0; i < resetBoard.length; i ++){
            for(int j = 0; j<resetBoard[i].length; j++){
                board[i][j] = resetBoard[i][j];
            }
         }

        for(int i = 0; i<index.length; i++){
            index[i] = -1;}
        for(int i = 0; i<board.length; i++){
            int queen = (int)(Math.random() * board[i].length);
            while(contains(queen, index)){
                queen = (int)(Math.random() * board[i].length);
            }
            index[i] = queen;
            board[i][queen] = "Q";
        }
        return board;
    }

    public static int[] count(String[] colors, String[][] board){
        int[] count = new int[colors.length];
        for(int col = 0; col<colors.length; col++){
            for (int i = 0; i<board.length; i++){
                for(int j = 0; j<board[i].length; j++){
                if(board[i][j].equals(colors[col])){
                    count[col] ++;
                }
            }
        }
    }
    return count;
    }
}
