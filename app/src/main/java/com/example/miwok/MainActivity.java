package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.UiModeManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
//TODO: Use Json
    //TODO: Integrate Glide
    //TODO: Customize icons
    //TODO: add more stuff



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tablayout);
        ViewPager2 pager = findViewById(R.id.viewpager);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), getLifecycle());
        pager.setAdapter(adapter);
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        //toolbar.inflateMenu(R.menu.menu_primary);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.about:
                        Toast.makeText(MainActivity.this, "About", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.dark:
                        toggleTheme();
                        return true;
                    case R.id.feedback:
                        Toast.makeText(MainActivity.this, "feedback", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.category_numbers)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.category_colors)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.category_family)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.category_phrases)));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                //int color = getResources().getColor(R.color.design_default_color_primary);
                switch (tab.getPosition()) {
                    case 0:
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.category_numbers));
                        tabLayout.setTabTextColors(getResources().getColor(R.color.disabled),getResources().getColor(R.color.category_numbers));
                        break;
                    case 1:
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.category_colors));
                        tabLayout.setTabTextColors(getResources().getColor(R.color.disabled),getResources().getColor(R.color.category_colors));
                        break;
                    case 2:
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.category_family));
                        tabLayout.setTabTextColors(getResources().getColor(R.color.disabled),getResources().getColor(R.color.category_family));
                        break;
                    case 3:
                        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.category_phrases));
                        tabLayout.setTabTextColors(getResources().getColor(R.color.disabled),getResources().getColor(R.color.category_phrases));
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

    private void toggleTheme() {

        int mode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES ? AppCompatDelegate.MODE_NIGHT_NO : AppCompatDelegate.MODE_NIGHT_YES;
        AppCompatDelegate.setDefaultNightMode(mode);
    }

}