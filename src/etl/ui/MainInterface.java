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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import etl.dataset.Data;
import etl.elements.MouseElement;
import etl.elements.PaintPanel;

public class MainInterface extends JFrame implements ActionListener{
	JButton select, groupBy, combine;
	JButton[] dataButtons;
	PaintPanel paintPanel;
	
	public MainInterface() {
		super("ETL - Flowchart");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		contentPane.setLayout(layout);
		
		JPanel mainPanel = new JPanel();
		
		
		//Buttons Panel
		JPanel buttonsPanel = new JPanel();
	    JButton runButton = new JButton("Run");
	    JButton prevButton = new JButton("Preview");
	    buttonsPanel.add(runButton, BorderLayout.LINE_START);
	    buttonsPanel.add(prevButton, BorderLayout.LINE_END);
	    
	    //Palette Panel
	    JPanel palettePanel = new JPanel();
	    select = new JButton("Select");
	    groupBy = new JButton("GroupBy");
	    combine = new JButton("Combine");
	    select.addActionListener(this);
	    groupBy.addActionListener(this);
	    combine.addActionListener(this);
	    palettePanel.add(select);
	    palettePanel.add(groupBy);
	    palettePanel.add(combine);
	    dataButtons = new JButton[Data.data.length];
	    for(int i=0; i< Data.data.length; i++) {
	    	dataButtons[i] = new JButton(Data.data[i]);
	    	dataButtons[i].addActionListener(this);
	    	palettePanel.add(dataButtons[i]);
	    }
	    
	    //Paint Panel
	    paintPanel = new PaintPanel();
	    
		
		
	    contentPane.add(buttonsPanel, BorderLayout.PAGE_START);
	    contentPane.add(paintPanel, BorderLayout.LINE_START);
		contentPane.add(palettePanel, BorderLayout.LINE_END);
		this.pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == select) {
        	MouseElement.newElmt(1, "Select");
        }
		else if(e.getSource() == groupBy) {
        	MouseElement.newElmt(2, "GroupBy");
        }
		else if(e.getSource() == combine) {
        	MouseElement.newElmt(3, "Combine");
        }
		else {
			for(int i=0; i<Data.data.length; i++) {
				if(e.getSource() == dataButtons[i]) {
					MouseElement.newElmt(4+i, Data.data[i]);
					break;
				}
			}
		}
        
    	paintPanel.repaint();
}
}
