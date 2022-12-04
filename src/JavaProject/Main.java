package JavaProject;


import JavaProject.network.Callretrofit;
import JavaProject.network.DTO.SensorValue;
import JavaProject.network.LoginStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainFrame extends JFrame implements ActionListener {

    JPanel p1, p2, p3;
    JLabel factoryTitle;
    JTable sensorValueTable = new JTable();

    int presentFactoryIndex = -1;
    String userId;

    class GuiUpdater extends SwingWorker<Void, Void> {
        public GuiUpdater(MainFrame mainFrame) {
            JScrollPane scrollPane = new JScrollPane(sensorValueTable);
            sensorValueTable.setFillsViewportHeight(true);
            mainFrame.add(scrollPane);
        }

        @Override
        protected Void doInBackground() {
            while (true) {
                sensorValueTable.setModel(calculateModel());
                //Thread.sleep(2000);
            }
        }
    }

    public MainFrame(String userId) {
        this.userId = userId;
        Container c = getContentPane();


        c.setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new GridLayout(10, 1, 0, 5));
        JPanel factoryMainPanel = new JPanel(new BorderLayout());

        JScrollPane leftSideScroll = new JScrollPane(p1);
        leftSideScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        leftSideScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        JButton[] leftSideButtons = new JButton[10];

        c.add(leftSideScroll, BorderLayout.WEST);

        //factoryMainPanel
        c.add(factoryMainPanel, BorderLayout.CENTER);
        factoryMainPanel.setBackground(Color.decode("#B2FFFF"));

        //메인화면 공장이름 레이블 설정
        factoryTitle = new JLabel("열람하고자 하는 공장을 선택해주세요.");
        factoryTitle.setHorizontalAlignment(0);
        factoryTitle.setFont(new Font("KOTRA_BOLD", 0, 24));
        factoryTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        factoryMainPanel.add(factoryTitle, BorderLayout.NORTH);

        //공장 스탯 확인하는 패널 설정
        new GuiUpdater(this).execute();


        //각 공장에 대한 설정을 위한 버튼 패널
        JPanel factoryBtnPanel = new JPanel(new FlowLayout());
        factoryMainPanel.add(factoryBtnPanel, BorderLayout.SOUTH);
        factoryBtnPanel.setBackground(Color.decode(ColorSet.darkTeal));

        JButton[] factoryBtns = new JButton[5];
        String[] btnsName = {"이름 변경", "그래프로 보기", "열람 요청", "열람 요청 확인", "로그아웃"};
        for (int i = 0; i < factoryBtns.length; i++) {
            factoryBtns[i] = new JButton(btnsName[i]);
            factoryBtnPanel.add(factoryBtns[i]);
            factoryBtns[i].setBorder(BorderFactory.createLineBorder(Color.black));
            factoryBtns[i].setForeground(Color.white);
            factoryBtns[i].setPreferredSize(new Dimension(100, 30));
        }

        //이름 변경 버튼 이벤트        //Todo 이거 이름변경 기능 안될듯..
        factoryBtns[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (presentFactoryIndex == -1) {
                    return;
                }
                for (int i = 0; i < leftSideButtons.length; i++) {
                    if (leftSideButtons[i].getText().equals(MainFrame.this.userId)) {
                        presentFactoryIndex = i;
                    }
                }
                String changedName = JOptionPane.showInputDialog(null, "변경할 공장의 이름을 입력하세요.", MainFrame.this.userId);
                leftSideButtons[presentFactoryIndex].setText(changedName);
            }
        });
        factoryBtns[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (presentFactoryIndex == -1) {
                    return;
                }

            }
        });
        factoryBtns[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (presentFactoryIndex == -1) {
                    return;
                }

                new PermissionReqestFrame();
            }
        });

        factoryBtns[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (presentFactoryIndex == -1) {
                    return;
                }

                new PermissionReqestCheckFrame();
            }
        });
        factoryBtns[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame();
            }
        });

        //Left Side Buttons Setting 공장리스트 사이드바
        //Todo 팔로워십 팔로우 트루인 애들 리스트업->추가
        for (int i = 0; i < leftSideButtons.length; i++) {
            leftSideButtons[i] = new JButton("Factory " + (i + 1));
            leftSideButtons[i].setPreferredSize(new Dimension(200, 50));
            leftSideButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            leftSideButtons[i].setFocusable(true);
            leftSideButtons[i].setBackground(Color.decode(ColorSet.lightGray));
            leftSideButtons[i].setOpaque(true);
            leftSideButtons[i].addActionListener(this);
            p1.add(leftSideButtons[i]);
        }


        //Frame Setting
        setSize(800, 500);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("RealTime Factory Observer Program");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        setBackground(Color.decode("#E1B7C3"));

        userId = e.getActionCommand();
        presentFactoryIndex = e.getModifiers();
        factoryTitle.setText(e.getActionCommand() + " 상태"); // 공장이름

        //a = 공장 번호
        int a = Integer.parseInt(e.getActionCommand().split(" ")[1]);


        setVisible(true);
    }

    private DefaultTableModel calculateModel() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Sensor Name", "Value"}, 0);
        //컬럼 명: Sensor name 컬럼, Value 컬럼

        SensorValue[] sensorValueList = Callretrofit.get_sensor_value_resent_one(userId);
        Callretrofit.get_alarm(userId);

        if (sensorValueList != null) {
            for (final SensorValue value : sensorValueList) {
                if (value != null) {
                    model.addRow(new String[]{value.getName() + ": ", value.getValue()});
                }
            }
        }
        return model;
    }
}
public class Main {
    public static void main(String[] args) {
        new LoginFrame();
    }
}


