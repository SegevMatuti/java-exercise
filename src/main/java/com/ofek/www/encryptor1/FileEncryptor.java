package com.ofek.www.encryptor1;

public class FileEncryptor
{
	private EncryptionAlgorithm m_EncryprionAlgorithm;
	
	public FileEncryptor(EncryptionAlgorithm Algorithm)
	{
		m_EncryprionAlgorithm = Algorithm;
	}
	
	public void EncryptFile(String sourceFile)
	{
		m_EncryprionAlgorithm.EncryptFile(sourceFile, "key");
	}

    public void EncryptFile(String sourceFile, String keyFile, int key)
    {
        m_EncryprionAlgorithm.EncryptFile(sourceFile, keyFile, key);
    }
	
	public void DecryptFile(String sourceFile, String keyLoc)
	{
		m_EncryprionAlgorithm.DecryptFile(sourceFile, keyLoc);
	}
}
