package com.pearl.normandy.vo
{
	import com.pearl.normandy.utils.Constants;
	
	import flash.utils.ByteArray;
	
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.user.UserTo")]	
	
	public class UserVO
	{
		public function UserVO()
		{
		}
		public var id:int;
		public var userType:String;
	    public var userGroupId:int;
	    public var customerId:int;	    
		public var firstName:String;
		public var lastName:String;
		public var englishFirstName:String;
		public var englishLastName:String;
		public var userName: String;		
		public var levelId:int;
		public var passwordHash:String;
		public var employeeNo:String;
		public var email: String;
		public var manager:String;
		public var deleted:String = "N";
		public var version:int;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;	
		public var entryDate:Date;
		public var leaveDate:Date;
	    
		public var name: String;
		public var fullName:String;	    
	    public var userGroupName:String;    
	    public var state:String;
		public var productionGroup:String;	    
	    public var customerName:String;
	    public var member:String;	
	    public var projectsId:String   = "";
	    public var projectsName:String = "";
	    public var searchStr:String;
	
		public var year:int;
		public var month:int;	
		public var projects:ArrayCollection;	   
		
		public var employeeDate:Date;
		public var department:String;
		public var position:String;
		public var birthday:Date;
		public var gender:String = Constants.USER_GENDER_MALE;
		public var recommendorName:String = "";
		public var recommendationBonus:Number = 0;
		public var recommendationBonusPayment:Number = 0;
		public var recommendationBonusPaymentTime:Date;
		public var mobile:String;
		public var home:String;
		public var personalEmail:String;
		public var idNo:String;
		public var idAddress:String;
		public var hukouAddress:String;
		public var shangHaiAddress:String;
		public var paidLeave:Number = 0;
		public var photo:ByteArray;
		
		[Transient]
		public var isSelected:Boolean; 	
	}
}