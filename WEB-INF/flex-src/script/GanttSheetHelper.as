// ActionScript file	
	import com.pearl.normandy.collections.HierarchicalCollectionViewFixed;
	import com.pearl.normandy.enum.ActivityEnum;
	import com.pearl.normandy.enum.ColorEnum;
	import com.pearl.normandy.utils.Constants;
	import com.pearl.normandy.utils.DateUtil;
	import com.pearl.normandy.vo.ActivityVO;
	import com.pearl.normandy.vo.ProjectMemberVO;
	import com.pearl.normandy.vo.ProjectVO;
	import com.pearl.normandy.vo.TaskVO;
	import com.pearl.normandy.vo.UserVO;
	
	import ilog.core.DataItem;
	import ilog.gantt.ConstraintItem;
	import ilog.gantt.GanttDataGrid;
	import ilog.gantt.GanttSheet;
	import ilog.gantt.GanttSheetEvent;
	import ilog.gantt.ResourceChart;
	import ilog.gantt.TaskItem;
	
	import mx.collections.ICollectionView;
	import mx.utils.StringUtil;
	

	  
    //==============================
    //Gantt Sheet Behaviors
    //==============================   	  
	/**
	 * Function to Zoom In Elixir GanttSheet
	 */  
    public function zoomIn(ganttSheet:GanttSheet):void{
	    if (!ganttSheet.selectedItem)
	    {
	      ganttSheet.zoom(Constants.ZOOM_IN_RATIO, null, true);
	    }
	    else
	    {
	      var item:Object = ganttSheet.selectedItem;
	      var startTime:Date = new Date(item.startTime);
	      var endTime:Date = new Date(item.endTime);
	      var center:Date = new Date( ( startTime.time + endTime.time ) / 2 );
	      ganttSheet.zoom(Constants.ZOOM_IN_RATIO, center, true);
	    }
  	}	  	  
  	
  	
	private function onGanttSheetChangeDefault(resourceChart:ResourceChart):void
	{      
		var ganttSheet:GanttSheet = resourceChart.ganttSheet;
		var ganttDataGrid:GanttDataGrid = resourceChart.dataGrid;
        var activity:Object = ganttSheet.selectedItem;
        
         if (activity)
        {
          ganttDataGrid.selectedItem = resourceChart.getResource(activity);
          highlight(resourceChart, activity);
        }           		
  	}  	     	
    
    
	public function onGanttSheetItemEditReassign(dataGrid: GanttDataGrid, event:GanttSheetEvent):void{
    	var taskItem:TaskItem = event.itemRenderer.data as TaskItem;      
    	if (dataGrid.selectedItem != taskItem.resource)
      		dataGrid.selectedItem = taskItem.resource; 
    }    
    
    
    public function getParentTask(resources:HierarchicalCollectionViewFixed, task:TaskVO):TaskVO{
    	var parentItem:TaskVO = resources.getParentItem(task) as TaskVO;
    	if(parentItem){
    		return parentItem;
    	}
    	else{
    		return task;
    	}
    }
	//--------------------------------------------------------------------------
    //
    //  GanttSheet Label function
    //
    //--------------------------------------------------------------------------
    private function taskChart_taskLabelFunc(item:Object):String{
    	var task:TaskVO = item as TaskVO;
    	
    	if(task){
    		if(task.children != null){
    			return task.owner;
    		}
    		else{
    			return task.resourcesName;
    		}
    	}
    	else{
    		return "";
    	}
    }        	
	
	//--------------------------------------------------------------------------
    //
    //  GanttSheet Datatip function
    //
    //--------------------------------------------------------------------------	
	private function ganttDataTip(item:DataItem):String{		                 
    	if(item is TaskItem)
        {
          	var taskItem:TaskItem = TaskItem(item);
          	
          	if(taskItem.data.children){
			    return StringUtil.substitute("{0}\n{1}\n{2}\n{3}\n{4}",    		    		 
		                 resourceManager.getString('Language','label.data.tip.task')+taskItem.data.name,
		                 resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(taskItem.startTime, DateUtil.SIMPLE),
		                 resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(taskItem.endTime, DateUtil.SIMPLE),
		                 resourceManager.getString('Language','label.data.tip.lead')+(taskItem.data.owner==null?"":taskItem.data.owner),
		                 (taskItem.data.referenceUrl!=null&&taskItem.data.referenceUrl!="")?
				 			("<img height='120' width='120' src='"+TaskVO.getReferenceUrl(taskItem.data as TaskVO)+"'/>"):"");       		
          	}
          	else{
			    return StringUtil.substitute("{0}\n{1}\n{2}\n{3}\n{4}",    		    		 
		                 resourceManager.getString('Language','label.data.tip.task')+taskItem.data.name,
		                 resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(taskItem.startTime, DateUtil.SIMPLE),
		                 resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(taskItem.endTime, DateUtil.SIMPLE),
		                 resourceManager.getString('Language','label.data.tip.lead')+(taskItem.data.owner==null?"":taskItem.data.owner),
		                 resourceManager.getString('Language','label.data.tip.artist')+(taskItem.data.resourcesName==null?"":taskItem.data.resourcesName));            		
		    }
        }
        else if(item is ConstraintItem)
        {
          var constraintItem:ConstraintItem = ConstraintItem(item);

          return StringUtil.substitute(
              "{0}\n{1}\n{2}",
              resourceManager.getString('Language','label.data.tip.constraint')+constraintItem.kind,
              resourceManager.getString('Language','label.data.tip.from')+TaskVO(constraintItem.fromTask).name,
              resourceManager.getString('Language','label.data.tip.to')+TaskVO(constraintItem.toTask).name
          );
        }
        return null;                 
	}
	
	private function activityDataTip(item:TaskItem):String{
		var activity:ActivityVO = item.data as ActivityVO;
		
//		if(ActivityVO.isNonProducton(item.data as ActivityVO)){
//		    return StringUtil.substitute("{0}\n{1}\n{2}\n{3}",   		    		 
//	                 resourceManager.getString('Language','label.data.tip.name')+item.label,
//	                 resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
//	                 resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE),
//	                 resourceManager.getString('Language','label.data.tip.approved_by')+activity.createdByName);		
//		}
		
		if(ActivityVO.isProjectReason(activity)){
			return StringUtil.substitute("{0}\n{1}\n{2}\n{3}\n{4}\n{5}\n{6}\n{7}",
					resourceManager.getString('Language', 'label.data.tip.project')+activity.projectName,
					resourceManager.getString('Language', 'label.data.tip.name')+activity.name,
					resourceManager.getString('Language', 'label.data.tip.category')+activity.activityType,
					resourceManager.getString('Language', 'label.data.tip.actual_effort')+activity.actualStaffDays+" Day(s)",
					resourceManager.getString('Language', 'label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
					resourceManager.getString('Language', 'label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE),
					resourceManager.getString('Language', 'label.data.tip.created_by')+activity.createdByName,
					resourceManager.getString('Language', 'label.data.tip.approved_by')+activity.provedBy);
		}else if(ActivityVO.isNonProjectReason(activity)){
			return StringUtil.substitute("{0}\n{1}\n{2}\n{3}\n{4}\n{5}",   		    		 
	                 resourceManager.getString('Language','label.data.tip.name')+item.label,
	                 resourceManager.getString('Language', 'label.data.tip.category')+activity.activityType,
	                 resourceManager.getString('Language','label.data.tip.actual_effort')+activity.actualStaffDays+" Days(s)",
	                 resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.approved_by')+activity.createdByName);
		}else{			
		    return StringUtil.substitute("{0}\t\t{1}\n{2}\n{3}\n{4}\n{5}\n{6}\n{7}\n{8}\n{9}\n{10}\n{11}\n{12}",
		    		 resourceManager.getString('Language','label.data.tip.project')+activity.projectName,
		    		 resourceManager.getString('Language','label.data.tip.id')+activity.taskId,
	                 "<b>"+resourceManager.getString('Language','label.data.tip.name')+item.label+"</b>",
	                 resourceManager.getString('Language','label.data.tip.milestone')+activity.milestone,
	                 resourceManager.getString('Language','label.data.tip.category')+activity.taskCategory,
	                 resourceManager.getString('Language','label.data.tip.assigned_effort')+activity.assignedEffort+" Day(s)",
	                 resourceManager.getString('Language','label.data.tip.actual_effort')+activity.actualStaffDays+" Days(s)",
	                 resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.lead')+(activity.taskOwnerName==null?"":activity.taskOwnerName),
	                 resourceManager.getString('Language','label.data.tip.assigned_by')+activity.createdByName,
	                 resourceManager.getString('Language','label.data.tip.completion')+activity.progress+"%",
				 	 ((activity.taskReferenceUrl!=null&&activity.taskReferenceUrl!="")||(activity.taskParentReferenceUrl!=null&&activity.taskParentReferenceUrl!=""))?
				 		("<img height='120' width='120' src='"+ActivityVO.getTaskReferenceUrl(activity)+"'/>"):"");    
	 	}
	}
	
	private function activityEditTip(item:TaskItem):String{
		var activity:ActivityVO = item.data as ActivityVO;
		
//		if(ActivityVO.isNonProducton(item.data as ActivityVO)){
//		    return StringUtil.substitute("{0}\n{1}\n{2}\n{3}",   		    		 
//	                 resourceManager.getString('Language','label.data.tip.name')+item.label,
//	                 resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
//	                 resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE),
//	                 resourceManager.getString('Language','label.data.tip.approved_by')+activity.createdByName);		
//		}
		if(activity.activityType == ActivityEnum.MANAGEMENT || activity.activityType == ActivityEnum.COMPENSATION_OFF 
					|| activity.activityType == ActivityEnum.PROJECT_TRAINING){
			return StringUtil.substitute("{0}\n{1}\n{2}\n{3}\n{4}\n{5}\n{6}",
					resourceManager.getString('Language', 'label.data.tip.project')+activity.projectName,
					resourceManager.getString('Language', 'label.data.tip.name')+activity.name,
					resourceManager.getString('Language', 'label.data.tip.category')+activity.activityType,
					resourceManager.getString('Language', 'label.data.tip.actual_effort')+activity.actualStaffDays+" Day(s)",
					resourceManager.getString('Language', 'label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
					resourceManager.getString('Language', 'label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE),
					resourceManager.getString('Language', 'label.data.tip.approved_by')+activity.createdByName);			
		}else if(activity.activityType == ActivityEnum.DOWN_TIME_TRAINING || activity.activityType == ActivityEnum.PAID_LEAVE 
					|| activity.activityType == ActivityEnum.QA || activity.activityType == ActivityEnum.RECRUITMENT 
					|| activity.activityType == ActivityEnum.UNPAID_LEAVE || activity.activityType == ActivityEnum.WAIT_FEEDBACK){
			return StringUtil.substitute("{0}\n{1}\n{2}\n{3}\n{4}",   		    		 
	                 resourceManager.getString('Language','label.data.tip.name')+item.label,
	                 resourceManager.getString('Language', 'label.data.tip.category')+activity.activityType,
	                 resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.approved_by')+activity.createdByName);
		}else{			
		    return StringUtil.substitute("{0}\t\t{1}\n{2}\n{3}\n{4}\n{5}\n{6}\n{7}\n{8}\n{9}\n{10}\n{11}",
		    		 resourceManager.getString('Language','label.data.tip.project')+activity.projectName,
		    		 resourceManager.getString('Language','label.data.tip.id')+activity.taskId,
	                 resourceManager.getString('Language','label.data.tip.name')+item.label,
	                 resourceManager.getString('Language','label.data.tip.milestone')+activity.milestone,
	                 resourceManager.getString('Language','label.data.tip.category')+activity.taskCategory,
	                 resourceManager.getString('Language','label.data.tip.assigned_effort')+activity.assignedEffort+" Day(s)",
	                 resourceManager.getString('Language','label.data.tip.actual_effort')+activity.actualStaffDays+" Days(s)",
	                 resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.lead')+(activity.taskOwnerName==null?"":activity.taskOwnerName),
	                 resourceManager.getString('Language','label.data.tip.assigned_by')+activity.createdByName,
	                 resourceManager.getString('Language','label.data.tip.completion')+activity.progress+"%");    
	 	}
	}	
	
	
	
	//--------------------------------------------------------------------------
    //
    //  GanttSheet Item editable function
    //
    //--------------------------------------------------------------------------	
	public function taskEditableFunction(item:TaskItem):Boolean{		
		if(model.ctrlKey){
			return true;
		}
		else{
			return false;
		}
	}    
    
	public function activityEditableFunction(item:TaskItem):Boolean{		
		if(model.isResourceViewLock){
			return false;
		}
		else{
			if(model.ctrlKey){
				return isActivityEditable(item.data as ActivityVO);
			}
			else{
				return false;
			}
		}

	}
	
	public function isActivityEditable(activity:ActivityVO):Boolean{				
		if(activity){
			if(activity.startTime < model.metaData.activityLockedTime){
				return false;
			}
			//PM for leave activites
			if(ActivityVO.isNonProducton(activity)){
				if(model.currUser.userName == activity.createdBy){
					return true;					
				}else{
					return false;
				}
			}else if(model.currUser.manager == Constants.BOOLEAN_YES){
				return true;
			}else if(model.activityEditable && activity.projectId == model.GLOBAL_SELECTED_PROJECT.id){				
				for each(var projm:ProjectMemberVO in model.releasedProjectMembers){ //Disable released project members
					if(projm.userId == activity.resourceId && projm.projectId == model.GLOBAL_SELECTED_PROJECT.id){
						return false;
					}
				}
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}		
	}
	
	
	//--------------------------------------------------------------------------
    //
    //  GanttSheet Item color function
    //
    //--------------------------------------------------------------------------
      private function defaultColorFunction(activity:ActivityVO):Object {      	
	  	var reason:String = "Default";      	
	  	if (activity){	  		
	  		if(ActivityVO.isNonProducton(activity)){
	  			reason = "NonProduction";
	  		}
  			else if(activity.activityType == ActivityEnum.FEEDBACK){
  				reason = "Feedback";
  			}
	  		else if(activity.deleted == Constants.BOOLEAN_YES){
	  			reason = "Deleted";
	  		}
	  		else{	  			
				return ColorEnum.performanceColor(activity.assignedEffort, activity.actualStaffDays);
		  	}
	  	}
	  	
	  	return ColorEnum.typeToColor[reason];
      }	
      
      
	//--------------------------------------------------------------------------
    //
    //  GanttSheet Highlight function
    //
    //-------------------------------------------------------------------------- 
    private function highlight(resourceChart:ResourceChart, source:Object):void{
    	var activities:ICollectionView = resourceChart.taskDataProvider as ICollectionView;
    	var taskId:int = 0;
    	
 		if(source is TaskVO && source.children == null){
    		taskId = (source as TaskVO).id;
    	}
    	else if(source is ActivityVO){
    		taskId = (source as ActivityVO).taskId;
    	}
    	
		for each(var activity:ActivityVO in activities){
			if(source && taskId!=0 && activity.taskId == taskId){
				activity.highlighted = true;
			}
			else{
				activity.highlighted = false;
			}
		}    	
    }     
    
	//--------------------------------------------------------------------------
    //
    //  GanttSheet Z-Order function
    //
    //--------------------------------------------------------------------------
    private function activitySortFunction(tasks:Array):Array{
    	tasks.sort(compareTasks);
    	return tasks;
    }
    
	private function compareTasks(taskItem1:TaskItem, taskItem2:TaskItem):int {
		var currUser:UserVO = model.currUser;
		var currProject:ProjectVO = model.GLOBAL_SELECTED_PROJECT;
			
		var task1Score:int = 0;
		var task2Score:int = 0;
		
		var activity1:ActivityVO = taskItem1.data as ActivityVO;
		var activity2:ActivityVO = taskItem2.data as ActivityVO;
		
	   	if(activity1 && activity2){
	   		//Check zOrder attribute
	   		task1Score += activity1.zOrder*10;
	   		task2Score += activity2.zOrder*10;
	   		
	   		//Test if created by current user
	   		if(currUser){
		   		if(activity1.createdBy == currUser.userName){
		   			task1Score+=5;
		   		}
		   		if(activity2.createdBy == currUser.userName){
		   			task2Score+=5;
		   		}
		   	}	   	
	   		
	   		//Test if current project activity
	   		if(currProject){
		   		if(activity1.projectId == currProject.id){
		   			task1Score+=3;
		   		}
		   		if(activity2.projectId == currProject.id){
		   			task2Score+=3;
		   		}
		   	}	   			 
	   		
	   		//Test if production activity
	   		if(activity1.activityType == ActivityEnum.PRODUCTION || activity1.activityType == ActivityEnum.FEEDBACK){
	   			task1Score+=1;
	   		}
	   		if(activity2.activityType == ActivityEnum.PRODUCTION || activity2.activityType == ActivityEnum.FEEDBACK){
	   			task2Score+=1;
	   		}   			    			   		
	   	}
	
	   if (task1Score > task2Score)
	      return 1;
	   else if (task1Score < task2Score)
	      return -1;
	   else
	      return 0;
	}    
         