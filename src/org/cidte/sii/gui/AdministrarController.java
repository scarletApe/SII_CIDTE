/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.cidte.sii.entidades.Usuario;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class AdministrarController implements Initializable {

    @FXML
    private Button btn_altas;
    @FXML
    private Button btn_cambios;
    @FXML
    private Button btn_bajas;
    @FXML
    private Label lb_welcome;
    
    private Usuario usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void initData(Usuario usuario) {
        this.usuario = usuario;
        lb_welcome.setText("Bienvenido "+usuario.getNombre()+" "+usuario.getApaterno());
        int id = usuario.getTipoUsario().getId_tipousario();
        switch(id){
            case 2:
                break;
            case 3:
                btn_bajas.setDisable(true);
                btn_cambios.setDisable(true);
                break;
            case 4:
                btn_altas.setDisable(true);
                break;
        }
  }
}
