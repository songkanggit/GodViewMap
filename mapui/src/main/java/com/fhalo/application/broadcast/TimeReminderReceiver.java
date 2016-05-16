package com.fhalo.application.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeReminderReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		long thisTime=intent.getLongExtra("thisTime", 0);
	}

}
