import java.util.HashMap;
import java.util.Map;

public class Board{
    private final int BOARD_WIDTH = 9, BOARD_HEIGHT = 10;
    private HashMap<String, Piece> pieces;
    private Piece[][] board = new Piece[BOARD_HEIGHT][BOARD_WIDTH];

    public Board initBoard(){
    	initPieces();
        for (Map.Entry<String, Piece> iterator : pieces.entrySet()){
        	int[] pos = iterator.getValue().getPosition();
        	board[pos[0]][pos[1]] = iterator.getValue();
        }
        return this;
    }

    public void putPiece(Piece putin){
    	board[putin.getPosition()[0]][putin.getPosition()[1]] = putin;
    }

    public char hasWin(){
    	if ( pieces.get("bw0") == null )
    		return 'r';
    	else if ( pieces.get("rw0") == null )
    		return 'b';
    	else return 't';
    }

    public HashMap<String, Piece> getPieces(){
    	return pieces;
    }

    public Piece getPiece(int row, int col){
    	return board[row][col];
    }

    public Piece getPiece(int[] position){
    	return getPiece(position[0], position[1]);
    }

    public boolean isValid(int row, int col){
    	return !(row < 0 || row >= BOARD_HEIGHT || col < 0 || col >= BOARD_WIDTH);
    }

    public boolean isEmpty(int row, int col){
    	return (board[row][col] == null);
    }

    public void update(int rS, int cS, int rE, int cE){
    	Piece target = getPiece(rS, cS);

    	if (board[rE][cE] != null)
    		pieces.remove(board[rE][cE].getKey());

        board[rS][cS] = null;
        board[rE][cE] = target;
        if (target != null)
        	target.setPosition(rE, cE);
    }

    public void update(int[] start, int[] end){
    	this.update(start[0], start[1], end[0], end[1]);
    }


    public String toString(){
    	String boardState = "";
    	for (int i = BOARD_HEIGHT - 1; i > -1; i--){
    		for (int j = 0; j < BOARD_WIDTH; j++){
    			if (j == 0)
    				boardState += i + " ";
    			if (board[i][j] == null)
    				boardState += "  ";    				
    			else if (board[i][j].getColor() == 'r')
    				boardState += board[i][j].getCharacter() + " ";
    			else
    				boardState += Character.toUpperCase(board[i][j].getCharacter()) + " ";
    		}
    		boardState += "\n";
    	}
    	boardState += "  0 1 2 3 4 5 6 7 8\n";
    	return boardState;
    }

    private void initPieces() {
        pieces = new HashMap<String, Piece>();
        pieces.put("rj0", new Piece("rj0", new int[]{0, 0}));
        pieces.put("rm0", new Piece("rm0", new int[]{0, 1}));
        pieces.put("rx0", new Piece("rx0", new int[]{0, 2}));
        pieces.put("rs0", new Piece("rs0", new int[]{0, 3}));
        pieces.put("rw0", new Piece("rw0", new int[]{0, 4}));
        pieces.put("rs1", new Piece("rs1", new int[]{0, 5}));
        pieces.put("rx1", new Piece("rx1", new int[]{0, 6}));
        pieces.put("rm1", new Piece("rm1", new int[]{0, 7}));
        pieces.put("rj1", new Piece("rj1", new int[]{0, 8}));
        pieces.put("rp0", new Piece("rp0", new int[]{2, 1}));
        pieces.put("rp1", new Piece("rp1", new int[]{2, 7}));
        pieces.put("rz0", new Piece("rz0", new int[]{3, 0}));
        pieces.put("rz1", new Piece("rz1", new int[]{3, 2}));
        pieces.put("rz2", new Piece("rz2", new int[]{3, 4}));
        pieces.put("rz3", new Piece("rz3", new int[]{3, 6}));
        pieces.put("rz4", new Piece("rz4", new int[]{3, 8}));

        pieces.put("bj0", new Piece("bj0", new int[]{9, 0}));
        pieces.put("bm0", new Piece("bm0", new int[]{9, 1}));
        pieces.put("bx0", new Piece("bx0", new int[]{9, 2}));
        pieces.put("bs0", new Piece("bs0", new int[]{9, 3}));
        pieces.put("bw0", new Piece("bw0", new int[]{9, 4}));
        pieces.put("bs1", new Piece("bs1", new int[]{9, 5}));
        pieces.put("bx1", new Piece("bx1", new int[]{9, 6}));
        pieces.put("bm1", new Piece("bm1", new int[]{9, 7}));
        pieces.put("bj1", new Piece("bj1", new int[]{9, 8}));
        pieces.put("bp0", new Piece("bp0", new int[]{7, 1}));
        pieces.put("bp1", new Piece("bp1", new int[]{7, 7}));
        pieces.put("bz0", new Piece("bz0", new int[]{6, 0}));
        pieces.put("bz1", new Piece("bz1", new int[]{6, 2}));
        pieces.put("bz2", new Piece("bz2", new int[]{6, 4}));
        pieces.put("bz3", new Piece("bz3", new int[]{6, 6}));
        pieces.put("bz4", new Piece("bz4", new int[]{6, 8}));
    }

    public int eval(){
        int redValue = 0;
        int blackValue = 0;
        for (Map.Entry<String, Piece> stringPieceEntry : pieces.entrySet()) {
            Piece piece = stringPieceEntry.getValue();
            int[] reversePosition = new int[]{BOARD_HEIGHT - 1 - piece.getPosition()[0], piece.getPosition()[1]};
            switch (piece.getCharacter()) {
                case 'w':
                    if (piece.getColor() == 'r'){
                    	redValue += evalPieceValue(0);
                    }
                    else{
                    	blackValue += evalPieceValue(0);
                    }
                    break;
                case 's':
                    if (piece.getColor() == 'r'){
                    	redValue += evalPieceValue(1);
                    }
                    else{
                    	blackValue += evalPieceValue(1);
                    }
                    break;
                case 'x':
                    if (piece.getColor() == 'r'){
                    	redValue += evalPieceValue(2);
                    }
                    else{
                    	blackValue += evalPieceValue(2);
                    }
                    break;
                case 'm':
                    if (piece.getColor() == 'r') {
                    	redValue += evalPieceValue(3);
                    	redValue += evalPiecePosition(3, reversePosition);
                    	redValue += evalFlexibility(3) * piece.move(this).size() * 0.2;
                    } else {
                    	blackValue += evalPieceValue(3);
                    	blackValue += evalPiecePosition(3, piece.getPosition());
                    	blackValue += evalFlexibility(3) * piece.move(this).size() * 0.2;
                    }
                    break;
                case 'j':
                    if (piece.getColor() == 'r') {
                    	redValue += evalPieceValue(4);
                    	redValue += evalPiecePosition(4, reversePosition);
                    } else {
                        blackValue += evalPieceValue(4);
                        blackValue += evalPiecePosition(4, piece.getPosition());
                    }
                    break;
                case 'p':
                    if (piece.getColor() == 'r') {
                    	redValue += evalPieceValue(5);
                    	redValue += evalPiecePosition(5, reversePosition);
                    } else {
                        blackValue += evalPieceValue(5);
                        blackValue += evalPiecePosition(5, piece.getPosition());
                    }
                    break;
                case 'z':
                    if (piece.getColor() == 'r') {
                    	redValue += evalPieceValue(6);
                    	redValue += evalPiecePosition(6, reversePosition);
                    } else {
                        blackValue += evalPieceValue(6);
                        blackValue += evalPiecePosition(6, piece.getPosition());
                    }
                    break;
            }
        }
        return blackValue - redValue;
    }

    private int evalPieceValue(int p) {
        /* w | s | x | m | j | p | z*/
        int[] pieceValue = new int[]{6000, 120, 120, 270, 600, 285, 30};
        if ( pieces.size() < 16 ){
        	pieceValue[3] = 320;
        	pieceValue[5] = 260;
        }
        return pieceValue[p];
    }
    
    private int evalFlexibility(int p){
    	int[] flexibility = new int[]{0, 7, 3, 7, 1, 13, 15};
		return flexibility[p];    	
    }

    private int evalPiecePosition(int p, int[] pos) {
        int[] pPosition = new int[]{
                6, 4, 0, -10, -12, -10, 0, 4, 6,
                2, 2, 0, -4, -14, -4, 0, 2, 2,
                2, 2, 0, -10, -8, -10, 0, 2, 2,
                0, 0, -2, 4, 10, 4, -2, 0, 0,
                0, 0, 0, 2, 8, 2, 0, 0, 0,
                -2, 0, 4, 2, 6, 2, 4, 0, -2,
                0, 0, 0, 2, 4, 2, 0, 0, 0,
                4, 0, 8, 6, 10, 6, 8, 0, 4,
                0, 2, 4, 6, 6, 6, 4, 2, 0,
                0, 0, 2, 6, 6, 6, 2, 0, 0
        };
        int[] mPosition = new int[]{
                4, 8, 16, 12, 4, 12, 16, 8, 4,
                4, 10, 28, 16, 8, 16, 28, 10, 4,
                12, 14, 16, 20, 18, 20, 16, 14, 12,
                8, 24, 18, 24, 20, 24, 18, 24, 8,
                6, 16, 14, 18, 16, 18, 14, 16, 6,
                4, 12, 16, 14, 12, 14, 16, 12, 4,
                2, 6, 8, 6, 10, 6, 8, 6, 2,
                4, 2, 8, 8, 4, 8, 8, 2, 4,
                0, 2, 4, 4, -2, 4, 4, 2, 0,
                0, -4, 0, 0, 0, 0, 0, -4, 0
        };
        int[] jPosition = new int[]{
                14, 14, 12, 18, 16, 18, 12, 14, 14,
                16, 20, 18, 24, 26, 24, 18, 20, 16,
                12, 12, 12, 18, 18, 18, 12, 12, 12,
                12, 18, 16, 22, 22, 22, 16, 18, 12,
                12, 14, 12, 18, 18, 18, 12, 14, 12,
                12, 16, 14, 20, 20, 20, 14, 16, 12,
                6, 10, 8, 14, 14, 14, 8, 10, 6,
                4, 8, 6, 14, 12, 14, 6, 8, 4,
                8, 4, 8, 16, 8, 16, 8, 4, 8,
                -2, 10, 6, 14, 12, 14, 6, 10, -2
        };
        int[] zPosition = new int[]{
                0, 3, 6, 9, 12, 9, 6, 3, 0,
                18, 36, 56, 80, 120, 80, 56, 36, 18,
                14, 26, 42, 60, 80, 60, 42, 26, 14,
                10, 20, 30, 34, 40, 34, 30, 20, 10,
                6, 12, 18, 18, 20, 18, 18, 12, 6,
                2, 0, 8, 0, 8, 0, 8, 0, 2,
                0, 0, -2, 0, 4, 0, -2, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        if (p == 3) return mPosition[pos[0] * 9 + pos[1]];
        if (p == 4) return jPosition[pos[0] * 9 + pos[1]];
        if (p == 5) return pPosition[pos[0] * 9 + pos[1]];
        if (p == 6) return zPosition[pos[0] * 9 + pos[1]];
        return -1;
    }
}