import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Board {
    private double width;
    private double height;
    private int numRows;
    private int numCols;
    private Stage primaryStage;
    private ConnectFour connectFour;
    Circle[][] slots;
    private Paint currentTurn;
    private Label currentTurnLabel;
    private HBox board;

    public Board(Stage primaryStage, ConnectFour connectFour) {
        this.primaryStage = primaryStage;
        this.connectFour = connectFour;
        width = 1200;
        height = 1200;
        numRows = 6;
        numCols = 7;
        slots = new Circle[numRows][numCols];
        currentTurn = Color.RED;
        board = new HBox();
        currentTurnLabel = new Label("Red's Turn");
    }

    Scene startMenu() {
        Label label = new Label("CONNECT FOUR");
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            connectFour.reset();
            board.setDisable(false);
            updateTurn();
            primaryStage.setScene(generateBoard());
        });
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
        VBox vBox = new VBox(label, startButton, exitButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(100);
        return new Scene(vBox, width, height);
    }

    Scene generateBoard() {
        Rectangle r = new Rectangle();
        r.setX(50);
        r.setY(50);
        r.setFill(Color.YELLOW);
        r.setWidth(this.width * (3.0 / 3));
        r.setHeight(this.height / 2);
        StackPane stackPane = new StackPane(r, generateSlots());
        Button exitGame = new Button("Exit Game");
        exitGame.setOnAction(e -> {
            primaryStage.setScene(startMenu());
        });
        ToolBar toolBar = new ToolBar(exitGame);
        return new Scene(new VBox(toolBar, currentTurnLabel, stackPane), width, height);
    }

    HBox generateSlots() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        for(int j = 0; j < this.slots[0].length; j++) {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            for(int i = 0; i < this.slots.length; i++) {
                this.slots[i][j] = new Circle(30);
                this.slots[i][j].setFill(Color.GRAY);
                vBox.getChildren().add(slots[i][j]);
            }
            int finalJ = j;
            vBox.setOnMousePressed(e -> {
                if(this.connectFour.insert(currentTurn, finalJ)) {
                    if (connectFour.getWin()) {
                        displayWin();
                    } else if (connectFour.isDraw()) {
                        displayDraw();
                    } else {
                        updateTurn();
                    }
                }
            });
            hBox.getChildren().add(vBox);
        }
        this.board = hBox;
        return hBox;
    }

    void displayWin() {
        if(currentTurn == Color.RED) {
            currentTurnLabel.setText("Red Wins!!");
        }
        else {
            currentTurnLabel.setText("Blue Wins!!");
        }
        disableBoardInput();

    }

    void displayDraw() {
        currentTurnLabel.setText("DRAW!");
        disableBoardInput();
    }

    void disableBoardInput() {
        this.board.setDisable(true);
    }

    void updateTurn() {
        if(currentTurn == Color.RED) {
            currentTurn = Color.BLUE;
            currentTurnLabel.setText("Blue's Turn");
        }
        else {
            currentTurn = Color.RED;
            currentTurnLabel.setText("Red's Turn");
        }
    }
}