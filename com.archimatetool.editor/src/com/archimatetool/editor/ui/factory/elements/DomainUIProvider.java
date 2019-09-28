package com.archimatetool.editor.ui.factory.elements;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;
import com.archimatetool.editor.diagram.figures.elements.DomainFigure;
import com.archimatetool.editor.ui.ColorFactory;
import com.archimatetool.editor.ui.IArchiImages;
import com.archimatetool.model.IArchimatePackage;
import com.archimatetool.model.ITextAlignment;


/**
 * Domain UI Provider
 * 
 * @author Viviana Bastidas
 */

public class DomainUIProvider extends AbstractArchimateElementUIProvider {

	@Override
	public EClass providerFor() {
		return IArchimatePackage.eINSTANCE.getDomain();
	}
	
	@Override
    public EditPart createEditPart() {
        return new ArchimateElementEditPart(DomainFigure.class);
    }

    @Override
    public String getDefaultName() {
        return Messages.DomainUIProvider_0;
    }

    @Override
    public Image getImage() {
        return IArchiImages.ImageFactory.getImage(IArchiImages.ICON_DOMAIN);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return IArchiImages.ImageFactory.getImageDescriptor(IArchiImages.ICON_DOMAIN);
    }
    
    @Override
    public Color getDefaultColor() {
        return ColorFactory.get(255, 255, 255);
    }
    
    @Override
    public Dimension getDefaultSize() {
        return new Dimension(400, 140);
    }
    
    @Override
    public Dimension getUserDefaultSize() {
        return getDefaultSize();
    }
    
    @Override
    public boolean hasAlternateFigure() {
        return true;
    }
    
    @Override
    public int getDefaultTextAlignment() {
        return ITextAlignment.TEXT_ALIGNMENT_LEFT;
    }

}
