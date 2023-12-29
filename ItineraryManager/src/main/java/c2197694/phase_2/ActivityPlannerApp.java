/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The "ActivityPlannerApp" class represents an application for planning and managing activities and itineraries.
 *
 * @author c2197694
 */
public class ActivityPlannerApp {
    /**
     * List of 
     */
    private List<Activity> activities;//list of available activities. selected activities will be added to the arraylist in itinerary class to be handled by that
    private List<AddOn> addOns;
    private List<Itinerary> iteneraries;//list of user itinearies already created
    private static Activity activity;
//    private static Attendee attendee; //not used

//    private Itinerary itinerary;
//    private Activity activity; //not used

    //Add preset itinerary add-ons
//    public AddOn accommodation = new AddOn("Accommodation", 2000, "itinerary");
//    public AddOn teaBreaks = new AddOn("Tea break", 700, "itinerary");
//    public AddOn lunch = new AddOn("Lunch", 2200, "itinerary");
    
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
    
    public ActivityPlannerApp() {
        this.activities = new ArrayList<>();
        this.addOns = new ArrayList<>();
        this.addActivity(paperBridge);
        this.addActivity(assaultCourse);
        this.addActivity(cookery);
        this.addActivity(hiking);
//        addAddOn(accommodation);
//        addAddOn(teaBreaks);
//        addAddOn(lunch);
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

    public void displayAddOns() {
        for (AddOn addOn : addOns) {
            System.out.println("Title: " + addOn.getName());
            System.out.println("Activity Cost: £" + addOn.getCost() / 100);
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
        Scanner scanner = new Scanner(System.in);

        //Add preset activity add-ons
//        AddOn insurance = new AddOn("Insurance", 2300, "activity");
//        AddOn travel = new AddOn("Travel", 2000, "activity");
//        AddOn photography = new AddOn("Photography", 2500, "activity");
//        plannerApp.addAddOn(insurance);
//        plannerApp.addAddOn(travel);
//        plannerApp.addAddOn(photography);
        //Add preset itinerary add-ons
//        AddOn accommodation = new AddOn("Accommodation", 2000, "itinerary");
//        AddOn teaBreaks = new AddOn("Tea break", 700, "itinerary");
//        AddOn lunch = new AddOn("Lunch", 2200, "itinerary");
//        plannerApp.addAddOn(accommodation);
//        plannerApp.addAddOn(teaBreaks);
//        plannerApp.addAddOn(lunch);
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
        plannerApp.addActivity(paperBridge);
        plannerApp.addActivity(assaultCourse);
        plannerApp.addActivity(cookery);
        plannerApp.addActivity(hiking);
        
        OUTER:
        while (true) {
            System.out.println("1. Display All Activities");
            System.out.println("2. Create Itinerary");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    plannerApp.displayActivities();
                    break OUTER;
                case 2:
                    createItinerary(plannerApp, scanner);
                    break OUTER;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    private static void createItinerary(ActivityPlannerApp plannerApp, Scanner scanner) {
        System.out.println("Creating Itinerary...");
        Itinerary itinerary = new Itinerary();

        //Gather attendee information (then sell it to third party companies)
        System.out.println("Enter attendee name: ");
        String attendeeName = scanner.nextLine();

        System.out.println("Enter attendee email: ");
        String attendeeEmail = scanner.nextLine();

        boolean hasInsurance = false;
        while (true) {
            System.out.println("Does the attendee have personal insurance? (Yes/No)");
            String temporaryHasInsurance = scanner.nextLine();
            if (temporaryHasInsurance.equalsIgnoreCase("Yes") || 
                    temporaryHasInsurance.equalsIgnoreCase("Y") || 
                    temporaryHasInsurance.equalsIgnoreCase("true")) {
                hasInsurance = true;
                break;
            } else if (temporaryHasInsurance.equalsIgnoreCase("No") || 
                    temporaryHasInsurance.equalsIgnoreCase("N") || 
                    temporaryHasInsurance.equalsIgnoreCase("false")) {
                hasInsurance = false;
                break;
            } else {
                System.out.println("Please type a valid input (Yes/No)");
            }
        }

        System.out.println("Enter the number of attendees: ");
        int numbers = scanner.nextInt();

        Attendee attendee = new Attendee(attendeeName, attendeeEmail, 
                hasInsurance, numbers);
        itinerary.addAttendee(attendee);

        // Add activities to the itinerary
        plannerApp.displayActivities();
        while (true) {//print first and ask options using do...while?//what?
            System.out.print("Enter activity number to add to the itinerary (0 to finish): ");//would be better if user could add activity addons after adding one activity
            int activityNumber = scanner.nextInt();
            scanner.nextLine();

            if (activityNumber == 0 && !itinerary.getActivities().isEmpty()) {//user cant exit without taking any activities
                break;
            }
            // Retrieve the selected activity from the plannerApp
            List<Activity> activities = plannerApp.getActivity();
            if (activityNumber > 0 && activityNumber <= activities.size()) {
                Activity selectedActivity = activities.get(activityNumber - 1);

                //copy the activity using copy constructor (thx steve it's so good)
                Activity selectedActivityCopy = new Activity(selectedActivity);
                // Add the selected activity to the itinerary
                Activity activity = new Activity(selectedActivityCopy);
                itinerary.addActivity(activity);

                while (true) {
                    activity.displayAddOns();
                    System.out.println("Please select an add-on for " + activity.getTitle() +
                            ", or enter 0 to proceed with no add-ons for this activity");
                    System.out.println("Enter add-on name: ");
                    String activityAddOnName = scanner.nextLine();

                    if (activityAddOnName.equals("0")) {
                        break;
                    }

                    // Check if the add-on name exists in activity.getAddOns
                    if (activity.hasAddOn(activityAddOnName)) {
                        // If it exists, add the add-on to the activity
                        AddOn selectedAddOn = activity.getAddOnByName(activityAddOnName);
                        activity.addAddOn(selectedAddOn);
                        System.out.println("Add-on " + activityAddOnName + " added to " +
                                activity.getTitle());
                    } else {
                        // If it doesn't exist, provide a hint for input
                        System.out.println("Invalid add-on name. Please try again or " +
                                "enter 0 to proceed with no add-ons for this activity.");
                    }
                }

                System.out.println(selectedActivity.getTitle() + " added to the itinerary.");
            } else {
                System.out.println("Invalid activity number. Please try again.");
            }

        }
        itinerary.displayAddOns();
        while (true) {
            //if i try to put this before the while loop it glitches and doesnt take addon names correctly idk why <- bug case
            System.out.println("Please select add-on(s) for your itinerary or " +
                    "enter 0 to proceed with no more add-ons for this itinerary.");
            String itineraryAddOnName = scanner.nextLine();

            if (itinerary.hasAddOn(itineraryAddOnName)) {
                // If it exists, add the add-on to the activity
                AddOn selectedAddOn = itinerary.getAddOnByName(itineraryAddOnName);
                itinerary.addAddOn(selectedAddOn);
                System.out.println("Add-on " + itineraryAddOnName + " added to the itinerary");
            } else if (itineraryAddOnName.equals("0")) {
                break;
            } else {
                // If it doesn't exist, provide a hint for input
                System.out.println("Invalid add-on name. Please try again " +
                        "or enter 0 to proceed with no add-ons for this activity.");
            }
        }

        // Display itinerary and receipt
        System.out.println("Printing receipt...");
        System.out.println();
        itinerary.printReceipt();

    }

}
//TODO display available itinerary addons and activity addons
