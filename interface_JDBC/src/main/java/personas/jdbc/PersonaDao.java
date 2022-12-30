package personas.jdbc;

import personas.Model.PersonaDTO;

import java.sql.SQLException;
import java.util.List;

public interface PersonaDao {

    public List<PersonaDTO> select() throws SQLException;

    public int insert(PersonaDTO personaDTO) throws SQLException;

    public int update(PersonaDTO personaDTO) throws SQLException;

    public int delete(PersonaDTO personaDTO) throws SQLException;


}
