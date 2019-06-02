package items;


import com.github.lunatrius.schematica.client.printer.SchematicPrinter;

import aceart.schemes.Schemes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import proxy.ClientProxy;

public class SchemeBuilding {
	
	@SubscribeEvent
	public void togglePrintingByHeldItem(MouseEvent event) {

		ItemStack item = 
				ClientProxy.MINECRAFT.player.getHeldItem(EnumHand.MAIN_HAND);

		if(SchematicPrinter.INSTANCE.isEnabled()) {
			if(item.getItem() instanceof BuildingHammer) {
				SchematicPrinter.INSTANCE.isPrinting = true;
			} else {
				SchematicPrinter.INSTANCE.isPrinting = false;
			}
		}
	}

}
