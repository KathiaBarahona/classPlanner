
package planificadorcarga;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Agile 2016
 */


public class buttonCell extends AbstractCellEditor
                         implements TableCellEditor,
                                    ActionListener {
    
    ArrayList<Class_UNITEC>classes =  new ArrayList();
    Student actualStudent = new Student();
    JButton button;
    JDialog dialog;


    public buttonCell(ArrayList<Class_UNITEC>classes) {
        this.classes = classes;
        button = new JButton("Ver Clases");
        button.addActionListener(this);
        button.setBorderPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String classesStudent ="";
        for(int i = 0 ; i < classes.size(); i++){
            for(int j = 0 ; j < classes.get(i).getStudents().size(); j++ ){
                Student s = classes.get(i).getStudents().get(j);
                if((s.getName() + " " + s.getLast_name()).equals(actualStudent.getName() + " " + actualStudent.getLast_name())){
                   classesStudent +=(i+1)+" - "+classes.get(i).getName()+" - "+classes.get(i).getHour()+"\n";
                   break;
                }
            }
           
        }
        JTextArea textArea =  new JTextArea(classesStudent.toString());
        textArea.setFont(new Font("Serif", Font.BOLD, 16));
        JScrollPane jScrollPane1 = new JScrollPane(textArea);
        jScrollPane1.setVisible(true);
        
        JFrame frame = new JFrame();
        frame.add(jScrollPane1);
        frame.setSize(500, 500);
        frame.setLocation(100, 100);
        frame.setVisible(true);
  

        
    }

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
 

    //Implement the one method defined by TableCellEditor.
    @Override
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        if(value instanceof Student)
            actualStudent.clone((Student) value);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Ver Clases";
    }
}
