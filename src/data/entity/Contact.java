package data.entity;

/**
 * @author Boris
 *
 */
public class Contact {

	/**
	 * 
	 */
	private int id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String firstName;
	/**
	 * 
	 */
	private String tel;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private boolean edited;
	/**
	 * 
	 */
	private boolean deleted;
	
	/**
	 * description ...
	 * @param name
	 * @param firstName
	 */
	public Contact(String name, String firstName) {
		super();
		this.name = name;
		this.firstName = firstName;
	}

	/**
	 * @param id
	 * @param name
	 * @param firstName
	 * @param tel
	 * @param email
	 */
	public Contact(int id, String name, String firstName, String tel, String email) {
		this(name, firstName);
		this.id = id;
		this.tel = tel;
		this.email = email;
	}

	public Contact() {
	}
	
	public boolean isNew() {
		return (this.id == 0);
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	

	/**
	 * @return
	 */
	public boolean isEdited() {
		return edited;
	}

	/**
	 * @param edited
	 */
	public void setEdited(boolean edited) {
		this.edited = edited;
	}
	
	/**
	 * @return
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * deleted attr setter
	 * @param deleted
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", firstName=" + firstName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
