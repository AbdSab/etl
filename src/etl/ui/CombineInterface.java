package etl.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import etl.dataset.Data;

public class CombineInterface extends JFrame implements ActionListener{
	
	//Transformation ID
	public int transID;
	
	//Confirm Buttons
	JButton okButton, closeButton;
	
	List<JButton> columns = new ArrayList<JButton>();
	List<JButton> currentColumns = new ArrayList<JButton>();
	List<JButton> columns2 = new ArrayList<JButton>();
	List<JButton> currentColumns2 = new ArrayList<JButton>();
	JPanel currentPanel, currentPanel2;
	
	String operations1 = "", operations2 = "";
	
	public CombineInterface(int id) {
		super("Combine");
		this.transID = id;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contentPane = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		contentPane.setLayout(layout);
		
		//Title
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Select Configuration");
		titlePanel.setPreferredSize(new Dimension(640, 64));
		titlePanel.add(title);

		//Columns1 Panel
		JPanel columnsPanel = new JPanel();
		columnsPanel.add(new JLabel("Columns :"));
		columnsPanel.setLayout(new GridLayout(20,1));
		for(int i=0; i<Data.columns.length; i++) {
			columns.add(new JButton(Data.columns[i]));
			columns.get(columns.size()-1).addActionListener(this);
			columnsPanel.add(columns.get(columns.size()-1));
		}
		
		//Columns2 Panel
		JPanel columnsPanel2 = new JPanel();
		columnsPanel2.add(new JLabel("Columns :"));
		columnsPanel2.setLayout(new GridLayout(20,1));
		for(int i=0; i<Data.columns.length; i++) {
			columns2.add(new JButton(Data.columns[i]));
			columns2.get(columns2.size()-1).addActionListener(this);
			columnsPanel2.add(columns2.get(columns2.size()-1));
		}
		
		//Inputs Panel
		JPanel inputPanel = new JPanel();
		//Input1 Panel
		currentPanel = new JPanel();
		currentPanel.setLayout(new GridLayout(20,1));
		currentPanel.add(new JLabel("Input 1"));
		
		//Input1 Panel
		currentPanel2 = new JPanel();
		currentPanel2.setLayout(new GridLayout(20,1));
		currentPanel2.add(new JLabel("Input 2"));

		inputPanel.add(currentPanel);
		inputPanel.add(currentPanel2);
		
		
		//Confirm Panel
		JPanel confirmPanel = new JPanel();
	    okButton = new JButton("Ok");
	    closeButton = new JButton("Close");
	    okButton.addActionListener(this);
	    closeButton.addActionListener(this);
	    confirmPanel.add(okButton);
	    confirmPanel.add(closeButton);
		
		
		contentPane.add(titlePanel, BorderLayout.PAGE_START);
	    contentPane.add(columnsPanel, BorderLayout.LINE_START);
	    contentPane.add(inputPanel, BorderLayout.CENTER);
	    contentPane.add(columnsPanel2, BorderLayout.LINE_END);
		this.pack();
		this.setVisible(true);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		for(JButton b : columns) {
			if(b == e.getSource()) {
				if(!operations1.contains(b.getText())) {
					currentColumns.add(new JButton(b.getText()));
					currentColumns.get(currentColumns.size()-1).addActionListener(this);
				}
			}
		}
		
		for(JButton b : columns2) {
			if(b == e.getSource()) {
				if(!operations2.contains(b.getText())) {
					currentColumns2.add(new JButton(b.getText()));
					currentColumns2.get(currentColumns2.size()-1).addActionListener(this);
				}
			}
		}
		
		for(int i=0; i<currentColumns.size(); i++) {
			if(e.getSource() == currentColumns.get(i)) {
				currentColumns.remove(i);
			}
		}
		
		for(int i=0; i<currentColumns2.size(); i++) {
			if(e.getSource() == currentColumns2.get(i)) {
				currentColumns2.remove(i);
			}
		}
		
		
		//Current Columns 2
		currentPanel.removeAll();
		currentPanel.add(new JLabel("Input 1"));
		operations1 = "";
		for(JButton b : currentColumns) {
			currentPanel.add(b);
			operations1 += " " + b.getText();
			this.pack();
		}
		System.out.println(operations1);
		
		
		//Current Columns 2
		currentPanel2.removeAll();
		currentPanel2.add(new JLabel("Input 2"));
		operations2 = "";
		for(JButton b : currentColumns2) {
			currentPanel2.add(b);
			operations2 += " " + b.getText();
			this.pack();
		}
		System.out.println(operations2);
	}

}
