package net.ayulin.simpleswarmer;

public class OptimisationProblem {
	OptimisationStrategy strategy;
	double maxPosition;
	double minPosition;
	
	EvaluationFunction function;
	int numberOfParticles;
	int dimensions;

	public OptimisationStrategy getStrategy() {
		return strategy;
	}
	public void setStrategy(OptimisationStrategy strategy) {
		this.strategy = strategy;
	}
	public double getMaxPosition() {
		return maxPosition;
	}
	public void setMaxPosition(double maxPosition) {
		this.maxPosition = maxPosition;
	}
	public double getMinPosition() {
		return minPosition;
	}
	public void setMinPosition(double minPosition) {
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
}
