package rth.building.algorithm;

import java.util.ArrayList;

import rth.building.Point;
import rth.building.RailBuilder;

public abstract class RailWay {
public ArrayList<Point> points = new ArrayList<Point>();
	
	Point A;
	Point B;
	
	Point diff;
	
	double distV;
	double distH;
	
	double length;
	
	char mainCor;
	char secCor;
	
	RailBuilder builder;
	
	RailWay(Point A, Point B, RailBuilder builder) {
		
		this.A = A;
		this.B = B;
		
		diff = B.subtract(A);
		distH = Math.abs(diff.x + diff.z);
		distV = Math.abs(diff.y);
		
		this.builder = builder;
	}
	
	RailWay(Point A, Point B, Point diff, double distH, double distV, RailBuilder builder) {
		this(A, B, builder);
		this.diff = diff;
		this.distV = distV;
		this.distH = distH;
	}
	
	public static RailWay defineRailwayType(Point A, Point B, RailBuilder builder) {
		
		Point diff = B.subtract(A);
		double distH = Math.abs(diff.x) + Math.abs(diff.z);
		double distV = Math.abs(diff.y);
		
		if (distV > distH)
			return new RailShaft(A, B, diff, distH, distV, builder);
		else
			return new RailRoad(A, B, builder);
		
	}
	
	public abstract void buildPath();
	
}
