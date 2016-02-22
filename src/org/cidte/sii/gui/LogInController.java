/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.cidte.sii.entidades.Usuario;
import org.cidte.sii.hibernate.ConectorTipoUsuario;
import org.cidte.sii.hibernate.ConectorUsuario;

/**
 *
 * @author manuelmartinez
 */
public class LogInController implements Initializable {

    private ConectorUsuario conuser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelMensage.setText("");

        conuser = new ConectorUsuario();

        conuser.getAll();
    }

    @FXML
    private PasswordField pfContrasena;

    @FXML
    private TextField tfNombreUsuario;

    @FXML
    private Label labelMensage;

    @FXML
    void handleButtonEntrar(ActionEvent event) {
        String un = tfNombreUsuario.getText();
        String pass = pfContrasena.getText();

        if (un.isEmpty() || pass.isEmpty()) {
            System.out.println("un or pass is empty");
            labelMensage.setText("Hay un campo vacio!");
        } else {
            Usuario usuario = conuser.get(un, pass);
            if (usuario == null) {
                labelMensage.setText("Usuario o Contraseña incorrecta!!");
            } else {
                labelMensage.setText("Access Granted User Type: " + usuario.getTipoUsario().getNombre());
                int id_tipousario = usuario.getTipoUsario().getId_tipousario();
                Parent root;
                switch (id_tipousario) {
                    case 1:
                        try {
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("org/cidte/sii/gui/Menu.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("Administrar Sistemas");
                            stage.setScene(new Scene(root, 450, 450));
                            stage.show();

                            //hide this current window (if this is whant you want
                            //((Node) (event.getSource())).getScene().getWindow().hide();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Error en abrir ventana");
                        }

                        break;
                    case 2:                     
                    case 3:
                    case 4:
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cidte/sii/gui/Administrar.fxml"));

                            Stage stage = new Stage(StageStyle.DECORATED);
                            stage.setTitle("Administrar Usuarios");
                            stage.setScene( new Scene((Parent) loader.load()));

                            AdministrarController controller = loader.<AdministrarController>getController();
                            controller.initData(usuario);

                            stage.show();

                            //hide this current window (if this is whant you want
                            //((Node) (event.getSource())).getScene().getWindow().hide();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Error en abrir ventana");
                        }
                        break;
                }
            }
        }
    }

}
