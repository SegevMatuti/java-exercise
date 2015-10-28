package com.ofek.www.encryptor1;

import java.io.IOException;

public class ShiftUpEncryption extends Encryptor implements EncryptionAlgorithm
{
	public int DecryptionStrategy(int character, int key)
	{
		return (character-key);
	}
	
	public int EncryptionStrategy(int character, int key)
	{
		return (character+key);
	}

    public int getKeyStrength() {
        TestUtills utills = new TestUtills();
        int strength = 0;
        try
        {
            strength = utills.checkKeyStrengthForEncryptionAlgo(new ShiftUpEncryption());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return strength;
    }
}
