// Seth Walz
// Q4_Mulario

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.layout.GridPane;


public class Q4_Mulario extends Application
{

  private ArrayList<Circle> nodes;
  private static  int KEYBOARD_MOVEMENT_DELTA = 5;
  private static  Duration TRANSLATE_DURATION      = Duration.seconds(1);
  public static  int size = 0;
  public static void main(String[] args) { launch(args); }

  @Override public void start(Stage stage) throws Exception
  {

	// basics
	Button submitOptions = new Button("Submit");
	Label sizeOfCircle = new Label("Size: 10");
	Button options = new Button("Show Options");
	sizeOfCircle.setTextFill(Color.WHITE);
	CheckBox showSize = new CheckBox("Current Size");
    showSize.setSelected(true);

	GridPane gpane = new GridPane();
	gpane.setPadding(new Insets(10, 10, 10, 10));
	gpane.setVgap(5);
	gpane.setHgap(5);

	Label chooseColor = new Label("Choose Your Color (lowercase): ");
	GridPane.setConstraints(chooseColor, 0,0);
	gpane.getChildren().add(chooseColor);

	final TextField colorCircle = new TextField();
	colorCircle.setPrefColumnCount(10);
	colorCircle.getText();
	GridPane.setConstraints(colorCircle, 1, 0);
	gpane.getChildren().add(colorCircle);
	GridPane.setConstraints(showSize,0,1);
	gpane.getChildren().add(showSize);
	GridPane.setConstraints(submitOptions,1,2);
	gpane.getChildren().add(submitOptions);
	Scene optionScene = new Scene(gpane, 360, 100);

	Stage stage1 = new Stage();
	stage1.setTitle("Options");
    stage1.setScene(optionScene);

	Group root = new Group();
    final Circle circle = createCircle();
    Circle[] targetList = new Circle[100];
    final TranslateTransition transition = createTranslateTransition(circle);
	nodes = new ArrayList<>();


	Pane pane = new Pane();
	pane.getChildren().add(circle);

	for(int i = 0; i < 100 ; i++)
	{
		targetList[i] = smallCircle();
		pane.getChildren().add(targetList[i]);
		nodes.add(targetList[i]);
	}

	for (Circle block : nodes) {
	      setDragListeners(block);
    }
    root.getChildren().addAll(nodes);
    checkShapeIntersection(nodes.get(nodes.size() - 1));


	Group group = new Group(root,pane);
    Scene scene = new Scene(group, 1000, 1000, Color.GREY);
    moveCircleOnKeyPress(scene, circle,targetList, root, sizeOfCircle);

    stage.setScene(scene);
    stage.show();
	stage1.show();

	// set color
	submitOptions.setOnAction((event) ->
	{

		if(colorCircle.getText().equals("red"))
		{
			circle.setFill(Color.RED);
		}
		else if(colorCircle.getText().equals("blue"))
		{
			circle.setFill(Color.BLUE);
		}
		else if(colorCircle.getText().equals("green"))
		{
			circle.setFill(Color.GREEN);
		}
		else if(colorCircle.getText().equals("purple"))
		{
			circle.setFill(Color.PURPLE);
		}
		else if(colorCircle.getText().equals("black"))
		{
			circle.setFill(Color.BLACK);
		}
		else if(colorCircle.getText().equals("yellow"))
		{
			circle.setFill(Color.YELLOW);
		}

		if(showSize.isSelected() == false)
		{
			sizeOfCircle.relocate(scene.getWidth() / 2,0);
			pane.getChildren().remove(sizeOfCircle);
		}
		else
		{
			sizeOfCircle.relocate(scene.getWidth() / 1.1,0);
			pane.getChildren().add(sizeOfCircle);
		}

	});

  }

// creating random colored circles
  private Circle smallCircle()
  {
	  Random rand = new Random();
	  int c = rand.nextInt(4);

	  if(c == 0)
	  {
		  Circle circle = new Circle(rand.nextInt(1000),rand.nextInt(1000), 15, Color.BLUE);
		  return circle;
	  }
	  else if(c == 1)
	  {
		  Circle circle = new Circle(rand.nextInt(1000),rand.nextInt(1000), 15, Color.RED);
		  return circle;
	  }
	  else if(c == 2)
	  {
		  Circle circle = new Circle(rand.nextInt(1000),rand.nextInt(1000), 15, Color.YELLOW);
		  return circle;
	  }
	  else if(c == 3)
	  {
	  		  Circle circle = new Circle(rand.nextInt(1000),rand.nextInt(1000), 15, Color.WHITE);
	  		  return circle;
	  }
	  else
	  {
	  final Circle circle = new Circle(rand.nextInt(1000),rand.nextInt(1000), 15, Color.BLACK);
	  	return circle;
	  }
  }

	// create main circle
    private Circle createCircle()
    {

      Circle circle = new Circle(200, 150, 50, Color.WHITE);
      return circle;
    }

// moving circle
  private void moveCircleOnKeyPress(Scene scene, final Circle circle,Circle[] targetList,Group root,Label sizeOfCircle)
    {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
          @Override public void handle(KeyEvent event) {
            switch (event.getCode()) {
              case UP:  circle.setCenterY(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA);
              			obsorbed(circle,targetList,root,sizeOfCircle);
              			break;
              case RIGHT:circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA);
              			obsorbed(circle,targetList,root,sizeOfCircle);
              			break;
              case DOWN:circle.setCenterY(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA);
              			obsorbed(circle,targetList,root,sizeOfCircle);
              			break;
              case LEFT:circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA);
              			obsorbed(circle,targetList,root,sizeOfCircle);
              			break;
            }
          }
        });
  }

  private TranslateTransition createTranslateTransition(final Circle circle)
  {
    final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, circle);
    transition.setOnFinished(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent t) {
        circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
        circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
        circle.setTranslateX(0);
        circle.setTranslateY(0);
      }
    });
    return transition;
  }
  // obsorbing other circles math
  private void obsorbed(final Circle circle,Circle[] targetList,Group root, Label sizeOfCircle){
   		for(int i = 0; i < targetList.length; i++){
			if(circle.getCenterX() > targetList[i].getCenterX()){
		   		if((circle.getCenterX() - targetList[i].getCenterX()) < (circle.getRadius())){
		   			if(circle.getCenterY() > targetList[i].getCenterY()){
		   				if((circle.getCenterY() - targetList[i].getCenterY()) < (circle.getRadius())){
		   					if(root.getChildren().contains(targetList[i])){
		   						root.getChildren().remove(targetList[i]);
		   						circle.setRadius(circle.getRadius() + (targetList[i].getRadius() * .25));
		   						sizeOfCircle.setText("Size: " + circle.getRadius());
		   					}

		   				}
		   			}
		   		}
		   	}
		   	else if(circle.getCenterX() < targetList[i].getCenterX()){
		   		if((targetList[i].getCenterX() - circle.getCenterX()) < (circle.getRadius())){
		   			if(circle.getCenterY() < targetList[i].getCenterY()){
		   				if((targetList[i].getCenterY() - circle.getCenterY()) < (circle.getRadius())){
		   					if(root.getChildren().contains(targetList[i])){
		   						root.getChildren().remove(targetList[i]);
		   						circle.setRadius(circle.getRadius() + (targetList[i].getRadius() * .25));

		   					}
		   				}
		   			}
		   		}
		   	}

		}
   }

   private void checkShapeIntersection(Shape block)
   {

	}

public void setDragListeners(final Circle block)
{
    Delta dragDelta = new Delta();

    block.setOnKeyPressed(new EventHandler<KeyEvent>()
    {
      @Override public void handle(KeyEvent keyEvent)
      {
        checkShapeIntersection(block);
      }
    });
}
   class Delta { double x, y; }
}