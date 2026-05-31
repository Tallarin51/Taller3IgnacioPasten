package dominio;

public class HechizoTierra extends Hechizo {

	private int mejoraDefensa;

	public HechizoTierra(String nombre, int daño, int mejoraDefensa) {
		super(nombre, "Tierra", daño);
		this.mejoraDefensa = mejoraDefensa;
	}

	public int getMejoraDefensa() {
		return mejoraDefensa;
	}

	public void setMejoraDefensa(int mejoraDefensa) {
		this.mejoraDefensa = mejoraDefensa;
	}

	@Override
	public double calcularPuntuacion() {
		return (daño * mejoraDefensa) / 2.0;
	}

	@Override
	public String toString() {
		return super.toString() + "| Mejora defensa" + mejoraDefensa;
	}
	
	
}
