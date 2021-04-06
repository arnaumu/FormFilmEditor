package com.example.demo.utils

import com.example.demo.model.Film

data class FilmEntity(val film_id: Int = 0, val film_title: String, val film_director: String, val film_year: String)

fun FilmEntity.toFilm() = Film(film_id, film_title, film_director, film_year)