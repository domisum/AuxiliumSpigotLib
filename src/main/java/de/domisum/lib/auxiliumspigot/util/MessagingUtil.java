package de.domisum.lib.auxiliumspigot.util;

import de.domisum.lib.auxilium.util.java.annotations.API;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_13_R2.ChatMessageType;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class MessagingUtil
{

	@API
	public static void sendActionBarMessage(String message, Player... players)
	{
		IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \""+message+"\"}");
		PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, ChatMessageType.CHAT);

		for(Player p : players)
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
	}


	@API
	public static void sendStatusMessage(Player player, String message)
	{
		player.sendMessage(ChatColor.GRAY.toString()+ChatColor.ITALIC+"["+message+"]");
	}

}
