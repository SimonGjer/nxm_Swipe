import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;

public class BottomPanel {

	public static HBox greateBottomPanel() {
		HBox bottomPanel = new HBox();
		
		bottomPanel.setAlignment(Pos.TOP_CENTER);
//		Group root = new Group();

		// Red line
		Line redLine = new Line(10, 10, 200, 10);
		// setting common properties
		redLine.setStroke(Color.RED);
		redLine.setStrokeWidth(10);
		redLine.setStrokeLineCap(StrokeLineCap.BUTT);
		
		// slider min, max, and current value
		Slider slider = new Slider(0, 100, 0);
//		slider.
		slider.setLayoutX(10);
		slider.setLayoutY(95);
				
		// bind the stroke dash offset property
		redLine.strokeDashOffsetProperty().bind(slider.valueProperty());
		Text offsetText = new Text("Stroke Dash Offset: 0.0");
		offsetText.setX(20);
		offsetText.setY(80);
		offsetText.setStroke(Color.PINK.darker());
		// display stroke dash offset value
		slider.valueProperty().addListener((ov, curVal, newVal) -> offsetText.setText("Stroke Dash Offset: " + slider.getValue()));

		bottomPanel.getChildren().addAll(/*slider,*/ offsetText);
		
		return bottomPanel;	
	}
}
