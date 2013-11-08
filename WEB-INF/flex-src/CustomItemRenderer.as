package {
  import com.pearl.normandy.utils.Constants;
  import com.pearl.normandy.vo.ProjectMemberVO;
  import com.pearl.normandy.vo.UserVO;
  
  import flash.display.Bitmap;
  import flash.display.BitmapData;
  import flash.events.MouseEvent;
  
  import ilog.orgchart.OrgChartItem;
  import ilog.orgchart.OrgChartItemRenderer;
  
  import mx.collections.ArrayCollection;
  import mx.controls.Alert;
  import mx.controls.DataGrid;
  import mx.controls.Image;
  import mx.controls.Tree;
  import mx.core.DragSource;
  import mx.core.UIComponent;
  import mx.events.CloseEvent;
  import mx.events.DragEvent;
  import mx.managers.DragManager;


  /**
   * This class extends the default item renderer to support drag and drop gesture. 
   */  
  public class CustomItemRenderer extends OrgChartItemRenderer
  {
    public function CustomItemRenderer()
    {
      super();
      
      addEventListener(MouseEvent.MOUSE_DOWN, dragMouseDownHandler);
      addEventListener(DragEvent.DRAG_ENTER, dragEnterHandler);
      addEventListener(DragEvent.DRAG_DROP, dragDropHandler);
      addEventListener(DragEvent.DRAG_EXIT, dragExitHandler);
    }
    
    private function isMoveEnabled():Boolean {
      return orgChart.allowNavigation == false;
    }
    
    private var _image:Image;
    
    /**
     * Starts the drag drop gesture.
     */   
    private function dragMouseDownHandler(event:MouseEvent):void {
      if (!isMoveEnabled()) {
        return;
      }
      
      var member:ProjectMemberVO = OrgChartItem(data).data as ProjectMemberVO;
      
      if (member.name == 'PDE') {
        return;
      }
      
      var ds:DragSource = new DragSource();
      ds.addData(OrgChartItem(data).data, "data");
            
      var bitmapData:BitmapData = new BitmapData(unscaledWidth, unscaledHeight, true);
      bitmapData.draw(this);
      var bitmap:Bitmap = new Bitmap(bitmapData)                
      _image = new Image();
      _image.source = bitmap;
                 
      parent.addChild(_image);
      
      DragManager.doDrag(this, ds, event, _image);
    }

    /**
     * Handles drag enter event.
     */  
    private function dragExitHandler(event:DragEvent):void {
      orgChart.selectedItems = [];
    }

    
    /**
     * Handles drag enter event.
     */  
    private function dragEnterHandler(event:DragEvent):void {
		if(event.dragInitiator == this)
		return;
		
		var oldItem:ProjectMemberVO;
		var newParent:ProjectMemberVO = OrgChartItem(this.data).data as ProjectMemberVO;

		if(!(event.dragInitiator as DataGrid)){
			if(event.dragInitiator as Tree){
				oldItem = event.dragSource.dataForFormat("treeItems")[0] as ProjectMemberVO;
			}else{
				oldItem = event.dragSource.dataForFormat("data") as ProjectMemberVO;
			}
			
			if(oldItem.memberType != Constants.USER_TYPE_EMPLOYEE ||(newParent.memberType == Constants.RESOURCE_PROJECT && oldItem.projectId == newParent.projectId))
			return;
		}
		if(newParent.memberType != Constants.RESOURCE_PROJECT)
		return;
		
		orgChart.selectedItems = [newParent];
		DragManager.acceptDragDrop(this);
    }
    
    /**
     * Clean up resources after drop
     */      
    public function endDragDrop():void {
      _image = null;
    }
	
	private var item:ProjectMemberVO;
  	private var oldItem:ProjectMemberVO;
	private var oldParent:ProjectMemberVO;
  	private var newParent:ProjectMemberVO;
    private function dragDropHandler(event:DragEvent):void {
    	event.preventDefault();
    	
		newParent = OrgChartItem(this.data).data as ProjectMemberVO;
      	if(event.dragInitiator as DataGrid){
	      	var userVO:UserVO = event.dragSource.dataForFormat("items")[0] as UserVO;
	      	oldItem = new ProjectMemberVO();
	      	oldItem.userId = userVO.id;
	      	oldItem.name = userVO.name;
	      	oldItem.employeeNo = userVO.employeeNo;
	      	oldItem.email = userVO.email;
	      	oldItem.memberType = Constants.USER_TYPE_EMPLOYEE;
   		}else if(event.dragInitiator as Tree)
      		oldItem = event.dragSource.dataForFormat("treeItems")[0] as ProjectMemberVO;
      	else{
      		item = event.dragSource.dataForFormat("data") as ProjectMemberVO;
      		oldItem = new ProjectMemberVO();
        	oldItem.userId = item.userId;
        	oldItem.name = item.name;
        	oldItem.employeeNo = item.employeeNo;
        	oldItem.email = item.email;
        	oldItem.memberType = item.memberType;
        	oldItem.isProjectUser = item.isProjectUser;
          	oldParent = (parentDocument as OrgChartPDE).hCol.getParentItem(item);
      	}
      	
      	if(event.ctrlKey || event.shiftKey){
      		if(event.dragInitiator as DataGrid){
	      		(parentDocument as OrgChartPDE).dataPool.removeItemAt((parentDocument as OrgChartPDE).dataPool.getItemIndex(event.dragSource.dataForFormat("items")[0]));
	      	}else if(event.dragInitiator as Tree){
//	      		(parentDocument as OrgChartPDE).dataPool.removeItemAt((parentDocument as OrgChartPDE).dataPool.getItemIndex(event.dragSource.dataForFormat("treeItems")[0]));
	      	}else{
	      		if(event.ctrlKey){
	      			addProjectMember();
	      		}else if(event.shiftKey && oldItem.isProjectUser == Constants.BOOLEAN_YES){
		      		addProjectMember();
		      		(parentDocument as OrgChartPDE).hCol.removeChild(oldParent,item);
	      		}else{
	      			Alert.show('此人目前还有未删除的项目角色');
	      		}
	      		return;
	      	}
			addProjectMember();
      	}else{
      		if(event.dragInitiator as DataGrid){
      			addProjectMember();
      			(parentDocument as OrgChartPDE).dataPool.removeItemAt((parentDocument as OrgChartPDE).dataPool.getItemIndex(event.dragSource.dataForFormat("items")[0]));
      		}else
      			Alert.show(resourceManager.getString('Language','alert.org_chart_move'),resourceManager.getString("Language","title.confirmation"),Alert.YES|Alert.NO,UIComponent(this.parentApplication),confirmHandler,null,Alert.NO);
      	}
    }
    
    public function addProjectMember():void{
		var array:ArrayCollection = newParent.children as ArrayCollection;
		for each(var member:ProjectMemberVO in array){
			if(member.userId == oldItem.userId)
			return;
		}
		
      	(parentDocument as OrgChartPDE).hCol.addChild(newParent,oldItem);
      	oldItem.projectId = newParent.projectId;
      	oldItem.status = Constants.RESOURCE_PROJECT_STATUS_ASSIGNED;
      	oldItem.createdDate = new Date();
      	oldItem.createdBy = 'TEST';
      	(parentDocument as OrgChartPDE).projectMemberRo.createProjectMember(oldItem);
      	
    }
    
    private function confirmHandler(event:CloseEvent):void{
    	if(event.detail == Alert.YES){
    		if(oldItem.isProjectUser == Constants.BOOLEAN_YES)
    		addProjectMember();
    		
    		(parentDocument as OrgChartPDE).projectMemberRo.deleteProjectMember(oldItem.userId,oldItem.projectId,new ArrayCollection());
    	}
    }
  }
  }