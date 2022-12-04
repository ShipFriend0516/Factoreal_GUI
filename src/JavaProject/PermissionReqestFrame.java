package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class PermissionReqestFrame extends JFrame {

    Vector<PermissionRequestCheckPanel> list = new Vector<>();


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

        JPanel requestMain = new JPanel(new BorderLayout());

        JPanel searchFieldPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        JButton searchBt = new JButton("검색");
        searchBt.setBorder(BorderFactory.createLineBorder(Color.black));
        searchBt.setPreferredSize(new Dimension(60,10));
        searchBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        searchFieldPanel.add(searchField);
        searchFieldPanel.add(searchBt,BorderLayout.EAST);
        searchFieldPanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));

        JPanel searchResultPanel = new JPanel(new BorderLayout());
        JPanel searchResult = new JPanel(new FlowLayout()); // 검색 결과들이 나오는 스크롤판의 패널
        searchResult.setPreferredSize(new Dimension(300,300)); //검색결과의 최대길이

        JScrollPane searchResultScroll = new JScrollPane(searchResult);
        searchResultScroll.setPreferredSize(new Dimension(300,200));

        searchResultScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        searchResultScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        // 검색결과 라벨 설정 >>
        JLabel resultLabel = new JLabel("검색결과");
        resultLabel.setBorder(BorderFactory.createEmptyBorder(3,10,0,0));
        resultLabel.setPreferredSize(new Dimension(300,30));

        searchResultPanel.add(resultLabel,BorderLayout.NORTH); //검색결과
        searchResultPanel.add(searchResultScroll,BorderLayout.CENTER); //스크롤판

        //searchResult는 패널입니다. 검색결과와 일치하는 이름의 공장의 팔로우 패널을 띄우기 위함
        searchResult.add(new PermissionRequestPanel("누군가"));
        searchResult.add(new PermissionRequestPanel("누군가"));
        searchResult.add(new PermissionRequestPanel("으악"));
        searchResult.add(new PermissionRequestPanel("누군가"));
        searchResult.add(new PermissionRequestPanel("누군가"));
        searchResult.add(new PermissionRequestPanel("누군가"));


        // <<>>


        requestMain.add(searchFieldPanel,BorderLayout.NORTH);
        requestMain.add(searchResultPanel, BorderLayout.CENTER);

        this.add(requestMain,BorderLayout.CENTER);

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
