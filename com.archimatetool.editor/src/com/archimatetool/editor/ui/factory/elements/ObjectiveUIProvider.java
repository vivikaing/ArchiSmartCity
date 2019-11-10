package com.archimatetool.editor.ui.factory.elements;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;
import com.archimatetool.editor.diagram.figures.elements.ObjectiveFigure;
import com.archimatetool.editor.ui.ColorFactory;
import com.archimatetool.editor.ui.IArchiImages;
import com.archimatetool.model.IArchimatePackage;

public class ObjectiveUIProvider extends AbstractArchimateElementUIProvider {

	@Override
	public EClass providerFor() {
		return IArchimatePackage.eINSTANCE.getObjective();
    }
    
    @Override
    public EditPart createEditPart() {
        return new ArchimateElementEditPart(ObjectiveFigure.class);
    }

    @Override
    public String getDefaultName() {
        return Messages.ObjectiveUIProvider_0;
    }

    @Override
    public Image getImage() {
        return IArchiImages.ImageFactory.getImage(IArchiImages.ICON_OBJECTIVE);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return IArchiImages.ImageFactory.getImageDescriptor(IArchiImages.ICON_OBJECTIVE);
    }

    @Override
    public Color getDefaultColor() {
        return ColorFactory.get(204, 204, 255);
    }

}
