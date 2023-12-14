package display.console;

import java.sql.SQLException;
import java.util.Scanner;

import data.entity.Contact;
import services.ContactService;

public class ContactFormView {
	
	Contact c;
	ContactService s;
	
	public ContactFormView() {
		this.s = new ContactService();
		this.c  = new Contact();
		this.display();
	}
	
	public void display() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Création d'un contact:");
		System.out.println("Nom:");
		this.c.setName(scan.nextLine());
		System.out.println("Prenom:");
		this.c.setFirstName(scan.nextLine());
		System.out.println("Tel:");
		this.c.setTel(scan.nextLine());
		System.out.println("Email:");
		this.c.setEmail(scan.nextLine());
		System.out.println("Apercu:");
		System.out.println(this.c);
		System.out.println("Créer le contact? O / N");
		String answer = scan.nextLine();
		if(answer.equals("O")) {
			s.addContact(c);
		}
		
	}

}
