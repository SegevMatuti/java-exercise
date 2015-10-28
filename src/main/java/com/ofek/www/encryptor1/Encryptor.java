package com.ofek.www.encryptor1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public abstract class Encryptor implements EncryptionAlgorithm
{
	private String encryptedFileLocation;
	private String keyLocation;
	private static Random rnd = new Random();
	
	private static int getRandomKey()
	{
		int randomInt = rnd.nextInt(20)+2;
		return randomInt;
	}
	
	private void GenerateKeyFile(String sourceFile, String keyFileName, int key) throws IOException
	{
		keyLocation = FileLocations.GetFolderLocation(sourceFile);
		keyLocation += "\\" + keyFileName + ".txt";
		FileWriter keyWriter = new FileWriter(keyLocation);
		keyWriter.write(String.valueOf(key).toCharArray());
		keyWriter.close();		
	}

    public int EncryptFile(String sourceFile, String keyFileName)
    {
        int key = getRandomKey();
        EncryptFile(sourceFile, keyFileName, key);
        return key;
    }

    public int EncryptFile(String sourceFile, String keyFileName, int key)
    {
        try
        {
            GenerateKeyFile(sourceFile, keyFileName, key);
            encryptedFileLocation = FileLocations.AddNameToFile(sourceFile, "_encrypted");
            FileWriter encryptedWriter = new FileWriter(encryptedFileLocation);
            FileReader reader = new FileReader(sourceFile);

            int character;
            while ((character = reader.read()) != -1)
            {
                encryptedWriter.write(EncryptionStrategy(character, key));
            }
            reader.close();
            encryptedWriter.close();
            System.out.println("The file has been Encrypted!");
            System.out.println("Key Location: " + keyLocation);
            System.out.println("Encrypted File Location: "+encryptedFileLocation);
            System.out.println();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not Found!");
            System.out.println();
        }
        catch (StringIndexOutOfBoundsException e)
        {
            System.err.println("invalid location");
            System.out.println();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return key;
    }

	//ex1
	public void DecryptFile(String sourceFile, String keyFile)
	{
		Scanner scanner = null;
		try 
		{
			scanner = new Scanner(new File(keyFile));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found!");
		}
		int key = scanner.nextInt();
        scanner.close();
        Decrypt(sourceFile,key);
	}

	//ex2
	private void Decrypt(String sourceFile, int key)
	{
		try
		{
			String DecryptedLoc = FileLocations.AddNameToFile(sourceFile, "_decrypted");
			FileReader reader = new FileReader(sourceFile);
			FileWriter writer = new FileWriter(DecryptedLoc);
			int character;
			while ((character = reader.read()) != -1)
			{
				writer.write(DecryptionStrategy(character,key));
			}
			reader.close();
			writer.close();
			System.out.println("The file has been Decrypted!");
			System.out.println("Decrypted File Location: "+DecryptedLoc);
			System.out.println();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//override
	abstract int DecryptionStrategy(int character, int key);
	
	//override
	abstract int EncryptionStrategy(int character, int key);
}
