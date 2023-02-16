import com.laTale.common.Mouse;
import com.laTale.model.Location;
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
        Robot robot = new Robot();
        Mouse mouse = new Mouse(robot);
        Like like = new Like();
        Location likeLocation = like.findLike(robot);
        mouse.move(likeLocation);
        System.out.println("X:" + likeLocation.getX() + "====Y:" + likeLocation.getY());
    }
}
