package de.domisum.lib.auxiliumspigot.data.structure.pds;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxiliumspigot.AuxiliumSpigotLib;
import org.bukkit.entity.Player;

import java.util.HashMap;

@API
public class PlayerKeyMap<T> extends HashMap<Player, T> implements PlayerDataStructure
{

	// INIT
	@API public PlayerKeyMap()
	{
		AuxiliumSpigotLib.getPlayerDataStructureListener().registerPlayerDataStructure(this);
	}


	// EVENTS
	@API @Override public boolean contains(Player player)
	{
		return super.containsKey(player);
	}

	@API @Override public boolean removePlayer(Player player)
	{
		return remove(player) != null;
	}

	@Override public void onLeave(Player player)
	{
		// to be overwritten, if so desired
	}

}
