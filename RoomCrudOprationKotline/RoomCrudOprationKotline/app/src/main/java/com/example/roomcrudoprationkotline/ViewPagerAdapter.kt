package com.example.roomcrudoprationkotline

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.roomcrudoprationkotline.fragments.DisplayDataFragment
import com.example.roomcrudoprationkotline.fragments.EnterDataFragment


class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int).
        val fragment: Fragment
         if (position == 0){
            fragment = EnterDataFragment()
        }else{
            fragment = DisplayDataFragment()
        }
        /*fragment.arguments = Bundle().apply {
            // The object is just an integer.
            putInt(ARG_OBJECT, position + 1)
        }*/
        return fragment
    }
}