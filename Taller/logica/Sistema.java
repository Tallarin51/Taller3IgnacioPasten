package logica;

public interface Sistema {
	
	void iniciar();
	
	void cargarHechizos();
	void cargarMagos();
	
	void guardarHechizos();
	void guardarMagos();
	
	void agregarMago();
	void modificarMago();
	void eliminarMago();
	
	void agregarHechizo();
	void modificarHechizo();
	void eliminarHechizo();
	
	void mostrarTop10Hechizos();
	void mostrarTop3Magos();
	
	void mostrarTodosLosHechizos();
	void mostrarTodosLosMagos();
	
	void mostrarHechizosConPuntuacion();
	void mostrarMagosConPuntuacion();
}
