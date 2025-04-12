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

        get("/items", (req, res) -> {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT item_id, item, wh, ea, cs, pa, item_typ, str1, str2 FROM item");

            JsonArray result = new JsonArray();
            while (rs.next()) {
                JsonObject obj = new JsonObject();
                obj.addProperty("item_id", rs.getString("item_id"));
                obj.addProperty("item", rs.getInt("item"));
                obj.addProperty("wh", rs.getString("wh"));
                obj.addProperty("ea", rs.getInt("ea"));
                obj.addProperty("cs", rs.getInt("cs"));
                obj.addProperty("pa", rs.getInt("pa"));
                obj.addProperty("item_typ", rs.getString("item_typ"));
                obj.addProperty("str1", rs.getString("str1"));
                obj.addProperty("str2", rs.getString("str2"));
                result.add(obj);
            }

            rs.close(); stmt.close(); conn.close();
            res.type("application/json");
            return gson.toJson(result);
        });

        get("/lpns", (req, res) -> {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT lpn, item, qty, loc, str1 FROM lpn");

            JsonArray result = new JsonArray();
            while (rs.next()) {
                JsonObject obj = new JsonObject();
                obj.addProperty("lpn", rs.getString("lpn"));
                obj.addProperty("item", rs.getInt("item"));
                obj.addProperty("qty", rs.getInt("qty"));
                obj.addProperty("loc", rs.getString("loc"));
                obj.addProperty("str1", rs.getString("str1"));
                result.add(obj);
            }

            rs.close(); stmt.close(); conn.close();
            res.type("application/json");
            return gson.toJson(result);
        });
    }
}
