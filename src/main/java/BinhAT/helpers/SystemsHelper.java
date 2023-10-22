package BinhAT.helpers;

import java.io.File;
import java.util.ArrayList;

public class SystemsHelper {

    //Hàm lấy đường dẫn tuyệt đối của project tính từ ổ cứng máy tính local
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }

    public static String splitString(String str, String valueSplit, int getPositon) {
        ArrayList<String> arrayListString = new ArrayList<>();
        for (String s : str.split(valueSplit, 0)) {
            arrayListString.add(s);
        }
        return arrayListString.get(getPositon);
    }
}
