package de.domisum.lib.auxiliumspigot.data.container;

import de.domisum.lib.auxilium.data.container.math.Vector3D;
import de.domisum.lib.auxilium.util.java.annotations.APIUsage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

@APIUsage
public interface VectorConverter
{

	// VECTOR3D
	@APIUsage static Vector3D toVector3D(Location location)
	{
		return new Vector3D(location.getX(), location.getY(), location.getZ());
	}

	@APIUsage static Vector3D toVector3D(Vector vector)
	{
		return new Vector3D(vector.getX(), vector.getY(), vector.getZ());
	}


	// LOCATION
	@APIUsage static Location toLocation(Vector3D vector, World world)
	{
		return toLocation(vector, world, 0, 0);
	}

	@APIUsage static Location toLocation(Vector3D vector, World world, float yaw, float pitch)
	{
		return new Location(world, vector.x, vector.y, vector.z, yaw, pitch);
	}

}
