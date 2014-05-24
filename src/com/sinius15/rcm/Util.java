package com.sinius15.rcm;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

public class Util {

	public static Block getTargetBlock(Player player, int range) {
		BlockIterator bit = new BlockIterator(player, range);
		Block next = null;
		while(bit.hasNext()){
			next= bit.next();
			if(!next.isEmpty()){
				return next;
		    }
		}
		return null;
	}
	
	public static String capitalize(String line){
	  return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
}
