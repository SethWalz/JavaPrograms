/*
Write a program that prompts the user to enter the
center coordinates, width, and height of two rectangles from the command line.
The program displays the rectangles and a text indicating whether the two are
overlapping, whether one is contained in the other, or whether they don’t overlap,
as shown in Figure 14.50. See Programming Exercise 10.13 for checking the
relationship between two rectangles.

Michael Walz
700609721
*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;


public class Exercise_14_23 extends Application {

	@Override
    public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		double WIDTH = 400;
		double HEIGHT = 400;

		Scanner input = new Scanner(System.in);
		ArrayList<Rectangle> rectangles = new ArrayList<>();

		for (int i =1; i <= 2; i++){
			System.out.print("Enter the location, height, width of rectangle " + i +": ");
			Rectangle temp = new Rectangle(
				input.nextDouble(),
				input.nextDouble(),
				input.nextDouble(),
				input.nextDouble());

			temp.setFill(Color.TRANSPARENT);
			temp.setStroke(Color.BLACK);

            rectangles.add(temp);
		}

		MyRectangle2D rect1 = new MyRectangle2D(rectangles.get(0));
		MyRectangle2D rect2 = new MyRectangle2D(rectangles.get(1));
		if (rect1.contains(rect2) || rect2.contains(rect1)) {
            System.out.println("One rectangle is contained in another.");
        } else if (rect1.overlaps(rect2) || rect2.overlaps(rect1)) {
            System.out.println("One rectangle overlaps another");
        } else {
            System.out.println("The rectangles do not overlap.");
        }

		pane.getChildren().addAll(rectangles);
		primaryStage.setTitle("RECTANGLE DETECTION SYSTEM");
		primaryStage.setScene(new Scene(pane, WIDTH, HEIGHT));
		primaryStage.show();

	}

}

class MyRectangle2D
{
    private double x;
    private double y;
    private double height;
    private double width;
    public MyRectangle2D(Rectangle rect)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public double getX()
    {
        return x;
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public double getY()
    {
        return y;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public double getHeight()
    {
        return height;
    }
    public void setHeight(double height)
    {
        this.height = height;
    }
    public double getWidth()
    {
        return width;
    }
    public void setWidth(double width)
    {
        this.width = width;
    }
    public MyRectangle2D()
    {
        this.x = 0;
        this.y = 0;
        this.height = 1;
        this.width = 1;
    }
    public boolean contains(double x, double y)
    {
        return (2 * Math.abs((x-this.x)) > height || 2 * Math.abs((y - this.y)) > width);
    }
    public boolean contains(MyRectangle2D r)
    {
        return (2 * Math.abs((r.getX()-this.x)) > height || 2 * Math.abs((r.getY() - this.y)) > width);
    }
    public boolean overlaps(MyRectangle2D r)
    {
        return (2 * Math.abs((r.getX()-this.x)) >= height || 2 * Math.abs((r.getY() - this.y)) >= width);
    }
}