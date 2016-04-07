/*
Michael Walz
700609721
Write a program that paints a smiley face
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class Exercise_14_11 extends Application {



	@Override
    public void start(Stage primaryStage) {

		Pane pane = new Pane();
        ArrayList<Shape> shapes = new ArrayList<>();

		//Cicle Stuff
        Circle circle = new Circle();
        circle.setCenterX(100);
		circle.setCenterY(100);
		circle.setRadius(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        shapes.add(circle);

        //Mouth Stuff
        Arc smile = new Arc(
			circle.getCenterX(),
        	circle.getCenterY() + (circle.getRadius() / 3),
        	circle.getRadius() / 2,
        	circle.getRadius() / 2 / 2,
        	180, 180);
		smile.setFill(Color.WHITE);
		smile.setStroke(Color.BLACK);
		shapes.add(smile);

		//Nose Stuff
		Polygon nose = new Polygon();
		nose.getPoints().addAll(new Double[]{
		    100.0, 85.0,
		    115.0, 110.0,
    		85.0, 110.0 });
   		nose.setFill(Color.WHITE);
   		nose.setStroke(Color.BLACK);
    	shapes.add(nose);

		//Eye Stuff
		double RADIUS = 50;
		double x = 100 - (RADIUS / 2.8);
		double y = 100 - (RADIUS / 2.8);
		for (int i = 0; i < 2; i++) {
			Ellipse outerCircle = new Ellipse(x, y, RADIUS / 4, RADIUS / 6);
			outerCircle.setFill(Color.WHITE);
			outerCircle.setStroke(Color.BLACK);
			shapes.add(outerCircle);

			Circle center = new Circle(outerCircle.getCenterX(), outerCircle.getCenterY(), outerCircle.getRadiusY() / 1.2);
			shapes.add(center);
			x += (RADIUS / 2.8) * 2;
		}

		//Display Things
		pane.getChildren().addAll(shapes);

        // Create Scene
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("ShowCircle");
        primaryStage.setScene(scene);
        primaryStage.show();



	}




}