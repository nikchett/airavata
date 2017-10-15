package org.Notification.API;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;


import org.apache.thrift.TDeserializer;
import org.apache.thrift.TSerializer;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class NotifySubmissionClient {
	private final static String QUEUE_NAME = "notify";

	public static void main(String[] args) {

//		  try {
//		   TTransport transport;
//
//		   transport = new TSocket("localhost", 9091);
//		   transport.open();
//
//		   TProtocol protocol = new TBinaryProtocol(transport);
//		   NotifySubmissionService.Client client = new NotifySubmissionService.Client(protocol);
//
//		   System.out.println(client.notifySubmission("1001"));
//
//		   transport.close();
//		  } catch (TTransportException e) {
//		   e.printStackTrace();
//		  } catch (TException x) {
//		   x.printStackTrace();
//		  }
		  
		    try {
		    	   
		        //Create a connection factory
		        ConnectionFactory factory = new ConnectionFactory();
		        //Set the host to the location of the RabbitMQ server
		        factory.setHost("localhost");
		        //Open a new connection
		        Connection connection = factory.newConnection();
		        //Channel is the abstraction for interacting with a queue
		        Channel channel = connection.createChannel();
		        //Create the Queue if it does not exist
		        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		       
		        //Create a new instance of the Thrift Serializer
		      //  TSerializer serializer = new TSerializer();
		        //Serialize our domain object to a byte array
		      //  byte[] payload = serializer.serialize("hello");
		        
		        String message  = "1001" ;
		        
		          //Publish message on the queue using our domain
		          //object as the message (or payload)
		          channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		        
		          //Print a friendly message
		          System.out.println(
		             " [x] Sent ");
		        
		       
		        //Close the channel
		        channel.close();
		        //Close the connection
		        connection.close();
		       
		      } catch (Exception e) {
		        //Dump any exception to the console
		        e.printStackTrace();
		      }
		 }
}
