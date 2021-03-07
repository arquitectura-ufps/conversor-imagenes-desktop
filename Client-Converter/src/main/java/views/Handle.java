/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import commons.Converter;
import commons.ConverterException;
import commons.ImageFormat;
import core.ImageConverter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexander
 */
public class Handle {

    private static final Handle instance = new Handle();
    private App app = new App();
    private ImageFormat extensions[] = ImageFormat.values();
    private File image; 
    InitConfig initConfig = new InitConfig();
    JFileChooser inputFile = initConfig.getInputFile();
    JFileChooser inputFolder = initConfig.getInputFolder();

    private Handle() {
        
    }

    public static Handle getInstance() {
        return instance;
    }

    private boolean validateImage() throws IOException {
        String ext = getExtension();
        ArrayList<String> valids = new ArrayList<>();
        boolean isValid = Arrays.asList(extensions).contains(ImageFormat.valueOf(ext));
        return isValid;
    }

    public String getExtension() throws IOException {
        String ext = Files.probeContentType(image.toPath()).split("/")[1];
        ext = ext.equalsIgnoreCase("jpeg") ? "JPG" : ext;
        return ext.toUpperCase();
    }

    public String selectDestinationPath() {
        int selection = inputFolder.showSaveDialog(app);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File folder = inputFolder.getSelectedFile();
            return folder.getAbsolutePath();
        }
        return "";
    }

    public void convertPhoto(String destination, String formatEnd) throws IOException {
        try {
            Converter converter = new ImageConverter();
            converter.defineFormatImages(ImageFormat.valueOf(this.getExtension()), ImageFormat.valueOf(formatEnd));
            converter.source(image);
            converter.setFolder(destination);
            File imageOut = converter.startProcess();
            JOptionPane.showMessageDialog(null, "Imagen convertida");
            cleanApp();
        } catch (ConverterException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
   

    public String selectImage() {
        int selection = inputFile.showOpenDialog(app);
        if (selection == JFileChooser.APPROVE_OPTION) {
            image = inputFile.getSelectedFile();
            try {
                boolean load = validateImage();
                if (load) {
                    return image.getAbsolutePath();
                } else {
                    String ext = Files.probeContentType(image.toPath()).split("/")[1];
                    JOptionPane.showMessageDialog(null, "La extension de la imagen es " + ext + " y no es permitida.");
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "";
    }
    
    private void cleanApp() {
        image = null;
        inputFile.remove(app);
        inputFolder.remove(app);
    }

    public File getImage() {
        return image;
    }

}
