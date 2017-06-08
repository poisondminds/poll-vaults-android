package com.pollvaults.deming.models.firebase

class PollModel : FirebaseModel()
{
	var lastResponse: Long? = null
	var options = hashMapOf<String, OptionModel>()
	var responseCount: Int = 0
	var tags = hashMapOf<String, Boolean>()
	lateinit var title: String

	companion object : Queryable
	{
		override val collectionRef = "polls"
	}
}