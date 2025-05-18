package calendariosLaborales.api.aplicacion.servicios;

import java.util.Date;
import java.util.Calendar;

public class ServiciosFechas {

    public static Date getInicioSemanSanta(int año) {
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;

        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        int dia = 15 + dias;
        int mes = 3;

        if (dia > 31) {
            dia = dia - 31;
            mes = 4;
        }

        return new Date(año - 1900, mes - 1, dia);
    }

    public static Date agregarDias(Date fecha, int dias) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE, dias);
        return calendario.getTime();
    }

    public static Date siguienteDia(Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
        if (diaSemana != Calendar.MONDAY) {
            if (diaSemana > Calendar.MONDAY)
                fecha = agregarDias(fecha, 9 - diaSemana);
            else
                fecha = agregarDias(fecha, 1);
        }
        return fecha;
    }
    
}

