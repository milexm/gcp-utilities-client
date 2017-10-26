package com.acloudysky.utilities;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;




/**
* Defines utility methods and variables to support the application operations
* such as menu creation, regions list initialization and so on.
* @author Michael Miele
*
*/
public class Utility implements IUtility {
	
	// For testing purposes.
	static boolean DEBUG;
	
	
	
	public static boolean isDEBUG() {
		return DEBUG;
	}


	public static void  setDEBUG(boolean dEBUG) {
		DEBUG = dEBUG;
	}
	
	
	/**
	 * Displays the available scopes. 
	 * @param scopes The list of the allowed service scopes.
	 */
	public static void displayScopes(HashMap<String, String> scopes) {
		Set<?> set = scopes.entrySet();
	    Iterator<?> iterator = set.iterator();
		while(iterator.hasNext()) {
	         Map.Entry<?, ?> mentry = (Map.Entry<?,?>)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
		} 
	}
	
	/**
	 * Gets the scope related to the specified key for the specific service scopes.
	 * @param scopes The list of the allowed service scopes.
	 * @param key The key representing the service scope. For example, dev_storage_full_control.
	 * @return The value representing the desired scope. 
	 */
	public static String getScope(HashMap<String, String> scopes, String key) {
		String currentScope = null;
		Set<?> set = scopes.entrySet();
	    Iterator<?> iterator = set.iterator();
		while(iterator.hasNext()) {
	       Map.Entry<?, ?> mentry = (Map.Entry<?,?>)iterator.next();
	       
	       // Test
	       // System.out.println(String.format("Key: %s Value: %s", mentry.getKey(), mentry.getValue()));
	       
	       // Check if the key exists in the list.
	       int keyExist = key.trim().compareTo(mentry.getKey().toString());
	       
	       if (keyExist == 0) {
	    	   // Assign the selected region. 
	    	   currentScope = (String) mentry.getValue();
	    	   break;
	       }	 
		} 
		
		if (currentScope == null)
			System.out.println(String.format("Selected scope %s not allowed!", key.trim()));
		return currentScope;
	}
	
	 /**
	  * Displays the menu.
	  * @param entry The array containing the menu entries. 
	  */
	 public static void displayMenu(ArrayList<String> entry) {
		
		// Display menu header.
		System.out.println(dividerLine("*", DIVIDER_LENGTH));
		
		// Display menu entries.
	 	Iterator<String> i = entry.iterator();
	 	while (i.hasNext()) {
	 		System.out.println(i.next());
	 	}	
	 	
	 	// Display menu footer.
	 	System.out.println(dividerLine("*", DIVIDER_LENGTH));
	 }
	 
	 /**
	  * Displays welcome message.
	  * @param message The message to display.
	  */
	 public static void displayWelcomeMessage(String message)
	 {
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	     String welcome = "Welcome to " + message; 
	     System.out.println(headerLine(welcome, DIVIDER_LENGTH));
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	 }
	
	 /**
	  * Displays good bye message.
	  * @param message The message to display.
	  */
	 public static void displayGoodbyeMessage(String message)
	 {
		 headerLine(message, DIVIDER_LENGTH);
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	     String bye = "Thank you for using " + message; 
	     System.out.println(headerLine(bye, DIVIDER_LENGTH));
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	 }
	 
	 /**
	  * Gets the name of the OS and the user home directory.
	  * @return The array containing the OS name and the user home directory.
	  */
	 public static ArrayList<String> getEnvironment() {
		 return environment;
	 }
	 
    /**
	 * Calculate the absolute path of the file containing the client secrets or the 
	 * file containing app defaults. 
	 * @param parentDir The parent directory such as .googleservices.
	 * @param dataDir The directory containing the data file such as storage.
	 * @param dataFile The fiel containing the data such as client_secrets.json or client_defaults.json. 
	 * @return The absolute path of the file.
	 */
	public static  String getAbsoluteFilePath (String parentDir, String dataDir, String dataFile) {
	
		// Get the current computer OS and the user home directory. 
	 	String OS = System.getProperty("os.name");
	    String home_dir = System.getProperty("user.home");
	    
		String filePath = null;
  
		if (OS.startsWith("Windows"))
			filePath = home_dir.concat("\\" + parentDir + "\\" + dataDir + "\\" + dataFile);
		else 
			if (OS.startsWith("Mac"))
				filePath = home_dir.concat("/" + parentDir + "/" + dataDir + "/" + dataFile);
	
		if (isDEBUG()){
			System.out.println(String.format("Home dir: %s", home_dir));
			System.out.println(String.format("OS: %s", OS));
			System.out.println(String.format("File path: %s", filePath));
		}	
		
		return filePath;
	}
 
	/***
	 * Calculate the absolute path of a file.
	 * @param dir The name of the directory where the file resides.
	 * @param fileName The name of the file.
	 * @return The absolute path of the file.
	 */
	public static  String getAbsoluteFilePath (String dir, String fileName) {
		
		// Get the current user home directory.
        String home_dir = System.getProperty("user.home");
        String OS = System.getProperty("os.name");
        
    	String filePath = null;
    	  
		if (OS.startsWith("Windows"))
			filePath = home_dir.concat("\\" + dir + "\\" + fileName);
		else 
			if (OS.startsWith("Mac"))
				filePath = home_dir.concat("/" + dir + "/" + fileName);
	
       
        // Test 
        // System.out.println(String.format("File path: %s", filePath));
       
        return filePath;
        
	}
		
	 /**
	  * Gets the specified resource file
	  * @param fileName The name of the resource file.
	  * @return The resource file.
	  * <b>Note</b>. The file should exist in the project resources folder. 
	  */
	 public static File getResourceFile(String fileName){
		 
		 File resourceFile = null;
		 
		 URL resource = Utility.class.getResource("/" + fileName);
		 
		 // Test
		 // System.out.println(String.format("File name is: %s", fileName));
		 // System.out.println(String.format("URL is: %s", resource.toString()));
		 
		 try {
			resourceFile = Paths.get(resource.toURI()).toFile();
		 } catch (URISyntaxException e) {
			
			e.printStackTrace();
		 }	
		 
		 return resourceFile;
		 
	 }
	 
	 /**
      * Displays the contents of the specified input stream as text.
      * @param input The input stream to display as text.
      * @throws IOException Error encountered while handling the stream.
      */
    public static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("    " + line);
        }
        System.out.println();
    }
    
    /**
     * Displays the response header.
     * @param response The HTTP response object. 
     */
	public static void  displayResponseHeaders(HttpResponse response) {

		HttpHeaders headers = response.getHeaders();
		
		
		// Get the file list iterator.
		Iterator<Entry<String, Object>> iterator = headers.entrySet().iterator();
		
		System.out.println(String.format("%n================== " + "Response Headers" + " ================== %n"));	
		
		while (iterator.hasNext()) {	
			Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue().toString();
			System.out.println(String.format("Key: %s Value: %s", key, value));
		}
		
	}
	
	/**
	 * Displays the response header.
	 * @param request The HTTP request object.
	 */
	public static void displayRequestHeaders(HttpRequest request) {
		
		HttpHeaders headers = request.getHeaders();
		
		// Get the file list iterator.
		Iterator<Entry<String, Object>> iterator = headers.entrySet().iterator();
		
		System.out.println(String.format("%n================== " + "Request Headers" + " ================== %n"));	
		
		while (iterator.hasNext()) {	
			Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue().toString();
			System.out.println(String.format("Key: %s Value: %s", key, value));
		}
		
	}
	 
	
	/***
	 * Copies an input string to a file.
	 * @param text The input string to read from.
	 * @param dir The name of the file directory.
	 * @param fileName The name of the file to write to.
	 * @throws IOException An I/O error has been detected.
	 */
	public static void copyInputStringToFile (String text, 
			String dir, String fileName) throws IOException {
		   
		String outFilePath = getAbsoluteFilePath(dir, fileName);
		// BufferedReader in = new BufferedReader(new InputStreamReader(stream));
	    
		PrintWriter outputStream = null;
        
        try {
				PrintWriter out = new PrintWriter(new FileWriter(outFilePath));
				out.println( text );
				out.close();
		} 
        catch (IOException e) {
        	System.out.println(String.format("[Utility.copyFileFromInputStream] error occured: %s", e.getMessage()));
		}
		finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}	
	}
	
	/**
	 * Copies an input stream to a file.
	 * @param inStream The input stream object.
	 * @param dir The name of the file directory.
	 * @param fileName The name of the file to write to.
	 * @throws IOException An I/O error has been detected.
	 */
	public static void copyInputStreamToFile (InputStream inStream, 
			String dir, String fileName) throws IOException {
		   
		String outFilePath = getAbsoluteFilePath(dir, fileName);
		PrintWriter outputStream = null;
		
		 try {
			    // FileWriter fw = new FileWriter(outFilePath); 
			    
				// PrintWriter out = new PrintWriter(new FileWriter(outFilePath));
				// out.print(stream);
				// out.print(stream);
				// out.println( stream );
				// out.close();
				
			 
				 FileOutputStream out = new FileOutputStream(new File(outFilePath));
			 	
			 	out.flush();
			    out.close();
		
		} 
     catch (IOException e) {
     	System.out.println(String.format("[Utility.copyFileFromInputStream] error occured: %s", e.getMessage()));
		}
		finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}	
		
	}

	/***
	 * Copies an existing file to a new file.
	 * @param dir The name of the directory where the files reside.
	 * @param inFileName The name of the file to copy from.
	 * @param outFileName The name of the file to copy to.
	 * @throws IOException An I/O error has been detected.
	 */
	public  void copyFile (String dir, String inFileName, 
			String outFileName) throws IOException {
		   
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;
		
		String inFilePath = getAbsoluteFilePath(dir, inFileName);
	    String outFilePath = getAbsoluteFilePath(dir, outFileName);
	
		try {
			
			/* Instantiate the input buffer line stream. */
			inputStream = new BufferedReader(new FileReader(inFilePath));
	
			/* Instantiate the output buffer line stream. */
			outputStream = new PrintWriter(new FileWriter(outFilePath));
	
			String l;
			while ((l = inputStream.readLine()) != null) {
				outputStream.println(l);
	     }
		} 
		catch (IOException e) {
	        	System.out.println(String.format("Error occured: %s", e.getMessage()));
		}  
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}	
	}
	
	
	 /*************************
	  ** Internal utilities. **
	  *************************/
	 
	 /*
	  * Creates the header to display.
	  * @param headerText The text to display in the header.
	  * @param length The length of the header.  
	  * @return Formatted header line.
	  */
	 private static String headerLine(String headerText, int length)
	 {
	     String header = "";
	     header = header.concat("***");
	     int blankSpaces = (length - (header.length() + headerText.length()))/2;
	     
	     for(int i = 2; i < blankSpaces; i++)
	     	header = header.concat(" ");
	     header = header.concat(headerText);
	     for(int i = header.length(); i < length - 3; i++)
	     	header = header.concat(" ");
	     header = header.concat("***");
	     return header;
	 }
	 
	 /*
	  * Creates the divider line.
	  * @param c The character to use to create the divider line.
	  * @param length The length of the divider line.
	  * @return Formatted divider line.
	  */
	 private static String dividerLine(String c, int length)
	 {
	     String divider = "";
	     for(int i = 0; i < length; i++)
	         divider = divider.concat(c);
	
	     return divider;
	 }
}

