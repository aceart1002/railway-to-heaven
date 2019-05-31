package tab;

import com.github.lunatrius.schematica.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import registry.ModBlocks;

public class SchemesTab extends CreativeTabs {
	
	public SchemesTab() {
		super(Reference.MODID);
		setBackgroundImageName("item_search.png");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock((Block) ModBlocks.CONTROLLER));
	}
	
	@Override
	public boolean hasSearchBar() {
		return true;
	}
}
