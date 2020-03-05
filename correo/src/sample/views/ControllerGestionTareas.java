package sample.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import modelos.Tarea;
import sample.logica.Logica;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerGestionTareas implements Initializable {

    private Stage stage;

    @FXML
    private TableView<Tarea> tableViewGestionTareas;

    @FXML
    void borrarCuenta(ActionEvent event) {
        Tarea tarea = tableViewGestionTareas.getSelectionModel().getSelectedItem();
        Logica.getInstance().borrarTarea(tarea);
        tableViewGestionTareas.setItems( Logica.getInstance().getListaTarea());
        Logica.getInstance().grabarDatosTareas();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewGestionTareas.setItems(Logica.getInstance().getListaTarea());
    }


}
