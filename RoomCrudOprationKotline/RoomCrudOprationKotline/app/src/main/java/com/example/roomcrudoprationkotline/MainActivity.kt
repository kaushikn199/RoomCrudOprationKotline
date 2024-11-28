package com.example.roomcrudoprationkotline

import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var demoCollectionAdapter: ViewPagerAdapter
    lateinit var viewpager: ViewPager2
    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)


        viewpager = findViewById<ViewPager2>(R.id.tabViewpager)
        var tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        demoCollectionAdapter = ViewPagerAdapter(this)
        viewpager.adapter = demoCollectionAdapter

        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            if (position == 0) {
                tab.text = "Add"
            } else if (position == 1) {
                tab.text = "Display"
            }
        }.attach()


    }

    fun navigateToPage(position: Int,note: Note) {
        viewpager.currentItem = position // Navigate to the specified page
    }

    fun getSharedViewModel(): SharedViewModel {
        return viewModel
    }


}