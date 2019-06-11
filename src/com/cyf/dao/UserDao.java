package com.cyf.dao;

import com.cyf.bean.User;
import com.cyf.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserDao {

    /**
     * 登录
     *
     * @param userName
     * @param userPassword
     * @return
     */
    public User login(String userName, String userPassword) {
        Connection conn = DbUtil.getConnection();
        String sql = "select * from user where user_name = ? and user_password = ?";
        User user = null;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            //为参数赋值
            pst.setString(1, userName);
            pst.setString(2, userPassword);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                user = new User();
                user.setUserId(rst.getInt("user_id"));
                user.setUserLevelId(rst.getInt("user_level_id"));
                user.setUserName(rst.getString("user_name"));
                user.setUserPassword(rst.getString("user_password"));
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 注册
     * 
     * @param userName
     * @param userPassword
     * @param userLevelId
     * @return
     */
    public boolean register(String userName, String userPassword, int userLevelId) {
        Connection conn = DbUtil.getConnection();
        String sql = "INSERT INTO user(user_name,user_password,user_level_id) VALUES (?,?,?)";//插入
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            //为参数赋值
            pst.setString(1, userName);
            pst.setString(2, userPassword);
            pst.setInt(3, userLevelId);
            int count = pst.executeUpdate();
            pst.close();
            return count > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 检查用户名重复
     *
     * @param userName
     * @return
     */
    public User checkUserName(String userName) {
        Connection conn = DbUtil.getConnection();
        String sql = "select * from user where user_name = ?";
        User user = null;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
           //为参数赋值
            pst.setString(1, userName);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                user = new User();
                //将user设置为数据库中的这个用户名对应的记录
                user.setUserId(rst.getInt("user_id"));
                user.setUserLevelId(rst.getInt("user_level_id"));
                user.setUserName(rst.getString("user_name"));
                user.setUserPassword(rst.getString("user_password"));
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    public boolean updateUser(User user) {
        Connection conn = DbUtil.getConnection();
        String sql = "UPDATE user set user_name = ? , user_password = ? where user_id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
          //为参数赋值
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getUserPassword());
            pst.setInt(3, user.getUserId());
            int count = pst.executeUpdate();
            pst.close();
            return count > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改用户信息
     *
     * @param userId
     * @return
     */
    public User getUser(int userId) {
        Connection conn = DbUtil.getConnection();
        String sql = "select * from user where user_id = " + userId;
        User user = null;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                user = new User();
                user.setUserId(rst.getInt("user_id"));
                user.setUserName(rst.getString("user_name"));
                user.setUserPassword(rst.getString("user_password"));
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
