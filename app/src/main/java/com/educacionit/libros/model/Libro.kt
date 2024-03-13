package com.educacionit.libros.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_BOOK)
data class Libro(
        @PrimaryKey
        @ColumnInfo(name = COLUMN_ID)
        var id: Int? = null,
        @ColumnInfo(name = COLUMN_NAME)
        var nombre: String? = null,
        @ColumnInfo(name = COLUMN_AUTHOR)
        var autor: String? = null)

const val TABLE_BOOK = "book"
const val COLUMN_ID = "id"
const val COLUMN_NAME = "name"
const val COLUMN_AUTHOR = "author"
