package blocks;

import aceart.blocks.BlockSchemeController;
import aceart.schemes.Schemes;
import net.minecraft.block.material.Material;

public class BlockRailsBuilder extends BlockSchemeController {

	public BlockRailsBuilder(Material material, String regName) {
		super(material, regName);
	}

	@Override
	protected boolean displaceOrNot() {
		return true;
	}

	@Override
	public void openLoadingGui() {
		try {
			//ClientProxy.MINECRAFT.displayGuiScreen(new 
			//		GuiGenerateRails(ClientProxy.MINECRAFT.currentScreen));
//			RailwayToHeaven.proxy.openGuiGenerate();
			Schemes.proxy.openGuiGenerate();
		} catch (Exception e) {

		}
	}
}
