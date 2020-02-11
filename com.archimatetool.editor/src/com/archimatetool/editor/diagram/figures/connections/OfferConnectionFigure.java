package com.archimatetool.editor.diagram.figures.connections;

import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;

public class OfferConnectionFigure extends AbstractArchimateConnectionFigure {
    /**
     * @return Decoration to use on Target Node
     */
    public static RotatableDecoration createFigureTargetDecoration() {
        return new PolylineDecoration();
    }
    
    public OfferConnectionFigure() {
    }

    @Override
    protected void setFigureProperties() {
        setTargetDecoration(createFigureTargetDecoration()); // arrow at target endpoint 
    }
}
