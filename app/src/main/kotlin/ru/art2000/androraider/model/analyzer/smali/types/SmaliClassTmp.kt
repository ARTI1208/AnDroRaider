package ru.art2000.androraider.model.analyzer.smali.types

import java.io.File

class SmaliClassTmp() : SmaliClass() {
    constructor(name: String,
                parentPackage: SmaliPackage? = null) : this() {
        this.name = name
        this.parentPackage = parentPackage
    }

    constructor(file: File) : this() {
        this.associatedFile = file
        this.name = file.nameWithoutExtension
    }
}