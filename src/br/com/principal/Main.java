package br.com.principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{

Stage window;
    
    @Override
    public void start(Stage stage) throws Exception {
             
        window = stage;
        window.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("../view/fxml_telaPrincipal.fxml"));
        Scene scene = new Scene(root );
        window.setScene(scene);
        window.show();
             
    }

       public static void main(String[] args) {
        launch(args);
    }
	
}

