package net.ayulin.simpleswarmer;

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
	
	public Swarm forOptimisationProblem(OptimisationProblem problem) {
		Swarm s = new Swarm();
		s.numberOfIterations = 0;

		s.dimensions = problem.getDimensions();
		s.setOptimisationStrategy(problem.getStrategy());

		s.particles = new Particle[problem.getNumberOfParticles()];
		for (int i = 0; i < problem.getNumberOfParticles(); i++) {
			Particle p = new Particle(problem.getDimensions());
			p.setMaxPosition(problem.getMaxPosition());
			p.setMinPosition(problem.getMinPosition());
			p.setRandomPosition();
			p.setRandomVelocity();
			
			particles[i] = p;
		}
		
		return s;
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
			if (particleBestScore > bestScore) {
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
}
