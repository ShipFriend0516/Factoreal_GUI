package JavaProject;

import JavaProject.network.GetMyrelation;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class PermissionReqestFrame extends JFrame {

    Vector<PermissionRequestPanel> list = new Vector<>();


    PermissionReqestFrame() {
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("열람 요청");
        title.setFont(new Font("KOTRA_BOLD", 0, 16));
        title.setHorizontalAlignment(0);
        title.setOpaque(true);
        title.setBackground(Color.decode(ColorSet.lightGreen));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        this.add(title, BorderLayout.NORTH);

        //
//        GetMyrelation.holder.getMoveContextUsers();
//        GetMyrelation.holder.getCancelContextUsers();
        //
        JPanel reqestListPanel = new JPanel(new FlowLayout());

        JScrollPane reqestListScroll = new JScrollPane(reqestListPanel);
        reqestListScroll.setPreferredSize(new Dimension(300, 300));

        reqestListScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        reqestListScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);



        reqestListPanel.setSize(300, 1000);



        reqestListPanel.add(new PermissionRequestPanel("유저1"));
        reqestListPanel.add(new PermissionRequestPanel("dd"));


//        reqestListPanel.add(new JButton("1"));
//        reqestListPanel.add(new JButton("1"));
//        reqestListPanel.add(new JButton("1"));
//        reqestListPanel.add(new JButton("1"));
//        reqestListPanel.add(new JButton("1"));
//        reqestListPanel.add(new JButton("1"));
//        reqestListPanel.add(new JButton("1"));
//        reqestListPanel.add(new JButton("1"));
//        reqestListPanel.add(new JButton("1"));


        this.add(reqestListScroll,BorderLayout.CENTER);
        this.revalidate();
        this.repaint();



        //Frame Setting
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        setSize(300, 300);
        setVisible(true);
        setTitle("열람 요청");
    }

    public static void main(String[] args) {
        new PermissionReqestFrame();
    }
}
