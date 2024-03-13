package com.educacionit.libros.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource

class DBHelper(context: Context) : OrmLiteSqliteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object{
        const val DB_NAME = "books"
        const val DB_VERSION = 1
    }
}