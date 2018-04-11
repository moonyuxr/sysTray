package SysTrayGuard.boss.panel.PopUpBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JDialog;

/**
 * Description:右下角弹出窗口布局及加载位置
 * Author:俞晓荣(779339162@qq.com)
 * Date:2018/4/11
 */
public class TipWindow extends JDialog {

  private static final long serialVersionUID = 8541659783234673950L;

  private int x, y;

  private int width, height;

  //屏幕大小
  private static Dimension dim;

  //屏幕外部填充
  private static Insets screenInsets;

  /**
   * Description:初始化弹出框
   * Author:俞晓荣(779339162@qq.com)
   * Date:2018/4/11
   * @param width  宽度
   * @param height 高度
   */
  public TipWindow(int width, int height) {
    this.width = width;
    this.height = height;
    //获取屏幕数据
    dim = Toolkit.getDefaultToolkit().getScreenSize();
    screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
    //放在右下角
    x = (int) (dim.getWidth() - width - 3);
    y = (int) (dim.getHeight() - screenInsets.bottom - 3);
    initComponents();
  }

  /**
   * Description:初始化组件
   * Author:俞晓荣(779339162@qq.com)
   * Date:2018/4/11
   */
  private void initComponents() {
    this.setSize(width, height);
    this.setLocation(x, y);
    this.setBackground(Color.black);
  }

  /**
   * Description:弹出框
   * Author:俞晓荣(779339162@qq.com)
   * Date:2018/4/11
   */
  public void run() {
    for (int i = 0; i <= height; i += 10) {
      try {
        this.setLocation(x, y - i);
        Thread.sleep(5);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
    //        此处代码用来实现让消息提示框5秒后自动消失,如果不需要自动关闭,则可注释掉
//    try {
//      Thread.sleep(3000);
//    } catch (InterruptedException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//    close(); // 窗口关闭
  }

  /**
   * Description:隐藏组件
   * Author:俞晓荣(779339162@qq.com)
   * Date:2018/4/11
   */
  public void close() {
    x = this.getX();
    y = this.getY();
    int ybottom = (int) dim.getHeight() - screenInsets.bottom;
    for (int i = 0; i <= ybottom - y; i += 10) {
      try {
        setLocation(x, y + i);
        Thread.sleep(5);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
    dispose();
  }
}
