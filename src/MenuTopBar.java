import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuTopBar {
	
	public static MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();

		Menu menuFile = new Menu("File");
		Menu menuEdit = new Menu("Edit");
		Menu menuView = new Menu("View");
		Menu menuHelp = new Menu("Help");

		menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
		return menuBar;
	}
}
