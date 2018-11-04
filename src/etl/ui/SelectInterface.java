package etl.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import etl.dataset.Data;
import etl.transformation.TransformationFlow;

public class SelectInterface extends JFrame implements ActionListener{
	
	public int transID;
	
	//Confirm Buttons
	JButton okButton, closeButton;
	
	
	String buttons_names = "AND OR NOT LIKE WHERE = < > <> >= <= AVG MIN MAX ( + - * / )";
	String[] names = buttons_names.split(" ");
	List<JButton> buttons = new ArrayList<JButton>();
	JButton input;
	String operation = "";
	JPanel currentPanel;
	List<JButton> currentButtons = new ArrayList<JButton>();
	
	
	List<JCheckBox> columns = new ArrayList<JCheckBox>();
	
	public SelectInterface(int id) {
		super("Select");
		this.transID = id;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contentPane = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		contentPane.setLayout(layout);
		
		
		//Title
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Select Configuration");
		titlePanel.setMinimumSize(new Dimension(1, 64));
		titlePanel.add(title);
		
		//Columns
		JPanel columnsPanel = new JPanel();
		columnsPanel.add(new JLabel("Columns :"));
		columnsPanel.setLayout(new BoxLayout(columnsPanel, BoxLayout.Y_AXIS));
		for(int i=0; i<Data.columns.length; i++) {
			columns.add(new JCheckBox(Data.columns[i]));
			columns.get(columns.size()-1).addActionListener(this);
			columnsPanel.add(columns.get(columns.size()-1));
		}
		
		//Operations
		JPanel rightPanel = new JPanel();
		GridLayout grid = new GridLayout(9,3);
		rightPanel.setLayout(grid);

		for(int i=0; i<names.length; i++) {
			buttons.add(new JButton(names[i]));
			buttons.get(buttons.size()-1).addActionListener(this);
			rightPanel.add(buttons.get(buttons.size()-1));
		}
		input = new JButton("Input");
		input.addActionListener(this);
		rightPanel.add(input);
		/*
		for(int i=0; i<Data.columns.length; i++) {
			buttons.add(new JButton(Data.columns[i]));
			buttons.get(buttons.size()-1).addActionListener(this);
			rightPanel.add(buttons.get(buttons.size()-1));
		}
		*/
		
		
		//Current Operation
	    currentPanel = new JPanel();
	    //GridLayout grid_ = new GridLayout(5, 5, 5, 5);
	    //currentPanel.setLayout(grid_);
	    currentPanel.setPreferredSize(new Dimension(640, 480));
	    
	    //Confirmation Panel
	    JPanel confirmPanel = new JPanel();
	    okButton = new JButton("Ok");
	    closeButton = new JButton("Close");
	    okButton.addActionListener(this);
	    closeButton.addActionListener(this);
	    confirmPanel.add(okButton);
	    confirmPanel.add(closeButton);
	    
	    
	    
	    contentPane.add(titlePanel, BorderLayout.PAGE_START);
	    contentPane.add(columnsPanel, BorderLayout.LINE_START);
	    contentPane.add(currentPanel, BorderLayout.CENTER);
	    contentPane.add(rightPanel, BorderLayout.LINE_END);
	    contentPane.add(confirmPanel, BorderLayout.PAGE_END);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<buttons.size(); i++) {
			if(e.getSource() == buttons.get(i)) {
				currentButtons.add(new JButton(buttons.get(i).getText()));
				currentButtons.get(currentButtons.size()-1).addActionListener(this);
			}
		}
		
		for(int i=0; i<currentButtons.size(); i++) {
			if(e.getSource() == currentButtons.get(i)) {
				currentButtons.remove(i);
			}
		}
		
		if(e.getSource() == input) {
			String value = JOptionPane.showInputDialog("Enter value");
			currentButtons.add(new JButton(value));
			currentButtons.get(currentButtons.size()-1).addActionListener(this);
		}
		
		operation = "";
		currentPanel.removeAll();
		for(JButton b:currentButtons) {
			operation += " " + b.getText();
			currentPanel.add(b);
			this.pack();
		}
		System.out.println(operation);
		
		//TransformationFlow.getById(transID) = ;
	}
}
