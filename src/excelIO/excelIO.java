package excelIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import planificadorcarga.*;

/**
 *
 * @author Agile 2016
 */
public class excelIO {

    private List cellData = new ArrayList();
    private File file = new File("");
    private XSSFWorkbook book;

    public excelIO() {

    }

    public excelIO(File fileName) {
        try {
            this.file = fileName;
            FileInputStream fileInputStream = new FileInputStream(fileName);
            this.book = new XSSFWorkbook(fileInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList();
        XSSFSheet sheet = book.getSheetAt(0);
        Iterator rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            XSSFRow row = (XSSFRow) rowIterator.next();
            Iterator cellIterator = row.cellIterator();
            Student student = new Student();
            int index = 0;
            while (cellIterator.hasNext()) {
                XSSFCell cell = (XSSFCell) cellIterator.next();
                switch (index) {
                    case 0:
                        student.setName(cell.toString());
                        break;
                    case 1:
                        student.setLast_name(cell.toString());
                        break;
                    case 2:
                        student.setCareer(cell.toString());
                        break;
                    default:
                        student.addClass(cell.toString());
                        break;
                }
                index++;
            }
            students.add(student);
        }
        return students;
    }

    public ArrayList<Teacher> getTeachers() {
        ArrayList<Teacher> teachers = new ArrayList();
        XSSFSheet sheet = book.getSheetAt(1);
        Iterator rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            XSSFRow row = (XSSFRow) rowIterator.next();
            Iterator cellIterator = row.cellIterator();
            Teacher teacher = new Teacher();
            int index = 0;
            while (cellIterator.hasNext()) {
                XSSFCell cell = (XSSFCell) cellIterator.next();
                switch (index) {
                    case 0:
                        teacher.setName(cell.toString());
                        break;
                    case 1:
                        teacher.setLast_name(cell.toString());
                        break;
                    default:
                        String temp = cell.toString();
                        Pattern r = Pattern.compile("^[0-9]{2}:[0-9]{2}$");
                        Matcher m = r.matcher(temp);
                        if (m.matches()) {
                            teacher.addHours(temp);
                        } else {
                            teacher.addClass(temp);
                        }
                }
                index++;
            }
            teachers.add(teacher);
        }

        return teachers;
    }

    public ArrayList<Class_UNITEC> getClasses() {
        ArrayList<Class_UNITEC> classes = new ArrayList();
        XSSFSheet sheet = book.getSheetAt(2);
        Iterator rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            XSSFRow row = (XSSFRow) rowIterator.next();
            Iterator cellIterator = row.cellIterator();
            Class_UNITEC class1 = new Class_UNITEC();
            int index = 0;
            while (cellIterator.hasNext()) {
                XSSFCell cell = (XSSFCell) cellIterator.next();
                class1.setName(cell.toString());
            }
            classes.add(class1);
        }

        return classes;

    }

    public void writeBook(ArrayList<Class_UNITEC> classes, ArrayList<Student> students, double[] averages) {
        XSSFWorkbook result = new XSSFWorkbook();
        XSSFSheet sheet = result.createSheet("Horarios");
        int rowNumber = 0;
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getTeacher() != null && classes.get(i).getStudents().size() != 0) {
                Row classNameRow = sheet.createRow(rowNumber);
                rowNumber++;
                Cell classTitle = classNameRow.createCell(0);
                classTitle.setCellValue("Clase");
                Cell classNameCell = classNameRow.createCell(1);
                classNameCell.setCellValue(classes.get(i).getName());
                Row teacherRow = sheet.createRow(rowNumber);
                rowNumber++;
                Cell teacherTitleCell = teacherRow.createCell(0);
                teacherTitleCell.setCellValue("Maestro ");
                Cell teacherNameCell = teacherRow.createCell(1);
                teacherNameCell.setCellValue(classes.get(i).getTeacher().getName() + " " + classes.get(i).getTeacher().getLast_name());
                Row roomRow = sheet.createRow(rowNumber);
                rowNumber++;
                Cell roomTitle = roomRow.createCell(0);
                roomTitle.setCellValue("Aula ");
                Cell roomNumberCell = roomRow.createCell(1);
                roomNumberCell.setCellValue(classes.get(i).getClassRoom());
                Row hourRow = sheet.createRow(rowNumber);
                rowNumber++;
                Cell hourTitle = hourRow.createCell(0);
                hourTitle.setCellValue("Hora");
                Cell hourCell = hourRow.createCell(1);
                hourCell.setCellValue(classes.get(i).getHour());
                Row studentRow = sheet.createRow(rowNumber);
                rowNumber++;
                Cell studentTitle = studentRow.createCell(0);
                studentTitle.setCellValue("Estudiantes");

                int cellCount = 1;
                for (int j = 0; j < classes.get(i).getStudents().size(); j++) {
                    Student S = classes.get(i).getStudents().get(j);
                    Cell studentNameCell = studentRow.createCell(cellCount);
                    cellCount++;
                    studentNameCell.setCellValue(S.getName() + " " + S.getLast_name());
                }
                sheet.createRow(rowNumber);
                sheet.createRow(rowNumber + 1);
                rowNumber += 2;
            }

        }
        XSSFSheet averageSheet = result.createSheet("Promedios");
        rowNumber = 0;
        Row headerRow = averageSheet.createRow(rowNumber);
        rowNumber++;
        Cell studentTitle = headerRow.createCell(0);
        studentTitle.setCellValue("Estudiante");
        Cell averageTitle = headerRow.createCell(1);
        averageTitle.setCellValue("Promedio");
        for (int i = 0; i < students.size(); i++) {
            Row row = averageSheet.createRow(rowNumber);
            rowNumber++;
            Cell studentNameCell = row.createCell(0);
            studentNameCell.setCellValue(students.get(i).getName() + " " + students.get(i).getLast_name());
            Cell averageCell = row.createCell(1);
            averageCell.setCellValue(averages[i]);
        }
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String directory = fc.getCurrentDirectory().toString() + "\\cargaAcademica.xlsx";
        int flag = fc.showOpenDialog(null);
        try {
            FileOutputStream out = new FileOutputStream(new File(directory));
            result.write(out);
            out.close();
            JOptionPane.showMessageDialog(null, "Se ha guardado la planificacion!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
