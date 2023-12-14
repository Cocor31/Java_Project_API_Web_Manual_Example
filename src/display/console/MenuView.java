package display.console;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuView {
	
	public MenuView() {
		this.display();
	}
	
	public void display() {
		while(true) {
			System.out.println("Menu");
			System.out.println("1) Creer contact ");
			System.out.println("2) Lister contacts ");
			System.out.println("3) Supprimer un contact ");
			System.out.println("4) Editer un contact ");
			System.out.println("exit pour quitter");
			Scanner scan = new Scanner(System.in);
			try{
				int choice = scan.nextInt();
				this.doAction(choice);
			} catch (InputMismatchException e) {
				String choice = scan.nextLine();
				if(choice.equals("exit")) break;
				else System.out.println("Erreur saisie");
			}
			
			
		}
	}
	
	public void doAction(int num) {
		switch (num) {
		case 1:
			new ContactFormView();
			break;
		default:
			System.out.println("404 not found");
			break;
		}
	}

}
