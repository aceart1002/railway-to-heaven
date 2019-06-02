package aceart.network;

import com.github.lunatrius.schematica.proxy.ClientProxy;
import com.github.lunatrius.schematica.proxy.CommonProxy;

import aceart.schemes.Schemes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerMessageProcessor implements IMessageHandler<UpdateMessage, IMessage>{

	@Override
	public IMessage onMessage(UpdateMessage message, MessageContext ctx) {

		EntityPlayerMP sendingPlayer = ctx.getServerHandler().player;

		final WorldServer playerWorldServer = sendingPlayer.getServerWorld();
		playerWorldServer.addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message);
				
			}
		});

		return null;
	}
	
	public void processMessage(UpdateMessage message) {
		com.github.lunatrius.schematica.proxy.CommonProxy proxy = Schemes.proxy;
		int op = message.operation;
		
		if(op == 1)
			proxy.saver.setSavedPoint(message.getPoint());
		else if(op == 2) {
			
			proxy.controller.switchMode();
			proxy.controller.setSchemeName(message.schemeName);
			proxy.controller.setRotationRender(message.rotationRender);
			proxy.controller.setDisplacement(message.getDisplacement());
			//Schemes.proxy.controller.setSchematic(message.schemeTag);
		}
		else if (op == 3) {
			//Schemes.proxy.controller.setSchematic(message.schemeTag);
		}
		else if(op == 4) {
			ClientProxy.controller.switchFlip();
		}
		else if (op == 5) {
			ClientProxy.controller.switchRotations();
		}
	}
	
}
