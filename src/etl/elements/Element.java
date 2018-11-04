package etl.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import etl.transformation.Select;
import etl.transformation.TransformationFlow;

public class Element extends JPanel{
	private int x,y;
	private String name;
	private int id;
	private int height,width;
	private boolean selected;
	private boolean isData ;
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Element(int id, String name,boolean isData, int x, int y, int width, int height) {
        super();
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isData = isData;
	}
	public boolean getIsData() {
		return this.isData;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setname(String text) {
		this.name = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(0,0,0));
		if(this.selected)
			g.setColor(new Color(255,0,0));
		g.drawRect(this.x, this.y, this.width, this.height);
		g.drawString(this.name, this.x+5, this.y+15);
	}
	
}
