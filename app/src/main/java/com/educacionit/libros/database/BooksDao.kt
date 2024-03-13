package com.educacionit.libros.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.educacionit.libros.model.Libro
import com.educacionit.libros.model.TABLE_BOOK

@Dao
interface BooksDao {

    @Query("SELECT * FROM $TABLE_BOOK")
    fun getBooks(): List<Libro>

    @Insert
    fun addBook(book: Libro)
}