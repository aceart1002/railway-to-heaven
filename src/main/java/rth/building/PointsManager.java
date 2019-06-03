package railway.building;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class PointsManager {

	final BlockPos pathStart, pathFinish;

	public static BlockPos minCorner;
	public static BlockPos maxCorner;
	public static char mainCor, secCor;

	public PointsManager(final BlockPos A, final BlockPos B) {

		pathStart = A;
		pathFinish = B;

		defineCornersAndCoordinates(A,B);
		
	}

	private void defineCornersAndCoordinates(final BlockPos from, final BlockPos to) {

		int minX = Math.min(from.getX(), to.getX());
		int maxX = Math.max(from.getX(), to.getX());
		int minY = Math.min(from.getY(), to.getY());
		int maxY = Math.max(from.getY(), to.getY());
		int minZ = Math.min(from.getZ(), to.getZ());
		int maxZ = Math.max(from.getZ(), to.getZ());

		minCorner = new BlockPos(minX, minY, minZ);
		maxCorner = new BlockPos(maxX, maxY, maxZ);
		
		if(maxX-minX < maxZ-minZ) {
			mainCor = 'z';
			secCor = 'x';
		} else {
			mainCor = 'x';
			secCor = 'z';
		}
	}

	public static void offsetCornerPoint(char whichCor, int dir, int newValue) {
		if(dir == -1) {
			Point min = new Point(minCorner);
			if(min.getCor(whichCor) > newValue) {
				min.setCor(whichCor, newValue);
				minCorner = min.pointToPos();
			}
			
		}
		else if(dir == 1) {
			Point max = new Point(maxCorner);
			if(max.getCor(whichCor) < newValue) {
				max.setCor(whichCor, newValue);
				maxCorner = max.pointToPos();
			}
		}
	}
	
	void expandVolume(int size) {
		Vec3i expander = new Vec3i(size, size, size);
		minCorner = minCorner.subtract(expander);
		maxCorner = maxCorner.add(expander);
	}
	
	public BlockPos calculateDisplacement() {

		int x = Math.abs(pathStart.getX() - minCorner.getX());
		int y = Math.abs(pathStart.getY() - minCorner.getY());
		int z = Math.abs(pathStart.getZ() - minCorner.getZ());

		return new BlockPos(x, y, z);
	}

}
