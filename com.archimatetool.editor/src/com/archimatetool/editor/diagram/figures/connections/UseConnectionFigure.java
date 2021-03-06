package com.archimatetool.editor.diagram.figures.connections;
/**
 * Connection Figure class
 * 
 * @author Viviana Bastidas
 */
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;

public class UseConnectionFigure extends AbstractArchimateConnectionFigure {
	/**
     * @return Decoration to use on Target Node
     */
    public static RotatableDecoration createFigureTargetDecoration() {
        return new PolylineDecoration();
    }
    
    public UseConnectionFigure() {
    }

    @Override
    protected void setFigureProperties() {
        setTargetDecoration(createFigureTargetDecoration()); // arrow at target endpoint 
    }
}
