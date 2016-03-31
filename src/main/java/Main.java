import com.google.gson.Gson;
import java.sql.*;
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

import static javax.measure.unit.SI.KILOGRAM;
import javax.measure.quantity.Mass;
import org.jscience.physics.model.RelativisticModel;
import org.jscience.physics.amount.Amount;

import com.heroku.sdk.jdbc.DatabaseUrl;
import spark.Request;

public class Main {

  public static void main(String[] args) {

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    get("/hello", (req, res) -> {
      RelativisticModel.select();

      String energy = System.getenv().get("ENERGY");

      Amount<Mass> m = Amount.valueOf(energy).to(KILOGRAM);
      return "E=mc^2: " + energy + " = " + m.toString();
    });

    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    get("/db", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add( "Read from DB: " + rs.getTimestamp("tick"));
        }

        attributes.put("results", output);
        return new ModelAndView(attributes, "db.ftl");
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return new ModelAndView(attributes, "error.ftl");
      } finally {
        if (connection != null) try{connection.close();} catch(SQLException e){}
      }
  }, new FreeMarkerEngine());
    
    
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
      
      post("/api/register", (req, res) -> {
          
          Map<String, Object> data = new HashMap<>();
          data.put("username", "username");
          data.put("password", "password");
          data.put("email", "email");
          return data;
      }, gson::toJson);
    

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
