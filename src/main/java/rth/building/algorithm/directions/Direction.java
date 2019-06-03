package railway.building.algorithm.directions;

import java.util.Random;

import railway.building.algorithm.RailRoad;

public abstract class Direction {

	public int magnitude;

	public int changeValue;
	public char changeCoordinate;

	int length;

	public RailRoad road;

	Random rand = new Random();

	public Direction(int changeValue, char changeCoordinate, RailRoad road) {
		this.changeCoordinate = changeCoordinate;
		this.changeValue = changeValue;

		magnitude = 0;

		this.road = road;
	}

	public int getMagnitude() {
		return magnitude;
	}

	//	public abstract Direction nextDirection();

	public void initialCondition() {
		//length++;
	}

	public Direction chooseFromDirections(Direction... directions) {

		int sum = 0;
		for (int i = 0; i < directions.length; i++) {
			sum += directions[i].magnitude;
		}

		int candidate = rand.nextInt(sum);

		Direction nextDir = null;
		int totalMagnitude = 0;
		int i;
		for (i = 0; i < directions.length; i++) {
			totalMagnitude += directions[i].magnitude;
			if(candidate < totalMagnitude) {
				nextDir = directions[i]; 
				break;
			}

		}

		finalCondition(i, nextDir);

		return checkDirForNull(nextDir);
	}

	public void finalCondition(int i, Direction nextDir) {
		if(i > 0) {
			length = 0;
			changingCondition(nextDir);
		} else if (i == 0) {
			length++;
			remainingCondition(nextDir);
		}

		if(magnitude > 0)
			this.magnitude--;
	}

	abstract Direction checkDirForNull(Direction dir);

	abstract void remainingCondition(Direction newDir);
	
	abstract void changingCondition(Direction newDir);

	public AlternateDirections nextInOrder(AlternateDirections dirs) {

		Direction firstOne, secondOne;
		if(dirs.one.magnitude > dirs.two.magnitude) {
			firstOne = dirs.one;
			secondOne = dirs.two;
		} else {
			firstOne = dirs.two;
			secondOne = dirs.one;
		}

		return new AlternateDirections(firstOne, secondOne); //chooseFromDirections(this, firstOne, secondOne);
	}

	class AlternateDirections {
		final Direction one;
		final Direction two;

		public AlternateDirections(Direction one, Direction two) {
			this.one = one;
			this.two = two;
		}
	}

	public abstract Direction nextDirection(boolean isTurning);

	public abstract Direction nextDirection();

}

abstract class HorizontalPlane extends Direction {

	public HorizontalPlane(int changeValue, char changeCoordinate, RailRoad road) {
		super(changeValue, changeCoordinate, road);
	}

	@Override
	void changingCondition(Direction newDir) {
		changingCondition();
	}

	@Override
	void remainingCondition(Direction newDir) {
		remainingCondition();
	}
	
	public void changingCondition() {
		road.isTurning = true;
	}
	public void remainingCondition() {
		road.isTurning = false;
	}
	
	@Override
	Direction checkDirForNull(Direction dir) {
		if(dir == null) {
			return road.directions.movingForward;
		} else
			return dir;
	}

	@Override
	public Direction nextDirection(boolean isTurning) {
		return null;
	}

	@Override
	public Direction nextDirection() {
		if(road.verticalDirection instanceof ChangeHeight) {
			finalCondition(0, this);
			return this;
		} else
			return nextHorizontalDirection();
	}

	abstract Direction nextHorizontalDirection();
}

class Turning extends HorizontalPlane {

	public Turning(int changeValue, char changeCoordinate, RailRoad road) {
		super(changeValue, changeCoordinate, road);
	}

	@Override
	public Direction nextHorizontalDirection() {
		//		if(road.verticalDirection instanceof ChangeHeight)
		//			return chooseFromDirections(this);
		//		else
		return chooseFromDirections(this, road.directions.movingForward);
	}

	@Override
	public void changingCondition() {
		((Forward)road.directions.movingForward).lastTurningDirection = this;
		super.changingCondition();
	}

}

class Forward extends HorizontalPlane {

	public HorizontalPlane lastTurningDirection;
	private final int maxLength;

	public Forward(int changeValue, char changeCoordinate, RailRoad road,
			int maxLengthBeforeSameTurn) {
		super(changeValue, changeCoordinate, road);
		maxLength = maxLengthBeforeSameTurn;
	}

	@Override
	public Direction nextHorizontalDirection() {

		if(length > maxLength) {
			AlternateDirections dirs = new AlternateDirections(road.directions.turningLeft, road.directions.turningRight);
			dirs = nextInOrder(dirs);
			return chooseFromDirections(this, dirs.one, dirs.two);
		} else {
			Direction secArgument = road.directions.turningLeft;

			if(lastTurningDirection == null) {
				return chooseFromDirections(this);
			} else {
				
				if(lastTurningDirection.equals(road.directions.turningLeft))
					secArgument = road.directions.turningLeft;
				else if(lastTurningDirection.equals(road.directions.turningRight))
					secArgument = road.directions.turningRight;

				return chooseFromDirections(this, secArgument);
			}
		}

	}

}

abstract class verticalPlane extends Direction {

	public verticalPlane(int changeValue, char changeCoordinate, RailRoad road) {
		super(changeValue, changeCoordinate, road);
	}

	@Override
	Direction checkDirForNull(Direction dir) {
		if(dir == null) 
			return road.directions.holdingHeight;
		else {
			return dir;
		}
	}

	public Direction nextDirection(boolean isTurning) {
		if(isTurning) {
			finalCondition(0, road.directions.holdingHeight);
			return road.directions.holdingHeight;
		} else
			return nextVerticalDirection();
	}

	abstract Direction nextVerticalDirection();

	@Override
	public Direction nextDirection() {
		return null;
	}

	public void plotSupportingPoint(Direction newDir) {
		if(newDir.changeValue == 1)
			road.plotPoint(road.currentPoint, road.horizontalDirection.changeCoordinate,
					road.horizontalDirection.changeValue, 0);
		else if (newDir.changeValue == -1)
			road.plotPoint(road.currentPoint, road.horizontalDirection.changeCoordinate,
					0, -1);
	}
	
	@Override
	void changingCondition(Direction newDir) {
	}
	
	@Override
	void remainingCondition(Direction newDir) {
	}
}

class ChangeHeight extends verticalPlane {

	public ChangeHeight(int changeValue, char changeCoordinate, RailRoad road) {
		super(changeValue, changeCoordinate, road);
	}

	@Override
	public Direction nextVerticalDirection() {
		return chooseFromDirections(this, road.directions.holdingHeight);
	}
	
	@Override
	void remainingCondition(Direction newDir) {
		plotSupportingPoint(newDir);
	}
}

class SameHeight extends verticalPlane {
	public SameHeight(int changeValue, char changeCoordinate, RailRoad road) {
		super(changeValue, changeCoordinate, road);
	}

	@Override
	public Direction nextVerticalDirection() {
		AlternateDirections dirs = new AlternateDirections(road.directions.goingUp, road.directions.goingDown);
		dirs = nextInOrder(dirs);
		return chooseFromDirections(this, dirs.one, dirs.two);
	}

	@Override
	void changingCondition(Direction newDir) {
		plotSupportingPoint(newDir);
	}
	
}