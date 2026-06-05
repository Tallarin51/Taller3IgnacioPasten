package logica;

import dominio.Hechizo;
import dominio.Mago;

public interface Sistema {
	
	void agregarHechizo(Hechizo hechizo);

    void agregarMago(Mago mago);

    void modificarMago(String nombreActual, Mago magoNuevo);
    

    void eliminarMago(String nombre);

    void modificarHechizo(String nombreActual, Hechizo hechizoNuevo);
    
    void eliminarHechizo(String nombre);

    void guardarHechizos();

    void guardarMagos();

    void mostrarTop10Hechizos();

    void mostrarTop3Magos();

    void mostrarTodosLosHechizos();

    void mostrarTodosLosMagos();

    void mostrarHechizosConPuntuacion();

    void mostrarMagosConPuntuacion();

    Hechizo buscarHechizoPorNombre(String nombre);

    Mago buscarMagoPorNombre(String nombre);
}
