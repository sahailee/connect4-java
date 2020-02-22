public class ConnectFour {
    private char[][] board;
    private boolean win;
    private char winner;
    public ConnectFour() {
        this.board = new char[6][7];
        win = false;
        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = ' ';
            }
        }
    }
    public boolean insert(char color, int col) throws IndexOutOfBoundsException{
        boolean complete = false;
        if(col < 0 || col > 6)
            throw new IndexOutOfBoundsException("Please enter an int from 0 to 6.");
        else {
            for(int i = this.board.length - 1; i >=  0; i--) {
                if(this.board[i][col] == ' ') {
                    this.board[i][col] = color;
                    complete = true;
                    this.checkWin(color, i, col);
                    return complete;
                }
            }
            return complete;
        }
    }
    public boolean isDraw() {
        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board[i].length; j++) {
                if(this.board[i][j] == ' ') {
                    return false;
                }
            }
        }
        this.win = true;
        return true;
    }
    public boolean getWin() {
        return this.win;
    }
    public char getWinner() {
        return winner;
    }
    public void checkWin(char color, int row, int col) {
        this.rowWin(color, row);
        this.colWin(color, col);
        this.leftDiagWin(color, row, col);
        this.rightDiagWin(color, row, col);
    }
    private void leftDiagWin(char color, int row, int col) {
        for(int k = 0; k <= 4; k++) {
            int count = 0;
            if(col + k < this.board[0].length - 1 && row + k < this.board.length) {
                for(int i = row, j = col; i > row - 4 && j + k >= 0 && i + k >= 0; i--,j--) {
                    if(this.board[i + k][j + k] != color) {
                        break;
                    }
                    else {
                        count++;
                    }
                }
                if(count == 4) {
                    this.win = true;
                    this.winner = color;
                    break;
                }
            }
        }
    }
    private void rightDiagWin(char color, int row, int col) {
        for(int k = 0; k < 4; k++) {
            int count = 0;
            if(col - k < this.board[0].length - 1 && row + k < this.board.length) {
                for(int i = row, j = col; i > row - 4 && (j - k) >= 0 && (i + k) >= 0; i--,j++) {
                    if(this.board[i + k][j - k] != color) {
                        break;
                    }
                    else {
                        count++;
                    }
                }
                if(count == 4) {
                    this.win = true;
                    this.winner = color;
                    break;
                }
            }
        }
    }
    private void colWin(char color, int col) {
        for(int i = this.board.length - 1; i > 2; i--) {
            int count = 0;
            for(int j = i; j > i - 4; j--) {
                if(this.board[j][col] != color) {
                    break;
                }
                else {
                    count++;
                }
            }
            if(count == 4) {
                this.win = true;
                this.winner = color;
                break;
            }
        }
    }
    private void rowWin(char color, int row) {
        for(int i = 0; i < 4; i++) {
            int count = 0;
            for(int j = i; j < i + 4; j++) {
                if(this.board[row][j] != color) {
                    break;
                }
                else {
                    count++;
                }
            }
            if(count == 4) {
                this.win = true;
                this.winner = color;
                break;
            }
        }
    }

    public String toString() {
        String s = "";
        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board[i].length;j++) {
                s += ("|" + this.board[i][j]);
            }
            s += "|\n";
        }
        return s;
    }
}
