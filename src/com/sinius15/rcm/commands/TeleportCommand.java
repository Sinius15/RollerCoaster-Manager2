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

public class TeleportCommand extends RCMCommand{

	public TeleportCommand(Main parent) {
		super(parent);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length < 1){
			sender.sendMessage(ChatColor.RED + help());
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
		Player who = null;
		if(args.length > 1){
			String playerName = args[1];
			who = Bukkit.getPlayer(playerName);
		}else{
			if(sender instanceof Player)
				who = (Player) sender;
		}
		if(who == null){
			sender.sendMessage(ChatColor.YELLOW + Lang.COULD_NOT_FIND_PLAYER);
			return;
		}
		who.teleport(point.getLoc());
	}

	@Override
	public String getName() {
		return "teleport";
	}

	@Override
	public String help() {
		return Lang.HELP_TELEPORT;
	}

	@Override
	public boolean onlyPlayer() {
		return false;
	}

}
