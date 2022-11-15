package JavaProject;

import com.sun.tools.jconsole.JConsolePlugin;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;



class LoginFrame extends JFrame {

    String id = "A";
    String pw = "aaaaaaaa";
    LoginFrame() {
        this.setLayout(new GridLayout(2,1));
        //JLabel Title Setting
        JLabel title = new JLabel("실시간 공장 추적 프로그램");
        title.setHorizontalAlignment(0);
        title.setAlignmentY(0);
        title.setFont(new Font("KOTRA_BOLD", 0, 26));
        this.add(title);

        //Login UI
        JPanel loginUI = new JPanel(new BorderLayout());
        JPanel idpwUI = new JPanel(new BorderLayout());

        JPanel idpwLabel = new JPanel(new GridLayout(2,1));

        JLabel idLabel = new JLabel("ID : ");
        JLabel pwLabel = new JLabel("PW : ");
        idpwLabel.add(idLabel);
        idpwLabel.add(pwLabel);

        JPanel idpwField = new JPanel(new GridLayout(2,1));

        JTextField idField = new JTextField();
        JTextField pwField = new JTextField();
        idpwField.add(idField);
        idpwField.add(pwField);

        idpwUI.add(idpwLabel,BorderLayout.WEST);
        idpwUI.add(idpwField,BorderLayout.CENTER);

        loginUI.add(idpwUI,BorderLayout.CENTER); // 아이디 비번 입력창


        //Enter or Quit Button Panel Setting
        JPanel botBtPanel = new JPanel(new GridLayout(1,3,5,0));

        JButton quitBt = new JButton("종료");
        JButton regiBt = new JButton("회원가입");
        JButton enterBt = new JButton("확인");

        botBtPanel.add(quitBt); //종료버튼
        quitBt.setBorder(BorderFactory.createLineBorder(Color.black));
        quitBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 회원가입 버튼 설정
        botBtPanel.add(regiBt);
        regiBt.setBorder(BorderFactory.createLineBorder(Color.black));
        regiBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼 이벤트
                new RegisterFrame();
            }
        });

        botBtPanel.add(enterBt); //확인버튼
        enterBt.setBorder(BorderFactory.createLineBorder(Color.black));
        enterBt.setSize(30,50);
        enterBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainFrame();
            }
        });
        botBtPanel.setBorder(BorderFactory.createEmptyBorder(20,5,20,5));
        loginUI.add(botBtPanel,BorderLayout.SOUTH);

        this.add(loginUI);

        loginUI.setBorder(BorderFactory.createEmptyBorder(0,20,10,20)); // 여백생성
        //Login Frame Setting
        setSize(400,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("로그인 화면");
    }

    class RegisterFrame extends JFrame {
        RegisterFrame() {
            this.setLayout(new BorderLayout(0,20));

            JLabel regi_Title = new JLabel("회원가입");
            JPanel regi_RegiPanel = new JPanel(new BorderLayout());
            JPanel regi_BtPanel = new JPanel(new GridLayout(1,2,5,0));

            //Title Setting
            regi_Title.setFont(new Font("KOTRA_BOLD", 0, 30));
            regi_Title.setHorizontalAlignment(0);
            regi_Title.setVerticalAlignment(0);
            regi_Title.setBorder(BorderFactory.createEmptyBorder(40,0,40,0));
            this.add(regi_Title,BorderLayout.NORTH);

            //FieldPanel Setting
            JPanel regi_LabelPanel = new JPanel(new GridLayout(3,1,0,70));
            JPanel regi_FieldPanel = new JPanel(new GridLayout(3,1,0,70));

            JLabel regi_IDLabel = new JLabel("아이디 :  ");
            JLabel regi_PWLabel = new JLabel("비밀번호 :  ");
            JLabel regi_PWCLabel = new JLabel("확인 :  ");

            regi_IDLabel.setHorizontalAlignment(4);
            regi_PWLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            regi_PWCLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            JTextField regi_IDField = new JTextField();
            JTextField regi_PWField = new JTextField();
            JTextField regi_PWConfirmField = new JTextField();

            regi_LabelPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
            regi_FieldPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));

            //Adding
            regi_LabelPanel.add(regi_IDLabel);
            regi_LabelPanel.add(regi_PWLabel);
            regi_LabelPanel.add(regi_PWCLabel);
            regi_FieldPanel.add(regi_IDField);
            regi_FieldPanel.add(regi_PWField);
            regi_FieldPanel.add(regi_PWConfirmField);

            regi_RegiPanel.add(regi_LabelPanel,BorderLayout.WEST);
            regi_RegiPanel.add(regi_FieldPanel,BorderLayout.CENTER);

            this.add(regi_RegiPanel);
            //BtPanel Setting
            JButton regi_quitBt = new JButton("취소");
            JButton regi_createBt = new JButton("생성");

            regi_quitBt.setBorder(BorderFactory.createLineBorder(Color.black));
            regi_createBt.setBorder(BorderFactory.createLineBorder(Color.black));

            regi_BtPanel.add(regi_quitBt);
            regi_BtPanel.add(regi_createBt);

            regi_BtPanel.setBorder(BorderFactory.createEmptyBorder(30,5,10,5));
            this.add(regi_BtPanel,BorderLayout.SOUTH);

            regi_quitBt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            regi_createBt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String id = regi_IDField.getText();
                    String pw = regi_PWField.getText();
                    String pwc = regi_PWConfirmField.getText();


                    Boolean pwConfrim = pw.equals(pwc) ? true : false;

                    if(pwConfrim == true) {

                    }
                }
            });

            //Frame Setting
            setLocationRelativeTo(this);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("회원가입");
            setSize(300,500);
        }
    }
} // 로그인 화면
class MainFrame extends JFrame implements ActionListener {

    JPanel p1, p2,p3;
    JLabel factoryTitle;
    JLabel[] factoryStatusLabel = new JLabel[8]; //2x4

    String[] factorySenses = {"온도" , "64℃" , "벨트 1 속도" , "0.2D", "소비전력", "50KW" ,"화재경보" , "False"};
    public MainFrame() {

        Container c = getContentPane();


        c.setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new GridLayout(10,1,0,5));
        JPanel p2 = new JPanel(new BorderLayout());

        JScrollPane leftSideScroll = new JScrollPane(p1);
        JButton[] leftSideButtons = new JButton[10];

        c.add(leftSideScroll,BorderLayout.WEST);

        //p2
        c.add(p2,BorderLayout.CENTER);
        p2.setBackground(Color.decode("#B2FFFF"));

        factoryTitle = new JLabel("열람하고자 하는 공장을 선택해주세요.");
        factoryTitle.setHorizontalAlignment(0);
        factoryTitle.setFont(new Font("KOTRA_BOLD", 0, 20));
        factoryTitle.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        p2.add(factoryTitle,BorderLayout.NORTH);
        p3 = new JPanel(new GridLayout(4,2,1,1));
        p2.add(p3,BorderLayout.CENTER);
        p3.setBorder(BorderFactory.createEmptyBorder(100,150,100,150));
        //p2 end

        for(int i=0;i<factoryStatusLabel.length;i++) {
            factoryStatusLabel[i] = new JLabel(" ");
            factoryStatusLabel[i].setHorizontalAlignment(0);
            p3.add(factoryStatusLabel[i]);
            p3.setBackground(Color.decode(ColorSet.lightGreen));

            factoryStatusLabel[i].setBorder(BorderFactory.createLineBorder(Color.black));
        }


        //Left Side Buttons Setting
        for(int i=0;i<leftSideButtons.length;i++) {
            leftSideButtons[i] = new JButton("Factory " + (i+1));
            leftSideButtons[i].setPreferredSize(new Dimension(200,50));
            leftSideButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            leftSideButtons[i].setFocusable(true);
            leftSideButtons[i].setBackground(Color.decode(ColorSet.lightBeige));
            leftSideButtons[i].setOpaque(true);
            leftSideButtons[i].addActionListener(this);
            p1.add(leftSideButtons[i]);
        }




        //Frame Setting
        setSize(800,500);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("RealTime Factory Observer Program");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        setBackground(Color.decode("#E1B7C3"));


        factoryTitle.setText(e.getActionCommand() + " 상태"); // 공장이름



        //a = 공장 번호
        int a = Integer.parseInt(e.getActionCommand().split(" ")[1]);

        for(int i=0;i<factorySenses.length;i++) {
            factoryStatusLabel[i].setText(factorySenses[i]);
            factoryStatusLabel[i].setSize(50,50);
        }

        setVisible(true);
    }
}
public class Main {
    public static void main(String[] args) {

        new LoginFrame();
    }
}
