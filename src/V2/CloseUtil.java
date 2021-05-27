
import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {
    // 可变参数
    public static void closeAll(Closeable ...able){
        for(Closeable c : able){
            try{
                c.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
