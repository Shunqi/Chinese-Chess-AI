import java.util.ArrayList;

public class Piece {
    private String key;
    private char color;
    private char character;
    private char index;
    private int[] position = new int[2];

    public Piece(String name, int[] position) {
        this.key = name;
        this.color = name.charAt(0);
        this.character = name.charAt(1);
        this.index = name.charAt(2);
        this.position = position;
    }

    public String getKey(){
        return this.key;
    }

    public char getColor(){
        return this.color;
    }

    public char getCharacter(){
        return this.character;
    }

    public char getIndex(){
        return this.index;
    }

    public int[] getPosition(){
        return this.position;
    }

    public void setPosition(int r, int c){
        this.position[0] = r;
        this.position[1] = c;
    }

    public ArrayList<int[]> move(Board board){
        switch (character) {
            case 'j':
                return jMove(board);
            case 'm':
                return mMove(board);
            case 'p':
                return pMove(board);
            case 'x':
                return xMove(board);
            case 's':
                return sMove(board);
            case 'w':
                return wMove(board);
            case 'z':
                return zMove(board);
            default:
                return null;
        }
    }

    private ArrayList<int[]> jMove(Board board){
        ArrayList<int[]> successors = new ArrayList<int[]>();
        int row = position[0];
        int col = position[1];
        while ( board.isValid(row + 1, col) ){
            if ( !board.isEmpty(row + 1, col) ){
                if ( !isSameColor(board.getPiece(row + 1, col)) )            
                    successors.add(new int[]{row + 1, col});
                break;
            }
            successors.add(new int[]{row + 1, col});
            row++;
        }
        row = position[0];
        while ( board.isValid(row - 1, col) ){
            if ( !board.isEmpty(row - 1, col) ){
                if ( !isSameColor(board.getPiece(row - 1, col)) )  
                    successors.add(new int[]{row - 1, col});
                break;
            }
            successors.add(new int[]{row - 1, col});
            row--;
        }
        row = position[0];
        while ( board.isValid(row, col + 1) ){
            if ( !board.isEmpty(row, col + 1) ){
                if ( !isSameColor(board.getPiece(row, col + 1)) )
                    successors.add(new int[]{row, col + 1});
                break;
            }
            successors.add(new int[]{row, col + 1});
            col++;
        }
        col = position[1];
        while ( board.isValid(row, col - 1) ){
            if ( !board.isEmpty(row, col - 1) ){
                if ( !isSameColor(board.getPiece(row, col - 1)) )
                    successors.add(new int[]{row, col - 1});
                break;
            }
            successors.add(new int[]{row, col - 1});
            col--;
        }
        col = position[1];
        return successors;
    }

    private ArrayList<int[]> mMove(Board board){
        ArrayList<int[]> successors = new ArrayList<int[]>();
        int row = position[0];
        int col = position[1];
        if ( board.isValid(row - 1, col) && board.isEmpty(row - 1, col) ){
            if ( board.isValid(row - 2 , col + 1) ){
                if ( board.isEmpty(row - 2 , col + 1) || !isSameColor(board.getPiece(row - 2 , col + 1)) )
                    successors.add(new int[]{row - 2 , col + 1});
            }
            if ( board.isValid(row - 2 , col - 1 ) ){
                if ( board.isEmpty(row - 2 , col - 1 ) || !isSameColor(board.getPiece(row - 2 , col - 1 )) )
                    successors.add(new int[]{row - 2 , col - 1 });
            }
        }

        if ( board.isValid(row + 1, col) && board.isEmpty(row + 1, col) ){
            if ( board.isValid(row + 2, col + 1) ){
                if ( board.isEmpty(row + 2, col + 1) || !isSameColor(board.getPiece(row + 2, col + 1)) )
                    successors.add(new int[]{row + 2, col + 1});
            }
            if ( board.isValid(row + 2, col - 1 ) ){
                if ( board.isEmpty(row + 2, col - 1 ) || !isSameColor(board.getPiece(row + 2, col - 1 )) )
                    successors.add(new int[]{row + 2, col - 1 });
            }
        }

        if ( board.isValid(row, col - 1) && board.isEmpty(row, col - 1) ){
            if ( board.isValid(row + 1, col - 2) ){
                if ( board.isEmpty(row + 1, col - 2) || !isSameColor(board.getPiece(row + 1, col - 2)) )
                    successors.add(new int[]{row + 1, col - 2});
            }
            if ( board.isValid(row - 1, col - 2) ){
                if ( board.isEmpty(row - 1, col - 2) || !isSameColor(board.getPiece(row - 1, col - 2)) )
                    successors.add(new int[]{row - 1, col - 2});
            }
        }

        if ( board.isValid(row, col + 1) && board.isEmpty(row, col + 1) ){
            if ( board.isValid(row + 1, col + 2) ){
                if ( board.isEmpty(row + 1, col + 2) || !isSameColor(board.getPiece(row + 1, col + 2)) )
                    successors.add(new int[]{row + 1, col + 2});
            }
            if ( board.isValid(row - 1, col + 2) ){
                if ( board.isEmpty(row - 1, col + 2) || !isSameColor(board.getPiece(row - 1, col + 2)) )
                    successors.add(new int[]{row - 1, col + 2});
            }
        }

        return successors;
    }

    private ArrayList<int[]> pMove(Board board){
        ArrayList<int[]> successors = new ArrayList<int[]>();
        int row = position[0];
        int col = position[1];
        int temp = 0;

        while ( board.isValid(row + 1, col) ){
            if ( board.isEmpty(row + 1, col) ){
                successors.add(new int[]{row + 1, col});
            } else {
                temp = row + 2;
                while ( board.isValid(temp, col) ){
                    if ( !board.isEmpty(temp, col) ){
                        if ( !isSameColor(board.getPiece(temp, col)) )
                            successors.add(new int[]{temp, col});
                        break;
                    }
                    temp++;
                }
                break;
            }
            row++;
        }
        row = position[0];
        while ( board.isValid(row - 1, col) ){
            if ( board.isEmpty(row - 1, col) ){
                successors.add(new int[]{row - 1, col});
            } else {
                temp = row - 2;
                while ( board.isValid(temp, col) ){
                    if ( !board.isEmpty(temp, col) ){
                        if ( !isSameColor(board.getPiece(temp, col)) )
                            successors.add(new int[]{temp, col});
                        break;
                    }
                    temp--;
                }
                break;
            }
            row--;
        }
        row = position[0];
        while ( board.isValid(row, col + 1) ){
            if ( board.isEmpty(row, col + 1) ){
                successors.add(new int[]{row, col + 1});
            } else {
                temp = col + 2;
                while ( board.isValid(row, temp) ){
                    if ( !board.isEmpty(row, temp) ){
                        if ( !isSameColor(board.getPiece(row, temp)) )
                            successors.add(new int[]{row, temp});
                        break;
                    }
                    temp++;
                }
                break;
            }
            col++;
        }
        col = position[1];
        while ( board.isValid(row, col - 1) ){
            if ( board.isEmpty(row, col - 1) ){
                successors.add(new int[]{row, col - 1});
            } else {
                temp = col - 2;
                while ( board.isValid(row, temp) ){
                    if ( !board.isEmpty(row, temp) ){
                        if ( !isSameColor(board.getPiece(row, temp)) )
                            successors.add(new int[]{row, temp});
                        break;
                    }
                    temp--;
                }
                break;
            }
            col--;
        }
        col = position[1];
        return successors;
    }

    private ArrayList<int[]> xMove(Board board){
        ArrayList<int[]> successors = new ArrayList<int[]>();
        int row = position[0];
        int col = position[1];
        if ( (this.color == 'b' && row - 2 > 4) || this.color == 'r' ){
            if ( board.isValid(row - 1, col - 1) ){
                if ( board.isEmpty(row - 1, col - 1) ){
                    if ( board.isValid(row - 2, col - 2) ){
                        if ( board.isEmpty(row - 2, col - 2) || !isSameColor(board.getPiece(row - 2, col - 2)) ){
                            successors.add(new int[]{row - 2, col - 2});
                        }
                    }
                }
            }
            if ( board.isValid(row - 1, col + 1) ){
                if ( board.isEmpty(row - 1, col + 1) ){
                    if ( board.isValid(row - 2, col + 2) ){
                        if ( board.isEmpty(row - 2, col + 2) || !isSameColor(board.getPiece(row - 2, col + 2)) ){
                            successors.add(new int[]{row - 2, col + 2});
                        }
                    }
                }
            }
        }
        if ( (this.color == 'r' && row + 2 < 5) || this.color == 'b' ){
            if ( board.isValid(row + 1, col + 1) ){
                if ( board.isEmpty(row + 1, col + 1) ){
                    if ( board.isValid(row + 2, col + 2) ){
                        if ( board.isEmpty(row + 2, col + 2) || !isSameColor(board.getPiece(row + 2, col + 2)) ){
                            successors.add(new int[]{row + 2, col + 2});
                        }
                    }
                }
            }
            if ( board.isValid(row + 1, col - 1) ){
                if ( board.isEmpty(row + 1, col - 1) ){
                    if ( board.isValid(row + 2, col - 2) ){
                        if ( board.isEmpty(row + 2, col - 2) || !isSameColor(board.getPiece(row + 2, col - 2)) ){
                            successors.add(new int[]{row + 2, col - 2});
                        }
                    }
                }
            }
        }
        return successors;
    }

    private ArrayList<int[]> sMove(Board board){
        ArrayList<int[]> successors = new ArrayList<int[]>();
        int row = position[0];
        int col = position[1];
        if (this.color == 'r'){
            if ( row + 1 < 3 && col + 1 < 6 ){
                if ( board.isEmpty(row + 1, col + 1) || !isSameColor(board.getPiece(row + 1, col + 1)) )
                    successors.add(new int[]{row + 1, col + 1});
            }
            if ( row + 1 < 3 && col - 1 > 2 ){
                if ( board.isEmpty(row + 1, col - 1) || !isSameColor(board.getPiece(row + 1, col - 1)) )
                    successors.add(new int[]{row + 1, col - 1});
            }
            if ( row - 1 > -1 && col + 1 < 6 ){
                if ( board.isEmpty(row - 1, col + 1) || !isSameColor(board.getPiece(row - 1, col + 1)) )
                    successors.add(new int[]{row - 1, col + 1});
            }
            if ( row - 1 > -1 && col - 1 > 2 ){
                if ( board.isEmpty(row - 1, col - 1) || !isSameColor(board.getPiece(row - 1, col - 1)) )
                    successors.add(new int[]{row - 1, col - 1});
            }
        } else {
            if ( row + 1 < 10 && col + 1 < 6 ){
                if ( board.isEmpty(row + 1, col + 1) || !isSameColor(board.getPiece(row + 1, col + 1)) )
                    successors.add(new int[]{row + 1, col + 1});
            }
            if ( row + 1 < 10 && col - 1 > 2 ){
                if ( board.isEmpty(row + 1, col - 1) || !isSameColor(board.getPiece(row + 1, col - 1)) )
                    successors.add(new int[]{row + 1, col - 1});
            }
            if ( row - 1 > 6 && col + 1 < 6 ){
                if ( board.isEmpty(row - 1, col + 1) || !isSameColor(board.getPiece(row - 1, col + 1)) )
                    successors.add(new int[]{row - 1, col + 1});
            }
            if ( row - 1 > 6 && col - 1 > 2 ){
                if ( board.isEmpty(row - 1, col - 1) || !isSameColor(board.getPiece(row - 1, col - 1)) )
                    successors.add(new int[]{row - 1, col - 1});
            }
        }
        return successors;
    }

    private ArrayList<int[]> wMove(Board board){
        ArrayList<int[]> successors = new ArrayList<int[]>();
        int row = position[0];
        int col = position[1];
        int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] move : moves) {
            row = row + move[0];
            col = col + move[1];
            if ( board.isValid(row, col) && col > 2 && col < 6 && ( (row < 3 && this.color == 'r') || (row > 6 && this.color == 'b') ) ){
                if ( board.isEmpty(row, col) || !isSameColor(board.getPiece(row, col)) ) 
                    successors.add(new int[]{row, col});
            }
        }

        boolean isRoadClear = true;
        int[] opWang = (this.color == 'r') ? board.getPieces().get("bw0").getPosition() : board.getPieces().get("rw0").getPosition();
        if (opWang[1] == col) {
            for (int i = Math.min(opWang[0], row) + 1; i < Math.max(opWang[0], row); i++) {
                if (board.getPiece(i, col) != null) {
                    isRoadClear = false;
                    break;
                }
            }
            if (isRoadClear) 
                successors.add(opWang);
        }
        return successors;
    }

    private ArrayList<int[]> zMove(Board board){
        ArrayList<int[]> successors = new ArrayList<int[]>();
        int row = position[0];
        int col = position[1];
        if (this.color == 'r'){
            if (row > 4) {
                if ( board.isValid(row + 1, col) ){
                    if ( board.isEmpty(row + 1, col) || !isSameColor(board.getPiece(row + 1, col)) )
                        successors.add(new int[]{row + 1, col});
                }
                if ( board.isValid(row, col - 1) ){
                    if ( board.isEmpty(row, col - 1) || !isSameColor(board.getPiece(row, col - 1)) )
                        successors.add(new int[]{row, col - 1});
                }
                if ( board.isValid(row, col + 1) ){
                    if ( board.isEmpty(row, col + 1) || !isSameColor(board.getPiece(row, col + 1)) )
                        successors.add(new int[]{row, col + 1});
                }
            } else {
                if ( board.isValid(row + 1, col) ){
                    if ( board.isEmpty(row + 1, col) || !isSameColor(board.getPiece(row + 1, col)) )
                        successors.add(new int[]{row + 1, col});
                }
            }
        } else {
            if (row < 5) {
                if ( board.isValid(row - 1, col) ){
                    if ( board.isEmpty(row - 1, col) || !isSameColor(board.getPiece(row - 1, col)) )
                        successors.add(new int[]{row - 1, col});
                }
                if ( board.isValid(row, col - 1) ){
                    if ( board.isEmpty(row, col - 1) || !isSameColor(board.getPiece(row, col - 1)) )
                        successors.add(new int[]{row, col - 1});
                }
                if ( board.isValid(row, col + 1) ){
                    if ( board.isEmpty(row, col + 1) || !isSameColor(board.getPiece(row, col + 1)) )
                        successors.add(new int[]{row, col + 1});
                }
            } else {
                if ( board.isValid(row - 1, col) ){
                    if ( board.isEmpty(row - 1, col) || !isSameColor(board.getPiece(row - 1, col)) )
                        successors.add(new int[]{row - 1, col});
                }
            }
        }
        return successors;
    }

    private boolean isSameColor(Piece target){
        return (target.getColor() == this.color);
    }
}