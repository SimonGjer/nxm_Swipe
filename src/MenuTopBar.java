import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;

public class MenuTopBar {

	public static MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();
		{
			Menu menuFile = new Menu("_File");
			{
				menuFile.setMnemonicParsing(true);
				MenuItem miNew = new MenuItem("_New"); miNew.setMnemonicParsing(true); miNew.setOnAction( e -> { LeftButtonPanel.doNew(); });
				MenuItem miNew3x3 = new MenuItem("New 3x3"); miNew3x3.setOnAction( e -> { LeftButtonPanel.doNew(3, 3); });
				MenuItem miNew10x10 = new MenuItem("New 10x10"); miNew10x10.setOnAction( e -> { LeftButtonPanel.doNew(10, 10); });
				MenuItem miNew30x30 = new MenuItem("New 30x30"); miNew30x30.setOnAction( e -> { LeftButtonPanel.doNew(30, 30); });
				MenuItem miSave = new MenuItem("_Save..."); miSave.setMnemonicParsing(true);
				MenuItem miLoad = new MenuItem("_Load..."); miLoad.setMnemonicParsing(true);
				MenuItem miExit = new MenuItem("E_xit"); miExit.setMnemonicParsing(true);

				menuFile.getItems().addAll(miNew, miNew3x3, miNew10x10, miNew30x30, new SeparatorMenuItem(), miSave, miLoad, miExit);
			}

			Menu menuEdit = new Menu("_Edit");
			{
				MenuItem miSelAll = new MenuItem("Select All");
				MenuItem miSelInv = new MenuItem("Inv. Select");

				menuEdit.getItems().addAll(miSelAll, miSelInv);
			}

			Menu menuView = new Menu("_View");
			{
				ToggleGroup tGroupCam = new ToggleGroup();
				RadioMenuItem radioPerCam = new RadioMenuItem("Perspective Camera");
				RadioMenuItem radioParCam = new RadioMenuItem("Parallel Camera");

				radioPerCam.setToggleGroup(tGroupCam); radioPerCam.setSelected(true);
				radioParCam.setToggleGroup(tGroupCam);

				menuView.getItems().addAll(radioPerCam, radioParCam);
			}

			Menu menuHelp = new Menu("_Help");
			{
				
			}


			menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
		}
		return menuBar;
	}
}
