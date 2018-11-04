package etl.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import etl.dataset.Data;

public class GroupByInterface extends JFrame implements ActionListener{
	
	public int transID;
	
	//Confirm Buttons
	JButton okButton, closeButton;
	
	List<JButton> columns = new ArrayList<JButton>();
	List<JButton> currentColumns = new ArrayList<JButton>();
	JPanel currentPanel;
	
	String operations = "";
	
	public GroupByInterface(int id) {
		super("GroupBy");
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

		//Columns Panel
		JPanel columnsPanel = new JPanel();
		columnsPanel.add(new JLabel("Columns :"));
		columnsPanel.setLayout(new GridLayout(20,1));
		for(int i=0; i<Data.columns.length; i++) {
			columns.add(new JButton(Data.columns[i]));
			columns.get(columns.size()-1).addActionListener(this);
			columnsPanel.add(columns.get(columns.size()-1));
		}
		
		//GroupBy Panel
		currentPanel = new JPanel();
		currentPanel.setLayout(new GridLayout(20,1));
		currentPanel.add(new JLabel("Group By"));
		
		//Aggregate Panel
		JPanel aggPanel = new JPanel();
		
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
	    contentPane.add(currentPanel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		for(JButton b : columns) {
			if(b == e.getSource()) {
				currentColumns.add(new JButton(b.getText()));
			}
		}
		
		currentPanel.removeAll();
		currentPanel.add(new JLabel("Group By"));
		operations = "";
		for(JButton b : currentColumns) {
			currentPanel.add(b);
			this.pack();
			operations += " " + b.getText();
		}
		System.out.println(operations);
	}
}
