/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion.AdminWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.cidte.sii.entidades.Usuario;
import org.cidte.sii.presentacion.SII_CIDTE;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class AdministrarController implements Initializable {

    @FXML
    private Button btnLogout;

    @FXML
    private AnchorPane paneData;

    @FXML
    private Button btnResize;

    @FXML
    private ListView<String> listMenu;

    @FXML
    private Label lbTitle;

    private Usuario usuario;

    private AnchorPane usuariosPane;
    private AnchorPane personalizacionPane;

    private UsuariosController usuariosController;
    private PersonalizacionController personalizacionController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ResourceBundle msg = SII_CIDTE.LOCALIZER.getMessages();

        ObservableList<String> opciones
                = FXCollections.observableArrayList(
                        msg.getString("usuarios"),
                        msg.getString("personalizar")
                );
        listMenu.setItems(opciones);

        //load the first pane
        Platform.runLater(() -> {
            listMenu.getSelectionModel().select(0);
            loadAllPanes();
            paneData.getChildren().setAll(usuariosPane);
////            setPane(usuariosPane);
            listMenu.requestFocus();

            listMenu.getSelectionModel().selectedItemProperty()
                    .addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
                        handleListMenu();
                    });
        });

    }

    public void initData(Usuario usuario, Stage s) {
        this.usuario = usuario;

        ResourceBundle msg = SII_CIDTE.LOCALIZER.getMessages();
        lbTitle.setText(msg.getString("bienvenido"));
    }

    @FXML
    void handleResize(ActionEvent event) {

    }

    private void handleListMenu() {
        switch (listMenu.getSelectionModel().getSelectedIndex()) {
            case 0: {
//                loadAnchorPane(paneData, "Usuarios.fxml");
                setPane(usuariosPane);
            }
            break;
            case 1: {
//                loadAnchorPane(paneData, "Personalizacion.fxml");
                setPane(personalizacionPane);
            }
            break;
            case 2: {

            }
            break;
        }
    }

    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        //hide this current window (if this is whant you want
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
        //esto muestra la ventana
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("/org/cidte/sii/presentacion/LogIn.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void loadAllPanes() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/org/cidte/sii/presentacion/AdminWindow/Usuarios.fxml"));
            usuariosPane = (AnchorPane) loader.load();
            usuariosController = loader.<UsuariosController>getController();

            loader = new FXMLLoader(getClass()
                    .getResource("/org/cidte/sii/presentacion/AdminWindow/Personalizacion.fxml"));
            personalizacionPane = (AnchorPane) loader.load();
            personalizacionController = loader.<PersonalizacionController>getController();

        } catch (IOException e) {
            System.err.println("Error Loading all panes " + e);
        }
    }

    public void setPane(AnchorPane p) {
//        System.out.println("p_width=" + p.getWidth() + " p_height=" + p.getHeight());
        p.setPrefSize(paneData.getWidth(), paneData.getHeight());
        p.setMinSize(paneData.getWidth(), paneData.getHeight());
//        System.out.println("pd_width=" + paneData.getWidth() + " pd_height=" + paneData.getHeight());
//        System.out.println("p_width=" + p.getWidth() + " p_height=" + p.getHeight());
        paneData.getChildren().setAll(p);
    }
}
