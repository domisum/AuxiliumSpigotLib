package de.domisum.lib.auxiliumspigot.util.player;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxiliumspigot.AuxiliumSpigotLib;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

@API
public class PlayerCausedExplosion implements Listener
{

	// REFERENCES
	private static PlayerCausedExplosion listener;
	private static Player currentPlayer;

	private Player player;

	// PROPERTIES
	private Location location;

	private double power = 4;
	private boolean fire = false;
	private boolean breakBlocks = true;

	private boolean damageSelf = true;


	// INIT
	@API public PlayerCausedExplosion(Location location, Player player)
	{
		this.location = location;

		this.player = player;
	}

	private void registerListener()
	{
		if(listener != null)
			return;

		listener = this;
		Plugin plugin = AuxiliumSpigotLib.getPlugin();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}


	// GETTERS
	@API protected boolean shouldDamage(Entity entity)
	{
		return true;
	}


	// SETTERS
	@API public PlayerCausedExplosion setPower(double power)
	{
		this.power = power;
		return this;
	}

	@API public PlayerCausedExplosion setFire(boolean fire)
	{
		this.fire = fire;
		return this;
	}

	@API public PlayerCausedExplosion setBreakBlocks(boolean breakBlocks)
	{
		this.breakBlocks = breakBlocks;
		return this;
	}

	@API public PlayerCausedExplosion setDamageSelf(boolean damageSelf)
	{
		this.damageSelf = damageSelf;
		return this;
	}


	// EXPLOSION
	@API public void detonate()
	{
		Bukkit.getScheduler().runTask(AuxiliumSpigotLib.getPlugin(), this::detonateSync);
	}

	private void detonateSync()
	{
		registerListener();
		currentPlayer = this.player;

		this.location.getWorld()
				.createExplosion(this.location.getX(), this.location.getY(), this.location.getZ(), (float) this.power, this.fire,
						this.breakBlocks);

		currentPlayer = null;
	}


	// EVENTS
	@EventHandler(priority = EventPriority.LOWEST) public void entityDeathByExplosion(EntityDeathEvent event)
	{
		if(currentPlayer == null)
			return;

		// set killer to player so other listeners pick it up as the player is the killer
		((CraftLivingEntity) event.getEntity()).getHandle().killer = ((CraftPlayer) currentPlayer).getHandle();
	}

	@EventHandler(priority = EventPriority.LOWEST) public void entityDamageByExplosion(EntityDamageEvent event)
	{
		if(currentPlayer == null)
			return;

		Entity entity = event.getEntity();

		// prevent self damage if disabled
		if(!this.damageSelf && currentPlayer.equals(entity))
			event.setCancelled(true);

		if(!shouldDamage(entity))
			event.setCancelled(true);
	}

}
