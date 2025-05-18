package calendariosLaborales.api.presentacion.controladores;

import calendariosLaborales.api.dominio.entidades.*;
import calendariosLaborales.api.core.servicios.*;
import calendariosLaborales.api.aplicacion.servicios.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import java.text.SimpleDateFormat;



import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.time.DateTimeException;




@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    private final IFestivoServicio servicio;
    private ServiciosFechas serviciosFechas;


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
    public ResponseEntity<?> esFestivo(
            @PathVariable int idPais,
            @PathVariable int anio,
            @PathVariable int mes,
            @PathVariable int dia) {

        try {
            LocalDate fecha = LocalDate.of(anio, mes, dia);
            System.out.println("fecha: " + fecha);
            boolean resultado = servicio.esFestivo(idPais, anio, mes, dia);
            return ResponseEntity.ok(Map.of("esFestivo", resultado));
        } catch (DateTimeException e) {
            // Retorna un 400 Bad Request con mensaje claro
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Fecha inválida: " + anio + "-" + mes + "-" + dia));
        }
    }


    @GetMapping("/{pais}/{anio}")
    public ResponseEntity<List<Festivo>> obtenerFestivosDelAnio(
            @PathVariable String pais,
            @PathVariable int anio) {
        List<Festivo> festivos = servicio.obtenerFestivosDelAnio(pais, anio);
        return ResponseEntity.ok(festivos);
    }

    @GetMapping("/semana-santa/{anio}")
    public String getInicioSemanaSanta(@PathVariable int anio) {
        Date fecha = ServiciosFechas.getInicioSemanSanta(anio);
        return new SimpleDateFormat("yyyy-MM-dd").format(fecha);
    }

    @GetMapping("/agregar-dias/{fecha}/{dias}")
    public String agregarDias(
            @PathVariable String fecha,
            @PathVariable int dias) {

        LocalDate fechaInicial = LocalDate.parse(fecha);
        LocalDate resultado = fechaInicial.plusDays(dias);
        return resultado.toString(); // Ej: "2025-05-30"
    }


    @GetMapping("/siguiente-lunes/{fecha}")
    public String siguienteLunes(@PathVariable String fecha) {
        LocalDate fechaInicial = LocalDate.parse(fecha);
        LocalDate siguienteLunes = fechaInicial.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        return siguienteLunes.toString(); // Ejemplo: "2025-04-14"
    }



    @GetMapping("/domingo-pascua/{anio}")
    public ResponseEntity<String> getDomingoPascua(@PathVariable int anio) {
        Date fecha = ServiciosFechas.getDomingoPascua(anio);
        String fechaFormateada = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
        return ResponseEntity.ok(fechaFormateada);
    }


}
