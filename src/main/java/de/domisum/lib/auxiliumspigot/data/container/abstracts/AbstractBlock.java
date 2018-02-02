package de.domisum.lib.auxiliumspigot.data.container.abstracts;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxilium.util.java.annotations.DeserializationNoArgsConstructor;
import de.domisum.lib.auxilium.util.java.annotations.InitByDeserialization;
import de.domisum.lib.auxiliumspigot.data.container.block.BlockCoordinate;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
 * Class for serializing and deserializing blocks
 */
@API
public class AbstractBlock
{

	// PROPERTIES
	@InitByDeserialization public final Material material;
	@InitByDeserialization public final byte data;


	/*
	// INITIALIZATION
	*/
	@DeserializationNoArgsConstructor public AbstractBlock()
	{
		this.material = null;
		this.data = 0;
	}

	/**
	 * @param material The material of the block
	 * @param data     the subId of the block
	 */
	@API public AbstractBlock(Material material, byte data)
	{
		this.material = material;
		this.data = data;
	}


	@API public AbstractBlock(World world, BlockCoordinate blockCoordinate)
	{
		this(world.getBlockAt(blockCoordinate.x, blockCoordinate.y, blockCoordinate.z));
	}

	@API public AbstractBlock(World world, int x, int y, int z)
	{
		this(world.getBlockAt(x, y, z));
	}

	@API public AbstractBlock(Block block)
	{
		this.material = block.getType();
		//noinspection deprecation
		this.data = block.getData();
	}

}
