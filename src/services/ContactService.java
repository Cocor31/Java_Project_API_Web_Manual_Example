package services;

import java.sql.SQLException;
import java.util.List;

import data.dao.ContactDAOImpl;
import data.entity.Contact;

public class ContactService {
	private ContactDAOImpl cdao;
	
	public ContactService() {
		this.cdao = new ContactDAOImpl();
	}
	
	public List<Contact> listAllContacts() {
		try {
			return cdao.listAllContacts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int addContact(Contact c) {
		try {
			return cdao.addContact(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void saveContact(Contact c) {
		try {
			cdao.saveContact(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void deleteContact(Contact c) {
		try {
			cdao.removeContact(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
