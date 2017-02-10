package de.domisum.lib.auxiliumspigot.data.structure.pds;

import de.domisum.lib.auxilium.util.java.annotations.APIUsage;
import de.domisum.lib.auxiliumspigot.AuxiliumSpigotLib;
import org.bukkit.entity.Player;

import java.util.HashMap;

@APIUsage
public class PlayerValueMap<T> extends HashMap<T, Player> implements PlayerDataStructure
{

	// -------
	// CONSTRUCTOR
	// -------
	@APIUsage
	public PlayerValueMap()
	{
		AuxiliumSpigotLib.getPlayerDataStructureListener().registerPlayerDataStructure(this);
	}


	// -------
	// EVENTS
	// -------
	@APIUsage
	@Override
	public boolean contains(Player player)
	{
		return super.containsValue(player);
	}

	@APIUsage
	@Override
	public boolean removePlayer(Player player)
	{
		return entrySet().removeIf((entry)->entry.getValue() == player);
	}

	@Override
	public void onLeave(Player player)
	{

	}

}
