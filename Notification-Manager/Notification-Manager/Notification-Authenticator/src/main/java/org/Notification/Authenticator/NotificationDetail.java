package org.Notification.Authenticator;

import org.Notification.Authenticator.Models.Request;
import org.Notification.Authenticator.stubs.NotifyRequestDetailsServer;
import org.apache.thrift.TException;

public class NotificationDetail {
	public String processRequest(String requestID) {
		Request request = new Request();
		
		try {
			request = (new NotifyRequestDetailsServer()).getStatus(requestID);
			
			if(request.status.equals("Accepted")) {
				return request.emailID;
			}else if(request.status.equals("admin")) {
				return (new NotifyRequestDetailsServer()).getAdmin(requestID).emailID;
			}
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
