package fr.istic.coa.mvc;

import fr.istic.coa.adapter.FxDisplay;
import fr.istic.coa.proxy.Channel;
import fr.istic.coa.proxy.Sensor;
import fr.istic.coa.strategy.AtomicDiffusion;
import fr.istic.coa.strategy.DiffusionAlgorithm;
import fr.istic.coa.strategy.SequenceDiffusion;
import fr.istic.coa.strategy.TimeDiffusion;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private GridPane window;

    @FXML
    private Button startStopBtn;

    @FXML
    private ChoiceBox<String> algorithmChoice;

    private GridPane displays;

    private Sensor sensor;
    private Timeline timeline;
    private List<Channel> channels;
    private boolean stopped;

    @FXML
    public void onStartStopAction() {
        if (stopped) {
            timeline.play();
            stopped = false;
            startStopBtn.setText("Stop");
            algorithmChoice.setDisable(true);
        } else {
            timeline.stop();
            stopped = true;
            startStopBtn.setText("Start");
            algorithmChoice.setDisable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stopped = true;
        displays = new GridPane();
        window.add(displays, 0, 0);

        // Configure the select box
        algorithmChoice.setValue("AtomicDiffusion");
        algorithmChoice.getItems().addAll("AtomicDiffusion", "SequenceDiffusion", "TimeDiffusion");
        algorithmChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
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
            }
        });
    }

    /**
     * Sets the sensor.
     * @param sensor
     */
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            this.sensor.tick();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
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
        for (int i = 0; i < channels.size(); i++) {
            Label value = new Label("Display " + i);

            channels.get(i).addObserver(new FxDisplay(value));

            displays.add(value, 0, i);
        }
    }
}
