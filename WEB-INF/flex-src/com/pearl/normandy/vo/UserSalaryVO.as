package com.pearl.normandy.vo
{
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.userSalary.UserSalary")]
	
	public class UserSalaryVO
	{
		public function UserSalaryVO()
		{
		}
		
		public var id:int;
		public var userId:int;
		public var salary:String				= "0";
		public var insurance:String				= "0";
		public var lunchBenefit:String			= "0";
		public var socialBenefitType1:String	= "0";
		public var socialBenefitType2:String	= "0";
		public var houseFound:String			= "0";
		public var startTime:Date;
		public var creatorId:int;
		public var createTime:Date;

	}
}