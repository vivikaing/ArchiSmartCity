package com.archimatetool.editor.diagram.figures.connections;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.SWT;

/**
 * Connection Figure class
 * 
 * @author Viviana Bastidas
 */

public class BelongingConnectionFigure extends AbstractArchimateConnectionFigure {
	/**
     * @return Decoration to use on Source Node
     */
    public static RotatableDecoration createFigureTargetDecoration() {
        return new PolygonDecoration() {
            {
                setScale(5, 3);
                PointList decorationPointList = new PointList();
                decorationPointList.addPoint( 0, 0);
                decorationPointList.addPoint(-1, 1);
                decorationPointList.addPoint(-2, 0);
                decorationPointList.addPoint(-1,-1);
                setTemplate(decorationPointList);
            }
            
            @Override
            protected void fillShape(Graphics g) {
                // Draw this as white in case it is disabled
                g.setBackgroundColor(ColorConstants.white);
                super.fillShape(g);
            }
        };
    }
    
    public BelongingConnectionFigure() {
    }

    @Override
    protected void setFigureProperties() {
        setTargetDecoration(createFigureTargetDecoration());
    }
}
