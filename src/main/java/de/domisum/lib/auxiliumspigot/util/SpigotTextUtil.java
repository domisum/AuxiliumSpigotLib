package de.domisum.lib.auxiliumspigot.util;

import de.domisum.lib.auxilium.util.java.annotations.APIUsage;
import de.domisum.lib.auxilium.util.math.MathUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@APIUsage
public class SpigotTextUtil
{

	// COMPLEX
	@APIUsage public static List<String> splitTextIntoLines(String text, int maxLineLength)
	{
		List<String> lines = new ArrayList<>();
		String textLeft = text;

		String lastLine = null;
		while(textLeft.length() > maxLineLength)
		{
			int lastSpaceIndex = -1;
			for(int i = 0; i < maxLineLength; i++)
				if(textLeft.charAt(i) == ' ')
					lastSpaceIndex = i;

			int lineLength = maxLineLength;
			if(lastSpaceIndex != -1)
				lineLength = lastSpaceIndex;

			String line = textLeft.substring(0, lineLength);

			// carry on the chat colors of the last line, each line's color is treated seperately
			if(lastLine != null)
				line = ChatColor.getLastColors(lastLine)+line;

			lines.add(line);
			textLeft = textLeft.substring(lineLength+1);
			lastLine = line;
		}

		// add last remaining bit, again add colors from line before
		String line = textLeft;
		if(lastLine != null)
			line = ChatColor.getLastColors(lastLine)+line;
		lines.add(line);

		return lines;
	}

	public static List<String> splitTextIntoLinesConsideringNewLines(String text, int maxLineLength)
	{
		List<String> lines = new ArrayList<>();
		String[] splitByNewLine = text.split("\\n");
		for(String s : splitByNewLine)
			lines.addAll(splitTextIntoLines(s.trim(), maxLineLength));

		return lines;
	}

	// TO STRING
	@APIUsage public static String getLocationAsString(Location location)
	{
		String worldName = location.getWorld() != null ? "'"+location.getWorld().getName()+"'" : "null";

		String string = "[";
		string += "world="+worldName+",";
		string += "x="+MathUtil.round(location.getX(), 3)+",";
		string += "y="+MathUtil.round(location.getY(), 3)+",";
		string += "z="+MathUtil.round(location.getZ(), 3)+",";
		string += "yaw="+MathUtil.round(location.getYaw(), 1)+",";
		string += "pitch="+MathUtil.round(location.getPitch(), 1);
		string += "]";
		return string;
	}

}
