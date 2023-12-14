package data.source;

import java.lang.invoke.ConstantCallSite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Boris
 *
 */
public class MysqlDataSource {
	
	public static final String URL = "jdbc:mysql://localhost/addressbook?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    
    private Connection conn = null;
	
	/**
	 * Static reference to itself 
	 */
	private static MysqlDataSource instance = null;
	
	/**
	 * Private constructor 
	 */
	private MysqlDataSource() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	
	/**
	 * Return a unique instance of MysqlDataSource
	 * @return MysqlDataSource
	 */
	public static MysqlDataSource getInstance() { 
		if(instance == null) {
			instance = new MysqlDataSource();
		}
		return instance;
	}
	
	/**
	 * Create a connection
	 * @return
	 */
	public Connection createConnection() {
		if( conn == null ) {
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return conn;	
	}
	
	
	/**
	 * Close current instance connection 
	 * And set corresponding attribute to null
	 */
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = null;
	}
	
	
	/**
	 * Return a new or existing connection
	 * @param isNew
	 * @return
	 */
	public static Connection getConnnection(boolean isNew) {
		MysqlDataSource ds = getInstance();
		if(isNew) ds.closeConnection();
		return ds.createConnection();
	}
	
	
	/**
	 * Call getConnnection with default argument false
	 * @return Connection
	 */
	public static Connection getConnnection() {
		return getConnnection(false);
	}
}
