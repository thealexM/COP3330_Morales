//Programmer: Alex Morales
//Program Name: Shape
//Date Written: 10/08/2020
//Purpose: The purpose of this assignment is to get familiar with writing basic Java programs and working with git and GitHub.

abstract class Shape {
    
    abstract String getName();             
    
    abstract double getArea();              
}
abstract class Shape2D extends Shape{
}
abstract class Shape3D extends Shape{
    
    abstract double getVolume();            
}

class Square extends Shape2D {              
    
    private static double side = 0;
   
    protected Square(double input) {        
        
        side = input;
    }
    
    @Override
    protected String getName() {
        return "Square";
    }
   
    @Override
    protected double getArea() {
        
        return side*side;
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
       
        return "Triangle";
    }
    
    @Override
    protected double getArea() {
        
        return 0.5*width*length;
    }
}

class Circle extends Shape2D {
   
    private static double radius = 0;
   
    protected Circle(double input) {
        
        radius = input;
    }
   
    @Override
    protected String getName() {
        
        return "Circle";
    }
    
    @Override
    protected double getArea() {
        
        return Math.PI*radius*radius;
    }
}

class Cube extends Shape3D {
    private static double side = 0;
   
    protected Cube(double input) {
       
        side = input;
    }
    @Override
   
    protected String getName() {
       
        return "Cube";
    }
    @Override
    
    protected double getArea() {
        return 6*side*side;
    }
    @Override
    protected double getVolume() {
        return side*side*side;
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
       
        return "Pyramid";
    }
   
    @Override
    protected double getArea() {
       
        return baseArea(width,length)+ sideArea1(length, width, height)+sideArea2(length, width, height);
    }

    @Override
    protected double getVolume() {
        
        return height*width*length/3.0;
    }
   
    private double baseArea(double width, double length){                   //Finds the area of the pyramid base
        
        return width*length;
    }
    
    private double sideArea1(double length, double width, double height){   
     
        return width*Math.sqrt(height*height+0.25*length*length);
    }
    private double sideArea2(double length, double width, double height){   
       
        return length*Math.sqrt(height*height+0.25*width*width);
    }
}

class Sphere extends Shape3D {
   
    private static double radius = 0;
   
    protected Sphere(double input) {
       
        radius = input;
    }
   
    @Override
    protected String getName() {
        
        return "Sphere";
    }
    
    @Override
    protected double getArea() {
        
        return 4.0*Math.PI*radius*radius;
    }
   
    @Override
    protected double getVolume() {
        
        return 4.0/3.0*Math.PI*radius*radius*radius;
    }
}
