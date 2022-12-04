package JavaProject;

import JavaProject.network.Callretrofit;
import JavaProject.network.DTO.AlarmDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

class HintTextField extends JTextField {
    Font gainFont = new Font("Tahoma", Font.PLAIN, 11);
    Font lostFont = new Font("Tahoma", Font.ITALIC, 11);
    public HintTextField(final String hint) {
        setText(hint);
        setFont(lostFont);
        setForeground(Color.GRAY);
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                    setFont(gainFont);
                } else {
                    setText(getText());
                    setFont(gainFont);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals(hint)|| getText().length()==0) {
                    setText(hint);
                    setFont(lostFont);
                    setForeground(Color.GRAY);
                } else {
                    setText(getText());
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                }
            }
        });
    }
}
public class AlarmFrame extends JFrame {
    JPanel panel;
    JPanel buttonBarPanel;
    HintTextField minText;
    HintTextField maxText;
    JButton confirm;
    JButton cancel;
    AlarmFrame(long sensorIndex,AlarmDTO alarmDTO){
        panel=new JPanel(new BorderLayout());
        buttonBarPanel=new JPanel(new GridLayout(2,1,10,10));
        minText=new HintTextField("최소값");
        maxText=new HintTextField("최대값");
        confirm=new JButton();
        cancel=new JButton();

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


    }

}
