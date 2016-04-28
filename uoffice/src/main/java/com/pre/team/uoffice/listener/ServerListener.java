/**
 * 
 */
package com.pre.team.uoffice.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.pre.team.uoffice.thread.VerificationCodeThread;

/**
 * Description:
 * @author xuejiahao  2015年7月13日
 * 
 */
public class ServerListener implements ServletContextListener {
	private Thread thread;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		if (thread != null && thread.isInterrupted()) {  
			thread.interrupt();
        } 
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
        if (thread == null) {  
            thread = new Thread(new VerificationCodeThread());
            // servlet 上下文初始化时启动 socket 
            thread.start();  
        }  
	}

}
