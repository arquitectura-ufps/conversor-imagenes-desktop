package views;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Alexander
 */
public class InitConfig {

    JFileChooser inputFile = new JFileChooser();
    JFileChooser inputFolder = new JFileChooser();

    public InitConfig() {
        configureInputFile();
        configureInputFolder();
    }

    private void configureInputFile() {
        inputFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        inputFile.setAcceptAllFileFilterUsed(false);
        FileFilter filters = new FileNameExtensionFilter("Image files (*.GIF,*.PNG,*.JPG, *.BMP)", "GIF", "PNG", "JPG", "BMP");
        inputFile.setFileFilter(filters);
    }

    private void configureInputFolder() {
        inputFolder.setDialogType(JFileChooser.SAVE_DIALOG);
        inputFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    public JFileChooser getInputFile() {
        return inputFile;
    }

    public JFileChooser getInputFolder() {
        return inputFolder;
    }

}
