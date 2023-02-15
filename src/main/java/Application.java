import com.laTale.script.Like;

import java.awt.*;

/**
 * @Desc: 启动类
 * @Author: 泽露
 * @Date: 2023/2/14 7:17 PM
 * @Version: 1.initial version; 2023/2/14 7:17 PM
 */
public class Application {

    public static void main(String[] args) throws AWTException {
        Like like =new Like();
        Robot robot = new Robot();
        like.findLikeIcon(robot);
    }
}
