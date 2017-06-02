package com.pollvaults.deming.models.firebase

import com.google.firebase.database.DataSnapshot

abstract class FirebaseModel
{
	lateinit var key: String
}

interface Queryable
{
	val collectionRef: String
}

inline fun <reified T : FirebaseModel> DataSnapshot.toFirebaseModel() =
		this.getValue(T::class.java).also { it.key = this.key}

fun <T: FirebaseModel> HashMap<String, T>.valueList(): List<T>
{
	return this.map {
		it.value.key = it.key
		it.value
	}
}