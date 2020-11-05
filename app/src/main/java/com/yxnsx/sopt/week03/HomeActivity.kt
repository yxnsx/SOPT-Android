package com.yxnsx.sopt.week03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yxnsx.sopt.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_profile.viewPager
import kotlin.properties.Delegates


class HomeActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewPager.addOnPageChangeListener(this)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }


    private val navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {

            var index by Delegates.notNull<Int>()
            when (it.itemId) {
                R.id.menu_profile -> index = 0
                R.id.menu_list -> index = 1
                R.id.menu_empty -> index = 2
            }
            viewPager.currentItem = index
            true
        }

    override fun onPageScrollStateChanged(state: Int) {}
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    override fun onPageSelected(position: Int) {
        bottomNavigationView.menu.getItem(position).isChecked = true
    }
}