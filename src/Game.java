import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConnectFour game = new ConnectFour(primaryStage);
        primaryStage.setScene(game.board.startMenu());
        primaryStage.setTitle("Connect Four");
        primaryStage.show();

    }
}
