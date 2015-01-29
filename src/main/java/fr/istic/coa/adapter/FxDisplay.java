package fr.istic.coa.adapter;

import fr.istic.coa.proxy.Channel;
import fr.istic.coa.proxy.ChannelObserver;
import javafx.scene.control.Label;

import java.util.concurrent.ExecutionException;

/**
 * @author thomas
 * @author amona
 */
public class FxDisplay implements ChannelObserver {
    
    private Label value;
    
    public FxDisplay(Label value) {
        this.value = value;
    }

    @Override
    public void update(Channel ch) {

        int val = 0;
        try {
            val = ch.getValue().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        value.setText("Value : " + val);
    }
}
