package de.domisum.lib.auxiliumspigot.util;

import de.domisum.lib.auxilium.util.java.annotations.API;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessagingUtil
{

	@API
	public static void sendActionBarMessage(String message, Player... players)
	{
		for(Player p : players)
			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
	}


	@API
	public static void sendStatusMessage(Player player, String message)
	{
		player.sendMessage(ChatColor.GRAY.toString()+ChatColor.ITALIC+"["+message+"]");
	}

}
