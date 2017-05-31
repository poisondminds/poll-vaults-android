package com.pollvaults.deming.models.firebase

import android.graphics.Color

class OptionModel : FirebaseModel()
{
	lateinit var text: String
	lateinit var color: String
	lateinit var icon: String
	var voteCount: Int = 0

	val colorInt: Int get()
	{
		return Color.parseColor("#${this.color}")
	}
}