package com.sinius15.rcm;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.entity.minecart.StorageMinecart;
	
public class RollerPoint implements Comparable<RollerPoint>{

	
	public static final int START = 0;
	public static final int END = 1;
	public static final int CART_NORMAL = 0;
	public static final int CART_CHEST = 1;
	public static final int CART_FURNACE = 2;
	public static final int CART_TNT = 3;
	public static final int CART_HOPPER = 4;
	
	public int x, y, z;
	public String world;
	public String name;
	public int type;
	public int whatToSpawn = CART_NORMAL;
	
	public RollerPoint(){}
	
	public RollerPoint(int x, int y, int z, String world, String name, int type, int whatToSpawn){
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
		this.name = name;
		this.type = type;
		this.whatToSpawn = whatToSpawn;

	}
	
	public RollerPoint(String x, String y, String z, String world, String name, String type, String whatToSpawn){
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
		this.z = Integer.parseInt(z);
		this.world = world;
		this.name = name;
		this.type = Integer.parseInt(type);
		this.whatToSpawn = Integer.parseInt(whatToSpawn);
	}
	
	public Location getLoc(){
		return new Location(Bukkit.getServer().getWorld(world), x + 0.5, y + 0.5, z + 0.5);
	}
	
	public Minecart spawnCart(){
		Location loc = this.getLoc();
		Minecart m;
		switch(whatToSpawn){
			case CART_NORMAL: m = loc.getWorld().spawn(loc, Minecart.class); break;
			case CART_CHEST: m = loc.getWorld().spawn(loc, StorageMinecart.class); break;
			case CART_FURNACE: m = loc.getWorld().spawn(loc, PoweredMinecart.class); break;
			case CART_TNT: m = loc.getWorld().spawn(loc, ExplosiveMinecart.class); break;
			case CART_HOPPER: m = loc.getWorld().spawn(loc, HopperMinecart.class); break;
			default : m = loc.getWorld().spawn(loc, Minecart.class); break;
		}
		return m;
	}

	@Override
	public String toString() {
		return "RollerPoint [x=" + x + ", y=" + y + ", z=" + z + ", world="
				+ world + ", name=" + name + ", type=" + type
				+ ", whatToSpawn=" + whatToSpawn + "]";
	}

	/**
	 * @return 0 if they do not share same coordinates, returns 1 if they share the same coordinates
	 */
	@Override
	public int compareTo(RollerPoint t) {
		return (x == t.x && y == t.y && z == t.z && world.equals(t.world)) ? 1 : 0;
	}
	
	
	
	
	
}
