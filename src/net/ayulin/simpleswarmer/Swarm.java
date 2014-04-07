package net.ayulin.simpleswarmer;

import java.util.Arrays;

public class Swarm {
	Particle[] particles;
	int dimensions;
	EvaluationFunction function;
	boolean shouldMaximise = true;

	double[] bestPosition;
	double bestScore;

	double particleResetProbability;

	int numberOfIterations;

	private Swarm() {

	}

	public static Swarm forOptimisationProblem(OptimisationProblem problem) {
		Swarm s = new Swarm();
		s.numberOfIterations = 0;

		s.dimensions = problem.getDimensions();
		s.setOptimisationStrategy(problem.getStrategy());
		s.setInitialScore(problem.getStrategy());

		s.particles = new Particle[problem.getNumberOfParticles()];
		for (int i = 0; i < problem.getNumberOfParticles(); i++) {
			Particle p = new Particle(problem.getDimensions(), problem.getFunction());
			p.setMaxPosition(problem.getMaxPosition());
			p.setMinPosition(problem.getMinPosition());
			p.setRandomPosition();
			p.setRandomVelocity();

			s.particles[i] = p;
		}

		return s;
	}

	private void setInitialScore(OptimisationStrategy strategy) {
		switch (strategy) {
		case MINIMISE:
			bestScore = Double.MAX_VALUE;
			break;
		case MAXIMISE: // Fall-through to default.
		default:
			bestScore = Double.MIN_VALUE;
			break;
		}
	}

	public void setOptimisationStrategy(OptimisationStrategy strategy) {
		switch (strategy) {
		case MINIMISE:
			shouldMaximise = false;
			break;
		case MAXIMISE: // Fall-through to default.
		default:
			shouldMaximise = true;
			break;
		}
	}

	public void optimise() {
		numberOfIterations++;

		for (Particle p : particles) {
			p.iterate(bestPosition);

			double particleBestScore = p.getBestScore();

			boolean scoreHigherWhenMaximising = shouldMaximise && particleBestScore > bestScore;
			boolean scoreLowerWhenMinimising = !shouldMaximise && particleBestScore < bestScore;

			boolean isBetterScore = scoreHigherWhenMaximising || scoreLowerWhenMinimising;
			if (isBetterScore) {
				bestScore = particleBestScore;
				bestPosition = p.getBestPosition();
			}

			double random = Math.random();
			if (random < particleResetProbability) {
				// Reset the particle.
				// Set the current position and score to the particle's best.
				p.setBestPosition(p.getPosition());
				p.setBestScore(p.getScore());

				// Randomise the particle's position.
				p.setRandomPosition();
				p.setRandomVelocity();
			}
		}
	}

	public double[] getBestPosition() {
		return bestPosition;
	}

	public double getBestScore() {
		return bestScore;
	}

	public String toStringStats() {
		StringBuilder builder = new StringBuilder();
		builder.append("Iterations: " + numberOfIterations);
		builder.append("\nBest result: " + bestScore);
		builder.append("\nBest position: " + Arrays.toString(bestPosition));

		return builder.toString();
	}
}
