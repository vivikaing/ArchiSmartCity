package com.archimatetool.editor.diagram.figures.connections;

import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;

/**
 * Serving Connection Figure class
 * 
 * @author Viviana Bastidas
 */

public class PlayConnectionFigure extends AbstractArchimateConnectionFigure {

	/**
     * @return Decoration to use on Target Node
     */
    public static RotatableDecoration createFigureTargetDecoration() {
        return new PolylineDecoration();
    }
    
    public PlayConnectionFigure() {
    }

    @Override
    protected void setFigureProperties() {
        setTargetDecoration(createFigureTargetDecoration()); // arrow at target endpoint 
    }
}
