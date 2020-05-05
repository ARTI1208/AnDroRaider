module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.fxmisc.richtext;
    requires reactfx;
    requires java.prefs;
    requires kotlin.stdlib;
    requires org.antlr.antlr4.runtime;
    requires flowless;
    requires io.reactivex.rxjava2;
    requires rxjavafx;
    requires org.apache.commons.io;
    requires undofx;

    opens ru.art2000.androraider.view.launcher;
    exports ru.art2000.androraider.view.launcher;
}