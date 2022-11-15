package JavaProject;

import javax.swing.*;
import java.awt.*;

public class PermissionReqestFrame extends JFrame {

    PermissionReqestFrame() {
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("열람 요청");
        title.setFont(new Font("KOTRA_BOLD", 0, 16));
        title.setHorizontalAlignment(0);
        title.setOpaque(true);
        title.setBackground(Color.decode(ColorSet.lightGreen));
        title.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        this.add(title,BorderLayout.NORTH);


        //Frame Setting
        setLocationRelativeTo(this);
        setSize(300,300);
        setVisible(true);
        setTitle("열람 요청");
    }
}
