package proxy;


import aceart.schemes.Schemes;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ServerProxy extends CommonProxy {

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
		return samePosition;
	}
	
	@Override
	public void init(final FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void openGuiGenerate() {
		// TODO Auto-generated method stub
		
	}



}
