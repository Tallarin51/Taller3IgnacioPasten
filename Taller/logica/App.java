package logica;

import dominio.*;
import java.io.*;
import java.util.Scanner;

public class App {
	
	private static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Sistema sistema = new SistemaImpl();
		
		leerHechizos(sistema);
		leerMagos(sistema);
		
		menuPrincipal(sistema);
		
		
	}
	
		private static void leerHechizos(Sistema sistema) {
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
		                sistema.agregarHechizo(new HechizoFuego(nombre, daño, duracionQuemadura));
		                
		            } else if (tipo.equalsIgnoreCase("Tierra")) {
		                int mejoraDefensa = Integer.parseInt(partes[3]);
		                sistema.agregarHechizo(new HechizoTierra(nombre, daño, mejoraDefensa));
		                
		            } else if (tipo.equalsIgnoreCase("Planta")) {
		                String[] extras = partes[3].split(",");
		                int duracionStun = Integer.parseInt(extras[0]);
		                int cantPlantas = Integer.parseInt(extras[1]);
		                sistema.agregarHechizo(new HechizoPlanta(nombre, daño, duracionStun, cantPlantas));
		                
		            } else if (tipo.equalsIgnoreCase("Agua")) {
		                String[] extras = partes[3].split(",");
		                int cantidadHeal = Integer.parseInt(extras[0]);
		                int presionAgua = Integer.parseInt(extras[1]);
		                sistema.agregarHechizo(new HechizoAgua(nombre, daño, cantidadHeal, presionAgua));
		            }
		        }
				sc.close();
			} catch (FileNotFoundException e) {
				System.out.println("No se encontró el archivo");
			}
	    }

	    private static void leerMagos(Sistema sistema) {
	    	
			try {
				File f = new File("Magos.txt");
				Scanner sc = new Scanner(f);
				
				while (sc.hasNextLine()) {
					
					String linea = sc.nextLine();
					String[] partes = linea.split(";");
					
					String nombreMago = partes[0];
		            Mago mago = new Mago(nombreMago);

		            if (partes.length > 1) {
		                String[] nombresHechizos = partes[1].split("\\|");

		                for (String nombreHechizo : nombresHechizos) {
		                    Hechizo hechizo = sistema.buscarHechizoPorNombre(nombreHechizo);

		                    if (hechizo != null) {
		                        mago.agregarHechizo(hechizo);
		                    }
		                }
		            }

		            sistema.agregarMago(mago);
		        }
				sc.close();
			} catch (FileNotFoundException e) {
				System.out.println("No se encontró el archivo.");
			}
	    }

	    private static void menuPrincipal(Sistema sistema) {
	    	String respuesta;
	    	
	    	do {
	            System.out.println("\n===== SISTEMA DE MAGOS =====");
	            System.out.println("1. Panel Administrador");
	            System.out.println("2. Panel Analista");
	            System.out.println("3. Salir");
	            System.out.print("Seleccione una opción: ");
	    	
	            respuesta = s.nextLine();
	            
	            switch (respuesta) {
	            
	            case "1":
	            	menuAdministrador(sistema);
	            	break;
	            case "2":
	            	menuAnalista(sistema);
	            	break;
	            case "3":
	            	System.out.println("Saliendo del sistema...");
	            default:
	            	System.out.println("Opción inválida.");
	            	break;
	            	
	            }
	            
	    	} while (!respuesta.equals("3"));
	    
	    }

	    private static void menuAdministrador(Sistema sistema) {
	    }

	    private static void menuAnalista(Sistema sistema) {
	    }

}
