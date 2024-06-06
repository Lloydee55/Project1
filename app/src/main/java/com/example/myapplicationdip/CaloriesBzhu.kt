package com.example.myapplicationdip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplicationdip.Constants.Constants
import com.example.myapplicationdip.classProj.PersonalInfo
import com.example.myapplicationdip.databinding.ActivityCaloriesBzhuBinding
import com.example.myapplicationdip.db.DBHelperPerson
import com.google.android.material.navigation.NavigationView

@Suppress("DEPRECATION")
class CaloriesBzhu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var bindingClass : ActivityCaloriesBzhuBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityCaloriesBzhuBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        var per = intent.getIntExtra(Constants.TRANC_CAL_ACT, 0)
        var per2 : Int
        bindingClass.apply {
            percentagesProt.text = "20"
            percentagesFast.text = "30"
            percentagesCorboh.text = "50"

            textMainCalculations.text = per.toString()
            per2 = per * 30 / 100 / 4
            gramsProt.text = per2.toString()
            per2 = per * 20 / 100 / 9
            gramsFats.text = per2.toString()
            per2 = per * 50 / 100 / 4
            gramsCarboh.text = per2.toString()


        }

    }

    fun onClickBack(view: View) {
        finish()
    }

    fun onClickSave(view: View) {
        var int = Intent(this, Prifile::class.java)
        startActivity(int)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                var int = Intent(this, Prifile::class.java)
                startActivity(int)
            }
            R.id.nav_settings -> {
                var int = Intent(this, Prifile::class.java)
                startActivity(int)
            }
            R.id.nav_share -> {
                var int = Intent(this, Prifile::class.java)
                startActivity(int)
            }
            R.id.nav_about -> {
                var int = Intent(this, Prifile::class.java)
                startActivity(int)
            }
            R.id.nav_logout -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}