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
		        //assuming this is the request id send
		        String message  = "1001" ;
		        
		      
		          channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		     
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
