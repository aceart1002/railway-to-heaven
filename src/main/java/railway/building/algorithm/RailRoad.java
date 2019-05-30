package railway.building.algorithm;

import railway.building.Point;
import railway.building.PointsManager;
import railway.building.RailBuilder;
import railway.building.algorithm.directions.Direction;
import railway.building.algorithm.directions.DirectionsWrapper;

public class RailRoad extends RailWay {

	public DirectionsWrapper directions;

	public Direction horizontalDirection;
	public Direction verticalDirection;

	public boolean isTurning = false;

	public Point currentPoint;

	RailRoad(Point A, Point B, RailBuilder builder) {
		super(A, B, builder);	

		directions = new DirectionsWrapper(diff, this);
	}

	@Override
	public void buildPath() {
		Point next = A;
		try {
			while(moreMaterial()) {
				
				currentPoint = plotPoint(next, horizontalDirection.changeCoordinate, 
						horizontalDirection.changeValue, verticalDirection.changeValue);

				shouldOffsetCornerPoint(next);
				if (reachedFarEnd(next)) {
//					break;
				}
				
				horizontalDirection = horizontalDirection.nextDirection();
				verticalDirection = verticalDirection.nextDirection(isTurning);
				
				builder.setter.buildRuntimePoint(currentPoint, horizontalDirection, isTurning);
				

				next = currentPoint;
				
			}
		} catch (Exception e) {
			System.out.println("loop exception");
		}

	}
	private boolean reachedFarEnd(Point cP) {
		if(cP.getCor(mainCor) == B.getCor(mainCor))
			return true;
		else
			return false;
	}

	void shouldOffsetCornerPoint(Point point) {
		try {
		char hChangeCor = horizontalDirection.changeCoordinate;
		if(hChangeCor == PointsManager.secCor) {
			PointsManager.offsetCornerPoint(hChangeCor, horizontalDirection.changeValue,
					currentPoint.getCor(hChangeCor));
		}

		if(!(verticalDirection == directions.holdingHeight))
			PointsManager.offsetCornerPoint('y', verticalDirection.changeValue, 
					currentPoint.getCor('y'));
		} catch (Exception e) {
			System.out.println("couldn't offset corner point");
		}
	}

	public Point plotPoint(Point previous, char hCor, int hValue, int vValue) {

		Point next = previous.copy();
		next.offsetCor(hCor, hValue);
		next.offsetCor('y',	vValue);

		points.add(next);

		return next;
	}

	public boolean moreMaterial() {
		return getMagnitudesSum() > 0;
	}

	public int getMagnitudesSum() {
		return directions.magnitudesSum();
	}

}




