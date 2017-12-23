package myserver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;



public class Connection implements Runnable {

	private Server server;
	private Socket client;
	private InputStream input;
	private OutputStream output;

	public Connection(Socket client, Server server) {
		this.client = client;
		this.server = server;
	}


	public void run() {
		try {
			input = client.getInputStream();
			output = client.getOutputStream();

			HttpRequest request = HttpRequest.parseAsHttp(input);
			
			if (request != null) {
				System.out.println("Request for " + request.getUrl() + " is being processed " +
					"by socket at " + client.getInetAddress() +":"+ client.getPort());
				
				HttpResponse response;
				
				String method;
				if ((method = request.getMethod()).equals("GET") 
						|| method.equals("HEAD")) {
					File f = new File(server.getWebRoot() + request.getUrl());
					response = new HttpResponse("200 OK").withFile(f);
					System.out.println("Repsonse");
					if (method.equals("HEAD")) {
						response.removeBody();
					}
				} else {
					response = new HttpResponse("Not Implemented");
				}
				
				respond(response);
				
			} else {
				System.err.println("Server accepts only HTTP protocol.");
			}
			
			input.close();
			output.close();
		} catch (IOException e) {
			System.err.println("Error in client's IO.");
		} finally {
			try {
				client.close();
			} catch (IOException e) {
					System.err.println("Error while closing client socket.");
			}
		}
	}

	public void respond(HttpResponse response) {
		String toSend = response.toString();
		PrintWriter writer = new PrintWriter(output);
		writer.write(toSend);
		writer.flush();
	}

}
