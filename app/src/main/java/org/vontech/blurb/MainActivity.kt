package org.vontech.blurb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

/**
 * The main activity of the Blurb application, which handles the management
 * of each Blurb screen
 */
class MainActivity : AppCompatActivity() {

    private lateinit var vPager: ViewPager
    private lateinit var blurbAdapter: ContentFragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Grab and store the ViewPager
        vPager = viewPager

        // Create and setup the adapter
        blurbAdapter = ContentFragmentPagerAdapter(supportFragmentManager)
        vPager.adapter = blurbAdapter

        // Start on the first page
        vPager.currentItem = 1

    }

}
