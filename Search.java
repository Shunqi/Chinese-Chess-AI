import java.util.ArrayList;
import java.util.Map;

public class Search {
	private Board board = new Board();
	private static int DEPTH = 3;

    public Node search(Board currentBoard) {
    	board = currentBoard;
        if (board.getPieces().size() < 7)
            DEPTH = 4;
        if (board.getPieces().size() < 4)
            DEPTH = 5;
        long startTime = System.currentTimeMillis();
        Node best = null;
        ArrayList<Node> moves = generateMoves('b');
        for (Node n : moves) {
            Piece eaten = board.getPiece(n.to);
            board.update(n.from, n.to);
            
            if (board.hasWin() == 'b'){
            	board.update(n.to, n.from);
                if (eaten != null) {
                    board.getPieces().put(eaten.getKey(), eaten);
                    board.putPiece(eaten);
                }
                return n;
            }

            n.value = alphaBeta(DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, 'r');
            if (best == null || n.value > best.value){
                best = n;
            }

            board.update(n.to, n.from);
            if (eaten != null) {
                board.getPieces().put(eaten.getKey(), eaten);
                board.putPiece(eaten);
            }
        }
        long finishTime = System.currentTimeMillis();
        System.out.println("Time used: " + (finishTime - startTime) + "ms\n");
        return best;
    }

    private int alphaBeta(int depth, int alpha, int beta, char color) {
        /* Return evaluation if reaching leaf node or any side won.*/
        if (depth == 0 || board.hasWin() != 't')
            return board.eval();
        ArrayList<Node> moves = generateMoves(color);

        for (Node n : moves) {
            Piece eaten = board.getPiece(n.to);
            board.update(n.from, n.to);
            int tempAlpha = Integer.MIN_VALUE;
            int tempBeta = Integer.MAX_VALUE;
            
            if (color == 'b') {
                tempAlpha = Math.max(tempAlpha, alphaBeta(depth - 1, alpha, beta, 'r'));
                tempBeta = beta;
            } else {
                tempBeta = Math.min(tempBeta, alphaBeta(depth - 1, alpha, beta, 'b'));
                tempAlpha = alpha;
            }
                
            board.update(n.to, n.from);
            if (eaten != null) {
                board.getPieces().put(eaten.getKey(), eaten);
                board.putPiece(eaten);
            }

            if (tempAlpha >= tempBeta){
                alpha = tempAlpha;
                beta = tempBeta;
                break;
            }
            alpha = Math.max(tempAlpha, alpha);
            beta = Math.min(tempBeta, beta);
        }

        return color == 'b' ? alpha : beta;
    }

    private ArrayList<Node> generateMoves(char color) {
        ArrayList<Node> moves = new ArrayList<Node>();
        for (Map.Entry<String, Piece> iterator : board.getPieces().entrySet()) {
            Piece piece = iterator.getValue();
            if ( piece.getColor() == color ){
	            for (int[] move : piece.move(board)){
	            	int[] pos = new int[]{piece.getPosition()[0], piece.getPosition()[1]};
	                moves.add(new Node(piece.getKey(), pos, move));
	            }
	        }
        }
        return moves;
    }
}