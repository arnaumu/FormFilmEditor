package com.example.demo.controller

import com.example.demo.model.Film
import com.example.demo.utils.FilmDAO
import com.example.demo.utils.FilmDAO.toEntity
import com.example.demo.utils.FilmEntity
import javafx.collections.ObservableList
import javafx.collections.transformation.FilteredList
import tornadofx.Controller
import tornadofx.SortedFilteredList
import tornadofx.observable

class FilmController : Controller() {

    private val filmDAO = FilmDAO

    //CREATE
    fun create(film: Film): FilmEntity {
        return filmDAO.createFilm(film.toEntity())
    }

    //READ
    fun read() =
        filmDAO.readAllFilms().map { f -> Film(f.film_id, f.film_title, f.film_director, f.film_year) }.observable()

    //UPDATE
    fun update(film: Film) {
        filmDAO.updateFilm(film.toEntity())
    }

    //DELETE
    fun delete(film: Film) {
        filmDAO.deleteFilm(film.toEntity())
    }

}