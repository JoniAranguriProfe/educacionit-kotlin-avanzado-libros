package com.educacionit.libros.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = TABLE_BOOK)
data class Libro(
    @DatabaseField
    var nombre: String? = null,

    @DatabaseField
    var autor: String? = null
) {
    @DatabaseField(id = true, useGetSet = true)
    var id: String = ""
        get() {
            return "${nombre}_$autor"
        }
}

const val TABLE_BOOK = "book"
