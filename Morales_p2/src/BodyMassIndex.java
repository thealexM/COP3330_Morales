//Programmer:Alex Morales
//Program Name: BMI Program
//Purpose: The purpose of this assignment is to get myself familiar with writing basic Java programs and working with git and  GitHub.
//Date Written: 09/28/2020


public class BodyMassIndex {


    public double index;

    public String category;

    public BodyMassIndex(double height, double weight) {

        index = FindIndex(height, weight);

        category = FindCategory(index);
    }

    public String FindCategory(double index){

        if(index < 18.5)        return "undernourished.";

        if(index < 25)          return "regular.";

        if(index < 30)          return "overweight.";
                                return "obese.";

    }

    public double FindIndex(double height, double weight){

        return Math.round(7030.0 * weight / (height * height))/10.0;
    }
}