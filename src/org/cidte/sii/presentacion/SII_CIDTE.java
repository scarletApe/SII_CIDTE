package org.cidte.sii.presentacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.cidte.sii.hibernate.ConectorUsuario;
import org.cidte.sii.negocio.Localizer;

/**
 *
 * @author manuelmartinez
 */
public class SII_CIDTE extends Application {
    
    public static Localizer LOCALIZER;
    @Override
    public void start(Stage stage) throws Exception {
        
        //esto es solo para inicializar Hibernate
        ConectorUsuario conuser = new ConectorUsuario();
        conuser.getAll();
        
        //esto es para poner el lenguaje
        LOCALIZER = new Localizer("es");
        
        //esto muestra la ventana
        Parent root = FXMLLoader.load(getClass().getResource("/org/cidte/sii/presentacion/LogIn.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
