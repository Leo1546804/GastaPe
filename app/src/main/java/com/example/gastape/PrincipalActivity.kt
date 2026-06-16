package com.example.gastape

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class PrincipalActivity : AppCompatActivity() {

    private lateinit var menuInferior: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationLateral: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)

        drawerLayout = findViewById(R.id.drawer_layout)
        menuInferior = findViewById(R.id.menu_inferior)
        navigationLateral = findViewById(R.id.navigation_lateral)

        // 3. Cargar la pantalla de Inicio por defecto
        if (savedInstanceState == null) {
            cambiarFragmento(InicioFragment())
        }

        menuInferior.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    cambiarFragmento(InicioFragment())
                    true
                }
                R.id.bottom_gastos ->{
                    cambiarFragmento(GastoFragment())
                    true
                }
                R.id.bottom_add ->{
                    startActivity(
                        Intent(this, AgregarGastoActivity::class.java)
                    )
                    false
                }
                R.id.bottom_estadisticas ->{
                    cambiarFragmento(EstadisticaFragment())
                    true
                }
                R.id.bottom_perfil ->{
                    drawerLayout.openDrawer(GravityCompat.START)

                    // Devolvemos 'false' para que el indicador visual no se quede estancado
                    // en la pestaña de perfil, ya que el menú lateral se superpondrá.
                    false
                }
                else -> true
            }
        }

        navigationLateral.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_inicio -> {
                    cambiarFragmento(InicioFragment())
                    // Esto hace que el menú inferior también se pinte en "Home" si se seleccionó desde el lateral
                    menuInferior.selectedItemId = R.id.bottom_home
                }
                R.id.nav_gastos -> {
                    cambiarFragmento(GastoFragment())
                    menuInferior.selectedItemId = R.id.bottom_gastos
                }
                R.id.nav_categorias -> {
                    cambiarFragmento(CategoriaFragment())
                }
                R.id.nav_estadisticas -> {
                    cambiarFragmento(EstadisticaFragment())
                    menuInferior.selectedItemId = R.id.bottom_estadisticas
                }
                R.id.nav_presupuesto -> {
                    cambiarFragmento(EstadisticaFragment())
                }
                R.id.nav_recordatorios -> {
                    cambiarFragmento(EstadisticaFragment())
                }
            }
            // Al hacer clic en cualquier opción, cerramos el menú lateral automáticamente
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun cambiarFragmento(fragmento: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedor_fragmentos, fragmento)
            .commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}