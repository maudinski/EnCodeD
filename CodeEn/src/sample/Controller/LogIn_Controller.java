package sample.Controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogIn_Controller implements EventHandler<ActionEvent> {

    @FXML
    PasswordField password;

    @FXML
    Pane background;

    @FXML
    TextField username;

    @FXML
    Label forgot_password;

    @FXML
    Label signUp;

    boolean isUser;
    public String id;
    public String pass;

    @Override
    public void handle(ActionEvent event) {

        id = username.getText();
        pass = password.getText();

        System.out.println(id);
        System.out.println(pass);

        isUser = verifyUser(id, pass);

        if (isUser == true){
            WelcomePage();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username or password is incorrect");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initStyle(StageStyle.UTILITY);
            alert.setHeaderText(null);
            alert.showAndWait();
            alert.setX(800);
            alert.setY(200);
        }
    }

    public void handleSignUp(ActionEvent event) {

        //Switch view to sign up page

    }

    public void handleForgotPassword(ActionEvent event) {

      //switch view to reset password page

    }

    private void WelcomePage()
    {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1.0));
        fade.setNode(background);
        fade.setFromValue(1.0);
        fade.setToValue(0);
        fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadWelcomeScene();
            }
        });
        fade.play();
    }

    private void loadWelcomeScene()
    {
        Parent Welcome;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/Welcome.fxml"));
            Welcome = (Pane) loader.load();
            Welcome_Controller controller = loader.getController();
            controller.setUsername(id);
            Scene scene  = new Scene(Welcome, 900,900);
            Stage currStage = (Stage) background.getScene().getWindow();
            currStage.setScene(scene);
        } catch (IOException e) {
            Logger.getLogger(LogIn_Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean verifyUser(String username, String password){
        boolean isUser = false;
        String id;
        String pass;

        File file = new File("/CodeEn/src/sample/Members");

        try {
            Scanner memberList = new Scanner(file);
            while (memberList.hasNextLine()){
                id = memberList.nextLine();
                pass = memberList.nextLine();

                if(id.equals(username))
                    if(pass.equals(password))
                        isUser = true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return isUser;
    }
}
