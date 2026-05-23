package com.homework;

import com.homework.model.LongestProject;
import com.homework.model.MaxProjectCountClient;
import com.homework.model.MaxSalaryWorker;
import com.homework.model.ProjectByClient;
import com.homework.model.ProjectPrice;
import com.homework.model.YoungestEldestWorker;
import com.homework.util.SqlFileLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() throws SQLException {
        String sql = SqlFileLoader.load("sql/find_max_projects_client.sql");
        List<MaxProjectCountClient> result = new ArrayList<>();
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new MaxProjectCountClient(
                        rs.getString("name"),
                        rs.getInt("project_count")
                ));
            }
        }
        return result;
    }

    public List<LongestProject> findLongestProject() throws SQLException {
        String sql = SqlFileLoader.load("sql/find_longest_project.sql");
        List<LongestProject> result = new ArrayList<>();
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new LongestProject(
                        rs.getString("name"),
                        rs.getInt("month_count")
                ));
            }
        }
        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws SQLException {
        String sql = SqlFileLoader.load("sql/find_max_salary_worker.sql");
        List<MaxSalaryWorker> result = new ArrayList<>();
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new MaxSalaryWorker(
                        rs.getString("name"),
                        rs.getInt("salary")
                ));
            }
        }
        return result;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() throws SQLException {
        String sql = SqlFileLoader.load("sql/find_youngest_eldest_workers.sql");
        List<YoungestEldestWorker> result = new ArrayList<>();
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new YoungestEldestWorker(
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getDate("birthday").toLocalDate()
                ));
            }
        }
        return result;
    }

    public List<ProjectPrice> printProjectPrices() throws SQLException {
        String sql = SqlFileLoader.load("sql/print_project_prices.sql");
        List<ProjectPrice> result = new ArrayList<>();
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new ProjectPrice(
                        rs.getString("name"),
                        rs.getLong("price")
                ));
            }
        }
        return result;
    }

    public List<ProjectByClient> findProjectsByClient(String clientName) throws SQLException {
        String sql = SqlFileLoader.load("sql/find_projects_by_client.sql");
        List<ProjectByClient> result = new ArrayList<>();
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, clientName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new ProjectByClient(
                            rs.getLong("id"),
                            rs.getDate("start_date").toLocalDate(),
                            rs.getDate("finish_date").toLocalDate()
                    ));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws SQLException {
        DatabaseQueryService service = new DatabaseQueryService();

        System.out.println("=== Max projects clients ===");
        service.findMaxProjectsClient().forEach(System.out::println);

        System.out.println("\n=== Longest project ===");
        service.findLongestProject().forEach(System.out::println);

        System.out.println("\n=== Max salary worker ===");
        service.findMaxSalaryWorker().forEach(System.out::println);

        System.out.println("\n=== Youngest and eldest workers ===");
        service.findYoungestEldestWorkers().forEach(System.out::println);

        System.out.println("\n=== Project prices ===");
        service.printProjectPrices().forEach(System.out::println);

        System.out.println("\n=== Projects of client 'LexCorp' (PreparedStatement) ===");
        service.findProjectsByClient("LexCorp").forEach(System.out::println);
    }
}
