package com.flx.xs.xsmon.ui;

public class MonitorController {
	MonitorFrame monitorFrame;

	public MonitorFrame getMonitorFrame() {
		return monitorFrame;
	}

	public void setMonitorFrame(MonitorFrame monitorFrame) {
		this.monitorFrame = monitorFrame;
	}
	
	public void initialize() {
		monitorFrame.initialize();
	}
}
