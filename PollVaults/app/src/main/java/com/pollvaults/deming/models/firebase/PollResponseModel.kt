package com.pollvaults.deming.models.firebase

class PollResponseModel: HashMap<String, ResponseModel>()
{
	companion object
	{
		val collectionRef = "pollResponses"
	}
}