package de.domisum.lib.auxiliumspigot.monitor;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxiliumspigot.AuxiliumSpigotLib;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PlayerVelocityMonitorManager implements Listener
{

	// REFERENCES
	private Map<Player, PlayerVelocityMonitor> playerVelocityMonitors = new HashMap<>();

	// STATUS
	private BukkitTask updateTask;


	// INIT
	public PlayerVelocityMonitorManager()
	{
		registerListener();
	}

	private void registerListener()
	{
		Plugin plugin = AuxiliumSpigotLib.getPlugin();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}


	// REGISTRATION
	@API public static PlayerVelocityMonitor getFor(Player player)
	{
		PlayerVelocityMonitorManager pvmm = AuxiliumSpigotLib.getPlayerVelocityMonitorManager();
		if(pvmm.playerVelocityMonitors.containsKey(player))
			return pvmm.playerVelocityMonitors.get(player);

		PlayerVelocityMonitor pvm = new PlayerVelocityMonitor(player);
		pvmm.playerVelocityMonitors.put(player, pvm);
		pvmm.startUpdateTask();

		return pvm;
	}


	// UPDATE
	private void startUpdateTask()
	{
		if(this.updateTask != null)
			return;

		this.updateTask = Bukkit.getScheduler().runTaskTimer(AuxiliumSpigotLib.getPlugin(), this::update, 1, 1);
	}

	private void stopUpdateTask()
	{
		if(this.updateTask == null)
			return;

		this.updateTask.cancel();
		this.updateTask = null;
	}

	private void update()
	{
		Iterator<PlayerVelocityMonitor> iterator = this.playerVelocityMonitors.values().iterator();
		while(iterator.hasNext())
		{
			PlayerVelocityMonitor pvm = iterator.next();
			Player player = pvm.getPlayer();

			if(!player.isOnline() || pvm.isTerminated() || pvm.isTimedOut())
				iterator.remove();
			else
				pvm.tick();
		}

		if(this.playerVelocityMonitors.size() == 0)
			stopUpdateTask();
	}


	// EVENTS
	@EventHandler public void playerMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();

		PlayerVelocityMonitor pvm = this.playerVelocityMonitors.get(player);
		if(pvm == null)
			return;

		Vector playerVelocity = event.getTo().toVector().subtract(event.getFrom().toVector());
		pvm.updatePlayerVelocity(playerVelocity);
	}

}
