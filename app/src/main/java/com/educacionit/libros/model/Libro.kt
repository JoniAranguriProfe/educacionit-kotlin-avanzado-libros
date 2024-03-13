package com.educacionit.libros.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = TABLE_BOOK)
data class Libro(
        @DatabaseField(id = true)
        var id: Int? = null,
        @DatabaseField
        var nombre: String? = null,
        @DatabaseField
        var autor: String? = null)

const val TABLE_BOOK = "book"
