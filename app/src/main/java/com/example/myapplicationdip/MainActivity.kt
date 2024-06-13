package com.example.myapplicationdip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplicationdip.classProj.PersonalInfo
import com.example.myapplicationdip.databinding.ActivityMainBinding
import com.example.myapplicationdip.db.DBHelperFood
import com.example.myapplicationdip.db.DBHelperPerson
import com.example.myapplicationdip.fragments.HomeFragment
import com.example.myapplicationdip.fragments.ProfileFragment
import com.example.myapplicationdip.fragments.StatistsFragment
import com.google.android.material.navigation.NavigationView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var drawerLayout: DrawerLayout

    private var dB = DBHelperPerson(this, null)
    private var person = PersonalInfo()

    private lateinit var bin: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bin.root)

        setSupportActionBar(bin.toolbar)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(
            this,
            bin.drawerLayout,
            bin.toolbar,
            R.string.open_nav,
            R.string.close_nav
        )
        bin.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)

        }
    }

    override fun onResume() {
        super.onResume()

        person = dB.readDbData()
        if (person.age == 0) {
            var int = Intent(this, FirstUser::class.java)
            startActivity(int)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            R.id.nav_profile -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment()).commit()
            R.id.nav_statist-> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StatistsFragment()).commit()
            R.id.nav_logout -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        }
        bin.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (bin.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            bin.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}