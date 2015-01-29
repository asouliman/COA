package fr.istic.coa.mvc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label value1;
    
    @FXML
    private Label value2;

    private List<Label> displays;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displays = new ArrayList<>();
        displays.add(value1);
        displays.add(value2);
    }

    public Label getValue(int i) {
        return displays.get(i);
    }

}
