package Modal.Dao.Cadastrar;

import Modal.Dao.Cadastrar.impl.AtividadeDaoJDBC;
import db.DB;

import java.sql.Connection;

public class DaoFactory {
    public static CadastrarDao creatAtividadeDAO(Connection conn) {
        return new AtividadeDaoJDBC(conn);
    }
}
