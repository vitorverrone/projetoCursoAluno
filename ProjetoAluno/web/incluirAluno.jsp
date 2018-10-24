<%@page import="java.util.ArrayList"%>
<%@page import="br.academico.Model.Curso"%>
<%@page import="br.academico.DAO.CursoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Inclusão de Aluno</h1>
        <form method="post" action="incluiALuno"><br/>
            RA: <input type="text" name="RA"/><br/>
            Nome: <input type="text" name="nome"/><br/>
            Curso: 
            <select name="idCurso">
                <%
                    CursoDAO cursos = new CursoDAO();
                    ArrayList<Curso> c = new ArrayList<Curso>();
                    c = cursos.getTodos();
                    
                    for(Curso cur : c){
                        out.print("<option value=\"" + cur.getIdCurso() + "\">"+ cur.getNomeCurso() + "</option>") ;
                    }
                %>
            </select>
            <a href="cadastrarCurso.jsp">Novo curso</a><br/>
            <input type="submit" value="Cadastrar"/>
        </form>
    </body>
</html>
