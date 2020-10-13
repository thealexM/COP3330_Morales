//Programmer: Alex Morales
//Program Name: Shape
//Date Written: 10/08/2020
//Purpose: The purpose of this assignment is to get familiar with writing basic Java programs and working with git and GitHub.

abstract class Shape {
    
    abstract String getName();             //returns a shape name
    
    abstract double getArea();             //returns the calculated area  




}
abstract class Shape2D extends Shape{
}
abstract class Shape3D extends Shape{
    
    abstract double getVolume();            
}                                                  // Creates assignments for inputs statements as variables

class Square extends Shape2D {              
    
    private static double side = 0;
   
    protected Square(double input) {        
        
        side = input;
    }
    
    @Override
    protected String getName() {                  // returns the name of shape square 
        return "Square";
    }
   
    @Override
    protected double getArea() {
        
        return side*side;                        // returns area of square
    }
}

class Triangle extends Shape2D {
    
    private static double length = 0;
    
    private static double width = 0;
   
    protected Triangle(double input1, double input2) {
        length = input1;
        width = input2;
    }
   
    @Override
    protected String getName() {
       
        return "Triangle";                    //returns the name of shape Triangle
    }
    
    @Override
    protected double getArea() {
        
        return 0.5*width*length;             //returns the area of a Triangle
    }
}

class Circle extends Shape2D {
   
    private static double radius = 0;
   
    protected Circle(double input) {
        
        radius = input;
    }
   
    @Override
    protected String getName() {
        
        return "Circle";                  //returns the name of the shape Circle
    }
    
    @Override
    protected double getArea() {
        
        return Math.PI*radius*radius;      //returns the area of the Circle
    }
}

class Cube extends Shape3D {
    private static double side = 0;
   
    protected Cube(double input) {
       
        side = input;
    }
    @Override
   
    protected String getName() {
       
        return "Cube";                      //returns the name of the shape Cube
    }
    @Override
    
    protected double getArea() {
        return 6*side*side;                 //returns the area of the Cube
    }
    @Override
    protected double getVolume() {
        return side*side*side;               //returns the value of the volume within the Cube
    }
}

class Pyramid extends Shape3D {             
   
    private static double height = 0;
   
    private static double width = 0;
   
    private static double length = 0;
   
    protected Pyramid(double input1, double input2, double input3) {
        length = input1;
        width = input2;
        height = input3;
    }
    
    @Override
    protected String getName() {
       
        return "Pyramid";                        //returns the name of the shape Pyramid
    }
   
    @Override
    protected double getArea() {
       
        return baseArea(width,length)+ sideArea1(length, width, height)+sideArea2(length, width, height);        //returns the entire are of the Pyramid
    }

    @Override
    protected double getVolume() {
        
        return height*width*length/3.0;                             //returns the value of the volume of the pyramid
    }
   
    private double baseArea(double width, double length){                   
        
        return width*length;                                         //returns the base area of the pyramid
    }
    
    private double sideArea1(double length, double width, double height){   
     
        return width*Math.sqrt(height*height+0.25*length*length);     //returns the first area of the side of the pyramid
    }
    private double sideArea2(double length, double width, double height){   
       
        return length*Math.sqrt(height*height+0.25*width*width);        //returns of the second area of the side of the pyramid
    }
}

class Sphere extends Shape3D {
   
    private static double radius = 0;
   
    protected Sphere(double input) {
       
        radius = input;
    }
   
    @Override
    protected String getName() {
        
        return "Sphere";              //returns the name of the shape Sphere
    }
    
    @Override
    protected double getArea() {
        
        return 4.0*Math.PI*radius*radius;       //returns the area of the Sphere
    }
   
    @Override
    protected double getVolume() {
        
        return 4.0/3.0*Math.PI*radius*radius*radius;    //returns the value of the volume of the Sphere
    }
}
