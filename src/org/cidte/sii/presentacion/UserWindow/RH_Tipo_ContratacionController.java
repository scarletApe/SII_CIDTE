/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion.UserWindow;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private ComboBox<?> cbTipoParticipacion;
    @FXML
    private Label lbHoras;
    @FXML
    private ComboBox<?> cbHoras;
    @FXML
    private TextField tfHoras;
    @FXML
    private Label lbParticipante;
    @FXML
    private ComboBox<?> cbParticipante;
    @FXML
    private Label lbIntExt;
    @FXML
    private ComboBox<?> cbIntExt;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setLabels();
    }    

    @FXML
    private void handleCambioTipoParticipacion(ActionEvent event) {
    }

    @FXML
    private void handleCambioHoras(ActionEvent event) {
    }

    @FXML
    private void handleCambioParticipante(ActionEvent event) {
    }

    @FXML
    private void handleCambioIntExt(ActionEvent event) {
    }

    @FXML
    private void handleActualizar(ActionEvent event) {
    }
    
    private void setLabels(){
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
    }
}
