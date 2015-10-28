package com.ofek.www.encryptor1;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;



import java.io.Console;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        TestUtills utills = new TestUtills();
        File testDir = new File("C:\\Encryptor1AppTest");
        testDir.mkdir();
        try
        {
            utills.CreateFile();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            assertTrue(utills.CheckEncryptionAlgoImplementation(new ShiftUpEncryption()));
            assertTrue(utills.CheckEncryptionAlgoImplementation(new ShiftMultiplyEncryption()));
            assertTrue(utills.CheckEncryptionAlgoImplementation(new DoubleEncryption(new ShiftMultiplyEncryption())));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        utills.deleteDirectory(testDir);
    }
}
