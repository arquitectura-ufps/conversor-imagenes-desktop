/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.converter;

import commons.ConverterException;
import commons.ImageFormat;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author USUARIO
 */
public interface IConverter {
         default File start(File image, ImageFormat formatEnd, String destination) throws ConverterException {
        switch (formatEnd) {
            case PNG: return run(image, "png", destination);
            case GIF: return run(image, "gif", destination);
            case JPG: return run(image, "jpg", destination);
            case BMP: return run(image, "bmp", destination);
            default: throw new ConverterException("Error al convertir imagen: formato no valido");
        }
    }

    default File run(File image, String formatName, String destination) throws ConverterException {
        File imageEnd = new File(destination + "." + formatName);
        try {
            BufferedImage bufferedImage = ImageIO.read(image);
            ImageIO.write(bufferedImage, formatName, imageEnd);
            return imageEnd;
        } catch (IOException e) {
            throw new ConverterException("Error al convertir imagen: " + e.getMessage());
        }
    }
}
