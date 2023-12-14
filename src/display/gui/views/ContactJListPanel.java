package display.gui.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.entity.Contact;
import display.gui.controllers.AbstractController;
import display.gui.controllers.ContactController;

public class ContactJListPanel extends AbstractView implements Observer {
	
	private ContactJListModel model;
	private JList contactList;
	
	private ContactController contactCtrl;
	
	public ContactJListPanel(AbstractController absCtrl) {
		ContactController contactCtrl = (ContactController) absCtrl;
		this.contactCtrl = (ContactController) contactCtrl;
		contactCtrl.addObserver(this);
		
		this.setPreferredSize(new Dimension(150, 10));
		this.setMinimumSize(new Dimension(150, 10));
		
		this.setLayout(new BorderLayout(0, 0));
		
		model = new ContactJListModel();
		contactList = new JList(new ContactJListModel());
		contactList.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Contact c = (Contact) contactList.getSelectedValue();
				contactCtrl.contactChanged(c);
			}
		});
		this.add(contactList, BorderLayout.CENTER);
		
		JButton btnNouveau = new JButton("Nouveau");
		btnNouveau.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				contactCtrl.contactChanged(new Contact());				
			}
		});
		this.add(btnNouveau, BorderLayout.SOUTH);
	}

	@Override
	public void update(Observable o, Object arg) {
		// mettre a jour la jlist
		if(arg instanceof Contact) {
			Contact c = (Contact) arg;
			if(c.isEdited()) {
				if(model.contains(c)) {
					model.set(model.indexOf(c), c);
					contactList.repaint();
				} else {
					model.addElement(c);
				}
				
			}
			if(c.isDeleted()) {
				contactList.setSelectedIndex(-1);
				model.removeElement(c);
				contactList.setModel( model );
			}
		}
	}
}
