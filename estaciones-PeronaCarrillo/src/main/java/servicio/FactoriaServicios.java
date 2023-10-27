package servicio;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import utils.PropertiesReader;

public class FactoriaServicios {

	private static final String PROPERTIES = "servicios.properties";
	
	private static Map<Class<?>, Object> servicios = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public static <T> T getServicio(Class<T> servicio){

		try {
			if(servicios.containsKey(servicio)) {
				return (T) servicios.get(servicio);
			}
			else {
				PropertiesReader properties = new PropertiesReader(PROPERTIES);
				String clase = properties.getProperty(servicio.getName());
				T servicioInstancia = (T) Class.forName(clase).getConstructor().newInstance();

				servicios.put(servicio, servicioInstancia);
				System.out.println("1");

				return servicioInstancia;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("No se ha podido obtener la implementación del servicio: " + servicio.getName());
		}
		
	}
}
