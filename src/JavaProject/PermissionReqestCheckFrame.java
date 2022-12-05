package JavaProject;

import JavaProject.network.GetMyrelation;
import JavaProject.network.VO.userItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class PermissionReqestCheckFrame extends JFrame {
    JTable requestTable;
    JPanel requestPanel;
    JScrollPane scrollPane;
    JLabel title;
    JPanel requestListPanel;
    JScrollPane requestListScroll;

    Vector<UserItemPanel> list = new Vector<>();

    PermissionReqestCheckFrame() {
        this.setLayout(new BorderLayout());

        title = new JLabel("열람 요청 확인");
        title.setFont(new Font("KOTRA_BOLD", 0, 16));
        title.setHorizontalAlignment(0);
        title.setOpaque(true);
        title.setBackground(Color.decode(ColorSet.lightGreen));
        title.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        this.add(title,BorderLayout.NORTH);

        GetMyrelation getMyrelation = new GetMyrelation(MainFrame.loginId);
        getMyrelation.start();


        requestListPanel = new JPanel(new FlowLayout()); // 리스트가 나오는 패널
        requestListPanel.setPreferredSize(new Dimension(300,300));
        requestListScroll = new JScrollPane(requestListPanel); //
        requestListScroll.setPreferredSize(new Dimension(300, 200));


        requestListScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        requestListScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        requestListPanel.setSize(300, 1000);
        List<userItem> requestList = getMyrelation.getRequestContextUsers();
        List<userItem> removeList = getMyrelation.getRemoveContextUsers();
        for (int i=0;i<requestList.size();i++) {
            System.out.println(requestList.get(i).getUserName()+": "+requestList.get(i).getFollowershipIndex()+" "+requestList.get(i).getContext() );
            requestListPanel.add(new UserItemPanel(requestList.get(i)));
        }
        for (int i=0;i<removeList.size();i++) {
            System.out.println(removeList.get(i).getUserName()+": "+removeList.get(i).getFollowershipIndex()+" "+removeList.get(i).getContext() );
            requestListPanel.add(new UserItemPanel(removeList.get(i)));
        }
        this.add(requestListScroll,BorderLayout.CENTER);
        this.revalidate();
        this.repaint();



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
