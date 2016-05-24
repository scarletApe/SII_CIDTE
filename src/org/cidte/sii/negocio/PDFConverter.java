//<editor-fold defaultstate="collapsed" desc="licence">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//</editor-fold>
package org.cidte.sii.negocio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author juanmartinez
 */
public class PDFConverter {

	public byte[] pdfToArray(String archivo) throws FileNotFoundException, IOException {

		File file = new File(archivo);

		FileInputStream fis = new FileInputStream(file);
		// System.out.println(file.exists() + "!!");

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];

		for (int readNum; (readNum = fis.read(buf)) != -1;) {
			bos.write(buf, 0, readNum); // no doubt here is 0
			// Writes len bytes from the specified byte array starting at offset
			// off to this byte array output stream.
			// System.out.println("read " + readNum + " bytes,");
		}

		byte[] bytes = bos.toByteArray();
		
		fis.close();

		return bytes;
	}

	public void arrayToPDF(byte[] bytes, String archivo) throws FileNotFoundException, IOException {
		// below is the different part
		File someFile = new File(archivo);
		FileOutputStream fos = new FileOutputStream(someFile);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
}
