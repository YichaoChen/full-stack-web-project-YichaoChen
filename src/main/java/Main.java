import com.google.gson.Gson;
import org.json.JSONObject;
import java.sql.*;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.heroku.sdk.jdbc.DatabaseUrl;
import spark.Request;

public class Main {

  public static void main(String[] args) {

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");


//    get("/db", (req, res) -> {
//      Connection connection = null;
//      Map<String, Object> attributes = new HashMap<>();
//      try {
//        connection = getConnection();
//
//        Statement stmt = connection.createStatement();
//        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//
//        ArrayList<String> output = new ArrayList<String>();
//        while (rs.next()) {
//          output.add( "Read from DB: " + rs.getTimestamp("tick"));
//        }
//
//        attributes.put("results", output);
//        return new ModelAndView(attributes, "db.ftl");
//      } catch (Exception e) {
//        attributes.put("message", "There was an error: " + e);
//        return new ModelAndView(attributes, "error.ftl");
//      } finally {
//        if (connection != null) try{connection.close();} catch(SQLException e){}
//      }
//  }, new FreeMarkerEngine());
    
    
    get("/following", (req, res) -> {
        ArrayList<String> title = new ArrayList<String>();
        title.add("title1");
        title.add("title2");
        title.add("title3");



        ArrayList<String> description = new ArrayList<String>();
        description.add("description1");
        description.add("description2");
        description.add("description3");



        Map<String, Object> attributes = new HashMap<>();
        attributes.put("title", title);
        attributes.put("description", description);



         return new ModelAndView(attributes, "following.ftl");
      }, new FreeMarkerEngine());
      
      Gson gson = new Gson();
      
      get("/api/index", (req, res) -> {
          List<Object> data = new ArrayList<>();
          Connection connection = null;
          try {
              connection = DatabaseUrl.extract().getConnection();
              Statement stmt = connection.createStatement();
              ResultSet rs = stmt.executeQuery("SELECT * FROM question");
              
              while (rs.next()) {
                  Map<String, Object> question = new HashMap<>();
                  
                  question.put("id", rs.getInt("id"));
                  question.put("title", rs.getString("title"));
                  question.put("description", rs.getString("description"));
                  data.add(question);
              }
          } catch (Exception e) {
              data.add("There was an error: " + e);
          } finally {
              if (connection != null)
                  try {
                      connection.close();
                  } catch (SQLException e) {
                  }
          }
          return data;
      }, gson::toJson);
      
      get("/api/test", (req, res) -> {
          List<Object> data = new ArrayList<>();
          Connection connection = null;
          try {
              connection = DatabaseUrl.extract().getConnection();
              Statement stmt = connection.createStatement();
              ResultSet rs = stmt.executeQuery("SELECT * FROM question");
              
              while (rs.next()) {
                  Map<String, Object> question = new HashMap<>();
                  
                  question.put("id", rs.getInt("id"));
                  question.put("title", rs.getString("title"));
                  question.put("description", rs.getString("description"));
                  data.add(question);
              }
          } catch (Exception e) {
              data.add("There was an error: " + e);
          } finally {
              if (connection != null)
                  try {
                      connection.close();
                  } catch (SQLException e) {
                  }
          }
          return data;
      }, gson::toJson);
      
      get("/index", (request, response) -> {
          ArrayList<String> test1 = new ArrayList<String>();
          test1.add("aaa");
          
          Map<String, Object> test2 = new HashMap<>();
          test2.put("test",test1);
          
          return new ModelAndView(test2, "index.ftl");
      }, new FreeMarkerEngine());
      
      post("/api/answer", (req, res) -> {
          
          Map<String, Object> data = new HashMap<>();
          data.put("answer", "test");
          return data;
      }, gson::toJson);
      
       post("api/register", (req, res) -> {
           Connection connection = null;
          
           System.out.println(req.body());
           try {
               connection = DatabaseUrl.extract().getConnection();
               JSONObject obj = new JSONObject(req.body());
               String username = obj.getString("username");
               String password = obj.getString("password");
               String email = obj.getString("email");
              
               String sql = "INSERT INTO users VALUES ('"+ username + "','" + password + "','" + email + "')";
              
               connection = DatabaseUrl.extract().getConnection();
               Statement stmt = connection.createStatement();
               stmt.executeUpdate(sql);
              
               ResultSet rs = stmt.executeQuery("SELECT * FROM users where username ='" + username + "'");
               Map<String, Object> currentuser = new HashMap<>();
              
               currentuser.put("username", rs.getString("username"));
               currentuser.put("email", rs.getString("email"));
              
               return currentuser;
               //  return req.body();
           } catch (Exception e) {
               return e.getMessage();
           } finally {
               if (connection != null) try{connection.close();} catch(SQLException e){}
           }
       });
    

  }

  private static Connection getConnection() throws URISyntaxException, SQLException {
    URI dbUri = new URI(System.getenv("DATABASE_URL"));
    int port = dbUri.getPort();
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();

    if (dbUri.getUserInfo() != null) {
      String username = dbUri.getUserInfo().split(":")[0];
      String password = dbUri.getUserInfo().split(":")[1];
      return DriverManager.getConnection(dbUrl, username, password);
    } else {
      return DriverManager.getConnection(dbUrl);
    }
  }

}
