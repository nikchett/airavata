package org.apache.airavata.allocation.manager.notification.api;

import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;

public class NotifySubmissionServer {
	public static void StartsimpleServer(NotifySubmissionService.Processor<NotifySubmissionServerHandler> processor) {
		  try {
		   TServerTransport serverTransport = new TServerSocket(9091);
		   TServer server = new TSimpleServer(
		     new Args(serverTransport).processor(processor));

		   // Use this for a multithreaded server
		   // TServer server = new TThreadPoolServer(new
		   // TThreadPoolServer.Args(serverTransport).processor(processor));

		   System.out.println("Starting the simple server...");
		   server.serve();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }
		 
		 public static void main(String[] args) {
		  StartsimpleServer(new NotifySubmissionService.Processor<NotifySubmissionServerHandler>(new NotifySubmissionServerHandler()));
		 }

}
