/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import commons.Converter;
import commons.ConverterException;
import commons.ImageFormat;
import core.converter.IConverter;
import java.io.File;
import java.util.Objects;
import org.apache.commons.io.FilenameUtils;
import static java.util.Objects.isNull;


/**
 *
 * @author USUARIO
 */
public class ImageConverter implements Converter, IConverter {
private ImageFormat formatInit;
    private ImageFormat formatEnd;
    private File image;
    private String folder;
    private String nameFile;

    @Override
    public void defineFormatImages(ImageFormat formatInit, ImageFormat formatEnd) {
        this.formatInit = formatInit;
        this.formatEnd = formatEnd;
    }

    @Override
    public void source(File image) {
        this.image = image;
    }

    @Override
    public File startProcess() throws ConverterException {
        String destination = myFolder() + "/" + myNameFile();
        return start(image, formatEnd, destination);
    }

    private String myNameFile() {
        return (isNull(nameFile) || nameFile.isEmpty()) ?
                FilenameUtils.removeExtension(image.getName()) :
                nameFile;
    }

    private String myFolder() {
        return Objects.isNull(folder) ? "/" : folder;
    }

    public ImageFormat getFormatInit() {
        return formatInit;
    }

    public void setFormatInit(ImageFormat formatInit) {
        this.formatInit = formatInit;
    }

    public void setFormatEnd(ImageFormat formatEnd) {
        this.formatEnd = formatEnd;
    }

    @Override
    public void setFolder(String folder) {
        this.folder = folder;
    }

    @Override
    public void setName(String nameFile) {
        this.nameFile = nameFile;
    }
}
