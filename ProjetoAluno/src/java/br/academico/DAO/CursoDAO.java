package br.academico.DAO;

import br.academico.Model.Curso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CursoDAO {
    
    private static final String urlBD = "jdbc:derby://localhost:1527/universidade";
    private static Connection conn = null;
    private static Statement stm = null;
    
    public ArrayList<Curso> getTodos() throws SQLException {
        ArrayList<Curso> c = new ArrayList<Curso>();

        conn = DriverManager.getConnection(urlBD, "root", "");

        stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String select = "select * from APP.\"tb_cursos\"";

        ResultSet resultados = stm.executeQuery(select);

        while (resultados.next()) {
            Curso cur = new Curso();
            cur.setIdCurso(resultados.getInt("idCurso"));
            cur.setNomeCurso(resultados.getString("nome_curso"));
            cur.setTipoCurso(resultados.getString("tipo_curso"));

            c.add(cur);
        }
        conn.close();

        return c;
    }
}
