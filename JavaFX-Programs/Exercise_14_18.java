/*
Michael Walz
700609721
(Plot the square function) Write a program that draws a diagram for the function
f(x) = x2 (see Figure 14.48b).
*/


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class Exercise_14_18 extends Application {

	private static double WIDTH = 400;
	private static double HEIGHT = 250;
	private static double CENTER = 200;
	double limit = WIDTH * 0.9;


	@Override
    public void start(Stage primaryStage) throws Exception {

		Pane pane = new Pane();

		//function line stuff
		Polyline polyline = new Polyline();
		ObservableList<Double> list = polyline.getPoints();
		double scaleFactor = 0.0125;
		for (int x = -100; x <= 100; x++) {
		list.add(x + 200.0);
		list.add(200 - scaleFactor * x * x);
		}

		// line stuff
		Line xAxis = new Line(0, 200, 350, 200);
		Line yAxis = new Line(200, 50, 200, 250);

		//arrowhead stuff
		Polyline yHead = new Polyline();
		yHead.getPoints().addAll(new Double[]{
			180.0, 70.0,
			200.0, 50.0,
    		220.0, 70.0
		});
		yHead.setFill(Color.TRANSPARENT);
		yHead.setStroke(Color.BLACK);
		Text Y = new Text(limit - 160, HEIGHT * 0.1, "Y");

		Polyline xHead = new Polyline();
				xHead.getPoints().addAll(new Double[]{
					330.0, 220.0,
					350.0, 200.0,
		    		330.0, 180.0
				});
		xHead.setFill(Color.TRANSPARENT);
		xHead.setStroke(Color.BLACK);
		Text X = new Text(limit + (WIDTH * 0.02), CENTER, "X");
		//Letter for arrows


		//pane things
		pane.getChildren().add(xAxis);
		pane.getChildren().add(xHead);
		pane.getChildren().add(X);
		pane.getChildren().add(yAxis);
		pane.getChildren().add(yHead);
		pane.getChildren().add(Y);
		pane.getChildren().add(polyline);
		primaryStage.setScene(new Scene(pane, WIDTH, HEIGHT));
		primaryStage.setTitle("f(x) = x^2");
        primaryStage.show();
	}



}