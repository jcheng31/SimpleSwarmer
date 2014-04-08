package net.ayulin.simpleswarmer;

public class OptimisationProblem {
	private OptimisationStrategy strategy;
	private double[] maxPosition;
	private double[] minPosition;
	
	private double[] maxVelocity;
	private double[] minVelocity;
	
	private EvaluationFunction function;
	private int numberOfParticles;
	private int dimensions;
	
	private double particleResetProbability;

	public OptimisationStrategy getStrategy() {
		return strategy;
	}
	public void setMaxMinPosition(double bound) {
		maxPosition = new double[dimensions];
		minPosition = new double[dimensions];
		
		for (int i = 0; i < dimensions; i++) {
			maxPosition[i] = Math.abs(bound);
			minPosition[i] = 0 - Math.abs(bound);
		}
	}
	public void setMaxMinVelocity(double bound) {
		maxVelocity = new double[dimensions];
		minVelocity = new double[dimensions];
		
		for (int i = 0; i < dimensions; i++) {
			maxVelocity[i] = Math.abs(bound);
			minVelocity[i] = 0 - Math.abs(bound);
		}
	}
	public void setStrategy(OptimisationStrategy strategy) {
		this.strategy = strategy;
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
	public EvaluationFunction getFunction() {
		return function;
	}
	public void setFunction(EvaluationFunction function) {
		this.function = function;
	}
	public int getNumberOfParticles() {
		return numberOfParticles;
	}
	public void setNumberOfParticles(int numberOfParticles) {
		this.numberOfParticles = numberOfParticles;
	}
	public int getDimensions() {
		return dimensions;
	}
	public void setDimensions(int dimensions) {
		this.dimensions = dimensions;
	}
	public double getParticleResetProbability() {
		return particleResetProbability;
	}
	public void setParticleResetProbability(double particleResetProbability) {
		this.particleResetProbability = particleResetProbability;
	}
}
