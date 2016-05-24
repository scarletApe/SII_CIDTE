/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidte.sii.negocio;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author juanmartinez
 */
public class Localizer {

	private String language;
	private Locale currentLocale;
	private ResourceBundle messages;

	public Localizer(String lang) {
		language = lang;
		currentLocale = new Locale(language);
		messages = ResourceBundle.getBundle("GUIText", currentLocale);
	}

	public void changeLocale(String lang) {
		language = lang;
		currentLocale = new Locale(lang);
		messages = ResourceBundle.getBundle("GUIText", currentLocale);
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}

	public ResourceBundle getMessages() {
		return messages;
	}

	public void setMessages(ResourceBundle messages) {
		this.messages = messages;
	}
}
