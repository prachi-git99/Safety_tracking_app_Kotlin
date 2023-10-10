package com.example.safetytrackingapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private  val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.INTERNET,
        Manifest.permission.READ_CONTACTS

    )

    val permissionCode = 78

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askForPermission()

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottom_bar)

        //bottom bar k item pr selected listerner chlega on the basis of id

        bottomBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navGuard -> {
                    inflateFragment(GuardFragment.newInstance())
                }
                R.id.navHome -> {
                    inflateFragment(HomeFragment.newInstance())
                }
                R.id.navDashboard -> {
                    inflateFragment(MapsFragment.newInstance())
                }
                R.id.navProfile -> {
                    inflateFragment(ProfileFragment.newInstance())
                }
            }

            true
        }
        //default selected
        bottomBar.selectedItemId = R.id.navHome

    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this,permissions,permissionCode)
    }


    private fun inflateFragment(newInstance: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,newInstance)
        transaction.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == permissionCode){
            if(allPermissionGranted()){

                //openCamera()
            }else{

            }
        }
    }

    private fun openCamera() {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intent)
    }

    private fun allPermissionGranted(): Boolean {
        for (item in permissions){
            if(ContextCompat.checkSelfPermission(this,item) != PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }

}