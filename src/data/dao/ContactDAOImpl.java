package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.entity.Contact;
import data.source.MysqlDataSource;

public class ContactDAOImpl implements ContactDAO {
	
	private Connection conn;
	
	public ContactDAOImpl() {
		conn = MysqlDataSource.getConnnection();
	}

	@Override
	public int addContact(Contact c) throws SQLException {
		String sql = "INSERT INTO contacts (lastname, firstname, tel, email) VALUES(?,?,?,?)";
        PreparedStatement stmnt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        // en spécifiant bien les types SQL cibles
        stmnt.setObject(1, c.getName());
        stmnt.setObject(2, c.getFirstName());
        stmnt.setObject(3, c.getTel());
        stmnt.setObject(4, c.getEmail());
        stmnt.executeUpdate();
        ResultSet rs = stmnt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
	}
	
	@Override
	public void saveContact(Contact c) throws SQLException {
		String sql = "UPDATE contacts SET lastname=?, firstname=?, tel=?, email=? WHERE id=?";
        PreparedStatement stmnt = conn.prepareStatement(sql);
        // en spécifiant bien les types SQL cibles
        stmnt.setObject(1, c.getName());
        stmnt.setObject(2, c.getFirstName());
        stmnt.setObject(3, c.getTel());
        stmnt.setObject(4, c.getEmail());
        stmnt.setObject(5, c.getId());
        stmnt.executeUpdate();
	}

	@Override
	public void removeContact(Contact c) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM contacts WHERE id=?";
        PreparedStatement stmnt = conn.prepareStatement(sql);
        stmnt.setObject(1, c.getId());
        stmnt.executeUpdate();
	}

	@Override
	public List<Contact> listAllContacts() throws SQLException {
		String query = "SELECT * FROM contacts";
		ResultSet rs = conn.createStatement().executeQuery(query);
		
		List<Contact> listContact = new ArrayList<Contact>();
		
		// TODO: Gérer si le resulset ne renvoie pas de résultats
		while(rs.next()) {
			Contact c = new Contact(rs.getInt("id"), rs.getString("lastname"),
						rs.getString("firstname"), rs.getString("tel"), rs.getString("email"));
			listContact.add(c);
		}
		
		return listContact;
	}

}
