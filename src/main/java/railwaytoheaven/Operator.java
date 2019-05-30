package railwaytoheaven;

import java.io.File;
import java.util.Map;

import com.github.lunatrius.core.util.math.MBlockPos;
import com.github.lunatrius.schematica.handler.ConfigurationHandler;
import com.github.lunatrius.schematica.proxy.ClientProxy;
import com.github.lunatrius.schematica.world.schematic.SchematicFormat;

import aceart.schemes.Schemes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import railway.building.RailBuilder;

public class Operator {

	public void makeRailway() {
		
	}
	
	public static void operate(BlockPos A, BlockPos B, String fileName) {
		try {
			Minecraft game = Minecraft.getMinecraft();
			
			RailBuilder rails = new RailBuilder(A, B);
			rails.buildRailway(); 
			Map<BlockPos, IBlockState> blockStates = rails.getBlockStates();
			BlockPos displace = rails.getDisplacement();
			
			A = rails.getMinCorner();
			B = rails.getMaxCorner();
			
			//ClientProxy.controller.setDisplacement(displace);
			
			MBlockPos disp = new MBlockPos(displace.getX(), displace.getY(), displace.getZ());
			ClientProxy.displace = disp;
			
			String format = SchematicFormat.FORMAT_DEFAULT;
			File dir = ConfigurationHandler.schematicDirectory;

			fileName += ".schematic";

			Schemes.proxy.saveSchematic(game.player, dir, fileName, game.world, format, A, B, 
					blockStates);
			Schemes.proxy.loadSchematicFromFile(game.player, dir, fileName); 

		} catch (Exception e) {
			System.out.println("exceptoin");
		}

	}
}
