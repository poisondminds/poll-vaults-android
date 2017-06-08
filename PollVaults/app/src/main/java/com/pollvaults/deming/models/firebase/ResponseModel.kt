package com.pollvaults.deming.models.firebase

class ResponseModel: FirebaseModel()
{
	var created: Long = 0
	lateinit var option: String
	lateinit var user: String
}