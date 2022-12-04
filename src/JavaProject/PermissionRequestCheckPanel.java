package JavaProject;

import javax.swing.*;
import java.awt.*;

public class PermissionRequestCheckPanel extends JPanel {
    private String name;
    PermissionRequestCheckPanel(String name) {

        //INFO//
        //이 패널은 요청이 들어오면 생기는 패널입니다.

        this.name = name;
        this.setLayout(new BorderLayout(5,0));

        JLabel requestName = new JLabel(name);
        requestName.setHorizontalAlignment(0);
        requestName.setOpaque(true);
        requestName.setForeground(Color.BLACK);
        requestName.setSize(200,40);
        requestName.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(requestName,BorderLayout.CENTER);
        requestName.repaint();

        JPanel btnPanel = new JPanel(new GridLayout(1,2,5,0));
        JButton accept = new JButton("수락");
        JButton deny = new JButton("거절");
        accept.setPreferredSize(new Dimension(40,30));
        deny.setPreferredSize(new Dimension(60,30));
        accept.setBorder(BorderFactory.createLineBorder(Color.black));
        deny.setBorder(BorderFactory.createLineBorder(Color.black));
        btnPanel.add(accept);
        btnPanel.add(deny);
        this.add(btnPanel,BorderLayout.EAST);

        accept.repaint();
        accept.revalidate();
        deny.repaint();
        deny.revalidate();


        this.setPreferredSize(new Dimension(300,40));
        this.setBorder(BorderFactory.createEmptyBorder(3,3,3,18));

        setVisible(true);
        setSize(300,40);

    }
}
