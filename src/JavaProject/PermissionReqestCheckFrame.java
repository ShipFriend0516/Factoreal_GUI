package JavaProject;

import JavaProject.network.DTO.SensorValue;
import JavaProject.network.GetMyrelation;
import JavaProject.network.VO.userItem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

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
        getContentPane().add(title,BorderLayout.NORTH);


        List<userItem> requestUsers = GetMyrelation.holder.getRequestContextUsers();
        List<userItem> removeUsers = GetMyrelation.holder.getRemoveContextUsers();




        requestPanel = new JPanel(new GridLayout(requestUsers.size()+removeUsers.size()+5,1));
        requestPanel.add(requestTable);
        scrollPane= new JScrollPane(requestPanel);
        JScrollPane scrollPane = new JScrollPane(requestPanel);
        getContentPane().add(scrollPane,BorderLayout.CENTER);




        //Frame Setting
        setLocationRelativeTo(this);
        setSize(300,300);
        setVisible(true);
        setTitle("열람 요청");
    }
}
