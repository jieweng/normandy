// ActionScript file
	import com.pearl.normandy.vo.ActivityVO;
	import com.pearl.normandy.vo.UserVO;
	
	import ilog.gantt.ResourceChart;
	
	import mx.collections.ArrayCollection;
	
	
    //==============================
    //Collection Filter Methods
    //==============================  
    private function updateCollectionFilter(resourceChart:ResourceChart):void{
    	updateResourceFilter(resourceChart);
    	updateActivityFilter(resourceChart);
    }	
    	
  	private function updateResourceFilter(resourceChart:ResourceChart):void{
  		//update resource collection
    	var resourceCollection:ICollectionView = resourceChart.resourceDataProvider as ICollectionView;
    	if (resourceCollection.filterFunction == null) {
      	resourceCollection.filterFunction = resourceFilter;
    	}
    	resourceCollection.refresh();    		
  	}
  
  	/**
   	* The collection view filter that displays only current project resource.
   	*/  
  	private function resourceFilter(item:Object):Boolean {
     	var user:UserVO = item as UserVO;
   
   		if(this.hasOwnProperty("currProjectOnlyCB") && this["currProjectOnlyCB"].selected){ 
			if(user.projectsId){
				var textArr:ArrayCollection = new ArrayCollection(user.projectsId.split(","));
				for each(var projectId:String in textArr){
					if(projectId==model.getCurrProject(SCREEN_TYPE).id.toString()){
						return true;
					}
				}
			}
			
			return false;
   		}
   		else{   
   			return true;
   		}          
  	}       
    
  	/**
   	* Refreshes the activity collection view. 
   	*/  
  	private function updateActivityFilter(resourceChart:ResourceChart):void {
  		//update activity collection
    	var activityCollection:ICollectionView = resourceChart.taskDataProvider as ICollectionView;
    	if (activityCollection.filterFunction == null) {
      	activityCollection.filterFunction = activityFilter;
    	}
    	activityCollection.refresh();   	
  	}

  
  	/**
   	* The collection view filter that displays only current project activites.
   	*/  
  	private function activityFilter(item:Object):Boolean {
    	var activity:ActivityVO = item as ActivityVO;
   
   		if(this.hasOwnProperty("currProjectOnlyCB") &&
   				this["currProjectOnlyCB"].selected && 
   				activity.projectId!=model.getCurrProject(SCREEN_TYPE).id){
   			return false;
   		}
   		else{
    		return true;
    	}             
  	} 	
 		