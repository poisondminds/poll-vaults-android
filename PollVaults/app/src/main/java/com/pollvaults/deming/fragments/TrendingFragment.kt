package com.pollvaults.deming.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pollvaults.deming.R
import com.pollvaults.deming.models.firebase.PollModel
import com.pollvaults.deming.models.firebase.toFirebaseModel
import com.pollvaults.deming.models.firebase.valueList
import kotlinx.android.synthetic.main.fragment_trending.*

class TrendingFragment : Fragment()
{

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
	{
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
					val poll: PollModel = snapshot.toFirebaseModel()

					val entryList = ArrayList<BarEntry>()

					poll.options.valueList().forEachIndexed { index, optionModel ->

						val entry = BarEntry(index+1.toFloat(), optionModel.voteCount.toFloat())
						entryList.add(entry)
					}

					val barData = BarDataSet(entryList, "Something")
					val barData2 = BarData(barData)
					barData2.barWidth = 0.9F

					barChart.data = barData2
					barChart.invalidate()
				}
			}

		})

		return inflater?.inflate(R.layout.fragment_trending, container, false)
	}
}
