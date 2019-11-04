package com.archimatetool.editor.diagram.figures.elements;

import java.io.InputStream;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;

import com.archimatetool.editor.diagram.figures.AbstractTextControlContainerFigure;
import com.archimatetool.editor.diagram.figures.FigureUtils;
import com.archimatetool.editor.preferences.IPreferenceConstants;
import com.archimatetool.editor.preferences.Preferences;
import com.archimatetool.model.IDiagramModelArchimateObject;
import com.archimatetool.model.ITextPosition;


/**
 * Domain Figure
 * 
 * @author Viviana Bastidas
 */
public class DomainFigure extends AbstractTextControlContainerFigure {
	 private static final int TOPBAR_HEIGHT = 18;
	    private static final float INSET = 1.4f;
	    
	    public DomainFigure() {
	        super(TEXT_FLOW_CONTROL);
	    }
	   
	    @Override
	    protected void drawFigure(Graphics graphics) {
	        graphics.pushState();
	        
	        Rectangle bounds = getBounds().getCopy();
	        
	        graphics.setAntialias(SWT.ON);
	        
	        graphics.setAlpha(getAlpha());
	        
	        graphics.setLineDash(new float[] { (float)(8 * graphics.getAbsoluteScale()), (float)(4 * graphics.getAbsoluteScale()) });
	        
	        graphics.setBackgroundColor(getFillColor());
	        graphics.setForegroundColor(getLineColor());
	        
	        Pattern gradient = null;
	        if(Preferences.STORE.getBoolean(IPreferenceConstants.SHOW_GRADIENT)) {
	            gradient = FigureUtils.createGradient(graphics, bounds, getFillColor(), getAlpha());
	            graphics.setBackgroundPattern(gradient);
	        }
	        
	        int type = getDiagramModelObject().getType();
	        
	        int[] mainRectangle;
	        
	        if(type == 1) {
	            mainRectangle = new int[] {
	                    bounds.x, bounds.y,
	                    bounds.x + bounds.width - 1, bounds.y,
	                    bounds.x + bounds.width - 1, bounds.y + bounds.height - 1,
	                    bounds.x, bounds.y + bounds.height - 1
	            };
	            
	            graphics.fillPolygon(mainRectangle);
	        }
	        else {
	            mainRectangle = new int[] {
	                    bounds.x, bounds.y + TOPBAR_HEIGHT,
	                    bounds.x + bounds.width - 1, bounds.y + TOPBAR_HEIGHT,
	                    bounds.x + bounds.width - 1, bounds.y + bounds.height - 1,
	                    bounds.x, bounds.y + bounds.height - 1
	            };
	            
	            int[] fillShape = new int[] {
	                    bounds.x, bounds.y,
	                    (int)(bounds.x + (bounds.width / INSET)), bounds.y,
	                    (int)(bounds.x + (bounds.width / INSET)), bounds.y + TOPBAR_HEIGHT,
	                    bounds.getRight().x, bounds.y + TOPBAR_HEIGHT,
	                    bounds.getRight().x, bounds.getBottom().y,
	                    bounds.x, bounds.getBottom().y
	            };
	            
	            graphics.fillPolygon(fillShape);
	            
	            graphics.setAlpha(getLineAlpha());
	            graphics.drawLine(bounds.x, bounds.y, bounds.x, bounds.y + TOPBAR_HEIGHT);
	            graphics.drawLine(bounds.x, bounds.y, (int)(bounds.x + (bounds.width / INSET)), bounds.y);
	            graphics.drawLine((int)(bounds.x + (bounds.width / INSET)), bounds.y, (int)(bounds.x + (bounds.width / INSET)), bounds.y + TOPBAR_HEIGHT);
	        }
	        
	        if(gradient != null) {
	            gradient.dispose();
	        }

	        // Outlines
	        graphics.setAlpha(getLineAlpha());
	        graphics.drawPolygon(mainRectangle);
	        
	        graphics.popState();
	        drawIconImage(graphics);
	    }
	    
	    @Override
	    protected Rectangle calculateTextControlBounds() {
	        Rectangle bounds = getBounds().getCopy();
	        
	        int textPosition = ((ITextPosition)getDiagramModelObject()).getTextPosition();
	        if(textPosition == ITextPosition.TEXT_POSITION_TOP) {
	            bounds.y -= 3;
	        }
	        
	        return bounds;
	    }
	    
	    /**
	     * Connection Anchor adjusts for Domain shape
	     */
	    class DomainFigureConnectionAnchor extends ChopboxAnchor {
	        public DomainFigureConnectionAnchor(IFigure owner) {
	            super(owner);
	        }
	        
	        @Override
	        public Point getLocation(Point reference) {
	            Point pt = super.getLocation(reference);
	            
	            int type = getDiagramModelObject().getType();

	            if(type == 1) {
	                return pt;
	            }
	            
	            Rectangle r = getBox().getCopy();
	            getOwner().translateToAbsolute(r);
	            
	            int shiftY = TOPBAR_HEIGHT - (pt.y - r.y) - 1;
	            
	            if(pt.x > r.x + (r.width / INSET) && shiftY > 0) {
	                pt.y += shiftY;
	            }
	            
	            return pt;
	        };
	    }
	    @Override
	    public IDiagramModelArchimateObject getDiagramModelObject() {
	        return (IDiagramModelArchimateObject)super.getDiagramModelObject();
	    }

	    @Override
	    public ConnectionAnchor getDefaultConnectionAnchor() {
	        return new DomainFigureConnectionAnchor(this);
	    }

	    /**
	     * Draw the icon image
	     */
	    protected void drawIconImage(Graphics graphics) {
	    	graphics.pushState();
	        graphics.setForegroundColor(isEnabled() ? ColorConstants.black : ColorConstants.gray); 
	    	// Start at top right 
	    	Point pt = getIconOrigin(); 
	    	
	    	final Display display = Display.getDefault();
	        //InputStream path = getClass().getResourceAsStream("img/about.png"); 
	        
	        Image imagen = new Image(display, "D:\\domain.png");	    		    	      
	        int w = imagen.getImageData().width;
	        int h = imagen.getImageData().height;
	        graphics.drawImage(imagen, 0, 0, w, h, pt.x, pt.y, 25, 25);
	        graphics.popState();
	        imagen.dispose();
	    }
	    
	    /**
	     * @return The icon start position
	     */
	    protected Point getIconOrigin() {
	        Rectangle bounds = getBounds();
	        return new Point(bounds.getRight().x - 29, bounds.y + 4);
	    }
	    
}
