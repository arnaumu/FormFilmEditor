package com.example.demo.utils

import com.example.demo.model.Film

object FilmDAO {

    private val con = Conection().conex

    //To entity
    internal fun Film.toEntity(): FilmEntity = FilmEntity(film_id, film_title, film_director, film_year)

    //Create
    fun createFilm(film: FilmEntity): FilmEntity {
        val ps = con.prepareStatement("INSERT INTO films (film_title, film_director, film_year) VALUES (?, ?, ?)")
        ps.setString(1, film.film_title)
        ps.setString(2, film.film_director)
        ps.setString(3, film.film_year)
        ps.executeUpdate()
        ps.close()
        println("--New Film was CREATED!--")
        return getLastFilm()
    }

    //Read
    fun readAllFilms(): List<FilmEntity> {
        val filmList = ArrayList<FilmEntity>()
        val rs = con.createStatement().executeQuery("SELECT * FROM  films ORDER BY film_id")
        while (rs.next()) {
            val id = rs.getInt("film_id")
            val title = rs.getString("film_title")
            val director = rs.getString("film_director")
            val year = rs.getString("film_year")
            filmList += FilmEntity(id, title, director, year)
        }
        rs.close()
        println("--Film list LOADED--")
        return filmList
    }

    //Update
    fun updateFilm(film: FilmEntity) {
        val ps =
            con.prepareStatement("UPDATE films SET film_title = ?, film_director = ?, film_year = ? WHERE film_id = ?")
        ps.setString(1, film.film_title)
        ps.setString(2, film.film_director)
        ps.setString(3, film.film_year)
        ps.setInt(4, film.film_id)
        ps.executeUpdate()
        ps.close()
        println("--Film * id:${film.film_id} title: ${film.film_title} * was UPDATED!--")
    }

    //Delete
    fun deleteFilm(film: FilmEntity) {
        val ps = con.prepareStatement("DELETE FROM films WHERE film_id = ?")
        ps.setInt(1, film.film_id)
        ps.executeUpdate()
        ps.close()
        println("--Film * id:${film.film_id} title: ${film.film_title} * was DELETED!--")
    }

    //Retorna últim film entrado
    private fun getLastFilm(): FilmEntity {
        val rs = con.createStatement().executeQuery("SELECT * FROM films ORDER BY film_id DESC")
        var lastFilm: FilmEntity? = null
        if (rs.next()) {
            lastFilm = FilmEntity(
                rs.getInt("film_id"),
                rs.getString("film_title"),
                rs.getString("film_director"),
                rs.getString("film_year")
            )
        }
        return lastFilm!!
    }
}