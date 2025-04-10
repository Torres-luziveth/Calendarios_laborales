package calendariosLaborales.api.core.servicios;

import java.util.List;
import calendariosLaborales.api.dominio.entidades.*;

public interface IFestivoServicio {

    public List<Festivo> listar();

    public Festivo obtener(int id);

    public Festivo agregar(Festivo festivo);

    public Festivo modificar(Festivo festivo);

    public boolean eliminar(int id);
}
