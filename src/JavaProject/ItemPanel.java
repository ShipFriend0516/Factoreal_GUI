package JavaProject;

import JavaProject.network.DTO.AlarmDTO;
import JavaProject.network.VO.userItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemPanel extends JPanel{
    JPanel item;
    JLabel jLabel;
    JButton jButton;
    ItemPanel(userItem useritem){
        super();
        this.setLayout(new BorderLayout());
        jLabel=new JLabel(useritem.getUserName());
        jButton=new JButton(useritem.getContext());
        switch (useritem.getContext()){
        }
        this.add(jLabel,BorderLayout.CENTER);
        this.add(jButton,BorderLayout.EAST);
    }


    public static class Context{
        public static final String requested="취소";//팔로우 되어있는 상태(disabled인)
        public static final String move="이동";//팔로우 되어있는 상태(enabled인)
        public static final String follow="팔로우";//팔로우 할 상태
        public static final String delete="삭제";//나를 팔로우 하고 있는 상태
        public static final String request="요청";//요청이 들어온 경우: 수락 삭제 두개가 뜸
    }
}

class ValuePanel extends JPanel{

    long sensorIndex;
    JLabel sensorName;
    JLabel value;
    JButton alarmBtn;
    AlarmDTO alarmDTO=null;
    ValuePanel(long sensorIndex, String sensorName , String value, AlarmDTO alarmDTO){
        super();
        this.sensorIndex=sensorIndex;
        this.setLayout(new BorderLayout());
        this.sensorName =new JLabel(sensorName);
        this.value =new JLabel(value);
        this.alarmDTO=alarmDTO;
        alarmBtn=new JButton();
        alarmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AlarmFrame(sensorIndex,alarmDTO);
            }
        });
        this.add(this.sensorName,BorderLayout.WEST);
        this.add(this.value,BorderLayout.CENTER);
        this.add(alarmBtn,BorderLayout.EAST);
    }


    public static class Context{

    }
}