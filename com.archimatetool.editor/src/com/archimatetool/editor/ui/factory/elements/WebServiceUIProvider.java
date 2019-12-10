package com.archimatetool.editor.ui.factory.elements;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;
import com.archimatetool.editor.diagram.figures.elements.WebServiceFigure;
import com.archimatetool.editor.ui.ColorFactory;
import com.archimatetool.editor.ui.IArchiImages;
import com.archimatetool.model.IArchimatePackage;

/**
 * WebService UI Provider
 * 
 * @author Viviana Bastidas
 */

public class WebServiceUIProvider extends AbstractInterfaceUIProvider {

	@Override
	public EClass providerFor() {
		return IArchimatePackage.eINSTANCE.getWebService();
	}
	
	@Override
    public EditPart createEditPart() {
        return new ArchimateElementEditPart(WebServiceFigure.class);
    }

    @Override
    public String getDefaultName() {
        return Messages.WebServiceUIProvider_0;
    }

    @Override
    public Image getImage() {
        return IArchiImages.ImageFactory.getImage(IArchiImages.ICON_WEBSERVICE);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return IArchiImages.ImageFactory.getImageDescriptor(IArchiImages.ICON_WEBSERVICE);
    }

    @Override
    public Color getDefaultColor() {
        //return ColorFactory.get(181, 255, 255);
        return ColorFactory.get(0, 172, 230);
    }

}
