package com.example.miwok.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.miwok.R
import com.example.miwok.adapters.TabAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null

    //TODO: Use Json
    //TODO: Integrate Glide
    //TODO: Customize icons
    //TODO: add more stuff
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tablayout)
        val pager = findViewById<ViewPager2>(R.id.viewpager)
        val adapter = TabAdapter(supportFragmentManager, lifecycle)
        pager.adapter = adapter
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        //toolbar.inflateMenu(R.menu.menu_primary);
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.about -> {

                    startActivity(Intent(this@MainActivity, AboutActivity::class.java))

                    true
                }
                R.id.dark -> {
                    toggleTheme()
                    true
                }
                R.id.feedback -> {
                    Toast.makeText(this@MainActivity, "hehe", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        val tabLayout = findViewById<TabLayout>(R.id.tablayout)
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.category_numbers)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.category_colors)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.category_family)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.category_phrases)))
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
                when (tab.position) {
                    0 -> {
                        tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.category_numbers))
                        tabLayout.setTabTextColors(resources.getColor(R.color.disabled), resources.getColor(R.color.category_numbers))
                    }
                    1 -> {
                        tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.category_colors))
                        tabLayout.setTabTextColors(resources.getColor(R.color.disabled), resources.getColor(R.color.category_colors))
                    }
                    2 -> {
                        tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.category_family))
                        tabLayout.setTabTextColors(resources.getColor(R.color.disabled), resources.getColor(R.color.category_family))
                    }
                    3 -> {
                        tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.category_phrases))
                        tabLayout.setTabTextColors(resources.getColor(R.color.disabled), resources.getColor(R.color.category_phrases))
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        pager.registerOnPageChangeCallback(object: OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    private fun toggleTheme() {
        val mode = if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}