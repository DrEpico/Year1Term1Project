/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author c2197694
 */
public class Itinerary {

    /**
     * Constant strings representing all letters and numbers that can be used in
     * generateRandomString() method.
     *
     * Random object instead of SecureRandom that is cryptographically stronger
     * than Random but slower as well. So for the purpose of itinerary ID
     * generation in this project, Random is more suitable
     */
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final Random random = new Random();

    /**
     * Lists of objects associated with Itinerary class such as activities and
     * itinerary addOns.
     */
    private final List<Activity> activities;
    private final List<AddOn> addOns;
    private final String id;
    private Attendee attendee;

    /**
     * Preset add-ons associated with itinerary.
     *
     * Itinerary add-ons are instantiated in the Itinerary class.
     */
    private final AddOn accommodation = new AddOn("Accommodation", 1700, "itinerary");
    private final AddOn teaBreaks = new AddOn("Coffee/Tea breaks", 500, "itinerary");
    private final AddOn lunch = new AddOn("Lunch", 1700, "itinerary");
    private final AddOn nightEntertainment = new AddOn("Night Entertainment", 1800, "itinerary");
    private final AddOn explorerJournal = new AddOn("Journal Kit", 400, "itinerary");

    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new Itinerary. Generates a unique identifier, initializes
     * empty lists for activities and add-ons, and prints a message indicating
     * the creation of the itinerary.
     */
    public Itinerary() {
        id = generateId();
        activities = new ArrayList<>();
        addOns = new ArrayList<>();
        System.out.println("Itinerary was created");
    }

    /**
     * This method generated a random string of characters from specified pool.
     * The method will then be called in {@link #generateId()} with specific
     * instructions via parameters.
     *
     * @param characters Either LETTERS or NUMBERS constant string
     * @param length The length of the random string to generate.
     * @return A random string of the specified length.
     */
    private String generateRandomString(String characters, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    /**
     * Generates a random itinerary reference by concatenating two random
     * letters, three random digits, and one random letter.
     *
     * @return A random itinerary reference.
     */
    private String generateId() {
        // Generate two random letters
        String randomLetters = generateRandomString(LETTERS, 2);

        // Generate three random digits
        String randomDigits = generateRandomString(NUMBERS, 3);

        // Generate one random letter
        String randomLastLetter = generateRandomString(LETTERS, 1);

        // Concatenate the parts to form the itinerary reference
        return randomLetters + randomDigits + randomLastLetter;
    }

    /**
     * Simple getter method.
     *
     * Gets reference code of the itinerary.
     *
     * @return The itinerary ID.
     */
    protected String getId() {
        return id;
    }

    /**
     * Adds an activity instance to the activities array list and prevents duplication
     * of one activity in the itinerary.
     *
     * @param activity The activity to be added to the list.
     */
    protected void addActivity(Activity activity) {
        if (!activities.contains(activity)){
            activities.add(activity);
            System.out.println(activity.getTitle() + " was added to the itinerary");
        } else {
            System.out.println(activity.getTitle() + " already exists in your list of activities!");
        }
    }

    /**
     * Adds an itinerary add-on to the list of itinerary add-ons.
     *
     * @param addOn The itinerary add-on to be added.
     */
    protected void addAddOn(AddOn addOn) {//adds itinerary addons
        addOns.add(addOn);
        System.out.println(addOn.getName() + " was added to the itinerary");
    }

    /**
     * Sets the attendee for this itinerary.
     *
     * @param attendee The attendee to be associated with this itinerary.
     */
    protected void setAttendee(Attendee attendee) {
        this.attendee = attendee;
        
    }

    /**
     * Checks if attendee has a personal insurance that covers for the activity
     * that requires insurance based on the company rules.
     *
     * If an activity requires insurance and user doesn't have personal
     * insurance, user will be prompted if they want to add insurance add-on
     * provided or delete the activity from the itinerary if they disagree with
     * the company rule and don't want to take any form of insurance. Ultimately
     * users have the choice to discard the entire itinerary if they choose to
     * do so.
     *
     * If user chose to remove the activity from itinerary, the activity object
     * will be stored in activityToRemoved variable and removeActivity() method
     * will be called outside of the loop to avoid
     * ConcurrentModificationException for directly modifying the collection
     * inside the loop.
     *
     * Boolean "shouldRemove" is introduced to remove an activity only if that
     * option is selected to avoid unexpected behavior for removing null from
     * the activities list.
     */
    private void incuranceCheck() {
        Activity activityToRemove = null;
        boolean shouldRemove = false;
        if (!attendee.hasInsurance()) {
            for (Activity activity : activities) {
                if (activity.requiresInsurance() && !activity.containsInsurance()) {
                    OUTER:
                    while (true) {
                        System.out.println(activity.getTitle() + " requires insurance. \n"
                                + "Please Select an option\n"
                                + "1: Add Insurance for £" + activity.getInsuranceAddOn().getCost() / 100 + "\n"
                                + "2: Remove " + activity.getTitle() + " from the itinerary\n"
                                + "3: Discard itinerary");
                        switch (scanner.nextInt()) {
                            case 1:
                                activity.addAddOn(activity.getInsuranceAddOn());
                                System.out.println("Insurance addOn was added");
                                break OUTER;
                            case 2:
                                shouldRemove = true;
                                activityToRemove = activity;//Stores activity to be removed
                                System.out.println("The activity was deleted from the itinerary");
                                break OUTER;
                            case 3:
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
        //direct modification of the list outside of any iteration
        if (shouldRemove == true) {
            removeActivity(activityToRemove);
        }
    }

    /**
     * Removes the specified activity from the list of activities associated
     * with the itinerary.
     *
     * @param activity The activity to be removed from the itinerary.
     */
    protected void removeActivity(Activity activity) {
        activities.remove(activity);
        System.out.println(activity.getTitle() + " was removed");
    }

    /**
     * Calculates the total cost for the itinerary.
     *
     * This method iterates over activities list and gets the cost of each
     * activity with its add-ons by calling calculateCostWithAddOn() from
     * activity class. Then cost of itinerary add-ons will be accumulated to
     * totalCost local variable.
     *
     * @return
     */
    private int calculateCost() {
        int totalCost = 0;
        for (Activity activity : activities) {
            totalCost += activity.calculateCostWithAddOn();//go back to old code
        }
        for (AddOn addOn : addOns) {
            totalCost += addOn.getCost();
        }

        int totalDiscount = getDiscountMultiplier(); 
        totalCost = (totalCost * totalDiscount) / 100;
        return totalCost;
    }

    /**
     * Determines the discount value but in base format for further
     * calculations. This is due to different methods requiring different
     * formats and this method is a capsulation that can be used in different
     * ways.
     *
     * @return Discount multiplier.
     */
    private int getDiscountMultiplier() {
        int discountPercentage = 0;

        if (attendee.getMembers() >= 10 && attendee.getMembers() <= 20
                && activities.size() <= 2) {
            discountPercentage = 5;
        } else if (attendee.getMembers() > 20
                && activities.size() <= 2) {
            discountPercentage = 8;
        } else if (attendee.getMembers() < 10
                && activities.size() >= 3 && activities.size() <= 5) {
            discountPercentage = 5;
        } else if (attendee.getMembers() >= 10 && attendee.getMembers() <= 20
                && activities.size() >= 3 && activities.size() <= 5) {
            discountPercentage = 8;
        } else if (attendee.getMembers() > 20
                && activities.size() >= 3 && activities.size() <= 5) {
            discountPercentage = 12;
        } else if (attendee.getMembers() < 10
                && activities.size() >= 6) {
            discountPercentage = 10;
        } else if (attendee.getMembers() >= 10 && attendee.getMembers() <= 20
                && activities.size() >= 6) {
            discountPercentage = 12;
        } else if (attendee.getMembers() > 20
                && activities.size() >= 6) {
            discountPercentage = 14;
        }
        int multiplier = 100 - discountPercentage;
        return multiplier;
    }

    /**
     * Displays the final cost of the itinerary, considering insurance checks,
     * individual attendee costs, and the total cost for the entire itinerary.
     *
     * @return The total cost of the itinerary in pence.
     */
    protected double displayFinalCost() {
        incuranceCheck();
        double individualItineraryCost = (double) calculateCost()/100;
        double finalItineraryCost = (double) calculateCost()/100 * attendee.getMembers();
        System.out.print("Itinerary cost for " + attendee.getName() 
                + ": £" + finalItineraryCost);
        if(attendee.getMembers() > 1){
            System.out.println(" (£" + individualItineraryCost + " per person)"); 
        }
        System.out.println();
        return finalItineraryCost;
    }
    
    /**
     * Getter methods for itinerary add-ons.
     * 
     * @return add-on object
     */
    
    protected AddOn getAccommodationAddOn(){
        return accommodation;
    }
    
    protected AddOn getTeaBreakAddOn(){
        return teaBreaks;
    }
    
    protected AddOn getLunchAddOn(){
        return lunch;
    }
    
    protected AddOn getnightEntertainment(){
        return nightEntertainment;
    }
    
    protected AddOn getExplorerJournal(){
        return explorerJournal;
    }
}
