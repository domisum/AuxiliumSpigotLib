package de.domisum.lib.auxiliumspigot.data.structure.pds;

import de.domisum.lib.auxilium.util.java.annotations.API;
import org.bukkit.entity.Player;

@API
public interface PlayerDataStructure
{

	boolean contains(Player player);

	boolean removePlayer(Player player);

	void onLeave(Player player);

}
