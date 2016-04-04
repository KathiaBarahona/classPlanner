package planificadorcarga;

import java.util.ArrayList;

/**
 *
 * @author Agile 2016
 */
public class Class_UNITEC {

    private String name;
    private ArrayList<Student> students = new ArrayList();
    private Teacher teacher = new Teacher();
    private String hour;
    private String classRoom;
    

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public Class_UNITEC(){
        
    }
    public void clone(Class_UNITEC c){
        this.name = c.getName();
        this.classRoom = c.getClassRoom();
        this.hour = c.getHour();
        this.students = c.getStudents();
        this.teacher = c.getTeacher();
    }
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public String getHour() {
        return hour;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isAvailable(String class1) {
        return this.name.equals(class1);
    }
    public String toString(){

        return name;
    }
    
}
