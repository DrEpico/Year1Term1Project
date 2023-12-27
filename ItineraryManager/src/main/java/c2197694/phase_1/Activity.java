/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_1;

import java.util.ArrayList;
import java.util.List;

/**
 * The "Activity" class represents an activity object within the itinerary manager program.
 * Each activity has various attributes such as base cost, title, description, location, and duration.
 * Activities may have associated add-on(s).
 * 
 * This class provides methods for processing information related to activity objects 
 * and displaying them.
 * 
 * @author c2197694
 */
public class Activity{
    private int baseCost;
    private String title;    
    private int number;
    private String description;
    private String location;
    private String dateTime;
    private int duration;
    private boolean requiresInsurance;
    private List<AddOn> addOns;
    
    /**
     * Preset add-ons associated with activities.
     * 
     * Activity add-ons are instantiated in the Activity class.
     */
    public AddOn insurance = new AddOn("Insurance", 2300, "activity");
    public AddOn travel = new AddOn("Travel", 2000, "activity");
    public AddOn photography = new AddOn("Photography", 2500, "activity");
    
    /**
     * Parameterized constructor for creating an activity.
     * 
     * @param baseCost          The base cost of the activity
     * @param title             The title/name of the activity.
     * @param number            The activity number/index.
     * @param description       The description of the activity.
     * @param location          The location of the activity.
     * @param dateTime          The date and time of the activity.
     * @param duration          The duration of the activity in hours.
     * @param requiresInsurance A Boolean indicating whether insurance is required for the activity
     */
    public Activity(int baseCost, String title, int number, String description, String location, 
            String dateTime, int duration, boolean requiresInsurance) {
        this.baseCost = baseCost;
        this.title = title;
        this.number = number;
        this.description = description; 
        this.location = location;
        this.dateTime = dateTime;
        this.duration = duration;
        this.requiresInsurance = requiresInsurance;
        this.addOns = new ArrayList<>();
    }
    
    /**
     * Copy activity constructor copies the original activity instance of an activity. 
     * 
     * This is the core solution to the problem where multiple itineraries shared the same activity
     * instance and subsequently shared add-ons array list causing issues in itinerary cost calculation
     * 
     * Currently only 3 display add-ons are added to ALL activities. 
     * 
     * @param activity  The activity instance to be copied.
     */
    public Activity (Activity activity){
        this.baseCost = activity.baseCost;
        this.title = activity.title;
        this.number = activity.number;
        this.description = activity.description; 
        this.location = activity.location;
        this.dateTime = activity.dateTime;
        this.duration = activity.duration;
        this.requiresInsurance = activity.requiresInsurance;
        this.addOns = new ArrayList<>();
    }
    
    /**
     * Adds an add-on to the list of associated add-ons for the activity.
     * 
     * @param addOn The add-on instance to be added.
     */
    public void addAddOn(AddOn addOn) {
        addOns.add(addOn);
    }
    
    /**
     * Removes an add-on from the list of associated add-ons for the activity.
     * 
     * @param addOn The add-on instance to be removed.
     */
    public void removeAddOn(AddOn addOn) {
        addOns.remove(addOn);
    }
    
    /**
     * Clears the list of add-ons associated with the activity.
     * 
     * This was a primary solution to the issue caused by shared activity instances.
     * Currently not in use.
     */
    public void clearActivity(){
        addOns.clear();
    }
    
    /**
     * Checks if the activity instance has an associated insurance add-on.
     * 
     * This method iterates through the list of add-ons associated with an activity
     * instance to determine if there is an add-on with the name "Insurance."
     * 
     * @return {@code true} if the activity instance in the itinerary has an insurance 
     * add-on, {@code false} otherwise.
     */
    public boolean containsInsurance() {//currently not used
        for (AddOn addOn : addOns) {
            if (addOn.getName().equalsIgnoreCase("Insurance")) {
                return true; // Found an insurance add-on
            }
        }
        return false; // Insurance add-on not found
    }
    
    /**
     * Calculates the total cost for the activity, including its base cost and associated add-ons.
     * 
     * The total cost is computed by summing the base cost of the activity and the costs of each 
     * associated add-on.
     * 
     * @return The total cost of one activity instance with its associated add-ons.
     */
    public int calculateCostWithAddOn(){
        int activityCost = this.getBaseCost();

        for (AddOn addOn : addOns) {
            activityCost+= addOn.getCost();
        }
//        clearActivity();
        return activityCost;
    }
    
    /**
     * Gets the list of add-ons associated with the specific instance of an activity.
     * 
     * @return The list of add-ons.
     */
    public List<AddOn> getAddOns() {
        return addOns;
    }
    
    /**
     * Generic getter methods.
     * 
     * @return Fields of this class.
     */
    
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
    
    /**
     * Generic setter methods.
     * 
     * @param baseCost & other class fields
     */
    
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
