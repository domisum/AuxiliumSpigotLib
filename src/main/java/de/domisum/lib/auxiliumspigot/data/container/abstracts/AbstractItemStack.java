package de.domisum.lib.auxiliumspigot.data.container.abstracts;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxilium.util.java.annotations.DeserializationNoArgsConstructor;
import de.domisum.lib.auxilium.util.java.annotations.InitByDeserialization;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.List;

/**
 * Class for serializing and deserializing ItemStacks
 */
@API
public class AbstractItemStack
{

	// GAME
	@InitByDeserialization private Material material;
	@InitByDeserialization private int amount;
	@InitByDeserialization private short durability;

	@InitByDeserialization private String displayName;
	@InitByDeserialization private List<String> lore;

	@InitByDeserialization private AbstractPotionEffect potionEffect;


	// INIT
	@DeserializationNoArgsConstructor public AbstractItemStack()
	{

	}

	/**
	 * Creates an AbstractItemStack from the supplied ItemStack
	 *
	 * @param itemStack the ItemStack to generate the AbstractItemStack from
	 */
	@API public AbstractItemStack(ItemStack itemStack)
	{
		this.material = itemStack.getType();
		this.amount = itemStack.getAmount();
		this.durability = itemStack.getDurability();

		if(itemStack.hasItemMeta())
		{
			ItemMeta itemMeta = itemStack.getItemMeta();

			if(itemMeta.hasDisplayName())
				this.displayName = itemMeta.getDisplayName();

			if(itemMeta.hasLore())
				this.lore = itemMeta.getLore();
		}
	}

	/**
	 * Creates an AbstractItemStack with the supplied Material and default values for everything else
	 *
	 * @param material The material of the AbstractItemStack
	 */
	@API public AbstractItemStack(Material material)
	{
		this.material = material;
		this.amount = 1;
	}


	// GETTERS
	@API public Material getMaterial()
	{
		return this.material;
	}

	@API public int getAmount()
	{
		return this.amount;
	}

	@API public short getDurability()
	{
		return this.durability;
	}

	@API public ItemStack get()
	{
		ItemStack itemStack = new ItemStack(this.material, this.amount, this.durability);

		ItemMeta itemMeta = itemStack.getItemMeta();
		if(this.displayName != null)
			itemMeta.setDisplayName(this.displayName);
		if(this.lore != null && !this.lore.isEmpty())
			itemMeta.setLore(this.lore);

		if(this.material == Material.POTION && this.potionEffect != null)
			this.potionEffect.applyTo((PotionMeta) itemMeta);

		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}


	// SETTERS
	@API public void setAmount(int amount)
	{
		this.amount = amount;
	}

}

