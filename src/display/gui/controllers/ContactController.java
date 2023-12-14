package display.gui.controllers;

import java.util.List;
import java.util.Observable;

import data.entity.Contact;
import services.ContactService;

public class ContactController extends Observable implements AbstractController{
	
	private ContactService contactService;
	private Contact currentContact;
	
	public ContactController() {
		contactService = new ContactService();
	}

	public List<Contact> listAllcontacts() {
		return contactService.listAllContacts();
	}
	
	public void contactChanged(Contact c) {
		System.out.println("ContactController.contactChanged : "+c);
		this.setCurrentContact(c);
		this.setChanged();
		this.notifyObservers(c);
	}
	
	public Contact getCurrentContact() {
		return currentContact;
	}

	public void setCurrentContact(Contact currentContact) {
		this.currentContact = currentContact;
	}

	public void saveContact(Contact editedContact) {
		if(editedContact.isNew()) {
			editedContact.setId(contactService.addContact(editedContact));
			System.out.println("Autogen id"+ editedContact.getId());
		} else {
			contactService.saveContact(editedContact);
		}
		editedContact.setEdited(true);
		contactChanged(editedContact);
	}
	
	public void removeContact(Contact editedContact) {
		contactService.deleteContact(editedContact);
		editedContact.setDeleted(true);
		contactChanged(editedContact);
	}
	
	
}
