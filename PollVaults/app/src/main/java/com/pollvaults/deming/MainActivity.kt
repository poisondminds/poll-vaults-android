package com.pollvaults.deming

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.database.*
import com.pollvaults.deming.models.firebase.*
import kotlinx.android.synthetic.main.content_main.*
import java.nio.channels.Selector

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
{

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val toolbar = findViewById(R.id.toolbar) as Toolbar
		setSupportActionBar(toolbar)

		val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
		val toggle = ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer.addDrawerListener(toggle)
		toggle.syncState()

		val navigationView = findViewById(R.id.nav_view) as NavigationView
		navigationView.setNavigationItemSelectedListener(this)


		val pollRef = FirebaseDatabase.getInstance().getReference(PollModel.collectionRef).child("one")

		pollRef.addValueEventListener(object : ValueEventListener
		{

			override fun onCancelled(error: DatabaseError?)
			{
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun onDataChange(snapshot: DataSnapshot?)
			{
				if (snapshot != null)
				{
					val poll: PollModel = FirebaseModel.FromSnapshot(snapshot)

					titleTextView.setText(poll.title)

					val barWidth = barLayout.measuredWidth / (poll.options.size + 1)



					val maxHeight = barLayout.measuredHeight
					val maxVotes = poll.options.valueList().maxBy { it.voteCount }?.voteCount ?: 0

					poll.options.valueList().forEach {

						val barView = View(this@MainActivity)

						val multiplier = it.voteCount / maxVotes.toFloat()
						val barHeight = (maxHeight * multiplier).toInt()
						val barParams = LinearLayout.LayoutParams(barWidth, barHeight)
						barParams.leftMargin = 20
						barParams.rightMargin = 20
						barView.layoutParams = barParams

						barView.isClickable = true
						barView.setOnClickListener { v ->

							val responseRef = FirebaseDatabase.getInstance()
									.getReference(PollResponseModel.collectionRef)
									.child(poll.key)
									.orderByChild("option")
									.equalTo(it.key)
							responseRef.addValueEventListener(object: ValueEventListener
							{
								override fun onCancelled(p0: DatabaseError?)
								{
									TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
								}

								override fun onDataChange(snapshot: DataSnapshot)
								{
									val shittyType = object : GenericTypeIndicator<HashMap<String, ResponseModel>>() {}
									val responses = snapshot.getValue(shittyType)

									var disp = ""
									responses.values.forEach { disp += "${it.user}\n" }
									Toast.makeText(this@MainActivity, disp, Toast.LENGTH_SHORT).show()
								}

							})
						}

						barView.setBackgroundColor(it.colorInt)

						barLayout.addView(barView)


						barView.requestLayout()
					}
				}
			}

		})
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

	override fun onCreateOptionsMenu(menu: Menu): Boolean
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		when (item.itemId)
		{
			R.id.action_settings -> return true
			else                 -> return super.onOptionsItemSelected(item)
		}
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean
	{
		// Handle navigation view item clicks here.
		when (item.itemId)
		{
			R.id.nav_camera    ->
			{
				// Handle the camera action
			}
			R.id.nav_gallery   ->
			{

			}
			R.id.nav_slideshow ->
			{

			}
			R.id.nav_manage    ->
			{

			}
			R.id.nav_share     ->
			{

			}
			R.id.nav_send      ->
			{

			}
		}

		val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
		drawer.closeDrawer(GravityCompat.START)
		return true
	}
}
