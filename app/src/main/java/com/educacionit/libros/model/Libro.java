package com.educacionit.libros.model;

import static com.educacionit.libros.model.Libro.TABLE_BOOK;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = TABLE_BOOK)
public class Libro implements Serializable {
    public static final String TABLE_BOOK = "book";

    @DatabaseField(id = true)
    private Integer id;
    @DatabaseField
    private String nombre;
    @DatabaseField

    private String autor;

    public Libro() {
    }

    public Libro(Integer id, String nombre, String autor) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
