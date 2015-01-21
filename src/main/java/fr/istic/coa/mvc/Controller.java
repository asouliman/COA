package fr.istic.coa.mvc;

import fr.istic.coa.observer.Observable;
import fr.istic.coa.proxy.ISensorObserver;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, ISensorObserver {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void update(Observable sensor) {
    }
}
