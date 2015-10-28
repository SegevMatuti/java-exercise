package com.ofek.www.encryptor1;

import java.io.IOException;

public class DoubleEncryption implements EncryptionAlgorithm
{
	EncryptionAlgorithm m_AlgorithmToUseTwice;
	private String secondKeyLocation;
	
	public DoubleEncryption(EncryptionAlgorithm Algorithm)
	{
		m_AlgorithmToUseTwice = Algorithm;
	}
	
	public int EncryptFile(String sourceFile, String keyFileName) 
	{
		m_AlgorithmToUseTwice.EncryptFile(sourceFile, keyFileName);
		int key = m_AlgorithmToUseTwice.EncryptFile(FileLocations.AddNameToFile(sourceFile, "_encrypted"), keyFileName+"1");		
		secondKeyLocation = FileLocations.GetFolderLocation(sourceFile);
		secondKeyLocation += "\\key1.txt";		
		return key;
	}

    public int EncryptFile(String sourceFile, String keyFileName, int key)
    {
        m_AlgorithmToUseTwice.EncryptFile(sourceFile, keyFileName, key);
        m_AlgorithmToUseTwice.EncryptFile(FileLocations.AddNameToFile(sourceFile, "_encrypted"), keyFileName+"1",key+1);
        secondKeyLocation = FileLocations.GetFolderLocation(sourceFile);
        secondKeyLocation += "\\key1.txt";
        return key;
    }

	public void DecryptFile(String sourceFile, String keyLoc)
	{
        m_AlgorithmToUseTwice.DecryptFile(sourceFile, secondKeyLocation);
        m_AlgorithmToUseTwice.DecryptFile(FileLocations.AddNameToFile(sourceFile, "_decrypted"), keyLoc);
	}

    public int getKeyStrength() {
        TestUtills utills = new TestUtills();
        int strength = 0;
        try
        {
            strength = utills.checkKeyStrengthForEncryptionAlgo(new DoubleEncryption(m_AlgorithmToUseTwice));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return strength;
    }
}
