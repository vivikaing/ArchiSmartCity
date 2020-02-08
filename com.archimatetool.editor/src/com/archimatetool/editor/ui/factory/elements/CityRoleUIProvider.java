package com.archimatetool.editor.ui.factory.elements;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;
import com.archimatetool.editor.diagram.figures.elements.CityRoleFigure;
import com.archimatetool.editor.ui.ColorFactory;
import com.archimatetool.editor.ui.IArchiImages;
import com.archimatetool.model.IArchimatePackage;

/**
 * Business Process UI Provider
 * 
 * @author Viviana Bastidas
 */

public class CityRoleUIProvider extends AbstractArchimateElementUIProvider {

	@Override
    public EClass providerFor() {
        return IArchimatePackage.eINSTANCE.getCityRole();
    }
    
    @Override
    public EditPart createEditPart() {
        return new ArchimateElementEditPart(CityRoleFigure.class);
    }

    @Override
    public String getDefaultName() {
        return Messages.CityRoleUIProvider_0;
    }

    @Override
    public Image getImage() {
        return IArchiImages.ImageFactory.getImage(IArchiImages.ICON_CITY_ROLE);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return IArchiImages.ImageFactory.getImageDescriptor(IArchiImages.ICON_CITY_ROLE);
    }
    
    @Override
    public Color getDefaultColor() {
    	return ColorFactory.get(0, 172, 230);
    }

}
