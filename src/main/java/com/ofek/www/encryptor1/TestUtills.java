package com.ofek.www.encryptor1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Segev on 28/10/2015.
 */
public class TestUtills
{
    FileEncryptor fileEncryptorTester;
    String sourceFile = "C:\\Encryptor1AppTest\\TextFile.txt";
    int TestKey;

    public int checkKeyStrengthForEncryptionAlgo(EncryptionAlgorithm algo) throws IOException
    {
        File testDir = new File("C:\\Encryptor1AppTest");
        testDir.mkdir();
        try
        {
            CreateFile();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        int strength = 0, key=1;
        boolean pass, isDoubleEncryption;
        isDoubleEncryption = algo instanceof DoubleEncryption;
        do {
            if(isDoubleEncryption)
            {
                GenerateKeyFile(sourceFile, "testKey1", key+1);
            }
            GenerateKeyFile(sourceFile, "testKey", key);
            pass = CheckEncryptionAlgoImplementation(algo);
            strength++;
            key*=10;
        }while (pass && key<=1000000000);
        deleteDirectory(testDir);
        return strength;
    }

    public static boolean deleteDirectory(File directory)
    {
        if(directory.exists())
        {
            File[] files = directory.listFiles();
            if(null!=files)
            {
                for(int i=0; i<files.length; i++)
                {
                    if(files[i].isDirectory())
                    {
                        deleteDirectory(files[i]);
                    }
                    else
                    {
                        files[i].delete();
                    }
                }
            }
        }
        return(directory.delete());
    }

    public void CreateFile() throws FileNotFoundException
    {
        File file = new File (sourceFile);
        PrintWriter writer = new PrintWriter(file);
        writer.println("123456789");
        writer.println("abcdefg");
        writer.close();
    }

    private boolean compareTwoFiles(String file1Path, String file2Path)
            throws IOException {

        File file1 = new File(file1Path);
        File file2 = new File(file2Path);

        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        BufferedReader br2 = new BufferedReader(new FileReader(file2));

        String thisLine = null;
        String thatLine = null;

        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();

        while ((thisLine = br1.readLine()) != null) {
            list1.add(thisLine);
        }
        while ((thatLine = br2.readLine()) != null) {
            list2.add(thatLine);
        }

        br1.close();
        br2.close();

        return list1.equals(list2);
    }

    public void GenerateKeyFile(String sourceFile, String keyFileName, int key) throws IOException
    {
        String keyLocation;
        keyLocation = FileLocations.GetFolderLocation(sourceFile);
        keyLocation += "\\" + keyFileName + ".txt";
        FileWriter keyWriter = new FileWriter(keyLocation);
        keyWriter.write(String.valueOf(key).toCharArray());
        keyWriter.close();
    }

    public boolean CheckEncryptionAlgoImplementation(EncryptionAlgorithm algoToCheck) throws IOException
    {
        fileEncryptorTester = new FileEncryptor(algoToCheck);
        fileEncryptorTester.EncryptFile(sourceFile);
        String encryptedDecryptedFile, encryptedFile;
        if(algoToCheck instanceof DoubleEncryption)
        {
            encryptedFile = FileLocations.AddNameToFile(sourceFile, "_encrypted_encrypted");
            fileEncryptorTester.DecryptFile(encryptedFile,FileLocations.GetFolderLocation(sourceFile) + "\\key.txt");
            encryptedDecryptedFile = FileLocations.AddNameToFile(sourceFile,"_encrypted_encrypted_decrypted_decrypted");
        }
        else
        {
            encryptedFile = FileLocations.AddNameToFile(sourceFile, "_encrypted");
            fileEncryptorTester.DecryptFile(encryptedFile,FileLocations.GetFolderLocation(sourceFile)+"\\key.txt");
            encryptedDecryptedFile = FileLocations.AddNameToFile(sourceFile,"_encrypted_decrypted");
        }
        return compareTwoFiles(sourceFile,encryptedDecryptedFile);
    }
}
