/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The "ActivityPlannerApp" class represents an application for planning and
 * managing activities and itineraries.
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
    private final Activity paperBridge = new Activity(
            500,
            "Building a bridge from paper",
            1,
            "Let's build a bridge from paper!",
            "Teesside University",
            "16th Dec 15:00",
            2,
            false,
            "PBR-01"
    );
    private final Activity assaultCourse = new Activity(
            9000,
            "SAS-style assault courses",
            2,
            "Become stronks",
            "Saltburn",
            "15th Dec 7:00",
            15,
            true,
            "SAS-01"
    );
    private final Activity cookery = new Activity(
            1000,
            "Cookery class",
            3,
            "Let bro cook",
            "Teesside University",
            "9th Dec 14:00",
            3,
            false,
            "CKR-01"
    );
    private final Activity hiking = new Activity(
            3000,
            "Hiking and nature walks",
            4,
            "Hiking time!",
            "North York Moors Park",
            "8th Dec 14:00",
            6,
            true,
            "HIK-01"
    );

    /**
     * Activity planner app constructor initialises activities list and adds
     * available activities to the list.
     */
    public ActivityPlannerApp() {
        this.activities = new ArrayList<>();
        this.addActivity(paperBridge);
        this.addActivity(assaultCourse);
        this.addActivity(cookery);
        this.addActivity(hiking);
    }

    /**
     * Adds activity to the list of available activities to be picked by users.
     *
     * @param activity The activity to be added to the list.
     */
    private void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * Displays information about each activity in the itinerary. Prints details
     * such as title, number, description, location, date and time, duration,
     * and base cost for each activity.
     */
    private void displayActivities() {
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
    protected void addItinenary(Itinerary itinerary) {
        iteneraries.add(itinerary);
    }

    /**
     * Simple getter method for the activities catalogue array-list.
     *
     * This will be used in
     * {@code List<Activity> activities = plannerApp.getActivities();} to avoid
     * a compiler error due to {@link #activities} not being initialised where
     * it's declared.
     *
     * @return The list of available activities.
     */
    protected List<Activity> getActivities() {
        return activities;
    }

    /**
     * Writes the given list of itineraries to a text file in the specified
     * format.
     *
     * @param itinerary The itinerary instance to be written to the file.
     * @param filePath The path of the file where itineraries will be saved.
     */
    public static void writeItineraryToFile(Itinerary itinerary, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(formatItinerary(itinerary));
            writer.newLine(); // Add a new line for each itinerary
            System.out.println("Itinerary have been successfully written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing itinerary to the file: " + e.getMessage()
                    + "Please contact us at IT@excitingactivitis.com");
        }
    }

    /**
     * Formats an itinerary as a string according to the specified requirements.
     *
     * All the information that is required for instantiating an itinerary are
     * stored in the text file by appending information using string builder and
     * tab characters separate each field.
     *
     * IMPORTANT: The codes for activities and add-ons will be printed like this
     * example: HIK-01:INS;ACC,TEA&CKR-01:TRA;ACC,TEA
     *
     * '&' separates different activities ':' separates activities and add-ons
     * ';' separates itinerary add-ons and anything before it ',' separates the
     * add-ons of same type
     *
     * These characters are used to identify correct information to instantiate
     * itineraries that will be displayed on GUI.
     *
     * @param itinerary The itinerary to be formatted.
     * @return A string representing the formatted itinerary.
     */
    private static String formatItinerary(Itinerary itinerary) {
        StringBuilder formattedItinerary = new StringBuilder();

        // Append reference, date, attendee name, total cost, and total activities
        formattedItinerary.append(itinerary.getId()).append('\t');
        formattedItinerary.append(itinerary.getDate()).append('\t');
        formattedItinerary.append(itinerary.getAttendee().getName()).append('\t');
        formattedItinerary.append(itinerary.getFinalCost()).append('\t');
        formattedItinerary.append(itinerary.getActivities().size()).append('\t');
        formattedItinerary.append(itinerary.getAttendee().getMembers()).append('\t');

        int activitiesSize = itinerary.getActivities().size();
        // Append activity details
//        for (int i = 0; i < itinerary.getActivities().size(); i++) {
//            Activity activity = itinerary.getActivities().get(i);
        int lastActivityIndex = 0;
        for (Activity activity : itinerary.getActivities()) {
            lastActivityIndex++;
            // Append add-ons if present
            List<AddOn> activityAddOns = activity.getAddOns();
            if (!itinerary.getActivities().isEmpty()) {
                formattedItinerary.append(activity.getActivityCode()).append(':');
                if (!activityAddOns.isEmpty()) {
                    for (AddOn addOn : activityAddOns) {
                        formattedItinerary.append(addOn.getCode()).append(',');
                    }
                }

                // Remove the trailing comma
                formattedItinerary.deleteCharAt(formattedItinerary.length() - 1);
            }
            if (lastActivityIndex < activitiesSize) {
                formattedItinerary.append('&'); // Not the last activity, append "&"
            }
        }

        // Check if it's the last activity in the list
        if (lastActivityIndex == activitiesSize) {
            //Appends 
            appendItineraryAddOns(itinerary, formattedItinerary);
            formattedItinerary.append('\t'); // Last activity, append "\t"
        }
        return formattedItinerary.toString();
    }

    /**
     * This sub-method is called inside formatItinerary() when there is no
     * activity left to be appended. Itinerary add-ons will be appended to the
     * text file and the process will terminate.
     *
     * @param itinerary The itinerary instance that is being read and stored.
     * @param formattedItinerary The StringBuilder instance in work.
     */
    public static void appendItineraryAddOns(Itinerary itinerary, StringBuilder formattedItinerary) {
        List<AddOn> itineraryAddOns = itinerary.getAddOns();
        if (!itineraryAddOns.isEmpty()) {
            formattedItinerary.append(';');
            for (AddOn addOn : itineraryAddOns) {
                formattedItinerary.append(addOn.getCode()).append(",");
            }
            // Remove the trailing comma
            formattedItinerary.deleteCharAt(formattedItinerary.length() - 1);
        }
    }


    /**
     * This method validates user input full-names and output relevant error
     * messages and hints when the the name is entered in the wrong format.
     *
     * The code allows users to proceed only when the first and surname exist
     * with only one space character and surname is less isn't longer than 11
     * letters to prevent expected errors.
     *
     * @param fullName The full-name to be validated.
     * @param scanner The Scanner object in work.
     */
    private static String nameInputValidation(String fullName, Scanner scanner) {
        while (true) {
            // Split the full name into parts using the space character
            String[] nameParts = fullName.split(" ");

            // Check if there are exactly two parts (first name and surname)
            if (nameParts.length == 2) {
                String firstName = nameParts[0];//this line is required
                String surname = nameParts[1];
                if (surname.length() > 11) {
                    System.out.println("Invalid input. Surname must be less than 11 characters long.\n"
                            + "Please enter your name again.");
                    fullName = scanner.nextLine();
                } else {
                    break;//first and surname exist and surname isn't over 11 characters.
                }
            } else {
                System.out.println("Invalid input. (Hint: Firstname<SPACE>Surname)\n"
                        + "Please enter your name again.");
                fullName = scanner.nextLine();
            }
        }
        return fullName;
    }

//    /**
//     * Validates user input for attendee count for mismatch exception to prevent
//     * the program from crashing when user enters letters in nextInt().
//     *
//     * @param scanner The Scanner object to read user input from.
//     * @return The valid integer entered by the user.
//     */
//    public static int attendeeCountValidation(Scanner scanner) {
//        int numbers = 0;
//        boolean isValidInput = false;
//        while (!isValidInput) {
//            try {
//                numbers = scanner.nextInt();
//                scanner.nextLine();
//                isValidInput = true; // Break the loop if input is successful
//            } catch (InputMismatchException e) {
//                System.out.println("Please enter a valid integer.");
//                scanner.next();
//            }
//        }
//        return numbers;
//    }
    /**
     * Prompts the user to enter a valid email address using a while loop.
     *
     * @param scanner The Scanner object for input.
     * @return The valid email address entered by the user.
     */
    private static String getEmailWithValidation(Scanner scanner) {
        String email;

        while (true) {
            email = scanner.nextLine();

            if (isValidEmail(email)) {
                break; // Exit the loop if the email is valid
            } else {
                System.out.println("Invalid email format. Please enter a valid email:");
            }
        }

        return email;
    }

    /**
     * Validates the email address using a simple regular expression.
     *
     * @param email The email address to validate.
     * @return true if the email is valid, false otherwise.
     */
    private static boolean isValidEmail(String email) {
        // Simple email validation using a regular expression
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    /**
     * Validates user input for personal insurance and converts to Boolean
     * value.
     *
     * 3 styles of answers are allowed for both yes and no. If the input doesn't
     * fall in any of them the input will be deemed as invalid and user will be
     * asked to input again.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The valid user input that was converted to Boolean value.
     */
    private static boolean validateInsurance(Scanner scanner) {
        boolean hasInsurance = false;
        System.out.println("Does the attendee have personal insurance? (Yes/No).");
        String temporaryHasInsurance = scanner.nextLine();
        while (true) {
            if (temporaryHasInsurance.equalsIgnoreCase("Yes")
                    || temporaryHasInsurance.equalsIgnoreCase("Y")
                    || temporaryHasInsurance.equalsIgnoreCase("true")) {
                hasInsurance = true;
                System.out.println();
                break;
            } else if (temporaryHasInsurance.equalsIgnoreCase("No")
                    || temporaryHasInsurance.equalsIgnoreCase("N")
                    || temporaryHasInsurance.equalsIgnoreCase("false")) {
                hasInsurance = false;
                System.out.println();
                break;
            } else {
                System.out.println("Please enter a valid input (Yes/No).");
                temporaryHasInsurance = scanner.nextLine();
            }
        }
        return hasInsurance;
    }

    /**
     * Reusable block of code for catching input mismatch exception for
     * nextInt() method instead of writing try-catch many times.
     *
     * @param scanner The Scanner object to read user input from.
     * @return
     */
    private static int protectedNextInt(Scanner scanner){
        int numbers = 0;
        boolean isValidInput = false;
        while (!isValidInput || numbers < 0) {
            try {
                numbers = scanner.nextInt();
                scanner.nextLine();
                isValidInput = true; // Break the loop if input is acceptable
                if(numbers < 0){
                    System.out.println("Please enter a valid value");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                scanner.next();
            }
        }
        return numbers;
    }

    /**
     * The main method to interact with the Activity Planner application.
     *
     * It provides a simple text-based menu for users to choose from various
     * options. Users can display all activities, create an itinerary, or exit
     * the application. The method uses a Scanner object to read user input and
     * performs the corresponding actions based on the user's choice.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Creates an instance of ActivityPlannerApp
        ActivityPlannerApp plannerApp = new ActivityPlannerApp();
        Scanner scanner = new Scanner(System.in);

        OUTER:
        while (true) {
            System.out.println("1. Display All Activities");
            System.out.println("2. Create Itinerary");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");

            int choice = protectedNextInt(scanner);

            switch (choice) {
                case 1:
                    plannerApp.displayActivities();
                    break;
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

    /**
     * Creates a new itinerary, gathers attendee information, adds activities
     * and add-ons, and prints the itinerary receipt.
     *
     * This method was carefully developed to deliver a better user experience
     * by displaying relevant lines of output as a block and putting one line
     * gap for moving on to the next steps of the process.
     *
     * @param plannerApp The ActivityPlannerApp instance.
     * @param scanner The Scanner object to read user input from.
     */
    private static void createItinerary(ActivityPlannerApp plannerApp, Scanner scanner) {
        System.out.println("Creating Itinerary...");
        Itinerary itinerary = new Itinerary();
        System.out.println();

        //Gather attendee information (then sell it to third party companies)
        System.out.println("Enter your full name (First Surname): ");
        String attendeeName = scanner.nextLine();
        attendeeName = nameInputValidation(attendeeName, scanner);
        System.out.println();

        System.out.println("Enter attendee email: ");
        String attendeeEmail = getEmailWithValidation(scanner);
        System.out.println();

        boolean hasInsurance = validateInsurance(scanner);

        System.out.println("Enter the number of attendees: ");
        int numbers = 0;
        while(true){
            numbers = protectedNextInt(scanner);
            if(numbers >= 100){
                System.out.println("Sorry! We can only offer you to add up to 99 attendees for one itinerary.\n"
                        + "Please enter the number of attendees again: ");
            } else {
                break;
            }
        }
        System.out.println();

        Attendee attendee = new Attendee(attendeeName, attendeeEmail,
                hasInsurance, numbers);
        itinerary.setAttendee(attendee);

        // Add activities to the itinerary
        activityManager(plannerApp, scanner, itinerary);

        itineraryAddonManager(scanner, itinerary);

        //prints receipt
        itinerary.printReceipt();
        
        String filePath = "ItineraryDatabase.txt";
        //prints itinerary out to the txt file
        writeItineraryToFile(itinerary, filePath);

    }

    /**
     * Activity manager manages activities as it's in the name and calls
     * activity add-on manager method. User can interact with the program to
     * chose their activities and activity add-ons through this method.
     *
     * I tried a different method for keeping the output window cleaner for a
     * better usability with setting askInput Boolean so individual lines of
     * failed input result won't be asking for re-input and instead it is all
     * managed in 1 place which makes it easier to maintain it as well.
     *
     * @param plannerApp The plannerApp instance that stores the activities
     * catalogue list.
     * @param scanner The Scanner object to read user input from.
     * @param itinerary The itinerary instance that is being worked on.
     */
    private static void activityManager(ActivityPlannerApp plannerApp, Scanner scanner, Itinerary itinerary) {
        plannerApp.displayActivities();
        System.out.print("Enter activity number to add to the itinerary \n"
                + "or enter 0 to proceed to the next step: ");//would be better if user could add activity addons after adding one activity
        int activityNumber = protectedNextInt(scanner);
        boolean repeatInput = false;
        while (true) {//print first and ask options using do...while?//what?

            if (repeatInput == true) {
                System.out.print("Enter activity number to add to the itinerary\n"
                        + "or enter 0 to proceed to the next step: ");
                activityNumber = protectedNextInt(scanner);
            }

            //user cant exit without taking any activities
            if (activityNumber == 0 && !itinerary.getActivities().isEmpty()) {
                break;
            }
            // Retrieve the selected activity from the plannerApp
            List<Activity> activitiesCatalogue = plannerApp.activities;
            if (activityNumber > 0 && activityNumber <= activitiesCatalogue.size()) {

                //grab the activity from catalogue list
                Activity selectedActivity = activitiesCatalogue.get(activityNumber - 1);

                //copy the activity using copy constructor (thx to Steve this thing is so good)
                Activity selectedActivityCopy = new Activity(selectedActivity);

                // Add the selected activity to the itinerary
                itinerary.addActivity(selectedActivityCopy);
                //run the addon manager and store the return value
                activityAddonManager(selectedActivityCopy, scanner);

                System.out.println(selectedActivityCopy.getTitle() + " was added to the itinerary.");
                System.out.println();
//                System.out.println("Add another activity or enter 0 to proceed to the next step:");
//                activityNumber = scanner.nextInt();
//                scanner.nextLine();
                repeatInput = true;
            } else {
                System.out.println("Invalid activity number. Please try again.");
                repeatInput = true;
            }
        }
    }

    /**
     * activityAddonManager is a sub-method to activityManager.
     *
     * User will be asked for an input. The input will be inspected and the
     * add-on will be added if the add-on of user choice exists and there will
     * no duplication in the array list.
     *
     * @param activity The activity instance being worked on (being added an
     * add-on to)
     * @param scanner The Scanner object to read user input from.
     */
    private static void activityAddonManager(Activity activity, Scanner scanner) {
        activity.displayAddOns();
        System.out.println("Please select an add-on for " + activity.getTitle() + ", \n"
                + "or enter 0 to proceed to the next step.");
        System.out.println("Enter an add-on name: ");
        String activityAddOnName = scanner.nextLine();

        while (true) {
            //break if input is 0
            if (activityAddOnName.equals("0")) {
                break;
            } else if (activity.hasAddOn(activityAddOnName)) {
                // Check if an add-on by the same name exists in the activities catalogue arraylist

                // If it exists, add the add-on to the activity
                AddOn selectedAddOn = activity.getAddOnByName(activityAddOnName);

                if (activity.getAddOns().contains(selectedAddOn)) {
                    System.out.println(selectedAddOn.getName() + " add-on already exists "
                            + "in your list! \nPlease enter a valid add-on name or 0 to proceed:");
                    activityAddOnName = scanner.nextLine();
                } else {
                    activity.addAddOn(selectedAddOn);
                    System.out.println(selectedAddOn.getName() + " add-on was added to "
                            + activity.getTitle() + ".");
                    System.out.println();
                    System.out.println("Add another add-on or enter 0 to proceed: ");
                    activityAddOnName = scanner.nextLine();
                }
            } else {
                // If it doesn't exist, provide a hint for input
                System.out.println("Invalid add-on name. Please try again \n"
                        + "or enter 0 to proceed to the next step.");
                activityAddOnName = scanner.nextLine();

            }
        }
    }

    /**
     * itineraryAddonManager is essentially an encapsulation of the big user
     * interface code.
     *
     * This method gets called after user exits activity selection and displays
     * a list of available add-ons and an instruction for what to do next. Once
     * the list and instruction was shown user will get to input their choices.
     *
     * First it gets checked if the the add-on exists in the available list or
     * not, if not it then checks to see if user instructed to exit by entering
     * 0 or not, if not the method concludes user entered an invalid input and
     * user will be asked to input a choice again.
     *
     * @param scanner The Scanner object in work.
     * @param itinerary The itinerary instance that is being worked on.
     */
    private static void itineraryAddonManager(Scanner scanner, Itinerary itinerary) {
        itinerary.displayAddOn();
        System.out.print("Please select add-on(s) for your itinerary\n"
                + "or enter 0 to proceed to the next step:");
        String itineraryAddOnName = scanner.nextLine();

        while (true) {
            if (itineraryAddOnName.equals("0")) {
                break;
            } else if (itinerary.hasAddOn(itineraryAddOnName)) {
                // If it exists, add the add-on to the activity
                AddOn selectedAddOn = itinerary.getAddOnByName(itineraryAddOnName);
                if (!itinerary.getAddOns().contains(selectedAddOn)) {
                    itinerary.addAddOn(selectedAddOn);
                    System.out.println(selectedAddOn.getName() + " add-on was added to the itinerary.");
                    System.out.println();
                    System.out.println("Add another itinerary add-on or enter 0 to proceed");
                    itineraryAddOnName = scanner.nextLine();
                } else {
                    System.out.println("This add-on already exists in your itinerary!\n"
                            + "Please choose another add-on or enter 0 to proceed to the next step:");
                    itineraryAddOnName = scanner.nextLine();
                }
            } else {
                // If it doesn't exist, provide a hint for input
                System.out.println("Invalid add-on name. Please try again \n"
                        + "or enter 0 to proceed to the next step.");
                itineraryAddOnName = scanner.nextLine();
            }
        }
        // Display itinerary and receipt
        System.out.println("Printing receipt...\n");
    }

//    /**
//     * Reads itineraries from a text file and adds them to the itineraries list.
//     *
//     * @param filePath The path of the file containing itinerary data.
//     */
//    public void readItinerariesFromFile(String filePath) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                Itinerary itinerary = parseItineraryFromString(line);
//                if (itinerary != null) {
//                    iteneraries.add(itinerary);
//                }
//            }
//
//            System.out.println("Itineraries have been successfully loaded from the file.");
//        } catch (IOException e) {
//            System.err.println("Error reading itineraries from the file: " + e.getMessage());
//        }
//    }
//    
//    /**
//     * Parses a string representation of an itinerary and creates an Itinerary object.
//     *
//     * @param itineraryString The string representation of an itinerary.
//     * @return The Itinerary object created from the string, or null if parsing fails.
//     */
//    private Itinerary parseItineraryFromString(String itineraryString) {
//        try {
//            // Split the string by tabs to get individual fields
//            String[] fields = itineraryString.split("\t");
//
//            // Extract data from fields (adjust indices as needed)
//            String reference = fields[0];
//            String date = fields[1];
//            String attendeeName = fields[2];
//            double totalCost = Double.parseDouble(fields[3]);
//            int totalActivities = Integer.parseInt(fields[4]);
//
//            // Extract activity details
//            List<Activity> activities = new ArrayList<>();
//            for (int i = 0; i < totalActivities; i++) {
//                // Extract activity data from fields (adjust indices as needed)
//                String activityDetails = fields[5 + i];
//                Activity activity = parseActivityFromString(activityDetails);
//                activities.add(activity);
//            }
//
//            // Create and return the Itinerary object
//            Itinerary itinerary = new Itinerary();
//            itinerary.setId(reference);
//            itinerary.setDate(date);
//            itinerary.getAttendee().setName(attendeeName);
//            itinerary.setFinalCost(totalCost);
//            itinerary.setActivities(activities);
//
//            return itinerary;
//        } catch (Exception e) {
//            System.err.println("Error parsing itinerary string: " + e.getMessage());
//            return null;
//        }
//    }
//    
//    /**
//     * Parses a string representation of an activity and creates an Activity object.
//     *
//     * @param activityString The string representation of an activity.
//     * @return The Activity object created from the string, or null if parsing fails.
//     */
//    private Activity parseActivityFromString(String activityString) {
//        try {
//            // Implement parsing logic for activity string
//            // Adjust the logic based on your specific format
//
//            // Example: Split by colon to get activity code and add-ons
//            String[] parts = activityString.split(":");
//            String activityCode = parts[0];
//
//            // Example: Split add-ons by comma
//            String[] addOns = parts[1].split(",");
//
//            // Create and return the Activity object
//            Activity activity = new Activity();
//            activity.setActivityCode(activityCode);
//
//            // Process add-ons and set them in the activity
//            for (String addOn : addOns) {
//                // Implement logic to process each add-on (you may need to split again, etc.)
//                // For example: Add the add-on to the activity's add-ons list
//                activity.addAddOn(new AddOn(addOn));
//            }
//
//            return activity;
//        } catch (Exception e) {
//            System.err.println("Error parsing activity string: " + e.getMessage());
//            return null;
//        }
//    }
}
