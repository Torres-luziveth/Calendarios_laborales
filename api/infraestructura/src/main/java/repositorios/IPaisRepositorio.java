package calendariosLaborales.api.infraestructura.repositorios;
import calendariosLaborales.api.dominio.entidades.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaisRepositorio extends JpaRepository<Pais, Integer> {
    @Query("SELECT p FROM Pais p WHERE p.nombre LIKE '%' || ?1 || '%'")
    List<Pais> buscar(String nombre);
    
}
