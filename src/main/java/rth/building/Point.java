package rth.building;

import net.minecraft.util.math.BlockPos;

public class Point {

	public int x;
	public int y;
	public int z;

	public Point() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Point(int x, int y, int z) {

		this.x = x;
		this.y = y;
		this.z = z;

	}
	
	public Point(BlockPos pos) {
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
	}

	public Point copy() {
		return new Point(x,y,z);
	}
	
	public int getCor(int n) {

		int out;
		switch (n) {

		case 1: out = this.x; break;
		case 2: out = this.y; break;
		case 3: out = this.z; break;
		default: out = this.x; break;
		}

		return out;
	}

	public int getCor(char ch) {
		int out;
		switch (ch) {

		case 'x': out = this.x; break;
		case 'y': out = this.y; break;
		case 'z': out = this.z; break;
		default: out = this.x; break;
		}

		return out;
	}
	
	public void setCor(int whichCor, int value) {

		switch (whichCor) {

		case 1: this.x = value; break;
		case 2: this.y = value; break;
		case 3: this.z = value; break;
		default:; break;
		}

	}
	
	public void setCor(char whichCor, int value) {

		switch (whichCor) {

		case 'x': this.x = value; break;
		case 'y': this.y = value; break;
		case 'z': this.z = value; break;
		default:; break;
		}

	}
	
	public void offsetCor(char whichCor, int value) {
		switch (whichCor) {

		case 'x': this.x = x + value; break;
		case 'y': this.y = y + value; break;
		case 'z': this.z = z + value; break;
		default:; break;
		}
	}

	public Point add(Point B) {

		int x = this.x + B.x;
		int y = this.y + B.y;
		int z = this.z + B.z;

		return new Point(x, y, z);
	}
	
	public Point add(int x, int y, int z) {
		int x1 = this.x + x;
		int y1 = this.y + y;
		int z1 = this.z + z;

		return new Point(x1, y1, z1);
	}

	public Point subtract(Point B) {

		int x = this.x - B.x;
		int y = this.y - B.y;
		int z = this.z - B.z;

		return new Point(x, y, z);
	}
	
	public BlockPos pointToPos() {
		return new BlockPos(x,y,z);
	}
	
}
