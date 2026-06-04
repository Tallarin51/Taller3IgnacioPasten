package logica;

import dominio.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;

public class SistemaImpl implements Sistema{
	
	private ArrayList<Hechizo> hechizos = new ArrayList<Hechizo>();
	private ArrayList<Mago> magos = new ArrayList<Mago>();
	private Scanner s = new Scanner(System.in);
	
	
	@Override
	public void guardarHechizos() {
		
		try {
	        BufferedWriter writer = new BufferedWriter(new FileWriter("Hechizos.txt"));

	        for (Hechizo hechizo : hechizos) {

	            if (hechizo instanceof HechizoFuego) {
	                HechizoFuego h = (HechizoFuego) hechizo;
	                writer.write(h.getNombre() + ";" + h.getTipo() + ";" + h.getDaño() + ";" + h.getDuracionQuemadura());
	            }

	            else if (hechizo instanceof HechizoTierra) {
	                HechizoTierra h = (HechizoTierra) hechizo;
	                writer.write(h.getNombre() + ";" + h.getTipo() + ";" + h.getDaño() + ";" + h.getMejoraDefensa());
	            }

	            else if (hechizo instanceof HechizoPlanta) {
	                HechizoPlanta h = (HechizoPlanta) hechizo;
	                writer.write(h.getNombre() + ";" + h.getTipo() + ";" + h.getDaño() + ";" + h.getDuracionStun() + "," + h.getCantPlantas());
	            }

	            else if (hechizo instanceof HechizoAgua) {
	                HechizoAgua h = (HechizoAgua) hechizo;
	                writer.write(h.getNombre() + ";" + h.getTipo() + ";" + h.getDaño() + ";" + h.getCantidadHeal() + "," + h.getPresionAgua());
	            }

	            writer.newLine();
	        }

	        writer.close();

	    } catch (IOException e) {
	        System.out.println("Error al guardar hechizos.");
	    }
		
	}

	@Override
	public void guardarMagos() {
		
		try {
	        BufferedWriter writer = new BufferedWriter(new FileWriter("Magos.txt"));

	        for (Mago mago : magos) {
	            writer.write(mago.getNombre() + ";");

	            for (int i = 0; i < mago.getHechizos().size(); i++) {
	                writer.write(mago.getHechizos().get(i).getNombre());

	                if (i < mago.getHechizos().size() - 1) {
	                    writer.write("|");
	                }
	            }

	            writer.newLine();
	        }

	        writer.close();

	    } catch (IOException e) {
	        System.out.println("Error al guardar magos.");
	    }
		
	}

	

	@Override
	public void mostrarTop10Hechizos() {
		
		if (hechizos.isEmpty()) {
	        System.out.println("No hay hechizos registrados.");
	        return;
	    }

	    ArrayList<Hechizo> copia = new ArrayList<>();

	    for (Hechizo h : hechizos) {
	        copia.add(h);
	    }

	    for (int i = 0; i < copia.size() - 1; i++) {
	        for (int j = 0; j < copia.size() - 1 - i; j++) {
	            if (copia.get(j).calcularPuntuacion() < copia.get(j + 1).calcularPuntuacion()) {
	                Hechizo aux = copia.get(j);
	                copia.set(j, copia.get(j + 1));
	                copia.set(j + 1, aux);
	            }
	        }
	    }

	    System.out.println("===== TOP 10 MEJORES HECHIZOS =====");

	    int limite = 10;

	    if (copia.size() < 10) {
	        limite = copia.size();
	    }

	    for (int i = 0; i < limite; i++) {
	        Hechizo h = copia.get(i);

	        System.out.println((i + 1) + ". " + h.getNombre()
	                + " | Tipo: " + h.getTipo()
	                + " | Puntuación: " + h.calcularPuntuacion());
	    }
	}

	@Override
	public void mostrarTop3Magos() {
		 if (magos.isEmpty()) {
		        System.out.println("No hay magos registrados.");
		        return;
		    }

		    ArrayList<Mago> copia = new ArrayList<>();

		    for (Mago m : magos) {
		        copia.add(m);
		    }

		    for (int i = 0; i < copia.size() - 1; i++) {
		        for (int j = 0; j < copia.size() - 1 - i; j++) {
		            if (copia.get(j).calcularPuntuacion() < copia.get(j + 1).calcularPuntuacion()) {
		                Mago aux = copia.get(j);
		                copia.set(j, copia.get(j + 1));
		                copia.set(j + 1, aux);
		            }
		        }
		    }

		    System.out.println("===== TOP 3 MEJORES MAGOS =====");

		    int limite = 3;

		    if (copia.size() < 3) {
		        limite = copia.size();
		    }

		    for (int i = 0; i < limite; i++) {
		        Mago m = copia.get(i);

		        System.out.println((i + 1) + ". " + m.getNombre()
		                + " | Puntuación: " + m.calcularPuntuacion());
		    }
		
	}

	@Override
	public void mostrarTodosLosHechizos() {
		
		if (hechizos.isEmpty()) {
			System.out.println("No hay hechizos registrados.");
		} else {
			for (Hechizo hechizo : hechizos) {
				System.out.println(hechizo);
			}
		}
		
	}

	@Override
	public void mostrarTodosLosMagos() {
		
		if (magos.isEmpty()) {
			System.out.println("No hay magos registrados");
		} else {
			for (Mago mago : magos) {
				System.out.println(mago);
			}
		}
		
	}

	@Override
	public void mostrarHechizosConPuntuacion() {
		
		if (hechizos.isEmpty()) {
			System.out.println("No hay archivos registrados.");
		} else {
			for (Hechizo hechizo : hechizos) {
				System.out.println(hechizo.getNombre() + " | Tipo: " + hechizo.getTipo()
                + " | Daño: " + hechizo.getDaño()
                + " | Puntuación: " + hechizo.calcularPuntuacion());
			}
		}
		
	}

	@Override
	public void mostrarMagosConPuntuacion() {
		
		if (magos.isEmpty()) {
			System.out.println("No hay magos registrados.");
		} else {
			for (Mago mago : magos) {
				System.out.println(mago.getNombre() + " | Puntuación: " + mago.calcularPuntuacion());
			}
		}
		
	}
	
	public Hechizo buscarHechizoPorNombre(String nombre) {
		
		for (Hechizo hechizo : hechizos) {
			if (hechizo.getNombre().equalsIgnoreCase(nombre)) {
				return hechizo;
			}
		}
		
		
		return null;
	}

	@Override
	public void agregarHechizo(Hechizo hechizo) {
		
		hechizos.add(hechizo);
	}

	@Override
	public void agregarMago(Mago mago) {
		magos.add(mago);
	}

	@Override
	public void modificarMago(String nombre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarMago(String nombre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarHechizo(String nombre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarHechizo(String nombre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mago buscarMagoPorNombre(String nombre) {
		
		for (Mago mago : magos) {
			if (mago.getNombre().equalsIgnoreCase(nombre)) {
				return mago;
			}
		}
		
		return null;
	}
	
}
