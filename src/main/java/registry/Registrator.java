package registry;


import aceart.blocks.tiles.ContainsTile;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import railwaytoheaven.RailwayToHeaven;
import registry.ModBlocks;
import registry.Registrable;

@Mod.EventBusSubscriber(modid = RailwayToHeaven.MODID)
public class Registrator {

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {

		for(Registrable unregistered : ModBlocks.BLOCKS) {
			String customName = unregistered.getCustomRegistryName();

			((Block)unregistered).setUnlocalizedName(customName);
			((Block)unregistered).setRegistryName(customName);

		}

		IForgeRegistry<Block> registry = event.getRegistry();
		for(Registrable unregistered : ModBlocks.BLOCKS) {
			registry.register((Block)unregistered);
		}

		for(ContainsTile<?> blockWithTile : ModBlocks.TILES) {
			GameRegistry.registerTileEntity((blockWithTile).getTileEntityClass(), ((Registrable)blockWithTile).getCustomRegistryName());
		}

	}
	

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		
		for(Registrable unregistered : ModItems.ITEMS) {
			String customName = unregistered.getCustomRegistryName();

			((Item)unregistered).setUnlocalizedName(customName);
			((Item)unregistered).setRegistryName(customName);

		}
		
		IForgeRegistry<Item> registry = event.getRegistry();
		for(Registrable unregistered : ModItems.ITEMS) {
			registry.register((Item)unregistered);
		}
		
		for(Registrable block : ModBlocks.BLOCKS) {
			Block unregistered = (Block) block;
			
			ResourceLocation location = unregistered.getRegistryName();
			
			Item itemBlock = new ItemBlock(unregistered).setRegistryName
					(location);
			registry.register(itemBlock);
		}

	}

	@SubscribeEvent
	public static void registerItemBlockModels(ModelRegistryEvent event) {

		for(Registrable block : ModBlocks.BLOCKS) {
			Block unregistered = (Block) block;
			Item item = Item.getItemFromBlock(unregistered);
			String id = unregistered.getRegistryName().toString();
			ModelResourceLocation itemModelLocation = new ModelResourceLocation
					(id, "inventory");

			ModelLoader.setCustomModelResourceLocation(
					item, 0, itemModelLocation);
		}
		
		for(Registrable item : ModItems.ITEMS) {
			Item unregistered = (Item) item;
			String id = unregistered.getRegistryName().toString();
			ModelResourceLocation itemModelLocation = new ModelResourceLocation
					(id, "inventory");

			ModelLoader.setCustomModelResourceLocation(
					unregistered, 0, itemModelLocation);
		}
	}

}
