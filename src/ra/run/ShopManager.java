package ra.run;

import ra.bussiness.entity.User;
import ra.bussiness.impl.UserImp;
import ra.data.InputMethods;
import ra.data.MenuView;

public class ShopManager {
   static UserImp userImpl  = new UserImp();
    public static void main(String[] args) {
        while (true) {
            System.out.println(MenuView.MenuLogin);
            System.out.println("Nhập lựa chọn :");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("thoát");
                    System.exit(0);
                    break;
                default:
                    System.err.println("nhập không hợp lệ");
                    break;
            }
        }
    }
    public static void login(){
        // đăng nhập
        System.out.println("Nhập username");
        String username = InputMethods.getString();
        System.out.println("Nhập password");
        String password = InputMethods.getString();
        User userLogin = userImpl.handleLogin(username,password);
        if (userLogin != null) {
            // đăng nhập thành công
            if (userLogin.getPermission() == 1){
                // tải khoản quản trị
                AdminManagement.displayMenu();
                InputMethods.pressAnyKey();
            }else {
                // người dùng
                UserManagement.displayMenu();
                InputMethods.pressAnyKey();
            }
        }else {
            System.err.println("Tài khaonr hoặc mật khẩu không đúng");
            System.out.println("1.bạn chưa có tài khoản, đăng ký");
            System.out.println("2. đăng nhập lại");
            byte choose = InputMethods.getByte();
            if (choose==1){
                register();
            }else {
                login();
            }
        }
    }
    public static void register() {
        System.out.println("Đăng kí tài khoản");
        // nhập vào các thoong tin
        User newUser =  userImpl.inputData();
        boolean check= userImpl.create(newUser);
        if (check) {
            System.out.println("Thêm mới thành công ");
        }else {
            System.err.println("Thêm mới thất bại");
        }

    }
}
