/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_1;

/**
 * Represents an add-on that can be added to an activity or the itinerary as a whole for a price.
 * This class contains information about the name, cost, and type of the add-on (itinerary/activity).
 * 
 * @author c2197694
 */
public class AddOn {
    private String name;
    private int cost;
    private String type;
    
    /**
     * Constructs an AddOn with the specified name, cost, and type.
     * 
     * @param name The name of the add-on.
     * @param cost The cost associated with the add-on.
     * @param type The type or category of the add-on (itinerary/activity).
     */
    public AddOn(String name, int cost, String type){
        this.name = name;
        this.cost = cost;
        this.type = type;
    }
    
    /**
     * Generic getter methods.
     * 
     * @return Fields of this class.
     */
    
    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }
    
    /**
     * Generic setter methods.
     * 
     * @param name & other class fields
     */
    
    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}
