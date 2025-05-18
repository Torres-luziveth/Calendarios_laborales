package calendariosLaborales.api.aplicacion.servicios;

import calendariosLaborales.api.core.servicios.IFestivoServicio;
import calendariosLaborales.api.dominio.entidades.Festivo;
import calendariosLaborales.api.infraestructura.repositorios.IFestivoRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;

@Service
public class FestivoServicioImpl implements IFestivoServicio {

    private final IFestivoRepositorio festivoRepositorio;

    public FestivoServicioImpl(IFestivoRepositorio festivoRepositorio) {
        this.festivoRepositorio = festivoRepositorio;
    }

    @Override
    public boolean esFestivo(int idPais, int anio, int mes, int dia) {
        LocalDate fecha = LocalDate.of(anio, mes, dia);
        var festivosPais = festivoRepositorio.findFestivosVariablesByPaisId(idPais);
        List<LocalDate> fechasFestivos = new ArrayList<>();

        LocalDate pascua = calcularFechaPascua(anio);

        for (var festivo : festivosPais) {
            LocalDate fechaFestivo;

            switch (festivo.getTipo().getId()) {
                case 1: // Fijo
                    fechaFestivo = LocalDate.of(anio, festivo.getMes(), festivo.getDia());
                    fechasFestivos.add(fechaFestivo);
                    break;

                case 2: // Ley Puente Festivo (lunes)
                    fechaFestivo = LocalDate.of(anio, festivo.getMes(), festivo.getDia());
                    if (fechaFestivo.getDayOfWeek() != DayOfWeek.MONDAY) {
                        fechaFestivo = fechaFestivo.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
                    }
                    fechasFestivos.add(fechaFestivo);
                    break;

                case 3:
                    fechaFestivo = pascua.plusDays(festivo.getDiasPascua());
                    break;

                case 4:
                    fechaFestivo = pascua.plusDays(festivo.getDiasPascua());
                    if (fechaFestivo.getDayOfWeek() != DayOfWeek.MONDAY) {
                        fechaFestivo = fechaFestivo.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
                    }
                    break;


                case 5: // Ley Puente Festivo Viernes
                    fechaFestivo = LocalDate.of(anio, festivo.getMes(), festivo.getDia());
                    if (fechaFestivo.getDayOfWeek() != DayOfWeek.FRIDAY) {
                        fechaFestivo = fechaFestivo.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
                    }
                    fechasFestivos.add(fechaFestivo);
                    break;
            }
        }

        return fechasFestivos.contains(fecha);
    }

    @Override
    public List<Festivo> obtenerFestivosDelAnio(String pais, int anio) {
        return festivoRepositorio.findAll().stream()
            .filter(f -> f.getFecha() != null &&
                         f.getPais().getNombre().equalsIgnoreCase(pais) &&
                         f.getFecha().getYear() == anio)
            .toList();
    }

    @Override
    public List<Festivo> listar() {
        return festivoRepositorio.findAll();
    }

    @Override
    public Festivo obtener(int id) {
        return festivoRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Festivo> buscar(String nombre) {
        return festivoRepositorio.buscar(nombre);
    }

    @Override
    public Festivo agregar(Festivo festivo) {
        return festivoRepositorio.save(festivo);
    }

    @Override
    public Festivo modificar(Festivo festivo) {
        if (festivoRepositorio.existsById(festivo.getId())) {
            return festivoRepositorio.save(festivo);
        }
        return null;
    }

    @Override
    public boolean eliminar(int id) {
        if (festivoRepositorio.existsById(id)) {
            festivoRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    // Método privado para calcular la fecha de Pascua usando el algoritmo de Gauss
    private LocalDate calcularFechaPascua(int anio) {
        int a = anio % 19;
        int b = anio / 100;
        int c = anio % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int mes = (h + l - 7 * m + 114) / 31;
        int dia = ((h + l - 7 * m + 114) % 31) + 1;

        return LocalDate.of(anio, mes, dia);
    }
}
