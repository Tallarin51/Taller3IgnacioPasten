package dominio;

import java.util.ArrayList;

public class Mago {
	
	private String nombre;
	private ArrayList<Hechizo> hechizos;
	
	public Mago(String nombre) {
		super();
		this.nombre = nombre;
		this.hechizos = new ArrayList<Hechizo>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Hechizo> getHechizos() {
		return hechizos;
	}

	public void setHechizos(ArrayList<Hechizo> hechizos) {
		this.hechizos = hechizos;
	}
	
	public void agregarHechizo(Hechizo h) {
		hechizos.add(h);
	}
	
	public void eliminarHechizo(String nombreHechizo) {
		for (int i = 0; i < hechizos.size(); i++) {
			if (hechizos.get(i).getNombre().equalsIgnoreCase(nombreHechizo)) {
				hechizos.remove(i);
				return;
			}
		}
	}
	
	public double calcularPuntuacion() {
		double total = 0;
		
		for (Hechizo hechizo : hechizos) {
			total += hechizo.calcularPuntuacion();
		}
		
		return total;
		
	}
	
	@Override
    public String toString() {
        String texto = "Mago: " + nombre + " | Hechizos: ";

        if (hechizos.isEmpty()) {
            texto += "Sin hechizos";
        } else {
            for (int i = 0; i < hechizos.size(); i++) {
                texto += hechizos.get(i).getNombre();

                if (i < hechizos.size() - 1) {
                    texto += ", ";
                }
            }
        }

        return texto;
    }
}
