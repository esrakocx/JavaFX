package edu.erciyes.javafx_1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FX_1 extends Application{

    @Override
    public void init(){
        System.out.println("Init");
    }

    @Override
    public void start(Stage stage){
        Button btnOK = new Button("OK");
        Button btnExit = new Button("Exit");
        Button btnCancel = new Button("Cancel");

        HBox root = new HBox();
        root.getChildren().add(btnOK);
        root.getChildren().addAll(btnCancel, btnExit);

        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.setTitle("JavaFX Demo");
        stage.show();

    }

    @Override
    public void stop(){
        System.out.println("Stop");
    }
}
