module app {

    // javafx
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.fxmisc.richtext;
    requires org.fxmisc.flowless;
    requires org.fxmisc.undo;

    requires java.prefs;
    requires kotlin.stdlib;

    requires io.reactivex.rxjava2;
    requires rxjavafx;

    requires org.apache.commons.io;
    requires org.antlr.antlr4.runtime;

    requires reactfx;
    requires flowless;
    requires undofx;


    requires common;

    opens ru.art2000.androraider.view;
    exports ru.art2000.androraider.view;

    opens ru.art2000.androraider.view.launcher;
    exports ru.art2000.androraider.view.launcher;

    opens ru.art2000.androraider.view.settings;
    exports ru.art2000.androraider.view.settings;

    opens ru.art2000.androraider.view.editor;
    exports ru.art2000.androraider.view.editor;

    opens ru.art2000.androraider.view.editor.code;
    exports ru.art2000.androraider.view.editor.code;

    opens ru.art2000.androraider.view.editor.console;
    exports ru.art2000.androraider.view.editor.console;

    opens ru.art2000.androraider.view.editor.filemanager;
    exports ru.art2000.androraider.view.editor.filemanager;

    opens ru.art2000.androraider.view.editor.search;
    exports ru.art2000.androraider.view.editor.search;

    opens ru.art2000.androraider.view.editor.statusbar;
    exports ru.art2000.androraider.view.editor.statusbar;

    opens ru.art2000.androraider.view.dialogs.decompile;
    exports ru.art2000.androraider.view.dialogs.decompile;
    opens ru.art2000.androraider.view.dialogs.recompile;
    exports ru.art2000.androraider.view.dialogs.recompile;
    opens ru.art2000.androraider.view.dialogs.projectsettings;
    exports ru.art2000.androraider.view.dialogs.projectsettings;
}