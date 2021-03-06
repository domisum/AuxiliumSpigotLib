package de.domisum.lib.auxiliumspigot.data.container.block;

import de.domisum.lib.auxilium.data.container.direction.Axis;
import de.domisum.lib.auxilium.util.java.annotations.API;

@API
public class BlockRotator
{

	@API public static byte rotateWood(byte data, Axis axis)
	{
		if(axis == Axis.Y)
			return data;
		else if(axis == Axis.X)
			return (byte) (data+4);
		else if(axis == Axis.Z)
			return (byte) (data+8);

		return -1;
	}

}
