package display.web.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;

public class HomeHttpController {
	
	// URL HTTP GET /
	public static void getHome(HttpExchange request) throws IOException {
		System.out.println("homepage");
		String response = getHomeHtml();
		// write response in http request
		request.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = request.getResponseBody();
		os.write(response.getBytes());
		os.close();	
	}
	
	public static String getHomeHtml() {
		Scanner sc;
		String str = "";
		try {
			sc = new Scanner(new File("html/welcome.html"));
			while(sc.hasNextLine()){
			    str += sc.nextLine();                     
			}
			return str;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
	}

}
