package tab;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import railwaytoheaven.RailwayToHeaven;

public class SchemesTab extends CreativeTabs {
	
	public SchemesTab() {
		super(RailwayToHeaven.MODID);
		setBackgroundImageName("item_search.png");
	}

	@Override
	public ItemStack getTabIconItem() {
		//return new ItemStack(Item.getItemFromBlock((Block) ModBlocks.RAILS));
		Block b = new Block(Material.CACTUS);
		return new ItemStack(Item.getItemFromBlock((Block) b));
	}
	
	@Override
	public boolean hasSearchBar() {
		return false;
	}

}
