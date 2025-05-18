package calendariosLaborales.api.presentacion.controladores;

import calendariosLaborales.api.dominio.entidades.*;
import calendariosLaborales.api.core.servicios.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    private final IFestivoServicio servicio;

    public FestivoControlador(IFestivoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Festivo> listar() {
        return servicio.listar();
    }

    @GetMapping("/obtener/{id}")
    public Festivo obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @GetMapping("/buscar")
    public List<Festivo> buscar(@RequestParam String nombre) {
        return servicio.buscar(nombre);
    }

    @PostMapping("/agregar")
    public Festivo agregar(@RequestBody Festivo festivo) {
        return servicio.agregar(festivo);
    }

    @PutMapping("/modificar")
    public Festivo modificar(@RequestBody Festivo festivo) {
        return servicio.modificar(festivo);
    }

    @DeleteMapping("/eliminar/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }

    @GetMapping("/es-festivo/{idPais}/{anio}/{mes}/{dia}")
    public Map<String, Boolean> esFestivo(
            @PathVariable int idPais,
            @PathVariable int anio,
            @PathVariable int mes,
            @PathVariable int dia) {

        LocalDate fecha = LocalDate.of(anio, mes, dia);
        System.out.println("fecha:" +fecha);
        boolean resultado = servicio.esFestivo(idPais, anio, mes, dia);
        return Map.of("esFestivo", resultado);
    }

    @GetMapping("/{pais}/{anio}")
    public ResponseEntity<List<Festivo>> obtenerFestivosDelAnio(
            @PathVariable String pais,
            @PathVariable int anio) {
        List<Festivo> festivos = servicio.obtenerFestivosDelAnio(pais, anio);
        return ResponseEntity.ok(festivos);
    }
}
