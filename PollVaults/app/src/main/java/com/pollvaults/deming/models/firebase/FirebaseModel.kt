package com.pollvaults.deming.models.firebase

import com.google.firebase.database.DataSnapshot

abstract class FirebaseModel
{
	lateinit var key: String

	companion object
	{
		inline fun <reified T : FirebaseModel> FromSnapshot(snapshot: DataSnapshot): T
		{
			val model = snapshot.getValue(T::class.java)
			model.key = snapshot.key
			return model
		}
	}
}

interface Queryable
{
	val collectionRef: String
}

fun <T: FirebaseModel> HashMap<String, T>.valueList(): List<T>
{
	return this.map {
		it.value.key = it.key
		it.value
	}
}