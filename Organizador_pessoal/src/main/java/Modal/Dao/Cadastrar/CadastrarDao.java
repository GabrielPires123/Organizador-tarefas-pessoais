package Modal.Dao.Cadastrar;

import Entities.CadastrarAtividade;
import java.util.List;

public interface CadastrarDao
{
    void upgrade(CadastrarAtividade obj, Integer id);
    void delete(Integer id);
    void insert(CadastrarAtividade obj);
    CadastrarAtividade findById (Integer id);
    List<CadastrarAtividade> findAll ();


}
