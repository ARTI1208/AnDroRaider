module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires richtextfx;
    requires reactfx;
    requires java.prefs;
    requires kotlin.stdlib;
    requires org.antlr.antlr4.runtime;
    requires flowless;
    requires io.reactivex.rxjava2;
    requires rxjavafx;

    opens ru.art2000.androraider;
    exports ru.art2000.androraider;
}