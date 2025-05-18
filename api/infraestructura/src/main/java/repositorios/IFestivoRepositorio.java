package calendariosLaborales.api.infraestructura.repositorios;

import calendariosLaborales.api.dominio.entidades.Festivo;
import calendariosLaborales.api.dtos.FestivoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer> {

    // Festivos fijos por día, mes y país
    @Query("SELECT f FROM Festivo f WHERE f.dia = ?1 AND f.mes = ?2 AND f.pais.id = ?3 AND f.diasPascua IS NULL")
    List<Festivo> findFestivosFijosByDiaAndMesAndPaisId(int dia, int mes, Integer paisId);

    // Festivos variables por país
    @Query("SELECT f FROM Festivo f WHERE f.pais.id = ?1")
    List<Festivo> findFestivosVariablesByPaisId(Integer paisId);

    // Listar festivos como DTO
    @Query("SELECT new calendariosLaborales.api.dtos.FestivoDTO(f.nombre, f.dia, f.mes, f.diasPascua) " +
           "FROM Festivo f WHERE f.pais.id = ?1")
    List<FestivoDTO> listarFestivosPorPaisComoDTO(Integer idPais);

    // Buscar por nombre
    @Query("SELECT f FROM Festivo f WHERE LOWER(f.nombre) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Festivo> buscar(String nombre);
    
}
