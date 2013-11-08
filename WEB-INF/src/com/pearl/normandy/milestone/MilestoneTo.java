package com.pearl.normandy.milestone;

import com.pearl.normandy.utils.Constants;

public class MilestoneTo extends Milestone {

	private static final long serialVersionUID = -2875504417786757397L;

	private static MilestoneTo defaultMilestone;

	public static MilestoneTo getDefault() {
		if (defaultMilestone == null) {
			defaultMilestone = new MilestoneTo();
			defaultMilestone.setId(0);
			defaultMilestone.setMilestone(Constants.SELECT_ITEM_ALL);
		}

		return defaultMilestone;
	}
}
