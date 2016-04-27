/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion.UserWindow;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.cidte.sii.entidades.DatosGenerales;
import org.cidte.sii.entidades.Usuario;
import org.cidte.sii.hibernate.ConectorDatosGenerales;
import org.cidte.sii.negocio.ImageManager;
import org.cidte.sii.negocio.Localizer;
import org.cidte.sii.negocio.LogInManager;
import org.cidte.sii.presentacion.LogInController;
import org.cidte.sii.presentacion.SII_CIDTE;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private ImageView ivLogo;
    @FXML
    private Label lbMensaje;
    @FXML
    private Button btnMiCuenta;
    @FXML
    private Button btnSalir;
    @FXML
    private Label lbFecha;
    @FXML
    protected ImageView ivUsuario;
    @FXML
    private Label lbUsuarioTexto;
    @FXML
    private ImageView ivBandera;
    @FXML
    private Label lbIdioma;
    @FXML
    private TabPane tpEscritorio;
    @FXML
    private Tab tabDashboard;
    @FXML
    private AnchorPane apaneDashboard;
    @FXML
    private Tab tabRH;
    @FXML
    private ListView<String> listRH;
    @FXML
    private AnchorPane apaneRH;
    @FXML
    private Tab tabRM;
    @FXML
    private Tab tabRF;
    @FXML
    private Tab tabIDE;
    @FXML
    private Tab tabMiCuenta;
    @FXML
    private AnchorPane apaneMiCuenta;

    private Usuario usuario;
    private AnchorPane dashboardPane;
    private MenuController dashboardController;
    private AnchorPane usuarioPane;
    private DatosUsuarioController usuarioController;

    private AnchorPane rh_tc_pane;
    private AnchorPane rh_nomina_pane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Usuario usuario, Stage s) {
        this.usuario = usuario;

        setLabels();
        loadPanes();

        ResourceBundle msg = SII_CIDTE.LOCALIZER.getMessages();

        LogInManager log = new LogInManager();
        ivLogo.setImage(log.getLogo());
        lbMensaje.setText(log.getCaption());

        ConectorDatosGenerales cdg = new ConectorDatosGenerales();
        DatosGenerales gen = cdg.get(usuario.getCurp());
        StringBuilder sb = new StringBuilder();
        sb.append(msg.getString("bienvenido")).append(" ");
        sb.append((gen.getNombre() == null) ? "" : gen.getNombre());
        sb.append(" ");
        sb.append((gen.getApaterno() == null) ? "" : gen.getApaterno());
        sb.append(" ");
        sb.append((gen.getAmaterno() == null) ? "" : gen.getAmaterno());
        sb.append(" ");
        lbUsuarioTexto.setText(sb.toString());

        Locale local = SII_CIDTE.LOCALIZER.getCurrentLocale();

        String displayLanguage = local.getDisplayLanguage();
        System.out.println("displayLanguage=" + displayLanguage);
        Image img = getImageView(displayLanguage);
        ivBandera.setImage(img);
        lbIdioma.setText(displayLanguage);

        Calendar cal = Calendar.getInstance(local);
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, local);
        lbFecha.setText(msg.getString("fecha") + ": " + df.format(cal.getTime()));

        ivUsuario.setImage(setFoto(gen));
        roundImage(ivUsuario);

        ObservableList<String> opciones
                = FXCollections.observableArrayList(
                        msg.getString("tipo_contratacion"),
                        msg.getString("nomina")
                );
        listRH.setItems(opciones);

        listRH.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
                    handleRHListMenu();
                });
    }

    private void setLabels() {
        ResourceBundle msg = SII_CIDTE.LOCALIZER.getMessages();

        btnMiCuenta.setText(msg.getString("mi_cuenta"));
        btnSalir.setText(msg.getString("salir"));

        tabDashboard.setText(msg.getString("dashboard"));
        tabRH.setText(msg.getString("recursos_humanos"));
        tabRM.setText(msg.getString("recursos_materiales"));
        tabRF.setText(msg.getString("recursos_financieros"));
        tabIDE.setText(msg.getString("ide"));
        tabMiCuenta.setText(msg.getString("mi_cuenta"));

    }

    private void loadPanes() {
        try {
            //dashboard pane
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/org/cidte/sii/presentacion/UserWindow/Menu.fxml"));
            dashboardPane = (AnchorPane) loader.load();
            dashboardController = loader.<MenuController>getController();
            dashboardPane.setPrefSize(apaneDashboard.getWidth(), apaneDashboard.getHeight());
            apaneDashboard.getChildren().setAll(dashboardPane);

            //datos de usuario pane
            loader = new FXMLLoader(getClass()
                    .getResource("/org/cidte/sii/presentacion/UserWindow/DatosUsuario.fxml"));
            usuarioPane = (AnchorPane) loader.load();
            usuarioController = loader.<DatosUsuarioController>getController();
            usuarioPane.setPrefSize(apaneDashboard.getWidth() - 15, apaneMiCuenta.getHeight());
            apaneMiCuenta.getChildren().setAll(usuarioPane);
            usuarioController.initData(usuario, this);

            //******************************************************************
            loader = new FXMLLoader(getClass()
                    .getResource("/org/cidte/sii/presentacion/UserWindow/RH_Tipo_Contratacion.fxml"));
            rh_tc_pane = (AnchorPane) loader.load();
            RH_Tipo_ContratacionController rhtcc = loader.<RH_Tipo_ContratacionController>getController();
            rh_tc_pane.setPrefSize(apaneRH.getWidth(), apaneRH.getHeight());
            apaneRH.getChildren().setAll(rh_tc_pane);

            loader = new FXMLLoader(getClass()
                    .getResource("/org/cidte/sii/presentacion/UserWindow/RH_Nomina.fxml"));
            rh_nomina_pane = (AnchorPane) loader.load();
//            RH_Tipo_ContratacionController rhtcc = loader.<RH_Tipo_ContratacionController>getController();
            rh_nomina_pane.setPrefSize(apaneRH.getWidth(), apaneRH.getHeight());
//            apaneRH.getChildren().setAll(rh_tc_pane);

        } catch (Exception e) {
            System.err.println("Error cargando los panes " + e);
        }
    }

    private Image setFoto(DatosGenerales generales) {
        byte[] foto = generales.getFoto();
        if (foto == null || foto.length < 1) {
            return new Image("/org/cidte/sii/imagenes/person.png");
        }
        try {
            return ImageManager.byteArraytoFXImage(foto);
        } catch (IOException e) {
            System.out.println("Error en covertir imagen " + e);
            return new Image("/org/cidte/sii/imagenes/person.png");
        }
    }

    @FXML
    private void handleMiCuenta(ActionEvent event) {
        tpEscritorio.getSelectionModel().select(tabMiCuenta);
    }

    @FXML
    private void handleSalir(ActionEvent event) {
        System.exit(0);
    }

    public void handleRHListMenu() {
        switch (listRH.getSelectionModel().getSelectedIndex()) {
            case 0: {
                setPane(apaneRH, rh_tc_pane);
            }
            break;
            case 1: {
                setPane(apaneRH, rh_nomina_pane);
            }
            break;
            case 2: {

            }
            break;
        }
    }

    private void setPane(AnchorPane papa, AnchorPane to_put) {
        to_put.setPrefSize(papa.getWidth(), papa.getHeight());
        to_put.setMinSize(papa.getWidth(), papa.getHeight());
        papa.getChildren().setAll(to_put);
    }

    private static Image getImageView(String imageName) {
        Image img = null;
        String r = "/org/cidte/sii/imagenes/";
        switch (imageName) {
            case "Español":
            case "Spanish":
                img = new Image(r + "flag_mx.gif");
                break;
            case "English":
            case "Inglés":
                img = new Image(r + "flag_us.gif");
                break;
            default:
                img = null;
        }
        return img;
    }

    private void roundImage(ImageView imageView) {
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(
                imageView.getFitWidth(), imageView.getFitHeight()
//                imageView.getImage().getRequestedWidth(), imageView.getImage().getRequestedHeight()
//                imageView.getImage().getWidth(), imageView.getImage().getWidth()
           
        );
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imageView.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        imageView.setClip(null);

        // apply a shadow effect.
//        imageView.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        imageView.setImage(image);
    }
}
