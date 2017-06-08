package com.pollvaults.deming.models.firebase

import android.graphics.Color

class OptionModel : FirebaseModel()
{
	lateinit var color: String
	lateinit var icon: String
	var responseCount: Int = 0
	lateinit var text: String

	val colorInt: Int get()
	{
		return Color.parseColor("#${this.color}")
	}
}