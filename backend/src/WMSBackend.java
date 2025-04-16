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
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT location, area FROM loc")) {

                JsonArray result = new JsonArray();
                while (rs.next()) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("location", rs.getString("location"));
                    obj.addProperty("area", rs.getString("area"));
                    result.add(obj);
                }

                res.type("application/json");
                return gson.toJson(result);

            } catch (SQLException e) {
                res.status(500);
                return "{\"error\":\"Database error: " + e.getMessage() + "\"}";
            }
        });

        get("/shipments", (req, res) -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT shipid, ship, car, shipsts, dock FROM shipment")) {

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

                res.type("application/json");
                return gson.toJson(result);

            } catch (SQLException e) {
                res.status(500);
                return "{\"error\":\"Database error: " + e.getMessage() + "\"}";
            }
        });

        get("/items", (req, res) -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT item_id, item, wh, ea, cs, pa, item_typ, str1, str2 FROM item")) {

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

                res.type("application/json");
                return gson.toJson(result);

            } catch (SQLException e) {
                res.status(500);
                return "{\"error\":\"Database error: " + e.getMessage() + "\"}";
            }
        });

        get("/lpns", (req, res) -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT lpn, item, qty, loc, str1 FROM lpn")) {

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

                res.type("application/json");
                return gson.toJson(result);

            } catch (SQLException e) {
                res.status(500);
                return "{\"error\":\"Database error: " + e.getMessage() + "\"}";
            }
        });

        get("/receiving/rcv", (req, res) -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT rcv_id, wh, treq FROM rcv")) {

                JsonArray result = new JsonArray();
                while (rs.next()) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("rcv_id", rs.getString("rcv_id"));
                    obj.addProperty("wh", rs.getString("wh"));
                    obj.addProperty("treq", rs.getString("treq"));
                    result.add(obj);
                }

                res.type("application/json");
                return gson.toJson(result);

            } catch (SQLException e) {
                res.status(500);
                return "{\"error\":\"Database error: " + e.getMessage() + "\"}";
            }
        });

        get("/receiving/rcvlin", (req, res) -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT rcvlin, rcv_id, item, wh FROM rcvlin")) {

                JsonArray result = new JsonArray();
                while (rs.next()) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("rcvlin", rs.getString("rcvlin"));
                    obj.addProperty("rcv_id", rs.getString("rcv_id"));
                    obj.addProperty("item", rs.getInt("item"));
                    obj.addProperty("wh", rs.getString("wh"));
                    result.add(obj);
                }

                res.type("application/json");
                return gson.toJson(result);

            } catch (SQLException e) {
                res.status(500);
                return "{\"error\":\"Database error: " + e.getMessage() + "\"}";
            }
        });

        get("/receiving/rcvlpn", (req, res) -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT lpn, rcvlin, rcv_id, item, qty FROM rcvlpn")) {

                JsonArray result = new JsonArray();
                while (rs.next()) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("lpn", rs.getString("lpn"));
                    obj.addProperty("rcvlin", rs.getString("rcvlin"));
                    obj.addProperty("rcv_id", rs.getString("rcv_id"));
                    obj.addProperty("item", rs.getInt("item"));
                    obj.addProperty("qty", rs.getInt("qty"));
                    result.add(obj);
                }

                res.type("application/json");
                return gson.toJson(result);

            } catch (SQLException e) {
                res.status(500);
                return "{\"error\":\"Database error: " + e.getMessage() + "\"}";
            }
        });

        get("/picking", (req, res) -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT pckwrk, pck_id, qty, wh FROM pck")) {

                JsonArray result = new JsonArray();
                while (rs.next()) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("pckwrk", rs.getString("pckwrk"));
                    obj.addProperty("pck_id", rs.getString("pck_id"));
                    obj.addProperty("qty", rs.getInt("qty"));
                    obj.addProperty("wh", rs.getString("wh"));
                    result.add(obj);
                }

                res.type("application/json");
                return gson.toJson(result);

            } catch (SQLException e) {
                res.status(500);
                return "{\"error\":\"Database error: " + e.getMessage() + "\"}";
            }
        });

        get("/packing", (req, res) -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT pack, packwrk_id, wh, packzon_id FROM packs")) {

                JsonArray result = new JsonArray();
                while (rs.next()) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("pack", rs.getString("pack"));
                    obj.addProperty("packwrk_id", rs.getString("packwrk_id"));
                    obj.addProperty("wh", rs.getString("wh"));
                    obj.addProperty("packzon_id", rs.getString("packzon_id"));
                    result.add(obj);
                }

                res.type("application/json");
                return gson.toJson(result);

            } catch (SQLException e) {
                res.status(500);
                return "{\"error\":\"Database error: " + e.getMessage() + "\"}";
            }
        });

        get("/outbound", (req, res) -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EWMS", "postgres", "sushisupertime8");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT ord_id, treq, wh FROM ord")) {

                JsonArray result = new JsonArray();
                while (rs.next()) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("ord_id", rs.getString("ord_id"));
                    obj.addProperty("treq", rs.getString("treq"));
                    obj.addProperty("wh", rs.getString("wh"));
                    result.add(obj);
                }

                res.type("application/json");
                return gson.toJson(result);

            } catch (SQLException e) {
                res.status(500);
                return "{\"error\":\"Database error: " + e.getMessage() + "\"}";
            }
        });
    }
}
