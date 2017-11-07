package de.domisum.lib.auxiliumspigot.util;

import de.domisum.lib.auxilium.util.java.annotations.API;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@API
public class ReflectionUtil
{

	@API public static void setDeclaredFieldValue(Class<?> clazz, Object object, String fieldName, Object value)
	{
		try
		{
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);

			field.set(object, value);
		}
		catch(NoSuchFieldException|IllegalArgumentException|SecurityException|IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	@API public static void setDeclaredFieldValue(Object object, String fieldName, Object value)
	{
		setDeclaredFieldValue(object.getClass(), object, fieldName, value);
	}


	@API public static Object getDeclaredFieldValue(Object object, String fieldName)
	{
		return getDeclaredFieldValue(object.getClass(), object, fieldName);
	}

	@API public static Object getDeclaredFieldValue(Class<?> clazz, Object object, String fieldName)
	{
		try
		{
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);

			return field.get(object);
		}
		catch(NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@API public static Object getFieldValue(Class<?> clazz, Object object, String fieldName)
	{
		try
		{
			Field field = clazz.getField(fieldName);
			// field.setAccessible(true);
			// this only works for public attributes, use getDeclaredFieldValue instead

			return field.get(object);
		}
		catch(NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@API public static Object getFieldValue(Object object, String fieldName)
	{
		return getFieldValue(object.getClass(), object, fieldName);
	}


	@API public static Object newInstance(Class<?> clazz, Object... args)
	{
		Class<?>[] classes = new Class<?>[args.length];
		for(int i = 0; i < args.length; i++)
			classes[i] = args[i].getClass();

		try
		{
			return clazz.getConstructor(classes).newInstance(args);
		}
		catch(InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException|NoSuchMethodException|SecurityException e)
		{
			e.printStackTrace();
		}

		return null;
	}


	@API public static String getNMSPath()
	{
		return "net.minecraft.server."+getVersion()+".";
	}

	@API public static String getCBPath()
	{
		return "org.bukkit.craftbukkit."+getVersion()+".";
	}

	@API public static String getVersion()
	{
		// from Skionz (https://bukkit.org/threads/basic-reflection-tutorial.329127/)
		return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	}

}
