package com.pearl.normandy.validator
{

import mx.validators.StringValidator;
import mx.validators.ValidationResult;

[ResourceBundle("validators")]
	
	public class LeastValidator extends StringValidator
	{ 
		public function LeastValidator()
		{
			super();
		}
		
		override protected function doValidation(value:Object):Array{
		
			super.doValidation(value);
			 return validatorSize(this, value, null);
			
		}
		
		private function validatorSize(validator:LeastValidator, 
											value:Object,
											baseField:String = null
											):Array{
		
			var resultArr:Array = [];
			for each(var result:Object in results){
			
				if(value == "" || Number(value) > Number(result)){
					
					resultArr.push(new ValidationResult(
						true, baseField, validatorTpye,
						errorMessage));
						}
			}
			return resultArr;
		}
		
		private var _results:Array;
		
		public function set results(val:Array):void{
		
			_results = val;
		}
		
		public function get results():Array{
		
			return _results;
		}
		
		private var _validatorTpye:String;
		public function set validatorTpye(val:String):void{
		
			_validatorTpye = val;
			
		}

		public function get validatorTpye():String{
		
			return _validatorTpye;
		}	
		
		private var _errorMessage:String;
		
		public function set errorMessage(val:String):void{
		
			_errorMessage = val;
		}
		
		public function get errorMessage():String{
		
			return _errorMessage;
		}
	}
}