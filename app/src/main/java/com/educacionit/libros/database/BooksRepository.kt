package com.educacionit.libros.database

import android.content.Context
import com.educacionit.libros.model.Libro
import com.j256.ormlite.android.apptools.OpenHelperManager
import com.j256.ormlite.dao.Dao
import java.sql.SQLException

class BooksRepository(context: Context) {
    private lateinit var booksDao: Dao<Libro, Int>

    init {
        val helperInstance = OpenHelperManager.getHelper(context, DBHelper::class.java)
        try {
            booksDao = helperInstance.getDao(Libro::class.java)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun getBooks(): List<Libro> = booksDao.queryForAll()

    fun addBook(book: Libro): ResultDBOperation = try {
        booksDao.create(book)
        ResultOk
    } catch (e: SQLException) {
        e.printStackTrace()
        ResultError
    }
}

sealed class ResultDBOperation
object ResultOk : ResultDBOperation()
object ResultError : ResultDBOperation()