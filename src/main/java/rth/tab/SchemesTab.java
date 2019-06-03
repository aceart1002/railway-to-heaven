package rth.tab;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rth.RailwayToHeaven;
import rth.registry.ModBlocks;

public class SchemesTab extends CreativeTabs {
	
	public SchemesTab() {
		super(RailwayToHeaven.MODID);
		setBackgroundImageName("item_search.png");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock((Block) ModBlocks.RAILS));
	}
	
	@Override
	public boolean hasSearchBar() {
		return false;
	}

}
