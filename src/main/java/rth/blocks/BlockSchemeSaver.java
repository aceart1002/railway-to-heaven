package blocks;

import com.github.lunatrius.schematica.proxy.ClientProxy;

import aceart.api.Saving;
import aceart.blocks.tiles.TileSavePoints;
import aceart.schemes.Schemes;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import railwaytoheaven.RailwayToHeaven;

public class BlockSchemeSaver extends AbstractTileBlock<TileSavePoints> implements Saving {

	BlockData currentData;

	public BlockSchemeSaver(Material material, String name) {
		super(material, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos position, IBlockState blockState, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

		TileSavePoints currentTile = getTileEntity(world, position);

		currentData = new BlockData(position, currentTile, world);
		Schemes.proxy.saver = this;

		if (world.isRemote) {

			Schemes.proxy.openGuiSave();
		}

		return true;
	}

	private boolean isSameSaver(BlockPos currentPos) {

		BlockPos previousPos; 
		if(Schemes.proxy.saver != null) {
			previousPos = Schemes.proxy.saver.getCurrentPosition();
			if(previousPos == null)
				return false;
		} else 
			return false;

		int x1 = currentPos.getX();
		int y1 = currentPos.getY();
		int z1 = currentPos.getZ();

		int x2 = previousPos.getX();
		int y2 = previousPos.getY();
		int z2 = previousPos.getZ();

		return new Boolean(x1 == x2 && y1 == y2 && z1 == z2) ? true : false;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		//		TileSavePoints tile = getTileEntity(worldIn, pos);
		//		tile.setPoint(pos);
		if(!worldIn.isRemote) {
			currentData = new BlockData(pos, getTileEntity(worldIn, pos), worldIn);
			BlockPos position = new BlockPos(pos);
			setSavedPoint(position);

		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

	}
	
	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
		if(worldIn.isRemote) {
			try {
				if(isSameSaver(pos))
					ClientProxy.isRenderingGuide = false;
			} catch (Exception e) {

			}
		}

		super.onBlockDestroyedByExplosion(worldIn, pos, explosionIn);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		if(worldIn.isRemote) {
			try {
				if(isSameSaver(pos))
					ClientProxy.isRenderingGuide = false;
			} catch (Exception e) {

			}
		}

		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

//		if(worldIn.isRemote) {
//			try {
//				if(isSameSaver(pos))
//					ClientProxy.isRenderingGuide = false;
//			} catch (Exception e) {
//
//			}
//		}
		super.breakBlock(worldIn, pos, state);
	}

	public BlockPos getCurrentPosition() {
		BlockPos pos = currentData.getCurrentPosition();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		return new BlockPos(x,y,z);
	}

	public BlockPos getSavedPoint() {
		return currentData.getTile().getPoint();
	}

	public void setSavedPoint(BlockPos newPoint) {

		World world = currentData.currentWorld;
		BlockPos pos = currentData.currentPos;

		currentData.getTile().setPoint(newPoint);

		IBlockState state = world.getBlockState(pos); 
		world.notifyBlockUpdate(pos, state, state, 3);
	}

	@Override
	public Class<TileSavePoints> getTileEntityClass() {

		return TileSavePoints.class;
	}

	@Override
	public TileSavePoints createTileEntity(World world, IBlockState blockState) {

		return new TileSavePoints();
	}

	private class BlockData  {

		final private BlockPos currentPos;
		final private TileSavePoints currentTile;
		final private World currentWorld;

		public BlockData(BlockPos pos, TileSavePoints currentTile, World world) {

			currentPos = pos;
			this.currentTile = currentTile;
			currentWorld = world;
		}

		private BlockPos getCurrentPosition() {
			return currentPos;
		}

		private TileSavePoints getTile() {
			return currentTile;
		}

	}

}