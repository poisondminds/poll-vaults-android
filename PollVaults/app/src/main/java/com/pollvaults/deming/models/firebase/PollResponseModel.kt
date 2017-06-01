package com.pollvaults.deming.models.firebase

class PollResponseModel: HashMap<String, ResponseModel>()
{
	companion object: Queryable
	{
		override val collectionRef = "pollResponses"
	}
}