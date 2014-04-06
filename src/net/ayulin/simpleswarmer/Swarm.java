package net.ayulin.simpleswarmer;

public class Swarm {
	Particle[] particles;
	int dimensions;
	EvaluationFunction function;
	boolean shouldMaximise = true;
	
	double[] bestPosition;

	private Swarm() {
		
	}
	
	public Swarm forOptimisationProblem(OptimisationProblem problem) {
		Swarm s = new Swarm();

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
}
