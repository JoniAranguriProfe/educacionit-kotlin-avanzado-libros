package com.educacionit.libros

import android.content.Intent
import android.content.Intent.ACTION_AIRPLANE_MODE_CHANGED
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var rvLibros: RecyclerView
    private var adapter: LibrosAdapter = LibrosAdapter {
        Toast.makeText(this@HomeActivity, it.nombre, Toast.LENGTH_SHORT).show()
    }
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val libroAgregado = result.data?.getBooleanExtra(LIBRO, false) ?: false
            if (libroAgregado) refrescarLibros()
        }
    }
    private val airplaneStateReceiver = AirplaneStateReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupToolbar()
        saludarUsuario()
        setupAdapter()
        refrescarLibros()
        initializeSyncService()
        registerReceiver(airplaneStateReceiver, IntentFilter(ACTION_AIRPLANE_MODE_CHANGED));
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Mis Libros"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_agregar) {
            goToAgregarLibro()
        } else if (item.itemId == R.id.item_favoritos) {
            goToFavoritos()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToFavoritos() {
        startActivity(
            Intent(
                this, LibrosFavoritosActivity::class.java
            )
        )
    }

    private fun goToAgregarLibro() {
        startForResult.launch(
            Intent(this@HomeActivity, AgregarLibroActivity::class.java)
        )
    }

    private fun setupAdapter() {
        rvLibros = findViewById(R.id.rvLibros)
        rvLibros.adapter = adapter
    }

    private fun refrescarLibros() {
        lifecycleScope.launch(Dispatchers.IO) {
            val libros = LibrosRepository(this@HomeActivity).getLibros()
            withContext(Dispatchers.Main) {
                adapter.libros = libros
            }
        }
    }

    private fun initializeSyncService() {
        val intent = Intent(this, SyncService::class.java)
        startService(intent)
    }

    override fun onDestroy() {
        stopService(Intent(this, SyncService::class.java))
        super.onDestroy()
    }

    private fun saludarUsuario() {
        val bundle = intent.extras
        if (bundle != null) {
            val usuario = bundle.getString("USUARIO")
            Toast.makeText(this@HomeActivity, "Bienvenido $usuario", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val LIBRO = "Libro"
    }
}