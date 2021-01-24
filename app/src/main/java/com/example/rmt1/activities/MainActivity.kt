package com.example.rmt1.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rmt1.R
import com.example.rmt1.fragments.CharactersFragment
import com.example.rmt1.fragments.EpisodesFragment
import com.example.rmt1.fragments.LocationFragment
import com.example.rmt1.helpers.MystringUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)


        val charactersFragment = CharactersFragment()
        val episodesFragment = EpisodesFragment()
        val locationsFragment = LocationFragment()

        makeCurrentFragment(MystringUtils.charactersFragment, charactersFragment)

        bn.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_characters -> makeCurrentFragment(MystringUtils.charactersFragment,charactersFragment)
                R.id.ic_episodes -> makeCurrentFragment(MystringUtils.episodesFragment,episodesFragment)
                R.id.ic_locations -> makeCurrentFragment(MystringUtils.locationFragment,locationsFragment)
            }
            true
        }

    }

    override fun onDestroy() {
        super.onDestroy()

    }
    private fun makeCurrentFragment(fragmentName: String, fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frame, fragment,fragmentName)
                        .commit()
            }

}

