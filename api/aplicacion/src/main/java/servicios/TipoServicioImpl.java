package calendariosLaborales.api.aplicacion.servicios;

import calendariosLaborales.api.core.servicios.ITipoServicio;
import calendariosLaborales.api.dominio.entidades.Tipo;
import calendariosLaborales.api.infraestructura.repositorios.ITipoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServicioImpl implements ITipoServicio {

    private final ITipoRepositorio tiporepositorio;

    public TipoServicioImpl(ITipoRepositorio tiporepositorio) {
        this.tiporepositorio = tiporepositorio;
    }

    @Override
    public List<Tipo> listar() {
        return tiporepositorio.findAll();
    }

    @Override
    public Tipo obtener(int id) {
        return tiporepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Tipo> buscar(String nombre) {
        return tiporepositorio.buscar(nombre);
    }

    @Override
    public Tipo agregar(Tipo tipo) {
        return tiporepositorio.save(tipo);
    }

    @Override
    public Tipo modificar(Tipo tipo) {
        if (tiporepositorio.existsById(tipo.getId())) {
            return tiporepositorio.save(tipo);
        }
        return null;
    }

    @Override
    public boolean eliminar(int id) {
        if (tiporepositorio.existsById(id)) {
            tiporepositorio.deleteById(id);
            return true;
        }
        return false;
    }
}
