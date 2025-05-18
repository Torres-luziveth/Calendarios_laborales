package calendariosLaborales.api.core.servicios;

import java.time.LocalDate;
import java.util.List;
import calendariosLaborales.api.dominio.entidades.Festivo;

public interface IFestivoServicio {

    boolean esFestivo(int idPais, int anio, int mes, int dia);
    
    List<Festivo> obtenerFestivosDelAnio(String pais, int anio);

    List<Festivo> listar();

    Festivo obtener(int id);

    List<Festivo> buscar(String nombre);

    Festivo agregar(Festivo festivo);

    Festivo modificar(Festivo festivo);

    boolean eliminar(int id);
}
