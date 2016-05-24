//<editor-fold defaultstate="collapsed" desc="licence">
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//</editor-fold>
package org.cidte.sii.negocio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.cidte.sii.entidades.Writable;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author juanmartinez
 */
public class PDFWriter {

	public void writePDF(ArrayList<Writable> list, String directorio, String nombre, java.awt.Image image)
			throws DocumentException, FileNotFoundException, BadElementException, IOException {

		Document doc = new Document();
		PdfWriter docWriter;

		// special font sizes
		Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
		Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);

		// file path
		String path = directorio + nombre + ".pdf";
		docWriter = PdfWriter.getInstance(doc, new FileOutputStream(new File(path)));

		// document header attributes
		doc.addAuthor("sii");
		doc.addCreationDate();
		doc.addProducer();
		doc.addCreator("sii");
		doc.addTitle(nombre);
		doc.setPageSize(PageSize.LETTER);

		// open document
		doc.open();

		Image img = Image.getInstance(image, null);
		img.setAlignment(Element.ALIGN_LEFT);
		doc.add(img);

		// create a paragraph
		Paragraph paragraph = new Paragraph("iText ® is a library that allows you to create and "
				+ "manipulate PDF documents. It enables developers looking to enhance web and other "
				+ "applications with dynamic PDF document generation and/or manipulation.");

		// create PDF table with the given widths
		PdfPTable table = new PdfPTable(list.get(0).getNames().length);
		// set table width a percentage of the page width
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f); // Space before table
		table.setSpacingAfter(10f); // Space after table

		// insert column headings
		String[] headings = list.get(0).getNames();
		for (String heading : headings) {
			insertCell(table, heading, Element.ALIGN_CENTER, 1, bfBold12);
		}
		table.setHeaderRows(1);

		// insert the data
		for (int i = 0; i < list.size(); i++) {
			Writable w = list.get(i);
			Object[] arr = w.getAsArray();
			for (int j = 0; j < arr.length; j++) {
				// arr[j]
				insertCell(table, arr[j].toString(), Element.ALIGN_LEFT, 1, bf12);
			}
		}

		// insert an empty row
		// insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12);
		// add the PDF table to the paragraph
		paragraph.add(table);
		// add the paragraph to the document
		doc.add(paragraph);

		// close the document
		doc.close();

		// close the writer
		docWriter.close();

	}

	private void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {

		// create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		// set the cell alignment
		cell.setHorizontalAlignment(align);

		// some padding
		cell.setPaddingLeft(10);

		// set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		// in case there is no text and you wan to create an empty row
		if (text.trim().equalsIgnoreCase("")) {
			cell.setMinimumHeight(10f);
		}
		// add the call to the table
		table.addCell(cell);

	}
}
