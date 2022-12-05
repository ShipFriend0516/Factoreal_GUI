package JavaProject;


import JavaProject.network.Callretrofit;
import JavaProject.network.DTO.AlarmDTO;
import JavaProject.network.DTO.SensorValue;
import JavaProject.network.GetMyrelation;
import JavaProject.network.VO.Sensor;
import JavaProject.network.VO.userItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class MainFrame extends JFrame implements ActionListener {
    MainFrame holder;
    Container c;
    //>>>>>좌측 스크롤 패넗>>>>>>
    JPanel p1;
    JScrollPane leftSideScroll;
    JButton[] leftSideButtons;
    ArrayList<userItem> followsArray;//버튼에 들어갈 유저 리스트임

    //<<<<<좌측 스크롤 패넗<<<<<<

    //>>>>>공장 화면 패널>>>>>>>
    GuiUpdater guiUpdater;//<-3000ms 마다 네트워크에 userid필드값으로 새 센서값 요청
    JPanel factoryMainPanel;
    JLabel factoryTitle;//스크롤을 포함함
    JScrollPane sensorValueScroll;
    JPanel sensorValuePanel;//스크롤에 포함됨

    ArrayList<ValuePanel> valuePanels;
        //>>>>>>>.하단 바 패널>>>>>>>>>
        JPanel factoryBtnPanel;
        JButton[] factoryBtns;
        //<<<<<<<.하단 바 패널<<<<<<<<<<

    //<<<<< 공장 화면 패널<<<<<<

    String userId;
     synchronized void setUserID(String id){
          this.userId=id;

     }
    public static String loginId;


        class GuiUpdater extends SwingWorker<Void, Void> {

            public GuiUpdater(JPanel factoryMainPanel) {

            }
            @Override
            protected Void doInBackground() {

                while (true) {
                    updateData();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        private void updateData() {
            getContentPane().revalidate();
            getContentPane().repaint();
            SensorValue[] sensorValueList = Callretrofit.get_sensor_value_resent_one(userId);

            getContentPane().revalidate();
            getContentPane().repaint();
            Sensor[] sensor = Callretrofit.get_all_sensor(userId);
            List<AlarmDTO> alarmDTOList = Callretrofit.get_alarm(userId);//데이터 가져옴

            sensorValuePanel.removeAll();
            sensorValuePanel.setLayout(new GridLayout(sensorValueList.length + 5, 1, 5, 5));
            //sensorValuePanel=new JPanel(new GridLayout(sensorValueList.length + 5, 1, 5, 5));
            valuePanels = new ArrayList<>();//센서 데이터가 담긴 각각의 패널
            getContentPane().revalidate();
            getContentPane().repaint();
            int offset = 0;
            for (int i = 0; i < sensor.length; i++) {
                AlarmDTO alarmDTO = null;
                if (!alarmDTOList.isEmpty()) {
                    if (alarmDTOList.get(offset).getSensorIndex() == sensor[i].getIndex()) {
                        alarmDTO = alarmDTOList.get(offset);
                        offset++;
                    } else {
                        alarmDTO = null;
                    }
                }
                final SensorValue value = sensorValueList[i];
                final long sensorIndex = sensor[i].getIndex();

                valuePanels.add(new ValuePanel(sensorIndex, value.getName(), value.getValue(), alarmDTO));
            }
            for(ValuePanel v:valuePanels){
                sensorValuePanel.add(v);
            }
           //sensorValueScroll=new JScrollPane(sensorValuePanel);
            sensorValueScroll.removeAll();
            sensorValueScroll.add(sensorValuePanel);
            sensorValueScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            sensorValueScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            factoryMainPanel.add(sensorValueScroll, BorderLayout.CENTER);
            getContentPane().add(factoryMainPanel,BorderLayout.CENTER);
            getContentPane().revalidate();
            getContentPane().repaint();
        }

    public MainFrame(String loginId) {
        holder=this;
        this.userId = loginId;
        this.loginId =loginId;
        c = getContentPane();
        c.setLayout(new BorderLayout());
        factoryMainPanel = new JPanel(new BorderLayout());




        //Left Side Buttons Setting 공장리스트 사이드바
        //Todo 팔로워십 팔로우 트루인 애들 리스트업->추가
        getRelationShip();
        GetMyrelation getMyrelation=new GetMyrelation(userId);
        getMyrelation.start();
        try {
            getMyrelation.join();//릴레이션 분류 작업이 끝나야 버튼 클릭 수행 가능함
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        followsArray=getMyrelation.getMoveContextUsers();//무브 컨텍스트, 즉 팔로우를 했고 상대에게 수락을 얻은 유저 리스트를 가져옴
        leftSideButtons=new JButton[followsArray.size()];

        p1 = new JPanel(new GridLayout(followsArray.size()+10,1,0,5));

        for (int i = 0; i < leftSideButtons.length; i++) {
            System.out.println("follows array "+ followsArray.get(i).getUserName()+" "+ followsArray.get(i).getFollowershipIndex()+" "+ followsArray.get(i).getContext());
            leftSideButtons[i] = new JButton(followsArray.get(i).getUserName());
            leftSideButtons[i].setPreferredSize(new Dimension(200, 50));
            leftSideButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            leftSideButtons[i].setFocusable(true);
            leftSideButtons[i].setBackground(Color.decode(ColorSet.lightGray));
            leftSideButtons[i].setOpaque(true);
            leftSideButtons[i].addActionListener(this);
            p1.add(leftSideButtons[i]);
        }
        leftSideScroll = new JScrollPane(p1);
        leftSideScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        leftSideScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        c.add(leftSideScroll, BorderLayout.WEST);

        //factoryMainPanel
        c.add(factoryMainPanel, BorderLayout.CENTER);
        factoryMainPanel.setBackground(Color.decode("#B2FFFF"));

        //메인화면 공장이름 레이블 설정
        factoryTitle = new JLabel(userId+"상태");
        factoryTitle.setHorizontalAlignment(0);
        factoryTitle.setFont(new Font("KOTRA_BOLD", 0, 24));
        factoryTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        factoryMainPanel.add(factoryTitle, BorderLayout.NORTH);

        //공장 스탯 확인하는 패널 설정
        sensorValuePanel=new JPanel();
        sensorValueScroll = new JScrollPane(sensorValuePanel);
        factoryMainPanel.add(sensorValueScroll,BorderLayout.CENTER);
        guiUpdater = new GuiUpdater(factoryMainPanel);
        guiUpdater.execute();


        //각 공장에 대한 설정을 위한 버튼 패널
        factoryBtnPanel = new JPanel(new FlowLayout());
        factoryMainPanel.add(factoryBtnPanel, BorderLayout.SOUTH);
        factoryBtnPanel.setBackground(Color.decode(ColorSet.darkTeal));

        factoryBtns = new JButton[5];
        String[] btnsName = {"내 공장", "그래프로 보기", "열람 요청", "열람 요청 확인", "로그아웃"};
        for (int i = 0; i < factoryBtns.length; i++) {
            factoryBtns[i] = new JButton(btnsName[i]);
            factoryBtnPanel.add(factoryBtns[i]);
            factoryBtns[i].setBorder(BorderFactory.createLineBorder(Color.black));
            factoryBtns[i].setForeground(Color.black);
            factoryBtns[i].setPreferredSize(new Dimension(100, 30));
        }

        //내 공장으로 이동
        factoryBtns[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                holder.setUserID(loginId);
                factoryTitle.setText(userId + " 상태");

            }
        });
        factoryBtns[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        factoryBtns[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PermissionReqestFrame();
            }
        });

        factoryBtns[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PermissionReqestCheckFrame();
            }
        });
        factoryBtns[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainFrame.loginId="";
                holder.userId="";
                //guiUpdater.cancel(true);
                new LoginFrame();
            }
        });




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

        holder.setUserID(e.getActionCommand());
        factoryTitle.setText(e.getActionCommand() + " 상태"); // 공장이름
        setVisible(true);
    }
    private void getRelationShip(){
        new Thread(){
            @Override
            public void run() {
                super.run();

            }
        }.start();
    }

}
public class Main {
    public static void main(String[] args) {
        new LoginFrame();
    }
}


