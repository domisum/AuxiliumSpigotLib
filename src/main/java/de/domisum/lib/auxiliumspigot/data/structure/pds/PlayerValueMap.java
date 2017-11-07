package de.domisum.lib.auxiliumspigot.data.structure.pds;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxiliumspigot.AuxiliumSpigotLib;
import org.bukkit.entity.Player;

import java.util.HashMap;

@API
public class PlayerValueMap<T> extends HashMap<T, Player> implements PlayerDataStructure
{

	// INIT
	@API public PlayerValueMap()
	{
		AuxiliumSpigotLib.getPlayerDataStructureListener().registerPlayerDataStructure(this);
	}


	// EVENTS
	@API @Override public boolean contains(Player player)
	{
		return super.containsValue(player);
	}

	@API @Override public boolean removePlayer(Player player)
	{
		return entrySet().removeIf((entry)->entry.getValue() == player);
	}

	@Override public void onLeave(Player player)
	{
		// to be overwritten, if so desired
	}

}
