/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The Itinerary class represents an itinerary for a series of activities and
 * associated add-ons. It includes methods to calculate costs, apply discounts,
 * and generate a receipt for the itinerary.
 *
 * @author c2197694
 */
public class Itinerary {

    /**
     * Constant strings representing all letters and numbers that can be used in
     * {@link #generateRandomString()} method, which will then be called in
     * {@link #generateId()} method to create itinerary ID.
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
    private final List<AddOn> displayAddOns;
    private final String id;
    private Attendee attendee;

    /**
     * These lines get the current day, month and year then store them in
     * variables to be used later in {@link #printReceipt()} function.
     */
    private final LocalDateTime now = LocalDateTime.now();
    private final int day = now.getDayOfMonth();
    private final int month = now.getMonthValue();
    private final int year = now.getYear();

    /**
     * Preset add-ons associated with itinerary.
     *
     * Itinerary add-ons are instantiated in the Itinerary class.
     */
    private final AddOn accommodation = new AddOn("Accommodation", 1700, "itinerary");
    private final AddOn teaBreaks = new AddOn("Coffee/Tea breaks", 500, "itinerary");
    private final AddOn lunch = new AddOn("Lunch", 1700, "itinerary");
    private final AddOn nightEntertainment = new AddOn("Night Entertainment Package", 1900, "itinerary");
    private final AddOn explorerJournal = new AddOn("Explorer's Journal Kit", 800, "itinerary");

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new Itinerary object with a randomly generated ID and
     * initializes the lists for activities, itinerary add-ons.
     *
     * Additionally, it adds preset display add-ons such as accommodation, tea
     * breaks, and lunch to the display add-ons list.
     *
     * The ID is generated using the {@link #generateReference()} method.
     */
    public Itinerary() {
        id = generateId();

        activities = new ArrayList<>();
        addOns = new ArrayList<>();
        displayAddOns = new ArrayList<>();

        this.addDisplayAddOns(accommodation);
        this.addDisplayAddOns(teaBreaks);
        this.addDisplayAddOns(lunch);
        this.addDisplayAddOns(nightEntertainment);
        this.addDisplayAddOns(explorerJournal); 
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
     * Simple getter method.
     *
     * Gets the list of activities associated with the itinerary.
     *
     * @return The list of activities associated with the itinerary.
     */
    protected List<Activity> getActivities() {
        return activities;
    }

    /**
     * Adds an activity instance to the activities array list and prevents
     * duplication of one activity in the itinerary.
     *
     * @param activity The activity to be added to the list.
     */
    protected void addActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
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
        if (!addOns.contains(addOn)) {
            addOns.add(addOn);
        } else {
            System.out.println(addOn.getName() + " already exists in list of itinerary add-ons!");
        }
    }

    /**
     * Adds an add-on to the list of display add-ons associated with the
     * itinerary.
     *
     * @param addOn Add-on instance to be added to the catalogue/display list.
     */
    protected final void addDisplayAddOns(AddOn addOn) {
        if (!displayAddOns.contains(addOn)) {
            displayAddOns.add(addOn);
        }
    }

    /**
     * Sets the lead attendee for this itinerary.
     *
     * @param attendee The attendee to be associated with this itinerary.
     */
    protected void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    /**
     * Displays the details of each itinerary add-on associated with the
     * itinerary. The details include the title, activity cost, and a separator
     * line for each add-on.
     */
    protected void displayAddOn() {//display catalogue
        System.out.println("\n--------------------------------------");
        for (AddOn addOn : displayAddOns) {
            System.out.println("Title: " + addOn.getName());
            System.out.println("Activity Cost: £" + addOn.getCost() / 100);
            System.out.println("--------------------------------------");
        }
    }

    /**
     * This encapsulated method allows the program to check if the add-on that
     * user inputted exists in the catalogue/display array or not and return
     * Boolean to move on to the next process.
     *
     * @param addOnName The add-on to be checked.
     * @return {@code true} if an add-on instance with the same name exists in
     * displayAddOns list and {@code false} otherwise.
     */
    protected boolean hasAddOn(String addOnName) {
        for (AddOn addOn : displayAddOns) {
            if (addOn.getName().equalsIgnoreCase(addOnName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method iterates over add-ons catalogue and returns an add-on that
     * matches user input.
     *
     * It should never reach the return null line as this method gets called
     * after {@link #hasAddOn()} returns true and confirms an add-on by that
     * name exists.
     *
     * @param addOnName User-input to be checked if an add-on by that name
     * exists or not.
     * @return Add-on that matches user input.
     */
    protected AddOn getAddOnByName(String addOnName) {
        for (AddOn addOn : displayAddOns) {
            if (addOn.getName().equalsIgnoreCase(addOnName)) {
                return addOn;
            }
        }
        return null; // Return null if the add-on with the specified name is not found
    }

    /**
     * Checks whether the itinerary add-ons list created by user contains
     * insurance.
     *
     * @return {@code true} if the list contains insurance add-on and
     * {@code false} otherwise.
     */
    protected boolean containsInsurance() {//insurance is an activity addon
        for (AddOn addOn : addOns) {
            if (addOn.getName().equalsIgnoreCase("Insurance")) {
                return true; // Found an insurance add-on
            }
        }
        return false; // Insurance add-on not found
    }

    /**
     * Reusable block of code for catching input mismatch exception for
     * nextInt() method instead of writing try-catch many times.
     *
     * @param scanner The Scanner object to read user input from.
     * @return
     */
    private static int protectedNextInt(Scanner scanner) {
        int numbers = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                numbers = scanner.nextInt();
                scanner.nextLine();
                isValidInput = true; // Break the loop if input is successful
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                scanner.next();
            }
        }
        return numbers;
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
                                + "1: Add Insurance for £"
                                + activity.getInsuranceAddOn().getCost() / 100 + "\n"
                                + "2: Remove " + activity.getTitle() + " from the itinerary\n"
                                + "3: Discard itinerary");
                        switch (protectedNextInt(scanner)) {
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
    private void removeActivity(Activity activity) {
        if (activities.contains(activity)) {
            activities.remove(activity);
        }
    }

    /**
     * Calculates the total cost for the itinerary.
     *
     * This method iterates over activities list and gets the cost of each
     * activity with its add-ons by calling calculateCostWithAddOn() from
     * activity class. Then cost of itinerary add-ons will be accumulated to
     * totalCost local variable.
     *
     * Activity and add-on prices stored in pence are converted to pounds to
     * display the final cost with a higher precision on the receipt after
     * applying the discount.
     *
     * @return The cost for the entire itinerary without applying the discount
     * yet.
     */
    private double calculateCost() {//without discounts applied
        double totalCost = 0;
        for (Activity activity : activities) {
            totalCost += (double) activity.calculateCostWithAddOn();//go back to old code
        }
        totalCost += (double) calculateAddOnsSubTotal();
        return totalCost * attendee.getMembers(); //without applying discounts
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
     * Calculates the discount amount in pounds for the itinerary.
     *
     * If there was a 5% discount, calculateDiscountPercentage() returns value
     * 95 and it will be stored in discountRate. discountRate will then be
     * casted to double and stored in discountPercentage variable as 0.05 so 5%.
     * Then this value will be multiplied by totalCost to achieve total discount
     * amount, which will be printed on the receipt.
     *
     * @return The total discord amount in pounds.
     */
    private double calculateDiscountInPounds() {
        double totalCost = calculateCost();
        int discountRate = getDiscountMultiplier();
        // Calculate the discount amount
        double discountPercentage = (100 - (double) discountRate) / 100.0;
        // Subtract the discount amount from the total cost
        double totalDiscountAmount = discountPercentage * (double) totalCost;
        return totalDiscountAmount / 100;
    }

    /**
     * This method applies the discount amount. Not by multiplying but by
     * subtracting the discount amount from base itinerary cost to allow
     * consistent display of correct prices on the receipt.
     *
     * @return Final cost of the itinerary discount applied.
     */
    private double applyDiscount() {
        double finalCost = calculateCost();
        double discountAmount = calculateDiscountInPounds();
        finalCost = (finalCost / 100) - discountAmount;
        return finalCost;
    }

    /**
     * Gets the formatted string containing the initial letter of the first name
     * and the full surname.
     *
     * @return The formatted string.
     */
    private String getAttendeeInitialSurname() {
        String fullName = attendee.getName();
        // Split the full name into first name and surname
        String[] fullNameArray = fullName.split(" ");
        //It returns the first letter from the String array 
        String initial = String.valueOf(fullNameArray[0].charAt(0));
        //surname is the whole second member of the String array
        String surname = fullNameArray[1];
        //concat both initial and surname variables to get initial<SPACE>surname outcome
        String initialSurname = initial.concat(" " + surname);
        return initialSurname;
    }

    /**
     * Gets the string representation of the number (1-5) in words or as string
     * digits (6-).
     *
     * This method is reused in receipt generation for 2 similar processes
     * (activity and attendee count)
     *
     * 0 attendee or 0 activities make no sense as an itinerary so it will be
     * disregarded and handled as input error
     *
     * @param number The integer to be converted.
     * @return The string representation of the number.
     */
    private String getStringNumber(int number) {
        if (number > 0) {
            switch (number) {
                case 1:
                    return "One";
                case 2:
                    return "Two";
                case 3:
                    return "Three";
                case 4:
                    return "Four";
                case 5:
                    return "Five";
                default:
                    return Integer.toString(number);
            }
        }
        // Handle the case when number is not greater than 0
        return "ERROR: Invalid parameter value. Number must not be negative.";
    }

    protected List<AddOn> getAddOns() {
        return addOns;
    }

    /**
     * Calculates the subtotal cost of the itinerary add-ons.
     *
     * @return The subtotal cost of the itinerary add-ons.
     */
    private int calculateAddOnsSubTotal() {
        int subTotal = 0;
        // Iterate through each add-on and accumulate its cost
        for (AddOn addOn : addOns) {
            subTotal += addOn.getCost();
        }
        return subTotal;
    }

    /**
     * Calculates the total cost of activities in an itinerary, including base
     * costs and costs of the activities and associated add-ons.
     *
     * @return The total cost of activities.
     */
    private int calculateSubTotal() {
        int activitySubTotal = 0;
        int addOnSubTotal = 0;
        // Iterate through each activity
        for (Activity activity : activities) {
            // Accumulate base cost of the activity
            activitySubTotal += activity.getBaseCost();
            // Get the list of add-ons associated with the activity
            List<AddOn> activityAddOns = activity.getAddOns();
            // Iterate through each add-on of the activity and accumulate its cost
            for (AddOn addOn : activityAddOns) {
                addOnSubTotal += addOn.getCost();
            }
        }
        // Calculate the sub-total, then adjust for attendees
        return ((activitySubTotal + addOnSubTotal) * attendee.getMembers()) / 100;
    }

    /**
     * Calls 3 encapsulated/split methods to perform one task of printing the
     * receipt.
     */
    protected void printReceipt() {
        printReceiptHeader();
        printReceiptBody();
        printReceiptFooter();
    }

    /**
     * Prints the header section of the receipt for the current itinerary,
     * including client information, reference, date, activities count,
     * attendees count, and the cost for the entire itienrary.
     */
    private void printReceiptHeader() {
        incuranceCheck();
        String initialSurname = getAttendeeInitialSurname();
        String reference = getId();
        double itineraryCost = applyDiscount();
        String activitiesCountString = getStringNumber(activities.size());
        String attendeesCountString = getStringNumber(attendee.getMembers());

        System.out.println("+===============================================================+");
        System.out.println("| Client: " + initialSurname + "\t\t\tRef: " + reference + "\t\t|");
        System.out.println("| Date: " + day + "/" + month + "/" + year + "\t\t\t\t\t\t|");
        System.out.println("| Activities: " + activitiesCountString + "\t\t\tAttendees: "
                + attendeesCountString + "\t|");//do i store it somewhere first?
        System.out.println("|\t\t\t\t\t\t\t\t|");
        System.out.println("| Cost:\t£" + itineraryCost + "\t\t\t\t\t\t\t|");
        System.out.println("|\t\t\t\t\t\t\t\t|");
    }

    /**
     * Prints the detailed breakdown of the itinerary, including costs,
     * discounts, and add-on details. It calculates various costs, considers
     * discounts, and provides a detailed breakdown of the itinerary and
     * associated add-ons.
     */
    private void printReceiptBody() {
        int itineraryAddOnSubTotal = calculateAddOnsSubTotal() / 100 * attendee.getMembers();
        int subTotal = calculateSubTotal();
        System.out.println("|\t\t\tCost breakdown\t\t\t\t|");
        System.out.println("|\t\t\t\t\t\t\t\t|");

        printReceiptItinearryAddOns(itineraryAddOnSubTotal);

        int i = 0;
        System.out.println("| Activities\t\t\t\t" + "Sub-Total:\t£" + subTotal + "\t|");
        for (Activity activity : activities) {
            i++;
            /*connot reuse the calculateSubTotal() bcause in here it is separete activities 
            and will be resetted after each loop*/
            int activityAddOnSubTotal = 0;
            System.out.println("| " + i + ". " + activity.getTitle()
                    + " @ £" + activity.getBaseCost() / 100 + " x" + attendee.getMembers()
                    + " = £" + (activity.getBaseCost() * attendee.getMembers()) / 100 + "\t\t\t|");
            activityAddOnSubTotal += activity.getBaseCost();

            List<AddOn> activityAddOns = activity.getAddOns();
            for (AddOn addOn : activityAddOns) {
                System.out.println("|\tAddOn: " + addOn.getName()
                        + " @ £" + addOn.getCost() / 100 + " x" + attendee.getMembers()
                        + " = £" + (addOn.getCost() * attendee.getMembers()) / 100 + "\t\t\t\t|");
                activityAddOnSubTotal += addOn.getCost();
            }
            System.out.println("|\t\t\t\t\tSub-Total:\t£"
                    + activityAddOnSubTotal * attendee.getMembers() / 100 + "\t|");
        }
        System.out.println("|\t\t\t\t\t\t\t\t|");
    }

    /**
     * This method will be called inside printReceiptBody() and determines if
     * itinerary add-ons part will be printed or not depending on the itinerary
     * has add-ons registered by user or not.
     *
     * @param itineraryAddOnSubTotal The sub-total value to be printed.
     */
    private void printReceiptItinearryAddOns(int itineraryAddOnSubTotal) {
        if (!addOns.isEmpty()) {
            System.out.println("| Itinerary Add-ons\t\t\tSub-Total:\t£" + itineraryAddOnSubTotal + "\t|");
            for (AddOn addOn : addOns) {
                System.out.println("| - " + addOn.getName()
                        + " @ £" + addOn.getCost() / 100 + " x " + attendee.getMembers()
                        + " = £" + (addOn.getCost() / 100) * attendee.getMembers() + "\t\t\t\t\t|");
            }
            System.out.println("|\t\t\t\t\t\t\t\t|");
        }
    }

    /**
     * Prints the footer section of the receipt for the current itinerary,
     * including the total discount and a closing statement. It calculates the
     * total discount in pounds and formats it to display up to 1 digit after
     * the decimal point.
     */
    private void printReceiptFooter() {
        double totalDiscount = calculateDiscountInPounds();
        // Format the total discount to display up to 1 digit after the decimal point
        String formattedTotalDiscount = String.format("%.1f", totalDiscount);
        System.out.println("| " + (100 - getDiscountMultiplier()) + "% Discount"
                + "\t\t\t\tTotal discount:\t£" + formattedTotalDiscount + "\t|");
        System.out.println("+===============================================================+");
    }
}
