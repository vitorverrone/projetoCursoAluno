package br.academico.DAO;

import br.academico.Model.Aluno;
import br.academico.Model.Curso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlunoDAO {
    
    private static final String urlBD = "jdbc:derby://localhost:1527/universidade";
    private static Connection conn = null;
    private static Statement stm = null;


    public ArrayList<Aluno> getTodos() throws SQLException {
        ArrayList<Aluno> alunos = new ArrayList();
        conn = DriverManager.getConnection(urlBD, "root", "");
        stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String select = "SELECT aluno.IDALUNO, aluno.\"RA\", aluno.\"NOME\", curso.\"idCurso\", curso.\"nome_curso\", curso.\"tipo_curso\"\n"
                + "FROM APP.\"tb_alunos\" as aluno, APP.\"tb_cursos\" as curso\n"
                + "where aluno.IDCURSO = curso.\"idCurso\"";

        ResultSet resultados = stm.executeQuery(select);

        while (resultados.next()) {
            Aluno a = new Aluno();
            Curso c = new Curso();
            a.setIdAluno(resultados.getInt("IDALUNO"));
            a.setRA(resultados.getInt("RA"));
            a.setNomeAluno(resultados.getString("NOME"));

            c.setIdCurso(resultados.getInt("idCurso"));
            c.setNomeCurso(resultados.getString("nome_curso"));
            c.setTipoCurso(resultados.getString("tipo_curso"));

            a.setCurso(c);

            alunos.add(a);
        }
        conn.close();
        return alunos;
    }
    
    public void inserirAluno(Aluno a, int idCurso) throws SQLException {
        conn = DriverManager.getConnection(urlBD, "root", "");
        
        PreparedStatement st = conn.prepareStatement("insert into \"tb_alunos\" (RA, NOME, IDCURSO) VALUES (?, ?, ?)");
        st.setInt(1, a.getRA());
        st.setString(2, a.getNomeAluno());
        st.setInt(3, idCurso);
        st.executeUpdate();
        conn.close();
    }
    
    
}
