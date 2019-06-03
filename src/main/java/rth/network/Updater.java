package rth.network;


import aceart.api.ServerUpdater;
import net.minecraft.util.math.BlockPos;
import rth.RailwayToHeaven;
import rth.proxy.ClientProxy;
import rth.proxy.CommonProxy;

public class Updater implements ServerUpdater {

	@Override
	public void updateServer(BlockPos savePoint, String name, int rotationRender, BlockPos displacement,
			int operation) {
		
		UpdateMessage message = new UpdateMessage(savePoint, name,
				rotationRender, displacement, operation);
		ClientProxy.wrapper.sendToServer(message);
		
	}

}
