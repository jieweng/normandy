package com.pearl.normandy.validator
{

import com.pearl.normandy.enum.ImageEnum;

import mx.containers.HBox;
import mx.controls.Image;
import mx.utils.StringUtil;
import mx.validators.StringValidator;
import mx.validators.ValidationResult;

[ResourceBundle("validators")]
	
	public class DuplicateValidator extends StringValidator
	{ 
		public function DuplicateValidator()
		{
			super();
		}
		
		override protected function doValidation(value:Object):Array{

			super.doValidation(value);
			return validatorDuplicate(this, value, null);
			  
		}
		
		private function validatorDuplicate(validator:DuplicateValidator, value:Object,	baseField:String = null):Array{
			if(doValidate){
				return [];
			}
			var resultArr:Array = [];
			for each(var p:Object in results){				
				if(validatorObject && StringUtil.trim(value.toString().toUpperCase()) == p[validatorObject].toString().toUpperCase()){
					resultArr.push(new ValidationResult(true, baseField, validatorType, errorMessage));					
				}
			}
			
			if(required && (StringUtil.trim(value.toString()) == "" || value.toString() == null)){
				resultArr.push(new ValidationResult(true, baseField, validatorType, "This field is required"));
			}
 			if(validateParent && validateParent.numChildren >= 2){
 				for(var i:int = 2; i < validateParent.numChildren; i++){
 					(validateParent as HBox).removeChildAt(i);
 				}
				(validateParent as HBox).removeChildAt(1);
			}
			if(resultArr.length == 0 && validateParent){
				var rightImage:Image = new Image();
				rightImage.source = ImageEnum.IMAGECLASS_LEFT;
				(validateParent as HBox).addChild(rightImage);
			}else if(validateParent){
				var errorImage:Image = new Image();
				errorImage.source = ImageEnum.IMAGECLASS_RIGHT;
				(validateParent as HBox).addChild(errorImage);
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
		
		private var _validatorType:String;
		public function set validatorType(val:String):void{
		
			_validatorType = val;
			
		}

		public function get validatorType():String{
		
			return _validatorType;
		}	
		
		private var _validatorObject:String;
		public function set validatorObject(val:String):void{
		
			_validatorObject = val;
		}
		
		public function get validatorObject():String{
		
			return _validatorObject;
		}
		
		private var _errorMessage:String;
		
		public function set errorMessage(val:String):void{
		
			_errorMessage = val;
		}
		
		public function get errorMessage():String{
			return _errorMessage;
		}
		
		private var _validateParent:Object;
		public function set validateParent(val:Object):void{
			_validateParent = val;
		}
		public function get validateParent():Object{
			return _validateParent;
		}
		
		private var _doValidate:Boolean = false;
		public function set doValidate(val:Boolean):void{
			_doValidate = val;
		}
		
		public function get doValidate():Boolean{
			return _doValidate;
		}
	}
}