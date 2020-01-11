package ru.art2000.androraider.analyzer

import io.reactivex.Observable
import java.io.File

abstract class SyntaxAnalyzer<BAR> {

    /*
    Has a return type since we analyze file fully and sequentially
     */
    public abstract fun analyzeFile(file: File): BAR

    /*
    Return RxJava Observable so that user can provide it's own event listeners
    */
    public abstract fun analyzeFilesInDir(directory: File): Observable<*>

    public abstract val filesRootDir: File
}