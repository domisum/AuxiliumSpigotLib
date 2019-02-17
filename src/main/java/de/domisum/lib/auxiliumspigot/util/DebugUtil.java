package de.domisum.lib.auxiliumspigot.util;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxiliumspigot.AuxiliumSpigotLib;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

@API
public final class DebugUtil
{

	@API
	public static void say(Object message)
	{
		say(message+"");
	}

	@API
	public static void say(String message)
	{
		String formattedMessage = "[DEBUG] "+message;

		AuxiliumSpigotLib.getPlugin().getLogger().log(Level.WARNING, formattedMessage);
		for(Player p : Bukkit.getOnlinePlayers())
			p.sendMessage(formattedMessage);
	}

}
