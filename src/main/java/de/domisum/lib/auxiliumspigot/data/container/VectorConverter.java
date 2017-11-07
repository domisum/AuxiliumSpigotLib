package de.domisum.lib.auxiliumspigot.data.container;

import de.domisum.lib.auxilium.data.container.math.Vector3D;
import de.domisum.lib.auxilium.util.java.annotations.API;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

@API
public interface VectorConverter
{

	// VECTOR3D
	@API static Vector3D toVector3D(Location location)
	{
		return new Vector3D(location.getX(), location.getY(), location.getZ());
	}

	@API static Vector3D toVector3D(Vector vector)
	{
		return new Vector3D(vector.getX(), vector.getY(), vector.getZ());
	}


	// LOCATION
	@API static Location toLocation(Vector3D vector, World world)
	{
		return toLocation(vector, world, 0, 0);
	}

	@API static Location toLocation(Vector3D vector, World world, float yaw, float pitch)
	{
		return new Location(world, vector.x, vector.y, vector.z, yaw, pitch);
	}

}
