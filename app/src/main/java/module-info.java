module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires richtextfx;
    requires reactfx;
//    requires VectorTools.main;
    requires java.prefs;
    requires kotlin.stdlib;
    requires org.antlr.antlr4.runtime;
//    requires org.antlr.antlr4.runtime;

//    requires AntlrAnalyzer;
//    requires smtest;

    opens ru.art2000.androraider;
    exports ru.art2000.androraider;

//    opens ru.art2000.androraider.analyzer.antlr;
//    exports ru.art2000.androraider.analyzer.antlr;
//
//    opens ru.art2000.androraider.analyzer;
//    exports ru.art2000.androraider.analyzer;
}