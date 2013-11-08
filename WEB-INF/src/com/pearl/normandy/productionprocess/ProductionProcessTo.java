package com.pearl.normandy.productionprocess;

import java.util.List;

import com.pearl.normandy.processstep.ProcessStepTo;
import com.pearl.normandy.utils.Constants;

public class ProductionProcessTo extends ProductionProcess {

	private static final long serialVersionUID = 1L;
	
	private List<ProcessStepTo> processSteps;
	private static ProductionProcessTo defaultProductionProcess;

	public static ProductionProcessTo getDefault() {
		if (defaultProductionProcess == null) {
			defaultProductionProcess = new ProductionProcessTo();
			defaultProductionProcess.setId(0);
			defaultProductionProcess.setProcessName(Constants.SELECT_ITEM_ALL);
		}

		return defaultProductionProcess;
	}	
	

	public List<ProcessStepTo> getProcessSteps() {
		return processSteps;
	}

	public void setProcessSteps(List<ProcessStepTo> processSteps) {
		this.processSteps = processSteps;
	}
}