import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CipherPartTwo 
{

	public static String caesar_cipher(String fileName, boolean encrypt, int shiftAmount) throws IOException
	{
		char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h','i','j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		char[] upperAlphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		char[] lowerCipher = new char[26];
		char[] upperCipher = new char[26];
		int shift = shiftAmount;
		
		if(encrypt)
		{
			for(int i = 0; i<lowerCipher.length; i++)
			{
				if((i+shift) % 26 <0)
					lowerCipher[i] = alphabet[26 + (i+shift)];
			
				else
					lowerCipher[i]=alphabet[(i+shift) % 26];
			}
			
			for(int j = 0; j<upperCipher.length; j++)
				upperCipher[j] = (char) (lowerCipher[j] - 32);
			
			File myFile = new File(fileName);
			Scanner inputFile = new Scanner(myFile);
			PrintWriter outputFile = new PrintWriter(fileName.substring(0, fileName.length()-4) + "_ENC.txt");
			
			String mess = "";
			String messEnc = "";
			while(inputFile.hasNext())
			{
				mess += inputFile.nextLine();
				mess += "\n";
				
			}
			
			int index = 0;
			for(int i=0; i<mess.length(); i++)
			{
				index = 0;
				if(isUpper(mess.charAt(i)))
				{
					while(mess.charAt(i) != upperAlphabet[index])
						index++;
					messEnc += upperCipher[index];
				}
				else if(isLower(mess.charAt(i)))
				{
					while(mess.charAt(i) != alphabet[index])
						index++;
					messEnc += lowerCipher[index];
				}
				else
					messEnc += mess.charAt(i);
			}
			
			outputFile.print(messEnc);
			outputFile.close();
			inputFile.close();
			String re = "Result written to " + fileName.substring(0,fileName.length()-4) + "_ENC.txt";
			return re;
		}
		else
		{
			for(int i = 0; i<lowerCipher.length;i++)
			{
				diff = alphabet[i] - 'a';
				if(i + shift % 26 < 0)
					lowerCipher[i] = (char) (26 + (i+shift));
				else
					lowerCipher[i] = alphabet[(i+shift) % 26];
				
			}
			
			for(int j = 0; j<upperCipher.length; j++)
				upperCipher[j] = (char) (lowerCipher[j] - 32);
			
			File myFile = new File(fileName);
			Scanner inputFile = new Scanner(myFile);
			PrintWriter outputFile = new PrintWriter(fileName.substring(0, fileName.length()-4) + "_ENC.txt");
			
			String encMess = "";
			String decMess = "";
			while(inputFile.hasNext())
			{
				encMess += inputFile.nextLine();
				encMess += "\n";
				
			}
			
			
			
		}
		

	}
	
	public static boolean isLower(char c)
	{
		return c >= 'a' && c <= 'z';
	}
	
	public static boolean isUpper(char c)
	{
		return c >= 'A' && c <= 'Z';
	}

}
