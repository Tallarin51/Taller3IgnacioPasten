package dominio;

public abstract class Hechizo {

	protected String nombre;
	protected String tipo;
	protected int daño;

	public Hechizo(String nombre, String tipo, int daño) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.daño = daño;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getDaño() {
		return daño;
	}

	public void setDaño(int daño) {
		this.daño = daño;
	}

	public abstract double calcularPuntuacion();

	@Override
	public String toString() {
		return "Hechizo: " + nombre + " | Tipo: " + tipo + " | Daño: " + daño + " | Puntuación: "
				+ calcularPuntuacion();
	}

}
