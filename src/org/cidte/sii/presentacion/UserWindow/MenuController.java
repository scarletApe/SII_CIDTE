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

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class MenuController implements Initializable {

	@FXML
	private Button btnAdmon;
	@FXML
	private Button btnConta;
	@FXML
	private Button btnProd;
	@FXML
	private Button btnRepor;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private Button btnLogout;

	@FXML
	void handleLogout(ActionEvent event) {

	}

}
