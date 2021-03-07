/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import java.io.File;

/**
 *
 * @author USUARIO
 */
public interface Converter {
    public void defineFormatImages(ImageFormat formatInit, ImageFormat formatEnd);
    public void source(File image);
    public void setFolder(String folder);
    public void setName(String nameFile);
    public File startProcess() throws ConverterException;
}
