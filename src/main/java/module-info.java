module behan.seven.sortingfr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires poi.ooxml;
    requires poi;
    requires com.jfoenix;

    opens behan.seven.sortingfr to javafx.fxml;
    exports behan.seven.sortingfr;
}