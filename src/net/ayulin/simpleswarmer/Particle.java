package net.ayulin.simpleswarmer;

public class Particle {
	double[] position;
	double[] velocity;

	double[] bestPosition;
	double bestScore;

	double[] maxPosition;
	double[] minPosition;

	double[] maxVelocity;
	double[] minVelocity;

	public Particle(int dimensions) {
		position = new double[dimensions];
	}

	public void setPosition(double[] updatedPosition) {
		position = updatedPosition;
	}

	public double[] getPosition() {
		return position;
	}

	public double[] getBestPosition() {
		return bestPosition;
	}

	public double getBestScore() {
		return bestScore;
	}

	public double[] getVelocity() {
		return velocity;
	}

	public void setRandomPosition() {
		for (int i = 0; i < position.length; i++) {
			double range = maxPosition[i] - minPosition[i];
			double randomValue = range * Math.random();
			double randomPosition = randomValue + minPosition[i];

			position[i] = randomPosition;
		}
	}

	public double[] getMaxPosition() {
		return maxPosition;
	}

	public void setMaxPosition(double[] maxPosition) {
		this.maxPosition = maxPosition;
	}

	public double[] getMinPosition() {
		return minPosition;
	}

	public void setMinPosition(double[] minPosition) {
		this.minPosition = minPosition;
	}

	public void setRandomVelocity() {
		for (int i = 0; i < position.length; i++) {
			double range = maxVelocity[i] - minVelocity[i];
			double randomValue = range * Math.random();
			double randomVelocity = randomValue + minVelocity[i];

			velocity[i] = randomVelocity;
		}
	}
}
