import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class LeftButtonPanel {

	public static Random random = new Random();
	
	public static boolean fFtn1 = false;

	public static VBox createPanel() {
		VBox leftPanel = new VBox(5);

		Button btn1 = new Button("test Timer");
		Button btn2 = new Button("btn 2");
		Button btn3 = new Button("btn 3");
		Button btn4 = new Button("btn 4");

		
		
		btn1.setOnAction( e -> {
			System.out.println("btn1 pressed");
			fFtn1 = !fFtn1;
		});
		
		
		leftPanel.getChildren().addAll(btn1, btn2, btn3, btn4);

		

		return leftPanel;
	}


}
