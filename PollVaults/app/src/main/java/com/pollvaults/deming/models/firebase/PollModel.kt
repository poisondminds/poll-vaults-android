package com.pollvaults.deming.models.firebase

import com.google.firebase.database.DataSnapshot

class PollModel : FirebaseModel()
{
	companion object
	{
		val collectionRef = "polls"

		fun FromSnapshot(snapshot: DataSnapshot): PollModel
		{
			val model = snapshot.getValue(PollModel::class.java)
			model.key = snapshot.key

			return model
		}
	}

	lateinit var title: String
	var options = hashMapOf<String, OptionModel>()
	var responses = hashMapOf<String, ResponseModel>()
}