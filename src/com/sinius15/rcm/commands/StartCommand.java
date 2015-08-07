package com.sinius15.rcm.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sinius15.rcm.Lang;
import com.sinius15.rcm.Main;
import com.sinius15.rcm.RCMCommand;
import com.sinius15.rcm.RollerPoint;

public class StartCommand extends RCMCommand{

	public StartCommand(Main parent) {
		super(parent);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length < 1){
			sender.sendMessage(ChatColor.RED + Lang.HELP_START);
			return;
		}
		RollerPoint point = parent.data.getPointByName(args[0]);
		if(point == null){
			sender.sendMessage(ChatColor.RED + Lang.THIS_POINT_DOES_NOT_EXIST);
			return;
		}
		World world = Bukkit.getWorld(point.world);
		if(world == null){
			sender.sendMessage(ChatColor.RED + Lang.UNEXISTING_WORLD);
			return;
		}
		if(args.length > 1){
			String playerName = args[1];
			Player player = Bukkit.getPlayer(playerName);
			if(playerName.equals(Lang.ME) && (player instanceof Player))
				player = (Player) sender;
			if(player != null && player.isOnline())
				point.spawnCart().setPassenger(player);
		}else{
			point.spawnCart();
		}
	}

	@Override
	public String getName() {
		return "start";
	}

	@Override
	public String help() {
		return Lang.HELP_START;
	}

	@Override
	public boolean onlyPlayer() {
		return false;
	}



}
