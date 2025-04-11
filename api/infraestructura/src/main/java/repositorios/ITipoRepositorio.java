package calendariosLaborales.api.infraestructura.repositorios;

import calendariosLaborales.api.dominio.entidades.*;
import entidades.Tipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoRepositorio extends JpaRepository<Tipo, Integer> {
    
    @Query("SELECT t FROM Tipo t WHERE t.nombre LIKE '%' || ?1 || '%'")
    List<Tipo> buscar(String nombre);

    
}
