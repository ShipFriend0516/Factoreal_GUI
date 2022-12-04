package JavaProject;

import javax.swing.*;
import java.awt.*;

public class PermissionReqestCheckFrame extends JFrame {
    JTable requestTable;
    JPanel requestPanel;
    JScrollPane scrollPane;




    PermissionReqestCheckFrame() {
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("열람 요청 확인");
        title.setFont(new Font("KOTRA_BOLD", 0, 16));
        title.setHorizontalAlignment(0);
        title.setOpaque(true);
        title.setBackground(Color.decode(ColorSet.lightGreen));
        title.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        this.add(title,BorderLayout.NORTH);

//
//        List<userItem> requestUsers = GetMyrelation.holder.getRequestContextUsers();
//        List<userItem> removeUsers = GetMyrelation.holder.getRemoveContextUsers();



        JPanel reqestListPanel = new JPanel(new FlowLayout());

        JScrollPane reqestListScroll = new JScrollPane(reqestListPanel); //
        reqestListScroll.setPreferredSize(new Dimension(300, 300));

        reqestListScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        reqestListScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        reqestListPanel.setSize(300, 1000);


        reqestListPanel.add(new PermissionRequestCheckPanel("유저1"));
        reqestListPanel.add(new PermissionRequestCheckPanel("dd"));

        this.add(reqestListScroll,BorderLayout.CENTER);
        this.revalidate();
        this.repaint();




        //Frame Setting
        setLocationRelativeTo(this);
        setSize(300,300);
        setVisible(true);
        setTitle("열람 요청");
    }

    public static void main(String[] args) {
        new PermissionReqestCheckFrame();
    }
}
