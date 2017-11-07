package de.domisum.lib.auxiliumspigot.data.structure.pds;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxiliumspigot.AuxiliumSpigotLib;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

@API
public class PlayerList extends ArrayList<Player> implements PlayerDataStructure
{

	// INIT
	@API public PlayerList()
	{
		AuxiliumSpigotLib.getPlayerDataStructureListener().registerPlayerDataStructure(this);
	}

	@API public PlayerList(Player[] players)
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
		// to be overwritten, if so desired
	}

}
