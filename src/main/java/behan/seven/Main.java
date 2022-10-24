/*
Chloe Behan & Jafar Hashim
Cmdr Schenk
Period 7
24 October 2022
Sorting Project: Main Class
 */
package behan.seven;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Scene scene;

    public void start(Stage stage) throws IOException {
        // load FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/sortingStarbucksFr.fxml"));
        // set up scene
        scene = new Scene(loadFXML("/sortingStarbucksFr"));
        stage.setScene(scene);

        Scene orderScene = new Scene(fxmlLoader.load());
        stage.setTitle("");    // title scene
        stage.setScene(orderScene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException{
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        new SortingClass();
    }

}

