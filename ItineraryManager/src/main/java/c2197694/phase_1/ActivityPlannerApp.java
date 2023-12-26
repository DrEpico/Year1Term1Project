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
    private List<AddOn> addOns;
    private List<Itinerary> iteneraries;

    public ActivityPlannerApp() {
        this.activities = new ArrayList<>();
        this.addOns = new ArrayList<>();
        
    }

    public void addActivity(Activity activity) {
        activities.add(activity);

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

    public void addAddOn(AddOn addOn) {
        addOns.add(addOn);
    }

    public void addItinenary(Itinerary itinerary) {
        iteneraries.add(itinerary);
    }

    public int calculateTotalCost() {
        return 0;
    }

    public List<Activity> getActivity() {
        return activities;
    }
    
//   public void clearLists(Activity activity, Itinerary itinerary){
//       activity.clearActivity();
//       itinerary.clearItinerary();
//   }

//    public void setActivity(List<Activity> activity) {
//        this.activity = activity;
//    } I don't think this is necessary or will be used (addActivity exists)
    
    public static void main(String[] args) {
        //product 
        ActivityPlannerApp plannerApp = new ActivityPlannerApp();  // Create an instance of ActivityPlannerApp
        
        //Add preset activity add-ons
        AddOn insurance = new AddOn("Insurance", 2300, "activity");
        AddOn travel = new AddOn("Travel", 2000, "activity");
        AddOn photography = new AddOn("Photography", 2500, "activity");
        
        //Add preset itinerary add-ons
        AddOn accommodation = new AddOn("Accommodation", 2000, "itinerary");
        AddOn teaBreaks = new AddOn("Coffee/Tea breaks", 700, "itinerary");
        AddOn lunch = new AddOn("Lunch", 2200, "itinerary");
        
        // Add preset/default activities (more activities may be added later
        Activity paperBridge = new Activity(1000, "Building a bridge from paper", 1,
                "Let's build a bridge from paper!", "Teesside University",
                "16th Dec 15:00", 2, false);    
        Activity assaultCourse = new Activity(10000, "SAS-style assault courses", 2,
                "Become stronks", "Saltburn",
                "15th Dec 7:00", 15, true);
        Activity cookery = new Activity(1700, "Cookery class", 3,
                "Let bro cook", "Teesside University",
                "9th Dec 14:00", 3, false);
        Activity hiking = new Activity(5900, "Hiking and nature walks", 4, 
                "Hiking time!", "North York Moors Park",
                "8th Dec 14:00", 6, true);
        
        //add the activities to the instance of ActivityPlannerApp object -> no point rlly -> deleted 
        

        //actual sign up process (3 attendee 3 activities - group discount of 5% is applied)
        Itinerary itinerary1 = new Itinerary();//instantiate new itinerery 
        Attendee attendee1 = new Attendee("Joe Biden", 
                "jbiden@live.tees.ac.uk", true, 3);
        itinerary1.addAttendee(attendee1);
        itinerary1.addActivity(cookery);//£17 //add existing activity to the itinerary using addActivity class which uses an activity as it's paramete
        Activity paperBridge1 = new Activity(paperBridge);//copies activity from parent 
        itinerary1.addActivity(paperBridge1);//£10
        paperBridge1.addAddOn(travel);//£20
        Activity assaultCourse1 = new Activity(assaultCourse);//copies activity from parent a
        assaultCourse1.addAddOn(insurance);//£23
        itinerary1.addActivity(assaultCourse1);//£100
        itinerary1.addAddOn(accommodation);//£20
        
        itinerary1.displayFinalCost();//should be £190 x 3 with 5% activity count discount = 180.5 -> £180
        //calling displayCost() clears the itinerary and activity array lists and make it ready for the next customer to customise their itinerary

        Itinerary itinerary2 = new Itinerary();
        Attendee attendee2 = new Attendee("Josh Hutchinson", 
                "jh1994@freemail", true, 1);
        itinerary2.addAttendee(attendee2);
        Activity assaultCourse2 = new Activity(assaultCourse);//copies activity from parent 
        assaultCourse2.addAddOn(insurance);//£23 //insurance needs to be added first so addActivity() wont panick seeing there is no insurance xD
        itinerary2.addActivity(assaultCourse2);//£100
        itinerary2.addAddOn(travel);//£20
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
