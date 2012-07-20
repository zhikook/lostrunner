package org.gunnm.lostrunner;

import org.gunnm.lostrunner.graphics.LostRenderer;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class LostRunner extends Activity {
	
	private boolean 		fullScreen = false;
	private GLSurfaceView 	surface;
	private LostRenderer	renderer;
	
	private GestureDetector gestureDetector;
	private static boolean 	fullscreen;
	 
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (fullScreen) {
        	requestWindowFeature(Window.FEATURE_NO_TITLE);  
        	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        	WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
         
        gestureDetector = new GestureDetector(this, new GlAppGestureListener(this));
        
        surface = new GLSurfaceView(this);
        renderer = new LostRenderer();
        surface.setRenderer(renderer);
        setContentView(surface);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	protected void onPause() {
		super.onPause();
		surface.onPause();
	}

	protected void onResume() {
		super.onResume();
		surface.onResume();
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		if (gestureDetector.onTouchEvent(event)) {
			return true;
		}
		return super.onTouchEvent(event);
	}

	private class GlAppGestureListener extends GestureDetector.SimpleOnGestureListener
    {
    	private LostRunner currentApp;
    	
    	public GlAppGestureListener(LostRunner app) {
    		this.currentApp = app;
    	}

		public boolean onDoubleTap(MotionEvent e) {

			LostRunner.fullscreen = !LostRunner.fullscreen;
			
			Intent intent = new Intent(currentApp, LostRunner.class);
			startActivity(intent);

			currentApp.finish();
			
			return true;
		}
    }
}


