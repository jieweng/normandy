package com.pearl.normandy.controls
{
	import mx.controls.DateField;
	import com.pearl.normandy.utils.NormandyModel;
	
	public class NormandyDateField extends DateField
	{		
		public function NormandyDateField()
		{
			super();
			this.disabledDays = NormandyModel.getInstance().nonWorkingDays;
			this.disabledRanges = NormandyModel.getInstance().nonWorkingRanges;			
		}
	}
}