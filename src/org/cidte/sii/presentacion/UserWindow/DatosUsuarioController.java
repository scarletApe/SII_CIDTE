/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.presentacion.UserWindow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.cidte.sii.entidades.DatosGenerales;
import org.cidte.sii.entidades.DatosLegales;
import org.cidte.sii.entidades.DatosMedicos;
import org.cidte.sii.entidades.DatosPersonales;
import org.cidte.sii.entidades.Usuario;
import org.cidte.sii.hibernate.ConectorDatosGenerales;
import org.cidte.sii.hibernate.ConectorDatosLegales;
import org.cidte.sii.hibernate.ConectorDatosMedicos;
import org.cidte.sii.hibernate.ConectorDatosPersonales;
import org.cidte.sii.hibernate.ConectorUsuario;
import org.cidte.sii.negocio.ImageManager;
import org.cidte.sii.negocio.LogInManager;
import org.cidte.sii.presentacion.SII_CIDTE;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class DatosUsuarioController implements Initializable {

    @FXML
    private Button btnActualizar;
    @FXML
    private Label lbRFC;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbAPaterno;
    @FXML
    private Label lbAMaterno;
    @FXML
    private Label lbFN;
    @FXML
    private Label lbSexo;
    @FXML
    private Label lbEmail;
    @FXML
    private Label lbTCasa;
    @FXML
    private Label lbTCell;
    @FXML
    private Label lbCalle;
    @FXML
    private Label lbColonia;
    @FXML
    private Label lbCiudad;
    @FXML
    private Label lbEstado;
    @FXML
    private Label lbCP;
    @FXML
    private Label lbNE;
    @FXML
    private Label lbNI;
    @FXML
    private Label lbTOficina;
    @FXML
    private Label lbFoto;
    @FXML
    private TextField tfRFC;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfAPaterno;
    @FXML
    private TextField tfAMaterno;
    @FXML
    private DatePicker dpFN;
    @FXML
    private ComboBox<String> cbSexo;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfTCasa;
    @FXML
    private TextField tfTCell;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfCP;
    @FXML
    private TextField tfNExt;
    @FXML
    private TextField tfNInt;
    @FXML
    private TextField tfTOficina;
    @FXML
    private ImageView ivFoto;
    @FXML
    private Button btnElegir;
    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private Label lbDatosPersonales;
    @FXML
    private Label lbReligion;
    @FXML
    private Label lbEstadoCivil;
    @FXML
    private Label lbTAltura;
    @FXML
    private Label lbTCintura;
    @FXML
    private Label lbTCuello;
    @FXML
    private Label lbTCalzado;
    @FXML
    private TextField tfReligion;
    @FXML
    private TextField tfTAltura;
    @FXML
    private TextField tfTCintura;
    @FXML
    private TextField tfTCuello;
    @FXML
    private TextField tfTCalzado;
    @FXML
    private ComboBox<String> cbEstadoCivil;
    @FXML
    private Label lbDatosLegales;
    @FXML
    private Label lbNacionalidad;
    @FXML
    private Label lbVisa;
    @FXML
    private Label lbPasaporte;
    @FXML
    private TextField tfNacionalidad;
    @FXML
    private TextField tfVisa;
    @FXML
    private TextField tfIDPass;
    @FXML
    private Label lbVijenciaPasaporte;
    @FXML
    private Label lbLicenciaManejo;
    @FXML
    private TextField tfLManejo;
    @FXML
    private TextField tfVLManejo;
    @FXML
    private TextField tfVP;
    @FXML
    private Label lbVijenciaLicenciaManejo;
    @FXML
    private Label lbDatosMedicos;
    @FXML
    private Label lbNSS;
    @FXML
    private Label lbSangre;
    @FXML
    private Label lbPeso;
    @FXML
    private Label lbLentes;
    @FXML
    private Label lbAlergias;
    @FXML
    private Label lbDiscapacidades;
    @FXML
    private Label lbTratamientos;
    @FXML
    private ComboBox<String> cbSangre;
    @FXML
    private ComboBox<String> cbLentes;
    @FXML
    private TextField tfPeso;
    @FXML
    private TextField tfNSS;
    @FXML
    private TextField tfAlergias;
    @FXML
    private TextField tfTratamientos;
    @FXML
    private TextField tfDiscapacidades;
    @FXML
    private Label lbEntidadMedica;
    @FXML
    private TextField tfEntidadMedica;
    @FXML
    private Label lbDatosBasicos;
    @FXML
    private Label lbNombreUsuario;
    @FXML
    private Label lbContrasena;
    @FXML
    private Label lbCURP;
    @FXML
    private TextField tfNombreUsuario;
    @FXML
    private PasswordField pfContrasena;
    @FXML
    private TextField tfCURP;
    @FXML
    private DatePicker cdVLM;
    @FXML
    private DatePicker cdVP;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbDatosGenerales;

    private VentanaPrincipalController papa;
    private Usuario usuario;
    private DatosGenerales generales;
    private DatosLegales legales;
    private DatosMedicos medicos;
    private DatosPersonales personales;

    private ConectorUsuario cusuario;
    private ConectorDatosGenerales cdgenerales;
    private ConectorDatosLegales cdlegales;
    private ConectorDatosMedicos cdmedicos;
    private ConectorDatosPersonales cdpersonales;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fillComboBoxes();
        setLabels();
    }

    public void initData(Usuario usuario, VentanaPrincipalController papa) {
        this.usuario = usuario;
        this.papa = papa;
        cusuario = new ConectorUsuario();
        cdgenerales = new ConectorDatosGenerales();
        cdlegales = new ConectorDatosLegales();
        cdmedicos = new ConectorDatosMedicos();
        cdpersonales = new ConectorDatosPersonales();

        generales = cdgenerales.get(usuario.getCurp());
        legales = cdlegales.get(usuario.getCurp());
        medicos = cdmedicos.get(usuario.getCurp());
        personales = cdpersonales.get(usuario.getCurp());

        fillFields();

    }

    private void fillFields() {
        //llenar los datos basicos
        tfNombreUsuario.setText(usuario.getUsername());
        tfCURP.setText(usuario.getCurp());

        //lenar los datos generales
        tfRFC.setText(generales.getRfc());
        tfNombre.setText(generales.getNombre());
        tfAPaterno.setText(generales.getApaterno());
        tfAMaterno.setText(generales.getAmaterno());
        tfCalle.setText(generales.getCalle());
        tfColonia.setText(generales.getColonia());
        tfCiudad.setText(generales.getCiudad());
        cbEstado.setValue(generales.getEstado());
        tfCP.setText(generales.getCodigopostal() + "");
        tfNExt.setText(generales.getNumero_ext() + "");
        tfNInt.setText(generales.getNumero_int() + "");
        tfTCasa.setText(generales.getTelefono_casa() + "");
        tfTCell.setText(generales.getTelefono_cell() + "");
        tfTOficina.setText(generales.getTelefono_oficina() + "");
//        dpFN.getEditor().setText(generales.getFecha_nacimiento().toString());
        cbSexo.setValue(generales.getSexo());
        tfEmail.setText(generales.getEmail());

        //set foto
        ivFoto.setImage(setFoto());

        //datos personales
        tfReligion.setText(personales.getReligion());
        cbEstadoCivil.setValue(personales.getEstado_civil());
        tfTAltura.setText(personales.getTalla_altura() + "");
        tfTCintura.setText(personales.getTalla_cintura() + "");
        tfTCuello.setText(personales.getTalla_cuello() + "");
        tfTCalzado.setText(personales.getTalla_calzado() + "");

        //datos legales
        tfNacionalidad.setText(legales.getNacionalidad());
        tfVisa.setText(legales.getVisa_americana());
        tfIDPass.setText(legales.getId_pasaporte());
//        tfVP.setText(legales.getVijencia_pasaporte().toString());
        tfLManejo.setText(legales.getLicencia_manejo());
//        tfVLManejo.setText(legales.getVijencia_licencia().toString());

        //datos medicos
        tfNSS.setText(medicos.getNumero_ss());
        cbSangre.setValue(medicos.getTipo_sangre());
        tfPeso.setText(medicos.getPeso() + "");
        cbLentes.setValue(medicos.getLentes());
        tfAlergias.setText(medicos.getAlergias());
        tfDiscapacidades.setText(medicos.getDiscapacidades());
        tfTratamientos.setText(medicos.getTratamiento_vida());
        tfEntidadMedica.setText(medicos.getEntidad_medica());
    }

    private void fillComboBoxes() {
        //llenar el combobox de sexos
        ObservableList<String> sexos
                = FXCollections.observableArrayList(
                        "Hombre",
                        "Mujer"
                );
        cbSexo.setItems(sexos);

        //llenar el combobox de estados
        ObservableList<String> estados
                = FXCollections.observableArrayList(
                        "Aguascalientes",
                        "Zacatecas"
                );
        cbEstado.setItems(estados);
        cbEstado.setValue(estados.get(0));

        //llenar el combobox de estados civiles
        ObservableList<String> estadosciviles
                = FXCollections.observableArrayList(
                        "Casado/a",
                        "Divorciado/a",
                        "Soltero/a",
                        "Viudo/a",
                        "Union Libre"
                );
        cbEstadoCivil.setItems(estadosciviles);

        //llenar el combobox de tipos de sangre
        ObservableList<String> sangres
                = FXCollections.observableArrayList(
                        "O+",
                        "A+",
                        "B+",
                        "AB+",
                        "O-",
                        "A-",
                        "B-",
                        "AB-"
                );
        cbSangre.setItems(sangres);

        //llenar el combobox de lentes
        ObservableList<String> sino
                = FXCollections.observableArrayList(
                        "Si",
                        "No"
                );
        cbLentes.setItems(sino);
        cbLentes.setValue(sino.get(1));
    }

    /**
     * Guardar o "Acatualizar" los datos del usuario
     *
     * @param event
     */
    @FXML
    private void handleActualizar(ActionEvent event) {

        //guardar los datos basicos
        usuario.setUsername(tfNombreUsuario.getText());
        String pass = pfContrasena.getText();
        if (!pass.isEmpty()) {
            usuario.setPassword(new LogInManager().encriptarContrasena(pass));
        }
        cusuario.update(usuario);

        //lenar los datos generales
        generales.setRfc(tfRFC.getText());
        generales.setNombre(tfNombre.getText());
        generales.setApaterno(tfAPaterno.getText());
        generales.setAmaterno(tfAMaterno.getText());
        generales.setCalle(tfCalle.getText());
        generales.setColonia(tfColonia.getText());
        generales.setCiudad(tfCiudad.getText());
        generales.setEstado(cbEstado.getValue());

        generales.setCodigopostal(Integer.parseInt(tfCP.getText()));
        generales.setNumero_ext(Integer.parseInt(tfNExt.getText()));
        generales.setNumero_int(Integer.parseInt(tfNInt.getText()));
        generales.setTelefono_casa(Integer.parseInt(tfTCasa.getText()));
        generales.setTelefono_cell(Integer.parseInt(tfTCell.getText()));
        generales.setTelefono_oficina(Integer.parseInt(tfTOficina.getText()));

//        dpFN.getEditor().setText(generales.getFecha_nacimiento().toString());
        generales.setSexo(cbSexo.getValue());
        generales.setEmail(tfEmail.getText());
        cdgenerales.update(generales);

        //datos personales
        personales.setReligion(tfReligion.getText());
        personales.setEstado_civil(cbEstadoCivil.getValue());
        personales.setTalla_altura(Integer.parseInt(tfTAltura.getText()));
        personales.setTalla_cintura(Integer.parseInt(tfTCintura.getText()));
        personales.setTalla_cuello(Integer.parseInt(tfTCuello.getText()));
        personales.setTalla_calzado(Integer.parseInt(tfTCalzado.getText()));
        cdpersonales.update(personales);

        //datos legales
        legales.setNacionalidad(tfNacionalidad.getText());
        legales.setVisa_americana(tfVisa.getText());
        legales.setId_pasaporte(tfIDPass.getText());
//        legales.setVijencia_pasaporte(new Date(tfVP.getText()));
        legales.setLicencia_manejo(tfLManejo.getText());
//        legales.setVijencia_licencia(new Date(tfVLManejo.getText()));
        cdlegales.update(legales);

        //datos medicos
        medicos.setNumero_ss(tfNSS.getText());
        medicos.setTipo_sangre(cbSangre.getValue());
        medicos.setPeso(Integer.parseInt(tfPeso.getText()));
        medicos.setLentes(cbLentes.getValue());
        medicos.setAlergias(tfAlergias.getText());
        medicos.setDiscapacidades(tfDiscapacidades.getText());
        medicos.setTratamiento_vida(tfTratamientos.getText());
        medicos.setEntidad_medica(tfEntidadMedica.getText());
        cdmedicos.update(medicos);
    }

    @FXML
    private void handleElegir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        // Set extension filter
        List<String> l = new ArrayList();
        l.add("*.png");
        l.add("*.jpg");
        l.add("*.gif");
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("Image files (png, jpg, gif)", l);
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(((Node) (event.getSource())).getScene().getWindow());
        if (file != null) {
            try {
                byte[] data = ImageManager.readBytesFromFile(file.getCanonicalPath());
                generales.setFoto(data);
                ivFoto.setImage(ImageManager.byteArraytoFXImage(data));
                papa.ivUsuario.setImage(ImageManager.byteArraytoFXImage(data));

            } catch (IOException | java.lang.IllegalArgumentException e) {
                System.out.println("Error en cargar imagen " + e);
            }

        }
    }

    private Image setFoto() {
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

    private void setLabels() {
        ResourceBundle msg = SII_CIDTE.LOCALIZER.getMessages();

//        lbTitle.setText(msg.getString("personalizar_sitio"));
        //los titulos
        lbTitulo.setText(msg.getString("datos_de_mi_cuenta"));
        lbDatosGenerales.setText(msg.getString("datos_generales"));
        
        lbDatosBasicos.setText(msg.getString("datos_basicos"));
        lbDatosPersonales.setText(msg.getString("datos_personales"));
        lbDatosLegales.setText(msg.getString("datos_legales"));
        lbDatosMedicos.setText(msg.getString("datos_medicos"));

        //los botones
        btnActualizar.setText(msg.getString("actualizar_datos"));
        btnElegir.setText(msg.getString("elegir"));

        //los datos basicos
        lbNombreUsuario.setText(msg.getString("nombre_usuario"));
        lbContrasena.setText(msg.getString("contrasena"));
        lbCURP.setText(msg.getString("curp"));
//        
//        //los datos generales
        lbRFC.setText(msg.getString("rfc"));
        lbNombre.setText(msg.getString("nombre"));
        lbAPaterno.setText(msg.getString("a_paterno"));
        lbAMaterno.setText(msg.getString("a_materno"));
        lbFN.setText(msg.getString("fecha_nacimiento"));
        lbSexo.setText(msg.getString("sexo"));
        lbEmail.setText(msg.getString("email"));
        lbTCasa.setText(msg.getString("tel_casa"));
        lbTCell.setText(msg.getString("tel_cell"));

        lbCalle.setText(msg.getString("calle"));
        lbColonia.setText(msg.getString("colonia"));
        lbCiudad.setText(msg.getString("ciudad"));
        lbEstado.setText(msg.getString("estado"));
        lbCP.setText(msg.getString("cp"));
        lbNE.setText(msg.getString("numero_ext"));
        lbNI.setText(msg.getString("numero_int"));
        lbTOficina.setText(msg.getString("tel_oficina"));
        lbFoto.setText(msg.getString("fotografia"));

        //los datos personales
        lbReligion.setText(msg.getString("religion"));
        lbEstadoCivil.setText(msg.getString("estado_civil"));
        lbTAltura.setText(msg.getString("talla_altura"));
        lbTCintura.setText(msg.getString("talla_cintura"));
        lbTCuello.setText(msg.getString("talla_cuello"));
        lbTCalzado.setText(msg.getString("talla_calzado"));

        //datos legsales
        lbNacionalidad.setText(msg.getString("nacionalidad"));
        lbPasaporte.setText(msg.getString("id_pasaporte"));
        lbLicenciaManejo.setText(msg.getString("lic_manejo"));
        lbVisa.setText(msg.getString("visa_americana"));
        lbVijenciaPasaporte.setText(msg.getString("vigencia_pasaporte"));
        lbVijenciaLicenciaManejo.setText(msg.getString("vigencia_lic_manejo"));
//        
//        //los datos medicos
        lbNSS.setText(msg.getString("numero_ss"));
        lbSangre.setText(msg.getString("tipo_sangre"));
        lbPeso.setText(msg.getString("peso"));
        lbLentes.setText(msg.getString("lentes"));
        lbEntidadMedica.setText(msg.getString("entidad_medica"));
        lbAlergias.setText(msg.getString("alergias"));
        lbDiscapacidades.setText(msg.getString("discapacidades"));
        lbTratamientos.setText(msg.getString("tratamientos"));
    }
}
