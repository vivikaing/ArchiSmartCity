package com.archimatetool.editor.ui.factory.elements;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;
import com.archimatetool.editor.diagram.figures.elements.CityServiceFigure;
import com.archimatetool.editor.ui.ColorFactory;
import com.archimatetool.editor.ui.IArchiImages;
import com.archimatetool.model.IArchimatePackage;

/**
 * Business Process UI Provider
 * 
 * @author Viviana Bastidas
 */

public class CityServiceUIProvider extends AbstractArchimateElementUIProvider {

	@Override
    public EClass providerFor() {
        return IArchimatePackage.eINSTANCE.getCityService();
    }
    
    @Override
    public EditPart createEditPart() {
        return new ArchimateElementEditPart(CityServiceFigure.class);
    }

    @Override
    public String getDefaultName() {
        return Messages.CityServiceUIProvider_0;
    }

    @Override
    public Image getImage() {
        return IArchiImages.ImageFactory.getImage(IArchiImages.ICON_CITY_SERVICE);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return IArchiImages.ImageFactory.getImageDescriptor(IArchiImages.ICON_CITY_SERVICE);
    }
    
    @Override
    public Color getDefaultColor() {
    	return ColorFactory.get(0, 172, 230);
    }

    @Override
    public boolean hasAlternateFigure() {
        return true;
    }

}
