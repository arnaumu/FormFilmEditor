package com.example.demo.utils

import java.sql.DriverManager
import java.sql.Connection

class Conection {

    var conex: Connection

    val MYSQL_URL = "jdbc:mysql://localhost:3306/gestacad"
    val MYSQL_USER = "root"
    val MYSQL_PASSWORD = ""

    init {
        Class.forName("com.mysql.jdbc.Driver")
        conex = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD)
    }
}