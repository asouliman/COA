package fr.istic.coa.mvc;

import fr.istic.coa.adapter.FxDisplay;
import fr.istic.coa.proxy.Channel;
import fr.istic.coa.proxy.Sensor;
import fr.istic.coa.strategy.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {

    private static final int LINE_WIDTH = 4;
    
    @FXML
    private BorderPane window;

    @FXML
    private Label sensorValue;
    
    @FXML
    private VBox displays;

    @FXML
    private Button startStopBtn;

    @FXML
    private ChoiceBox<String> algorithmChoice;

    private boolean stopped;
    private Sensor<Value> sensor;
    private List<Channel> channels;
    private ScheduledExecutorService executorService;

    @FXML
    public void onStartStopAction() {
        if (stopped) {
            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(this.sensor::tick, 0, 1000, TimeUnit.MILLISECONDS);
            stopped = false;
            startStopBtn.setText("Stop");
            algorithmChoice.setDisable(true);
        } else {
            shutdown();
            stopped = true;
            startStopBtn.setText("Start");
            algorithmChoice.setDisable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stopped = true;

        // Configure the select box
        algorithmChoice.setValue("AtomicDiffusion");
        algorithmChoice.getItems().addAll("AtomicDiffusion", "SequenceDiffusion", "TimeDiffusion");
        algorithmChoice.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue.intValue()) {
                case 0:
                    sensor.setAlgorithm(new AtomicDiffusion());
                    break;
                case 1:
                    sensor.setAlgorithm(new SequenceDiffusion());
                    break;
                case 2:
                    sensor.setAlgorithm(new TimeDiffusion());
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * Sets the sensor.
     * @param sensor
     */
    public void setSensor(Sensor<Value> sensor) {
        this.sensor = sensor;
    }

    /**
     * Sets the algorithm.
     * @param algorithm
     */
    public void setAlgorithm(DiffusionAlgorithm algorithm) {
        sensor.setAlgorithm(algorithm);
    }

    /**
     * Sets the channel list.
     * @param channels
     */
    public void setChannels(List<Channel> channels) {
        this.channels = channels;
        generateDisplays();
    }

    /**
     * Gets the channel list.
     * @return
     */
    public List<Channel> getChannels() {
        return channels;
    }

    /**
     * Generate the display number for the window.
     */
    private void generateDisplays() {
        HBox line = new HBox();
        
        for (int i = 0; i < channels.size(); i++) {
            if (i % LINE_WIDTH == 0) {
                line = new HBox();
                line.setAlignment(Pos.CENTER);
                line.setSpacing(20);
                displays.getChildren().add(line);
            }
            
            FxDisplay value = new FxDisplay(i + 1);
            channels.get(i).addObserver(value);
            line.getChildren().add(value);
        }
    }

    /**
     * Shuts down the executor service.
     */
    public void shutdown() {
        executorService.shutdown();
    }
}
