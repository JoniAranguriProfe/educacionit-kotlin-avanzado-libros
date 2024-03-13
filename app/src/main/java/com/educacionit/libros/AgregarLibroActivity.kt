package com.educacionit.libros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.educacionit.libros.database.BooksRepository
import com.educacionit.libros.database.ResultError
import com.educacionit.libros.database.ResultOk
import com.educacionit.libros.model.Libro

class AgregarLibroActivity : AppCompatActivity() {
    private lateinit var etNombreLibro: EditText
    private lateinit var etAutor: EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_libro)
        setupUI()
    }

    private fun setupUI() {
        etNombreLibro = findViewById(R.id.etNombreLibro)
        etAutor = findViewById(R.id.etAutor)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener { guardarLibro() }
    }

    private fun guardarLibro() {
        if (datosValidos()) {
            val libro = Libro()
            libro.nombre = etNombreLibro.text.toString()
            libro.autor = etAutor.text.toString()
            setResult(saveBook(libro))
            finish()
        } else {
            Toast.makeText(
                    this@AgregarLibroActivity,
                    "Completar todos los campos",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun saveBook(newBook: Libro): Int {
        return when (BooksRepository(this).addBook(newBook)) {
            is ResultError -> RESULT_CANCELED
            is ResultOk -> RESULT_OK
        }
    }

    private fun datosValidos(): Boolean {
        var datosValidos = true
        if (etNombreLibro.text.toString().isEmpty() || etAutor.text.toString().isEmpty()) {
            datosValidos = false
        }
        return datosValidos
    }
}