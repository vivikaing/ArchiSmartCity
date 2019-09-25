/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.editor.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.graphics.Image;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.archimatetool.editor.TestSupport;
import com.archimatetool.editor.model.IArchiveManager;
import com.archimatetool.model.IArchimateFactory;
import com.archimatetool.model.IArchimateModel;
import com.archimatetool.model.IDiagramModel;
import com.archimatetool.model.IDiagramModelImage;
import com.archimatetool.testingtools.ArchimateTestModel;
import com.archimatetool.tests.TestData;
import com.archimatetool.tests.TestUtils;

import junit.framework.JUnit4TestAdapter;


@SuppressWarnings("nls")
public class ArchiveManagerTests {
    
    private ArchimateTestModel tm;
    private IArchimateModel model;
    private IDiagramModel dm;
    private ArchiveManager archiveManager;
    
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ArchiveManagerTests.class);
    }
    
    @Before
    public void runBeforeEachTest() {
        tm = new ArchimateTestModel();
        model = tm.createNewModel();
        dm = model.getDefaultDiagramModel();
        archiveManager = (ArchiveManager)model.getAdapter(IArchiveManager.class);
    }
    
    @Test
    public void testCreateArchiveManager() throws Exception {
        assertNotNull(archiveManager);
        
        // Should have one EContentListener
        assertEquals(1, model.eAdapters().size());
        
        assertTrue(archiveManager.getImagePaths().isEmpty());
        assertFalse(archiveManager.hasImages());
    }
    
    @Test
    public void testEContentAdapter() throws Exception {
        assertTrue(archiveManager.getImagePaths().isEmpty());
        
        IDiagramModelImage dmImage = IArchimateFactory.eINSTANCE.createDiagramModelImage();
        
        // Add IDiagramModelImageProvider without an image path set - should not register
        dm.getChildren().add(dmImage);
        assertTrue(archiveManager.getImagePaths().isEmpty());
        assertTrue(archiveManager.getLoadedImagePaths().isEmpty());
        
        dm.getChildren().remove(dmImage);

        // Add IDiagramModelImageProvider with an image path set - should register
        String imagePath = "/somePath/image.png";
        dmImage.setImagePath(imagePath);
        dm.getChildren().add(dmImage);
        assertEquals(1, archiveManager.getImagePaths().size());
        assertEquals(1, archiveManager.getLoadedImagePaths().size());
        assertEquals(imagePath, archiveManager.getImagePaths().get(0));
        
        // Set image path, should still only be one
        String imagePath2 = "/somePath/image2.png";
        dmImage.setImagePath(imagePath2);
        assertEquals(1, archiveManager.getImagePaths().size());
        assertEquals(2, archiveManager.getLoadedImagePaths().size()); // This should be increased
        assertEquals(imagePath2, archiveManager.getImagePaths().get(0));
    }
    
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    
    @Test
    public void testAddImageFromFile_Exception() throws IOException {
        expectedEx.expect(IOException.class);
        expectedEx.expectMessage("Cannot find file");
        archiveManager.addImageFromFile(new File("bogus.pomp"));
    }
    
    @Test
    public void testAddImageFromFile_WrongFileFormat() throws IOException {
        expectedEx.expect(IOException.class);
        expectedEx.expectMessage("Not a supported image file");
        archiveManager.addImageFromFile(TestSupport.TEST_MODEL_FILE_ZIPPED);
    }

    @Test
    public void testAddImageFromFile() throws Exception {
        IDiagramModelImage dmImage = IArchimateFactory.eINSTANCE.createDiagramModelImage();
        dm.getChildren().add(dmImage);
        
        assertTrue(archiveManager.getLoadedImagePaths().isEmpty()); // should be empty

        File imgFile = new File(TestSupport.getTestDataFolder(), "/img/img1.png");
        String pathName = archiveManager.addImageFromFile(imgFile);
        assertNotNull(pathName);
        assertTrue(archiveManager.getLoadedImagePaths().isEmpty()); // should still be empty
        
        dmImage.setImagePath(pathName);
        assertEquals(pathName, archiveManager.getLoadedImagePaths().get(0)); // This should now be increased
        assertEquals(pathName, archiveManager.getImagePaths().get(0)); // This should be set
    }
    
    @Test
    public void testCreateImage_Null() throws Exception {
        assertNull(archiveManager.createImage("something"));
    }
    
    @Test
    public void testCreateImage() throws Exception {
        IDiagramModelImage dmImage = IArchimateFactory.eINSTANCE.createDiagramModelImage();
        dm.getChildren().add(dmImage);
        
        File imgFile = new File(TestSupport.getTestDataFolder(), "/img/img1.png");
        String pathName = archiveManager.addImageFromFile(imgFile);
        
        Image image = archiveManager.createImage(pathName);
        assertNotNull(image);
    }
    
    @Test
    public void testGetImagePaths() {
        assertTrue(archiveManager.getImagePaths().isEmpty());
        
        IDiagramModelImage dmImage = IArchimateFactory.eINSTANCE.createDiagramModelImage();
        dm.getChildren().add(dmImage);
        String pathName = "/aPath.png";
        dmImage.setImagePath(pathName);
        
        assertEquals(pathName, archiveManager.getImagePaths().get(0));
    }
    
    @Test
    public void testLoadImages() throws Exception {
        model.setFile(TestSupport.TEST_MODEL_FILE_ZIPPED);
        
        assertTrue(archiveManager.getLoadedImagePaths().isEmpty());
        
        archiveManager.loadImages();
        assertFalse(archiveManager.getLoadedImagePaths().isEmpty());
    }
    
    @Test
    public void testLoadImagesFromModelFile() throws Exception {
        boolean result = archiveManager.loadImagesFromModelFile(null);
        assertFalse(result);
        
        result = archiveManager.loadImagesFromModelFile(TestData.TEST_MODEL_FILE_ARCHISURANCE);
        assertFalse(result);
        
        assertTrue(archiveManager.getLoadedImagePaths().isEmpty());
        
        result = archiveManager.loadImagesFromModelFile(TestSupport.TEST_MODEL_FILE_ZIPPED);
        assertTrue(result);
        assertFalse(archiveManager.getLoadedImagePaths().isEmpty());
    }
    
    @Test
    public void testHasImages() {
        assertFalse(archiveManager.hasImages());
        
        IDiagramModelImage dmImage = IArchimateFactory.eINSTANCE.createDiagramModelImage();
        dmImage.setImagePath("somePath");
        dm.getChildren().add(dmImage);
        
        assertTrue(archiveManager.hasImages());
    }    
    
    @Test
    public void testSaveModel() throws IOException {
        IDiagramModelImage dmImage = IArchimateFactory.eINSTANCE.createDiagramModelImage();
        dm.getChildren().add(dmImage);
        
        File file = TestUtils.createTempFile(".archimate");
        model.setFile(file);
        
        archiveManager.saveModel();
        
        // Not an archive file
        assertTrue(file.exists());
        assertFalse(IArchiveManager.FACTORY.isArchiveFile(file));
        
        // Is an archive file
        archiveManager.loadImagesFromModelFile(TestSupport.TEST_MODEL_FILE_ZIPPED);
        dmImage.setImagePath(archiveManager.getLoadedImagePaths().get(0));
        
        archiveManager.saveModel();
        assertTrue(IArchiveManager.FACTORY.isArchiveFile(file));
        
        file.delete();
    }
    
    @Test
    public void testSaveModel_ResourceSame() throws IOException {
        File file = TestUtils.createTempFile(".archimate");
        model.setFile(file);
        
        Resource resource = model.eResource();
        assertNull(resource);
        
        archiveManager.saveModel();
        resource = model.eResource();
        assertNotNull(resource);
        
        // Change file name
        file = TestUtils.createTempFile(".archimate");
        model.setFile(file);
        archiveManager.saveModel();

        assertSame(resource, model.eResource());
    }
    
    @Test
    public void testDispose() throws IOException {
        IDiagramModelImage dmImage = IArchimateFactory.eINSTANCE.createDiagramModelImage();
        dm.getChildren().add(dmImage);
        archiveManager.loadImagesFromModelFile(TestSupport.TEST_MODEL_FILE_ZIPPED);
        dmImage.setImagePath(archiveManager.getLoadedImagePaths().get(0));
        
        assertEquals(1, model.eAdapters().size());
        assertFalse(archiveManager.getLoadedImagePaths().isEmpty());
        
        archiveManager.dispose();
        
        assertTrue(archiveManager.getLoadedImagePaths().isEmpty());
    }
}