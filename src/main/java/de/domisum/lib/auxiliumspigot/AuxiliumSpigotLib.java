package de.domisum.lib.auxiliumspigot;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxiliumspigot.data.structure.pds.PlayerDataStructureListener;
import de.domisum.lib.auxiliumspigot.monitor.PlayerVelocityMonitorManager;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public class AuxiliumSpigotLib
{

	// REFERENCES
	private static AuxiliumSpigotLib instance;
	private Plugin plugin;

	// MANAGERS
	private PlayerDataStructureListener playerDataStructureListener;
	private PlayerVelocityMonitorManager playerVelocityMonitorManager;


	// INIT
	private AuxiliumSpigotLib(Plugin plugin)
	{
		this.plugin = plugin;
	}

	@API public static void enable(Plugin plugin)
	{
		if(instance != null)
			return;

		instance = new AuxiliumSpigotLib(plugin);
		instance.onEnable();
	}

	@API public static void disable()
	{
		if(instance == null)
			return;

		getInstance().onDisable();
		instance = null;
	}

	private void onEnable()
	{
		this.playerDataStructureListener = new PlayerDataStructureListener();
		this.playerVelocityMonitorManager = new PlayerVelocityMonitorManager();

		getLogger().info(this.getClass().getSimpleName()+" has been enabled");
	}

	private void onDisable()
	{
		getLogger().info(this.getClass().getSimpleName()+" has been disabled");
	}


	// GETTERS
	@API public static AuxiliumSpigotLib getInstance()
	{
		if(instance == null)
			throw new IllegalArgumentException(AuxiliumSpigotLib.class.getSimpleName()+" has to be initialized before usage");

		return instance;
	}

	public static Plugin getPlugin()
	{
		return getInstance().plugin;
	}

	private Logger getLogger()
	{
		return getInstance().plugin.getLogger();
	}


	public static PlayerDataStructureListener getPlayerDataStructureListener()
	{
		return getInstance().playerDataStructureListener;
	}

	public static PlayerVelocityMonitorManager getPlayerVelocityMonitorManager()
	{
		return getInstance().playerVelocityMonitorManager;
	}

}
