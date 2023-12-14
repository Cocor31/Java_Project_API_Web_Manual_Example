package data.dao;

import java.sql.SQLException;
import java.util.List;

import data.entity.Contact;

/**
 * @author Boris
 *
 */
public interface ContactDAO {
	/**
	 * INSERT Contact
	 * @param c
	 * @return 
	 * @throws SQLException
	 */
	public int addContact(Contact c) throws SQLException;
	/**
	 * DELETE Contact
	 * @param c
	 * @throws SQLException
	 */
	public void removeContact(Contact c) throws SQLException;
	/**
	 * UPDATE Contact
	 * @param c
	 * @throws SQLException
	 */
	public void saveContact(Contact c) throws SQLException;
	/**
	 * SELECT * Contact
	 * @return
	 * @throws SQLException
	 */
	public List<Contact> listAllContacts() throws SQLException;
}
