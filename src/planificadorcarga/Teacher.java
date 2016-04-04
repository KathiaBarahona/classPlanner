
package planificadorcarga;

import java.util.ArrayList;


/**
 *
 * @author Agile 2016
 */


public class Teacher {
    private String name;
    private String last_name;
    private ArrayList<String> hours = new ArrayList();
    private ArrayList<String> classes = new ArrayList();
    public Teacher(String name,String last_name, ArrayList<String> hours,ArrayList<String> classes){
        this.name = name;
        this.last_name = last_name;
        this.hours = hours;
        this.classes = classes;
    }
    public Teacher(){
        
    }
    /**
     * 
     * @Getters 
     */
    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public ArrayList<String> getHours() {
        return hours;
    }

    public ArrayList<String> getClasses() {
        return classes;
    }
    /**
     * 
     * @Setters 
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void addHours(String hour) {
        this.hours.add(hour);
    }
    
    

    public void addClass(String classes) {
        this.classes.add(classes);
    }
    public void setClasses(ArrayList<String> classes){
        this.classes = classes;
    }
    
    public void setHours(ArrayList<String>hours){
        this.hours = hours;
    }
    public boolean givesClass(String class1){
        for (int i = 0; i < classes.size(); i++) {
            if(classes.get(i).equals(class1))
                return true;
        }
        return false;
    }
    public void clone(Teacher t){
        this.name = t.name;
        this.last_name = t.last_name;
        this.hours = t.hours;
        this.classes = t.classes;
    }
    @Override
    public String toString(){
        return name + " " + last_name;
    }
    
   

    
}
