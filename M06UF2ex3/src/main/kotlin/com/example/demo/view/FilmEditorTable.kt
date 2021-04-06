package com.example.demo.view

import com.example.demo.controller.FilmController
import com.example.demo.model.Film
import com.example.demo.model.FilmModel
import com.example.demo.utils.toFilm
import javafx.application.Platform
import javafx.collections.ObservableList
import javafx.scene.input.KeyCombination
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.*
import java.awt.TextField


class FilmEditorTable : View("Film Editor") {

    override val root: BorderPane = BorderPane()
    private val films: ObservableList<Film>
    private var model: TableViewEditModel<Film> by singleAssign()
    private var modelf: FilmModel = FilmModel(Film())
    private val controller: FilmController by inject()

    init {

        films = controller.read()

        with(root) {
            prefWidth = 340.0
            top {
                menubar {
                    menu("App") {
                        item("Commit").action {
                            model.items.forEach {
                                if (it.value.isDirty) {
                                    update(it.key)
                                    println(it.key)
                                }
                            }
                            model.commit()
                        }
                        item("Close app").action {
                            Platform.exit()
                        }
                    }
                }
            }

            center {
                tableview(films) {

                    isEditable = true
                    enableCellEditing()
                    enableDirtyTracking()
                    model = editModel

                    column("Title", Film::titleProperty).makeEditable()
                    column("Director", Film::directorProperty).makeEditable()
                    column("Year", Film::yearProperty).makeEditable()

                    contextmenu {
                        item("Remove").action {
                            selectedItem?.apply {
                                model.commit()
                                delete(this)
                            }
                        }
                        item("Update").action {
                            selectedItem?.apply {
                                model.commit()
                                update(this)
                            }
                        }
                    }
                }

            }
            bottom {
                buttonbar {
                    button("Add film").action {
                        create(
                            Film(
                                film_title = "*edit new title*",
                                film_director = "*edit new director*",
                                film_year = "*edit new year*"
                            )
                        )
                    }
                    button("Rollback").action {
                        model.rollback()
                    }
                }
            }
        }
    }

    private fun create(film: Film) {
        val film = controller.create(film)
        films.add(film.toFilm())
        films.sortBy { it.film_year }
    }

    private fun update(film: Film) {
        if (film.film_id > 0) {
            controller.update(film)
        }
    }

    private fun delete(film: Film) {
        if (film.film_id > 0) {
            controller.delete(film)
            films.remove(film)
        }
    }

    private fun newFilmData() {

    }
}
