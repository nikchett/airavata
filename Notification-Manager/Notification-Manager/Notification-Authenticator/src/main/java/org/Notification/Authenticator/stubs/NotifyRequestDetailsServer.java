package org.Notification.Authenticator.stubs;

import org.apache.thrift.*;
import org.Notification.Authenticator.Models.*;
import org.Notification.Authenticator.Repo.Data;

import java.util.*;


public class NotifyRequestDetailsServer implements NotificationRequestDetailsService.Iface {

	@Override
	public List<Reviewer> getReviewers(String requestID) throws TException {
		
		return (new Data()).getReviewers(requestID);
	}

	@Override
	public Admin getAdmin(String requestID) throws TException {
		// TODO Auto-generated method stub
		return (new Data()).getAdmin(requestID);
	}

	@Override
	public Request getStatus(String requestID) throws TException {
		// TODO Auto-generated method stub
		return (new Data()).getStatus(requestID);
	}

}
