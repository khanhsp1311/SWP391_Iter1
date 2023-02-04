/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Role;
import model.User;

/**
 *
 * @author DELL
 */
public class UserDAO extends DBContext {
    
    public User login(String email, String password) { // day la kiem tra xem co so dien thoai trung ko: tuc la phone_number la khoa
        String sql = "select * from users where uemail = ? "
                + "and upwad = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Role r = getRoleById(rs.getInt("roleID"));
                    return new User(rs.getInt("id"), rs.getString("uname"), rs.getString("upwad"), rs.getString("uemail"),
                        rs.getString("umobile"),r);
            }
        } catch (Exception e) {
        }
        return null;
    }
    
     public Role getRoleById(int id) { // id này là của category id bên trong sản phầm products
        String sql = "select * from role where id = ?"; // chú ý sửa chỗ này

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);                           // chú ý sửa chỗ này
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Role r = new Role();
                // lay bang id cua product
                // lấy ra cái tên thương hiệu của sản phẩm đấy
                r.setName(rs.getString("name"));
                return r;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void insert(User u) {
        String sql = "insert into users(uname,upwad,uemail,umobile,roleID) values(?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getUname());
            st.setString(2, u.getUpwad());
            st.setString(3, u.getUemail());
            st.setString(4, u.getUmobile());
            
            st.setInt(5, u.getRoleID2());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
     public User login2(String email, String phone_number) { // day la kiem tra xem co so dien thoai trung ko: tuc la phone_number la khoa
        String sql = "select * from users where uemail = ? or umobile = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, phone_number);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
//               return new User(rs.getInt("userId"), rs.getString("fullname"),email , rs.getString("phone_number"), rs.getString("address"), rs.getString("password"), rs.getDate("created_at"), rs.getInt("roleId"),rs.getDate("updated_at"), rs.getInt("deleted"));
               return new User(rs.getString("uname"), rs.getString("upwad"), rs.getString("uemail"),
                        rs.getString("umobile"),rs.getInt("roleID"));
            }  
        } catch (Exception e) {
        }
        return null;
    }
     
       public User getUserById(String email) {
        String sql = "select * from users where uemail = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);                           // chú ý sửa chỗ này
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                Role r = getRoleById(rs.getInt("roleID"));
                    return new User(rs.getInt("id"), rs.getString("uname"), rs.getString("upwad"), rs.getString("uemail"),
                        rs.getString("umobile"),r);
            }
        } catch (Exception e) {
        }
        return null;
    }


    
    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
       User u = new User();
       u = ud.getUserById("khanhddqhe161879@fpt.edu.vn");
        System.out.println("login cua user: " +u.toString());
    }
}
