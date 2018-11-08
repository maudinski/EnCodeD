package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import org.opencv.core.Core;
import org.opencv.core.*;

//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.CvType;
//import org.opencv.core.Scalar;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/LogIn.fxml"));
        primaryStage.setTitle("CodeEn");
        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
  
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        launch(args);
    }
}
