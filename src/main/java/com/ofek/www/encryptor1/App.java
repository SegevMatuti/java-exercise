package com.ofek.www.encryptor1;

public class App 
{
	public static void main( String[] args )
	{

        //new EncryptDecryptMenu();
        //DoubleEncryption f = new DoubleEncryption(new ShiftMultiplyEncryption());
        ShiftMultiplyEncryption f = new ShiftMultiplyEncryption();
        System.out.println(f.getKeyStrength());

	}
}
