package de.domisum.lib.auxiliumspigot.data.structure.pds;

import de.domisum.lib.auxiliumspigot.AuxiliumSpigotLib;
import de.domisum.lib.auxilium.util.java.annotations.APIUsage;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;

@APIUsage
public class PlayerSet extends HashSet<Player> implements PlayerDataStructure
{

	// CONSTRUCTOR
	@APIUsage public PlayerSet()
	{
		AuxiliumSpigotLib.getPlayerDataStructureListener().registerPlayerDataStructure(this);
	}

	@APIUsage public PlayerSet(Player[] players)
	{
		this();

		this.addAll(Arrays.asList(players));
	}


	// EVENTS
	@APIUsage @Override public boolean contains(Player player)
	{
		return super.contains(player);
	}

	@APIUsage @Override public boolean removePlayer(Player player)
	{
		return remove(player);
	}

	@Override public void onLeave(Player player)
	{
		// to be overwritten, if so desiredN
	}

}
