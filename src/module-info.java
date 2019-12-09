module AnDroRaider {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires richtextfx;
    requires reactfx;
    requires VectorTools;
    requires java.prefs;

    opens ru.art2000.androraider;
    exports ru.art2000.androraider;

    opens resources.drawable;
    opens resources.style;
}