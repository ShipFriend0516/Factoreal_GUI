package JavaProject;

import JavaProject.network.Callretrofit;
import JavaProject.network.LoginStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    LoginFrame holder;

    LoginFrame() {
        this.holder=this;
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
        JPanel botBtPanel = new JPanel(new GridLayout(1,2,5,0));

        JButton quitBt = new JButton("종료");
        JButton enterBt = new JButton("로그인");

        quitBt.setPreferredSize(new Dimension(100,30));
        enterBt.setPreferredSize(new Dimension(100,30));

        //종료버튼
        botBtPanel.add(quitBt);
        quitBt.setBorder(BorderFactory.createLineBorder(Color.black));
        quitBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        //확인버튼 설정
        botBtPanel.add(enterBt);
        enterBt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        enterBt.setOpaque(true);
        enterBt.setBackground(Color.BLACK);
        enterBt.setForeground(Color.white);
        enterBt.setSize(30,50);
        enterBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = Callretrofit.post_login_request(idField.getText(),pwField.getText());
                if (result.equals(LoginStatus.PERMITTED+"")){
                    new MainFrame(idField.getText());
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(holder, "아이디와 패스워드를 다시 확인하세요", "Message",JOptionPane.ERROR_MESSAGE );
                }
            }
        });

        botBtPanel.setBorder(BorderFactory.createEmptyBorder(20,5,10,5));
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
            regi_quitBt.setPreferredSize(new Dimension(120,30));
            regi_createBt.setPreferredSize(new Dimension(120,30));

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

                }
            });

            //Frame Setting
            setLocationRelativeTo(this);
            setVisible(true);
            setTitle("회원가입");
            setSize(300,500);
        }
    }
}