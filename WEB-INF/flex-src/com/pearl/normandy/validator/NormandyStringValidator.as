package com.pearl.normandy.validator
{
	import mx.collections.ArrayCollection;
	import mx.utils.StringUtil;
	import mx.validators.StringValidator;
	import mx.validators.ValidationResult;
	
	[ResourceBundle("validators")]
	
	//在StringValidator的基础上增加验证重复
	public class NormandyStringValidator extends StringValidator{
		
		private var results:Array;
		public var collection:ArrayCollection;		
		public var field:String;
		public var validatedObject:Object;
		public var errorMessageString:String;
		public var ignoreFunction:Function;
		public function NormandyStringValidator(){
			super();
		}
		
		override protected function doValidation(value:Object):Array{
			results = [];
			results = super.doValidation(value);
			if(null!=collection){
				var arr:Array=collection.source;
				value=StringUtil.trim(value.toString().toUpperCase());
				for(var i:int=0;i<arr.length;i++){
					var object:Object=arr[i];
					if(value == object[field].toString().toUpperCase() && (null==ignoreFunction || !ignoreFunction(object))){
						if(validatedObject.id == 0){
							results.push(new ValidationResult(true, "", "", errorMessageString));
						}else{
							if(validatedObject.id != object.id){
								results.push(new ValidationResult(true, "", "", errorMessageString));
							}
						}	
					}
				}
			}
			return results;
		} 
	}
}