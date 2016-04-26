/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion.AdminWindow;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.cidte.sii.entidades.TipoUsuario;
import org.cidte.sii.hibernate.ConectorTipoUsuario;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class TipoUsuarioController implements Initializable {

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabTU;
    @FXML
    private AnchorPane paneTabel;
    @FXML
    private TableView<TipoUsuario> tableTU;
    @FXML
    private TableColumn<?, ?> colAction;
    @FXML
    private TableColumn<?, ?> colProductId;
    @FXML
    private TableColumn<?, ?> colManufacturerIid;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnNuevo;
    @FXML
    private ProgressBar bar;
    @FXML
    private Label lbTitleTU;
    @FXML
    private Tab tabRMTU;
    @FXML
    private Label lbID;
    @FXML
    private Label lbNom;
    @FXML
    private Label lbDesc;
    @FXML
    private Label lbRol;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfDesc;
    @FXML
    private ComboBox<String> cbRol;
    @FXML
    private Label lbTitleRM;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnGuardar;

    private ConectorTipoUsuario conTU;
    private TipoUsuario selected;
    private boolean modificar = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conTU = new ConectorTipoUsuario();
//        setLabels();
        createTable();
        fillTable();
    }

    @FXML
    private void handleEliminar(ActionEvent event) {
        TipoUsuario tu = tableTU.getSelectionModel().getSelectedItem();
        if (tu != null) {
            conTU.delete(tu);
            fillTable();
        }
    }

    @FXML
    private void handleModificar(ActionEvent event) {
        TipoUsuario tu = tableTU.getSelectionModel().getSelectedItem();
        if (tu != null) {
            tabPane.getSelectionModel().select(tabRMTU);
            btnGuardar.setText("Actualizar Tipo Usuario");

            selected = tu;
            modificar = true;
            tfID.setText(tu.getId_tipousario() + "");
            tfNom.setText(tu.getNombre());
            tfDesc.setText(tu.getDescripcion());
            cbRol.setValue(tu.getRol());
        }
    }

    @FXML
    private void handleNuevo(ActionEvent event) {
        fillTable();
    }

    @FXML
    private void handleLimpiar(ActionEvent event) {
        tfID.setText("");
        tfNom.setText("");
        tfDesc.setText("");
        btnGuardar.setText("Guardar");
        modificar = false;
    }

    @FXML
    private void handleGuardar(ActionEvent event) {
        String id = tfID.getText();
        String nombre = tfNom.getText();
        String descripcion = tfDesc.getText();
        String rol = cbRol.getSelectionModel().getSelectedItem();

        if (modificar) {
            selected.setNombre(nombre);
            selected.setDescripcion(descripcion);
            selected.setRol(rol);
            conTU.update(selected);
            
            handleLimpiar(event);
            fillTable();
        } else {
            TipoUsuario tu = new TipoUsuario(nombre, descripcion, rol);
            conTU.saveNew(tu);

            handleLimpiar(event);
            fillTable();
        }
    }

    private void setLabels() {

    }

    private void createTable() {
        TableColumn colID = new TableColumn("Id");
        colID.setMinWidth(100);
        colID.setCellValueFactory(
                new PropertyValueFactory<>("id_tipousario"));

        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setMinWidth(200);
        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        TableColumn colDescripcion = new TableColumn("Descripcion");
        colDescripcion.setMinWidth(300);
        colDescripcion.setCellValueFactory(
                new PropertyValueFactory<>("descripcion"));

        TableColumn colRol = new TableColumn("Rol");
        colRol.setMinWidth(200);
        colRol.setCellValueFactory(
                new PropertyValueFactory<>("rol"));
        
        tableTU.getColumns().clear();
        tableTU.getColumns().addAll(
                colID,
                colNombre,
                colDescripcion,
                colRol
        );
    }

    private void fillTable() {
        ArrayList<TipoUsuario> tipousuarios = conTU.getAll();
        tableTU.getItems().clear();
        tableTU.getItems().addAll(tipousuarios);

        //llenar el combobox de rols
        ObservableList<String> rols
                = FXCollections.observableArrayList(
                        "Administrativo",
                        "Operativo"
                );
        cbRol.setItems(rols);
        cbRol.setValue("Operativo");
    }
}
