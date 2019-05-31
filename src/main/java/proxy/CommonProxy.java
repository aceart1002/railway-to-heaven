package proxy;

import aceart.network.ServerMessageProcessor;
import aceart.network.UpdateMessage;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import railwaytoheaven.RailwayToHeaven;

public abstract class CommonProxy {

	 public static SimpleNetworkWrapper wrapper;
	 private static final byte MESSAGE_ID = 64;
		
	public void preInit(final FMLPreInitializationEvent event) {

		wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(RailwayToHeaven.MODID + RailwayToHeaven.VERSION);
		wrapper.registerMessage(ServerMessageProcessor.class, UpdateMessage.class, 
				MESSAGE_ID, Side.SERVER);

	}
	public void init(final FMLInitializationEvent event) {

	}
	
	public void postInit(final FMLPostInitializationEvent event) {
		
	}

	  abstract public void openGuiGenerate();
}
