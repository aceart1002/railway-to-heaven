package rth.building;

import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import rth.building.algorithm.RailWay;

public class RailBuilder {
	
	public PointsManager points;
	public BlocksMaker setter;
	public RailWay rails;
	
	public Map<BlockPos, IBlockState> getBlockStates() {
		return setter.blockStates;
	}
	
	public BlockPos getDisplacement() {
		return points.calculateDisplacement();
	}
	
	public RailBuilder(BlockPos A, BlockPos B) {
		points = new PointsManager(A, B);
	}
	
	public BlockPos getMinCorner() {
		return PointsManager.minCorner;
	}
	
	public BlockPos getMaxCorner() {
		return PointsManager.maxCorner;
	}
	
	public void buildRailway() {
		
		
		rails = RailWay.defineRailwayType(new Point(points.pathStart),
				new Point(points.pathFinish), this);
		
		setter = new BlocksMaker(this);
		rails.buildPath();
		
		setter.buildRailpath(rails.points);
		
		points.expandVolume(2);
	}
}
