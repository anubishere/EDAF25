package gui.menu;

import gui.XL;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SaveMenuItem extends MenuItem {
  public SaveMenuItem(XL xl, Stage stage) {
    super("Save");
    setOnAction(event -> {
      FileChooser fileChooser = new FileChooser();

      String userDirectoryString = System.getProperty("user.dir");
      File userDirectory = new File(userDirectoryString);
      if(!userDirectory.canRead()) {
        userDirectory = new File("c:/");
      }
      fileChooser.setInitialDirectory(userDirectory);

      fileChooser.getExtensionFilters()
          .add(new FileChooser.ExtensionFilter("XL files (*.xl)", "*.xl"));
      File file = fileChooser.showSaveDialog(stage);
      if (file != null) {
        // TODO

        xl.saveFile(file);
        xl.clearEditor();
      }
    });
  }
}
