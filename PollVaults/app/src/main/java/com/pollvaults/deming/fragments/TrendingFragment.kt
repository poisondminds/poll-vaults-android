package com.pollvaults.deming.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pollvaults.deming.R
import com.pollvaults.deming.adapters.PollAdapter
import com.pollvaults.deming.models.firebase.PollModel
import com.pollvaults.deming.models.firebase.toFirebaseModel

class TrendingFragment : Fragment()
{
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
	{
		val v = inflater.inflate(R.layout.fragment_trending, container, false)
		val polls = ArrayList<PollModel>()
		val pollsRecyclerView: RecyclerView = v.findViewById(R.id.pollsRecyclerView) as RecyclerView // TOOD: no more

		val ref = FirebaseDatabase.getInstance().getReference("polls").addListenerForSingleValueEvent(object: ValueEventListener
		{
			override fun onCancelled(p0: DatabaseError?)
			{
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun onDataChange(snapshot: DataSnapshot)
			{
				snapshot.children.forEach { polls.add(it.toFirebaseModel()) }

				val adapter = PollAdapter(polls)

				pollsRecyclerView.adapter = adapter
				pollsRecyclerView.layoutManager = LinearLayoutManager(activity)
			}

		})




		return v
	}
}
