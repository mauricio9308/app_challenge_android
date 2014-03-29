package com.app.appchallenge.utils;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

/**
 * Asynctask executor for the different android versions
 * */
public final class AsyncTaskUtils {

	private AsyncTaskUtils() {
		/*
		 * D o nothing
		 */
	}

	@SafeVarargs
	public static <T> void executeAsyncTask(AsyncTask<T, ?, ?> task,
			T... params) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			honeycombExecuteAsyncTask(task, params);
		} else {
			task.execute(params);
		}
	}

	@SafeVarargs
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private static <T> void honeycombExecuteAsyncTask(AsyncTask<T, ?, ?> task,
			T... params) {
		task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
	}

}
