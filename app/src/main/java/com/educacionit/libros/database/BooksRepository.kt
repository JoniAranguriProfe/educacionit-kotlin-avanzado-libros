package com.educacionit.libros.database

import android.content.Context
import androidx.room.Room
import com.educacionit.libros.model.Libro
import java.lang.Exception

class BooksRepository(context: Context) {
    private lateinit var booksDao: BooksDao

    init {
        try {
            val  db = Room.databaseBuilder(context, BooksDatabase::class.java, DB_NAME).build()
            booksDao = db.booksDao()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getBooks(): List<Libro> = booksDao.getBooks()

    fun addBook(book: Libro): ResultDBOperation = try {
        booksDao.addBook(book)
        ResultOk
    } catch (e: Exception) {
        e.printStackTrace()
        ResultError
    }
}

sealed class ResultDBOperation
object ResultOk : ResultDBOperation()
object ResultError : ResultDBOperation()