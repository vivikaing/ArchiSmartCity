package com.archimatetool.editor.ui.factory.relationships;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import com.archimatetool.editor.diagram.figures.connections.InterfaceConnectionFigure;
import com.archimatetool.editor.ui.IArchiImages;
import com.archimatetool.model.IArchimatePackage;

public class InterfaceRelationshipUIProvider extends AbstractArchimateRelationshipUIProvider {

	@Override
    public EClass providerFor() {
        return IArchimatePackage.eINSTANCE.getInterfaceRelationship();
    }
    
    @Override
    public EditPart createEditPart() {
        return new ArchimateRelationshipEditPart(InterfaceConnectionFigure.class);
    }

    @Override
    public String getDefaultName() {
        return Messages.InterfaceRelationshipUIProvider_0;
    }

    @Override
    public Image getImage() {
        return IArchiImages.ImageFactory.getImage(IArchiImages.ICON_INTERFACE_RELATION);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return IArchiImages.ImageFactory.getImageDescriptor(IArchiImages.ICON_INTERFACE_RELATION);
    }
}

