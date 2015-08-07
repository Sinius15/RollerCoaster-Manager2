package com.sinius15.rcm;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Data {

	public List<RollerPoint> points = new ArrayList<RollerPoint>();
	private JavaPlugin parent;
	
	public Data(JavaPlugin parant){
		this.parent = parant;
		parant.getConfig().addDefault("rollerPointAmount", 0);
	}
	
	public void loadPoints(){
		parent.reloadConfig();
		
		int i = 0;
		while(parent.getConfig().getString("point." + i + ".x") != null){
			points.add(new RollerPoint(
					new Location(null, 
							parent.getConfig().getInt("point." + i + ".x"), 
							parent.getConfig().getInt("point." + i + ".y"), 
							parent.getConfig().getInt("point." + i + ".z")
					),
					parent.getConfig().getString("point." + i + ".world"), 
					parent.getConfig().getString("point." + i + ".name"), 
					parent.getConfig().getInt("point." + i + ".type"), 
					parent.getConfig().getInt("point." + i + ".whatToSpawn")));
			i++;
		}
		
	}
	
	public void saveConfig(){
		
		for(String key : parent.getConfig().getKeys(true)){
			parent.getConfig().set(key, null);
		}
		
		int i = 0;
		for(RollerPoint p : points){
			parent.getConfig().set("point." + i , "");
			parent.getConfig().set("point." + i + ".name", p.name);
			parent.getConfig().set("point." + i + ".type", p.type);
			parent.getConfig().set("point." + i + ".world", p.world);
			parent.getConfig().set("point." + i + ".x", p.x);
			parent.getConfig().set("point." + i + ".y", p.y);
			parent.getConfig().set("point." + i + ".z", p.z);
			parent.getConfig().set("point." + i + ".whatToSpawn", p.whatToSpawn.toInt());
			i++;
		}
		parent.saveConfig();
	}
	
	public boolean addPoint(Location loc, String name, int type){
		for(RollerPoint p : points){
			if(p.name.equals(name)){
				return false;
			}
		}
		RollerPoint p = new RollerPoint(loc, loc.getWorld().getName(), name, type, RollerPoint.Cart.EMPTY.toInt());
		points.add(p);
		
		return true;
	}
	
	public boolean removePoint(String name){
		int i = 0;
		for(RollerPoint p : points){
			if(p.name.equals(name)){
				points.remove(i);
				return true;
			}
			i++;
		}
		return false;
	}
	
	public RollerPoint getPointByName(String name){
		for(RollerPoint p : points){
			if(p.name.equals(name)){
				return p;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String o = "Data [points=";
		for (RollerPoint p : points) 
			o+=p.toString();
		return o + "]";
	}
	
	
}
