/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author c2197694
 */
public class Activity{
    //attributes
    private int baseCost;
    private String title;    
    private int number;
    private String description;
    private String location;
    private String dateTime;
    private int duration;
    private boolean requiresInsurance;
    private List<AddOn> addOns;
    public Activity paperBridge, assaultCourse, cookery, hiking;
    public AddOn insurance = new AddOn("Insurance", 2300, "activity");
    
    // Parameterized constructor
    public Activity(int baseCost, String title, int number, String description, String location, String dateTime, int duration, boolean requiresInsurance) {
        this.baseCost = baseCost;
        this.title = title;
        this.number = number;
        this.description = description; // why this.? -> a copy of description variable for each instance
        this.location = location;
        this.dateTime = dateTime;
        this.duration = duration;
        this.requiresInsurance = requiresInsurance;
        this.addOns = new ArrayList<>();
    }
    
    public Activity (Activity activity){//copy constructor
        this.baseCost = activity.baseCost;
        this.title = activity.title;
        this.number = activity.number;
        this.description = activity.description; 
        this.location = activity.location;
        this.dateTime = activity.dateTime;
        this.duration = activity.duration;
        this.requiresInsurance = activity.requiresInsurance;
        this.addOns = new ArrayList<>();
//        this.paperBridge = new Activity(1000, "Building a bridge from paper", 1,
//                "Let's build a bridge from paper!", "Teesside University", "16th Dec 15:00", 2, false);
//        this.assaultCourse = new Activity(10000, "SAS-style assault courses", 2,
//                "Become stronks", "Saltburn", "15th Dec 7:00", 15, true);
//        this.cookery = new Activity(1700, "Cookery class", 3,
//                "Let bro cook", "Teesside University", "9th Dec 14:00", 3, false);
//        this.hiking = new Activity(5900, "Hiking and nature walks", 4, 
//                "Hiking time!", "North York Moors Park", "8th Dec 14:00", 6, true);
        
    }
    
    public void addAddOn(AddOn addOn) {
        addOns.add(addOn);
    }
    
    public void clearActivity(){//clear lists in activity class (addOns array list) which should stop adding addons to shared  activity objects causing issues with cost calculation for different customers
        addOns.clear();
    }
    
    public boolean containsInsurance() {//currently not used
        for (AddOn addOn: addOns) {
            if (addOn.getName().equalsIgnoreCase("Insurance")) {
                return true; // Found an insurance add-on
            }
        }
        return false; // Insurance add-on not found
    }
    
    public int calculateCostWithAddOn(){/*this method calculates the cost for the activity + its addons and clears the activity list as this method is called in the final operations of a*/
        int activityCost = this.getBaseCost();

        for (AddOn addOn : addOns) {
            activityCost+= addOn.getCost();
        }
        clearActivity();
        return activityCost;
    }
    
    public List<AddOn> getAddOns() {
        return addOns;
    }
    
    public int getBaseCost(){
        return baseCost;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getNumber(){
        return number;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getLocation() {
        return location;
    }
    
    public String getDateTime() {
        return dateTime;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public boolean requiresInsurance(){
        return requiresInsurance;
    }
    
    
     public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
   
    
}
