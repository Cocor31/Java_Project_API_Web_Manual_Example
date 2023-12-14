package display.web.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;

import data.entity.Contact;
import services.ContactService;

public class ContactHttpController {

	// URL HTTP GET /contacts
	public static void getAllContacts(HttpExchange request) throws IOException {
		System.out.println("Liste des contacts");
		String response = getContactsJSON();
		// write response in http request
		request.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = request.getResponseBody();
		os.write(response.getBytes());
		os.close();	
	}
	
	// URL HTTP GET /contacts/1
	public static void getOneContact(HttpExchange request) throws IOException {
		System.out.println("Liste un contact");
		String response = "<h1>Contact #1</h1>";
		// write response in http request
		request.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = request.getResponseBody();
		os.write(response.getBytes());
		os.close();	
	}
	
	public static String getContactsHTML() {
		ContactService cs = new ContactService();
		List<Contact> contacts = cs.listAllContacts();
		String html = "<h1>Liste des contacts</h1>";
		for(Contact c : contacts) {
			html += "<div>";
			html += "<h2>" + c.getName() + " " + c.getFirstName() + "</h2>";
			html += "<ul>";
			html += "<li>" + c.getEmail() + "</li>";
			html += "<li>" + c.getTel() + "</li>";
			html += "</ul>";
			html += "</div>";
		}
		return html;
	}
	
	public static String getContactsXML() {
		return "";
	}
	
	public static String getContactsJSON() {
		ContactService cs = new ContactService();
		List<Contact> contacts = cs.listAllContacts();
		String json = "[";
		for(Contact c: contacts) {
			json += "{ lastname: '"+c.getName()+"', firstname:'"+c.getFirstName()+"'}";
		}
		json += "]";
		return json;
	}
	
}
