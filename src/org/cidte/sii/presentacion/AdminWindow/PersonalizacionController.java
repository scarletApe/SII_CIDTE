/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion.AdminWindow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.cidte.sii.entidades.Organizacion;
import org.cidte.sii.hibernate.ConectorOrganizacion;
import org.cidte.sii.negocio.ImageManager;
import org.cidte.sii.negocio.LogInManager;
import org.cidte.sii.presentacion.SII_CIDTE;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class PersonalizacionController implements Initializable {

	@FXML
	private AnchorPane paneTabel;
	@FXML
	private Label lbNombreOrg;
	@FXML
	private TextField tfNombreOrg;
	@FXML
	private TextField tfMensaje;
	@FXML
	private Label lbMensaje;
	@FXML
	private ImageView ivImagen;
	@FXML
	private Button btnSelecImg;
	@FXML
	private Button btnActualizar;
	@FXML
	private ProgressBar bar;
	@FXML
	private Label lbTitle;
	@FXML
	private TitledPane tpTPane;

	private ConectorOrganizacion conorg;
	private Organizacion organizacion;
	private boolean isNew = false;

	/**
	 * Initializes the controller class.
	 *
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		conorg = new ConectorOrganizacion();
		organizacion = null;
		ArrayList<Organizacion> orgs = conorg.getAll();
		if (orgs != null && !orgs.isEmpty()) {
			// hay una organizaaicon registrada
			Organizacion o = orgs.get(0);
			organizacion = o;
			tfNombreOrg.setText(o.getNombre());
			tfMensaje.setText(o.getMensaje());
			ivImagen.setImage(new LogInManager().getLogo());
		} else {
			// no hay organizacion registrada, crear una.
			organizacion = new Organizacion("Su nombre", "Algun mensage", new byte[1]);
			ivImagen.setImage(new LogInManager().getLogo());
			isNew = true;
		}
		setLabels();
	}

	private void setLabels() {
		ResourceBundle msg = SII_CIDTE.LOCALIZER.getMessages();

		lbTitle.setText(msg.getString("personalizar_sitio"));
		lbNombreOrg.setText(msg.getString("nombre_org"));
		lbMensaje.setText(msg.getString("texto_vacio"));

		// logo_de_org
		tpTPane.setText(msg.getString("logo_de_org"));

		btnActualizar.setText(msg.getString("btn_actualizar_datos"));
		btnSelecImg.setText(msg.getString("btn_seleccionar_imagen"));

	}

	@FXML
	private void handleSelectImage(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");

		// Set extension filter
		List<String> l = new ArrayList<String>();
		l.add("*.png");
		l.add("*.jpg");
		l.add("*.gif");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (png, jpg, gif)", l);
		fileChooser.getExtensionFilters().add(extFilter);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(((Node) (event.getSource())).getScene().getWindow());
		if (file != null) {
			try {
				byte[] data = ImageManager.readBytesFromFile(file.getCanonicalPath());
				organizacion.setLogo(data);
				ivImagen.setImage(ImageManager.byteArraytoFXImage(data));

			} catch (IOException | java.lang.IllegalArgumentException e) {
				System.out.println("Error en cargar imagen " + e);
			}

		}
	}

	@FXML
	private void handleActualizar(ActionEvent event) {
		Organizacion o = organizacion;
		o.setNombre(tfNombreOrg.getText());
		o.setMensaje(tfMensaje.getText());
		if (isNew) {
			conorg.saveNew(o);
		} else {
			conorg.update(organizacion);
		}
	}

}
