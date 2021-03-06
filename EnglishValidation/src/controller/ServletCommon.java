package controller;

import interfacce.UserInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import model.Admin;
import model.Secretary;
import model.Student;
import model.Utils;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class ServletCommon.
 */
@WebServlet("/ServletCommon")
public class ServletCommon extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletCommon() {
    super();
  }

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * Method doPost().
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @SuppressWarnings("unchecked")
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

	  
    Integer result = 0;
    String error = "";
    String content = "";
    String redirect = "";
    PreparedStatement stmt = null;

    int flag = Integer.parseInt(request.getParameter("flag"));
    Connection conn = new DbConnection().getInstance().getConn();
    String sql = "";

    if (conn != null) {

      if (flag == 1) { // login
        String email = request.getParameter("email");
        String password = new Utils().generatePwd(request.getParameter("password"));
        try {
          sql =
              " SELECT  name, surname, sex, user_type, ban FROM user "
              + "WHERE TRIM(LOWER(email)) = TRIM(?) AND TRIM(password) = TRIM(?)";
          stmt = conn.prepareStatement(sql);
          stmt.setString(1, email.toLowerCase());
          stmt.setString(2, password);
          ResultSet r = stmt.executeQuery();
          if (r.wasNull()) {
            error = "Errore nell'esecuzione della Query";
          } else {
            int count = r.last() ? r.getRow() : 0;
            if (count == 1) {
              UserInterface user = null;
              String name = r.getString("name");
              String surname = r.getString("surname");
              char sex = r.getString("sex").charAt(0);
              int userType = r.getInt("user_type");
              Date ban=r.getDate("ban");
              
              
              
              if (userType == 0) { // Profilo Student
            	  System.out.println("Login in funzione,utente studente");
                redirect = request.getContextPath() + "/_areaStudent/viewRequest.jsp";
                user = new Student(email, name, surname, sex, password, userType);
                
              } else if (userType == 1) { // Profilo Secretary
            	  System.out.println("Login in funzione,utente segreteria");
                redirect = request.getContextPath() + "/_areaSecretary/viewRequest.jsp";
                user = new Secretary(email, name, surname, sex, password, userType);
                
              } else if (userType == 2) { // Profilo Admin
            	  System.out.println("Login in funzione,utente admin");
                redirect = request.getContextPath() + "/_areaAdmin/viewRequest.jsp";
                user = new Admin(email, name, surname, sex, password, userType);
              }
              else {
                throw new NumberFormatException("utente non valido");
              }
              
              if(ban!=null) { //Impedisce la normale fruizione del login
            	  redirect= request.getContextPath() + "/banned.jsp";
              }

              request.getSession().setAttribute("user", user);

              result = 1;
            } else {
              error = "Username o Password errati.";
            }
          }


          if (result == 0) {
            conn.rollback();
          } else {
            conn.commit();
          }

        } catch (Exception e) {
          error += e.getMessage();
        }
      } else if (flag == 2) { // Aggiornamento Nome
        String idUser = request.getParameter("idUser");
        String newName = request.getParameter("newName");

        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(newName);
        boolean b = m.find();


        if (newName.length() == 0 || newName.length() > 20 || b == true) {
            throw new IllegalArgumentException("Formato non corretto");
        }
        try {
          sql = "UPDATE user SET name = ? WHERE email = ?";
          stmt = conn.prepareStatement(sql);
          stmt.setString(1, newName);
          stmt.setString(2, idUser);
          if (stmt.executeUpdate() > 0) {
            content = "Nome Aggiornato";
            result = 1;
          } else {
            error = "Errore aggiornamento Nome.";
          }

          if (result == 0) {
            conn.rollback();
          } else {
            conn.commit();
          }

        } catch (Exception e) {
          error += e.getMessage();
        }
      } else if (flag == 3) { // Aggiornamento Cognome
        String idUser = request.getParameter("idUser");
        String newSurname = request.getParameter("newSurname");
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(newSurname);
        boolean b = m.find();

        if (newSurname.length() == 0 || newSurname.length() > 20 || b == true) {
            throw new IllegalArgumentException("Formato non corretto");
        }

        try {
          sql = "UPDATE user SET surname = ? WHERE email = ?";
          stmt = conn.prepareStatement(sql);
          stmt.setString(1, newSurname);
          stmt.setString(2, idUser);
          if (stmt.executeUpdate() > 0) {
            content = "Cognome Aggiornato";
            result = 1;
          } else {
            error = "Errore aggiornamento Cognome.";
          }

          if (result == 0) {
            conn.rollback();
          } else {
            conn.commit();
          }

        } catch (Exception e) {
          error += e.getMessage();
        }
      }


    } else {
      error = "Nessuna connessione al database.";
    }


    JSONObject res = new JSONObject();
    res.put("result", result);
    res.put("error", error);
    res.put("content", content);
    res.put("redirect", redirect);
    PrintWriter out = response.getWriter();
    out.println(res);
    response.setContentType("json");
  }
}
