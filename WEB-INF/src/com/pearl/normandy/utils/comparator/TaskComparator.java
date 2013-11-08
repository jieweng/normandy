package com.pearl.normandy.utils.comparator;

import java.util.Comparator;

import com.pearl.normandy.task.TaskTo;

@SuppressWarnings("unchecked")
public class TaskComparator implements Comparator{
	
	public int compare(Object arg0, Object arg1) {
		  TaskTo task0=(TaskTo)arg0;
		  TaskTo task1=(TaskTo)arg1;
		  
		  return task0.getId().compareTo(task1.getId());
	}
}
