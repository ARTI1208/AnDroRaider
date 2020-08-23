package ru.art2000.androraider.model.analyzer.xml

import javafx.collections.ObservableMap
import ru.art2000.androraider.model.analyzer.result.Project
import ru.art2000.androraider.model.analyzer.xml.types.Document
import java.io.File

interface XMLProject : Project {

    val fileToXMLDocMapping: ObservableMap<File, Document>
}