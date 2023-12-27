package c2197694.phase_2;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// */
//
//package b0123456.phase_1;
//
//import b0123456.phase_1.Activity;
//import b0123456.phase_1.AddOn;
//
///**
// *
// * @author b0123456
// */
//public class MainPhase1 {
//    
//    private int calculateCostForAttendee() {//could pass itinerary ID as parameter to point to the certain itinerary instance i wanna calcualte the cost? update: isnt necessary 
//        int baseCost = 0;
//        for (Activity activity : activities) {
//            baseCost += activity.calculateCostWithAddOn();
//        }
//        for (AddOn addOn : itineraryAddOns) {
//            baseCost += addOn.getCost();
//        }
//
//        int attendeeDiscount = 0;
//        if (attendeeList.size() >= 10 && attendeeList.size() < 20) {
//            attendeeDiscount = 5;
//        } else if (attendeeList.size() >= 20) {
//            attendeeDiscount = 8;
//        }
//
//        int activityDiscount = 0;
//        if (activities.size() >= 3 && activities.size() <= 5) {
//            activityDiscount = 5;
//        } else if (activities.size() > 5 && activities.size() <= 6) {
//            activityDiscount = 10;
//        } else if (activities.size() > 6) {
//            activityDiscount = 12;
//        }
//
//        int totalDiscount = 100 - attendeeDiscount;
//        totalDiscount = (totalDiscount * (100 - activityDiscount)) / 100;
//
//        int totalCost = (baseCost * totalDiscount) / 100;
//
//        return baseCost;
//    }
//    
////    public void displayFinalCost() {//make it so it calculates and displays the overal cost for the entire itinerary (impossible)
////        for (Attendee attendee : attendeeList) {
////            displayCost(attendee);
////        }
////    }
//    
//    public static void main(String[] args) {
//        
//    }
//}
