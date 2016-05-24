/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion.UserWindow;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.util.Callback;
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
public class RH_PermisoTablaController implements Initializable {

    @FXML
    private Label lbTitulo;
    @FXML
    private TableView<Permiso> tablaPermisos;
    @FXML
    private Label lbSolicitante;
    @FXML
    private ComboBox<DatosGenerales> cbSolicitante;

    private DocumentoPDF current_doc;
    private ConectorPermiso cp;
    private ConectorDocumentoPDF cdpdf;
    private javafx.scene.image.Image gato;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cp = new ConectorPermiso();
        cdpdf = new ConectorDocumentoPDF();

        gato = new javafx.scene.image.Image("/org/cidte/sii/imagenes/gato.png");

        // llenar comobox de participantes
        ConectorDatosGenerales cdg = new ConectorDatosGenerales();
        ArrayList<DatosGenerales> all = cdg.getAll2();
        cbSolicitante.getItems().clear();
        cbSolicitante.getItems().addAll(all);

        crearTabla();
    }

    @FXML
    private void handleChangeSolicitante(ActionEvent event) {

        DatosGenerales dg = cbSolicitante.getSelectionModel().getSelectedItem();
        if (dg == null) {
            return;
        }
        fillTable(dg.getUsuario());
    }

    private void crearTabla() {
        ImageView iv;

        TableColumn colID = new TableColumn("ID");
        colID.setMinWidth(50);
        colID.setCellValueFactory(new PropertyValueFactory<>("id_permiso"));
        iv = new ImageView(gato);
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        colID.setGraphic(iv);

        TableColumn colMotivo = new TableColumn("Motivo");
        colMotivo.setMinWidth(200);
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo_solicitud"));
        iv = new ImageView(gato);
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        colMotivo.setGraphic(iv);

        TableColumn colFSolicitud = new TableColumn("Fecha Solicitud");
        colFSolicitud.setMinWidth(200);
        colFSolicitud.setCellValueFactory(new PropertyValueFactory<>("fecha_solicitud"));
        iv = new ImageView(gato);
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        colFSolicitud.setGraphic(iv);

        TableColumn colAutorizado = new TableColumn("Autorizado");
        colAutorizado.setMinWidth(200);
        colAutorizado.setCellValueFactory(new PropertyValueFactory<>("autorizado"));
        iv = new ImageView(gato);
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        colAutorizado.setGraphic(iv);
        //
        TableColumn colFAutorizacion = new TableColumn("Fecha_Autorización");
        colFAutorizacion.setMinWidth(200);
        colFAutorizacion.setCellValueFactory(new PropertyValueFactory<>("fecha_autorizacion"));
        iv = new ImageView(gato);
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        colFAutorizacion.setGraphic(iv);

        TableColumn colDocumento = new TableColumn("Documento");
        colDocumento.setMinWidth(200);
        iv = new ImageView(gato);
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        colDocumento.setGraphic(iv);
//        colDocumento.setCellValueFactory(
//                new PropertyValueFactory<>("amaterno"));

        colDocumento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        colDocumento.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
            @Override
            public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                return new ButtonCell(tablaPermisos);
            }
        });

        tablaPermisos.getColumns().clear();
        tablaPermisos.getColumns().addAll(colID, colMotivo, colFSolicitud,
                colAutorizado, colFAutorizacion, colDocumento);
    }

    private void fillTable(Usuario u) {

        //llenar la tabla permisos
        ArrayList<Permiso> p = cp.getAllFrom(u.getCurp());
        tablaPermisos.getItems().clear();
        tablaPermisos.getItems().addAll(p);

    }

    private class ButtonCell extends TableCell<Object, Boolean> {

        final Hyperlink cellButtonDelete = new Hyperlink("Descargar Documento");
//        final Hyperlink cellButtonEdit = new Hyperlink("Edit");
        final HBox hb = new HBox(cellButtonDelete);

        ButtonCell(final TableView tblView) {
            hb.setSpacing(4);

            cellButtonDelete.setOnAction((ActionEvent t) -> {

                int row = getTableRow().getIndex();
                tblView.getSelectionModel().select(row);
                Object selectedItem = tblView.getSelectionModel().getSelectedItem();
                Permiso p = (Permiso) selectedItem;
                DocumentoPDF doc = cdpdf.get(p.getDocumento());

                if (doc == null) {
//                    cellButtonDelete.setText("Ningun Documento");
                    return;
                }
//                cellButtonDelete.setText(doc.getNombre());

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save PDF");
                fileChooser.setInitialFileName(doc.getNombre());
                File file = fileChooser.showSaveDialog(((Node) (t.getSource())).getScene().getWindow());
                if (file != null) {
                    try {
                        PDFConverter con = new PDFConverter();
                        con.arrayToPDF(doc.getDocumento(), file.getCanonicalPath());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            });
//            cellButtonEdit.setOnAction((ActionEvent event) -> {
//                System.out.println("Moo on edit");
//            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(hb);
            } else {
                setGraphic(null);
            }
        }
    }
}
