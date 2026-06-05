package logica;

//Ignacio Antonio Pastén Durán 22.067.577-7 ICCI

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
	    	        	agregarMagoDesdeMenu(sistema);
	    	        	break;
	    	        case "2":
	    	        	modificarMagoDesdeMenu(sistema);
	    	        	break;
	    	        case "3":
	    	        	System.out.println("Ingrese el nombre del mago a eliminar: ");
	    	        	sistema.eliminarMago(s.nextLine());
	    	        	break;
	    	        case "4":
	    	        	agregarHechizoDesdeMenu(sistema);
	    	        	break;
	    	        case "5":
	    	        	modificarHechizoDesdeMenu(sistema);
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
	    	        
	    	        
	    	 } while (!respuesta.equals("7"));
	    }

	    private static void agregarHechizoDesdeMenu(Sistema sistema) {
	    	
	    	System.out.print("Ingrese el nombre del hechizo: ");
	        String nombre = s.nextLine();

	        if (sistema.buscarHechizoPorNombre(nombre) != null) {
	            System.out.println("Ya existe un hechizo con ese nombre.");
	            return;
	        }

	        System.out.print("Ingrese el tipo del hechizo (Fuego/Tierra/Planta/Agua): ");
	        String tipo = s.nextLine();

	        System.out.print("Ingrese el daño del hechizo: ");
	        int daño = Integer.parseInt(s.nextLine());

	        if (tipo.equalsIgnoreCase("Fuego")) {
	            System.out.print("Ingrese duración de quemadura: ");
	            int duracionQuemadura = Integer.parseInt(s.nextLine());

	            sistema.agregarHechizo(new HechizoFuego(nombre, daño, duracionQuemadura));

	        } else if (tipo.equalsIgnoreCase("Tierra")) {
	            System.out.print("Ingrese mejora de defensa: ");
	            int mejoraDefensa = Integer.parseInt(s.nextLine());

	            sistema.agregarHechizo(new HechizoTierra(nombre, daño, mejoraDefensa));

	        } else if (tipo.equalsIgnoreCase("Planta")) {
	            System.out.print("Ingrese duración de stun: ");
	            int duracionStun = Integer.parseInt(s.nextLine());

	            System.out.print("Ingrese cantidad de plantas: ");
	            int cantPlantas = Integer.parseInt(s.nextLine());

	            sistema.agregarHechizo(new HechizoPlanta(nombre, daño, duracionStun, cantPlantas));

	        } else if (tipo.equalsIgnoreCase("Agua")) {
	            System.out.print("Ingrese cantidad de heal: ");
	            int cantidadHeal = Integer.parseInt(s.nextLine());

	            System.out.print("Ingrese presión de agua: ");
	            int presionAgua = Integer.parseInt(s.nextLine());

	            sistema.agregarHechizo(new HechizoAgua(nombre, daño, cantidadHeal, presionAgua));

	        } else {
	            System.out.println("Tipo de hechizo inválido.");
	            return;
	        }

	        sistema.guardarHechizos();

	        System.out.println("Hechizo agregado correctamente.");
	    }

		private static void modificarHechizoDesdeMenu(Sistema sistema) {
	    	
	    	System.out.print("Ingrese el nombre del hechizo a modificar: ");
	        String nombreActual = s.nextLine();

	        Hechizo hechizoExistente = sistema.buscarHechizoPorNombre(nombreActual);

	        if (hechizoExistente == null) {
	            System.out.println("No existe un hechizo con ese nombre.");
	            return;
	        }

	        System.out.print("Ingrese el nuevo nombre del hechizo: ");
	        String nuevoNombre = s.nextLine();

	        System.out.print("Ingrese el nuevo tipo del hechizo (Fuego/Tierra/Planta/Agua): ");
	        String tipo = s.nextLine();

	        System.out.print("Ingrese el nuevo daño del hechizo: ");
	        int daño = Integer.parseInt(s.nextLine());

	        Hechizo hechizoNuevo = null;

	        if (tipo.equalsIgnoreCase("Fuego")) {
	            System.out.print("Ingrese duración de quemadura: ");
	            int duracionQuemadura = Integer.parseInt(s.nextLine());

	            hechizoNuevo = new HechizoFuego(nuevoNombre, daño, duracionQuemadura);

	        } else if (tipo.equalsIgnoreCase("Tierra")) {
	            System.out.print("Ingrese mejora de defensa: ");
	            int mejoraDefensa = Integer.parseInt(s.nextLine());

	            hechizoNuevo = new HechizoTierra(nuevoNombre, daño, mejoraDefensa);

	        } else if (tipo.equalsIgnoreCase("Planta")) {
	            System.out.print("Ingrese duración de stun: ");
	            int duracionStun = Integer.parseInt(s.nextLine());

	            System.out.print("Ingrese cantidad de plantas: ");
	            int cantPlantas = Integer.parseInt(s.nextLine());

	            hechizoNuevo = new HechizoPlanta(nuevoNombre, daño, duracionStun, cantPlantas);

	        } else if (tipo.equalsIgnoreCase("Agua")) {
	            System.out.print("Ingrese cantidad de heal: ");
	            int cantidadHeal = Integer.parseInt(s.nextLine());

	            System.out.print("Ingrese presión de agua: ");
	            int presionAgua = Integer.parseInt(s.nextLine());

	            hechizoNuevo = new HechizoAgua(nuevoNombre, daño, cantidadHeal, presionAgua);

	        } else {
	            System.out.println("Tipo de hechizo inválido.");
	            return;
	        }

	        sistema.modificarHechizo(nombreActual, hechizoNuevo);
	    }
	    	

		private static void modificarMagoDesdeMenu(Sistema sistema) {
	    	
	    	System.out.print("Ingrese el nombre del mago a modificar: ");
	        String nombreActual = s.nextLine();

	        Mago magoExistente = sistema.buscarMagoPorNombre(nombreActual);

	        if (magoExistente == null) {
	            System.out.println("No existe un mago con ese nombre.");
	            return;
	        }

	        System.out.print("Ingrese el nuevo nombre del mago: ");
	        String nuevoNombre = s.nextLine();

	        Mago magoNuevo = new Mago(nuevoNombre);

	        String respuesta;

	        do {
	            System.out.print("Ingrese el nombre del hechizo que dominará: ");
	            String nombreHechizo = s.nextLine();

	            Hechizo hechizo = sistema.buscarHechizoPorNombre(nombreHechizo);

	            if (hechizo != null) {
	                magoNuevo.agregarHechizo(hechizo);
	                System.out.println("Hechizo agregado al mago.");
	            } else {
	                System.out.println("No existe ese hechizo.");
	            }

	            System.out.print("¿Desea agregar otro hechizo? (s/n): ");
	            respuesta = s.nextLine();

	        } while (respuesta.equalsIgnoreCase("s"));
	        
	        if (magoNuevo.getHechizos().isEmpty()) {
	            System.out.println("No se puede dejar un mago sin hechizos.");
	            return; }

	        sistema.modificarMago(nombreActual, magoNuevo);
	    }
	    	

		private static void agregarMagoDesdeMenu(Sistema sistema) {
	    	System.out.print("Ingrese el nombre del mago: ");
	        String nombre = s.nextLine();

	        if (sistema.buscarMagoPorNombre(nombre) != null) {
	            System.out.println("Ya existe un mago con ese nombre.");
	            return;
	        }

	        Mago mago = new Mago(nombre);

	        String respuesta;

	        do {
	            System.out.print("Ingrese el nombre del hechizo que domina: ");
	            String nombreHechizo = s.nextLine();

	            Hechizo hechizo = sistema.buscarHechizoPorNombre(nombreHechizo);

	            if (hechizo != null) {
	                mago.agregarHechizo(hechizo);
	                System.out.println("Hechizo agregado al mago.");
	            } else {
	                System.out.println("No existe ese hechizo.");
	            }

	            System.out.print("¿Desea agregar otro hechizo? (s/n): ");
	            respuesta = s.nextLine();

	        } while (respuesta.equalsIgnoreCase("s"));
	        
	        if (mago.getHechizos().isEmpty()) {
	            System.out.println("No se puede agregar un mago sin hechizos.");
	            return; }

	        sistema.agregarMago(mago);
	        sistema.guardarMagos();

	        System.out.println("Mago agregado correctamente.");
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
