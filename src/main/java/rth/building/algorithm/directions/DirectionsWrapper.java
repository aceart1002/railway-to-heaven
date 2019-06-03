package rth.building.algorithm.directions;

import java.util.Random;

import rth.building.Point;
import rth.building.PointsManager;
import rth.building.algorithm.RailRoad;

public class DirectionsWrapper {

	int dMain;
	int dSec;
	int dY;

	public Direction turningLeft;
	public Direction turningRight;
	public Direction movingForward;
	
	public Direction goingUp;
	public Direction holdingHeight;
	public Direction goingDown;

	DirectionValues dirValues;
	
	Random rand = new Random();

	public DirectionsWrapper(Point diff, RailRoad road) {

		dMain = diff.getCor(PointsManager.mainCor);
		dSec = diff.getCor(PointsManager.secCor);
		dY = diff.getCor('y');

		initDirections(road);

		initMagnitudes();

		road.horizontalDirection = movingForward;
		road.verticalDirection = holdingHeight;

	}
	
	void initDirections(RailRoad road) {

		dirValues = new DirectionValues();

		movingForward = new Forward(dirValues.straight, PointsManager.mainCor, road, 6);
		turningLeft = new Turning(dirValues.left, PointsManager.secCor, road);
		turningRight = new Turning(dirValues.right, PointsManager.secCor, road);

		holdingHeight = new SameHeight(0, 'y', road);
		goingUp = new ChangeHeight(1, 'y', road);
		goingDown = new ChangeHeight(-1, 'y', road);
		
	}
	
	double calculateDeviation(double percent) {
		double distHorizontal = Math.abs(dMain) + Math.abs(dSec);
		double deviation = distHorizontal*percent;
		return Math.floor(deviation/2);
	}


	void initMagnitudes() {
		double percentH = (rand.nextInt(20) + 10)/100.0f;
		double percentV = (rand.nextInt(40) + 50)/100.0f;
		int deviationH = (int)calculateDeviation(percentH);
		int deviationV = (int)calculateDeviation(percentV);
		
		movingForward.magnitude = Math.abs(dMain);
		holdingHeight.magnitude = Math.abs(dSec) + Math.abs(dMain) - Math.abs(dY);

		int sign = Integer.signum(dSec);
		if(sign == dirValues.left) {
			turningLeft.magnitude = Math.abs(dSec) + deviationH;
			turningRight.magnitude = deviationH;
		} else {
			turningRight.magnitude = Math.abs(dSec) + deviationH;
			turningLeft.magnitude = deviationH;
		}
		
		sign = Integer.signum(dY);
		if(sign == 1) {
			goingUp.magnitude = Math.abs(dY) + deviationV;
			goingDown.magnitude = deviationV;
		}
		else {
			goingDown.magnitude = Math.abs(dY) + deviationV;
			goingUp.magnitude = deviationV;
		}
		
	}

	public int magnitudesSum() {
		return turningLeft.magnitude + turningRight.magnitude + movingForward.magnitude;
	}
	
	class DirectionValues {
		int straight;
		int left;
		int right;

		public DirectionValues() {
			if(dMain > 0)
				this.straight = 1;
			else
				this.straight = -1;

			if(PointsManager.mainCor == 'x') {
				this.left = -straight;
				right = straight;
			} else if (PointsManager.mainCor == 'z') {
				this.left = straight;
				this.right = -straight;
			}
		}
	}
}
