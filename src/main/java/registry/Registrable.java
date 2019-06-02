package registry;

import net.minecraft.block.Block;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface Registrable<T  extends IForgeRegistryEntry<T>> {
	
	
	String getCustomRegistryName();
	
	void setCustomRegistryName(String newName);
	
	public Object setUnlocalizedName(String name);
	
	public T setRegistryName(String name);
	
}
