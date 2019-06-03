package rth.items;

import net.minecraft.item.Item;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rth.RailwayToHeaven;
import rth.registry.Registrable;

public class BuildingHammer extends Item implements Registrable {
	
	String customName;
	
	public BuildingHammer(String name) {
		customName = name;
		
		setCreativeTab(RailwayToHeaven.SCHEMES);
		
		//TODO: check max stack size
	}
	
	@Override
	public void setCustomRegistryName(String newName) {
		customName = newName;
		
	}
	
	@Override
	public String getCustomRegistryName() {
		return customName;
	}
	
	
}
