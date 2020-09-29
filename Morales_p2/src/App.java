//Programmer:Alex Morales
//Program Name: BMI Program
//Purpose: The purpose of this assignment is to get myself familiar with writing basic Java programs and working with git and  GitHub.
//Date Written: 09/28/2020

import java.util.ArrayList;
import java.util.Scanner;


public class App {


    public static void main(String[] args) {


        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();



        while (moreInput()) {


            double height = getUserHeight();

            double weight = getUserWeight();



            BodyMassIndex bmi = new BodyMassIndex(height, weight);

            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }

    private static boolean moreInput() {


        System.out.println("\n\nDo you want to make an entry?\n\n\nEnter Y for Yes or N for No.");


        Scanner in = new Scanner(System.in);


        String Buffer = in.nextLine();


        return errorCheck(Buffer);
    }

    private static double getUserHeight() {


        System.out.println("Enter your height in inches.");


        Scanner in = new Scanner(System.in);


        double Buffer = in.nextDouble();


        return errorCheck(Buffer, 1);
    }

    private static double getUserWeight() {


        System.out.println("Enter your weight in pounds.");


        Scanner in = new Scanner(System.in);


        double Buffer = in.nextDouble();


        return errorCheck(Buffer, 2);
    }

    private static void displayBmiInfo(BodyMassIndex bmi) {


        System.out.printf("\nYour index of %.1f means you are %s", bmi.index, bmi.category);
    }


    private static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData) {

        double average = calculateAverage(bmiData);


        System.out.printf("\nThe average BMI of yours was %.1f.", average);
    }

    private static Boolean errorCheck(String Buffer){

        if(Buffer.matches("Y")) return true;
        if(Buffer.matches("N")) return false;


        screenClear();


        return moreInput();
    }

    private static double errorCheck(double Buffer, int Version){
        
        if(Buffer > 0) return Buffer;

        screenClear();

        if(Version == 1) return getUserHeight();


        return getUserWeight();
    }

    private static double calculateAverage(ArrayList<BodyMassIndex> bmiData){


        double summation = 0;


        for(int i=0; i<bmiData.size(); i++) summation = summation + bmiData.get(i).index;


        return Math.round(10.0*summation/bmiData.size())/10.0;

    }

    private static void screenClear(){
        System.out.println("\n\n\n\nThat entry is invalid.\n\n\n\n");
    }
}