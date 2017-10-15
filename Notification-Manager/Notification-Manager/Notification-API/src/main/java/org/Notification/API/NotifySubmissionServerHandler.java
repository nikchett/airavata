package org.Notification.API;

import org.Notification.Sender.MailNotification;
import org.apache.thrift.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class NotifySubmissionServerHandler implements NotifySubmissionService.Iface {

	@Override
	public String notifySubmission(String requestID) throws TException {
		(new MailNotification()).sendMail(requestID, (new NotifySubmissionServerHandler()).getAdminId());
		return "Request processed" + requestID;
	}

	public String getAdminId() {
		return "demosga123@gmail.com";
	}
}
