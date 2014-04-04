package net.ayulin.simpleswarmer;

public class Particle {
	double[] position;
	
	public Particle(int dimensions) {
		position = new double[dimensions];
	}
	
	public void setPosition(double[] updatedPosition) {
		position = updatedPosition;
	}
	
	public double[] getPosition() {
		return position;
	}
}
