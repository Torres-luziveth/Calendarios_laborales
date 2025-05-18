package calendariosLaborales.api.presentacion.controladores;

import calendariosLaborales.api.dominio.entidades.*;
import calendariosLaborales.api.core.servicios.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pais")
public class PaisControlador {

    private final IPaisServicio servicio;

    public PaisControlador(IPaisServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Pais> listar() {
        return servicio.listar();
    }

    @GetMapping("/obtener/{id}")
    public Pais obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @GetMapping("/buscar")
    public List<Pais> buscar(@RequestParam String nombre) {
        return servicio.buscar(nombre);
    }

    @PostMapping("/agregar")
    public Pais agregar(@RequestBody Pais pais) {
        return servicio.agregar(pais);
    }

    @PutMapping("/modificar")
    public Pais modificar(@RequestBody Pais pais) {
        return servicio.modificar(pais);
    }

    @DeleteMapping("/eliminar/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}
