import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class ChessGame {
    private Board board = new Board();

    public static void main(String[] args) throws IOException {
        ChessGame game = new ChessGame();
        game.info();
        game.init();
        game.start();
    }

    private void info(){
        System.out.println("******************************************************");
        System.out.println("***                 Version 1.0                    ***");
        System.out.println("***  Welcome to this simple Chinese Chess Program  ***");
        System.out.println("***You are playing on the Red side against Black AI***");
        System.out.println("***      Lower case: Red    Upper case: Black      ***");
        System.out.println("******************************************************");
        System.out.println("***********   Piece Representation Rules   ***********");
        System.out.println("***********    W: King  J: Rook  Z: Pawn   ***********");
        System.out.println("***********    M: Knight   S: Assistant    ***********");
        System.out.println("***********    P: Cannon    X: Elephant    ***********");
        System.out.println("******************************************************\n");
    }

    private void init() {
        board = board.initBoard();
        System.out.println(board);
    }

    private void start() throws IOException{
        Scanner in = new Scanner(System.in);
        Search search = new Search();
        while ( board.hasWin() == 't' ){    
            System.out.print("Enter the piece you want to move(two int number seperated by \' \', e.g. \'0  1\' -> m): ");
            int rStart = in.nextInt();
            int cStart = in.nextInt();
            if ( !board.isValid(rStart, cStart) || board.isEmpty(rStart, cStart) || board.getPiece(rStart, cStart).getColor() == 'b' )
                System.out.println("Invalid Input! Please enter again!");
            else {
                System.out.print("Enter the location you want to move to: ");
                int rEnd = in.nextInt();
                int cEnd = in.nextInt();
                for (int[] move : board.getPiece(rStart, cStart).move(board)){
                    if (Arrays.equals(move, new int[]{rEnd, cEnd})){
                        board.update(rStart, cStart, rEnd, cEnd);
                        System.out.println("\n" + board);
                        if (board.hasWin() != 't'){
                            System.out.println("Red wins! Game over!");
                            System.exit(0);
                        }
                        System.out.println("AI's turn! Please wait!");
                        Node aiMove = search.search(board);
                        board.update(aiMove.from, aiMove.to);
                        System.out.println(board);
                        break;
                    }
                }
            }
                                    
        }
        System.out.println(board);
        System.out.println("Black wins! Game over!");

        in.close();
    }
}