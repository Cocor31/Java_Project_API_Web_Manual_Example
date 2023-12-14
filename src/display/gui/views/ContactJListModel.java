package display.gui.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import data.entity.Contact;
import display.gui.controllers.ContactController;

public class ContactJListModel extends DefaultListModel {

	public ContactJListModel() {
		load();
	}
	
	public void load() {
		ContactController contactCtrl = new ContactController();
		List<Contact> listContacts = contactCtrl.listAllcontacts();
		for (Contact c : listContacts) {
			this.addElement(c);
		}
	}
}
