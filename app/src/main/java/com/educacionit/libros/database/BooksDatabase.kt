package com.educacionit.libros.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.educacionit.libros.model.Libro

@Database(entities = [Libro::class], version = DB_VERSION)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao
}

const val DB_VERSION = 1