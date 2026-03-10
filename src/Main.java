import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new java.io.File("src/saves").mkdirs();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Start.fxml"));
        primaryStage.setTitle("Start");
        primaryStage.setScene(new Scene(root, 900, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}