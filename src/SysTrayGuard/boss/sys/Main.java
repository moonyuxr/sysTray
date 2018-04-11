package SysTrayGuard.boss.sys;

import javax.swing.*;

import SysTrayGuard.boss.panel.login.DesktopCapture;
import SysTrayGuard.boss.panel.message.UpComingPanel;
import test.VersionUtil;

/**
 * Description:系统托盘守护程序启动入口
 * Author:俞晓荣(779339162@qq.com)
 * Date:2018/3/27
 */
public class Main {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {
      //根据客户的平台版本动态更改应用的外观
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      DesktopCapture desk = new DesktopCapture();
      //      InfoUtil i = new InfoUtil();
      //      i.show("消息", "111");
      // new VersionUtil();
//      VersionUtil.main(args);
      new UpComingPanel();

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
