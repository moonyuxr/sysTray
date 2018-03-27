package SysTrayGuard.Boss.Tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import SysTrayGuard.Boss.Panel.Login.DesktopCapture;

/**
 * Description:托盘加载
 * Author:俞晓荣(779339162@qq.com)
 * Date:2018/3/27
 */
public class BossSystemTray implements ActionListener, MouseListener {

  private Image icon;// 托盘图标

  private TrayIcon trayIcon;

  private SystemTray systemTray;// 系统托盘

  private DesktopCapture frame; // 托盘所属主窗体

  private PopupMenu pop = new PopupMenu(); // 弹出菜单

  /**
   * 设置
   */
  private MenuItem set = new MenuItem("设置");

  /**
   * 获取消息
   */
  private MenuItem getMessage = new MenuItem("获取消息");

  /**
   * 重新登录
   */
  private MenuItem relogin = new MenuItem("重新登录");

  /**
   * 退出登录
   */
  private MenuItem exit = new MenuItem("退出登录");

  /**
   * Description:初始化托盘
   * Author:俞晓荣(779339162@qq.com)
   * Date:2018/3/27
   * @param frame 托盘所属主窗体
   */
  public BossSystemTray(DesktopCapture frame) {
    this.frame = frame;
    icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/xiaomai.png")).getImage();
    //如果系统支持托盘
    if (SystemTray.isSupported()) {
      //获取系统托盘
      systemTray = SystemTray.getSystemTray();
      //创建Boss托盘
      trayIcon = new TrayIcon(icon, "预算管理一体化系统守护", pop);
      //右击菜单添加菜单选项
      pop.add(set);
      pop.add(getMessage);
      pop.add(relogin);
      pop.add(exit);

      try {
        //将Boss托盘添加到系统托盘
        systemTray.add(trayIcon);
      } catch (AWTException e1) {
        e1.printStackTrace();
        trayIcon.addMouseListener(this);
      }
    }

    //一些事件的添加
    trayIcon.addMouseListener(this);
    getMessage.addActionListener(this);
    relogin.addActionListener(this);
    exit.addActionListener(this);
    set.addActionListener(this);
  }

  /**
   * 操作事件监听
   * @param e 事件
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == relogin) {
      frame.iconed = false;
      frame.setVisible(true);
      frame.setExtendedState(JFrame.NORMAL);
    } else if (e.getSource() == set) {
      frame.capture();
    } else {
      System.exit(0);
    }

  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 1 && e.getButton() != MouseEvent.BUTTON3) {
      frame.capture();
    }
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

}
