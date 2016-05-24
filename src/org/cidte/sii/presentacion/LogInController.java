/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion;

import static org.cidte.sii.presentacion.SII_CIDTE.LOCALIZER;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.cidte.sii.entidades.Usuario;
import org.cidte.sii.negocio.LogInManager;
import org.cidte.sii.presentacion.AdminWindow.AdministrarController;
import org.cidte.sii.presentacion.UserWindow.VentanaPrincipalController;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author manuelmartinez
 */
public class LogInController implements Initializable {

	private LogInManager lim;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lim = new LogInManager();
		setLabels();

		ivLogo.setImage(lim.getLogo());

		// llenar el combobox de sexos
		ObservableList<String> idiomas = FXCollections.observableArrayList("Español", "English");
		cbIdiomas.setItems(idiomas);
		cbIdiomas.setValue("Español");
		cbIdiomas.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
					String lan = null;
					if (nv.equalsIgnoreCase("Español")) {
						lan = "es";
					} else if (nv.equalsIgnoreCase("English")) {
						lan = "en";
					}
					LOCALIZER.changeLocale(lan);
					setLabels();
				});
		cbIdiomas.setCellFactory(listview -> new StringImageCell());
		cbIdiomas.setButtonCell(new StringImageCell());
	}

	// A Custom ListCell that displays an image and string
	static class StringImageCell extends ListCell<String> {

		Label label;

		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (item == null || empty) {
				setItem(null);
				setGraphic(null);
			} else {
				setText(item);
				// setTextFill(Color.WHITE);
				ImageView image = getImageView(item);
				label = new Label("", image);
				setGraphic(label);
			}
		}

		private static ImageView getImageView(String imageName) {
			ImageView imageView = null;
			String r = "/org/cidte/sii/imagenes/";
			switch (imageName) {
			case "Español":
				imageView = new ImageView(new Image(r + "flag_mx.gif"));
				break;
			case "English":
				imageView = new ImageView(new Image(r + "flag_us.gif"));
				break;
			default:
				imageName = null;
			}
			return imageView;
		}

	}

	private void setLabels() {
		ResourceBundle msg = LOCALIZER.getMessages();

		lbCaption.setText(lim.getCaption());
		lbUsuario.setText(msg.getString("usuario"));
		lbContrasena.setText(msg.getString("contrasena"));
		btnEntrar.setText(msg.getString("entrar"));
		labelMensage.setText("");
	}

	@FXML
	private Label lbContrasena;

	@FXML
	private ImageView ivLogo;

	@FXML
	private Label lbUsuario;

	@FXML
	private PasswordField pfContrasena;

	@FXML
	private Label lbCaption;

	@FXML
	private TextField tfNombreUsuario;

	@FXML
	private Label labelMensage;

	@FXML
	private ComboBox<String> cbIdiomas;

	@FXML
	private Button btnEntrar;

	@FXML
	void handleButtonEntrar(ActionEvent event) {
		String un = tfNombreUsuario.getText();
		String pass = pfContrasena.getText();
		ResourceBundle msg = LOCALIZER.getMessages();

		if (un.isEmpty() || pass.isEmpty()) {
			labelMensage.setText(msg.getString("mensage_campo_vacio"));
			return;
		}
		Usuario usuario = lim.logIn(un, pass);
		if (usuario == null) {
			labelMensage.setText(msg.getString("mensage_error"));
			return;
		}
		String rol = usuario.getRol();
		labelMensage.setText(msg.getString("mensage_bueno") + ": " + rol);

		if (rol.equalsIgnoreCase("Adm")) {
			try {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/org/cidte/sii/presentacion/AdminWindow/Administrar.fxml"));

				Stage stage = new Stage(StageStyle.DECORATED);
				stage.setTitle("Administrator Window");
				stage.setMaximized(true);

				Parent root = (Parent) loader.load();

				// apply the css
				root.getStylesheets().add(getClass().getResource("/org/cidte/sii/css/Metro-UI.css").toExternalForm());
				// root.getStylesheets().add(getClass().getResource("/org/cidte/sii/css/login.css").toExternalForm());
				root.getStylesheets()
						.add(getClass().getResource("/org/cidte/sii/css/buttonCircle.css").toExternalForm());
				root.getStylesheets().add(getClass().getResource("/org/cidte/sii/css/listView.css").toExternalForm());

				stage.setScene(new Scene(root));

				AdministrarController controller = loader.<AdministrarController> getController();
				controller.initData(usuario, stage);

				stage.show();

				// hide this current window (if this is whant you want
				((Node) (event.getSource())).getScene().getWindow().hide();
			} catch (IOException e) {
				System.out.println("Error en abrir ventana " + e);
				e.printStackTrace();
			}
		} else if (rol.equalsIgnoreCase("Reg")) {
			try {
				// Parent root =
				// FXMLLoader.load(getClass().getClassLoader().getResource("org/cidte/sii/presentacion/UserWindow/VentanaPrincipal.fxml"));
				// Stage stage = new Stage();
				//// stage.setTitle("Sistema");
				// stage.setScene(new Scene(root, 830, 560));
				// stage.show();
				//
				// //hide this current window (if this is whant you want
				// ((Node) (event.getSource())).getScene().getWindow().hide();

				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/org/cidte/sii/presentacion/UserWindow/VentanaPrincipal.fxml"));

				Stage stage = new Stage(StageStyle.DECORATED);
				// stage.setTitle("Administrator Window");
				stage.setMaximized(true);

				Parent root = (Parent) loader.load();

				// apply the css
				root.getStylesheets().add(getClass().getResource("/org/cidte/sii/css/Metro-UI.css").toExternalForm());
				root.getStylesheets().add(getClass().getResource("/org/cidte/sii/css/buttonCircle.css").toExternalForm());
				root.getStylesheets().add(getClass().getResource("/org/cidte/sii/css/listView.css").toExternalForm());
				

				stage.setScene(new Scene(root));

				VentanaPrincipalController controller = loader.<VentanaPrincipalController> getController();

				stage.show();
				controller.initData(usuario, stage); // i switched this to
														// activate after

				// hide this current window (if this is whant you want
				((Node) (event.getSource())).getScene().getWindow().hide();
			} catch (IOException e) {
				System.out.println("Error en abrir ventana " + e);
			}
		}

	}
}
