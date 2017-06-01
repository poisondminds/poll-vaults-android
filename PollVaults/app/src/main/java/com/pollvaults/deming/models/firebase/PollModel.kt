package com.pollvaults.deming.models.firebase

class PollModel : FirebaseModel()
{
	lateinit var title: String
	var options = hashMapOf<String, OptionModel>()

	companion object: Queryable
	{
		override val collectionRef = "polls"
	}
}