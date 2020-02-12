package com.archimatetool.editor.ui.factory.relationships;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import com.archimatetool.editor.diagram.figures.connections.QuantifyConnectionFigure;
import com.archimatetool.editor.diagram.figures.connections.ServingConnectionFigure;
import com.archimatetool.editor.ui.IArchiImages;
import com.archimatetool.model.IArchimatePackage;

/**
 * Relationship UI Provider
 * 
 * @author Viviana Bastidas
 */

public class QuantifyRelationshipUIProvider extends AbstractArchimateRelationshipUIProvider {

	 @Override
	    public EClass providerFor() {
	        return IArchimatePackage.eINSTANCE.getQuantifyRelationship();
	    }
	    
	    @Override
	    public EditPart createEditPart() {
	        return new ArchimateRelationshipEditPart(QuantifyConnectionFigure.class);
	    }

	    @Override
	    public String getDefaultName() {
	        return Messages.QuantifyRelationshipUIProvider_0;
	    }

	    @Override
	    public Image getImage() {
	        return IArchiImages.ImageFactory.getImage(IArchiImages.ICON_QUANTIFY_RELATION);
	    }

	    @Override
	    public ImageDescriptor getImageDescriptor() {
	        return IArchiImages.ImageFactory.getImageDescriptor(IArchiImages.ICON_QUANTIFY_RELATION);
	    }
	}