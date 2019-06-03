package rth.building.algorithm;

import rth.building.Point;
import rth.building.RailBuilder;

public class RailShaft extends RailWay {

	double xRib;
	double zRib;


	RailShaft(Point A, Point B, Point diff, double distH, double distV, RailBuilder builder) {
		super(A, B, diff, distH, distV, builder);

		xRib = Math.abs(diff.x);
		zRib = Math.abs(diff.z); 

	}

	@Override
	public void buildPath() {
		Point startPoint = A;
		//double totalDistance = distV; delete?

		//TODO xRib zRib > 0 || >= 1. Initial conditions

		//combine logic of these 2 with current side class
		//main sec cors romance
		char firstCor; char nextCor;
		if(xRib > zRib) {
			firstCor = 'x';
			nextCor = 'z';
		} else {
			firstCor = 'z';
			nextCor = 'x';
		}


		//also define direction, yep
		int firstDir, nextDir;
		if(diff.getCor(firstCor) > 0)
			firstDir = 1;
		else
			firstDir = -1;

		if(diff.getCor(nextCor) > 0)
			nextDir = 1;
		else
			nextDir = -1;


		//up or down
		int vertDir;
		if (diff.y > 0)
			vertDir = 1;
		else
			vertDir = -1;


		double bigRib = xRib + zRib;

		//where do i end up at the bottom - how far from seeked corner
		double actualBigTurns = distV/bigRib;

		//now, i can find amount of cubes to spread 
		//I can go only further (not beforther)
		//so i need closest odd value
		double c = Math.ceil(actualBigTurns);
		double seekedNumberOfBigTurns;
		if (c % 2 == 0) 
			seekedNumberOfBigTurns = c + 1;
		else
			seekedNumberOfBigTurns = c;

		double toSpread = bigRib * (seekedNumberOfBigTurns - actualBigTurns);
		double numberOfCubes = seekedNumberOfBigTurns*bigRib;
		double smallTurns = seekedNumberOfBigTurns*2;

		double toSpreadFreq = Math.floor(numberOfCubes/toSpread); //а точно floor

		Point nextPoint = startPoint.copy();
		points.add(startPoint);	

		CurrentSide curSide = new CurrentSide(firstCor, firstDir, nextDir);

		//main loop
		double cubesPassed = 1;
		for(int n = 1; n <= smallTurns; n++) {

			for(int i = 1; i <= curSide.curLength; i++ ) {

				nextPoint = nextPoint.copy();
				nextPoint.setCor(curSide.curCor, nextPoint.getCor(curSide.curCor) + curSide.curDir); 
				if (!(i == 1 || i == curSide.curLength) &&
						cubesPassed % toSpreadFreq != 0) { //if need to spread - don't change y coordinate
					nextPoint.setCor('y', nextPoint.getCor('y') + vertDir); 
					plotSupportingPoint(curSide.curCor, curSide.curDir, vertDir, nextPoint);
				}
				points.add(nextPoint);

				cubesPassed++;
				
				builder.setter.buildRuntimePoint(nextPoint, curSide.curCor, i == curSide.curLength);
				

			}

			curSide.next();
		}
	} 

	void plotSupportingPoint(char curCor, int curDir, int vertDir, Point point) {
		Point support = point.copy();
		if(vertDir == 1)
			support.setCor('y', support.getCor('y') - 1);
		else if (vertDir == -1)
			support.setCor(curCor, support.getCor(curCor) - curDir);
		
		points.add(support);
	}

	//parameters for each of 2 possible sides
	class CurrentSide {

		char curCor;

		int curDir;
		int nextDir;

		double curLength;

		CurrentSide(char mainCor, int mainDir, int secDir) {

			curCor = mainCor;

			curDir = mainDir;
			nextDir = secDir;

			setLength(mainCor);

		}

		void setNextCor() {
			switch (curCor) {
			case 'x': curCor = 'z'; break;
			case 'z': curCor = 'x'; break;
			}
		}

		void setNextDir() {
			int dir1, dir2;

			dir1 = nextDir;
			dir2 = curDir*(-1);

			curDir = dir1;
			nextDir = dir2;

		}

		void setNextLength() {
			setLength(curCor);
		}

		void setLength(char cor) {
			switch (cor) {
			case 'x': curLength = xRib; break;
			case 'z': curLength = zRib; break;
			}
		}

		void next() {

			setNextCor();
			setNextDir(); 
			setNextLength();

		}


	}

}
