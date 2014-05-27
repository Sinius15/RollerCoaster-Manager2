package com.sinius15.rcm.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.sinius15.rcm.Lang;
import com.sinius15.rcm.Main;
import com.sinius15.rcm.RCMCommand;
import com.sinius15.rcm.RollerPoint;
import com.sinius15.rcm.RollerPoint.Cart;

public class SetCartCommand extends RCMCommand{

	public SetCartCommand(Main parent) {
		super(parent);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length < 2){
			sender.sendMessage(ChatColor.RED + Lang.HELP_SET_CART);
			return;
		}
		RollerPoint point = parent.data.getPointByName(args[0]);
		if(point == null){
			sender.sendMessage(ChatColor.RED + Lang.THIS_POINT_DOES_NOT_EXIST);
			return;
		}
		Cart cart = Cart.getCart(args[1]);
		
		if(cart == null){
			sender.sendMessage(ChatColor.RED + Lang.HELP_SET_CART);
			return;
		}
		
		point.whatToSpawn = cart;
		sender.sendMessage(ChatColor.YELLOW + point.name + " " + Lang.NOW_SPAWNS + " " +  cart.toString() + "s");
		
	}

	@Override
	public String getName() {
		return "setCart";
	}

	@Override
	public String help() {
		return Lang.HELP_SET_CART;
	}

	@Override
	public boolean onlyPlayer() {
		return false;
	}

}
