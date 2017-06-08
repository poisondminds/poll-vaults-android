package com.pollvaults.deming.models.firebase

class UserModel : FirebaseModel()
{
	var created: Long = 0
	var premium: Boolean = false
}
