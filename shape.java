
package database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

abstract class Shape {
   abstract double getArea();
   abstract double getPerimeter();
}

class Circle extends Shape {
   private double radius;
   
   public Circle(double radius) {
      this.radius = radius;
   }
   
   public void setRadius(double radius) {
      this.radius = radius;
   }
   
   public double getArea() {
      return Math.PI * radius * radius;
   }
   
   public double getPerimeter() {
      return 2 * Math.PI * radius;
   }
}

class Square extends Shape {
   private double side;
   
   public Square(double side) {
      this.side = side;
   }
   
   public void setSide(double side) {
      this.side = side;
   }
   
   public double getArea() {
      return side * side;
   }
   
   public double getPerimeter() {
      return 4 * side;
   }
}

class Rectangle extends Shape {
   private double width;
   private double height;
   
   public Rectangle(double width, double height) {
      this.width = width;
      this.height = height;
   }
   
   public void setWidth(double width) {
      this.width = width;
   }
   
   public void setHeight(double height) {
      this.height = height;
   }
   
   public double getArea() {
      return width * height;
   }
   
   public double getPerimeter() {
      return 2 * (width + height);
   }
}

class MyFrame extends JFrame {
   private JLabel label;
   private JTextField textField1, textField2, textField3;
   private JButton button1, button2, button3;
   private JComboBox<String> comboBox;
   
   public MyFrame() {
      setLayout(new FlowLayout());
      
      label = new JLabel("Select a shape:");
      add(label);
      
      String[] shapes = {"Circle", "Square", "Rectangle"};
      comboBox = new JComboBox<String>(shapes);
      add(comboBox);
      
      textField1 = new JTextField("0", 5);
      add(textField1);
      
      textField2 = new JTextField("0", 5);
      add(textField2);
      
      textField3 = new JTextField("0", 10);
      textField3.setEditable(false);
      add(textField3);
      
      button1 = new JButton("Calculate area");
      button1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            double result = 0;
            
            switch(comboBox.getSelectedIndex()) {
               case 0: // Circle
                  Circle c = new Circle(Double.parseDouble(textField1.getText()));
                  result = c.getArea();
                  break;
                  
               case 1: // Square
                  Square s = new Square(Double.parseDouble(textField1.getText()));
                  result = s.getArea();
                  break;
                  
               case 2: // Rectangle
                  Rectangle r = new Rectangle(Double.parseDouble(textField2.getText()), Double.parseDouble(textField3.getText()));
result = r.getPerimeter();
break;
}
                    textField3.setText(Double.toString(result));
     }
  });
  add(button1);
  
  button2 = new JButton("Calculate perimeter");
  button2.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e) {
        double result = 0;
        
        switch(comboBox.getSelectedIndex()) {
           case 0: // Circle
              Circle c = new Circle(Double.parseDouble(textField1.getText()));
              result = c.getPerimeter();
              break;
              
           case 1: // Square
              Square s = new Square(Double.parseDouble(textField1.getText()));
              result = s.getPerimeter();
              break;
              
           case 2: // Rectangle
              Rectangle r = new Rectangle(Double.parseDouble(textField2.getText()), Double.parseDouble(textField3.getText()));
              result = r.getPerimeter();
              break;
        }
        
        textField3.setText(Double.toString(result));
     }
  });
  add(button2);
  
  button3 = new JButton("Clear");
  button3.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e) {
        textField1.setText("0");
        textField2.setText("0");
        textField3.setText("0");
     }
  });
  add(button3);
}
}

public class shape {
public static void main(String[] args) {
MyFrame frame = new MyFrame();
frame.setTitle("Shape Calculator");
frame.setSize(300, 150);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
}
}
