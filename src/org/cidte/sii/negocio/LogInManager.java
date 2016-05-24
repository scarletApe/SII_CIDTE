/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.negocio;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;
import org.cidte.sii.entidades.Organizacion;
import org.cidte.sii.entidades.Usuario;
import org.cidte.sii.hibernate.ConectorOrganizacion;
import org.cidte.sii.hibernate.ConectorUsuario;
import org.cidte.sii.presentacion.SII_CIDTE;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 *
 * @author juanmartinez
 */
public class LogInManager {

	private final ConectorUsuario con;
	private final ConectorOrganizacion conOrg;

	public LogInManager() {
		con = new ConectorUsuario();
		conOrg = new ConectorOrganizacion();
	}

	public Usuario logIn(String un, String pass) {
		String s = encriptarContrasena(pass);
		return con.get(un, s);
	}

	public Image getLogo() {
		ArrayList<Organizacion> all = conOrg.getAll();

		if (all == null || all.isEmpty()) {
			return new Image("/org/cidte/sii/imagenes/empty.png");
		} else {
			Organizacion org = all.get(0);
			if (org.getLogo() == null || org.getLogo().length < 1) {
				return new Image("/org/cidte/sii/imagenes/empty.png");
			}
			try {
				InputStream in = new ByteArrayInputStream(org.getLogo());
				BufferedImage bImageFromConvert = ImageIO.read(in);
				WritableImage image = SwingFXUtils.toFXImage(bImageFromConvert, null);
				return image;
			} catch (IOException e) {
				System.out.println("Error en covertir imagen " + e);
				return new Image("/org/cidte/sii/imagenes/empty.png");
			}
		}
	}

	public String getCaption() {
		ArrayList<Organizacion> all = conOrg.getAll();
		if (all == null || all.isEmpty()) {
			return SII_CIDTE.LOCALIZER.getMessages().getString("texto_vacio");
		}
		return all.get(0).getNombre();
	}

	public String encriptarContrasena(String s) {
		return DigestUtils.sha1Hex(s);
	}

	public boolean compararContrasena(String plain, String encripted) {
		String s = DigestUtils.sha1Hex(plain);

		return s.equalsIgnoreCase(encripted);
	}
}
