package com.pollvaults.deming.models.firebase

import com.google.firebase.database.DataSnapshot

class PollModel : FirebaseModel()
{
	lateinit var title: String
	var options = hashMapOf<String, OptionModel>()

	companion object: FirebaseModel.Creatable<PollModel>
	{
		val collectionRef = "polls"

		override fun FromSnapshot(snapshot: DataSnapshot): PollModel
		{
			val model = snapshot.getValue(PollModel::class.java)
			model.key = snapshot.key

			return model
		}
	}
}