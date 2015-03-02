package fr.istic.coa.adapter;

import fr.istic.coa.proxy.Channel;
import fr.istic.coa.proxy.ChannelObserver;
import fr.istic.coa.strategy.Value;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author thomas
 * @author amona
 */
public class FxDisplay extends VBox implements ChannelObserver {

    @FXML
    private Label name;
    
    @FXML
    private Label value;
    
    public FxDisplay(int id) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/display.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        name.setText("Display " + id);
    }

    @Override
    public void update(Channel subject) {
        try {
            Value val = subject.getValue().get();
            String text = "";
            if (val.getVersion() >= 0) {
                text += "[" + val.getVersion() + "]: ";
            }
            final String version = text;
            Platform.runLater(() -> value.setText(version + val.getValue()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Future<Integer> updateAsync(Channel subject) {
        return null;
    }
}
