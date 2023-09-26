package BinhAT.helpers;

import java.io.File;

public class SystemsHelper {

    //Hàm lấy đường dẫn tuyệt đối của project tính từ ổ cứng máy tính local
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }
}
