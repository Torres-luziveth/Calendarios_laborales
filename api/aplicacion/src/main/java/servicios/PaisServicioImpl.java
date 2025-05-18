package calendariosLaborales.api.aplicacion.servicios;

import calendariosLaborales.api.core.servicios.IPaisServicio;
import calendariosLaborales.api.dominio.entidades.Pais;
import calendariosLaborales.api.infraestructura.repositorios.IPaisRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServicioImpl implements IPaisServicio {

    private final IPaisRepositorio paisRepositorio;

    public PaisServicioImpl(IPaisRepositorio paisRepositorio) {
        this.paisRepositorio = paisRepositorio;
    }

    @Override
    public List<Pais> listar() {
        return paisRepositorio.findAll();
    }

    @Override
    public Pais obtener(int id) {
        return paisRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Pais> buscar(String nombre) {
        return paisRepositorio.buscar(nombre);
    }

    @Override
    public Pais agregar(Pais pais) {
        return paisRepositorio.save(pais);
    }

    @Override
    public Pais modificar(Pais pais) {
        if (paisRepositorio.existsById(pais.getId())) {
            return paisRepositorio.save(pais);
        }
        return null;
    }

    @Override
    public boolean eliminar(int id) {
        if (paisRepositorio.existsById(id)) {
            paisRepositorio.deleteById(id);
            return true;
        }
        return false;
    }
}
