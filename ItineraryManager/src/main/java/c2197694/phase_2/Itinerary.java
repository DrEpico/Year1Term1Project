/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_2;

import java.security.SecureRandom;
import java.time.LocalDateTime;
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
    private List<AddOn> addOns;//this is a list for itinerary addons! NOT activity addons!
    private List<AddOn> displayAddOns;
    private String id;
    //private List<String> idList; not used because one itinerary won't have multiple ID's//there will be an idList in itinerary class to handle dupes
    private Attendee attendee;//Not required by specs, i could just set an Attendee type and write getter and setter but having an array list would look more consistant in the main method

    private LocalDateTime now = LocalDateTime.now();
    int day = now.getDayOfMonth();
    int month = now.getMonthValue();
    int year = now.getYear();

    //Add preset itinerary add-ons
    public AddOn accommodation = new AddOn("Accommodation", 2000, "itinerary");
    public AddOn teaBreaks = new AddOn("Tea break", 700, "itinerary");
    public AddOn lunch = new AddOn("Lunch", 2200, "itinerary");
    
    Scanner scanner = new Scanner(System.in);

    public Itinerary() {
        id = generateId(6);

        activities = new ArrayList<>();
        addOns = new ArrayList<>();
        displayAddOns = new ArrayList<>();

        //idList = new ArrayList<>();
        //idList.add(id); same reason as line 23
//        addAddOn(accommodation);
//        addAddOn(teaBreaks);
//        addAddOn(lunch);
        this.addDisplayAddOns(accommodation);
        this.addDisplayAddOns(teaBreaks);
        this.addDisplayAddOns(lunch);
    }

    public String getId() {
        return id;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        System.out.println(activity.getTitle() + " was added to the itinerary");
    }

    public void clearItinerary() {
        activities.clear();

    }

    public void addDisplayAddOns(AddOn addOn) {
        displayAddOns.add(addOn);
    }
    
    public boolean hasAddOn(String addOnName) {
        for (AddOn addOn : displayAddOns) {
            if (addOn.getName().equalsIgnoreCase(addOnName)) {
                return true;
            }
        }
        System.out.println(displayAddOns.size());
        return false;
    }
    
    public AddOn getAddOnByName(String addOnName) {
        for (AddOn addOn : displayAddOns) {
            if (addOn.getName().equalsIgnoreCase(addOnName)) {
                return addOn;
            }
        }
        return null; // Return null if the add-on with the specified name is not found
    }
    
    public void displayAddOns() {
        for (AddOn addOn : displayAddOns) {
            System.out.println("Title: " + addOn.getName());
            System.out.println("Activity Cost: £" + addOn.getCost() / 100);
            System.out.println("--------------------------------------");
        }
    }

    public boolean containsInsurance() {//insurance is an activity addon
        for (AddOn addOn : addOns) {
            if (addOn.getName().equalsIgnoreCase("Insurance")) {
                return true; // Found an insurance add-on
            }
        }
        return false; // Insurance add-on not found
    }

    public void addAddOn(AddOn addOn) {//adds itinerary addons
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

    public void incuranceCheck() {
        if (attendee.hasInsurance()) {//checks if attendee has personal insurance

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

    public double calculateCost() {//without discounts applied
        double totalCost = 0;
        for (Activity activity : activities) {
            totalCost += (double) activity.calculateCostWithAddOn();//go back to old code
        }
//        for (AddOn addOn : addOns) {
//            totalCost += addOn.getCost();
//        }
        //instead of the code above i use the existing method for calculating itinerary addOn costs per person
        totalCost += (double) calculateAddOnsSubTotal();

        return totalCost * attendee.getMembers(); //without applying discounts
    }

//    private int calculateCost() {
//        int totalCost = 0;
//        for (Activity activity : activities) {
//            totalCost += activity.calculateCostWithAddOn();//go back to old code
//        }
////        for (AddOn addOn : addOns) {
////            totalCost += addOn.getCost();
////        }
//        //instead of the code above i use the existing method for calculating itinerary addOn costs per person
//        totalCost += calculateAddOnsSubTotal();
//        
//        int totalDiscount = calculateDiscountPercentage();
//        double discountPercentage = (100 - (double) totalDiscount) / 100.0;
//        // Calculate the discount amount
//        double totalDiscountAmount = discountPercentage * (double) totalCost;
//        // Subtract the discount amount from the total cost
//        totalCost = totalCost - (int)totalDiscountAmount;
//        return totalCost * attendee.getMembers();
//    }
    public double calculateDiscountInPounds() {
        double totalCost = calculateCost();
        int discountRate = calculateDiscountPercentage();
        double discountPercentage = (100 - (double) discountRate) / 100.0;
        // Calculate the discount amount
        double totalDiscountAmount = discountPercentage * (double) totalCost;
        // Subtract the discount amount from the total cost
//        totalDiscountAmount = totalCost - (int) totalCost;
        return totalDiscountAmount / 100;
    }

    public double applyDiscount() {
        double finalCost = calculateCost();
        double discountAmount = calculateDiscountInPounds();
        finalCost = finalCost / 100 - discountAmount;
        return finalCost;
    }

    private int calculateDiscountPercentage() {//splitted the discount calculatin logic from total cost calculation
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

        int totalDiscount = 100 - attendeeDiscount;  //changing it so it outputs the discount percentage (e.g. 5%)
        totalDiscount = (totalDiscount * (100 - activityDiscount)) / 100;
        return totalDiscount;
//        int discountPercentage = attendeeDiscount + activityDiscount;
//        return discountPercentage;
    }

//    public int displayFinalCost() {//For phase 2 this method is not relevant as the calculateCost() in line 83 knows the attendees count and displays 
//        int itineraryCost = calculateCost() * attendee.getMembers();
//        /*= calculatedCost * attendee.getMembers();*/ //price multiplied by members logic diabled for now //not disabled anymore
////        System.out.println("Itinerary cost for " + attendee.getName() + ": £" + itineraryCost / 100);
////        clearItinerary();//As this method will be the last operation in an itenerary, calling displayCost() method will trigger clearing all the lists so issues with price calculation will not happen
//        return itineraryCost;
//    }
    private String generateId(int length) {//generate random string//this function is private and there is a getter for it
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
    public String getAttendeeInitialSurname() {//I use it 
//        List<String> result = new ArrayList<>();
//        for(Attendee attendee : attendeeList){//
        String fullName = attendee.getName();//gets attendee's fullname, for example Josh Hutchinson 
        String[] fullNameArray = fullName.split(" ");//Splits fullname into 2 members of a String type array
        String initial = String.valueOf(fullNameArray[0].charAt(0));//It is supposed to return the first letter from the String array ("J" in this example)
        String surname = fullNameArray[1];//surname is the whole second member of the String array
        String initialSurname = initial.concat(" " + surname);//concat both initial and surname variables to get initial<SPACE>surname outcome
//        System.out.println(initialSurname);//test line
//            result.add(initialSurname);           
//        }
//        return result;
        return initialSurname;
    }

    //this method is reused in receipt generation for 2 similar processes (activity and attendee count)
    public String getStringNumber(int number) {//pass an integer to get a String representing that number if it is 1-5
        if (number > 0) {//0 attendee or 0 activities make no sense as an itinerary so it will be disregarded and handled as input error  
            switch (number) {//returns One to Five if parameter is 1-5 and returns string digits if parameter value is bigger than 5
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
        return "ERROR: invalid parameter value";//attendee or activities can't be 0 or less
    }

    private int calculateAddOnsSubTotal() {//for displaying the itinerary addons subtotal
        int subTotal = 0;
        for (AddOn addOn : addOns) {
            subTotal += addOn.getCost();
        }
        return subTotal /* attendee.getMembers()*/;//commented out the getMembers() because it kinda ruins the entire calculation logic
    }

//    public void displayAddOns() {
//        for (AddOn addOn : addOns) {
//            System.out.println("Title: " + addOn.getName());
//            System.out.println("Activity Number: " + addOn.getCost());
//            System.out.println("--------------------------------------");
//        }
//    }
    private int calculateSubTotal() {//for displaying the whole activities subtotal including addons
        int activitySubTotal = 0;
        int addOnSubTotal = 0;
        for (Activity activity : activities) {
            activitySubTotal += activity.getBaseCost();
            List<AddOn> activityAddOns = activity.getAddOns();//nice debug fix me :3
            for (AddOn addOn : activityAddOns) {
                addOnSubTotal += addOn.getCost();
            }
        }
        return ((activitySubTotal + addOnSubTotal) * attendee.getMembers()) / 100;
    }

//    public void printReceipt() {
//    String initialSurname = getAttendeeInitialSurname();
//    String reference = getID();
//    int itineraryCost = calculateCost();
//    String activitiesCountString = getStringNumber(activities.size());
//    String attendeesCountString = getStringNumber(attendee.getMembers());
//    int itineraryAddOnSubTotal = calculateAddOnsSubTotal();
//
//    final String DOUBLE_LINE = "+===============================================================+";
//    final String LINE_FORMAT = "| %-61s |%n";  // Adjust the width (70)
//    final String SUBTOTAL_FORMAT = "| %-40s Sub-Total: £%-9.2f |%n";  // Adjust the width (60) based on your needs
//
//    System.out.println(DOUBLE_LINE);
//    System.out.printf(LINE_FORMAT, "Client: " + initialSurname + "\t\t\tRef: " + reference);
//    System.out.printf(LINE_FORMAT, "Date: " + day + "/" + month + "/" + year);
//    System.out.printf(LINE_FORMAT, "Activities: " + activitiesCountString + "\t\t\tAttendees: " + attendeesCountString);
//    System.out.printf(LINE_FORMAT, "");
//    System.out.printf(LINE_FORMAT, "Cost:\t£%-56.2f |", itineraryCost / 100.0);  // Adjusted width and alignment
//
//    // Print Cost breakdown
//    System.out.printf(LINE_FORMAT, "\t\t\tCost breakdown");
//    System.out.printf(LINE_FORMAT, "");
//    System.out.printf(SUBTOTAL_FORMAT, "Itinerary Add-ons", itineraryAddOnSubTotal / 100.0);
//    for (AddOn addOn : addOns) {
//        int addOnSubTotal = addOn.getCost() * attendee.getMembers();
//        System.out.printf(LINE_FORMAT, "- " + addOn.getName()
//                + " @ £" + addOn.getCost() / 100 + " x " + attendee.getMembers()
//                + " = £" + addOnSubTotal / 100.0);
//    }
//    // Print Activities
//    int i = 0;
//    int totalActivitySubTotal = 0;
//    for (Activity activity : activities) {
//        i++;
//        int activityAddOnSubTotal = 0;
//        int activitySubTotal = activity.getBaseCost() * attendee.getMembers();
//        totalActivitySubTotal += activitySubTotal;
//
//        System.out.printf(SUBTOTAL_FORMAT, i + ". " + activity.getTitle(), activitySubTotal / 100.0);
//
//        List<AddOn> activityAddOns = activity.getAddOns();
//        for (AddOn addOn : activityAddOns) {
//            int addOnSubTotal = addOn.getCost() * attendee.getMembers();
//            activityAddOnSubTotal += addOnSubTotal;
//
//            System.out.printf(LINE_FORMAT, "\tAddOn: " + addOn.getName()
//                    + " @ £" + addOn.getCost() / 100.0 + " x " + attendee.getMembers()
//                    + " = £" + addOnSubTotal / 100.0);
//        }
//
//        System.out.printf(SUBTOTAL_FORMAT, "", activityAddOnSubTotal / 100.0);
//    }
//
//    // Print Total and Discount
//    int totalDiscount = calculateDiscount();
//    double totalDiscountValue = ((double) totalDiscount / 100) * itineraryCost;
//    System.out.printf(LINE_FORMAT, "");
//    System.out.printf(LINE_FORMAT, " " + totalDiscount + "% Discount\t\t\tTotal discount: £%-9.2f", totalDiscountValue / 100.0);
//    System.out.println(DOUBLE_LINE);
//    System.out.println((double) totalDiscount / 100);
//}
    public void printReceipt() {
        incuranceCheck();
        String initialSurname = getAttendeeInitialSurname();
        String reference = getId();
        double itineraryCost = applyDiscount();
        String activitiesCountString = getStringNumber(activities.size());
        String attendeesCountString = getStringNumber(attendee.getMembers());
        int itineraryAddOnSubTotal = calculateAddOnsSubTotal() / 100 * attendee.getMembers();
        int subTotal = calculateSubTotal();

        System.out.println("+===============================================================+");
        System.out.println("| Client: " + initialSurname + "\t\t\tRef: " + reference + "\t\t|");
        System.out.println("| Date: " + day + "/" + month + "/" + year + "\t\t\t\t\t\t|");
        System.out.println("| Activities: " + activitiesCountString + "\t\t\tAttendees: " + 
                attendeesCountString + "\t|");//do i store it somewhere first?
        System.out.println("|\t\t\t\t\t\t\t\t|");
        System.out.println("| Cost:\t£" + itineraryCost + "\t\t\t\t\t\t\t|");
        System.out.println("|\t\t\t\t\t\t\t\t|");
        System.out.println("|\t\t\tCost breakdown\t\t\t\t|");
        System.out.println("|\t\t\t\t\t\t\t\t|");
        System.out.println("| Itinerary Add-ons\t\t\tSub-Total:\t£" + itineraryAddOnSubTotal + "\t|");
        for (AddOn addOn : addOns) {
            System.out.println("| - " + addOn.getName()
                    + " @ £" + addOn.getCost() / 100 + " x " + attendee.getMembers() +
                    " = £" + itineraryAddOnSubTotal + "\t\t\t\t|");
        }
        System.out.println("|\t\t\t\t\t\t\t\t|");
        int i = 0;
        int activitySubTotal = 0;
        int addOnSubTotal = 0;//SUBTOTAL CALCULATIONS NEED TO BE OUTSIDE OF THIS METHOD, SOMETHING LIKE THE FOR LOOP NEST BELOW BUT FOR PRICES. GOODLUCK TOMORROW ME

        System.out.println("| Activities\t\t\t\t" + "Sub-Total:\t£" + subTotal + "\t|");
        for (Activity activity : activities) {
            i++;
            int activityAddOnSubTotal = 0;//i cant reuse the calculateSubTotal() bcause in here it is separete activities and will be resetted after each loop
//            activitySubTotal += activity.getBaseCost();
            System.out.println("| " + i + ". " + activity.getTitle() + 
                    " @ £" + activity.getBaseCost() / 100 + " x" + attendee.getMembers() + 
                    " = £" + (activity.getBaseCost() * attendee.getMembers()) / 100 + "\t\t\t|");
            activityAddOnSubTotal += activity.getBaseCost();

            List<AddOn> activityAddOns = activity.getAddOns();
//            System.out.println("Number of add-ons: " + addOns1.size());//debug line
            for (AddOn addOn : activityAddOns) {
                System.out.println("|\tAddOn: " + addOn.getName() + 
                        " @ £" + addOn.getCost() / 100 + " x" + attendee.getMembers() + 
                        " = £" + (addOn.getCost() * attendee.getMembers()) / 100 + "\t\t\t\t|");
                activityAddOnSubTotal += addOn.getCost();
            }
            System.out.println("|\t\t\t\t\tSub-Total:\t£" + 
                    activityAddOnSubTotal * attendee.getMembers() / 100 + "\t|");
        }
        System.out.println("|\t\t\t\t\t\t\t\t|");

        //System.out.println("| " + (100 - calculateDiscount()) + "% Discount\t\t\t\tTotal discount:\t£" + (((1 - (double) calculateDiscount() / 100) * itineraryCost)) / 100 + "\t|");
        double totalDiscount = calculateDiscountInPounds();
        String formattedTotalDiscount = String.format("%.1f", totalDiscount);// Format the total discount to display up to 1 digit after the decimal point
        System.out.println("| " + (100 - calculateDiscountPercentage()) + "% Discount"
                + "\t\t\t\tTotal discount:\t£" + formattedTotalDiscount + "\t|");
        System.out.println("+===============================================================+");

//        System.out.println(calculateDiscount());
//        System.out.println(1 - (double) calculateDiscount() / 100);//debug 
    }
}
