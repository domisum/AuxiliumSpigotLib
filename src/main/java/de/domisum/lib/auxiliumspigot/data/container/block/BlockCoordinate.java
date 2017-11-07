package de.domisum.lib.auxiliumspigot.data.container.block;

import de.domisum.lib.auxilium.data.container.dir.Direction2D;
import de.domisum.lib.auxilium.util.java.annotations.API;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;

@API
public class BlockCoordinate implements Comparable<BlockCoordinate>
{

	// PROPERTIES
	public final int x;
	public final int y;
	public final int z;


	// INIT
	@API public BlockCoordinate(Block block)
	{
		this.x = block.getX();
		this.y = block.getY();
		this.z = block.getZ();
	}

	@API public BlockCoordinate(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@API public BlockCoordinate(int x, int z)
	{
		this(x, 0, z);
	}


	@Override public boolean equals(Object o)
	{
		if(!(o instanceof BlockCoordinate))
			return false;

		BlockCoordinate other = (BlockCoordinate) o;
		return (other.x == this.x) && (other.y == this.y) && (other.z == this.z);
	}

	@Override public int hashCode()
	{
		int hash = 0;
		hash |= (this.x%4096)<<20; // 12 bits long, in [0;11]
		hash |= this.y<<12; // 8 bits (2^8 = 256) long, in [12;19]
		hash |= (this.z%4096); // 12 bits long, in [20;31]

		return hash;
	}

	@Override public String toString()
	{
		return "[x="+this.x+",y="+this.y+",z="+this.z+"]";
	}


	@Override public int compareTo(@Nonnull BlockCoordinate other)
	{
		int dX = other.x-this.x;
		if(dX != 0)
			return dX;

		int dZ = other.z-this.z;
		if(dZ != 0)
			return dZ;

		int dY = other.y-this.y;
		if(dY != 0)
			return dY;

		return 0;
	}


	// INTERACTION
	@API public BlockCoordinate add(int dX, int dY, int dZ)
	{
		return new BlockCoordinate(this.x+dX, this.y+dY, this.z+dZ);
	}

	@API public BlockCoordinate add(BlockCoordinate other)
	{
		return add(other.x, other.y, other.z);
	}

	@API public BlockCoordinate add(Direction2D dir2d)
	{
		return add(dir2d.dX, 0, dir2d.dZ);
	}


	@API public BlockCoordinate subtract(int dX, int dY, int dZ)
	{
		return add(-dX, -dY, -dZ);
	}

	@API public BlockCoordinate subtract(BlockCoordinate other)
	{
		return add(other.opposite());
	}


	@API public BlockCoordinate opposite()
	{
		return new BlockCoordinate(-this.x, -this.y, -this.z);
	}


	@API public double getDistance(BlockCoordinate other)
	{
		return Math.sqrt(getDistanceSquared(other));
	}

	@API public double getDistanceSquared(BlockCoordinate other)
	{
		int dX = other.x-this.x;
		int dY = other.y-this.y;
		int dZ = other.z-this.z;

		return (dX*dX)+(dY*dY)+(dZ*dZ);
	}

}
