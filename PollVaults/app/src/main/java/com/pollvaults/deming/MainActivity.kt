package com.pollvaults.deming

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.pollvaults.deming.fragments.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		setupNav()

		showFragment(TrendingFragment())
	}

	override fun onBackPressed()
	{
		val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
		if (drawer.isDrawerOpen(GravityCompat.START))
		{
			drawer.closeDrawer(GravityCompat.START)
		}
		else
		{
			super.onBackPressed()
		}
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean
	{
		when (item.itemId)
		{
			R.id.nav_trending -> showFragment(TrendingFragment())
			R.id.nav_browse   -> showFragment(BrowseFragment())
			R.id.nav_search   -> showFragment(SearchFragment())
			R.id.nav_profile  -> showFragment(ProfileFragment())
			R.id.nav_settings -> showFragment(SettingsFragment())
		}

		val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
		drawer.closeDrawer(GravityCompat.START)
		return true
	}

	private fun showFragment(frag: Fragment)
	{
		this.supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, frag).commit();
	}

	private fun setupNav()
	{
		val toolbar = findViewById(R.id.toolbar) as Toolbar
		setSupportActionBar(toolbar)

		val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
		val toggle = ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer.addDrawerListener(toggle)
		toggle.syncState()

		val navigationView = findViewById(R.id.nav_view) as NavigationView
		navigationView.setNavigationItemSelectedListener(this)
	}
}
