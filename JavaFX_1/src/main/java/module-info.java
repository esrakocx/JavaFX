module edu.erciyes.javafx_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.erciyes.javafx_1 to javafx.fxml;
    exports edu.erciyes.javafx_1;
}