package com.ofek.www.encryptor1;

public interface EncryptionAlgorithm {

    public int EncryptFile(String sourceFile, String keyFileName);

    public int EncryptFile(String sourceFile, String keyFileName, int key);

    public void DecryptFile(String sourceFile, String keyFile);

    public int getKeyStrength();
}