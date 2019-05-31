package blocks;

import aceart.schemes.Schemes;
import net.minecraft.block.material.Material;
import railwaytoheaven.RailwayToHeaven;

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

			RailwayToHeaven.proxy.openGuiGenerate();
//			Schemes.proxy.openGuiGenerate();
			
		} catch (Exception e) {

		}
	}
}
