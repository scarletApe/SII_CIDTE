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
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.cidte.sii.entidades.Writable;

/**
 *
 * @author juanmartinez
 */
public class XLSWriter {

    public void writeXLS(ArrayList<Writable> list, String directorio, String nombre) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(nombre);

        for (int i = 0; i < list.size(); i++) {

            Row row = sheet.createRow(i);
            Writable writ = list.get(i);
            Object[] objArr;
            if (i == 0) {
                //el primero entonces los nombres de las columnas
                objArr = writ.getNames();
            } else {
                objArr = writ.getAsArray();
            }
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
//                cell.setCellStyle(CellStyle.ALIGN_FILL);
                if (obj instanceof Date) {
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }

        try {
            FileOutputStream out
                    = new FileOutputStream(new File(directorio + nombre + ".xls"));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            System.out.println("Error " + e);
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }
}
