/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.editor.ui;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.ResourceLocator;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.archimatetool.editor.preferences.IPreferenceConstants;
import com.archimatetool.editor.preferences.Preferences;
import com.archimatetool.editor.ui.components.CompositeMultiImageDescriptor;
import com.archimatetool.editor.utils.PlatformUtils;



/**
 * Image Factory
 * 
 * @author Phillip Beauvoir
 */
public class ImageFactory {
    
    private AbstractUIPlugin fPlugin;
    
    /**
     * @return The actual device zoom level.
     */
    public static int getDeviceZoom() {
        // This is needed if we are running from the Command Line to init Display and thus ensure DPIUtil.setDeviceZoom(int) is called
        // when com.archimatetool.editor.preferences.PreferenceInitializer calls this method
        Display.getDefault();
        
        String deviceZoom = System.getProperty("org.eclipse.swt.internal.deviceZoom"); //$NON-NLS-1$
        return deviceZoom == null ? 100 : Integer.parseInt(deviceZoom);
    }

    /**
     * @return The zoom level for creating images.
     * Windows OS with scaling > 100 needs to export images at x2 size
     * If Preferences are set to not use a scaled device zoom then return 100
     */
    public static int getImageDeviceZoom() {
        boolean scaleImages = Preferences.STORE.getBoolean(IPreferenceConstants.SCALE_IMAGE_EXPORT);
        int deviceZoom = getDeviceZoom();
        // If scaling prefs x2 is true and device zoom is 100 then return 200
        // Else return device zoom
        // Else 100
        return scaleImages ? (deviceZoom == 100) ? 200 : deviceZoom : 100;
    }
    
    /**
     * @return The zoom level for creating objects such as cursors.
     * On Windows and Linux we need to use device zoom, but not on Mac
     */
    public static int getLogicalDeviceZoom() {
        return PlatformUtils.isMac() ? 100 : getDeviceZoom();
    }
    
    /**
     * @param plugin The plug-in where we can find the images
     */
    public ImageFactory(AbstractUIPlugin plugin) {
        fPlugin = plugin;
    }
    
    /**
     * @return The plugin that owns this ImageFactory
     */
    public AbstractUIPlugin getPlugin() {
        return fPlugin;
    }
    
    /**
     * Returns the shared image represented by the given key.
     * 
     * @param imageName
     *          the logical name of the image to retrieve
     * @return the shared image represented by the given key
     */
    public Image getImage(String imageName) {
        if(imageName == null) {
            throw new IllegalArgumentException("Image name cannot be null"); //$NON-NLS-1$
        }

        ImageRegistry registry = fPlugin.getImageRegistry();

        Image image = registry.get(imageName);
        if(image == null) {
            // Image will be created in registry.get(imageName) after image descriptor is put into registry
            getImageDescriptor(imageName);
            image = registry.get(imageName);
        }
        
        return image;
    }
    
    /**
     * Return a composite image with overlay image
     * 
     * @param underlay The underlay image
     * @param overlayName Name of the overlay image
     * @param quadrant the quadrant (one of {@link IDecoration} 
     * ({@link IDecoration#TOP_LEFT}, {@link IDecoration#TOP_RIGHT},
     * {@link IDecoration#BOTTOM_LEFT}, {@link IDecoration#BOTTOM_RIGHT} 
     * or {@link IDecoration#UNDERLAY})
     * @return The image
     */
    public Image getOverlayImage(Image underlay, String overlayName, int quadrant) {
        String key = underlay.hashCode() + overlayName + quadrant;
        
        Image newImage = getImage(key);
        if(newImage == null) {
            ImageDescriptor overlayDescripter = getImageDescriptor(overlayName);
            if(overlayDescripter != null) {
                newImage = new DecorationOverlayIcon(underlay, overlayDescripter, quadrant).createImage();
                if(newImage != null) {
                    ImageRegistry registry = fPlugin.getImageRegistry();
                    registry.put(key, newImage);
                }
            }
        }
        
        return newImage != null ? newImage : underlay;
    }
    
    /**
     * Return a composite image consisting of many images
     * 
     * @param imageNames
     * @return
     */
    public Image getCompositeImage(String[] imageNames) {
        // Make a registry name, cached
        String key_name = "@"; //$NON-NLS-1$
        for(String name : imageNames) {
            key_name += name;
        }

        ImageRegistry registry = fPlugin.getImageRegistry();
        Image image = registry.get(key_name);
        
        if(image == null) {
            // Image will be created in registry.get(imageName) after image descriptor is put into registry
            getCompositeImageDescriptor(imageNames);
            image = registry.get(key_name);
        }

        return image;
    }
    
    /**
     * Return a composite image consisting of many images
     * 
     * @param imageNames
     * @return
     */
    public CompositeImageDescriptor getCompositeImageDescriptor(String[] imageNames) {
        // Make a registry name, cached
        String key_name = "@"; //$NON-NLS-1$
        for(String name : imageNames) {
            key_name += name;
        }
        
        ImageRegistry registry = fPlugin.getImageRegistry();
        
        CompositeImageDescriptor cid = (CompositeImageDescriptor)registry.getDescriptor(key_name);
        
        // Make it and cache it
        if(cid == null) {
            ImageDescriptor[] desc = new ImageDescriptor[imageNames.length];
            for(int i = 0; i < imageNames.length; i++) {
                desc[i] = getImageDescriptor(imageNames[i]);
            }
            cid = new CompositeMultiImageDescriptor(desc);
            registry.put(key_name, cid);
        }
        
        return cid;
    }

    /**
     * Returns the shared image description represented by the given key.
     * 
     * @param imageName
     *          the logical name of the image description to retrieve
     * @return the shared image description represented by the given name
     */
    public ImageDescriptor getImageDescriptor(String imageName) {
        if(imageName == null) {
            throw new IllegalArgumentException("Image name cannot be null"); //$NON-NLS-1$
        }
        
        ImageRegistry registry = fPlugin.getImageRegistry();
        ImageDescriptor id = registry.getDescriptor(imageName);
        if(id == null) {
            id = ResourceLocator.imageDescriptorFromBundle(fPlugin.getBundle().getSymbolicName(), imageName).orElse(null);
            if(id != null) {
                registry.put(imageName, id); // The image will be created next when registry.get(imageName) is called
            }
            else {
                // Can be null in the case of overlay images where the image name is a composite name
                //System.err.println("Could not get Image Descriptor for: " + imageName); //$NON-NLS-1$
            }
        }
        
        return id;
    }

    /**
     * @param name
     * @return A shared Image from the system
     */
    public static Image getSharedImage(String name) {
        return PlatformUI.getWorkbench().getSharedImages().getImage(name);
    }
    
    /**
     * @param name
     * @return A shared ImageDescriptor from the system
     */
    public static ImageDescriptor getSharedImageDescriptor(String name) {
        return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(name);
    }
    
    /**
     * Rescale an image to given width and height
     * @param source The Image source
     * @param width New width
     * @param height New height
     * @return A new scaled image
     */
    public static Image getScaledImage(Image source, int width, int height) {
        // ImageData#scaledTo does not use interpolation, so resized images look bad.
        // Here we draw to a GC which is better quality.
        
        Image image;
        
        // If there is a transparency pixel set copy the source ImageData to preserve it
        ImageData sourceImageData = source.getImageData(ImageFactory.getImageDeviceZoom());
        if(sourceImageData.transparentPixel != -1) {
            ImageData id = new ImageData(width, height, sourceImageData.depth, sourceImageData.palette);
            id.transparentPixel = sourceImageData.transparentPixel;
            image = new Image(source.getDevice(), id);
        }
        // Else use a blank image
        else {
            image = new Image(source.getDevice(), width, height);
        }
        
        GC gc = new GC(image);
        gc.setAntialias(SWT.ON);
        gc.setInterpolation(SWT.HIGH);
        
        // This is necessary for images that have the transparency pixel set
        Color transparentColor = source.getBackground();
        if(transparentColor != null) {
            gc.setBackground(transparentColor);
            gc.fillRectangle(0, 0, width, height);
        }
        
        gc.drawImage(source, 0, 0, source.getBounds().width, source.getBounds().height, 0, 0, width, height);
        gc.dispose();
        
        return image;
    }
    
    /**
     * Rescale the Image to max size (width or height)
     * @param source the Image source
     * @param maxSize the maximum width or size. Will always be a minimum of 10.
     * @return A new scaled image
     */
    public static Image getScaledImage(Image source, int maxSize) {
        if(maxSize < 10) {
            maxSize = 10;
        }
        
        Rectangle srcBounds = source.getBounds();
        int width = srcBounds.width;
        int height = srcBounds.height;

        if(height > maxSize) {
            width *= ((float)maxSize / height);
            height = maxSize;
        }
        if(width > maxSize) {
            height *= ((float)maxSize / width);
            width = maxSize;
        }
        
        return getScaledImage(source, width, height);
    }
}