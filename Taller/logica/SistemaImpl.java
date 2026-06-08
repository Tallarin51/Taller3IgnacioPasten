package logica;

import dominio.*;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;

public class SistemaImpl implements Sistema{
	
	private ArrayList<Hechizo> hechizos = new ArrayList<Hechizo>();
	private ArrayList<Mago> magos = new ArrayList<Mago>();
	
	
	@Override
	//Guarda todos los hechizos actuales en Hechizos.txt.
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
	//Guarda todos los magos actuales en Magos.txt.
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
	//Muestra los 10 hechizos con mayor puntuación.   
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
	//Muestra los 3 magos con mayor puntuación.
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
	//Muestra todos los hechizos registrados.
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
	//Muestra todos los magos registrados.
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
	//Muestra todos los hechizos junto a su puntuación.
	public void mostrarHechizosConPuntuacion() {
		
		if (hechizos.isEmpty()) {
			System.out.println("No hay hechizos registrados.");
		} else {
			for (Hechizo hechizo : hechizos) {
				System.out.println(hechizo.getNombre() + " | Tipo: " + hechizo.getTipo()
                + " | Daño: " + hechizo.getDaño()
                + " | Puntuación: " + hechizo.calcularPuntuacion());
			}
		}
		
	}

	@Override
	//Muestra todos los magos junto a su puntuación.
	public void mostrarMagosConPuntuacion() {
		
		if (magos.isEmpty()) {
			System.out.println("No hay magos registrados.");
		} else {
			for (Mago mago : magos) {
				System.out.println(mago.getNombre() + " | Puntuación: " + mago.calcularPuntuacion());
			}
		}
		
	}
	
	//Busca un hechizo por su nombre dentro de la lista de hechizos.
	public Hechizo buscarHechizoPorNombre(String nombre) {
		
		for (Hechizo hechizo : hechizos) {
			if (hechizo.getNombre().equalsIgnoreCase(nombre)) {
				return hechizo;
			}
		}
		
		
		return null;
	}

	@Override
	//Agrega un hechizo a la lista de hechizos.
	public void agregarHechizo(Hechizo hechizo) {
		
		hechizos.add(hechizo);
	}

	@Override
	//Agrega un mago a la lista de magos.
	public void agregarMago(Mago mago) {
		magos.add(mago);
	}

	@Override
	//Elimina un mago del sistema y actualiza el archivo.
	public void eliminarMago(String nombre) {
		
		Mago mago = buscarMagoPorNombre(nombre);
		if (mago != null) {
			magos.remove(mago);
			guardarMagos();
			System.out.println("Mago eliminado correctamente.");
		} else {
			System.out.println("No existe un mago con ese nombre.");
		}
		
	}

	@Override
	//Elimina un hechizo del sistema y de todos los magos que lo dominan.
	public void eliminarHechizo(String nombre) {
		
		Hechizo hechizo = buscarHechizoPorNombre(nombre);
		
		if (hechizo != null) {
			hechizos.remove(hechizo);
			
			for (Mago mago : magos) {
				mago.eliminarHechizo(nombre);
			}
			
			guardarHechizos();
			guardarMagos();
			
			System.out.println("Hechizo eliminado correctamente.");
		} else {
			System.out.println("No existe un hechizo con ese nombre.");
		}
		
	}

	@Override
	//Busca un mago por su nombre dentro de la lista de magos.
	public Mago buscarMagoPorNombre(String nombre) {
		
		for (Mago mago : magos) {
			if (mago.getNombre().equalsIgnoreCase(nombre)) {
				return mago;
			}
		}
		
		return null;
	}

	@Override
	//Reemplaza un mago existente por uno con datos actualizados.
	public void modificarMago(String nombreActual, Mago magoNuevo) {
		 Mago mago = buscarMagoPorNombre(nombreActual);

		    if (mago != null) {
		        int posicion = magos.indexOf(mago);
		        magos.set(posicion, magoNuevo);
		        guardarMagos();
		        System.out.println("Mago modificado correctamente.");
		    } else {
		        System.out.println("No existe un mago con ese nombre.");
		    }
	}

	@Override
	//Reemplaza un hechizo existente y actualiza los magos que lo dominan.
	public void modificarHechizo(String nombreActual, Hechizo hechizoNuevo) {
		Hechizo hechizo = buscarHechizoPorNombre(nombreActual);

	    if (hechizo != null) {
	        int posicion = hechizos.indexOf(hechizo);
	        hechizos.set(posicion, hechizoNuevo);

	        for (Mago mago : magos) {
	            for (int i = 0; i < mago.getHechizos().size(); i++) {
	                if (mago.getHechizos().get(i).getNombre().equalsIgnoreCase(nombreActual)) {
	                    mago.getHechizos().set(i, hechizoNuevo);
	                }
	            }
	        }

	        guardarHechizos();
	        guardarMagos();

	        System.out.println("Hechizo modificado correctamente.");
	    } else {
	        System.out.println("No existe un hechizo con ese nombre.");
	    }
		
	}
	
}
