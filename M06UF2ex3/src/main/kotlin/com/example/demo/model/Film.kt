package com.example.demo.model

import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Film(film_id: Int = 0, film_title: String? = null, film_director: String? = null, film_year: String? = null) {

    val idProperty: SimpleIntegerProperty = SimpleIntegerProperty(this, "film_id", film_id)
    var film_id: Int by idProperty

    val titleProperty: SimpleStringProperty = SimpleStringProperty(this, "fiml_title", film_title)
    var film_title: String by titleProperty

    val directorProperty: SimpleStringProperty = SimpleStringProperty(this, "film_director", film_director)
    var film_director: String by directorProperty

    val yearProperty: SimpleStringProperty = SimpleStringProperty(this, "film_year", film_year)
    var film_year: String by yearProperty

    //val idProperty = SimpleIntegerProperty(film_id)
    //val titleProperty = SimpleStringProperty(film_title)
    //val directorProperty = SimpleStringProperty(film_director)
    //val yearProperty = SimpleStringProperty(film_year)

}

class FilmModel(film: Film) : ItemViewModel<Film>(film) {

    private val id: Property<Number> = bind(Film::idProperty)
    private val title: Property<String> = bind(Film::titleProperty)
    private val director: Property<String> = bind(Film::directorProperty)
    private val year: Property<String> = bind(Film::yearProperty)

    override fun onCommit() {
        super.onCommit()
        println("onCommit invoked")
    }

    override fun onCommit(commits: List<Commit>) {
        commits.findChanged(title)?.let { println("Title changed from '${it.first}' to '${it.second}'") }
        commits.findChanged(director)?.let { println("Director changed from '${it.second}' to '${it.first}'") }
        commits.findChanged(year)?.let { println("Year changed from '${it.second}' to '${it.first}'") }
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed }
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }
}

