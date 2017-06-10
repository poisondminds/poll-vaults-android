package com.pollvaults.deming.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pollvaults.deming.models.firebase.PollModel

class PollAdapter(val polls : List<PollModel>) : RecyclerView.Adapter<PollViewHolder>()
{
	override fun onBindViewHolder(holder: PollViewHolder, position: Int)
	{
		val textView = holder.itemView as TextView
		textView.setText(this.polls.get(position).title)
	}

	override fun getItemCount(): Int
	{
		return this.polls.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, position: Int): PollViewHolder
	{
		val v = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, null)
		return PollViewHolder(v)
	}
}

class PollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
