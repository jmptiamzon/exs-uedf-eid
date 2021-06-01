package com.sprint.uedfeid.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.sprint.uedfeid.controller.Controller;

public class View extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton submitBtn;
	private JTextField serialField;
	private JLabel fieldLbl;
	private Controller controller;
	private GridBagConstraints gbc;
	private Insets inset;
	

	public View() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		controller = new Controller();
		inset = new Insets(5, 5, 5, 5);
		gbc = new GridBagConstraints();
		panel = new JPanel(new GridBagLayout());
		
		serialField = new JTextField(20);
		fieldLbl = new JLabel("Enter serial: ");
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(this);
		
		setLayout(new BorderLayout());
		setResizable(false);
		setTitle("UEDF EID Paired");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(panel, BorderLayout.CENTER);
		setComponent(fieldLbl, panel, 1, 0, 0, 0, 0);
		setComponent(serialField, panel, 0.5, 0, 1, 0, 150);
		setComponent(submitBtn, panel, 0.5, 1, 1, 0, 0);
		
		pack();
		setLocationRelativeTo(null);
	
	}
	
	public void setComponent (Component component, JPanel panel, double weightx, int gridx, int gridy, int ipady, int ipadx) {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = inset;
		gbc.weightx = weightx;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.ipady = ipady;
		panel.add(component, gbc);
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == submitBtn) {
			String serialStr = serialField.getText().trim();
			
			if (!serialStr.isEmpty()) {
			//	serialField.setEnabled(false);
			//	submitBtn.setEnabled(false);
				
				controller.runApp(serialStr);
				
			//	serialField.setEnabled(true);
			//	submitBtn.setEnabled(true);
				
			} else {
				JOptionPane.showMessageDialog(null, "Serial field should not be empty.");
			}
			
		}
		
		
	}
	

}
