package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val savedNutritionData = mutableListOf<NutritionData>()
    private lateinit var recy: RecyclerView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setContentView(R.layout.activity_main)

        //get the fragments
        val logFragment: Fragment = LogFragment()
        val dashBoardFragment: Fragment = DashBoardFragment()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigation)
        Log.e("GD", "Test5")
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.logID -> fragment = logFragment
                R.id.dashBoardID -> fragment = dashBoardFragment
            }

            replaceFragment(fragment)
            true
        }
        bottomNavigationView.selectedItemId = R.id.logID

    }

    private fun replaceFragment(foodListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.food_frame_layout, foodListFragment)
        fragmentTransaction.commit()
        Log.e("GD", "Test1")
    }

    fun onAddNewFoodClicked() {
        val intent: Intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivity(intent)
    }



/*
    private fun replaceFragment(logFragment: LogFragment) {
        val fragmentManager = supportFragmentManager
        Log.e("GD", "Test1")
        val fragmentTransaction = fragmentManager.beginTransaction()
        Log.e("GD", "Test2")
        fragmentTransaction.replace(R.id.food_frame_layout, logFragment)
        Log.e("GD", "Test3")
        fragmentTransaction.commit()
        Log.e("GD", "Test4")
    }
*/


}




