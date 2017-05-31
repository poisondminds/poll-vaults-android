package com.pollvaults.deming.models.firebase

import com.google.firebase.database.DataSnapshot

abstract class FirebaseModel
{
	lateinit var key: String

	interface Creatable<T>
	{
		fun FromSnapshot(snapshot: DataSnapshot): T
	}
}

fun <T: FirebaseModel> HashMap<String, T>.valueList(): List<T>
{
	return this.map {
		it.value.key = it.key
		it.value
	}
}