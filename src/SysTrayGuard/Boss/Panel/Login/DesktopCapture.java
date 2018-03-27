package SysTrayGuard.Boss.Panel.Login;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import SysTrayGuard.Boss.Tray.BossSystemTray;
import test.VersionUtil;

public class DesktopCapture extends JFrame implements ActionListener {

  /**
   * 1.改变双击托盘截图为单击托盘截图 2.修复了托盘后无反应BUG 3.将放大镜提示框移至左上角和右上角
   */
  private static final long serialVersionUID = 1L;

  // JButton confirm;
  // BufferedImage desktopImg;
  BossSystemTray tray;

  public boolean iconed = false;

  private JButton btLog;

  private JButton mesg;

  private JTextField tfUser;

  private JPasswordField tfPwd;

  private JLabel laberuser;

  private JLabel laberpassword;

  public DesktopCapture() {
    super("守护程序");

    //mesg = new JButton("弹出消息框");
    // mesg.setBounds(new Rectangle(10, 10, 100, 30));//参数分别是坐标x，y，宽，高
    //this.setLayout(null);//设置布局管理器为空
    //this.add(mesg);
    btLog = new JButton("登     录");
    btLog.setBounds(new Rectangle(115, 220, 160, 30));//参数分别是坐标x，y，宽，高
    this.setLayout(null);//设置布局管理器为空
    this.add(btLog);
    laberuser = new JLabel("用户名：");
    laberuser.setBounds(new Rectangle(40, 115, 60, 25));
    this.add(laberuser);
    tfUser = new JTextField();
    tfUser.setBounds(new Rectangle(103, 115, 200, 25));
    this.add(tfUser);
    laberpassword = new JLabel("密  码：");
    laberpassword.setBounds(new Rectangle(40, 150, 60, 25));
    this.add(laberpassword);
    tfPwd = new JPasswordField();
    tfPwd.setBounds(new Rectangle(103, 150, 200, 25));
    this.add(tfPwd);

    init();
    // 当点击"-"最小化按钮时，系统会最小化到托盘
    addWindowListener(new WindowAdapter() {
      public void windowIconified(WindowEvent e) {
        iconed = true;
        setVisible(false);
      }

      // 当点击"X"关闭窗口按钮时，会询问用户是否要最小化到托盘
      // 是，表示最小化到托盘，否，表示退出
      public void windowClosing(WindowEvent e) {
        int option = JOptionPane.showConfirmDialog(DesktopCapture.this, "是否最小化到托盘？", "提示：",
          JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
          iconed = true;
          setVisible(false);
        } else {
          System.exit(0);
        }
      }
    });
    //重新计算窗口，以适合其子组件的首选大小和布局
    pack();
    //设置长宽
    setSize(380, 292);
    centered(this);
    //设置位置
    //    setLocation(500, 300);
    //用户单击窗口的关闭按钮时程序执行的操作
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //设置是否可以调整大小
    setResizable(false);
    //设置是否可见
    setVisible(true);

    //点击登录按钮触发的事件
    btLog.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //System.out.print(tfUser.getText());
        //System.out.println(tfPwd.getPassword());
        //if (tfUser.getText() == "1") {
        //  JOptionPane.showMessageDialog(null, "用户名或密码错误", "错误提示", JOptionPane.ERROR_MESSAGE);
        //} else {

        //缩小到托盘
        iconed = true;
        setVisible(false);
        //}
      }
    });

    //mesg.addActionListener(new ActionListener() {

    //  @Override
    //public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    //        JFrame j = new JFrame("1111");
    //        j.setLayout(null);
    //        j.setBounds(20, 20, 300, 100);
    //        j.setVisible(true);
    //}
    // });
  }

  void init() {
    tray = new BossSystemTray(DesktopCapture.this);
  }

  // 截图
  public void capture() {

  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {
      //根据客户的平台版本动态更改应用的外观
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      DesktopCapture desk = new DesktopCapture();
      //      InfoUtil i = new InfoUtil();
      //      i.show("消息", "111");
      // new VersionUtil();
      VersionUtil.main(args);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  //布局居中方法
  private void centered(Container container) {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int w = container.getWidth();
    int h = container.getHeight();
    container.setBounds((screenSize.width - w) / 2, (screenSize.height - h) / 2, w, h);
  }
}
