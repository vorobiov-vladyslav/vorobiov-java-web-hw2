package com.homework;

import com.homework.util.SqlFileLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {

    public static void main(String[] args) throws SQLException {
        String script = SqlFileLoader.load("sql/init_db.sql");
        Connection conn = Database.getInstance().getConnection();

        for (String stmt : script.split(";")) {
            String sql = stmt.trim();
            if (sql.isEmpty()) {
                continue;
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.execute();
            }
        }

        System.out.println("Database initialized: tables created.");
    }
}
