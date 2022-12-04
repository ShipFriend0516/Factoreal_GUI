package JavaProject;

import JavaProject.network.Callretrofit;
import JavaProject.network.DTO.AlarmDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class AlarmFrame extends JFrame {
    JPanel panel;
    JPanel buttonBarPanel;
    JTextField minText;
    JTextField maxText;
    JButton confirm;
    JButton cancel;

    public static void main(String[] args) {
        new AlarmFrame(2,null);
    }
    AlarmFrame(long sensorIndex,AlarmDTO alarmDTO){
        panel=new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonBarPanel=new JPanel(new GridLayout(1,2,10,10));

        minText=new JTextField("최소값");
        maxText=new JTextField("최대값");
        minText.setPreferredSize(new Dimension(100,30));
        maxText.setPreferredSize(new Dimension(100,30));

        confirm=new JButton();
        cancel=new JButton();
        confirm.setPreferredSize(new Dimension(100,30));
        cancel.setPreferredSize(new Dimension(100,30));
        inputPanel.add(minText);
        inputPanel.add(maxText);
        buttonBarPanel.add(confirm);
        buttonBarPanel.add(cancel);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel,BorderLayout.CENTER);
        getContentPane().add(buttonBarPanel,BorderLayout.SOUTH);

        if (alarmDTO!=null){
            minText.setText(alarmDTO.getMinimum()+"");
            maxText.setText(alarmDTO.getMaximum()+"");
        }

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alarmDTO==null){
                    if (minText.getText()==null||maxText.getText()==null){
                        dispose();
                    }else {
                        Callretrofit.post_alarm(MainFrame.loginId,new AlarmDTO(null,sensorIndex,Double.parseDouble(minText.getText()), Double.parseDouble(maxText.getText()),null,null));
                    }
                }
                else if (minText.getText()==null||maxText.getText()==null){
                    Callretrofit.delete_alarm(alarmDTO.getIndex());
                }else{
                    Callretrofit.patch_alarm(new AlarmDTO(alarmDTO.getIndex(),alarmDTO.getSensorIndex(),Double.parseDouble(minText.getText()), Double.parseDouble(maxText.getText()),null,null));
                }
            }
        });

        setLocationRelativeTo(this);
        setSize(300,300);
        setVisible(true);
        setTitle("알람 설정");
    }

}
