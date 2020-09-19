package edu.eci.cvds.servlet;

import edu.eci.cvds.servlet.model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.MalformedInputException;
import java.util.LinkedList;
import java.util.Optional;


@WebServlet(urlPatterns = "/davidPete")

public class SuperServlet extends HttpServlet {

    public SuperServlet (){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer servlet = resp.getWriter();
        try {
            Optional<String> name = Optional.ofNullable(req.getParameter("id"));
            if(name.isPresent()){
                int id = Integer.parseInt(name.get());
                resp.setStatus(HttpServletResponse.SC_OK);
                LinkedList<Todo> todos = new LinkedList<>();
                Todo todo = Service.getTodo(id);
                todos.add(todo);
                servlet.write(Service.todosToHTMLTable(todos));
            }
        }catch (FileNotFoundException e){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            servlet.write("No existe un item con el indicardor dado");
        }catch (NumberFormatException e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            servlet.write("El parametro no es un entero");
        }catch (MalformedInputException e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            servlet.write("Error inteno en el servidor");
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            servlet.write("Requerimiento invalido");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer servlet = resp.getWriter();
        try {
            Optional<String> name = Optional.ofNullable(req.getParameter("id"));
            if(name.isPresent()){
                int id = Integer.parseInt(name.get());
                resp.setStatus(HttpServletResponse.SC_OK);
                LinkedList<Todo> todos = new LinkedList<>();
                Todo todo = Service.getTodo(id);
                todos.add(todo);
                servlet.write(Service.todosToHTMLTable(todos));
            }
        }catch (FileNotFoundException e){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            servlet.write("No existe un item con el indicardor dado");
        }catch (NumberFormatException e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            servlet.write("El parametro no es un entero");
        }catch (MalformedInputException e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            servlet.write("Error inteno en el servidor");
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            servlet.write("Requerimiento invalido");
        }
    }
}
