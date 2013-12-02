package j3chess;

import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class ContentManager {

	private String sourcePath;
	private HashMap<String, ImageIcon> content = new HashMap<String, ImageIcon>();


	public ContentManager(final String sourcePath) {
		this.sourcePath = sourcePath;
	}


	public final ImageIcon getContent(final String filename) {
		if (content.containsKey(filename)) {
			return content.get(filename);
		} else {
			if (getContentFromFileSystem(filename, sourcePath)) {
				return content.get(filename);
			}
		}
		//TODO image not found exception
		return null;
	}




	/**
	 * @ Brief searches recursive for a file which contains the search value
	 * @param searchFileName searched filename
	 * @param directoryPath Path for searching the file
	 * @return returns true if a file is founded which contains the search FileName
	 */
	private Boolean getContentFromFileSystem (final String searchFileName, String directoryPath) {

		if (!directoryPath.endsWith("/")) {
			directoryPath = directoryPath+"/";
		}

		Boolean returnValue = false;
		File tmpFile = null;
		String tmpPath = null;

		File directory = new File(directoryPath);
		String [] fileNameArray = directory.list();


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


	private void addToHashmap(final String path, final String key) {
		ImageIcon imageIcon = new ImageIcon(path);
		content.put(key, imageIcon);
	}

}
