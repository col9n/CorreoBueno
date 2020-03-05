package sample.views;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.control.TableView;
        import javafx.stage.Stage;
        import net.sf.jasperreports.engine.JRException;
        import net.sf.jasperreports.engine.JasperExportManager;
        import net.sf.jasperreports.engine.JasperFillManager;
        import net.sf.jasperreports.engine.JasperPrint;
        import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
        import sample.clases.Correo;
        import sample.clases.CuentaCorreo;
        import sample.clases.MensajeInforme;
        import sample.clases.TreeItemPropio;
        import sample.logica.Logica;

        import javax.mail.Folder;
        import java.io.File;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map;
        import java.util.ResourceBundle;

public class ControllerCuentasCorreo implements Initializable {

    @FXML
    private TableView<CuentaCorreo> tableViewGestionCuentas;

    @FXML
    private Button imprimeCuenta;

    private Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableViewGestionCuentas.setItems(Logica.getInstance().getListaCuentas());
    }

    public void borrarCuenta() {

        CuentaCorreo cuenta = tableViewGestionCuentas.getSelectionModel().getSelectedItem();
        Logica.getInstance().borrarCuenta(cuenta);
        tableViewGestionCuentas.setItems( Logica.getInstance().getListaCuentas());

    }

    @FXML
    public void imprimirCuenta(ActionEvent event){
        File file=Logica.getInstance().getFile();
        Runnable runnable =
                () -> {
                    try {
                        ArrayList<MensajeInforme> list = Logica.getInstance().imprimirCuenta(tableViewGestionCuentas.getSelectionModel().getSelectedItem().getStore(), tableViewGestionCuentas.getSelectionModel().getSelectedItem().getUser());
                        JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(list); //lista sería la colección a mostrar. Típicamente saldría de la lógica de nuestra aplicación
                        Map<String, Object> parametros = new HashMap<>(); //En este caso no hay parámetros, aunque podría haberlos
                        JasperPrint print = null;


                        print = JasperFillManager.fillReport(getClass().getResourceAsStream("/sample/reportes/informeCuenta.jasper"), parametros, jr);

                        JasperExportManager.exportReportToPdfFile(print, file.getPath());

                    } catch (JRException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
        new Thread(runnable).start();
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
