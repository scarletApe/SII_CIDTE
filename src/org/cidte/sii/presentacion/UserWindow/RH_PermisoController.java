/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion.UserWindow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.cidte.sii.entidades.DatosGenerales;
import org.cidte.sii.entidades.DocumentoPDF;
import org.cidte.sii.entidades.Permiso;
import org.cidte.sii.entidades.Usuario;
import org.cidte.sii.hibernate.ConectorDatosGenerales;
import org.cidte.sii.hibernate.ConectorDocumentoPDF;
import org.cidte.sii.hibernate.ConectorPermiso;
import org.cidte.sii.negocio.PDFConverter;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class RH_PermisoController implements Initializable {

    @FXML
    private Label lbTitulo;
    @FXML
    private Button btnActualizar;
    @FXML
    private Label lbSolicitador;
    @FXML
    private Label lbMotivo;
    @FXML
    private Label lbFechaSol;
    @FXML
    private Label lbAuto;
    @FXML
    private Label lbFechaAuto;
    @FXML
    private Label lbDoc;
    @FXML
    private TextField tfMotivo;
    @FXML
    private DatePicker dpFechaSol;
    @FXML
    private DatePicker dpFechaAuto;
    @FXML
    private Button btnElegir;
    @FXML
    private Label lbShowDoc;
    @FXML
    private ComboBox<String> cbAuto;
    @FXML
    private ComboBox<DatosGenerales> cbSolicitador;
    @FXML
    private Hyperlink hlShowDoc;

    private DocumentoPDF current_doc;
    private ConectorPermiso cp;
    private ConectorDocumentoPDF cdpdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cp = new ConectorPermiso();
        cdpdf = new ConectorDocumentoPDF();

        //llenar comobox de participantes
        ConectorDatosGenerales cdg = new ConectorDatosGenerales();
        ArrayList<DatosGenerales> all = cdg.getAll2();
        cbSolicitador.getItems().clear();
        cbSolicitador.getItems().addAll(all);

        //llenar el combobox de autorizado
        ObservableList<String> autorizado
                = FXCollections.observableArrayList(
                        "Si",
                        "No"
                );
        cbAuto.setItems(autorizado);
        cbAuto.setValue(autorizado.get(0));
    }

    @FXML
    private void handleActualizar(ActionEvent event) {
        DatosGenerales dg = cbSolicitador.getSelectionModel().getSelectedItem();
        if (dg == null) {
            return;
        }
        Usuario u = dg.getUsuario();

        String motivo = tfMotivo.getText();
        String fsolicitud = dpFechaSol.getEditor().getText();
        String autorizado = cbAuto.getSelectionModel().getSelectedItem();
        String fauto = dpFechaAuto.getEditor().getText();

        if (motivo.isEmpty()
                || fsolicitud.isEmpty()
                || autorizado.isEmpty()
                || fauto.isEmpty()) {
            return;
        }
        boolean autorizado_bool;
        switch (autorizado) {
            case "Si":
            case "Yes":
            case "Oui":
                autorizado_bool = true;
                break;
            case "No":
            case "Non":
            default:
                autorizado_bool = false;
                break;

        }

        Permiso p = cp.get(u.getCurp());
        if (p == null) {
            //no hay entonces crear
            p = new Permiso();
            p.setCurp(u.getCurp());
            p.setMotivo_solicitud(motivo);
            p.setFecha_solicitud((fsolicitud.isEmpty() ? null : new Date(fsolicitud)));
            p.setAutorizado(autorizado_bool);
            p.setFecha_autorizacion((fauto.isEmpty() ? null : new Date(fauto)));

            if (current_doc != null) {
                cdpdf.saveNew(current_doc);
                p.setDocumento(current_doc.getId_documentopdf());
            }

            cp.saveNew(p);
            limpiar();
        } else {
            //actualizarlo
            p.setCurp(u.getCurp());
            p.setMotivo_solicitud(motivo);
            p.setFecha_solicitud((fsolicitud.isEmpty() ? null : new Date(fsolicitud)));
            p.setAutorizado(autorizado_bool);
            p.setFecha_autorizacion((fauto.isEmpty() ? null : new Date(fauto)));

            if (current_doc != null) {
                cdpdf.update(current_doc);
                p.setDocumento(current_doc.getId_documentopdf());
            }
//            else {
//                p.setDocumento(0);
//            }

            cp.update(p);
            limpiar();
        }
    }

    @FXML
    private void handleElegir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open PDF File");

        // Set extension filter
        List<String> l = new ArrayList();
        l.add("*.pdf");
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("Document files (pdf)", l);
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(((Node) (event.getSource())).getScene().getWindow());
        if (file != null) {

            try {
                PDFConverter con = new PDFConverter();
                byte[] pdfToArray = con.pdfToArray(file.getCanonicalPath());
                current_doc = new DocumentoPDF(file.getName(), pdfToArray);
                hlShowDoc.setText(file.getName());

            } catch (IOException ex) {
                System.out.println("Error loading pdf " + ex);
            }
        }
    }

    @FXML
    private void handleShowDoc(ActionEvent event) {
        if (current_doc == null) {
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF");
        fileChooser.setInitialFileName(current_doc.getNombre());
        File file = fileChooser.showSaveDialog(((Node) (event.getSource())).getScene().getWindow());
        if (file != null) {
            try {
                PDFConverter con = new PDFConverter();
                con.arrayToPDF(current_doc.getDocumento(), file.getCanonicalPath());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void handleCambioSolicitante(ActionEvent event) {
        limpiar();

        DatosGenerales dg = cbSolicitador.getSelectionModel().getSelectedItem();
        if (dg == null) {
            return;
        }

        Permiso p = cp.get(dg.getUsuario().getCurp());
        if (p == null) {

        } else {
            //fill the info

            cbAuto.setValue((p.isAutorizado()) ? "Si" : "No");
            tfMotivo.setText(p.getMotivo_solicitud());

            java.sql.Date d = (java.sql.Date) p.getFecha_solicitud();
            if (d != null) {
                dpFechaSol.setValue(d.toLocalDate());
            }

            d = (java.sql.Date) p.getFecha_solicitud();
            if (d != null) {
                dpFechaAuto.setValue(d.toLocalDate());
            }

            int documento = p.getDocumento();

            DocumentoPDF get = cdpdf.get(documento);
            if (get == null) {
                //no hay documento
                current_doc = null;
            } else {
                hlShowDoc.setText(get.getNombre());
                current_doc = get;
            }
        }
    }

    private void limpiar() {
//        cbSolicitador.setValue(null);
        cbAuto.setValue(null);
        tfMotivo.setText("");

        current_doc = null;
        hlShowDoc.setText("Ningun Documento");
    }
}
