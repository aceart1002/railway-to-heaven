package proxy;

import aceart.api.Controlling;
import aceart.api.InitObject;
import aceart.api.Saving;
import aceart.network.Updater;
import aceart.schemes.Schemes;
import blocks.BlockSchemeController;
import gui.GuiGenerateRails;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import printer.BoxBuilder;
import registry.ModBlocks;

public class ClientProxy extends proxy.CommonProxy {

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
	}
	@Override
	public void init(final FMLInitializationEvent event) {
		super.init(event);

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
