package j3chess;

import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class ContantManager {
	
	private String sourcePath;
	private HashMap <String,ImageIcon> contant = new HashMap<String, ImageIcon>(); 
	
	
	public ContantManager(String sourcePath){
		this.sourcePath=sourcePath;
	}


	public ImageIcon getContant(String filename){	
		if(contant.containsKey(filename)){
			return contant.get(filename);
		}else{
			if(getContantFromFileSystem(filename, sourcePath)){
				return contant.get(filename);
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
	private Boolean getContantFromFileSystem (String searchFileName, String directoryPath){

		if(!directoryPath.endsWith("/")){
			directoryPath=directoryPath+"/";
		}
		
		Boolean returnValue=false;
		File tmpFile =null;
		String tmpPath=null;
		
		File directory = new File(directoryPath);
		String [] fileNameArray =directory.list();
		
		
		for(int i=0;i<fileNameArray.length;i++){
			tmpPath=directoryPath+fileNameArray[i];
			if(fileNameArray[i].contains(searchFileName)){
				//file found
				addToHashmap(tmpPath, searchFileName);
				return true;
			}
			
			tmpFile =new File(tmpPath);	
			if (tmpFile.isDirectory()){
				returnValue= getContantFromFileSystem(searchFileName,(tmpPath));
			}
		
		}
		return returnValue;		
	}
	
	
	private void addToHashmap (String path, String key){
		ImageIcon imageIcon=new ImageIcon(path);
		contant.put(key, imageIcon);	
	}

}
