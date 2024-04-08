package com.educacionit.libros.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = TABLE_BOOK, primaryKeys = [COLUMN_NAME, COLUMN_AUTHOR])
data class Libro(
    @ColumnInfo(name = COLUMN_NAME)
    var nombre: String,
    @ColumnInfo(name = COLUMN_AUTHOR)
    var autor: String
)

const val TABLE_BOOK = "book"
const val COLUMN_NAME = "name"
const val COLUMN_AUTHOR = "author"
