/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_1;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author c2197694
 */
public class Itinerary {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    private List<Activity> activities;
    private List<AddOn> addOns;
    private String id;
    //private List<String> idList; not used because one itinerary won't have multiple ID's
    private Attendee attendee;/*Not required by specs, i could just set an Attendee type
    and write getter and setter but having an array list would look more consistant in the main method*/
    
    public AddOn accommodation = new AddOn("Accommodation", 2000, "itinerary");
    public AddOn teaBreaks = new AddOn("Coffee/Tea breaks", 700, "itinerary");
    public AddOn lunch = new AddOn("Lunch", 2200, "itinerary");
    
    Scanner scanner = new Scanner(System.in);

    public Itinerary() {
        id = generateId(6);
        activities = new ArrayList<>();
        addOns = new ArrayList<>();
        //idList = new ArrayList<>();
        //idList.add(id); same reason as line 24
        System.out.println("Itinerary was created");
    }

    public String getId() {
        return id;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        System.out.println(activity.getTitle() + " was added to the itinerary");
    }

    public void clearItinerary() {
        activities.clear();
    }

    public void incuranceCheck() {
        if (attendee.hasInsurance()) {//checks if attendee has personal insurance
            //might do something in here idk
        } else {//if they dont...
//            boolean insuranceRequired = false;//
            for (Activity activity : activities) {
                if (activity.requiresInsurance() && !activity.containsInsurance()) {
                    OUTER:
                    while (true) {
                        System.out.println(activity.getTitle() + " requires insurance. "
                                + "Please Select an option\n"
                                + "1: Add Insurance for £20\n"
                                + "2: Discard itinerary");
                        switch (scanner.nextInt()) {
                            case 1:
                                activity.addAddOn(activity.insurance);
                                System.out.println("Insurance addOn was added");
                                break OUTER;
                            case 2:
                                System.out.println("Discarding the process...");
                                System.exit(0);
                                break OUTER;
                            default:
                                System.out.println("Please select a valid choice");
                                break;
                        }
                    }
                }
            }
        }
    }

//    public boolean ContainsInsurance() {//insurance is an activity addon. what even is this method lmao
//        for (AddOn addOn : addOns) {
//            if (addOn.getName().equalsIgnoreCase("Insurance")) {
//                return true; // Found an insurance add-on
//            }
//        }
//        return false; // Insurance add-on not found
//    }
    public void addAddOn(AddOn addOn) {
//        if (addOn.getType().equalsIgnoreCase("itinerary")) {
        addOns.add(addOn);
//            System.out.println(addOn.getName() + " was added to the itinerary (itinerary addon)");
//        } else if (addOn.getType().equalsIgnoreCase("activity")) {
//            activityAddOns.add(addOn);
//            System.out.println(addOn.getName()+ " was added to the itinerary (activity addon)");//
//        }
    }

    public void addAttendee(Attendee attendee) {
        this.attendee = attendee;
    }
//
//    public List<Attendee> getAttendeeList() {
//        return attendeeList;
//    }

    private int calculateCost() {
        int totalCost = 0;
        for (Activity activity : activities) {
            totalCost += activity.calculateCostWithAddOn();//go back to old code
        }
        for (AddOn addOn : addOns) {
            totalCost += addOn.getCost();
        }

        int totalDiscount = calculateDiscount(); // Pass attendee as a parameter (required to know the members value)
        totalCost = (totalCost * totalDiscount) / 100;
        return totalCost;
    }

    private int calculateDiscount() {//splitted the discount calculatin logic from total cost calculation
        int attendeeDiscount = 0;

        if (attendee.getMembers() >= 10 && attendee.getMembers() < 20) {
            attendeeDiscount = 5;
        } else if (attendee.getMembers() >= 20) {
            attendeeDiscount = 8;
        }

        int activityDiscount = 0;
        if (activities.size() >= 3 && activities.size() <= 5) {
            activityDiscount = 5;
        } else if (activities.size() > 5 && activities.size() <= 6) {
            activityDiscount = 10;
        } else if (activities.size() > 6) {
            activityDiscount = 12;
        }

        int totalDiscount = 100 - attendeeDiscount;
        totalDiscount = (totalDiscount * (100 - activityDiscount)) / 100;

        return totalDiscount;
    }

    public int displayFinalCost() {
        incuranceCheck();
        int itineraryCost = calculateCost() /**
                 * attendee.getMembers()
                 */
                ;
        /*= calculatedCost * attendee.getMembers();*/ //price multiplied by members logic diabled for now //not disabled anymore
        System.out.println("Itinerary cost for " + attendee.getName() + ": £" + itineraryCost / 100);
//        clearItinerary();//As this method will be the last operation in an itenerary, calling displayCost() method will trigger clearing all the lists so issues with price calculation will not happen
        System.out.println();
        return itineraryCost;

    }

    private String generateId(int length) {//generate random string
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        id = stringBuilder.toString();
        return id;//TODO:check if it's unique!
    }

    //TODO/note: generate receipt storing the first member in attendee arrayList as lead attendee
    //save for later: insurance check
//    if (activity.requiresInsurance() && !containsInsurance() && activity.containsInsurance() && !attendee.hasInsurance()) {
//            // Handle the case where insurance is required but not present in the itinerary
//            System.out.println("Insurance is required for adding " + activity.getTitle() + " to this itinerary");
//            return;  // Do not add the activity to the itinerary 
//        }
}
