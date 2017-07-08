package de.domisum.lib.auxiliumspigot.data.container;

import de.domisum.lib.auxilium.data.container.math.Vector3D;
import de.domisum.lib.auxilium.util.java.annotations.APIUsage;
import org.bukkit.Location;
import org.bukkit.util.Vector;

@APIUsage
public interface VectorConverter
{

	@APIUsage static Vector3D toVector3D(Location location)
	{
		return new Vector3D(location.getX(), location.getY(), location.getZ());
	}

	@APIUsage static Vector3D toVector3D(Vector vector)
	{
		return new Vector3D(vector.getX(), vector.getY(), vector.getZ());
	}

}
