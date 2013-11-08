// ActionScript file
  import mx.collections.ArrayCollection;
  import mx.collections.HierarchicalData;
  
  
  [Bindable]
  public var systemUser:ArrayCollection = new ArrayCollection([
  	"Admin", "Project Manager", "Art Production Manager", "QA Manager", "Art Director",
  	"Lead", "Artist", "QA", "Inspector" 
  ]);
  
  //-------------Project Menu Item---------------
  [Bindable]
  public var projectMenu:ArrayCollection = new ArrayCollection([
	  	{label: "New Project"},
	  	{label: "Edit Project"},
	  	{label: "Cancel Project"}
  	]);
  
  //----------------Task Menu Item---------------
  [Bindable]
  public var taskMenu:ArrayCollection = new ArrayCollection([  
  		{label: "New Task"},
  		{label: "Edit Task"},
  		{label: "Cancel Task"} 	  		
  ]);
  
  //----------------User Menu Item---------------
  [Bindable]
  public var userMenu:ArrayCollection = new ArrayCollection([  
  		{label: "New User"},
  		{label: "Edit User"},
  		{label: "Delete User"} 	  		
  ]);  
  
  //---------------Task Priority LOV--------------
  [Bindable]
  public var priorityLOV:ArrayCollection = new ArrayCollection([
  	"High", "Medium", "Low"
  ]);
  
  //---------------Task Category LOV--------------
  [Bindable]
  public var taskCategoryLOV:ArrayCollection = new ArrayCollection([
  	"Characters", "Prop", "Scene", "Vehicle", "Weapon"
  ]);
  
  //---------------Task Category LOV--------------
  [Bindable]
  public var milestoneLOV:ArrayCollection = new ArrayCollection([
  	"MSP1", "MSP2"
  ]);  
  
  //---------------Lead Check LOV--------------
  [Bindable]
  public var leadCheckLOV:ArrayCollection = new ArrayCollection([
  	"One Vote Pass", "One Vote Reject"
  ]);  
  
  //---------------Task Production LOV--------------
  [Bindable]
  public var taskProductionLOV:ArrayCollection = new ArrayCollection([
  	"Process 1", "Process 2"
  ]);  
  
  //------------System User Dummy Data-------------
  [Bindable]
  public var userArray:ArrayCollection = new ArrayCollection([
  	{employeeNo: "1001", group: "Scene/Object", userName: "MMatthews", 
  		firstName: "Matt", lastName: "Matthews", email: "matt@myco.com", privilege: "Art Production Manager"},
  	{employeeNo: "1002", group: "Scene/Object", userName: "SSanderson", 
  		firstName: "Sue", lastName: "Sanderson", email: "sue@myco.com", privilege: ""},
  	{employeeNo: "1003", group: "Character", userName: "HHarrison", 
  		firstName: "Harry", lastName: "Harrison", email: "harry@myco.com", privilege: ""}
  ]);

  //--------------Group List Dummy Data------------
  [Bindable]
  public var groupListArray:ArrayCollection = new ArrayCollection(["Character", "Scene/Object", "QA", "PM", "Admin", "Inspector"]);

  //---------------Project Dummy Data--------------
  [Bindable]
  public var projectListArray:ArrayCollection = new ArrayCollection([
  	"DICE", "Skate2", "CCP1", "CCP2", "QD"
  ]);
  
    //---------------Project Dummy Data With All Option--------------
  [Bindable]
  public var projectListWithAll:ArrayCollection = new ArrayCollection([
  	"All", "DICE", "Skate2", "CCP1", "CCP2", "QD"
  ]);
  
  //---------------Project Milestone list--------------
  [Bindable]
  public var milestoneListArray:ArrayCollection = new ArrayCollection([
  	"All", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
  ]);  
  
  //-------------Assigned/Unassigned Task LOV-------------
  [Bindable]
  public var taskLOVArray:ArrayCollection = new ArrayCollection([
  	"All Tasks", "Assigned", "Unassigned"
  ]);    
  
  //--------------Project Tree Dummy Data----------
  [Bindable]
  public var projectTreeArray:ArrayCollection = new ArrayCollection([
  {name: "DICE", label: "DICE", children: [
  	{name: "DICE_AD", label: "DICE_AD", children: [
  		{name: "Lead1", label: "Lead1", children: [
  			{name: "Artist1", label: "Artist1"},
  			{name: "Artist2", label: "Sr. Artist2"},
  			{name: "Artist3", label: "Artist3"}
  		]},
  		
  		{name: "Lead2", label: "Lead2", children: [
  			{name: "Artist4", label: "Artist4"},
  			{name: "Artist6", label: "Artist6"},
  			{name: "Artist5", label: "Artist5"}
  		]}	  			
  	]}			
  ]},
  
  {name: "Skate2", label: "Skate2", children: [
  	{name: "Skate2_AD1", label: "Skate2_AD", children: [
  		{name: "Lead1", label: "Lead1", children: [
  			{name: "Artist1", label: "Artist1"},
  			{name: "Artist2", label: "Sr. Artist2"},
  			{name: "Artist3", label: "Artist3"}
  		]},
  		
  		{name: "Lead2", label: "Lead2", children: [
  			{name: "Artist4", label: "Artist4"},
  			{name: "Artist6", label: "Artist6"},
  			{name: "Artist5", label: "Artist5"}
  		]}	  			
  	]}			
  ]},  		   	
  
  {name: "CCP1", label: "CCP1", children: [
  	{name: "CCP1_AD", label: "CCP1_AD", children: [
  		{name: "Lead1", label: "Lead1", children: [
  			{name: "Artist1", label: "Artist1"},
  			{name: "Artist2", label: "Sr. Artist2"},
  			{name: "Artist3", label: "Artist3"}
  		]},
  		
  		{name: "Lead2", label: "Lead2", children: [
  			{name: "Artist4", label: "Artist4"},
  			{name: "Artist6", label: "Artist6"},
  			{name: "Artist5", label: "Artist5"}
  		]}	  			
  	]}			
  ]},
   
  {name: "CCP2", label: "CCP2", children: [
  	{name: "CCP2_AD", label: "CCP2_AD", children: [
  		{name: "Lead1", label: "Lead1", children: [
  			{name: "Artist1", label: "Artist1"},
  			{name: "Artist2", label: "Sr. Artist2"},
  			{name: "Artist3", label: "Artist3"}
  		]},
  		
  		{name: "Lead2", label: "Lead2", children: [
  			{name: "Artist4", label: "Artist4"},
  			{name: "Artist6", label: "Artist6"},
  			{name: "Artist5", label: "Artist5"}
  		]}	  			
  	]}			
  ]},
  
  {name: "QD", label: "QD", children: [
  	{name: "QD_AD", label: "QD_AD", children: [
  		{name: "Lead1", label: "Lead1", children: [
  			{name: "Artist1", label: "Artist1"},
  			{name: "Artist2", label: "Sr. Artist2"},
  			{name: "Artist3", label: "Artist3"}
  		]},
  		
  		{name: "Lead2", label: "Lead2", children: [
  			{name: "Artist4", label: "Artist4"},
  			{name: "Artist6", label: "Artist6"},
  			{name: "Artist5", label: "Artist5"}
  		]}	  			
  	]}			
  ]}  						
  ]);  
  
  [Bindable]
  public var qaArray: ArrayCollection = new ArrayCollection ([
  	{name: "QA Manager", project: "DICE, Skate2, CCP1, CCP2"}, 
  	{name: "QA 1", project: "DICE, CCP2"}, 
  	{name: "QA 2", project: "Skate2, CCP2"}, 
  	{name: "QA 3", project: "DICE, Skate2"},
  	{name: "QA 4", project: ""},
  	{name: "QA 5", project: "CCP1"},
  	{name: "QA 6", project: ""}
  ]);

  //--------------Group Available Resources-------------
  [Bindable]
  public var resourceArray:ArrayCollection = new ArrayCollection([
	{ id: "R1", name: "Artist1", project: "DICE", noproject: 3, currentProject: "DICE, CCP1", level: "Animator" },
	{ id: "R2", name: "Artist2", project: "Skate2", noproject: 1, currentProject: "CCP2", level: "Student" },
	{ id: "R3", name: "Sr. Artist3", project: "DICE", noproject: 1, currentProject: "CCP2, Skate2", level: "Texcture Only (Junior)" },
	{ id: "R4", name: "Sr. Artist4", project: "Skate2", noproject: 2, currentProject: "DICE", level: "Animation Leader" }
  ]);
  
  //-----------Resource Capablity Dummy Data------------
	[Bindable]
	public var resourceCapabilityArray:ArrayCollection = new ArrayCollection([
		{legend: "Productivity", capability: 5},
		{legend: "Quality", capability: 3},
		{legend: "Texture", capability: 4},
		{legend: "Modeling", capability: 4},
		{legend: "Animation", capability: 1}
	]);  
	
	[Bindable]
	public var resourceCapabilityArray2:ArrayCollection = new ArrayCollection([
		{legend: "Productivity", capability: 2},
		{legend: "Quality", capability: 4},
		{legend: "Texture", capability: 2},
		{legend: "Modeling", capability: 4},
		{legend: "Animation", capability: 3}
	]);		
  
  //--------------Unassigned/Assigned Workload Dummy Data------------------------
  [Bindable]
  public var workload:ArrayCollection = new ArrayCollection([
	{taskId:"CCP-1", resourceId: "R1", taskName: "HK45", subtaskName: "Mesh", 
	name: "HK45 (Mesh)", priority: "High", completion: 0, plannedEffort: 14,
  	startTime: "5/19/2008", endTime: "6/2/2008", owner: false, feedback: false},
	{taskId:"CCP-2", resourceId: "R2", taskName: "Taurus44", subtaskName: "Low Mesh", 
	name: "Taurus44 (Low Mesh)", priority: "High", completion: 0, plannedEffort: 2,
  	startTime: "6/5/2008", endTime: "6/7/2008", owner: false, feedback: false},  	
	{taskId:"CCP-3", resourceId: "R1", taskName: "AS 'Val'", subtaskName: "UV", 
	name: "AS 'Val' (UV)", priority: "Medium", completion: 0, plannedEffort: 7,
  	startTime: "6/3/2008", endTime: "6/10/2008", owner: true, feedback: false}
  ]);
  
  [Bindable]
  public var assignedWorkload:ArrayCollection = new ArrayCollection([
	{taskId:"QD-1", resourceId: "R1", taskName: "The Mall Int+Ext - X Large -", subtaskName: "High Mesh", 
	name: "The Mall Int+Ext - X Large - (High Mesh)", priority: "High", completion: 0, plannedEffort: 14,
  	startTime: "6/2/2008", endTime: "6/19/2008", owner: false, feedback: false},
	{taskId:"QD-2", resourceId: "R4", taskName: "The Mall Int+Ext - X Large -", subtaskName: "Low Mesh", 
	name: "The Mall Int+Ext - X Large - (Low Mesh)", priority: "High", completion: 0, plannedEffort: 10,
  	startTime: "6/20/2008", endTime: "6/30/2008", owner: false, feedback: false},  	
	{taskId:"QD-3", resourceId: "R2", taskName: "Psy Office", subtaskName: "High Mesh", 
	name: "Psy Office (High Mesh)", priority: "Medium", completion: 0, plannedEffort: 30,
  	startTime: "5/20/2008", endTime: "6/21/2008", owner: false, feedback: false},  	
	{taskId:"QD-7", resourceId: "R2", taskName: "Psy Office", subtaskName: "High Mesh", 
	name: "Psy Office (High Mesh)", priority: "Medium", completion: 0, plannedEffort: 1,
  	startTime: "6/21/2008", endTime: "6/23/2008", owner: false, feedback: true},  	
	{taskId:"QD-4", resourceId: "R2", taskName: "Psy Office", subtaskName: "Low Mesh",
	name: "Psy Office (Low Mesh)", priority: "Medium", completion: 0, plannedEffort: 18,
  	startTime: "6/23/2008", endTime: "7/10/2008", owner: false, feedback: false},  	
	{taskId:"QD-5", resourceId: "R3", taskName: "Leland House Int - Large -", subtaskName: "High Mesh",
	name: "Leland House Int - Large - (High Mesh)", priority: "Low", completion: 0, plannedEffort: 13,
  	startTime: "6/12/2008", endTime: "6/25/2008", owner: false, feedback: false},
	{taskId:"QD-6", resourceId: "R3", taskName: "Leland House Int - Large -", subtaskName: "Low Mesh", 
	name: "Leland House Int - Large - (Low Mesh)", priority: "Low", completion: 0, plannedEffort: 40,
  	startTime: "6/26/2008", endTime: "7/13/2008", owner: false, feedback: false}  
 ]);  	
 
  //--------------------Unassigned/Assigned Task/Subtask Dummy Data-------------------------
  [Bindable]
  public var tasks:ArrayCollection = new ArrayCollection([
	{id:"QD-1", resourceId: "QD-1", name: "The Mall Int+Ext - X Large -", priority: "High", milestone: 1,
	plannedStaffDays: 12, duration: 17, startTime: "6/2/2008", endTime: "6/19/2008"},
		{id:"QD-1.1", resourceId: "QD-1.1", name: "Mesh & AO UV-Set", duration: 3, startTime: "6/2/2008", endTime: "6/5/2008"},
		{id:"QD-1.2", resourceId: "QD-1.2", name: "UV0", duration: 3, startTime: "6/6/2008", endTime: "6/8/2008"},
		{id:"QD-1.3", resourceId: "QD-1.3", name: "UV1+UV2", duration: 5, startTime: "6/9/2008", endTime: "6/13/2008"},
		{id:"QD-1.4", resourceId: "QD-1.4", name: "Wreck", duration: 2, startTime: "6/14/2008", endTime: "6/15/2008"},
		{id:"QD-1.5", resourceId: "QD-1.5", name: "LODS+Collision+Assign materials", duration: 2, startTime: "6/16/2008", endTime: "6/17/2008"},	
	
	{id:"QD-2", resourceId: "QD-2", name: "Psy Office", priority: "Medium", milestone: 1,
  	plannedStaffDays: 25, duration: 31, startTime: "5/20/2008", endTime: "6/21/2008"},
		{id:"QD-2.1", resourceId: "QD-2.1", name: "Mesh & AO UV-Set", duration: 12, startTime: "5/20/2008", endTime: "6/1/2008"},
		{id:"QD-2.2", resourceId: "QD-2.2", name: "UV0", duration: 5, startTime: "6/2/2008", endTime: "6/7/2008"},
		{id:"QD-2.3", resourceId: "QD-2.3", name: "UV1+UV2", duration: 8, startTime: "6/8/2008", endTime: "6/15/2008"},
		{id:"QD-2.4", resourceId: "QD-2.4", name: "Wreck", duration: 3, startTime: "6/16/2008", endTime: "6/18/2008"},
		{id:"QD-2.5", resourceId: "QD-2.5", name: "LODS+Collision+Assign materials", duration: 3, startTime: "6/19/2008", endTime: "6/21/2008"},  	
  	
	{id:"QD-3", resourceId: "QD-3", name: "Leland House Int - Large -", priority: "Low", milestone: 1,
  	plannedStaffDays: 20, duration: 27, startTime: "6/26/2008", endTime: "7/13/2008"},
		{id:"QD-3.1", resourceId: "QD-3.1", name: "Mesh & AO UV-Set", duration: 8, startTime: "6/26/2008", endTime: "7/3/2008"},
		{id:"QD-3.2", resourceId: "QD-3.2", name: "UV0", duration: 8, startTime: "7/4/2008", endTime: "7/11/2008"},
		{id:"QD-3.3", resourceId: "QD-3.3", name: "UV1+UV2", duration: 4, startTime: "7/12/2008", endTime: "7/15/2008"},
		{id:"QD-3.4", resourceId: "QD-3.4", name: "Wreck", duration: 3, startTime: "7/16/2008", endTime: "7/18/2008"},
		{id:"QD-3.5", resourceId: "QD-3.5", name: "LODS+Collision+Assign materials", duration: 6, startTime: "7/18/2008", endTime: "7/23/2008"}  		
  ]);
  
  [Bindable]
  public var tasksHierarchical:HierarchicalData = new HierarchicalData([
	{id:"QD-1", resourceId: "QD-1", name: "The Mall Int+Ext - X Large -", priority: "High", milestone: "MSP1",
	plannedStaffDays: 17, duration: 17, startTime: new Date(2008,6,2), endTime: new Date(2008,6,19)}
//	, children: [
//		{id:"QD-1.1", resourceId: "QD-1.1", name: "Mesh & AO UV-Set", plannedStaffDays: 3, duration: 3, startTime:new Date(2008, 5, 1), endTime:new Date(2008,5,15)},
//		{id:"QD-1.2", resourceId: "QD-1.2", name: "UV0", plannedStaffDays: 3, duration: 3, startTime: new Date(2008,6,6), endTime: new Date(2008,6,18)},
//		{id:"QD-1.3", resourceId: "QD-1.3", name: "UV1+UV2", plannedStaffDays: 5, duration: 5, startTime: new Date(2008,6,9), endTime: new Date(2008,6,13)},
//		{id:"QD-1.4", resourceId: "QD-1.4", name: "Wreck", plannedStaffDays: 2, duration: 2, startTime: new Date(2008,6,14), endTime: new Date(2008,6,15)},
//		{id:"QD-1.5", resourceId: "QD-1.5", name: "LODS+Collision+Assign materials", plannedStaffDays: 2, duration: 2, startTime: new Date(2008,6,16), endTime: new Date(2008,6,17)},
//	]},
//	
//	
//	{id:"QD-2", resourceId: "QD-2", name: "Psy Office", priority: "Medium", milestone: "MSP1",
//  	plannedStaffDays: 31, duration: 31, startTime: new Date(2008,5,20), endTime: new Date(2008,6,21), children: [
//		{id:"QD-2.1", resourceId: "QD-2.1", name: "Mesh & AO UV-Set", plannedStaffDays: 12, duration: 12, startTime: new Date(2008,5,20), endTime: new Date(2008,6,1)},
//		{id:"QD-2.2", resourceId: "QD-2.2", name: "UV0", plannedStaffDays: 5, duration: 5, startTime: new Date(2008,6,2), endTime: new Date(2008,6,7)},
//		{id:"QD-2.3", resourceId: "QD-2.3", name: "UV1+UV2", plannedStaffDays: 8, duration: 8, startTime: new Date(2008,6,8), endTime: new Date(2008,6,18)},
//		{id:"QD-2.4", resourceId: "QD-2.4", name: "Wreck", plannedStaffDays: 3, duration: 3, startTime: new Date(2008,6,16), endTime: new Date(2008,6,18)},
//		{id:"QD-2.5", resourceId: "QD-2.5", name: "LODS+Collision+Assign materials",plannedStaffDays: 3,  duration: 3, startTime:new Date(2008,6,19), endTime: new Date(2008,6,21)},
//	]},  	
//  	
//	{id:"QD-3", resourceId: "QD-3", name: "Leland House Int - Large -", priority: "Low", milestone: "MSP1",
//  	plannedStaffDays: 27, duration: 27, startTime:new Date(2008,6,26), endTime: new Date(2008,7,23), children: [
//		{id:"QD-3.1", resourceId: "QD-3.1", name: "Mesh & AO UV-Set", plannedStaffDays: 8, duration: 8, startTime:new Date(2008,6,26), endTime: new Date(2008,7,3)},
//		{id:"QD-3.2", resourceId: "QD-3.2", name: "UV0", plannedStaffDays: 8, duration: 8, startTime:new Date(2008,7,4), endTime:new Date(2008,7,11)},
//		{id:"QD-3.3", resourceId: "QD-3.3", name: "UV1+UV2", plannedStaffDays: 4, duration: 4, startTime: new Date(2008,7,12), endTime: new Date(2008,7,15)},
//		{id:"QD-3.4", resourceId: "QD-3.4", name: "Wreck", plannedStaffDays: 3, duration: 3, startTime: new Date(2008,7,16), endTime: new Date(2008,7,18)},
//		{id:"QD-3.5", resourceId: "QD-3.5", name: "LODS+Collision+Assign materials", plannedStaffDays: 6, duration: 6, startTime: new Date(2008,7,18), endTime: new Date(2008,7,23)},
//	]} 	
  ]);
  
  [Bindable]
  public var tasksArrayCollection:ArrayCollection = new ArrayCollection([
	{id:"QD-1", resourceId: "QD-1", name: "The Mall Int+Ext - X Large -", priority: "High", milestone: 1,
	plannedStaffDays: 12, duration: 17, startTime: "6/2/2008", endTime: "6/19/2008", children: [
	{id:"QD-2", resourceId: "QD-2", name: "Psy Office", priority: "Medium", milestone: 1,
  	plannedStaffDays: 25, duration: 31, startTime: "5/20/2008", endTime: "6/21/2008"},
	{id:"QD-3", resourceId: "QD-3", name: "Leland House Int - Large -", priority: "Low", milestone: 1,
  	plannedStaffDays: 20, duration: 27, startTime: "6/26/2008", endTime: "7/13/2008"} 
  	]}  	
  ]);      
  
  [Bindable]
  public var projectStatusProcessStep:ArrayCollection = new ArrayCollection([
  	{id: "DICE_P1_1", step: "Mesh & AO UV-Set"},
  	{id: "DICE_P1_2", step: "UV0"},
  	{id: "DICE_P1_3", step: "UV1+UV2"},
  	{id: "DICE_P1_4", step: "Wreck"},
  	{id: "DICE_P1_5", step: "LODS+Collision+Assign materials"} 			
  ]);
  
//  [Bindable]
//  public var projectStatusReportTasks:ArrayCollection = new ArrayCollection([
//  	{id:"QD-1", taskName: "The Mall Int+Ext - X Large -", priority: "High", milestone: "1", artistId: "Artist1", progress: "100%",
//  	DICE_P1_1: "A", DICE_P1_2: "A", DICE_P1_3: "A", DICE_P1_4: "A", DICE_P1_5: "A", nextDeliveryDue: "", scheduledStart: "6/2/2008",
//  	scheduledEnd: "6/19/2008", plannedStaffDays: 17, actualStaffDays: 17, staffDayDifference: 0, numberOfIterations: 2},
//  	{id:"QD-2", taskName: "Psy Office", priority: "Medium", milestone: "1", artistId: "Artist2", progress: "20%",
//  	DICE_P1_1: "A", DICE_P1_2: "F", DICE_P1_3: "N", DICE_P1_4: "N", DICE_P1_5: "N", nextDeliveryDue: "6/12/2008", scheduledStart: "5/20/2008",
//  	scheduledEnd: "6/21/2008", plannedStaffDays: 32, actualStaffDays: 32, staffDayDifference: 0, numberOfIterations: 3},
//  	{id:"QD-3", taskName: "Leland House Int - Large - ", priority: "Low", milestone: "1", artistId: "Artist2, Artist3", progress: "60%",
//  	DICE_P1_1: "A", DICE_P1_2: "A", DICE_P1_3: "A", DICE_P1_4: "20%", DICE_P1_5: "N", nextDeliveryDue: "6/10/2008", scheduledStart: "5/20/2008",
//  	scheduledEnd: "6/21/2008", plannedStaffDays: 32, actualStaffDays: 32, staffDayDifference: 0, numberOfIterations: 3}  	  	
//  ]);
  
  [Bindable]
  public var leadWorkItemArray:ArrayCollection = new ArrayCollection([
  	{projectName: "DICE", id:"QD-1", taskName: "The Mall Int+Ext - X Large -", subTaskName: "High Mesh", category: "Props", priority: "High", 
  	artistId: "Artist1", lead: "Lead 1", nextDeliveryDue: "6/2/2008", numberOfIterations: 2},
  	{projectName: "QD", id:"QD-2", taskName: "Psy Office", subTaskName: "Low Mesh", category: "Characters", priority: "Medium", 
  	artistId: "Artist2", lead: "Lead 2", nextDeliveryDue: "6/12/2008", numberOfIterations: 3},
  	{projectName: "Skate2", id:"QD-3", taskName: "Leland House Int - Large - ", subTaskName: "UV", category: "Props", priority: "Low", 
  	artistId: "Artist2, Artist3", lead: "Lead 1", nextDeliveryDue: "6/10/2008", numberOfIterations: 3}  	  	
  ]);    
  
  
  [Bindable]
  public var subtasks:ArrayCollection = new ArrayCollection([
	{taskId:"QD-1", taskName: "The Mall Int+Ext - X Large -", subtaskName: "High Mesh", 
	name: "The Mall Int+Ext - X Large - (High Mesh)", priority: "High", completion: 0, plannedEffort: 17,
  	startTime: "6/2/2008", endTime: "6/19/2008", owner: "Frank"},
	{taskId:"QD-2", taskName: "The Mall Int+Ext - X Large -", subtaskName: "Low Mesh", 
	name: "The Mall Int+Ext - X Large - (Low Mesh)", priority: "High", completion: 0, plannedEffort: 10,
  	startTime: "6/20/2008", endTime: "6/30/2008", owner: "Frank"},  	
	{taskId:"QD-3", taskName: "Psy Office", subtaskName: "High Mesh", 
	name: "Psy Office (High Mesh)", priority: "Medium", completion: 0, plannedEffort: 32,
  	startTime: "5/20/2008", endTime: "6/21/2008", owner: "Frank"},
	{taskId:"QD-4", taskName: "Psy Office", subtaskName: "Low Mesh",
	name: "Psy Office (Low Mesh)", priority: "Medium", completion: 0, plannedEffort: 19,
  	startTime: "6/21/2008", endTime: "7/10/2008", owner: "Frank"},  	
	{taskId:"QD-5", taskName: "Leland House Int - Large -", subtaskName: "High Mesh",
	name: "Leland House Int - Large - (High Mesh)", priority: "Low", completion: 0, plannedEffort: 13,
  	startTime: "6/12/2008", endTime: "6/25/2008", owner: "Frank"},
	{taskId:"QD-6", taskName: "Leland House Int - Large -", subtaskName: "Low Mesh", 
	name: "Leland House Int - Large - (Low Mesh)", priority: "Low", completion: 0, plannedEffort: 17,
  	startTime: "6/26/2008", endTime: "7/13/2008", owner: "Frank"}   	
  ]);
  

  	
  	[Bindable]
  	public var taskDistribution:ArrayCollection = new ArrayCollection([
  		{legend: "Lead1", plannedEffort: 45.3},
  		{legend: "Lead2", plannedEffort: 67.8},
  		{legend: "Lead3", plannedEffort: 102.4},
  		{legend: "Lead4", plannedEffort: 50.0},
  		{legend: "Unassigned", plannedEffort: 125}	
  	]);
  	
  	[Bindable]
  	public var lead1Task:ArrayCollection = new ArrayCollection([
  		{date: "01/02/2008", effort: 0},
  		{date: "02/02/2008", effort: 12},
  		{date: "03/02/2008", effort: 15},
  		{date: "04/02/2008", effort: 23},
  		{date: "05/02/2008", effort: 10},
  		{date: "06/02/2008", effort: 0},
  		{date: "07/02/2008", effort: 0},
  		{date: "08/02/2008", effort: 0},
  		{date: "09/02/2008", effort: 0},
  		{date: "10/02/2008", effort: 0},
  		{date: "11/02/2008", effort: 0},
  		{date: "12/02/2008", effort: 0}  		
  	]);
  	
  	[Bindable]
  	public var qaProjectTask:ArrayCollection = new ArrayCollection([
  		{date: "01/02/2008", characterTasks: 12, sceneTasks: 24},
  		{date: "02/02/2008", characterTasks: 14, sceneTasks: 23},
  		{date: "03/02/2008", characterTasks: 5, sceneTasks: 16},
  		{date: "04/02/2008", characterTasks: 23, sceneTasks: 30},
  		{date: "05/02/2008", characterTasks: 10, sceneTasks: 20},
  		{date: "06/02/2008", characterTasks: 12, sceneTasks: 28 },
  		{date: "07/02/2008", characterTasks: 15, sceneTasks: 35},
  		{date: "08/02/2008", characterTasks: 10, sceneTasks: 19},
  		{date: "09/02/2008", characterTasks: 8, sceneTasks: 22},
  		{date: "10/02/2008", characterTasks: 20, sceneTasks: 23},
  		{date: "11/02/2008", characterTasks: 5, sceneTasks: 18},
  		{date: "12/02/2008", characterTasks: 3, sceneTasks: 16} 		
  	]);
  	   	  	
  	
  	[Bindable]
  	public var qaTask:ArrayCollection = new ArrayCollection([
  		{date: "01/02/2008", planned: 30, received: 30},
  		{date: "02/02/2008", planned: 21, received: 21},
  		{date: "03/02/2008", planned: 28, received: 28},
  		{date: "04/02/2008", planned: 19, received: 19},
  		{date: "05/02/2008", planned: 13, received: 13},
  		{date: "06/02/2008", planned: 12, received: 2},
  		{date: "07/02/2008", planned: 40, received: 0},
  		{date: "08/02/2008", planned: 25, received: 0},
  		{date: "09/02/2008", planned: 29, received: 0},
  		{date: "10/02/2008", planned: 18, received: 0},
  		{date: "11/02/2008", planned: 19, received: 0},
  		{date: "12/02/2008", planned: 20, received: 0}		
  	]);
  	
  	[Bindable]
  	public var qaTaskMonth:ArrayCollection = new ArrayCollection([
  		{date: "01/01/2008", planned: 0, received: 0},
  		{date: "01/02/2008", planned: 0, received: 0},
  		{date: "01/03/2008", planned: 2, received: 2},
  		{date: "01/04/2008", planned: 3, received: 3},
  		{date: "01/05/2008", planned: 0, received: 1},
  		{date: "01/06/2008", planned: 1, received: 0},
  		{date: "01/07/2008", planned: 3, received: 4},
  		{date: "01/08/2008", planned: 2, received: 2},
  		{date: "01/09/2008", planned: 1, received: 1},
  		{date: "01/10/2008", planned: 0, received: 0},
  		{date: "01/11/2008", planned: 1, received: 1},
  		{date: "01/02/2008", planned: 0, received: 0},
  		{date: "01/13/2008", planned: 0, received: 0},
  		{date: "01/14/2008", planned: 0, received: 1},
  		{date: "01/15/2008", planned: 3, received: 2},
  		{date: "01/16/2008", planned: 2, received: 2},
  		{date: "01/17/2008", planned: 1, received: 1},
  		{date: "01/18/2008", planned: 0, received: 0},
  		{date: "01/19/2008", planned: 3, received: 3},
  		{date: "01/20/2008", planned: 2, received: 2},
  		{date: "01/21/2008", planned: 3, received: 3},
  		{date: "01/22/2008", planned: 1, received: 1},
  		{date: "01/24/2008", planned: 2, received: 1},
  		{date: "01/25/2008", planned: 3, received: 4},
  		{date: "01/26/2008", planned: 4, received: 4},
  		{date: "01/27/2008", planned: 1, received: 1},
  		{date: "01/28/2008", planned: 0, received: 0},
  		{date: "01/29/2008", planned: 0, received: 0},
  		{date: "01/30/2008", planned: 0, received: 0},  		  		
  		{date: "01/31/2008", planned: 1, received: 1}		
  	]);
		
	[Bindable]
  	public var projectColumnsTask:ArrayCollection = new ArrayCollection([
  		{project: "DICE", planned: 68, received: 35,approved:80},
  		{project: "CCP1", planned: 80, received: 20},
  		{project: "CCP2", planned: 70, received: 30},
  		{project: "Skate2", planned: 90, received: 10},
  		{project: "QD", planned: 50, received: 50}
  	]);
  	
  	[Bindable]
  	public var projectLineChartData:ArrayCollection = new ArrayCollection([
  		{date: "01/06/2008", planned: 50, received: 50},
  		{date: "01/13/2008", planned: 48, received: 49},
  		{date: "01/20/2008", planned: 40, received: 40},
  		{date: "01/27/2008", planned: 36, received: 40},
  		{date: "02/03/2008", planned: 32, received: 35},
  		{date: "02/10/2008", planned: 27, received: 30},
  		{date: "02/17/2008", planned: 21, received: 23},
  		{date: "02/24/2008", planned: 18},
  		{date: "03/02/2008", planned: 15},
  		{date: "03/09/2008", planned: 12},
  		{date: "03/16/2008", planned: 8},
  		{date: "03/23/2008", planned: 0}
  	]);
  	
  	[Bindable]
  	public var leadList:ArrayCollection = new ArrayCollection([
		{ name: "Lead1", level: "Animator", members: "Artist1, Artist2, Sr. Artist3" },
		{ name: "Lead2", level: "Texcture", members: "Sr. Artist4, Artist 5" },
		{ name: "Lead3", level: "Character Leader", members: "Artist 6, Artist 2" },
		{ name: "Lead4", level: "Animation Leader", members: "Artist 7, Artist 8, Artist 9" }
	]);
	
	[Bindable]
	public var propsList:ArrayCollection = new ArrayCollection([
		{ taskId: "QD-1", taskName: "The Mall Int+Ext - X Large -", priority: "High", milestone: 1, plannedStaffDays: 90, startDate: "8/27/2007", endDate: "12/20/2007", owner: ""},
		{ taskId: "QD-2", taskName: "Psy Office", priority: "Medium", milestone: 1, plannedStaffDays: 60, startDate: "9/6/2007", endDate: "11/9/2007", owner: "Lead2"},
		{ taskId: "QD-3", taskName: "Leland House Int - Large -", milestone: 1, priority: "High", plannedStaffDays: 65, startDate: "5/8/2007", endDate: "7/26/2007", owner: "Lead3"},
		{ taskId: "QD-4", taskName: "Lauren Bedroom - Medium -", milestone: 1, priority: "Low", plannedStaffDays: 27, startDate: "9/17/2007", endDate: "10/24/2007", owner: "Lead1"}
	]);

	[Bindable]
	public var lead1TaskList:ArrayCollection = new ArrayCollection([
		{ taskId: "QD-4", taskName: "Lauren Bedroom - Medium -", milestone: 1, priority: "Low", plannedStaffDays: 27, startDate: "9/17/2007", endDate: "10/24/2007", owner: "Lead1"}
	]);
	
	//----------------Project Process Dummy Data----------------
	[Bindable]
	public var processArray:ArrayCollection = new ArrayCollection([
		{id: 1, name: "Process 1"}
	]); 
	
	[Bindable]
	public var processStepArray:ArrayCollection = new ArrayCollection(/* [
		{name: "Mesh & AO UV-Set", deliverable: true},
		{name: "UV0", deliverable: true},
		{name: "UV1+UV2", deliverable: false},		
		{name: "Wreck", deliverable: true},
		{name: "LODS+Collision+Assign materials", deliverable: true}
	] */);	
	
/* 	public function getCalendar():Array {
		var calendar: Array = [];
		var calendarEvent: CalendarEvent = new CalendarEvent;
		calendarEvent.start = new Date("06/01/2008");
		calendarEvent.end = new Date("06/11/2008");
		calendarEvent.allDay = true;
		calendarEvent.summary = "Psy Office (Low Mesh)";
		calendar.push(calendarEvent);
		
		calendarEvent = new CalendarEvent;
		calendarEvent.start = new Date("06/12/2008");
		calendarEvent.end = new Date("06/22/2008");
		calendarEvent.allDay = true;
		calendarEvent.summary = "Psy Office (High Mesh)";
		calendarEvent.color = 0xff6d33;
		calendar.push(calendarEvent);
		
		calendarEvent = new CalendarEvent;
		calendarEvent.start = new Date("06/23/2008");
		calendarEvent.end = new Date("07/10/2008");
		calendarEvent.allDay = true;
		calendarEvent.summary = "Psy Office (UV)";
		calendarEvent.color = 0x4cb841;
		calendar.push(calendarEvent);
		
		return calendar;	
	}; */
	
	[Bindable]
	public var artistWIPTasks: ArrayCollection = new ArrayCollection([
		{project: "DICE", task: "Psy Office", subtask: "(Low Mesh)", startDate: "06/01/2008", endDate: "06/11/2008"},
		{project: "DICE", task: "Psy Office", subtask: "(High Mesh)", startDate: "06/12/2008", endDate: "06/22/2008"},
		{project: "DICE", task: "Psy Office", subtask: "(UV)", startDate: "06/23/2008", endDate: "07/10/2008"},
	]);
	
	
	[Bindable]
	public var milestoneArray:ArrayCollection = new ArrayCollection([
		{ id: "1", milestone: "MSP1", description: "Eve Fest 2007 Scenes", plannedCompletion: new Date(2007,7,15), 
			objectives: "Finish the work no later than date specificed", 
			assumptions: "Reference material will be on time",
			constraints:"Schedule is dependant on reference being in on time"},
		{ id: "2", milestone: "MSP2", description: "", plannedCompletion: new Date(2007,8,20), objectives: "", assumptions: "",constraints:""},
		{ id: "3", milestone: "", description: "", plannedCompletion: null, objectives: "", assumptions: "",constraints:""},
		{ id: "4", milestone: "", description: "", plannedCompletion: null, objectives: "", assumptions: "",constraints:""}
	]);	
	
	[Bindable]
	public var artistUnreadNotification: ArrayCollection = new ArrayCollection([
		{date: "06/12/2008", description: "任务'Psy Office (LOD)'重新分配给Artist2", issuer: "Lead 1"},
		{date: "06/13/2008", description: "新任�?Psy Office (UV)'", issuer: "Lead 2"},
		{date: "06/14/2008", description: "任务'Psy Office (High Mesh)'修改如下: 结束日期2008/06/20 => 2008/06/21", issuer: "Lead 1"} 
	]);
	
	[Bindable]
	public var workloadBalanceArray:ArrayCollection = new ArrayCollection([
		{name: "Artist1", startDate: new Date(2008, 6, 3), endDate: new Date(2008, 6, 10), plannedEffort: 7},
		{name: "Artist2", startDate: new Date(2008, 6, 3), endDate: new Date(2008, 6, 10), plannedEffort: 0}
	]);
	
	[Bindable]
	public var resourceProductivitySimple:ArrayCollection = new ArrayCollection([
		{date: "January", planned: 21, done: 23},
		{date: "Febuary", planned: 20, done: 20},
		{date: "March", planned: 22, done: 24},
		{date: "April", planned: 21, done: 25},
		{date: "May", planned: 20, done: 23},
		{date: "June", planned: 21, done: 21},
		{date: "July", planned: 23, done: 25},
		{date: "August"},
		{date: "September"},
		{date: "October"},
		{date: "November"},
		{date: "December"}
	]);
	
	[Bindable]
	public var teamProductivity1:ArrayCollection = new ArrayCollection([
		{date: "January",  done: 23},
		{date: "Febuary", done: 20},
		{date: "March", done: 24},
		{date: "April", done: 25},
		{date: "May", done: 23},
		{date: "June", done: 21},
		{date: "July", done: 25},
		{date: "August"},
		{date: "September"},
		{date: "October"},
		{date: "November"},
		{date: "December"}
	]);	
	
	[Bindable]
	public var teamProductivity2:ArrayCollection = new ArrayCollection([
		{date: "January",  done: 48},
		{date: "Febuary", done: 44},
		{date: "March", done: 50},
		{date: "April", done: 46},
		{date: "May", done: 47},
		{date: "June", done: 50},
		{date: "July", done: 46},
		{date: "August"},
		{date: "September"},
		{date: "October"},
		{date: "November"},
		{date: "December"}
	]);		
	
	[Bindable]
	public var teamProductivity3:ArrayCollection = new ArrayCollection([
		{date: "January",  done: 70},
		{date: "Febuary", done: 69},
		{date: "March", done: 73},
		{date: "April", done: 72},
		{date: "May", done: 68},
		{date: "June", done: 70},
		{date: "July", done: 73},
		{date: "August"},
		{date: "September"},
		{date: "October"},
		{date: "November"},
		{date: "December"}
	]);			


	[Bindable]
	public var monthlyCostArray:ArrayCollection = new ArrayCollection([
		{date: "Jan-08",  cost: 4000},
		{date: "Feb-08", cost: 4250},
		{date: "Mar-08", cost: 4500},
		{date: "Apr-08", cost: 5000},
		{date: "May-08", cost: 5200},
		{date: "Jun-08", cost: 4000},
		{date: "Jul-08", cost: 6000},
		{date: "Aug-08", cost: 6100},
		{date: "Sep-08"},
		{date: "Oct-08"},
		{date: "Nov-08"},
		{date: "Dec-08"}
	]);
	
	[Bindable]
	public var projectCostArray1:ArrayCollection = new ArrayCollection([
		{project: "DICE",  cost: 1250},
		{project: "Skate2", cost: 670},
		{project: "CCP", cost: 1100},
		{project: "CCP2", cost: 900}
	]);
	
	[Bindable]
	public var projectCostArray2:ArrayCollection = new ArrayCollection([
		{project: "DICE",  cost: 1900},
		{project: "Skate2", cost: 1000},
		{project: "CCP", cost: 1500},
		{project: "CCP2", cost: 1000}
	]);	
	
	[Bindable]
	public var projectDetailArray1:ArrayCollection = new ArrayCollection([
		{date: "Jan-08", production: 1300, fix: 600},
		{date: "Feb-08", production: 1200, fix: 400},
		{date: "Mar-08", production: 1450, fix: 320},
		{date: "Apr-08", production: 1600, fix: 300},
		{date: "May-08", production: 1230, fix: 450},
		{date: "Jun-08", production: 1450, fix: 600},
		{date: "Jul-08", production: 1110, fix: 230},
		{date: "Aug-08", production: 1340, fix: 200},
		{date: "Sep-08"},
		{date: "Oct-08"},
		{date: "Nov-08"},
		{date: "Dec-08"}
	]);		
	
	[Bindable]
	public var projectDetailArray2:ArrayCollection = new ArrayCollection([
		{date: "Jan-08", production: 1100, fix: 500},
		{date: "Feb-08", production: 1200, fix: 400},
		{date: "Mar-08", production: 1750, fix: 320},
		{date: "Apr-08", production: 1300, fix: 400},
		{date: "May-08", production: 1230, fix: 450},
		{date: "Jun-08", production: 1610, fix: 320},
		{date: "Jul-08", production: 1110, fix: 230},
		{date: "Aug-08", production: 1340, fix: 200},
		{date: "Sep-08"},
		{date: "Oct-08"},
		{date: "Nov-08"},
		{date: "Dec-08"}
	]);	
  
  [Bindable]
  public var newSpeakData:ArrayCollection=new ArrayCollection([
  	{speaker:"Lead1",date:"6/24/2008",content:"You use the <mx:Repeater> tag to declare a Repeater component that handles repetition of one or more user interface components based on dynamic or static data arrays at run time. The repeated components can be controls or containers. Using a Repeater component requires data binding to allow for run-time-specific values. For more information about data binding, see Storing Data."},
  	{speaker:"Lead2",date:"6/25/2008",content:"You can use the <mx:Repeater> tag anywhere a control or container tag is allowed. To repeat user interface components, you place their tags within the <mx:Repeater> tag. All components derived from the UIComponent class can be repeated with the exception of the <mx:Application> container tag. You can also use more than one <mx:Repeater> tag in an MXML document, and you can nest <mx:Repeater> tags."},
  	{speaker:"Lead1",date:"6/27/2008",content:"The principles described in Basic principles of the Repeater component work with dynamic data providers as well. The only difference is the source of the data array; instead of a static array written directly into your application, the array is defined in an <mx:Model> tag and drawn from an XML file, a web service, a remote object, or some other source. The data is collected and evaluated at run time to determine the number and value of elements in the data provider and how the Repeater component behaves."}
  ]);

  [Bindable]
  public var productionProcess:ArrayCollection = new ArrayCollection([
	{id:1, projectId: 1, processName: "Production", taskCategoryId: 7, description: "无", children: [
		{id:1, productionProcessId:1, step: "Mesh", sequence: 8, deliverable: "D1",description: "无"},
		{id:2, productionProcessId:1, step: "Low Mesh", sequence: 8, deliverable: "D2",description: "无"},
  	]},
  	{id:2, projectId: 1, processName: "Integration", taskCategoryId: 9, description: "无", children: [
		{id:3, productionProcessId:2, step: "Texture", sequence: 8, deliverable: "D3",description: "无"},
		{id:4, productionProcessId:2, step: "UV", sequence: 8, deliverable: "D4",description: "无"},
  	]}  	
  ]);
  
    [Bindable]
  public var advancedDataGridData:ArrayCollection=new ArrayCollection([
  	{pops:"pops1",characters:"",weapons:"",vehicles:"vehicles1",scenes:"scenes1"},
  	{pops:"pops2",characters:"",weapons:"",vehicles:"",scenes:""},
  	{pops:"pops3",characters:"",weapons:"",vehicles:"",scenes:""}
  ]);