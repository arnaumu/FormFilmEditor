package com.example.demo.app


import com.example.demo.view.FilmEditorTable
import tornadofx.App

class MyApp: App(FilmEditorTable::class, Styles::class)

fun main(args: Array<String>) {
    tornadofx.launch<MyApp>(args)
}