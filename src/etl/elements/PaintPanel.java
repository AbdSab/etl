package etl.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JPanel;

import etl.dataset.Data;
import etl.dataset.Dataset;
import etl.transformation.Combine;
import etl.transformation.GroupBy;
import etl.transformation.Select;
import etl.transformation.TransformationFlow;
import etl.ui.CombineInterface;
import etl.ui.GroupByInterface;
import etl.ui.SelectInterface;

public class PaintPanel extends JPanel{
	private int width = 640;
	private int height = 480;
	Collection<Element> elements = new ArrayList<>();
	Collection<Line> lines = new ArrayList<>();
	public int ids = 0;
	public PaintPanel() {
        super();
        this.setBackground(new Color(255,255,255));
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setVisible(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	if(e.getClickCount() == 2  && !e.isConsumed()) {
            		//e.consume();
            		System.out.println("Double click");
            		for(Element elm : elements) {
        				if(e.getX() >= elm.getX() && e.getX() <= elm.getX()+elm.getWidth() 
        					&& e.getY() >= elm.getY() && e.getY() <= elm.getHeight()+elm.getY()) {
							if(elm.getName() == "Select") {
		            			new SelectInterface(elm.getId());
		            		}
							if(elm.getName() == "GroupBy") {
		            			new GroupByInterface(elm.getId());
		            		}
							if(elm.getName() == "Combine") {
		            			new CombineInterface(elm.getId());
		            		}
        				}
            		}
            		for(Element elm : elements) {
            			elm.setSelected(false);
            		}
            		MouseElement.take(null);
            	}else{
            	//if(MouseElement.function == 0) {
            		if(MouseElement.Element >= 1) {
		            	if(MouseElement.Element == 1) {
	            			//Adding Select Transformation
		        			MouseElement.Element = 0;
		        			MouseElement.name = "Nothing";
		        			elements.add(new Element(++ids, "Select",false, e.getX(), e.getY(), 64, 32));
		        			TransformationFlow.transformations.add(new Select(ids));
		        		}else if(MouseElement.Element == 2) {
	            			//Adding GroupBy Transformation
		        			MouseElement.Element = 0;
		        			MouseElement.name = "Nothing";
		        			elements.add(new Element(++ids, "GroupBy",false, e.getX(), e.getY(), 64, 32));
		        			TransformationFlow.transformations.add(new GroupBy(ids));
		        		}else if(MouseElement.Element == 3) {
	            			//Adding Combine Transformation
		        			MouseElement.Element = 0;
		        			MouseElement.name = "Nothing";
		        			elements.add(new Element(++ids, "Combine",false, e.getX(), e.getY(), 64, 32));
		        			TransformationFlow.transformations.add(new Combine(ids));
		        		}else{
	            			//Adding Datasets
		        			elements.add(new Element(++ids, Data.data[MouseElement.Element-4],true, e.getX(), e.getY(), 64, 64));
		        			TransformationFlow.datasets.add(new Dataset(ids, Data.data[MouseElement.Element-4]));
	        				MouseElement.Element = 0;
		        			MouseElement.name = "Nothing";
		        		}
		        	}else {
		        		boolean selected = false;
	        			for(Element elm : elements) {
	        				if(e.getX() >= elm.getX() && e.getX() <= elm.getX()+elm.getWidth() 
	        					&& e.getY() >= elm.getY() && e.getY() <= elm.getHeight()+elm.getY()) {
	        					System.out.println(elm.getX());
	        					if(MouseElement.function == 0) {
	        						//if(TransformationFlow.getById(elm.getId()).getTransformation() != null)
	        						//	System.out.println(TransformationFlow.getById(elm.getId()).getTransformation().toString());
	        						MouseElement.function = 1;
		        					elm.setSelected(true);
		        					MouseElement.take(elm);
		        					break;
	        					}else if(MouseElement.function == 1) {
	        						//Link Transformation/Dataset to Transformation
	        						//if(!MouseElement.src.getIsData())
	        						//	TransformationFlow.getById(elm.getId()).setTransformation(TransformationFlow.getById(MouseElement.src.getId()));
	        						//else
	        						//	TransformationFlow.getById(elm.getId()).setData(TransformationFlow.getByDataId(MouseElement.src.getId()));
	        						lines.add(new Line(MouseElement.src.getX(), elm.getX(), MouseElement.src.getY(), elm.getY()));
	        						MouseElement.function = 0;
	    	        				elm.setSelected(false);
	    	        				MouseElement.src.setSelected(false);
	    	        				MouseElement.take(null);
	    	        				break;
	        	            	}
	        				}
	        					elm.setSelected(false);
	        			}
	        		}
        		repaint();
            }
            }
        });
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Selected Element : " + MouseElement.name,10, this.height-20);
		g.drawString("Function : " + MouseElement.function,10, this.height-5);
		if(!elements.isEmpty())
			for(Element e  : elements) {
				e.paintComponent(g);
			}
		if(!lines.isEmpty())
			for(Line e  : lines) {
				e.paintComponent(g);
			}
	}	
}
