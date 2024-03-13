package com.educacionit.libros.database

import android.content.Context
import androidx.room.Room
import com.educacionit.libros.model.Libro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository(context: Context) {
    private lateinit var booksDao: BooksDao

    init {
        try {
            val db = Room.databaseBuilder(context, BooksDatabase::class.java, DB_NAME).build()
            booksDao = db.booksDao()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getBooks(): List<Libro> = withContext(Dispatchers.IO) {
        booksDao.getBooks()
    }

    suspend fun addBook(book: Libro): ResultDBOperation = withContext(Dispatchers.IO) {
        try {
            booksDao.addBook(book)
            ResultOk
        } catch (e: Exception) {
            e.printStackTrace()
            ResultError
        }
    }
}

sealed class ResultDBOperation
object ResultOk : ResultDBOperation()
object ResultError : ResultDBOperation()