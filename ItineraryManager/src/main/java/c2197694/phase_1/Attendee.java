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
public class Attendee {
    private String name;
    private String contactInfo;
    private List<Activity> activities = new ArrayList<>();
    private List<AddOn> addOns = new ArrayList<>();
    private List<Itinerary> itineraries;
    private int members;
    private boolean hasInsurance; //does the attendee have a personal insurance that covers for the activity that require insurace 
    
    public Attendee(String name, String contactInfo, boolean hasInsurance, int members){
        this.name = name;
        this.contactInfo = contactInfo;
        this.hasInsurance = hasInsurance;
        this.members = members;
        this.itineraries = new ArrayList<>();
    }
    
    public void addActivity(Activity activity){
        activities.add(activity);
    }
    
    public void addAddOn(AddOn addOn){
        addOns.add(addOn);
    }
    
    public void addItinenary(Itinerary itinerary){
        itineraries.add(itinerary);
    }
    
    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public List<AddOn> getAddOns() {
        return addOns;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public int getMembers() {
        return members;
    }

    public boolean hasInsurance() {
        return hasInsurance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void setAddOns(List<AddOn> addOns) {
        this.addOns = addOns;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public void setInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }
    
    
}
