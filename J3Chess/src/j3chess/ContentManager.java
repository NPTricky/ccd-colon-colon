package j3chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.ImageIcon;


/**
 * Class to find Content and cache it into an Hashmap.
 *
 */
public class ContentManager {

    /**
     * Source Path where the Content Manager looks for Content.
     */
    private final String mSourcePath;
    /**
     *  HaspMap of the Founded Content.
     */
    private final HashMap<String, ImageIcon> mContent = new HashMap<String, ImageIcon>();


    /**
     * @Brief creates a new ContentMange needs a sourcePath
     * <* @param sourcePath the sourcePath looking for files
     */
    public ContentManager(final String sourcePath) {
        this.mSourcePath = sourcePath;
    }


    /**
     * @Brief searches for a imageFile - looks first in the local Hashmap, then in the filesystem
     * @param filename which is searched
     * @return the searched ImageIcon - returns null if not found
     * @throws FileNotFoundException if the searched file can not be found
     */
    public final ImageIcon getContent(final String filename) throws FileNotFoundException {
        if (mContent.containsKey(filename)) {
            return mContent.get(filename);
        } else {
            if (getContentFromFileSystem(filename, mSourcePath)) {
                return mContent.get(filename);
            } else {
                throw new FileNotFoundException();
            }
        }
    }

    /**
     * @ Brief searches recursive for a file which contains the search value
     * @param searchFileName searched filename
     * @param directory Path for searching the file
     * @return true if a file is found which contains the search FileName
     * @throws FileNotFoundException directory not Found
     */
    private Boolean getContentFromFileSystem(final String searchFileName, final String directory) throws FileNotFoundException {
        // Create copy of input parameter
        String directoryPath = directory;
        // Append slash if its missing
        if (!directoryPath.endsWith("/")) {
            directoryPath = directoryPath + "/";
        }
        Boolean returnValue = false;
        File tmpFile = null;
        String tmpPath = null;

        final File directoryFile = new File(directoryPath);
        final String [] fileNameArray = directoryFile.list();


        for (int i = 0; i < fileNameArray.length; i++) {
            tmpPath = directoryPath + fileNameArray[i];
            if (fileNameArray[i].contains(searchFileName)) {
                //file found
                addToHashmap(tmpPath, searchFileName);
                return true;
            }
            tmpFile = new File(tmpPath);
            if (tmpFile.isDirectory()) {
                returnValue = getContentFromFileSystem(searchFileName, tmpPath);
            }
        }
        return returnValue;
    }

    /**
     * @Brief adds an ImageIcon to the hashmap
     * @param path path to the Content
     * @param key key for this Content
     */
    private void addToHashmap(final String path, final String key) {
        final ImageIcon imageIcon = new ImageIcon(path);
        mContent.put(key, imageIcon);
    }
}
