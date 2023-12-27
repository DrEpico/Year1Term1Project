/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_1;

/**
 * Represents an attendee participating in activities.
 * This class contains information about the attendee's name, contact information,
 * insurance status, number of members, and a list of activities they are participating in.
 *
 * @author c2197694
 */
public class Attendee {
    private String name;
    private String contactInfo;
    private int members;
    private boolean hasInsurance; //does the attendee have a personal insurance that covers for the activity that require insurace 
    
    /**
     * Constructs an Attendee with the specified name, contact information,
     * insurance status, and number of members.
     * 
     * @param name          The name of the attendee.
     * @param contactInfo   The contact information of the attendee.
     * @param hasInsurance  Whether the attendee has personal insurance or not. 
     * @param members       The number of members in the attendee's group.
     */
    public Attendee(String name, String contactInfo, boolean hasInsurance, int members){
        this.name = name;
        this.contactInfo = contactInfo;
        this.hasInsurance = hasInsurance;
        this.members = members;
    }
    
    /**
     * Generic getter methods.
     * 
     * @return Fields of this class.
     */
    
    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public int getMembers() {
        return members;
    }

    public boolean hasInsurance() {
        return hasInsurance;
    }
    
    /**
     * Generic setter methods.
     * 
     * @param name & other class fields
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public void setInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }
    
    
}
