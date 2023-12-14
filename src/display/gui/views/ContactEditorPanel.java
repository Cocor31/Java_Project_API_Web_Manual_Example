package display.gui.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import data.entity.Contact;
import display.gui.controllers.AbstractController;
import display.gui.controllers.ContactController;

public class ContactEditorPanel extends JPanel implements Observer {
	
	private ContactController contactCtrl;
	
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textTel;
	private JTextField textEmail;
	
	private JButton btnSupprimer;
	
	private Contact currentContact;

	public ContactEditorPanel(ContactController contactCtrl) {
		this.contactCtrl = contactCtrl;
		contactCtrl.addObserver(this);
		
		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNom = new JLabel("Nom");
		this.add(lblNom, "4, 4, right, default");
		
		textNom = new JTextField();
		this.add(textNom, "6, 4, fill, default");
		textNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom");
		this.add(lblPrenom, "4, 6, right, default");
		
		textPrenom = new JTextField();
		this.add(textPrenom, "6, 6, fill, default");
		textPrenom.setColumns(10);
		
		JLabel lblTel = new JLabel("Tel");
		this.add(lblTel, "4, 8, right, default");
		
		textTel = new JTextField();
		this.add(textTel, "6, 8, fill, default");
		textTel.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		this.add(lblEmail, "4, 10, right, default");
		
		textEmail = new JTextField();
		this.add(textEmail, "6, 10, fill, default");
		textEmail.setColumns(10);
		
		JButton btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				fillContact();
				saveContact();
			}
		});
		this.add(btnSauvegarder, "6, 12");
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeContact();
			}
		});
		btnSupprimer.setEnabled(false);
		this.add(btnSupprimer, "6, 14");
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof Contact) {
			Contact c = (Contact) arg;
			if( !c.isEdited()) {
				System.out.println("ContactEditorPanel.update : "+ c);
				currentContact =c; 
				fillForm(c);
			}
			if( c.isDeleted()) {
				this.currentContact = null;
				// empty fields
				// maj btns
			}
			
		}
	}
	
	private void fillForm(Contact c) {
		this.textNom.setText(c.getName());
		this.textPrenom.setText(c.getFirstName());
		this.textTel.setText(c.getTel());
		this.textEmail.setText(c.getEmail());
		this.allowDelete(!c.isNew()); // send false if new contact
	}
	
	private void allowDelete(boolean status) {
		btnSupprimer.setEnabled(status);
	}
	
	private void fillContact() {
		this.currentContact.setName(textNom.getText());
		this.currentContact.setFirstName(textPrenom.getText());
		this.currentContact.setTel(textTel.getText());
		this.currentContact.setEmail(textEmail.getText());
	}
	
	private void saveContact() {
		this.contactCtrl.saveContact(this.currentContact);
	}
	
	private void removeContact() {
		this.contactCtrl.removeContact(this.currentContact);
	}
}
