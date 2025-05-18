package calendariosLaborales.api.dtos;

public class FestivoDTO {
    private String nombre;
    private String fecha;

    public FestivoDTO(String nombre, Integer dia, Integer mes, Integer diasPascua) {
        this.nombre = nombre;

        if (diasPascua != null) {
            this.fecha = "Variable (días desde Pascua: " + diasPascua + ")";
        } else if (dia != null && mes != null) {
            this.fecha = String.format("%02d-%02d", dia, mes); // Ej: 01-01
        } else {
            this.fecha = "Fecha desconocida";
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

