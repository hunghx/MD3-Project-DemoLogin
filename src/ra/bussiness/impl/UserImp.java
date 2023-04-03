package ra.bussiness.impl;

import ra.bussiness.design.ICRUD;
import ra.bussiness.entity.User;
import ra.data.FileMethod;
import ra.data.InputMethods;
import ra.data.PathConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserImp implements ICRUD<User, Integer> {
    FileMethod<User> fileMethod = new FileMethod<>();

    @Override
    public List<User> readFromFile() {
        return fileMethod.readFromFile(PathConfig.USER_PATH);
    }

    @Override
    public boolean writeToFile(List<User> users) {
        return fileMethod.writeToFile(PathConfig.USER_PATH, users);
    }

    @Override
    public boolean create(User user) {
        // lấy ra danh sách tất cả user
        List<User> list = getAll();
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(user);
        writeToFile(list);
        return true;
    }

    @Override
    public User inputData() {
        List<User> list = getAll();
        if (list == null) {
            list = new ArrayList<>();
        }
        User user = new User();
        // tạo id tự tăng
        if (list.size() == 0) {
            user.setUserId(1);
        } else {
            int newId = list.get(list.size() - 1).getUserId() + 1;
            user.setUserId(newId);
        }
        // nhập tên
        System.out.println("nhập username");
        user.setUsername(InputMethods.getString());
        System.out.println("Nhập password");
        user.setPassword(InputMethods.getString());
        // nhập lại mật khẩu
        while (true) {
            System.out.println("xác nhận mật khẩu");
            String rePass = InputMethods.getString();
            if (!user.getPassword().equals(rePass)) {
                System.err.println("Mật khẩu không trung khớp , vui lòng nhập lại");
            } else {
                break;
            }
        }
        user.setPermission(0);
        user.setStatus(true);
        return user;
    }

    @Override
    public boolean update(Integer id) {
        User userEdit = findById(id);
        if (userEdit == null) {
            return false;
        }
        System.out.println("Nhập full name mới");
        userEdit.setFullName(InputMethods.getString());
        System.out.println("nhập phonenumber mới");
        userEdit.setPhoneNumber(InputMethods.getString());
        System.out.println("Nhập địa chỉ mới");
        userEdit.setAddress(InputMethods.getString());
        System.out.println("Nhập lại mật khẩu");
        userEdit.setPassword(InputMethods.getString());

        // cập nhật lại vào list
        List<User> listUpdate = new ArrayList<>();
        for (User user : getAll()) {
            if (user.getUserId() == userEdit.getUserId()) {
                listUpdate.add(userEdit);
            } else {
                listUpdate.add(user);
            }
        }
        return writeToFile(listUpdate);
    }

    @Override
    public boolean delete(Integer integer) {
        // lấy ra danh sách
        List<User> list = getAll();
        User userDelete = findById(integer);
        if (userDelete == null) {
            return false;
        }
        list.remove(userDelete);
        return true;
    }

    @Override
    public List<User> searchByName(String name) {
        List<User> list = getAll();
        List<User> listSearch = new ArrayList<>();
        for (User user : list) {
            if (user.getFullName().contains(name)) {
                listSearch.add(user);
            }
        }
        return listSearch;
    }

    @Override
    public User findById(Integer integer) {
        List<User> list = getAll();
        for (User user : list) {
            if (user.getUserId() == integer) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return readFromFile();
    }

    public User handleLogin(String username, String password) {
        List<User> list = getAll();
        if (list == null) {
            return null;
        }
        for (User user : getAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
