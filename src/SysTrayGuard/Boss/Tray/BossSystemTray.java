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
 * Description:系统守护托盘程序加载
 * Author:俞晓荣(779339162@qq.com)
 * Date:2018/3/27
 */
public class BossSystemTray implements ActionListener, MouseListener {

  private Image icon;// 图标

  private TrayIcon trayIcon;

  private SystemTray systemTray;// 系统托盘

  private DesktopCapture frame; // 托盘所属主窗体

  private PopupMenu pop = new PopupMenu(); // 弹出菜单

  private MenuItem capture = new MenuItem("capture");

  private MenuItem show = new MenuItem("open");

  private MenuItem exit = new MenuItem("exit");

  public BossSystemTray(DesktopCapture frame) {
    this.frame = frame;
    // icon = Toolkit.getDefaultToolkit().getImage("./images/xiaomai.png");
    icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/xiaomai.png")).getImage();

    if (SystemTray.isSupported()) {
      systemTray = SystemTray.getSystemTray();
      trayIcon = new TrayIcon(icon, "单击直接截图-EasyCapture", pop);
      pop.add(capture);
      pop.add(show);
      pop.add(exit);

      try {
        systemTray.add(trayIcon);
      } catch (AWTException e1) {
        e1.printStackTrace();
        trayIcon.addMouseListener(this);
      }
    }
    trayIcon.addMouseListener(this);
    show.addActionListener(this);
    exit.addActionListener(this);
    capture.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == show) {
      frame.iconed = false;
      frame.setVisible(true);
      frame.setExtendedState(JFrame.NORMAL);
    } else if (e.getSource() == capture) {
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
