package net.ayulin.simpleswarmer;

public class Particle {
	double[] position;
	double[] velocity;

	double[] bestPosition;
	double bestScore;

	double currentScore;

	double[] maxPosition;
	double[] minPosition;

	double[] maxVelocity;
	double[] minVelocity;

	EvaluationFunction scorer;

	double inertia = 0.729;
	final double cognitiveWeight = 1.49445;
	final double socialWeight = 1.49445;

	public Particle(int dimensions, EvaluationFunction scorer) {
		position = new double[dimensions];
		velocity = new double[dimensions];
		bestPosition = new double[dimensions];
		
		maxPosition = new double[dimensions];
		minPosition = new double[dimensions];
		
		maxVelocity = new double[dimensions];
		minVelocity = new double[dimensions];

		this.scorer = scorer;
	}

	public void setPosition(double[] updatedPosition) {
		System.arraycopy(updatedPosition, 0, position, 0,
				updatedPosition.length);
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
		System.arraycopy(maxPosition, 0, this.maxPosition, 0,
				maxPosition.length);
	}

	public double[] getMinPosition() {
		return minPosition;
	}

	public void setMinPosition(double[] minPosition) {
		System.arraycopy(minPosition, 0, this.minPosition, 0,
				minPosition.length);
	}

	public void setRandomVelocity() {
		for (int i = 0; i < position.length; i++) {
			double range = maxVelocity[i] - minVelocity[i];
			double randomValue = range * Math.random();
			double randomVelocity = randomValue + minVelocity[i];

			velocity[i] = randomVelocity;
		}
	}

	public void iterate(double[] globalBestPosition) {
		updateVelocity(globalBestPosition);
		updatePosition();
		evaluateScore();
	}

	private void evaluateScore() {
		double newScore = scorer.evaluatePosition(position);
		if (newScore > bestScore) {
			bestScore = newScore;
			System.arraycopy(position, 0, bestPosition, 0, position.length);
		}
	}

	private void updatePosition() {
		for (int i = 0; i < position.length; i++) {
			position[i] += velocity[i];

			// Make sure it's still within the boundaries.
			if (position[i] < minPosition[i]) {
				position[i] = minPosition[i];
			} else if (position[i] > maxPosition[i]) {
				position[i] = maxPosition[i];
			}
		}
	}

	private void updateVelocity(double[] globalBestPosition) {
		for (int i = 0; i < velocity.length; i++) {
			double randFactor1 = Math.random();
			double randFactor2 = Math.random();

			double inertiaComponent = inertia * velocity[i];
			double localBestComponent = cognitiveWeight * randFactor1
					* (bestPosition[i] - position[i]);
			double globalBestComponent = socialWeight * randFactor2
					* (globalBestPosition[i] - position[i]);

			velocity[i] = inertiaComponent + localBestComponent
					+ globalBestComponent;
		}
	}

	public double getScore() {
		return currentScore;
	}

	public void setBestScore(double score) {
		this.bestScore = score;
	}

	public void setBestPosition(double[] newBestPosition) {
		System.arraycopy(newBestPosition, 0, bestPosition, 0,
				newBestPosition.length);
	}
}
