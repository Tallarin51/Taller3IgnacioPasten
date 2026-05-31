package dominio;

public class HechizoFuego extends Hechizo {

	private int duracionQuemadura;

	public HechizoFuego(String nombre, int daño, int duracionQuemadura) {
		super(nombre, "Fuego", daño);
		this.duracionQuemadura = duracionQuemadura;
	}

	public int getDuracionQuemadura() {
		return duracionQuemadura;
	}

	public void setDuracionQuemadura(int duracionQuemadura) {
		this.duracionQuemadura = duracionQuemadura;
	}

	@Override
	public double calcularPuntuacion() {
		return daño * duracionQuemadura;
	}

	@Override
	public String toString() {
		return super.toString() + "| Duración quemadura: " + duracionQuemadura;
	}
	
	
	
}
