package com.pollvaults.deming.models.firebase

import com.google.firebase.database.DataSnapshot

class ResponseModel: FirebaseModel()
{
	lateinit var option: String
	lateinit var user: String

	companion object: FirebaseModel.Creatable<ResponseModel>
	{
		override fun FromSnapshot(snapshot: DataSnapshot): ResponseModel
		{
			val model = snapshot.getValue(ResponseModel::class.java)
			model.key = snapshot.key

			return model
		}
	}
}