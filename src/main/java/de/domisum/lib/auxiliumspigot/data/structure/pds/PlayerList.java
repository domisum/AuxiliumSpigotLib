package de.domisum.lib.auxiliumspigot.data.structure.pds;

import de.domisum.lib.auxilium.util.java.annotations.APIUsage;
import de.domisum.lib.auxiliumspigot.AuxiliumSpigotLib;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

@APIUsage
public class PlayerList extends ArrayList<Player> implements PlayerDataStructure
{

	// CONSTRUCTOR
	@APIUsage public PlayerList()
	{
		AuxiliumSpigotLib.getPlayerDataStructureListener().registerPlayerDataStructure(this);
	}

	@APIUsage public PlayerList(Player[] players)
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
		// to be overwritten, if so desired
	}

}
