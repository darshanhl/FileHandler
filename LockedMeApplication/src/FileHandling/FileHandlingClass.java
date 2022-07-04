package FileHandling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileHandlingClass 
{

	static FileHandlingClass f = new FileHandlingClass();

	Path path = Paths.get("/Users/darshan/Desktop/Test/");

    static String filePath ;

	int choice = 0;
	
	/**
	 * Method will create directory on the mentioned path
	 */
	public void createDirectory()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the directory where files to to created..");
		filePath = sc.nextLine();
		
		this.path = Paths.get("/Users/darshan/Desktop/Test/");
		
	}

	/**
	 * Method will create file in the created directory
	 * System will prompt to provide filename
	 * After file creation, system will request user to provide input data
	 */
	public void createUserFile()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter the filename to be created..");
		
		String fileName = sc.nextLine();
		
		File newFile =  new File(this.path + "/" + fileName);
		if(newFile.exists())
		{
			System.out.println("File already exists..");
		}
		else
		{
			try
			{
				newFile.createNewFile();		
				FileWriter fw = new FileWriter(newFile);
				BufferedWriter bw = new BufferedWriter(fw);
				
				System.out.println("Please enter the text to be written to file..");
				String data = sc.nextLine();
				bw.write(data);
				System.out.println("File successfully created..");
				bw.close();
				fw.close();
			} 
			catch (IOException e) 
			{
				System.out.println("Filed to create file.."+e.getMessage());
			}
		}
		
	}
	
	/**
	 * Method is used to delete file created
	 * USer should provide file to be deleted as input
	 */
	public void deleteFile()
	{
		Scanner sc = new Scanner(System.in);

		
		System.out.println("Enter the file name to be deleted..");
		String fileName = sc.nextLine();
		
		File file = new File(this.path + "/" +fileName);
		
		if(file.exists())
		{
			file.delete();
			
			System.out.println("File "+ file +" deleted successfully");
		}
		else
		{
			System.out.println(file +" doesn't exists..");
		}
		
	}
	
	/**
	 * Method to search file from the directory
	 * User should enter the filename to be searched
	 */
	void searchFile()
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the file to be searched..");
		String fileToSearch = sc.nextLine();
		
		Path path = Paths.get(this.path + "/" + fileToSearch);
		
		if(Files.exists(path))
		{
			if(Files.isReadable(path))
			{
				System.out.println("File "+fileToSearch+ " exists..");
			}
			if (Files.isDirectory(path)) 
			{
                System.out.println("File "+fileToSearch+ " exists, but it is a directory.");
            }
		}
		else 
		{
            System.out.println("File doesn't exist");
		}
		
	}
	
	/**
	 * Method will sort the files by filename
	 */
	public void listFileInAscendingOrder()
	{
		if (this.path != null) 
		{
			String dir = this.path.toString();

			File[] listOfFiles = new File(dir).listFiles();
			if (listOfFiles != null) 
			{
				List<File> list = Arrays.asList(listOfFiles);

				Collections.sort(list);

				for (File file : list) {
					System.out.println(file.getName());
				}
			}

		}
		else
		{
			System.out.println("No files found. Please create new file..");
		}

	}
	
	
	public void displayUserMenu()
	{
		System.out.println("**************************");
		System.out.println(" 1. Add new file");
		System.out.println(" 2. Search file");
		System.out.println(" 3. Delete file");
		System.out.println(" 4. Back to main menu");
		System.out.println("**************************");
	}
	
	public static void main(String[] args)
	{
		System.out.println("Welcome to LockedMe.com - A File Management System\n");
		mainMenu();
		
	}


	private static void mainMenu() {
		Scanner sc = new Scanner(System.in);
		int option ;
		
		do
		{
			System.out.println("Please select option from below menu..");
			System.out.println("1. Display all file names in ascending order");
			System.out.println("2. Go to user menu");
			System.out.println("3. Exit Application");
			
		    option = sc.nextInt();
		    
		    switch(option)
		    {
		    	case 1:
		    		f.listFileInAscendingOrder();
		    		break;
		    	case 2:
		    		f.displayUserMenu();
		    		f.userOperations();
		    		break;
		    	case 3:
		    		sc.close();
		    		System.out.println("Thank you for using LockedMe.com..");
		    		System.exit(0);
		    		break;
		    	default:
		    		System.out.println("Please enter valid option");
		    }
		    
		}while(option !=3);
	}


	public void userOperations() 
	{
		Scanner sc = new Scanner(System.in);

		int option;
		do
		{
			System.out.println("\n Please enter the option from user menu :");
			
		    option = sc.nextInt();
		    
			switch(option)
			{
			case 1:
				   System.out.println("Option 1 selected");
				   f.createUserFile();
				   break;
			case 2: 
				System.out.println("Option 2 selected");
				   f.searchFile();
				   break;
			case 3:
				   System.out.println("Option 3 selected");
				   f.deleteFile();
				   break;
			case 4:
				   System.out.println("Option 4 selected");
				   f.mainMenu();
				   break;
			default:
				System.out.println("Wrong option");
			}
			
		}
		while(option !=4);
	}
}
