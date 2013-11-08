package com.pearl.normandy.component.progressWindow
{
	public interface IProgress {
		function progress(bytesLoaded:int, bytesTotal:int):void;
	}
}