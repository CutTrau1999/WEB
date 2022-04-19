package com.example.videoapi.model;

import com.example.videoapi.entity.Video;
import com.example.videoapi.utils.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoModel {
    private Connection conn;

    public VideoModel() {
        conn = ConnectionHelper.getConnection();
    }

    public List<Video> findAll() throws SQLException {
        List<Video> employees = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("select * from video ");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String link = rs.getString("link");
            employees.add(new Video(id, name, link));
        }
        return employees;
    }

    public Video findById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select * from video where id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String name = rs.getString("name");
            String link = rs.getString("link");

            return new Video(id, name, link);
        }
        return null;
    }

    public Video save(Video video) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("insert into video (name, salary) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, video.getName());
        stmt.setString(1, video.getLink());
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            ResultSet resultSetGeneratedKeys = stmt.getGeneratedKeys();
            if (resultSetGeneratedKeys.next()) {
                int id = resultSetGeneratedKeys.getInt(1);
                video.setId(id);
                return video;
            }
        }
        return null;
    }

//    public Video update(int id, Video updateVideo) throws SQLException {
//        PreparedStatement stmt = conn.prepareStatement("update video set name = ?, salary = ? where id = ?");
//        stmt.setString(1, updateEmoloyee.getName());
//        stmt.setDouble(2, updateEmoloyee.getSalary());
//        stmt.setInt(3, id);
//        int affectedRows = stmt.executeUpdate();
//        if (affectedRows > 0) {
//            updateEmoloyee.setId(id);
//            return updateEmoloyee;
//        }
//        return null;
//    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement stmtDelete = conn.prepareStatement("delete from video where id = ?");
        stmtDelete.setInt(1, id);
        int affectedRows = stmtDelete.executeUpdate();
        if (affectedRows > 0) {
            return true;
        }
        return false;
    }
}

