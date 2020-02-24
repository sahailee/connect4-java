import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ConnectFour {
    private boolean win;
    private int winNumber;
    Board board;

    public ConnectFour(Stage primaryStage) {
        this.board = new Board(primaryStage, this);
        win = false;
        winNumber = 4;
    }

    public boolean insert(Paint color, int col) throws IndexOutOfBoundsException{
        if(col < 0 || col > 6)
            throw new IndexOutOfBoundsException("Please enter an int from 0 to 6.");
        else {
            for(int i = this.board.slots.length - 1; i >=  0; i--) {
                if(this.board.slots[i][col].getFill() == Color.GRAY) {
                    this.board.slots[i][col].setFill(color);
                    this.checkWin(color, i, col);
                    return true;
                }
            }
            return false;
        }
    }
    public boolean isDraw() {
        for(int i = 0; i < this.board.slots.length; i++) {
            for(int j = 0; j < this.board.slots[i].length; j++) {
                if(this.board.slots[i][j].getFill() == Color.GRAY) {
                    return false;
                }
            }
        }
        this.win = true;
        return true;
    }
    public void reset() {
        this.win = false;
        winNumber = 4;
    }

    public void setWinNumber(int winNumber) {
        this.winNumber = winNumber;
    }

    public boolean getWin() {
        return this.win;
    }

    public void checkWin(Paint color, int row, int col) {
        this.rowWin(color, row);
        this.colWin(color, col);
        this.leftDiagWin(color, row, col);
        this.rightDiagWin(color, row, col);
    }
    private void leftDiagWin(Paint color, int row, int col) {
        int diff = this.board.slots.length - row - 1;
        int count = 0;
        int j = col + diff;
        int i = row + diff;
        if(j >= this.board.slots[0].length) {
            int diff2 = j - (this.board.slots[0].length - 1);
            j -= diff2;
            i -= diff2;
        }
        for(; i >= 0 && j >= 0; i--, j--){
            if (count == winNumber) {
                this.win = true;
                return;
            }
            if(this.board.slots[i][j].getFill() == color) {
                count++;
            }
            else {
                count = 0;
            }
        }
        if (count == winNumber)
            this.win = true;
    }
    private void rightDiagWin(Paint color, int row, int col) {
        int diff = this.board.slots.length - row - 1;
        int count = 0;
        int j = col - diff;
        int i = row + diff;
        if(j < 0) {
            int diff2 = 0 - j;
            j += diff2;
            i -= diff2;
        }
        for(; i >= 0  && j < this.board.slots[0].length; i--, j++){
            if (count == winNumber) {
                this.win = true;
                return;
            }
            if(this.board.slots[i][j].getFill() == color) {
                count++;
            }
            else {
                count = 0;
            }
        }
        if (count == winNumber)
            this.win = true;
    }
    private void colWin(Paint color, int col) {
        for(int i = this.board.slots.length - 1; i > this.board.slots.length - winNumber; i--) {
            int count = 0;
            for(int j = i; j > i - winNumber; j--) {
                if(this.board.slots[j][col].getFill() != color) {
                    break;
                }
                else {
                    count++;
                }
            }
            if(count == winNumber) {
                this.win = true;
                break;
            }
        }
    }
    private void rowWin(Paint color, int row) {
        for(int i = 0; i < winNumber; i++) {
            int count = 0;
            for(int j = i; j < i + winNumber; j++) {
                if(this.board.slots[row][j].getFill() != color) {
                    break;
                }
                else {
                    count++;
                }
            }
            if(count == winNumber) {
                this.win = true;
                break;
            }
        }
    }
}