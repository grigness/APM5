package repository;

import Domain.MealPlanner;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    private List<MealPlanner> mealPlanner;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Repository() {
        mealPlanner = new ArrayList<>();
        openConnection();
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM MealPlanner");

            while (rs.next()) {
                mealPlanner.add(new MealPlanner(
                        rs.getString("name"),
                        rs.getInt("cookingTime"),
                        rs.getString("ingredients")))
                ;
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MealPlanner> getActivities() {
        List<MealPlanner> activity = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * from MealPlanner");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MealPlanner a = new MealPlanner(
                        rs.getString("name"),
                        rs.getInt("cookingTime"),
                        rs.getString("ingredients"));
                activity.add(a);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }

    public void add(MealPlanner activity) throws SQLException{

        String date=activity.getName();

        MealPlanner existingDate=this.getActivities().stream()
                .filter(a->a.getName().equals(date))
                .findFirst()
                .orElse(null);

        if(existingDate==null){
            try {
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO MealPlanner VALUES(?, ?, ?)");
                stmt.setString(1, activity.getName());
                stmt.setInt(2, activity.getCookingTime());
                stmt.setString(3,activity.getIngredients());

                stmt.executeUpdate();
                stmt.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }}
        else{
            PreparedStatement stmt = conn.prepareStatement("UPDATE MealPlanner SET cookingTime = ?, ingredients = ? WHERE name=?");
            stmt.setInt(1, activity.getCookingTime());
            stmt.setString(2, activity.getIngredients());
            stmt.setString(3,date);

            stmt.executeUpdate();
            stmt.close();
        }
    }
}
