module erciyes.edu.javafx_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens erciyes.edu.javafx_2 to javafx.fxml;
    exports erciyes.edu.javafx_2;
}