package aceart.network;


import aceart.api.ServerUpdater;
import net.minecraft.util.math.BlockPos;
import proxy.ClientProxy;
import proxy.CommonProxy;
import railwaytoheaven.RailwayToHeaven;

public class Updater implements ServerUpdater {

	@Override
	public void updateServer(BlockPos savePoint, String name, int rotationRender, BlockPos displacement,
			int operation) {
		
		UpdateMessage message = new UpdateMessage(savePoint, name,
				rotationRender, displacement, operation);
		ClientProxy.wrapper.sendToServer(message);
		
	}

}
