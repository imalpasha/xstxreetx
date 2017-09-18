package com.open.project.utils;

import android.app.Application;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.util.AQUtility;

import java.util.Arrays;
import java.util.List;

public class App extends Application {

	public static final String FONT_HELVETICA_NEUE = "fonts/HelveticaNeue.ttf";


	public static final String YOUTUBE_API_KEY = "AIzaSyBi8DTbv4W8atAzrixb0AIoaunLd6xjgco";

	// code 7 is success code with badge
	public static final List<String> SUCCESS_CODE = Arrays.asList("1", "7");

    public static String deviceID;

	@Override
	public void onCreate()
	{
		super.onCreate();

		AQUtility.setDebug(true);

		// set the max number of concurrent network connections, default is 4
		AjaxCallback.setNetworkLimit(12);

		// set the max number of icons (image width <= 50) to be cached in
		// memory, default is 20
		BitmapAjaxCallback.setIconCacheLimit(20);

		// set the max number of images (image width > 50) to be cached in
		// memory, default is 20
		BitmapAjaxCallback.setCacheLimit(40);

		// set the max size of an image to be cached in memory, default is 1600
		// pixels (ie. 400x400)
		BitmapAjaxCallback.setPixelLimit(500 * 500);

		// set the max size of the memory cache, default is 1M pixels (4MB)
		BitmapAjaxCallback.setMaxPixelLimit(2000000);

		// set timeout, default is 30 seconds (30000)
		BitmapAjaxCallback.setTimeout(120000);
	}

	@Override
	public void onLowMemory()
	{
		// clear all memory cached images when system is in low memory
		// note that you can configure the max image cache count, see
		// CONFIGURATION
		BitmapAjaxCallback.clearCache();
	}


}
