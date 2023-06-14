package com.example.javafx_3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.nio.file.Watchable;


public class FX_3 extends Application{

    @Override
    public void start(Stage stage) throws Exception{
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 800);
        stage.setScene(scene);

        Rectangle r1 = new Rectangle(20, 20, 200, 150);
        r1.setFill(Color.YELLOW);
        r1.setStroke(Color.BLUE);
        r1.setStrokeWidth(5.0);

        Rectangle r2 = new Rectangle(20, 250, 200, 150);
        r2.setFill(Color.BLACK);
        r2.setStroke(Color.RED);
        r2.setArcWidth(100.0);
        r2.setArcHeight(100.0);

        Ellipse e1 = new Ellipse(400, 100, 100, 75);

        Arc a1 = new Arc(400, 300, 100, 100, 90, 90);
        a1.setFill(Color.PINK);
        a1.setType(ArcType.ROUND);

        root.getChildren().addAll(r1, r2, e1, a1);
        stage.show();

    }

}
