package logica;

import dominio.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class SistemaImpl implements Sistema{
	
	private ArrayList<Hechizo> hechizos = new ArrayList<Hechizo>();
	private ArrayList<Mago> magos = new ArrayList<Mago>();
	private Scanner s = new Scanner(System.in);
	
	@Override
	public void iniciar() {
		
		cargarHechizos();
		cargarMagos();
		//menuPrincipal();
		
	}

	private void menuPrincipal() {
		// TODO Auto-generated method stub
		
	}
	
	private void menuAdministrador() {
		
		
		
	}
	
	private void menuAnalista() {
		
		
	}
	
	@Override
	public void cargarHechizos() {
		
		try {
			
			File f = new File("Hechizos.txt");
			Scanner sc = new Scanner(f);
			
			while (sc.hasNextLine()) {
				
				String linea = sc.nextLine();
				String[] partes = linea.split(";");
				
				String nombre = partes[0];
				String tipo = partes[1];
				int daño = Integer.parseInt(partes[2]);
				
				if (tipo.equalsIgnoreCase("Fuego")) {
	                int duracionQuemadura = Integer.parseInt(partes[3]);
	                hechizos.add(new HechizoFuego(nombre, daño, duracionQuemadura));
	                
	            } else if (tipo.equalsIgnoreCase("Tierra")) {
	                int mejoraDefensa = Integer.parseInt(partes[3]);
	                hechizos.add(new HechizoTierra(nombre, daño, mejoraDefensa));
	                
	            } else if (tipo.equalsIgnoreCase("Planta")) {
	                String[] extras = partes[3].split(",");
	                int duracionStun = Integer.parseInt(extras[0]);
	                int cantPlantas = Integer.parseInt(extras[1]);
	                hechizos.add(new HechizoPlanta(nombre, daño, duracionStun, cantPlantas));
	                
	            } else if (tipo.equalsIgnoreCase("Agua")) {
	                String[] extras = partes[3].split(",");
	                int cantidadHeal = Integer.parseInt(extras[0]);
	                int presionAgua = Integer.parseInt(extras[1]);
	                hechizos.add(new HechizoAgua(nombre, daño, cantidadHeal, presionAgua));
	            }
	        }
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo");
		}
		
	}

	@Override
	public void cargarMagos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardarHechizos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardarMagos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarMago() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarMago() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarMago() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarHechizo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarHechizo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarHechizo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarTop10Hechizos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarTop3Magos() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarHechizosConPuntuacion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarMagosConPuntuacion() {
		// TODO Auto-generated method stub
		
	}

}
