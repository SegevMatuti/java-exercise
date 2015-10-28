package com.ofek.www.encryptor1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EncryptDecryptMenu 
{
	private FileEncryptor m_Encryptor;
	
	public void ShowMenu()
	{
		System.out.println("Please press 1 for Encryption or 2 for Decryption.");
		System.out.println("0. Exit");
		System.out.println("1. Encrypt");
		System.out.println("2. Decrypt");
		
	}
	
	public EncryptDecryptMenu()
	{
		m_Encryptor = new FileEncryptor(new ShiftUpEncryption());
        //m_Encryptor = new FileEncryptor(new DoubleEncryption(new ShiftUpEncryption()));
		MenuLoop();
	}

	public void MenuLoop()
	{
		int next = -1;
		Scanner in = new Scanner ( System.in );
		Scanner file = new Scanner ( System.in );

		do
		{		
			try
			{
				ShowMenu();			
				next = in.nextInt();
				switch ( next ) 
				{
				case 0:
					System.out.println("goodbye!");
					break;
				case 1:
					System.out.println ( "You picked Encryption!" );
					System.out.print( "Please enter a file location to encrypt: " );	
					String fileToEncryptLoc = file.nextLine();
					m_Encryptor.EncryptFile(fileToEncryptLoc);
					break;
				case 2:
					System.out.println ( "You picked Decryption!" );
					System.out.print( "Please enter encrypted txt file location to decrypt: " );
					String encryptedFileLoc = file.nextLine();
					System.out.print( "Please enter key txt file location: " );
					String keyFileLoc = file.nextLine();
					m_Encryptor.DecryptFile(encryptedFileLoc, keyFileLoc);
					break;

				default:
					System.err.println ( "invalid input!" );
					System.out.println();
					//MenuLoop();
					break;				
				}
			}catch(InputMismatchException e)
			{
				System.err.println("invalid input!");
				System.out.println();
				//MenuLoop();
			}

		}while(next!= 0);		
		in.close();		
		file.close();
	}
}

