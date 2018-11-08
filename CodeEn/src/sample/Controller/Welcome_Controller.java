package sample.Controller;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.*;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.*;
import org.opencv.imgcodecs.*;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Welcome_Controller {

    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    @FXML
    Button startcamera;

    @FXML
    ImageView camOn;

    @FXML
    Pane background;

    private Timer timer;
    private VideoCapture capture = new VideoCapture();
    public String username;

    private int absoluteFaceSize = 0;
    private boolean cameraActive;
    private CascadeClassifier faceCascade = new CascadeClassifier();
    private Image CamStream;


    String sequence = "";
    String alphabet = "abcdefghijkLMNOPQRSTuvWXyz";

    public void setUsername(String id){
        username = id;
    }

    public void initialize() {
        fadeInTransition();
    }

    @FXML
    protected void startCamera(ActionEvent event) {
        if (this.background != null) {
            // get the ImageView object for showing the video stream
            final ImageView frameView = camOn;
            // check if the capture stream is opened
            if (!this.capture.isOpened()) {
                this.cameraActive = true;
                // start the video capture
                this.capture.open(0);
                // grab a frame every 33 ms (30 frames/sec)
                TimerTask frameGrabber = new TimerTask() {
                    @Override
                    public void run() {

                        CamStream = grabFrame();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                frameView.setImage(CamStream);
                                frameView.setFitWidth(655.0);
                                frameView.setPreserveRatio(true);
                            }
                        });
                    }
                };

                this.timer = new Timer();
//set the timer scheduling, this allow you to perform

                this.timer.schedule(frameGrabber, 0, 33);
                this.startcamera.setText("Stop Camera");
            } else {
                this.startcamera.setText("Start Camera");
                // stop the timer
                if (this.timer != null) {
                    this.timer.cancel();
                    this.timer = null;
                }
                // release the camera
                this.capture.release();
                // clear the image container
                frameView.setImage(null);
            }
        }
    }

    private Image grabFrame()
    {
        //init
        Image imageToShow = null;
        Mat frame = new Mat();
        // check if the capture is open
        if (this.capture.isOpened())
        {
            try
            {
                // read the current frame
                this.capture.read(frame);
                // if the frame is not empty, process it
                if (!frame.empty())
                {
                    // convert the image to gray scale
                    //Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
                        // convert the Mat object (OpenCV) to Imag (JavaFX)
                        imageToShow = mat2Image(frame);
                }
            }
            catch (Exception e)
            {
                // log the error
                System.err.println("ERROR: " + e.getMessage());
            }
        }

        String filename = username + ".jpg";
        Imgcodecs.imwrite("~/Desktop" + filename, frame);
        return imageToShow;
    }

    private Image mat2Image(Mat frame)
    {
        // create a temporary buffer
        MatOfByte buffer = new MatOfByte();
        // encode the frame in the buffer
        Imgcodecs.imencode(".png", frame, buffer);
// build and return an Image created from the image encoded in the buffer
        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }
    public void setRootElement(Pane root)
    {
        this.background = root;
    }



    private void fadeInTransition(){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1.0));
        fade.setNode(background);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        fade.play();
    }


}












    /*  background.setOpacity(0);

        Random rand = new Random();
        int temp;
        int index;

        for (int i = 0; i < 4; i++){
            index = rand.nextInt(alphabet.length());
            sequence = sequence + alphabet.charAt(index);
        }

        for (int i = 0; i < 4; i++){
            temp = rand.nextInt(9) + 0;
            sequence = sequence + Integer.toString(temp);
        }
*/