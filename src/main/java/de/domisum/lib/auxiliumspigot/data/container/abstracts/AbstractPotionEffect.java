package de.domisum.lib.auxiliumspigot.data.container.abstracts;

import de.domisum.lib.auxilium.util.java.annotations.API;
import de.domisum.lib.auxilium.util.java.annotations.DeserializationNoArgsConstructor;
import de.domisum.lib.auxilium.util.java.annotations.InitByDeserialization;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@API
public class AbstractPotionEffect
{

	// PROPERTIES
	@InitByDeserialization private String type;
	@InitByDeserialization private int amplifier;
	@InitByDeserialization private int duration;

	// STATUS
	private PotionEffectType type_;


	// INIT
	@DeserializationNoArgsConstructor public AbstractPotionEffect()
	{

	}


	// GETTERS
	private PotionEffectType getType()
	{
		if(this.type_ == null)
			determinePotionEffectType();

		return this.type_;
	}

	private void determinePotionEffectType()
	{
		this.type_ = PotionEffectType.getByName(this.type);

		if(this.type == null)
			// noinspection ConstantConditions // type is not always null because of deserialized objects
			throw new IllegalArgumentException("The potion effect type '"+this.type+"' does not exist");
	}


	// APPLICATION
	@API public void applyTo(PotionMeta potionMeta)
	{
		PotionEffect effect = new PotionEffect(getType(), this.duration, this.amplifier);
		potionMeta.addCustomEffect(effect, true);
	}

}
