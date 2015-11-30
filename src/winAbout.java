import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class winAbout {

	public static void popUp() {
		Stage window = new Stage();

		window.setTitle("About");
		window.setMinWidth(400);
		window.setMinHeight(200);

		Label label = new Label("This Program was made during the SAD2 course 2015" + '\n' +
								"Authors:" + '\n' +
								"Jens Ravn Jensen" + '\n' + 
								"Peter Skovgaard");

		Button btnOK = new Button("OK");

		btnOK.setOnAction(e -> window.close());

		VBox layout = new VBox(30);

		layout.getChildren().addAll(label, btnOK);

		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);

		window.setScene(scene);
		window.show();
	} 
}
