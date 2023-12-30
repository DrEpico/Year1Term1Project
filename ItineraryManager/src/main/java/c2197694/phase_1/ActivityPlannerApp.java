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
public class ActivityPlannerApp {

    /**
     * List of available activities and itineraries created by customers.
     *
     * Selected activities by users will be added to the array-list in itinerary
     * class to be process in the same class.
     *
     * List of itineraries are currently not in use but it can be used to
     * control itinerary ID duplication which is very unlikely in the first
     * place (1 in 17 million).
     */
    private final List<Activity> activities;
    private List<Itinerary> iteneraries;

    /**
     * Available activities are declared and initialised in the activity planner
     * app for higher relevancy.
     *
     * These activity objects will then be added to the relevant array-list in
     * itinerary class.
     */
    private static final Activity paperBridge = new Activity(
            500,
            "Building a bridge from paper",
            1,
            "Let's build a bridge from paper!",
            "Teesside University",
            "16th Dec 15:00",
            2,
            false);
    private static final Activity assaultCourse = new Activity(
            9000,
            "SAS-style assault courses",
            2,
            "Become stronks",
            "Saltburn",
            "15th Dec 7:00",
            15,
            true);
    private static final Activity cookery = new Activity(
            1000,
            "Cookery class",
            3,
            "Let bro cook",
            "Teesside University",
            "9th Dec 14:00",
            3,
            false);
    private static final Activity hiking = new Activity(
            3000,
            "Hiking and nature walks",
            4,
            "Hiking time!",
            "North York Moors Park",
            "8th Dec 14:00",
            6,
            true);

    /**
     * Activity planner app constructor initialises activities list.
     */
    public ActivityPlannerApp() {
        this.activities = new ArrayList<>();
    }

    /**
     * Adds activity to the list of selected activities by user.
     *
     * @param activity The activity to be added to the list by users.
     */
    public void addActivity(Activity activity) {
        System.out.println(activity.getTitle() + " was added to the itinerary");
        activities.add(activity);

    }

    /**
     * Displays information about each activity in the itinerary. Prints details
     * such as title, number, description, location, date and time, duration,
     * and base cost for each activity.
     */
    public void displayActivities() {
        for (Activity activity : activities) {
            System.out.println("Title: " + activity.getTitle());
            System.out.println("Activity Number: " + activity.getNumber());
            System.out.println("Description: " + activity.getDescription());
            System.out.println("Location: " + activity.getLocation());
            System.out.println("DateTime: " + activity.getDateTime());
            System.out.println("Duration: " + activity.getDuration() + " hours");
            System.out.println("Base Cost: £" + activity.getBaseCost() / 100);
            System.out.println("--------------------------------------");
        }
    }

    /**
     * Could be used for ID duplication logic. Currently not in used.
     *
     * @param itinerary The itinerary to be added.
     */
    public void addItinenary(Itinerary itinerary) {
        iteneraries.add(itinerary);
    }

    /**
     * The main method to demonstrate the functionality of the Activity Planner
     * application. It creates instances of the ActivityPlannerApp, Itinerary,
     * and Attendee classes, adds activities, attendees, and add-ons to the
     * itineraries, calculates and displays the final cost, and performs some
     * additional testing.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create an instance of ActivityPlannerApp 
        ActivityPlannerApp plannerApp = new ActivityPlannerApp();
        
        // Itinerary 1
        Itinerary itinerary1 = new Itinerary();//instantiate a new itinerery 
        Attendee attendee1 = new Attendee("Joe Biden",
                "jbiden@live.tees.ac.uk", true, 3);
        itinerary1.setAttendee(attendee1);
        itinerary1.addActivity(cookery);//£10
        //copied activity from orignal instance
        Activity paperBridge1 = new Activity(paperBridge);
        itinerary1.addActivity(paperBridge1);//£5
        paperBridge1.addAddOn(paperBridge1.travel);//£10
        //copied activity from orignal instance
        Activity assaultCourse1 = new Activity(assaultCourse);
        itinerary1.addActivity(assaultCourse1);//£90
        assaultCourse1.addAddOn(assaultCourse1.insurance);//£13
        //itinerary add-on
        itinerary1.addAddOn(itinerary1.getLunchAddOn());//£17
        //should be £145 x 3 with 5% activity count discount = 180.5 -> £180
        itinerary1.displayFinalCost(); 
        
        // Itinerary 2
        Itinerary itinerary2 = new Itinerary();
        Attendee attendee2 = new Attendee("Josh Hutchinson",
                "jh1994@freemail", true, 1);
        itinerary2.setAttendee(attendee2);
        //copied activity from orignal instance
        Activity assaultCourse2 = new Activity(assaultCourse);
        itinerary2.addActivity(assaultCourse2);//£90
        assaultCourse2.addAddOn(assaultCourse2.insurance);
        itinerary2.addActivity(hiking);//£30
        //itinerary add-on
        itinerary2.addAddOn(itinerary2.getAccommodationAddOn());//£17
        
        itinerary2.displayFinalCost();//should be £202

        //test field
        System.out.println("----------------------TEST STUFF-----------------------");

        System.out.println("Itinerary 1 ID: " + itinerary1.getId());
        System.out.println("Itinerary 2 ID: " + itinerary2.getId());

        // Display available activities 
        plannerApp.displayActivities();

    }
}
