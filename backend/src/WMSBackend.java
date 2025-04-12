import static spark.Spark.*;
import java.sql.*;
import com.google.gson.*;

public class WMSBackend {
    public static void main(String[] args) {
        Gson gson = new Gson();

        options("/*", (req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
            return "OK";
        });

        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));

        get("/locations", (req, res) -> {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT location, area FROM loc");

            JsonArray result = new JsonArray();
            while (rs.next()) {
                JsonObject obj = new JsonObject();
                obj.addProperty("location", rs.getString("location"));
                obj.addProperty("area", rs.getString("area"));
                result.add(obj);
            }

            rs.close(); stmt.close(); conn.close();
            res.type("application/json");
            return gson.toJson(result);
        });

        get("/shipments", (req, res) -> {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT shipid, ship, car, shipsts, dock FROM shipment");

            JsonArray result = new JsonArray();
            while (rs.next()) {
                JsonObject obj = new JsonObject();
                obj.addProperty("shipid", rs.getString("shipid"));
                obj.addProperty("ship", rs.getString("ship"));
                obj.addProperty("car", rs.getString("car"));
                obj.addProperty("shipsts", rs.getString("shipsts"));
                obj.addProperty("dock", rs.getString("dock"));
                result.add(obj);
            }

            rs.close(); stmt.close(); conn.close();
            res.type("application/json");
            return gson.toJson(result);
        });
    }
}
