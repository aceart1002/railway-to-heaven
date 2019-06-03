package rth.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import rth.building.algorithm.*;
import rth.building.algorithm.directions.Direction;

public class BlocksMaker {

	RailBuilder builder;
	Random rand = new Random();
	private int it = 0;

	private final int goldenDistance;

	Map<BlockPos, IBlockState> blockStates = new HashMap<>();

	final IBlockState air = Blocks.AIR.getDefaultState();
	final IBlockState railpathBlock = Blocks.COBBLESTONE.getDefaultState();
	final IBlockState energyFoundation = Blocks.STONE.getDefaultState();
	final IBlockState simpleRail = Blocks.RAIL.getDefaultState();
	final IBlockState poweredRail = Blocks.GOLDEN_RAIL.getDefaultState();
	final IBlockState energySource = Blocks.REDSTONE_TORCH.getDefaultState();
	final IBlockState glass = Blocks.GLASS.getDefaultState();

	private Tunnel tunnel = new Tunnel();

	public BlocksMaker(RailBuilder builder) {
		this.builder = builder;
		
		int length = 0;
		if(builder.rails instanceof RailShaft)  {
			length = 4;
			}
		else if(builder.rails instanceof RailRoad) {
			length = 7;
			}
		goldenDistance = length;
	}
	public void buildRuntimePoint(Point point, final Direction dir, boolean isTurning) {
		buildRuntimePoint(point, dir.changeCoordinate, isTurning);

	}

	public void buildRuntimePoint(Point p, char changeCor, boolean isTurning) {
		setRail(p, changeCor, isTurning);

		if(!isTurning) {}
		//			tunnel.setTunnel(point, dir, isTurning);

	}

	private void setRail(Point p, char changeCor, boolean isTurning) {
		IBlockState railType;
		if (it > goldenDistance && !isTurning) {
			railType = poweredRail; it = 0;
			addPowerSource(p, changeCor);
		} else {
			railType = simpleRail; it++;
		}

		Point above = p.add(0,1,0);
		blockStates.put(above.pointToPos(), railType);
	}



	private char chooseOppositeCor(char cor) {
		char ch = 'x';
		switch(cor) {
		case 'x': ch = 'z'; break;
		case 'z': ch = 'x'; break;
		}
		return ch;
	}
	private void addPowerSource(Point pos, char currentCor) {
		int offset = (int) Math.pow(-1, rand.nextInt(2) + 1);

		char coordinate = chooseOppositeCor(currentCor);

		Point foundationPos = pos.copy();
		foundationPos.offsetCor(coordinate, offset);

		Point sourcePos = foundationPos.copy();
		sourcePos.offsetCor('y', 1);
 
		blockStates.put(foundationPos.pointToPos(), energyFoundation);
		blockStates.put(sourcePos.pointToPos(), energySource);

	}

	public void buildRailpath(ArrayList<Point> railPoints) {

		for(Point point : railPoints) {
			BlockPos pos = point.pointToPos();
			blockStates.put(pos, railpathBlock);
		}

	}



	class Tunnel {
		int tunnelHeight = 6;
		int tunnelWidth = 3;

		public Tunnel() {

		}

		public Tunnel(int width, int height, boolean centered) {

			tunnelHeight = height;
			tunnelWidth = width;

		}

		void setTunnel(Point p, Direction dir, boolean isTurning) {

			Point next = p.copy();
			char cor = chooseOppositeCor(dir.changeCoordinate);

			Point centralBottom = next.copy();
			Point centralTop = centralBottom.copy();

			centralTop.offsetCor('y', tunnelHeight);
			makePiece(centralBottom, centralTop, true);


			for(int j = 1; j <= 2; j++) {
				int sign = (int) Math.pow(-1, j);

				Point top = centralTop.copy();
				top.offsetCor(cor, sign);

				Point bottom = centralBottom.copy();
				bottom.offsetCor(cor, sign);

				int decY = 0;
				boolean makeHollow = true;
				for(int i = 1; i <= tunnelWidth; i++) {

					if(i == tunnelWidth)
						makeHollow = false;

					makePiece(bottom, top, makeHollow);

					bottom.offsetCor(cor, sign);
					top.offsetCor(cor, sign);
					top.offsetCor('y', -1*decY);

					decY = 1;
				}
			}	
		}

		void makePiece(Point bottom, Point top, boolean makeHollow) {
			blockStates.put(top.pointToPos(), glass);
			blockStates.put(bottom.pointToPos(), glass);

			int dY = top.y - bottom.y;

			IBlockState state;
			if(makeHollow)
				state = air;
			else
				state = glass;

			Point next = bottom.copy();
			for(int i = 0; i < dY; i++) {
				next.offsetCor('y', 1);
				blockStates.put(next.pointToPos(), state);
			}

		}
	}
}


