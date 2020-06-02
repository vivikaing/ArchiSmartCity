package com.archimatetool.editor.ui.factory.relationships;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import com.archimatetool.editor.diagram.figures.connections.ImpactConnectionFigure;
import com.archimatetool.editor.ui.IArchiImages;
import com.archimatetool.model.IArchimatePackage;

public class ImpactRelationshipUIProvider extends AbstractArchimateRelationshipUIProvider {

	@Override
	public EClass providerFor() {
		return IArchimatePackage.eINSTANCE.getImpactRelationship();
	}

	@Override
	public EditPart createEditPart() {
		return new ArchimateRelationshipEditPart(ImpactConnectionFigure.class);
	}

	@Override
	public String getDefaultName() {
		return Messages.ImpactRelationshipUIProvider_0;
	}

	@Override
	public Image getImage() {
		return IArchiImages.ImageFactory.getImage(IArchiImages.ICON_IMPACT_RELATION);
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return IArchiImages.ImageFactory.getImageDescriptor(IArchiImages.ICON_IMPACT_RELATION);
	}

}
