package com.example.miwok.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.miwok.fragments.ColorsFragment
import com.example.miwok.fragments.FamilyFragment
import com.example.miwok.fragments.NumbersFragment
import com.example.miwok.fragments.PhrasesFragement

class TabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        // Hardcoded in this order, you'll want to use lists and make sure the titles match
        return when (position) {
            0 -> NumbersFragment()
            1 -> ColorsFragment()
            2 -> FamilyFragment()
            3 -> PhrasesFragement()
           else -> NumbersFragment()
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}