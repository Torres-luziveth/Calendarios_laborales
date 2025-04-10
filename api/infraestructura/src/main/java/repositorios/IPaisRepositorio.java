package calendariosLaborales.api.infraestructura.repositorios;
import calendariosLaborales.api.dominio.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaisRepositorio extends JpaRepository<Pais, Integer> {
    
}
