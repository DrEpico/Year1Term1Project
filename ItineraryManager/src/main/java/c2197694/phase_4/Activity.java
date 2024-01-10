/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_4;

import java.util.ArrayList;
import java.util.List;

/**
 * The "Activity" class represents an activity object within the itinerary
 * manager program. Each activity has various attributes such as base cost,
 * title, description, location, and duration. Activities may have associated
 * add-on(s).
 *
 * This class provides methods for processing information related to activity
 * objects and displaying them.
 *
 * @author c2197694
 */
public class Activity {

    private int baseCost;
    private String title;
    private final int number;
    private String description;
    private String location;
    private String dateTime;
    private int duration;
    private final boolean requiresInsurance;
    private final String activityCode;

    /**
     * "addOns" array list manages addOns that was added within the itinerary
     * creation by users, while "displayAddOn" is responsible for displaying
     * what addOns are available for users on command line and no addition or
     * deletion happens to "displayAddOns" due to user actions.
     */
    private final List<AddOn> addOns;
    private final List<AddOn> displayAddOns;

    /**
     * Preset add-ons associated with activities.
     *
     * Activity add-ons are instantiated in the Activity class.
     */
    private final AddOn insurance = new AddOn("Insurance", 1300, "activity", "INS");
    private final AddOn travel = new AddOn("Travel", 1000, "activity", "TRA");
    private final AddOn photography = new AddOn("Photography", 1400, "activity", "PHT");
    private final AddOn extension = new AddOn("1 hour extension", 1200, "activity", "EXN");
    private final AddOn privateGuide = new AddOn("Private Guide", 1800, "activity", "PGU");

    /**
     * Parameterized constructor for creating an activity.
     *
     * @param baseCost The base cost of the activity
     * @param title The title/name of the activity.
     * @param number The activity number/index.
     * @param description The description of the activity.
     * @param location The location of the activity.
     * @param dateTime The date and time of the activity.
     * @param duration The duration of the activity in hours.
     * @param requiresInsurance A Boolean indicating whether insurance is
     * required for the activity
     * @param code The code of an activity.
     */
    public Activity(int baseCost, String title, int number, String description,
            String location, String dateTime, int duration, boolean requiresInsurance, String code) {
        this.baseCost = baseCost;
        this.title = title;
        this.number = number;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.duration = duration;
        this.requiresInsurance = requiresInsurance;
        this.activityCode = code;
        this.addOns = new ArrayList<>();
        this.displayAddOns = new ArrayList<>();
    }

    /**
     * Copy activity constructor copies the original activity instance of an
     * activity.
     *
     * This is the core solution to the problem where multiple itineraries
     * shared the same activity instance and subsequently shared add-ons array
     * list causing issues in itinerary cost calculation
     *
     * Currently only 3 display add-ons are added to ALL activities.
     *
     * @param activity The activity instance to be copied.
     */
    public Activity(Activity activity) {//copy constructor
        this.baseCost = activity.baseCost;
        this.title = activity.title;
        this.number = activity.number;
        this.description = activity.description;
        this.location = activity.location;
        this.dateTime = activity.dateTime;
        this.duration = activity.duration;
        this.requiresInsurance = activity.requiresInsurance;
        this.activityCode = activity.activityCode;
        this.addOns = new ArrayList<>();
        this.displayAddOns = new ArrayList<>();
        this.addDisplayAddOn(insurance);
        this.addDisplayAddOn(travel);
        this.addDisplayAddOn(photography);
        this.addDisplayAddOn(extension);
        this.addDisplayAddOn(privateGuide);
    }

    /**
     * Adds an add-on to the list of activity add-ons with duplication
     * prevention logic.
     *
     * @param addOn The activity add-on to be added.
     */
    protected void addAddOn(AddOn addOn) {//adds itinerary addons
        if (!addOns.contains(addOn)) {
            addOns.add(addOn);
        } else {
            System.out.println(addOn.getName() + " already exists in list of itinerary add-ons!");
        }
    }

    /**
     * Removes an activity add-on from the list of associated add-ons for the
     * activity if the activity instance exists in the list.
     *
     * @param addOn The add-on instance to be removed.
     */
    protected void removeAddOn(AddOn addOn) {
        if (addOns.contains(addOn)) {
            addOns.remove(addOn);
        }
    }

    /**
     * Adds an add-on to the list of display add-ons for the activity if that
     * add-on doesn't already exists in the catalogue list.
     *
     * @param addOn The add-on to be added for display purposes.
     */
    protected final void addDisplayAddOn(AddOn addOn) {
        if (!addOns.contains(addOn)) {
            displayAddOns.add(addOn);
        }
    }

    /**
     * Removes an add-on from the list of display add-ons for the activity.
     *
     * @param addOn The add-on to be removed from displayAddOns list.
     */
    protected void removeDisplayAddOn(AddOn addOn) {
        displayAddOns.remove(addOn);
    }

    /**
     * Clears the list of add-ons associated with the activity.
     *
     * This was a primary solution to the issue caused by shared activity
     * instances. Currently not in use.
     */
    protected void clearActivity() {
        addOns.clear();
    }

    /**
     * Checks if the activity instance has an associated insurance add-on.
     *
     * This method iterates through the list of add-ons associated with an
     * activity instance to determine if there is an add-on with the name
     * "Insurance."
     *
     * @return {@code true} if the activity instance in the itinerary has an
     * insurance add-on, {@code false} otherwise.
     */
    protected boolean containsInsurance() {
        for (AddOn addOn : addOns) {
            if (addOn.getName().equalsIgnoreCase("Insurance")) {
                return true; // Found an insurance add-on
            }
        }
        return false; // Insurance add-on not found
    }

    /**
     * Calculates the total cost for the activity, including its base cost and
     * associated add-ons.
     *
     * The total cost is computed by summing the base cost of the activity and
     * the costs of each associated add-on.
     *
     * @return The total cost of one activity instance with its associated
     * add-ons.
     */
    protected int calculateCostWithAddOn() {
        int activityCost = this.getBaseCost();

        for (AddOn addOn : addOns) {
            activityCost += addOn.getCost();
        }
        return activityCost;
    }

    /**
     * Displays a list of available add-ons for user on command line.
     *
     * This method is useful for presenting the details of the add-ons in a
     * user-friendly format. The information displayed includes the name (title)
     * and cost of each add-on, separated by a line.
     */
    protected void displayAddOns() {
        System.out.println("\n--------------------------------------");
        for (AddOn addOn : displayAddOns) {
            System.out.println("Title: " + addOn.getName());
            System.out.println("Activity Number: £" + addOn.getCost() / 100);
            System.out.println("--------------------------------------");
        }
    }

    /**
     * Checks if the activity has an add-on with the specified name.
     *
     * This method iterates through the list of display add-ons associated with
     * the activity to determine if there is an add-on with a name that matches
     * the specified name (case-insensitive).
     *
     * @param addOnName The name of the add-on to check for.
     * @return {@code true} if the activity has an add-on with the specified
     * name, {@code false} otherwise.
     */
    protected boolean hasAddOnByName(String addOnName) {
        for (AddOn addOn : displayAddOns) {
            if (addOn.getName().equalsIgnoreCase(addOnName)) {
                return true;
            }
        }
        return false;
    }

    protected boolean hasAddOnByCode(String addOnCode) {
        for (AddOn addOn : displayAddOns) {
            //String code = addOn.getCode();debug
            if (addOn.getCode().equalsIgnoreCase(addOnCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves an add-on associated with the activity based on its name.
     *
     * This method iterates through the list of display add-ons to find the
     * add-on with a name that matches the specified name (case-insensitive).
     *
     * @param addOnName The name of the add-on to retrieve.
     * @return The add-on with the specified name, or {@code null} if not found.
     */
    protected AddOn getAddOnByName(String addOnName) {
        for (AddOn addOn : displayAddOns) {
            if (addOn.getName().equalsIgnoreCase(addOnName)) {
                return addOn;
            }
        }
        return null;
    }

    protected AddOn getAddOnByCode(String addOnCode) {
        for (AddOn addOn : displayAddOns) {
            if (addOn.getCode().equalsIgnoreCase(addOnCode)) {
                return addOn;
            }
        }
        return null; // Return null if the add-on with the specified name is not found
    }

    /**
     * Gets the list of add-ons associated with the specific instance of an
     * activity.
     *
     * @return The list of add-ons.
     */
    protected List<AddOn> getAddOns() {
        return addOns;
    }

    /**
     * Generic getter methods.
     *
     * @return Fields of this class.
     */
    protected int getBaseCost() {
        return baseCost;
    }

    protected String getTitle() {
        return title;
    }

    protected int getNumber() {
        return number;
    }

    protected String getDescription() {
        return description;
    }

    protected String getLocation() {
        return location;
    }

    protected String getDateTime() {
        return dateTime;
    }

    protected int getDuration() {
        return duration;
    }

    protected boolean requiresInsurance() {
        return requiresInsurance;
    }

    protected AddOn getInsuranceAddOn() {
        return insurance;
    }

    protected AddOn getTravelAddOn() {
        return travel;
    }

    protected AddOn getPhotographyAddOn() {
        return photography;
    }

    protected AddOn getExtension() {
        return extension;
    }

    protected AddOn getPrivatGuide() {
        return privateGuide;
    }

    protected String getCode() {
        return activityCode;
    }

    /**
     * Generic setter methods.
     *
     * @param baseCost & other class fields
     */
    protected void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setLocation(String location) {
        this.location = location;
    }

    protected void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    protected void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * This method was used for printing all the addOns inside the array list of
     * a specific instance of one activity.
     *
     * Currently not in use and is saved for later use.
     */
    protected void printAddOns() {
        for (AddOn addOn : addOns) {
            System.out.println("addon name: " + addOn.getName());
        }
    }

}
