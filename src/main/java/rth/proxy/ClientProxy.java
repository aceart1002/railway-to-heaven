package rth.proxy;

import aceart.api.Controlling;
import aceart.api.InitObject;
import aceart.api.Saving;
import aceart.schemes.Schemes;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import rth.RailwayToHeaven;
import rth.gui.GuiGenerateRails;
import rth.items.SchemeBuilding;
import rth.network.ServerTileUpdater;
import rth.network.UpdateMessage;
import rth.network.Updater;
import rth.printer.BoxBuilder;
import rth.registry.ModBlocks;
import rth.registry.Registrator;

public class ClientProxy extends rth.proxy.CommonProxy {

	public static final Minecraft MINECRAFT = Minecraft.getMinecraft();
	
	
	
	@Override
	public boolean isSameController(BlockPos currentPos) {
		
		BlockPos previousPos; 
		if(Schemes.proxy.controller != null) {
			previousPos = Schemes.proxy.controller.getPosition();
			if(previousPos == null)
				return false;
		} else 
			return false;

		int x1 = currentPos.getX();
		int y1 = currentPos.getY();
		int z1 = currentPos.getZ();

		int x2 = previousPos.getX();
		int y2 = previousPos.getY();
		int z2 = previousPos.getZ();

		boolean samePosition = (x1 == x2 && y1 == y2 && z1 == z2) ? true : false;

		if(samePosition && 
				com.github.lunatrius.schematica.proxy.ClientProxy.schematic != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		InitObject initSchemes = new InitObject(new BoxBuilder(), (Saving) ModBlocks.SAVER,
				(Controlling) ModBlocks.CONTROLLER, new Updater());
		
		//Registrator.registerItemBlockModels();
		
	}
	@Override
	public void init(final FMLInitializationEvent event) {
		super.init(event);
		
		MinecraftForge.EVENT_BUS.register(new SchemeBuilding());
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		
	}
	
	@Override
	public void openGuiGenerate() {
		//try {
			MINECRAFT.displayGuiScreen(new GuiGenerateRails(MINECRAFT.currentScreen));
		//} catch (Exception e) {

		//}
	}
	
	

}
