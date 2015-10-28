package com.ofek.www.encryptor1;

public class FileLocations 
{
	public static String GetFileLocatoinAtFolder(String sourceFile, String newName)
	{
		int lastIndex = sourceFile.lastIndexOf('\\');
		String fileLocation = sourceFile.substring(0, lastIndex+1);
		fileLocation+=newName+=GetFileType(sourceFile);
		return fileLocation;
	}
	
	public static String GetFolderLocation(String sourceFile)
	{
		int lastIndex = sourceFile.lastIndexOf('\\');
		String folderLocation = sourceFile.substring(0, lastIndex);
		return folderLocation;
	}
	
	public static String GetFileType(String sourceFile)
	{
		int lastIndex = sourceFile.lastIndexOf('.');
		String fileType = sourceFile.substring(lastIndex, sourceFile.length());
		return fileType;		
	}
	
	public static String AddNameToFile(String sourceFile, String fileNameEnding)
	{
		String encryptedFile = sourceFile.split("\\.")[0];
		encryptedFile += fileNameEnding + GetFileType(sourceFile);
		return encryptedFile;
	}
	
	public static String GetDecryptedName(String sourceFile)
	{
		String encryptedFile = sourceFile.split("_")[0];
		encryptedFile += "_decrypted" + GetFileType(sourceFile);
		return encryptedFile;
	}

}
