package com.sras.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sras.model.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class ReportGenerator {

    public static void generateStudentPDFReport(Student student, String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(new Paragraph("Student Result Report"));
            document.add(new Paragraph("----------------------------------"));
            document.add(new Paragraph("Student ID: " + student.getStudentId()));
            document.add(new Paragraph("Name: " + student.getName()));
            document.add(new Paragraph("Department: " + student.getDepartment()));
            document.add(new Paragraph("Semester: " + student.getSemester()));
            document.add(new Paragraph("\n*** This is an auto-generated report ***"));
            document.close();
            System.out.println("PDF generated at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateClassExcelReport(List<Student> students, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Class Report");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Student ID");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Department");
            header.createCell(3).setCellValue("Semester");

            int rowNum = 1;
            for (Student s : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(s.getStudentId());
                row.createCell(1).setCellValue(s.getName());
                row.createCell(2).setCellValue(s.getDepartment());
                row.createCell(3).setCellValue(s.getSemester());
            }

            try (FileOutputStream out = new FileOutputStream(filePath)) {
                workbook.write(out);
            }
            System.out.println("Excel generated at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
