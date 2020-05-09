package cis44Project;

import java.sql.*; // * means adds all classes of java.sql
import javax.swing.*;
// if other frames are going to use the database, copy import lines from above
/*
 * Download latest jar file (sqlite-jdbc-3.30.1.jar) from https://bitbucket.org/xerial/sqlite-jdbc/downloads/
 * (not the repository) (pick the latest one 3.30.1 or greater)
 * Then add those jar files into a folder called Resources in workspace (you make this folder)
 * Then drag and drop the folder into the Project folder (link files)
 * Then Right click the JRE System Library folder of project -> Build Path -> Configure Build Path
 * Click on Modulepath and click the Add Jar file
 * Then click on the added jar files in the Project folder
 * 
 * 
 */

public class sqliteConnection {
	
	Connection conn = null;
	
	public static Connection dbConnector()
	{
		
		try {
			//database_location is the location of the database in the user's computer
			String database_location = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\database files\\CIS44ProjectData.sqlite";
			
			Class.forName("org.sqlite.JDBC");

			
			//establishes connection of Java program to SQLite database.
			Connection conn = DriverManager.getConnection(database_location);
			JOptionPane.showMessageDialog(null, "Connection Successful");
			return conn;
		} catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}

//download driver https://bitbucket.org/xerial/sqlite-jdbc/downloads/
//location of sqlite file database for project

/*
switch(PC)
{
case "ALFONSO'S":
	database_location = "jdbc:sqlite:C:\\Users\\Alfonso\\git\\cis44Project\\cis44Project\\database files\\CIS44ProjectData.sqlite";
	break;
case "ALI'S":
	database_location = "jdbc:sqlite:C:\\Users\\Ali Altimimi\\git\\cis44project\\cis44Project\\database files\\CIS44ProjectData.sqlite";
	break;
}
*/

//System.out.println(System.getProperty("user.dir"));