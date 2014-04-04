package net.ayulin.simpleswarmer;

public class Swarm {
	Particle[] particles;
	int dimensions;
	EvaluationFunction function;

	boolean shouldMaximise = true;

	public Swarm(int particles, int dimensions, EvaluationFunction function) {
		this.particles = new Particle[particles];
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
