package com.pearl.normandy.utils
{
	import ilog.gantt.ResourceChart;
	
	public class GanttUtil
	{
	      public static function isResource(resourceChart:ResourceChart, item:Object):Boolean
	      {
	        return item && (item[resourceChart.resourceIdField] != null);        
	      }      	
	}
}