package model.bo;

import java.util.List;

import model.bean.User;
import model.dao.UserDAO;

public class UserBO {
    private UserDAO userDAO = new UserDAO();

    // Kiểm tra đăng nhập
    public User checkLogin(String username, String password) {
        // Có thể thêm logic mã hóa password tại đây (MD5/BCrypt) trước khi so sánh
        return userDAO.checkLogin(username, password);
    }

    // Kiểm tra user tồn tại
    public boolean isUserExist(String username) {
        return userDAO.isUsernameExist(username);
    }

    // Xử lý đăng ký tài khoản
    public boolean registerUser(String username, String password, String confirmPassword, String fullname, String email) {
        // 1. Validate logic nghiệp vụ
        if (password == null || !password.equals(confirmPassword)) {
            return false; // Mật khẩu không khớp
        }
        
        if (userDAO.isUsernameExist(username)) {
            return false; // User đã tồn tại
        }

        // 2. Tạo đối tượng User chuẩn
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Nên mã hóa password tại đây
        user.setFullname(fullname);
        user.setEmail(email);
        user.setRole("user"); // Mặc định là user thường

        // 3. Gọi DAO lưu
        return userDAO.registerUser(user);
    }
    
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
}