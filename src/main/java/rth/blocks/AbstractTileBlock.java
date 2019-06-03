package rth.blocks;

import javax.annotation.Nullable;

import aceart.schemes.Schemes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import rth.RailwayToHeaven;
import rth.registry.Registrable;
import rth.tiles.ContainsTile;

public abstract class AbstractTileBlock<T extends TileEntity> extends Block 
implements Registrable, ContainsTile {
	
	String name;
	
	//public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public AbstractTileBlock(Material material, String name) {
		super(material);
		
		this.name = name;
		
		setCreativeTab(RailwayToHeaven.SCHEMES);
		
	}

	@Override
	public String getCustomRegistryName() {
		return name;
	}

	@Override
	public void setCustomRegistryName(String newName) {
		name = newName;
		
	}
	
	public abstract Class<T> getTileEntityClass();

	public T getTileEntity(IBlockAccess world, BlockPos position) {

		return (T) world.getTileEntity(position);
	}

	@Override
	public boolean hasTileEntity(IBlockState blockState) {

		return true;
	}

	@Nullable
	@Override
	public abstract T createTileEntity(World world, IBlockState blockState);
	
//	 @Override
//	  protected BlockStateContainer createBlockState()
//	  {
//	    return new BlockStateContainer(this, new IProperty[] {PROPERTYFACING});
//	  }
//	 
//	 @Override
//	  public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
//	  {
//	    // find the quadrant the player is facing
//	    EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
//
//	    return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing);
//	  }
}
