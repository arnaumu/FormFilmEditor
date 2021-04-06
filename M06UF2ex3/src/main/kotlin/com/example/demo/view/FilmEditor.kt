package com.example.demo.view

import com.example.demo.controller.FilmController
import com.example.demo.model.Film
import com.example.demo.model.FilmModel
import javafx.collections.ObservableList
import javafx.scene.layout.BorderPane
import tornadofx.*
import tornadofx.Stylesheet.Companion.right

/*class FilmEditor : View() {

    override val root: BorderPane = BorderPane()
    private var films: ObservableList<Film>
    private var model: FilmModel = FilmModel(Film())
    private val controller: FilmController by inject()

    init {
        films = controller.allFilms()

        with(root) {

            center {
                tableview(films) {
                    column("Title", Film::titleProperty)
                    column("Director", Film::directorProperty)
                    column("Year", Film::yearProperty)

                    model.rebindOnChange(this) { filmSelecionado ->
                        item = filmSelecionado ?: Film()

                    }
                    prefWidth = 293.0
                    bindSelected(model)
                }
            }

            right {
                form {
                    fieldset("Films Editor") {
                        field("Title") {
                            textfield(model.title).required()
                        }
                        field("Director") {
                            textfield(model.director).required()
                        }
                        field("Year") {
                            textfield(model.year).required()
                        }

                        buttonbar {
                            button("Reset").action {
                                model.rollback()
                            }

                            button("Add") {
                                enableWhen(model.dirty)
                                action {
                                    addf()
                                }
                            }
                            button("Delete") {
                                enableWhen(model.valid)
                                action {
                                    deletef()
                                }
                            }
                            button("Update") {
                                // model.dirty vol dir que ha canviat name/title
                                enableWhen(model.dirty)
                                action {
                                    updatef()
                                }
                            }

                        }


                    }
                }


            }


        }

    }



    private fun addf(){
        val film = model.item
        model.commit()
        controller.createFilm(model.title.value, model.director.value, model.year.value)
        with(films){
            add(film)
        }
        println("Film ${film.film_title} created")
    }

    private fun deletef(){
        val film = model.item
        model.commit()
        controller.deleteFilm(film)
        with(films){
            remove(film)
        }
        println("Film ${film.film_title} deleted")
    }

    private fun updatef(){
        val film = model.item
        model.commit()
        controller.updateFilm(film, model.title.value, model.director.value, model.year.value)
        println("Film ${film.film_title} + added")
    }
}

 */