/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_1;

/**
 *
 * @author c2197694
 */
public class AddOn {
    private String name;
    private int cost;
    private String type;
    
    public AddOn(String name, int cost, String type){
        this.name = name;
        this.cost = cost;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }
    
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
