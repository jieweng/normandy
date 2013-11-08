package com.pearl.normandy.controllers
{
	import com.adobe.cairngorm.control.FrontController;
	import com.pearl.normandy.commands.activity.*;
	import com.pearl.normandy.commands.comment.*;
	import com.pearl.normandy.commands.customer.*;
	import com.pearl.normandy.commands.feedback.*;
	import com.pearl.normandy.commands.process.*;
	import com.pearl.normandy.commands.processstep.*;
	import com.pearl.normandy.commands.project.*;
	import com.pearl.normandy.commands.projectuser.*;
	import com.pearl.normandy.commands.task.*;
	import com.pearl.normandy.commands.taskdetail.*;
	import com.pearl.normandy.event.*;
	
	public class CenteralController extends FrontController
	{
		
		public function CenteralController()
		{
			//=============================================================================
			//                          Customer
			//=============================================================================
			addCommand(CustomerEvent.EVENT_GET_ALL_CUSTOMER, 	GetAllCustomerCommand);				
			
			//=============================================================================
			//                          Project
			//=============================================================================
			addCommand(ProjectEvent.EVENT_GET_ALL_PROJECT, 		GetAllProjectCommand);
			addCommand(ProjectEvent.EVENT_GET_PROJECT_BY_USER, 	GetProjectCommand);
			addCommand(ProjectEvent.EVENT_CREATE_PROJECT, 		CreateProjectCommand);
			addCommand(ProjectEvent.EVENT_UPDATE_PROJECT, 		UpdateProjectCommand);						


			//=============================================================================
			//                          Process
			//=============================================================================
			addCommand(ProcessEvent.EVENT_GET_PROJECT_PROCESS, 	GetProjectProcessCommand);
			addCommand(ProcessEvent.EVENT_SAVE_PROCESS, 		SaveProcessCommand);
			addCommand(ProcessEvent.EVENT_DELETE_PROCESS, 		DeleteProcessCommand);
			
			//=============================================================================
			//                          Process Step
			//=============================================================================
			addCommand(ProcessStepEvent.EVENT_DELETE_PROCESS_STEP, 		DeleteProcessStepCommand);						
			


			//=============================================================================
			//                          Task
			//=============================================================================
			addCommand(TaskEvent.EVENT_GET_PROJECT_MILESTONE, 		GetProjectMilestoneCommand);
			addCommand(TaskEvent.EVENT_GET_PROJECT_TASK_CATEGORY,	GetProjectTaskCategoryCommand);
			addCommand(TaskEvent.EVENT_GET_PROJECT_TASK_GROUP, 		GetProjectTaskGroupCommand);
			addCommand(TaskEvent.EVENT_GET_TASK_TASK_GROUP, 		GetTaskTaskGroupCommand);							
			addCommand(TaskEvent.EVENT_GET_USER_TASK, 				GetUserTaskCommand);
			addCommand(TaskEvent.EVENT_GET_PROJECT_TASK, 			GetProjectTaskCommand);
			addCommand(TaskEvent.EVENT_GET_LEAD_TASK, 				GetLeadTaskCommand);
			addCommand(TaskEvent.EVENT_CREATE_TASK, 				CreateTaskCommand);
			addCommand(TaskEvent.EVENT_ADD_SUBTASK, 				AddSubtaskCommand);
			addCommand(TaskEvent.EVENT_UPDATE_TASK, 				UpdateTaskCommand);
			addCommand(TaskEvent.EVENT_CANCEL_TASK, 				CancelTaskCommand);
			addCommand(TaskEvent.EVENT_COPY_TASK, 					CopyTaskCommand);
			addCommand(TaskEvent.EVENT_CREATE_TASK_FROM_MPP,		CreateTaskFromMppCommand);
			
			addCommand(TaskDetailEvent.EVENT_GET_TASK_DETAIL,   	GetTaskDetailCommand);
			addCommand(TaskDetailEvent.EVENT_UPDATE_TASK_DETAIL,	UpdateTaskDetailCommand);
			
			//=============================================================================
			//                          Activity
			//=============================================================================
			addCommand(ActivityOptEvent.EVENT_GET_TASK_ACTIVITY,   			GetTaskActivityCommand);
			addCommand(ActivityOptEvent.EVENT_GET_TASK_DELETED_ACTIVITY,   	GetTaskDeletedActivityCommand);			
			addCommand(ActivityOptEvent.EVENT_CREATE_ACTIVITY,   			CreateActivityCommand);
			addCommand(ActivityOptEvent.EVENT_UPDATE_ACTIVITIES, 			UpdateActivitiesCommand);
			
			//=============================================================================
			//                          Project User
			//=============================================================================			
			addCommand(ProjectUserEvent.EVENT_GET_PROJECT_LEAD, GetProjectLeadCommand);
			
			
			
			//=============================================================================
			//                          Feedback/Comment
			//=============================================================================
			addCommand(FeedbackEvent.EVENT_GET_TASK_FEEDBACK,	GetTaskFeedbackCommand);
			addCommand(FeedbackEvent.EVENT_SAVE_FEEDBACK,		SaveFeedbackCommand);
			addCommand(CommentEvent.EVENT_GET_TASK_COMMENT,		GetTaskCommentCommand);
			addCommand(CommentEvent.EVENT_CREATE_COMMENT,		CreateCommentCommand);							
		}		
	}
}