package planificadorcarga;

import java.util.ArrayList;

/**
 *
 * @author Agile 2016
 */
public class Student {

    private String name;
    private String last_name;
    private String career;
    private ArrayList<String> classes = new ArrayList();

    public Student(String name, String last_name, String career, ArrayList<String> classes) {
        this.name = name;
        this.last_name = last_name;
        this.career = career;
        this.classes = classes;
    }

    public Student() {

    }

    /**
     * Get the value of last_name
     *
     * @return the value of last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Set the value of last_name
     *
     * @param last_name new value of last_name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Get the value of career
     *
     * @return the value of career
     */
    public String getCareer() {
        return career;
    }

    /**
     * Set the value of career
     *
     * @param career new value of career
     */
    public void setCareer(String career) {
        this.career = career;
    }

    /**
     * Get the value of classes
     *
     * @return the value of classes
     */
    public ArrayList<String> getClasses() {
        return classes;
    }

    /**
     * Set the value of classes
     *
     * @param clases new value of classes
     */
    public void setClasses(ArrayList<String> classes) {
        this.classes = classes;
    }

    public void addClass(String class1) {
        this.classes.add(class1);
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
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
    
    public boolean hasClass(String class1){
        for (int i = 0; i < this.classes.size(); i++) {
            if(classes.get(i).equals(class1)){
                return true;
            }
            
        }
        return false;
    }
    public void clone(Student s){
        this.name = s.name;
        this.last_name = s.last_name;
        this.career = s.career;
        this.classes = s.classes;
    }
    @Override
    public String toString(){
        return name+ " " + last_name;
    }

}
