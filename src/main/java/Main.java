import fr.istic.coa.mvc.Controller;
import fr.istic.coa.proxy.BaseSensor;
import fr.istic.coa.proxy.Channel;
import fr.istic.coa.proxy.Sensor;
import fr.istic.coa.strategy.AtomicDiffusion;
import fr.istic.coa.strategy.Value;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main extends Application {

    private static final int NUMBER_OF_DISPLAY = 4;
    private Controller controller;
    private Sensor<Value> sensor;
    private List<Channel> channels;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL location = getClass().getResource("fxml/sample.fxml");
        Parent root = fxmlLoader.load(location.openStream());

        controller = fxmlLoader.getController();
        channels = new ArrayList<>();
        sensor = new BaseSensor();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(NUMBER_OF_DISPLAY + 1);

        createDisplays(NUMBER_OF_DISPLAY, executorService);

        controller.setSensor(sensor);
        controller.setChannels(channels);
        controller.setAlgorithm(new AtomicDiffusion());

        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles/application.css");

        stage.setTitle("COA - Project");
        stage.setScene(scene);
        stage.setOnCloseRequest((event) -> {
            controller.shutdown();
            executorService.shutdown();
        });    // Shutdown on close

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void createDisplays(int number, ScheduledExecutorService executorService) {
        for (int i=0; i < number; i++) {
            createDisplay(i, executorService);
        }
    }

    private void createDisplay(int current, ScheduledExecutorService executorService) {
        Channel channel = new Channel(executorService, sensor);

        sensor.addObserver(channel);

        channels.add(channel);
    }
}
