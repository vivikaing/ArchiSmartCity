package com.archimatetool.editor.ui.factory.relationships;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import com.archimatetool.editor.diagram.figures.connections.OfferConnectionFigure;
import com.archimatetool.editor.diagram.figures.connections.ServingConnectionFigure;
import com.archimatetool.editor.ui.IArchiImages;
import com.archimatetool.model.IArchimatePackage;

/**
 * Serving Relationship UI Provider
 * 
 * @author Viviana Bastidas
 */

public class OfferRelationshipUIProvider extends AbstractArchimateRelationshipUIProvider {

	 @Override
	    public EClass providerFor() {
	        return IArchimatePackage.eINSTANCE.getOfferRelationship();
	    }
	    
	    @Override
	    public EditPart createEditPart() {
	        return new ArchimateRelationshipEditPart(OfferConnectionFigure.class);
	    }

	    @Override
	    public String getDefaultName() {
	        return Messages.OfferRelationshipUIProvider_0;
	    }

	    @Override
	    public Image getImage() {
	        return IArchiImages.ImageFactory.getImage(IArchiImages.ICON_OFFER_RELATION);
	    }

	    @Override
	    public ImageDescriptor getImageDescriptor() {
	        return IArchiImages.ImageFactory.getImageDescriptor(IArchiImages.ICON_OFFER_RELATION);
	    }
	}

