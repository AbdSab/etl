package etl.elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Line extends JPanel{
	private int x1,x2,y1,y2;
	Line(int x1, int x2, int y1, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(0,0,0));
		g.drawLine(this.x1, this.y1, this.x2, this.y2);
		g.drawOval( this.x2-5, this.y2-5, 10, 10);
	}
}
