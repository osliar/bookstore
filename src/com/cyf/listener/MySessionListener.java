package com.cyf.listener;


import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class MySessionListener implements HttpSessionListener {
	    public void sessionCreated(HttpSessionEvent se) {
	        ServletContext context = se.getSession().getServletContext();
	        Integer onLineCount = (Integer) context.getAttribute("onLineCount");
	        if(onLineCount==null){
	            context.setAttribute("onLineCount", 1);
	        }else{
	            onLineCount++;
	            context.setAttribute("onLineCount", onLineCount);
	        }
	    }

	    @Override
	    public void sessionDestroyed(HttpSessionEvent se) {
	        ServletContext context = se.getSession().getServletContext();
	        Integer onLineCount = (Integer) context.getAttribute("onLineCount");
	        if(onLineCount==null||onLineCount==0){
	            context.setAttribute("onLineCount", 0);
	        }else if(onLineCount>=1){
	            onLineCount--;
	            context.setAttribute("onLineCount", onLineCount);
	        }
	    }
}
