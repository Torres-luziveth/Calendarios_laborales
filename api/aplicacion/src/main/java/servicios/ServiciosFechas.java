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

    public static Date siguienteLunes(Date fecha) {
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
    public static Date getDomingoPascua(int anio) {
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

        return new Date(anio - 1900, mes - 1, dia); // cuidado con offset en Date
    }

    
}

