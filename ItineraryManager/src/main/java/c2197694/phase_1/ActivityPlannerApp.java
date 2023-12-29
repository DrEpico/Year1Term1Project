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
    private List<Activity> activities;
    private List<Itinerary> iteneraries;
    
    private static Activity paperBridge = new Activity(1000, "Building a bridge from paper",
            1, "Let's build a bridge from paper!", "Teesside University",
                "16th Dec 15:00", 2, false);    
    private static Activity assaultCourse = new Activity(10000, "SAS-style assault courses", 
            2, "Become stronks", "Saltburn",
                "15th Dec 7:00", 15, true);
    private static Activity cookery = new Activity(1700, "Cookery class", 
            3, "Let bro cook", "Teesside University",
                "9th Dec 14:00", 3, false);
    private static Activity hiking = new Activity(5900, "Hiking and nature walks", 
            4, "Hiking time!", "North York Moors Park",
                "8th Dec 14:00", 6, true);
    
    public ActivityPlannerApp() {
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        System.out.println(activity.getTitle() + " was added to the itinerary");
    }

    public void displayActivities() {
        for (Activity activity : activities) {
            System.out.println("Title: " + activity.getTitle());
            System.out.println("Activity Number: " + activity.getNumber());
            System.out.println("Description: " + activity.getDescription());
            System.out.println("Location: " + activity.getLocation());
            System.out.println("DateTime: " + activity.getDateTime());
            System.out.println("Duration: " + activity.getDuration() + " hours");
            System.out.println("Base Cost: " + activity.getBaseCost());
            System.out.println("--------------------------------------");
        }
    }

    public void addItinenary(Itinerary itinerary) {
        iteneraries.add(itinerary);
    }
    
    public static void main(String[] args) {
        //product 
        ActivityPlannerApp plannerApp = new ActivityPlannerApp();  // Create an instance of ActivityPlannerApp
        
        // Add preset/default activities (more activities may be added later
        
        
        //add the activities to the instance of ActivityPlannerApp object -> no point rlly -> deleted 
        

        //actual sign up process (3 attendee 3 activities - group discount of 5% is applied)
        Itinerary itinerary1 = new Itinerary();//instantiate new itinerery 
        Attendee attendee1 = new Attendee("Joe Biden", 
                "jbiden@live.tees.ac.uk", true, 3);
        itinerary1.addAttendee(attendee1);
        itinerary1.addActivity(cookery);//£17 //add existing activity to the itinerary using addActivity class which uses an activity as it's paramete
        Activity paperBridge1 = new Activity(paperBridge);//copies activity from parent 
        itinerary1.addActivity(paperBridge1);//£10
        paperBridge1.addAddOn(paperBridge1.travel);//£20
        Activity assaultCourse1 = new Activity(assaultCourse);//copies activity from parent a
        assaultCourse1.addAddOn(assaultCourse1.insurance);//£23
        itinerary1.addActivity(assaultCourse1);//£100
        itinerary1.addAddOn(itinerary1.accommodation);//£20
        
        itinerary1.displayFinalCost();//should be £190 x 3 with 5% activity count discount = 180.5 -> £180
        //calling displayCost() clears the itinerary and activity array lists and make it ready for the next customer to customise their itinerary

        Itinerary itinerary2 = new Itinerary();
        Attendee attendee2 = new Attendee("Josh Hutchinson", 
                "jh1994@freemail", true, 1);
        itinerary2.addAttendee(attendee2);
        Activity assaultCourse2 = new Activity(assaultCourse);//copies activity from parent 
        assaultCourse2.addAddOn(assaultCourse2.insurance);//£23 //insurance needs to be added first so addActivity() wont panick seeing there is no insurance xD
        itinerary2.addActivity(assaultCourse2);//£100
        itinerary2.addAddOn(itinerary2.accommodation);//£20
        itinerary2.addActivity(hiking);//£59
        
        itinerary2.displayFinalCost();//should be £202

        //test field
        System.out.println("----------------------TEST STUFF-----------------------");

        System.out.println("Itinerary 1 ID: " + itinerary1.getId());
        System.out.println("Itinerary 2 ID: " + itinerary2.getId());

        // Display available activities 
        plannerApp.displayActivities();

    }
}
