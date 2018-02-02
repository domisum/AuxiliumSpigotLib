package de.domisum.lib.auxiliumspigot.data.container.abstracts;

import de.domisum.lib.auxilium.data.container.math.Vector3D;
import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxilium.util.java.annotations.DeserializationNoArgsConstructor;
import de.domisum.lib.auxilium.util.java.annotations.InitByDeserialization;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

@API
public class AbstractLocation
{

	// GAME
	@InitByDeserialization private String world;

	@InitByDeserialization private double x;
	@InitByDeserialization private double y;
	@InitByDeserialization private double z;

	@InitByDeserialization private float yaw;
	@InitByDeserialization private float pitch;


	// INIT
	@DeserializationNoArgsConstructor public AbstractLocation()
	{

	}

	@API public AbstractLocation(String world, double x, double y, double z)
	{
		this(world, x, y, z, 0, 0);
	}

	@API public AbstractLocation(String world, double x, double y, double z, float yaw, float pitch)
	{
		this.world = world;

		this.x = x;
		this.y = y;
		this.z = z;

		this.yaw = yaw;
		this.pitch = pitch;
	}

	@API public AbstractLocation(Location model)
	{
		this.world = model.getWorld().getName();

		this.x = model.getX();
		this.y = model.getY();
		this.z = model.getZ();

		this.yaw = model.getYaw();
		this.pitch = model.getPitch();
	}


	// GETTERS
	@API public Location get()
	{
		World bukkitWorld = Bukkit.getWorld(this.world);
		if(bukkitWorld == null)
			throw new IllegalStateException("The world '"+this.world+"' of the abstract location is not loaded");

		return new Location(bukkitWorld, this.x, this.y, this.z, this.yaw, this.pitch);
	}

	@API public Vector3D getPosition()
	{
		return new Vector3D(this.x, this.y, this.z);
	}

}
