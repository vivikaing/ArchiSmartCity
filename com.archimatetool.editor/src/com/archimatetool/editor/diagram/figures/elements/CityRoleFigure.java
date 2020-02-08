package com.archimatetool.editor.diagram.figures.elements;

import java.io.File;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import com.archimatetool.editor.diagram.figures.AbstractTextControlContainerFigure;
import com.archimatetool.editor.diagram.figures.RectangleFigureDelegate;
import com.archimatetool.editor.ui.IArchiImages;

/**
 * Dashboard Figure
 * 
 * @author Viviana Bastidas
 */

public class CityRoleFigure extends AbstractTextControlContainerFigure {

	public CityRoleFigure() {
        super(TEXT_FLOW_CONTROL);
        // Use a Rectangle Figure Delegate to Draw
        setFigureDelegate(new RectangleFigureDelegate(this, 20 - getTextControlMarginWidth()));
    }
    
    @Override
    protected void drawFigure(Graphics graphics) {
        super.drawFigure(graphics);
        //drawIcon(graphics);
        drawIconImage(graphics);
    }
    
    /**
     * Draw the icon
     */
    protected void drawIcon(Graphics graphics) {
        graphics.pushState();
        
        graphics.setLineWidth(1);
        graphics.setForegroundColor(isEnabled() ? ColorConstants.black : ColorConstants.gray);
        graphics.setBackgroundColor(isEnabled() ? ColorConstants.black : ColorConstants.gray);
        
        Point pt = getIconOrigin();
        
        Path path = new Path(null);
        
        graphics.setLineWidthFloat(1.2f);
        path.addArc(pt.x, pt.y, 13, 13, 0, 360);
        
        graphics.drawPath(path);
        path.dispose();

        graphics.fillOval(pt.x + 5, pt.y + 5, 4, 4);
        
        graphics.setLineWidth(1);
        
        path = new Path(null);
        
        path.moveTo(pt.x - 2, pt.y + 6.5f);
        path.lineTo(pt.x + 15, pt.y + 6.5f);
        
        path.moveTo(pt.x + 6.5f, pt.y - 2);
        path.lineTo(pt.x + 6.5f, pt.y + 15);
       
        path.moveTo(pt.x + 0.5f, pt.y + 0.5f);
        path.lineTo(pt.x + 12.5f, pt.y + 12.5f);
        
        path.moveTo(pt.x + 0.5f, pt.y + 12.5f);
        path.lineTo(pt.x + 12.5f, pt.y + 0.5f);
        
        graphics.drawPath(path);
        path.dispose();
        
        graphics.popState();
    }
    
	/**
	 * Draw the icon image
	 */
	protected void drawIconImage(Graphics graphics) {
		graphics.pushState();
		graphics.setForegroundColor(isEnabled() ? ColorConstants.black : ColorConstants.gray);
		// Start at top right
		Point pt = getIconOrigin();

		final File path = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		final Display display = Display.getDefault();

		ImageData data = new ImageData(path + "/" + IArchiImages.ICON_CITY_ROLE_IMAGE);
		Image image = new Image(display, data);

		int w = image.getImageData().width;
		int h = image.getImageData().height;
		graphics.drawImage(image, 0, 0, w, h, pt.x, pt.y, 22, 22);
		graphics.popState();
		image.dispose();
	}
    
    
    /**
     * @return The icon start position
     */
    protected Point getIconOrigin() {
        Rectangle bounds = getBounds();
        return new Point(bounds.x + bounds.width - 24, bounds.y + 6);
    }
    
    @Override
    protected int getIconOffset() {
        return 23;
    }

}
