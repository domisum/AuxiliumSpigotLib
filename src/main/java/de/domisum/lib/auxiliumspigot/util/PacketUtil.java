package de.domisum.lib.auxiliumspigot.util;

import de.domisum.lib.auxilium.util.java.annotations.API;
import net.minecraft.server.v1_9_R1.Packet;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

public class PacketUtil
{

	// VALUE CONVERSION
	@API public static int toPacketDistance(double d)
	{
		return (int) Math.floor(d*32)*128;
	}

	@API public static byte toPacketAngle(float f)
	{
		return (byte) ((int) ((f*256.0f)/360.0f));
	}


	// SENDING
	@API public static void sendPacket(Packet<?> packet, Player... players)
	{
		for(Player p : players)
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}

	@API public static void sendPacket(Packet<?> packet, Collection<? extends Player> players)
	{
		sendPacket(packet, players.toArray(new Player[players.size()]));
	}
}
