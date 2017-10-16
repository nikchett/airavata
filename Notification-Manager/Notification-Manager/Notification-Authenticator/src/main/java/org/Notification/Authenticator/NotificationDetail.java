package org.Notification.Authenticator;

import org.Notification.Authenticator.Models.Request;
import org.Notification.Authenticator.stubs.NotifyRequestDetailsServer;
import org.apache.thrift.TException;
/*
 * This class checks what is the status of the request and according picks up the list of the authorized receipients of the notification.
 * Currently there are 2 sample conditions. But this will be modified*/
public class NotificationDetail {
	public String processRequest(String requestID) {
		Request request = new Request();
		
		try {
			request = (new NotifyRequestDetailsServer()).getStatus(requestID);
			// checks if the user's request is accepted then notify the user.
			if(request.status.equals("Accepted")) {
				return request.emailID;
			}
			// checks if the user's request is new and is to be sent to the admin.
			else if(request.status.equals("admin")) {
				return (new NotifyRequestDetailsServer()).getAdmin(requestID).emailID;
			}
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
