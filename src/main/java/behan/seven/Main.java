/*

 */
package behan.seven;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public void start(Stage stage) throws IOException {
        // load FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        // set up scene
        Scene orderScene = new Scene(fxmlLoader.load());
        stage.setTitle("");    // title scene
        stage.setScene(orderScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
        String a = "c";
    }

}

