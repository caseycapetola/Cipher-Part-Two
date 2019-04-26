import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CipherPartTwo 
{
	public static void main(String[] args) throws IOException
	{
		//do everything
		Scanner user = new Scanner(System.in);
		System.out.print("Would you like to encrypt or decrypt a file? ");
		String crypt = user.nextLine();
		boolean pizza = true;
		String fileName = "";
		while(!crypt.equals("encrypt") && !crypt.equals("decrypt"))
		{
			System.out.print("\nInvalid Response. Would you like to encrypt or decrypt a file? ");
			crypt = user.nextLine();
		}
		
		System.out.print("\nHow many places should the alphabet be shifted? ");
		int shift = user.nextInt();
		user.nextLine();
		
		if(crypt.equals("encrypt"))
		{
			pizza = true;
			System.out.print("\nEnter a filename to encrypt: ");
			fileName = user.nextLine();
		}
		else
		{
			pizza = false;
			System.out.print("Enter a filename to decrypt: ");
			fileName = user.nextLine();
		}
		
		String fin = caesar_cipher(fileName, pizza, shift);
		System.out.print("\n" + fin);
		
		user.close();
	}
	
	
	public static String caesar_cipher(String fileName, boolean encrypt, int shiftAmount) throws IOException
	{
		char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h','i','j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		char[] upperAlphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		char[] lowerCipher = new char[26];
		char[] upperCipher = new char[26];
		int shift = shiftAmount;
		
		if(encrypt)
		{
			for(int i = 0; i<lowerCipher.length;i++)
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
			
			return messEnc;
		}
		else
		{
			for(int i = 0; i<lowerCipher.length;i++)
			{
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
			
			int index = 0;
			for(int i=0; i<encMess.length(); i++)
			{
				index = 0;
				if(isUpper(encMess.charAt(i)))
				{
					while(encMess.charAt(i) != upperCipher[index])
						index++;
					decMess += upperAlphabet[index];
				}
				else if(isLower(encMess.charAt(i)))
				{
					while(encMess.charAt(i) != lowerCipher[index])
						index++;
					decMess += alphabet[index];
				}
				else
					decMess += encMess.charAt(i);
			}
			
			outputFile.print(decMess);
			outputFile.close();
			inputFile.close();
			return decMess;
			
			
			
			
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
