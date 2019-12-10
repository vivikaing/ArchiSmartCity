package com.archimatetool.editor.ui.factory.elements;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;
import com.archimatetool.editor.diagram.figures.elements.DecisionFigure;
import com.archimatetool.editor.ui.ColorFactory;
import com.archimatetool.editor.ui.IArchiImages;
import com.archimatetool.model.IArchimatePackage;

/**
 * Business Process UI Provider
 * 
 * @author Viviana Bastidas
 */

public class DecisionUIProvider extends AbstractArchimateElementUIProvider {

	@Override
    public EClass providerFor() {
        return IArchimatePackage.eINSTANCE.getDecision();
    }
    
    @Override
    public EditPart createEditPart() {
        return new ArchimateElementEditPart(DecisionFigure.class);
    }

    @Override
    public String getDefaultName() {
        return Messages.DecisionUIProvider_0;
    }

    @Override
    public Image getImage() {
        return IArchiImages.ImageFactory.getImage(IArchiImages.ICON_DECISION);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return IArchiImages.ImageFactory.getImageDescriptor(IArchiImages.ICON_DECISION);
    }
    
    @Override
    public Color getDefaultColor() {
        //return ColorFactory.get(255, 255, 181);
    	return ColorFactory.get(0, 172, 230);
    }

    @Override
    public boolean hasAlternateFigure() {
        return true;
    }

}
