package calendariosLaborales.api.core.servicios;

import java.time.LocalDate;
import java.util.List;
import calendariosLaborales.api.dominio.entidades.*;
import entidades.Festivo;
import entidades.Tipo;

public interface IFestivoServicio {

    Boolean consultarSiEsFestivo(int idpais, LocalDate fecha);

    List<Festivo> obtenerFestivosDelAnio(String pais, int anio);

    public List<Festivo> listar();

    public Festivo obtener(int id);

    public List<Festivo> buscar(String nombre);

    public Festivo agregar(Festivo festivo);

    public Festivo modificar(Festivo festivo);

    public boolean eliminar(int id);
}
