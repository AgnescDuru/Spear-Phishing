import java.util.*;
import java.io.*;

//To Run:
//java Spearphishing.java wordBank.txt dictionary.txt giftcardSample.txt 
//java [Java Program Name] [Training File](0) [Dictionary File](1) [Test File](2)

public class Spearphishing{
	public static void main(String[] argv){
		int iSusCount = 0;
		int iMisspellCount = 0;
		int iPhishTotal = 0;
		String theDictFile = GetFileContents(argv[1]).toLowerCase(); //Dictionary File 
		String theTestFile = GetFileContents(argv[2]).toLowerCase(); //Test File 
		
		try {
			//Suspicious Words
			Scanner scanner = new Scanner(new File(argv[0])); //Training file
			while (scanner.hasNextLine()) {
				if (theTestFile.contains(scanner.nextLine().toLowerCase())){
					iSusCount++;
				}
			}
			
			//Mispelled Words
			iMisspellCount = GetMisspelledCount(theTestFile,theDictFile);
			
			scanner.close();
			iPhishTotal = iSusCount + iMisspellCount;
			
			//Suspicous Words 
			System.out.println("\nThe test data contains " + iSusCount + " suspicious words...\n");
			
			//Mispelled Words 
			System.out.println("The test data contains " + iMisspellCount + " misspelled words...");
			
			if(iPhishTotal >= 3){
				System.out.println("\nThis is a potential phishing attack!! Be careful.\n");
			} else {
				System.out.println("\nThis is not a potential phishing attack. Stay safe. \n");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	///GetMisspelledCount
	//Returns the count of the misspelled words from the test file 
	//@Return: int 
	///
	public static int GetMisspelledCount(String sTestFile, String sDicFile)
	{
		int iMisspellCount = 0;
		String sDictFile = sDicFile.toLowerCase();
		String[] arrWord = sTestFile.toLowerCase().replace('.', ' ').replace(',', ' ').replace('!', ' ').replace(':', ' ').replace('#',' ').split(" ");
		for(int i=0;i< arrWord.length; i++)
		{
			if (!sDictFile.contains(" " + arrWord[i] + " "))
			{
				iMisspellCount = iMisspellCount + 1;
			}
		}
		return iMisspellCount;
	}
	
	///GetFileContents
	//Returns the contents of the file as a string
	//@Return: String 
	///
	public static String GetFileContents(String argvN){
		String contents = "";
		
		try {
			Scanner scanner = new Scanner(new File(argvN));
			while (scanner.hasNextLine()) {
				contents += scanner.nextLine() + " ";
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return contents;
	}
}