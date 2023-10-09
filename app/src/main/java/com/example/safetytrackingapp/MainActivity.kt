package com.example.safetytrackingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottom_bar)

        //bottom bar k item pr selected listerner chlega on the basis of id

        bottomBar.setOnItemSelectedListener {
            if(it.itemId == R.id.navGuard){
                inflateFragment(GuardFragment.newInstance())
            }
            else if(it.itemId == R.id.navHome){
                inflateFragment(HomeFragment.newInstance())
            }
            else if(it.itemId == R.id.navDashboard){
                inflateFragment(DashboardFragment.newInstance())
            }
            else if(it.itemId == R.id.navProfile){
                inflateFragment(ProfileFragment.newInstance())
            }
            true
        }

    }


    private fun inflateFragment(newInstance: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,newInstance)
        transaction.commit()
    }

}