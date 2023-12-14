package display.web;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import display.web.controllers.ContactHttpController;
import display.web.controllers.HomeHttpController;

public class BasicHttpServer {
	public BasicHttpServer() {
		try {
			int port = 8500;
			// creation du server
			// start server on port 8500
			HttpServer srv = HttpServer.create(
				new InetSocketAddress(port), 0);
			
			// ecouter les requete http sur l'url /
			// localhost:8500/
			HttpContext context  = srv.createContext("/");
			context.setHandler(HomeHttpController::getHome);
			// HomeController.getHome(request);
			
			// declarer / lier l'url /contacts
			srv.createContext("/contacts")
				.setHandler(ContactHttpController::getAllContacts);
			
			// declarer / lier l'url /contacts/1
			srv.createContext("/contacts/1")
			.setHandler(ContactHttpController::getOneContact);
			
			srv.start();
			System.out.println("Server is listening on port " + port);
		} catch (IOException e) {
			System.out.println("Server start failed...");
			e.printStackTrace();
		}
	}
}
