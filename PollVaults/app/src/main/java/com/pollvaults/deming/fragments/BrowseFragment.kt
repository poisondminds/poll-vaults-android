package com.pollvaults.deming.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pollvaults.deming.R

class BrowseFragment : Fragment()
{
	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
	{
		return inflater?.inflate(R.layout.fragment_browse, container, false)
	}
}
