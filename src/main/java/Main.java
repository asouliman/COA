import fr.istic.coa.adapter.FxDisplay;
import fr.istic.coa.mvc.Controller;
import fr.istic.coa.proxy.Channel;
import fr.istic.coa.proxy.Sensor;
import fr.istic.coa.proxy.BaseSensor;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private final int NUMBER_OF_DISPLAY = 2;
    private Controller controller;
    private Sensor sensor;
    private List<Channel> channels;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL location = getClass().getResource("sample.fxml");
        Parent root = fxmlLoader.load(location.openStream());
        stage.setTitle("COA - Project");

        controller = fxmlLoader.getController();
        Scene scene = new Scene(root, 500, 450);
        channels = new ArrayList<>();
        sensor = new BaseSensor();

        createDisplays();

        stage.setScene(scene);
        stage.show();

        // Tick
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            sensor.tick();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
        // Close everything
        stage.setOnCloseRequest(ev -> {
            timeline.stop();
            for (Channel ch : channels) {
                ch.shutdownExecutor();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void createDisplay(int current) throws Exception {
        Channel channel = new Channel();
        channel.setSensor(sensor);
        
        sensor.addObserver(channel);

        FxDisplay display = new FxDisplay(controller.getValue(current));
        channel.addObserver(display);
        channels.add(channel);
    }

    private void createDisplays() throws Exception {
        for (int i=0; i<NUMBER_OF_DISPLAY; i++) {
            createDisplay(i);
        }
    }
}
