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
	            System.out.print("> ");
	    	
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
	            	break;
	            default:
	            	System.out.println("Opción inválida.");
	            	break;
	            	
	            }
	            
	    	} while (!respuesta.equals("3"));
	    
	    }

	    private static void menuAdministrador(Sistema sistema) {
	    	String respuesta;
	    	
	    	 do {
	    	        System.out.println("\n===== PANEL ADMINISTRADOR =====");
	    	        System.out.println("1. Agregar Mago");
	    	        System.out.println("2. Modificar Mago");
	    	        System.out.println("3. Eliminar Mago");
	    	        System.out.println("4. Agregar Hechizo");
	    	        System.out.println("5. Modificar Hechizo");
	    	        System.out.println("6. Eliminar Hechizo");
	    	        System.out.println("7. Volver");
	    	        System.out.print("> ");
	    	        
	    	        respuesta = s.nextLine();
	    	        
	    	        switch (respuesta) {
	    	        
	    	        case "1":
	    	        	//agregarMago (sistema)
	    	        	break;
	    	        case "2":
	    	        	System.out.println("Ingrese el nombre del mago a modificar: ");
	    	        	sistema.modificarMago(s.nextLine());
	    	        	break;
	    	        case "3":
	    	        	System.out.println("Ingrese el nombre del mago a eliminar: ");
	    	        	sistema.eliminarMago(s.nextLine());
	    	        	break;
	    	        case "4":
	    	        	//agregarHechizo(sistema)
	    	        	break;
	    	        case "5":
	    	        	System.out.println("Ingrese el nombre del hechizo a modificar: ");
	    	        	sistema.modificarHechizo(s.nextLine());
	    	        	break;
	    	        case "6":
	    	        	System.out.println("Ingrese el nombre del hechizo a eliminar: ");
	    	        	sistema.eliminarHechizo(s.nextLine());
	    	        	break;
	    	        case "7":
	    	        	System.out.println("Volviendo al menú principal...");
	    	        	break;
	    	        default:
	    	        	System.out.println("Opción inválida.");
	    	        	break;
	    	        }
	    	        
	    	        
	    	 } while (respuesta.equals("7"));
	    }

	    private static void menuAnalista(Sistema sistema) {
	    	
	    	String respuesta;
	    	
	    	 do {
	    	        System.out.println("\n===== PANEL ANALISTA =====");
	    	        System.out.println("1. Top 10 Mejores Hechizos");
	    	        System.out.println("2. Top 3 Mejores Magos");
	    	        System.out.println("3. Mostrar todos los Hechizos");
	    	        System.out.println("4. Mostrar todos los Magos");
	    	        System.out.println("5. Mostrar todos los Hechizos junto a su puntuación");
	    	        System.out.println("6. Mostrar todos los Magos junto a su puntuación");
	    	        System.out.println("7. Volver");
	    	        System.out.print("> ");
	    	        
	    	        respuesta = s.nextLine();
	    	        
	    	        switch (respuesta) {
	    	        
	    	        case "1":
	    	        	sistema.mostrarTop10Hechizos();
	    	        	break;
	    	        case "2":
	    	        	sistema.mostrarTop3Magos();
	    	        	break;
	    	        case "3":
	    	        	sistema.mostrarTodosLosHechizos();
	    	        	break;
	    	        case "4":
	    	        	sistema.mostrarTodosLosMagos();
	    	        	break;
	    	        case "5":
	    	        	sistema.mostrarHechizosConPuntuacion();
	    	        	break;
	    	        case "6":
	    	        	sistema.mostrarMagosConPuntuacion();
	    	        	break;
	    	        case "7":
	    	        	System.out.println("Volviendo al menú principal...");
	    	        	break;
	    	        default:
	    	        	System.out.println("Opción inválida");
	    	        	break;
	    	        }
	    	
	    	 } while (!respuesta.equals("7"));
	    }

}
