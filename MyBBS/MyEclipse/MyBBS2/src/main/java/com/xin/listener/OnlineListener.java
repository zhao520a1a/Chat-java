package com.xin.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineListener implements HttpSessionListener {

	private static int count;

	public void sessionCreated(HttpSessionEvent arg0)  { 
		++count;
    }

	
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	--count;
    }
	
}
