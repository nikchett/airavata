package org.Notification.API;

import org.apache.thrift.transport.TServerSocket;
import com.rabbitmq.client.*;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


import java.io.IOException;

import org.Notification.Authenticator.NotificationDetail;
import org.Notification.Sender.MailNotification;


public class NotifySubmissionServer {
	public static void StartsimpleServer() {
		try {
			   
		      //Create a connection factory
		      ConnectionFactory factory = new ConnectionFactory();
		      //Set the host to the location of the RabbitMQ server
		      factory.setHost("localHost");
		      //Open a new connection
		      Connection connection = factory.newConnection();

		      Channel channel = connection.createChannel();
	
		      channel.queueDeclare("notify", false, false, false, null);
		      //Comfort logging
		      System.out.println(
		         " [*] Waiting for messages. To exit press CTRL+C");
		      System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		      
		  

		      Consumer consumer = new DefaultConsumer(channel) {
		        @Override
		        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		            throws IOException {
		          String message = new String(body, "UTF-8");
		          String mail = (new NotificationDetail()).processRequest(message);
		          System.out.println("888888888mail"+mail);
		          (new MailNotification()).sendMail(message, mail);
		          System.out.println(" [x] Received '" + message + "'");
		        }
		       
		      };
		      channel.basicConsume("notify", true, consumer);
		      
		       
		    } catch(Exception e){
		      //Dump any error to the console
		      e.printStackTrace();
		    }
		 }
		 
		 public static void main(String[] args) {
		  StartsimpleServer();
		 }

}
