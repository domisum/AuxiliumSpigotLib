package de.domisum.lib.auxiliumspigot.data.structure.pds;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxiliumspigot.AuxiliumSpigotLib;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;

@API
public class PlayerSet extends HashSet<Player> implements PlayerDataStructure
{

	// INIT
	@API public PlayerSet()
	{
		AuxiliumSpigotLib.getPlayerDataStructureListener().registerPlayerDataStructure(this);
	}

	@API public PlayerSet(Player[] players)
	{
		this();

		this.addAll(Arrays.asList(players));
	}


	// EVENTS
	@API @Override public boolean contains(Player player)
	{
		return super.contains(player);
	}

	@API @Override public boolean removePlayer(Player player)
	{
		return remove(player);
	}

	@Override public void onLeave(Player player)
	{
		// to be overwritten, if so desiredN
	}

}
