package JavaProject;

import javax.swing.*;
import java.awt.*;

public class PermissionRequestPanel extends JPanel {
    private String name;
    PermissionRequestPanel(String name) {

        //INFO//
        //이 패널은 요청을 하기 위한 패널입니다.

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

        JButton follow = new JButton("팔로우");
        follow.setPreferredSize(new Dimension(60,30));
        follow.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(follow,BorderLayout.EAST);

        follow.repaint();
        follow.revalidate();


        this.setPreferredSize(new Dimension(300,40));
        this.setBorder(BorderFactory.createEmptyBorder(3,3,3,18));

        setVisible(true);
        setSize(300,40);

    }
}
