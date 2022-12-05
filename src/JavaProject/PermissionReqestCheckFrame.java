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



        JPanel requestListPanel = new JPanel(new FlowLayout()); // 리스트가 나오는 패널
        requestListPanel.setPreferredSize(new Dimension(300,300));
        JScrollPane requestListScroll = new JScrollPane(requestListPanel); //
        requestListScroll.setPreferredSize(new Dimension(300, 200));

        requestListScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        requestListScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);





        requestListPanel.add(new PermissionRequestCheckPanel("유저1"));
        requestListPanel.add(new PermissionRequestCheckPanel("dd"));

        this.add(requestListScroll,BorderLayout.CENTER);




        //Frame Setting
        setLocationRelativeTo(this);
        setSize(300,300);
        setVisible(true);
        setTitle("열람 요청 확인");
    }

    public static void main(String[] args) {
        new PermissionReqestCheckFrame();
    }
}
