package com.notifySubmission.server;
import org.apache.thrift.*;
public class NotifySubmissionServerHandler implements NotifySubmissionService.Iface {

	 @Override
	 public String notifySubmission(String requestID) throws TException {
	  return "Request processed"+requestID;
	 }

	}

