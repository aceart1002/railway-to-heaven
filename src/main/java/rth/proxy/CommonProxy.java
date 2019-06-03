package proxy;

import aceart.network.ServerTileUpdater;
import aceart.network.UpdateMessage;
import aceart.schemes.Schemes;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import railwaytoheaven.RailwayToHeaven;

public abstract class CommonProxy {

	public static final SimpleNetworkWrapper wrapper = 
			NetworkRegistry.INSTANCE.newSimpleChannel(RailwayToHeaven.MODID + RailwayToHeaven.VERSION);
	;
	private static final byte MESSAGE_ID = 64;

	public abstract boolean isSameController(BlockPos currentPos);
	
	public void preInit(final FMLPreInitializationEvent event) {

//		wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(RailwayToHeaven.MODID + RailwayToHeaven.VERSION);
		wrapper.registerMessage(ServerTileUpdater.class, UpdateMessage.class, 
				MESSAGE_ID, Side.SERVER);

	}
	public void init(final FMLInitializationEvent event) {
		
	}

	public void postInit(final FMLPostInitializationEvent event) {
//		com.github.lunatrius.schematica.proxy.CommonProxy proxy = Schemes.proxy;
//
//		proxy.controller = (Controlling) ModBlocks.CONTROLLER;
//		proxy.saver = (Saving) ModBlocks.SAVER;
//		proxy.updater = new Updater();
//		proxy.printAreaConstructor = new BoxBuilder();
		
	}

	abstract public void openGuiGenerate();
}
