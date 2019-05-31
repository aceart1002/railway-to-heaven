package proxy;


import blocks.BlockSchemeController;
import gui.GuiGenerateRails;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends proxy.CommonProxy {

	public static final Minecraft MINECRAFT = Minecraft.getMinecraft();

	@Override
	public void init(final FMLInitializationEvent event) {
		super.init(event);

	}

	@Override
	public void openGuiGenerate() {
		//try {
			MINECRAFT.displayGuiScreen(new GuiGenerateRails(MINECRAFT.currentScreen));
		//} catch (Exception e) {

		//}
	}

}
