package Modal.Dao.Cadastrar.impl;

import Entities.CadastrarAtividade;
import Modal.Dao.Cadastrar.CadastrarDao;
import db.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtividadeDaoJDBC implements CadastrarDao {

    private Connection conn;

    public AtividadeDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void upgrade(CadastrarAtividade obj, Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE fila SET Nome = ?, NomeDescricao = ?, DataRegistro = ? WHERE Id = ?"
            );

            st.setString(1, obj.getNomeAtividade());
            st.setString(2, obj.getDescricao());
            st.setDate(3, new java.sql.Date(obj.getData().getTime()));
            st.setInt(4, id);

            int rows = st.executeUpdate();
            if (rows > 0) {
                System.out.println("Update realizado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM fila WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void insert(CadastrarAtividade obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO fila (Nome, NomeDescricao, DataRegistro) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, obj.getNomeAtividade());
            st.setString(2, obj.getDescricao());
            st.setDate(3, new java.sql.Date(obj.getData().getTime()));

            int rowsAffected = st.executeUpdate();

            // Recupera o ID gerado
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public CadastrarAtividade findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM fila WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return instanciaCad(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<CadastrarAtividade> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM fila");
            rs = st.executeQuery();

            List<CadastrarAtividade> list = new ArrayList<>();
            while (rs.next()) {
                list.add(instanciaCad(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    private CadastrarAtividade instanciaCad(ResultSet rs) throws SQLException {
        CadastrarAtividade obj = new CadastrarAtividade();
        obj.setId(rs.getInt("Id"));
        obj.setNomeAtividade(rs.getString("Nome"));
        obj.setDescricao(rs.getString("NomeDescricao"));
        obj.setData(rs.getDate("DataRegistro"));
        return obj;
    }
}
