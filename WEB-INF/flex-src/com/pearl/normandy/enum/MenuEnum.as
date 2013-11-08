package com.pearl.normandy.enum
{
	import mx.collections.ArrayCollection;
	
	
	public final class MenuEnum
	{
		public static const mainMenuItems:ArrayCollection = new ArrayCollection([
			{label: "项目",enabled: false, children: new ArrayCollection([
				{label: "新建项目"},
				{label: "编辑项目"},
				{label: "取消项目", enabled: false},
				{label: "确认列表"},
				{label: "创建基线", enabled: false}
			])},

			{label: "流程", children: new ArrayCollection([
		  		{label: "编辑流程"}
			])},	

			{label: "任务", enabled:false, children: new ArrayCollection([
		  		{label: "新建任务"},
		  		{label: "添加子任务"},
		  		{label: "编辑任务"},
		  		{label: "拷贝任务"},
		  		{label: "取消任务"},
				{label: "从MPP导入任务"} 
			])},
			
			{label: "资源", children: new ArrayCollection([
		  		{label: "分配人员"},
		  		{label: "分配任务"} 
			])},
			
			{label: "确认列表", children: new ArrayCollection([
		  		{label: "Lead/AD"},
		  		{label: "QA"},
		  		{label: "PM"}
			])},
			
			{label: "报表", children: new ArrayCollection([
				{label: "创建项目报表"},
				{label: "下载项目报表"},
				{label: "下载效率报表"}
			])},
			
			{label: "绩效", children: new ArrayCollection([
				{label: "录入绩效"},
				{label: "查看绩效"}
			])}										
		]);	
		
		
		public static const filterTaskTypeItems:ArrayCollection = new ArrayCollection([
		 	{label: "--All--", value: ""},
		 	{label: "Production", value: "Production"},
		 	{label: "Training", value: "Training"}
		]);	 
		
		public static const filterResourceStatusItems:ArrayCollection = new ArrayCollection([
			{label: "--All--", value: ""},
			{label: "Assigned", value: "Assigned"},
			{label: "Released", value: "Released"}
		]);	
		
		public static const filterActivityQualityItems:ArrayCollection = new ArrayCollection([
			{label: "--All--", value: 0},
			{label: "NotEdit", value: 6},
			{label: "Rain", value: 1},
			{label: "Levin", value: 2},
			{label: "Star", value: 3},
			{label: "Luna", value: 4},
			{label: "Sun", value: 5}
		]);
		
		
		public static const filterAssignmentItems:ArrayCollection = new ArrayCollection([
			{label: "--All--", value: ""},
			{label: "Assigned", value: "Assigned"},
			{label: "Not Assigned", value: "NotAssigned"}
		]);	
		
		public static const sysSettingItems:ArrayCollection = new ArrayCollection([
			{label:"setting", children: new ArrayCollection([
				{label: "Maintain Holiday"}
			])}
		]);
	}
}