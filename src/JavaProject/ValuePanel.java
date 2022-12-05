package JavaProject;

import JavaProject.network.DTO.AlarmDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ValuePanel extends JPanel{

    long sensorIndex;
    JLabel sensorName;
    JLabel value;
    JButton alarmBtn;
    AlarmDTO alarmDTO=null;

    ImageIcon ico = new ImageIcon("images/alarm.png");

    ValuePanel(long sensorIndex, String sensorName , String value, AlarmDTO alarmDTO){
        super();
        this.sensorIndex=sensorIndex;
        this.setLayout(new BorderLayout(30,5));
        this.sensorName =new JLabel(sensorName);
        this.value =new JLabel(value);
        this.alarmDTO=alarmDTO;
        alarmBtn=new JButton(new ImageIcon(ico.getImage().getScaledInstance(15,15,Image.SCALE_SMOOTH)));
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