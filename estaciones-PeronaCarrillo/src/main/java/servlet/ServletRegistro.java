package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Usuario;

public class ServletRegistro extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ServletRegistro() {
        super();
    }   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Usuario c = new Usuario(request.getParameter("nombre"),request.getParameter("apellidos"),request.getParameter("email"),request.getParameter("password"),request.getParameter("telefono"),LocalDate.parse(request.getParameter("fechaNacimiento")));
    	// Recupera el contexto de la aplicación
    	ServletContext app = getServletConfig().getServletContext();
    	// Intenta localizar la tabla de usuarios
    	HashMap<String, Usuario> usuariosHash = (HashMap<String,Usuario>) app.getAttribute("usuarios");
    	// Si no existe, la crea
    	if (usuariosHash == null){
    	    usuariosHash = new HashMap();
    	    app.setAttribute("usuarios", usuariosHash);
    	}
    	// Intenta guardar un usuario. Si existe el identificador, devuelve un error
    	if ( usuariosHash.get(c.getNombre()) != null ) {
    	    response.sendError(500, "Identificador de usuario duplicado");
    	    return;
    	} else {
    	    usuariosHash.put(c.getNombre(), c);
    	}
    	app.setAttribute("usuarios", usuariosHash);
        // Establecemos el tipo MIME de la respuesta
        response.setContentType("text/html");
        // Escribimos la respuesta
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        // Establecemos el título de la página HTML
        out.println("<title>" + "Procesamiento Datos Cliente" + "</title>");
        out.println("</head>");
        // Cuerpo de la página
        out.println("<body>");
        out.println("<B><P> Datos Cliente Procesados </P></B>");
        out.println("</body>");
        out.println("</html>");
    }
}