package calendariosLaborales.api.presentacion.controladores;

import calendariosLaborales.api.dominio.entidades.*;
import calendariosLaborales.api.core.servicios.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tipo")
public class TipoControlador {

    private final ITipoServicio servicio;

    public TipoControlador(ITipoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Tipo> listar() {
        return servicio.listar();
    }

    @GetMapping("/obtener/{id}")
    public Tipo obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @GetMapping("/buscar")
    public List<Tipo> buscar(@RequestParam String nombre) {
        return servicio.buscar(nombre);
    }

    @PostMapping("/agregar")
    public Tipo agregar(@RequestBody Tipo Tipo) {
        return servicio.agregar(Tipo);
    }

    @PutMapping("/modificar")
    public Tipo modificar(@RequestBody Tipo Tipo) {
        return servicio.modificar(Tipo);
    }

    @DeleteMapping("/eliminar/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}
