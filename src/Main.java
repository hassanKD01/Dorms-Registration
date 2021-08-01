

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/LoginPage_fxml.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setMaximized(true);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/images/lulogo.jpg")));
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
