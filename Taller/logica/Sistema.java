package logica;

import dominio.Hechizo;
import dominio.Mago;

public interface Sistema {
	
	//Agrega un hechizo al sistema.
	void agregarHechizo(Hechizo hechizo);
	
	//Agrega un mago al sistema.
    void agregarMago(Mago mago);
    
    //Modifica un mago existente.
    void modificarMago(String nombreActual, Mago magoNuevo);
    
    //Elimina un mago existente.
    void eliminarMago(String nombre);
    
    //Modifica un hechizo existente.
    void modificarHechizo(String nombreActual, Hechizo hechizoNuevo);
    
    //Elimina un hechizo existente.
    void eliminarHechizo(String nombre);
    
    //Guarda los hechizos en el archivo Hechizos.txt.
    void guardarHechizos();
    
    //Guarda los magos en el archivo Magos.txt.
    void guardarMagos();
    
    //Muestra los 10 hechizos con mayor puntuación.
    void mostrarTop10Hechizos();
    
    //Muestra los 3 magos con mayor puntuación.
    void mostrarTop3Magos();
    
    //Muestra todos los hechizos registrados.
    void mostrarTodosLosHechizos();
    
    //Muestra todos los magos registrados.
    void mostrarTodosLosMagos();
    
    //Muestra todos los hechizos con su puntuación.
    void mostrarHechizosConPuntuacion();
    
    //Muestra todos los magos con su puntuación.
    void mostrarMagosConPuntuacion();
    
    //Busca un hechizo por su nombre.
    Hechizo buscarHechizoPorNombre(String nombre);
    
    //Busca un mago por su nombre.
    Mago buscarMagoPorNombre(String nombre);
}
