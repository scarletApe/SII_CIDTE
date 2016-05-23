/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion.UserWindow;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.cidte.sii.entidades.DatosGenerales;
import org.cidte.sii.entidades.TipoContratacion;
import org.cidte.sii.entidades.Usuario;
import org.cidte.sii.hibernate.ConectorDatosGenerales;
import org.cidte.sii.hibernate.ConectorTipoContratacion;
import org.cidte.sii.presentacion.SII_CIDTE;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class RH_Tipo_ContratacionController implements Initializable {

    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbTipoParticipacion;
    @FXML
    private ComboBox<String> cbTipoParticipacion;
    @FXML
    private Label lbHoras;
    @FXML
    private ComboBox<String> cbHoras;
    @FXML
    private TextField tfHoras;
    @FXML
    private Label lbParticipante;
    @FXML
    private ComboBox<DatosGenerales> cbParticipante;
    @FXML
    private Label lbIntExt;
    @FXML
    private ComboBox<String> cbIntExt;
    @FXML
    private Label lbInstitucion;
    @FXML
    private TextField tfInstitucion;
    @FXML
    private Label lbArea;
    @FXML
    private TextField tfArea;
    @FXML
    private Label lbPrograma;
    @FXML
    private TextField tfPrograma;
    @FXML
    private Button btnActualizar;
    @FXML
    private Label lbFInicio;
    @FXML
    private Label lbFTerminacion;
    @FXML
    private DatePicker dpFInicio;
    @FXML
    private DatePicker dpFTerminacion;

    private ConectorTipoContratacion ctc;
    private ObservableList<String> horas;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setLabels();

        ctc = new ConectorTipoContratacion();

        //llenar comobox de participantes
        ConectorDatosGenerales cdg = new ConectorDatosGenerales();
        ArrayList<DatosGenerales> all = cdg.getAll2();
        cbParticipante.getItems().clear();
        cbParticipante.getItems().addAll(all);

        //llenar el combobox de tipo de participantes
        ObservableList<String> tipo_parti
                = FXCollections.observableArrayList(
                        "Docente",
                        "Trabajador",
                        "Estudiante"
                );
        cbTipoParticipacion.setItems(tipo_parti);
        cbTipoParticipacion.setValue(tipo_parti.get(0));
        cbTipoParticipacion.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
                    switch (nv) {
                        case "Docente":
                            tfInstitucion.setText("SPAUAZ");
                            break;
                        case "Trabajador":
                            tfInstitucion.setText("STUAZ");
                            break;
                        case "Estudiante":
                            tfInstitucion.setText("");
                            break;
                    }
                });
        tfInstitucion.setText("SPAUAZ");

        //llenar el combobox de horas
         horas
                = FXCollections.observableArrayList(
                        "Tiempo Completo",
                        "Tiempo Medio",
                        "Horas"
                );
        cbHoras.setItems(horas);
        cbHoras.setValue(horas.get(0));
        cbHoras.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
                    switch (nv) {
                        case "Tiempo Completo":
                            tfHoras.setText("40");
                            tfHoras.setDisable(true);
                            break;
                        case "Medio Tiempo":
                            tfHoras.setText("20");
                            tfHoras.setDisable(true);
                            break;
                        case "Horas":
                            tfHoras.setText("0");
                            tfHoras.setDisable(false);
                            break;
                    }
                });
        tfHoras.setText("40");
        tfHoras.setDisable(true);

        //llenar el combobox interno o externo
        ObservableList<String> int_ext
                = FXCollections.observableArrayList(
                        "Interno",
                        "Externo"
                );
        cbIntExt.setItems(int_ext);
        cbIntExt.setValue(int_ext.get(0));
        cbIntExt.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
                    switch (nv) {
                        case "Interno":
//                            tfInstitucion.setText("SPAUAZ");
                            break;
                        case "Externo":
                            tfInstitucion.setText("");
                            break;
                    }
                });
    }

    @FXML
    private void handleCambioTipoParticipacion(ActionEvent event) {
    }

    @FXML
    private void handleCambioHoras(ActionEvent event) {
    }

    @FXML
    private void handleCambioParticipante(ActionEvent event) {
        limpiar();
        
        DatosGenerales dg = cbParticipante.getSelectionModel().getSelectedItem();
        TipoContratacion contrato = ctc.get(dg.getUsuario().getCurp());
        if (contrato == null) {

        } else {
            //fill the info
            cbTipoParticipacion.setValue(contrato.getTipo_participacion());
            
            tfHoras.setText(contrato.getHoras());
            setComboHoras(contrato.getHoras());
            
            cbIntExt.setValue(contrato.getInterno());
            tfInstitucion.setText(contrato.getInstitucion());
            tfArea.setText(contrato.getArea());
            tfPrograma.setText(contrato.getPrograma());
            
            java.sql.Date d = (java.sql.Date) contrato.getFecha_inicio();
            if (d != null) {
                dpFInicio.setValue(d.toLocalDate()); 
            }
            
            d = (java.sql.Date) contrato.getFecha_terminacion();
            if (d != null) {
                dpFTerminacion.setValue(d.toLocalDate()); 
            }
        }
    }

    @FXML
    private void handleCambioIntExt(ActionEvent event) {
    }

    @FXML
    private void handleActualizar(ActionEvent event) {

        DatosGenerales dg = cbParticipante.getSelectionModel().getSelectedItem();
        if (dg == null) {
            return;
        }
        Usuario u = dg.getUsuario();
        String tipoParticipacion = cbTipoParticipacion.getSelectionModel().getSelectedItem();
        String horas = tfHoras.getText();
        String interior = cbIntExt.getSelectionModel().getSelectedItem();
        String insitucion = tfInstitucion.getText();
        String area = tfArea.getText();
        String programa = tfPrograma.getText();
        String fInicio = dpFInicio.getEditor().getText();
        String fTer = dpFTerminacion.getEditor().getText();

        TipoContratacion contrato = ctc.get(u.getCurp());
        if (contrato == null) {
            //no hay un contrato
            TipoContratacion c = new TipoContratacion(u.getCurp());
            c.setArea(area);
            c.setHoras(horas);
            c.setInstitucion(insitucion);
            c.setInterno(interior);
            c.setPrograma(programa);
            c.setTipo_participacion(tipoParticipacion);
            c.setFecha_inicio((fInicio.isEmpty() ? null : new Date(fInicio)));
            c.setFecha_terminacion((fTer.isEmpty() ? null : new Date(fTer)));

            ctc.saveNew(c);

            limpiar();
        } else {
            //actualizar el contrato

            contrato.setArea(area);
            contrato.setHoras(horas);
            contrato.setInstitucion(insitucion);
            contrato.setInterno(interior);
            contrato.setPrograma(programa);
            contrato.setTipo_participacion(tipoParticipacion);
            contrato.setFecha_inicio((fInicio.isEmpty() ? null : new Date(fInicio)));
            contrato.setFecha_terminacion((fTer.isEmpty() ? null : new Date(fTer)));

            ctc.update(contrato);

            limpiar();
        }
    }

    private void setLabels() {
        ResourceBundle msg = SII_CIDTE.LOCALIZER.getMessages();

        lbTitulo.setText(msg.getString("tipo_contratacion"));
        btnActualizar.setText(msg.getString("actualizar_datos"));

        lbParticipante.setText(msg.getString("participante"));
        lbTipoParticipacion.setText(msg.getString("tipo_de_participacion"));
        lbHoras.setText(msg.getString("horas_de_participacion"));
        lbIntExt.setText(msg.getString("interno_externo"));
        lbInstitucion.setText(msg.getString("institucion"));
        lbArea.setText(msg.getString("area"));
        lbPrograma.setText(msg.getString("programa"));
        lbFInicio.setText(msg.getString("fecha_inicio"));
        lbFTerminacion.setText(msg.getString("fecha_terminacion"));
    }

    private void limpiar() {
        cbTipoParticipacion.setValue("");
        cbHoras.setValue("");
        tfHoras.setText("");
        cbIntExt.setValue("");
        tfInstitucion.setText("");
        tfArea.setText("");
        tfPrograma.setText("");
        
        /** @todo I have no idea what happens right here that it disapears
        but then it doens't allow to reappear.
        */
//        dpFInicio.getEditor().clear(); //setText("");
//        dpFTerminacion.getEditor().clear(); //setText("");
    }
    
    private void setComboHoras(String h){
        try{
            int hrs = Integer.parseInt(h);
            if(hrs == 40){
                cbHoras.setValue(horas.get(0));
                return;
            }
            if(hrs == 20){
                cbHoras.setValue(horas.get(1));
                return;
            }
            cbHoras.setValue(horas.get(2));
        }catch(Exception e){
            System.out.println("error parsing "+e);
        }
    }
}
