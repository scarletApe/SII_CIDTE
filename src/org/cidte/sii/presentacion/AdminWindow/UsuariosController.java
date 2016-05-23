/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion.AdminWindow;

import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import org.cidte.sii.entidades.DatosGenerales;
import org.cidte.sii.entidades.DatosLegales;
import org.cidte.sii.entidades.DatosMedicos;
import org.cidte.sii.entidades.DatosPersonales;
import org.cidte.sii.entidades.Usuario;
import org.cidte.sii.entidades.Writable;
import org.cidte.sii.hibernate.ConectorDatosGenerales;
import org.cidte.sii.hibernate.ConectorDatosLegales;
import org.cidte.sii.hibernate.ConectorDatosMedicos;
import org.cidte.sii.hibernate.ConectorDatosPersonales;
import org.cidte.sii.hibernate.ConectorTipoUsuario;
import org.cidte.sii.hibernate.ConectorUsuario;
import org.cidte.sii.negocio.LogInManager;
import org.cidte.sii.negocio.PDFWriter;
import org.cidte.sii.negocio.XLSWriter;
import org.cidte.sii.presentacion.SII_CIDTE;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class UsuariosController implements Initializable {

    private ConectorUsuario conusuario;
    private ConectorTipoUsuario conTU;
    private boolean modificar = false;
    private Usuario selected;
    private javafx.scene.image.Image gato;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conusuario = new ConectorUsuario();
        conTU = new ConectorTipoUsuario();

        gato = new javafx.scene.image.Image("/org/cidte/sii/imagenes/gato.png");

        setLabels();
        createTable();
        fillTable();
    }

    private void setLabels() {
        ResourceBundle msg = SII_CIDTE.LOCALIZER.getMessages();

        lbAdminUsrs.setText(msg.getString("administrar_usuarios"));
        btnEliminar.setText(msg.getString("btn_eliminar_usuario"));
        btnModificar.setText(msg.getString("btn_modificar_usuario"));
        btnNuevo.setText(msg.getString("refrescar"));
    }

    @FXML
    private Label lbAdminUsrs;

    @FXML
    private TabPane tablePane;

    @FXML
    private AnchorPane paneTabel;

    @FXML
    private Label lbID;

    @FXML
    private Button btnLimpiar;

    @FXML
    private TableView<Usuario> tablaUsuarios;

    @FXML
    private Button btnNuevo;

    @FXML
    private Label lbNom;

    @FXML
    private TextField tfCurp;

    @FXML
    private Label lbNU;

    @FXML
    private TextField tfNU;

    @FXML
    private ComboBox<String> cbRol;

    @FXML
    private Tab tabUsuarios;

    @FXML
    private ProgressBar bar;

    @FXML
    private Label lbCon;

    @FXML
    private PasswordField pfCont;

    @FXML
    private Label lbTitleUsuarios;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Tab tabMod;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnXLS;
    @FXML
    private Button btnPDF;

    @FXML
    void handleEliminar(ActionEvent event) {
        Usuario u = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (u != null) {
            conusuario.delete(u);
            fillTable();
        }
    }

    @FXML
    void handleModificar(ActionEvent event) {
        Usuario u = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (u != null) {
            tablePane.getSelectionModel().select(tabMod);
            btnGuardar.setText("Actualizar Usuario");

            selected = u;
            modificar = true;

            tfCurp.setText(u.getCurp());
            tfNU.setText(u.getUsername());
            pfCont.setText("");
            cbRol.setValue(u.getRol());;

        }
    }

    @FXML
    void handleNuevo(ActionEvent event) {
        fillTable();
    }

    @FXML
    void handleLimpiar(ActionEvent event) {
        tfCurp.setText("");
        tfNU.setText("");
        pfCont.setText("");

        btnGuardar.setText("Guardar");
        modificar = false;
    }

    @FXML
    void handleGuardar(ActionEvent event) {
        String curp = tfCurp.getText();
        String nu = tfNU.getText();
        String contrasena = pfCont.getText();
        String rol = cbRol.getSelectionModel().getSelectedItem();

        if (curp.isEmpty()
                || nu.isEmpty()
                || contrasena.isEmpty()
                || rol.isEmpty()) {
            return;
        }

        String pass = new LogInManager().encriptarContrasena(contrasena);

        if (modificar) {
            Usuario u = selected;
            u.setUsername(nu);
            u.setPassword(pass);
            u.setRol(rol);

            conusuario.update(u);
            handleLimpiar(event);
            fillTable();
        } else {
            Usuario u = new Usuario(curp, nu, pass, rol);
            conusuario.saveNew(u);

            DatosGenerales dg = new DatosGenerales(u);
            ConectorDatosGenerales cdg = new ConectorDatosGenerales();
            cdg.saveNew(dg);

            DatosLegales dl = new DatosLegales(u);
            ConectorDatosLegales cdl = new ConectorDatosLegales();
            cdl.saveNew(dl);

            DatosPersonales dp = new DatosPersonales(u);
            ConectorDatosPersonales cdp = new ConectorDatosPersonales();
            cdp.saveNew(dp);

            DatosMedicos dm = new DatosMedicos(u);
            ConectorDatosMedicos cdm = new ConectorDatosMedicos();
            cdm.saveNew(dm);

//            Nomina n = new Nomina(u.getCurp());
//            ConectorNomina cn = new ConectorNomina();
//            cn.saveNew(n);
//            TipoContratacion tc = new TipoContratacion(u.getCurp());
//            ConectorTipoContratacion ctc = new ConectorTipoContratacion();
//            ctc.saveNew(tc);
            handleLimpiar(event);
            fillTable();
        }
    }

    private void createTable() {
        ImageView iv;

        TableColumn colID = new TableColumn("Curp");
        colID.setMinWidth(200);
        colID.setCellValueFactory(
                new PropertyValueFactory<>("curp"));
        iv = new ImageView(gato);
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        colID.setGraphic(iv);

        TableColumn colNU = new TableColumn("Nombre Usuario");
        colNU.setMinWidth(200);
        colNU.setCellValueFactory(
                new PropertyValueFactory<>("username"));
        iv = new ImageView(gato);
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        colNU.setGraphic(iv);

        TableColumn colRol = new TableColumn("Rol");
        colRol.setMinWidth(200);
        colRol.setCellValueFactory(
                new PropertyValueFactory<>("rol"));
        iv = new ImageView(gato);
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        colRol.setGraphic(iv);

        TableColumn colPass = new TableColumn("Contraseña");
        colPass.setMinWidth(200);
        colPass.setCellValueFactory(
                new PropertyValueFactory<>("password"));
        iv = new ImageView(gato);
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        colPass.setGraphic(iv);
//
//        TableColumn colAP = new TableColumn("APaterno");
//        colAP.setMinWidth(150);
//        colAP.setCellValueFactory(
//                new PropertyValueFactory<>("apaterno"));
//
//        TableColumn colAM = new TableColumn("AMaterno");
//        colAM.setMinWidth(150);
//        colAM.setCellValueFactory(
//                new PropertyValueFactory<>("amaterno"));
//
//        TableColumn colCalle = new TableColumn("Calle");
//        colCalle.setMinWidth(150);
//        colCalle.setCellValueFactory(
//                new PropertyValueFactory<>("calle"));
//
//        TableColumn colColonia = new TableColumn("Colonia");
//        colColonia.setMinWidth(150);
//        colColonia.setCellValueFactory(
//                new PropertyValueFactory<>("colonia"));
//
//        TableColumn colCiudad = new TableColumn("Ciudad");
//        colCiudad.setMinWidth(150);
//        colCiudad.setCellValueFactory(
//                new PropertyValueFactory<>("ciudad"));
//
//        TableColumn colEstado = new TableColumn("Estado");
//        colEstado.setMinWidth(150);
//        colEstado.setCellValueFactory(
//                new PropertyValueFactory<>("estado"));
//
//        TableColumn colCP = new TableColumn("CP");
//        colCP.setMinWidth(150);
//        colCP.setCellValueFactory(
//                new PropertyValueFactory<>("codigopostal"));
//
//        TableColumn colTel = new TableColumn("Telefono");
//        colTel.setMinWidth(150);
//        colTel.setCellValueFactory(
//                new PropertyValueFactory<>("telefono"));
//
//        TableColumn colEmail = new TableColumn("Email");
//        colEmail.setMinWidth(150);
//        colEmail.setCellValueFactory(
//                new PropertyValueFactory<>("email"));
        tablaUsuarios.getColumns().clear();
        tablaUsuarios.getColumns().addAll(colID,
                colNU,
                colRol,
                colPass
        //                colNom,
        //                colAP,
        //                colAM,
        //                colCalle,
        //                colColonia,
        //                colCiudad,
        //                colEstado,
        //                colCP,
        //                colTel,
        //                colEmail
        );
    }

    private void fillTable() {
        ArrayList<Usuario> usuarios = conusuario.getAll();
        tablaUsuarios.getItems().clear();
        tablaUsuarios.getItems().addAll(usuarios);

        //llenar el combobox de tipo usuario
        ObservableList<String> roles
                = FXCollections.observableArrayList(
                        "Adm",
                        "Reg"
                );
        cbRol.setItems(roles);
        cbRol.setValue("Reg");
    }

    @FXML
    public void handleXLS(ActionEvent event) {
        ArrayList<Usuario> usuarios = conusuario.getAll();

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory
                = directoryChooser.showDialog(((Node) (event.getSource())).getScene().getWindow());

        if (selectedDirectory == null) {
            return;
        }
        String path = selectedDirectory.getAbsolutePath();

        ArrayList<Writable> towrite = new ArrayList<>(usuarios.size());
        for (int i = 0; i < usuarios.size(); i++) {
            towrite.add(usuarios.get(i));
        }

        XLSWriter xw = new XLSWriter();
        xw.writeXLS(towrite, path + "/", "Usuarios");
    }

    @FXML
    public void handlePDF(ActionEvent event)  {
        ArrayList<Usuario> usuarios = conusuario.getAll();

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory
                = directoryChooser.showDialog(((Node) (event.getSource())).getScene().getWindow());

        if (selectedDirectory == null) {
            return;
        }
        String path = selectedDirectory.getAbsolutePath();

        ArrayList<Writable> towrite = new ArrayList<>(usuarios.size());
        for (int i = 0; i < usuarios.size(); i++) {
            towrite.add(usuarios.get(i));
        }
        LogInManager logInManager = new LogInManager();
        Image logo = logInManager.getLogo();

        PDFWriter xw = new PDFWriter();
        try {
            xw.writePDF(towrite, path + "/", "Usuarios",SwingFXUtils.fromFXImage(logo, null));
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
