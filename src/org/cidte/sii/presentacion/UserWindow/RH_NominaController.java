/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion.UserWindow;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.cidte.sii.entidades.DatosGenerales;
import org.cidte.sii.entidades.Nomina;
import org.cidte.sii.entidades.TipoContratacion;
import org.cidte.sii.hibernate.ConectorDatosGenerales;
import org.cidte.sii.hibernate.ConectorNomina;
import org.cidte.sii.hibernate.ConectorTipoContratacion;
import org.cidte.sii.presentacion.SII_CIDTE;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class RH_NominaController implements Initializable {

    @FXML
    private Label lbTitulo;
    @FXML
    private Button btnActualizar;
    @FXML
    private Label lbParticipante;
    @FXML
    private ComboBox<DatosGenerales> cbParticipante;
    @FXML
    private Label lbIDContratacion;
    @FXML
    private Label lbGrupoLaboral;
    @FXML
    private Label lbNivel;
    @FXML
    private Label lbCategorial;
    @FXML
    private Label lbFormaContratacion;
    @FXML
    private Label lbDescripcion;
    @FXML
    private Label lbValidacionDoc;
    @FXML
    private TextField tfGrupoLaboral;
    @FXML
    private TextField tfNivel;
    @FXML
    private TextField tfCategorial;
    @FXML
    private TextField tfFormaContratacion;
    @FXML
    private TextField tfDescripcion;
    @FXML
    private ImageView ivPDF;
    @FXML
    private Button btnElegir;
    @FXML
    private TextField tfIDContratacion;

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

        //llenar comobox de participantes
        ConectorDatosGenerales cdg = new ConectorDatosGenerales();
        ArrayList<DatosGenerales> all = cdg.getAll2();
        cbParticipante.getItems().clear();
        cbParticipante.getItems().addAll(all);

        btnActualizar.setDisable(true);
        btnElegir.setDisable(true);

    }

    @FXML
    private void handleAcutalizar(ActionEvent event) {

        String grupoLaboral = tfGrupoLaboral.getText();
        String nivel = tfNivel.getText();
        String categorial = tfCategorial.getText();
        String formaContratacion = tfFormaContratacion.getText();
        String descripcion = tfDescripcion.getText();

        //se obtiene el participante el contrato
        DatosGenerales dg = cbParticipante.getSelectionModel().getSelectedItem();
        ConectorTipoContratacion ctc = new ConectorTipoContratacion();
        TipoContratacion contrato = ctc.get(dg.getUsuario().getCurp());

        //luego se obtiene la nomina por si se va a actualizar
        ConectorNomina cn = new ConectorNomina();
        Nomina nomina = cn.get(contrato.getId_contratacion());

        if (nomina == null) {
            //crear una nueva
            Nomina n = new Nomina(contrato.getId_contratacion());
            n.setCategorial(categorial);
            n.setDescripcion(descripcion);
            n.setForma_contratacion(formaContratacion);
            n.setGrupo_laboral(grupoLaboral);
            n.setNivel(nivel);

            cn.saveNew(n);

            limpiar();
        } else {
            //modificar la ya existente
            nomina.setCategorial(categorial);
            nomina.setDescripcion(descripcion);
            nomina.setForma_contratacion(formaContratacion);
            nomina.setGrupo_laboral(grupoLaboral);
            nomina.setNivel(nivel);

            cn.update(nomina);

            limpiar();
        }
    }

    @FXML
    private void handleCambiarParticipante(ActionEvent event) {
        limpiar();

        DatosGenerales dg = cbParticipante.getSelectionModel().getSelectedItem();
        ConectorTipoContratacion ctc = new ConectorTipoContratacion();
        TipoContratacion contrato = ctc.get(dg.getUsuario().getCurp());
        if (contrato == null) {

            tfIDContratacion.setText("Porfavor creé un Tipo de Contratación.");
            btnActualizar.setDisable(true);
            btnElegir.setDisable(true);
        } else {
            //fill the info
            tfIDContratacion.setText(contrato.toString());
            btnActualizar.setDisable(false);
            btnElegir.setDisable(false);

            ConectorNomina cn = new ConectorNomina();
            Nomina nomina = cn.get(contrato.getId_contratacion());

            if (nomina != null) {
                tfGrupoLaboral.setText(nomina.getGrupo_laboral());
                tfNivel.setText(nomina.getNivel());
                tfCategorial.setText(nomina.getCategorial());
                tfFormaContratacion.setText(nomina.getForma_contratacion());
                tfDescripcion.setText(nomina.getDescripcion());
            }
        }
    }

    @FXML
    private void handleElegir(ActionEvent event) {
        System.out.println("boo");
    }

    private void limpiar() {
        tfIDContratacion.setText("");
        tfGrupoLaboral.setText("");
        tfNivel.setText("");
        tfCategorial.setText("");
        tfFormaContratacion.setText("");
        tfDescripcion.setText("");
    }

    private void setLabels() {
        ResourceBundle msg = SII_CIDTE.LOCALIZER.getMessages();

        lbTitulo.setText(msg.getString("nomina_modo_contratacion"));

        btnActualizar.setText(msg.getString("actualizar_datos"));
        btnElegir.setText(msg.getString("elegir"));

        lbParticipante.setText(msg.getString("participante"));
        lbIDContratacion.setText(msg.getString("contratacion"));
        lbGrupoLaboral.setText(msg.getString("grupo_laboral"));
        lbNivel.setText(msg.getString("nivel"));
        lbCategorial.setText(msg.getString("categorial"));
        lbFormaContratacion.setText(msg.getString("forma_contratacion"));
        lbDescripcion.setText(msg.getString("descripcion"));
        lbValidacionDoc.setText(msg.getString("validacion_documental"));
    }
}
