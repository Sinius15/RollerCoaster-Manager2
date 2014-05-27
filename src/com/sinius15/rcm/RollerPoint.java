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
	
	public int x, y, z;
	public String world;
	public String name;
	public int type;
	public Cart whatToSpawn = Cart.EMPTY;
	
	public RollerPoint(){}
	
	public RollerPoint(int x, int y, int z, String world, String name, int type, int whatToSpawn){
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
		this.name = name;
		this.type = type;
		this.whatToSpawn = Cart.getCart(whatToSpawn);

	}
	
	public RollerPoint(String x, String y, String z, String world, String name, String type, String whatToSpawn){
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
		this.z = Integer.parseInt(z);
		this.world = world;
		this.name = name;
		this.type = Integer.parseInt(type);
		this.whatToSpawn = Cart.getCart(Integer.parseInt(whatToSpawn));
	}
	
	public Location getLoc(){
		return new Location(Bukkit.getServer().getWorld(world), x + 0.5, y + 0.5, z + 0.5);
	}
	
	public Minecart spawnCart(){
		Location loc = this.getLoc();
		Minecart m;
		switch(whatToSpawn){
			case EMPTY: m = loc.getWorld().spawn(loc, Minecart.class); break;
			case CHEST: m = loc.getWorld().spawn(loc, StorageMinecart.class); break;
			case FURNACE: m = loc.getWorld().spawn(loc, PoweredMinecart.class); break;
			case TNT: m = loc.getWorld().spawn(loc, ExplosiveMinecart.class); break;
			case HOPPER: m = loc.getWorld().spawn(loc, HopperMinecart.class); break;
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
	
	public enum Cart{
		EMPTY, CHEST, FURNACE, TNT, HOPPER;
		
		public String toString(){
			switch (this) {
				case EMPTY: 
					return "minecart";
				case CHEST:
					return "chestCart";
				case FURNACE:
					return "furnaceCart";
				case HOPPER:
					return "hopperCart";
				case TNT:
					return "tntCart";
				default:
					return null;
			}
		}
		
		public int toInt(){
			switch (this) {
				case EMPTY: 
					return 0;
				case CHEST:
					return 1;
				case FURNACE:
					return 2;
				case HOPPER:
					return 4;
				case TNT:
					return 3;
				default:
					return -1;
			}
		}
		public static Cart getCart(int type){
			switch (type) {
				case 0: 
					return EMPTY;
				case 1:
					return CHEST;
				case 2:
					return FURNACE;
				case 4:
					return HOPPER;
				case 3:
					return TNT;
				default:
					return null;
			}
		}
		public static Cart getCart(String type){
			type = type.toLowerCase();
			switch (type) {
				case "minecart": 
					return EMPTY;
				case "chestcart":
					return CHEST;
				case "furnacecart":
					return FURNACE;
				case "hoppercart":
					return HOPPER;
				case "tntcart":
					return TNT;
				default:
					return null;
			}
		}
	}
	
	
	
	
}
