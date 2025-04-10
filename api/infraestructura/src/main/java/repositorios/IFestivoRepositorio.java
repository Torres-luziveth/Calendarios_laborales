package calendariosLaborales.api.infraestructura.repositorios;

import calendariosLaborales.api.dominio.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer> {
    
        // Historia de Usuario 1: Buscar festivos fijos por día, mes y país
        @Query("SELECT f FROM Festivo f WHERE f.dia = ?1 AND f.mes = ?2 AND f.pais.id = ?3 AND f.diasPascua IS NULL")
        List<Festivo> findFestivosFijosByDiaAndMesAndPaisId(int dia, int mes, Integer paisId);
    
        // Historia de Usuario 1: Obtener festivos variables por país
        @Query("SELECT f FROM Festivo f WHERE f.pais.id = ?1 AND f.diasPascua IS NOT NULL")
        List<Festivo> findFestivosVariablesByPaisId(Integer paisId);
    
        // Historia de Usuario 2: Listar festivos de un país como DTO
        @Query("SELECT f FROM Festivo f (f.nombre, " +
            "CASE WHEN f.diasPascua IS NULL THEN CONCAT(f.mes, '-', f.dia) " +
            "ELSE 'Variable' END) " +
            "FROM Festivo f WHERE f.pais.id = ?1")
        List<Festivo> listarFestivosPorPaisComoDTO(Integer idPais);
}
