package br.com.principal;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class teste  extends Application {
 
//    public void start(Stage stage) {
//        Group root = new Group();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle("Progress Controls");
// 
//        final Slider slider = new Slider();
//        slider.setMin(0);
//        slider.setMax(50);
//         
//        final ProgressBar pb = new ProgressBar(0);
//        final ProgressIndicator pi = new ProgressIndicator(0);
// 
//        slider.valueProperty().addListener(new ChangeListener<Number>() {
//            public void changed(ObservableValue<? extends Number> ov,
//                Number old_val, Number new_val) {
//                pb.setProgress(new_val.doubleValue()/50);
//                pi.setProgress(new_val.doubleValue()/50);
//            }
//        });
// 
//        final HBox hb = new HBox();
//        hb.setSpacing(5);
//        hb.setAlignment(Pos.CENTER);
//        hb.getChildren().addAll(slider, pb, pi);
//        scene.setRoot(hb);
//        stage.show();
//    }
//    public static void main(String[] args) {
//        launch(args);
//    }
    
//    ---------------------------------------------------------------
    
    
    final Float[] values = new Float[] {-1.7f, 0f, 0.6f, 0.5f};
    final Label [] labels = new Label[values.length];
    final ProgressBar[] pbs = new ProgressBar[values.length];
    final ProgressIndicator[] pins = new ProgressIndicator[values.length];
    final HBox hbs [] = new HBox [values.length];

        @Override
        public void start(Stage stage) {
            Group root = new Group();
            Scene scene = new Scene(root, 300, 150);
            scene.getStylesheets().add("progresssample/Style.css");
            stage.setScene(scene);
            stage.setTitle("Progress Controls");
     
     
            for (int i = 0; i < values.length; i++) {
                final Label label = labels[i] = new Label();
                label.setText("progress:" + values[i]);
     
                final ProgressBar pb = pbs[i] = new ProgressBar();
                pb.setProgress(values[i]);
     
                final ProgressIndicator pin = pins[i] = new ProgressIndicator();
                pin.setProgress(values[i]);
                final HBox hb = hbs[i] = new HBox();
                hb.setSpacing(5);
                hb.setAlignment(Pos.CENTER);
                hb.getChildren().addAll(label, pb, pin);
            }
     
            final VBox vb = new VBox();
            vb.setSpacing(5);
            vb.getChildren().addAll(hbs);
            scene.setRoot(vb);
            stage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    
    
    
    
    
	
}