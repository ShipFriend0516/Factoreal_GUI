package JavaProject;

import JavaProject.network.Callretrofit;
import JavaProject.network.GetMyrelation;
import JavaProject.network.VO.userItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class PermissionReqestFrame extends JFrame {

    Vector<UserItemPanel> list = new Vector<>();
    JLabel title;
    JPanel requestMain;
    JPanel searchFieldPanel;
    JTextField searchField;
    JButton searchBt;
    JPanel searchResultPanel;
    JPanel searchResult;
    JScrollPane searchResultScroll;
    PermissionReqestFrame() {
        this.setLayout(new BorderLayout());

        title = new JLabel("열람 요청");
        title.setFont(new Font("KOTRA_BOLD", 0, 16));
        title.setHorizontalAlignment(0);
        title.setOpaque(true);
        title.setBackground(Color.decode(ColorSet.lightGreen));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        this.add(title, BorderLayout.NORTH);

        GetMyrelation getMyrelation=new GetMyrelation(MainFrame.loginId);
        getMyrelation.start();

        requestMain = new JPanel(new BorderLayout());

        searchFieldPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchBt = new JButton("검색");
        searchBt.setBorder(BorderFactory.createLineBorder(Color.black));
        searchBt.setPreferredSize(new Dimension(60,10));
        searchBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetListOfUserTheread theread=new GetListOfUserTheread(getMyrelation.getMoveContextUsers(),getMyrelation.getCancelContextUsers());
                theread.start();
            }
        });

        searchFieldPanel.add(searchField);
        searchFieldPanel.add(searchBt,BorderLayout.EAST);
        searchFieldPanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));

        searchResultPanel = new JPanel(new BorderLayout());
        searchResult = new JPanel(new FlowLayout()); // 검색 결과들이 나오는 스크롤판의 패널
        searchResult.setPreferredSize(new Dimension(300,300)); //검색결과의 최대길이

        searchResultScroll = new JScrollPane(searchResult);
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
        /*searchResult.add(new PermissionRequestPanel("누군가"));
        searchResult.add(new PermissionRequestPanel("누군가"));
        searchResult.add(new PermissionRequestPanel("으악"));
        searchResult.add(new PermissionRequestPanel("누군가"));
        searchResult.add(new PermissionRequestPanel("누군가"));
        searchResult.add(new PermissionRequestPanel("누군가"));*/


        // <<>>


        requestMain.add(searchFieldPanel,BorderLayout.NORTH);
        requestMain.add(searchResultPanel, BorderLayout.CENTER);

        this.add(requestMain,BorderLayout.CENTER);

        //Frame Setting
        setLocationRelativeTo(this);
        setSize(300, 300);
        setVisible(true);
        setTitle("열람 요청");
    }
    class GetListOfUserTheread extends Thread{
        List<userItem> foundlist_found_true;
        List<userItem> foundlist_found_false;
        List<userItem> foundlist_found_just;
        List<String> listOfuser;

        List<String> followTrueList;
        List<String> followFalseList;
        List<userItem>trueFollow;
        List<userItem>falseFollow;
        GetListOfUserTheread(ArrayList<userItem> followTrueList,ArrayList<userItem> followFalseList){
            this.followTrueList=new ArrayList<>();
            this.followFalseList=new ArrayList<>();
            foundlist_found_true=new ArrayList<>();
            foundlist_found_false=new ArrayList<>();
            foundlist_found_just=new ArrayList<>();
            trueFollow=followTrueList;
            falseFollow=followFalseList;

            for (userItem s:followTrueList) {
                this.followTrueList.add(s.getUserName());
            }
            for (userItem s:followFalseList) {
                this.followFalseList.add(s.getUserName());
            }
        }
        @Override
        public void run() {
            super.run();
            listOfuser= Callretrofit.get_list_of_user_by(searchField.getText());

            for (int i=0;i<listOfuser.size();i++) {
                String id=listOfuser.get(i);
                if (MainFrame.loginId.equals(id)){
                    listOfuser.remove(i);
                    break;
                }
            }
            int offset1=0;
            int offset2=0;
            for (int i=0; i<listOfuser.size();i++){
                String id=listOfuser.get(i);
                if(followTrueList.contains(id)){
                    foundlist_found_true.add(trueFollow.get(offset1));
                    offset1++;
                }
                else if(followFalseList.contains(id)){
                    foundlist_found_false.add(falseFollow.get(offset2));
                    offset2++;
                }else{
                    foundlist_found_just.add(new userItem(-1,id, Context.follow));
                }
            }

            list=new Vector<>();
            for (userItem u:foundlist_found_true) {
                list.add(new UserItemPanel(u));
            }
            for (userItem u:foundlist_found_false) {
                list.add(new UserItemPanel(u));
            }
            for (userItem u:foundlist_found_just) {
                list.add(new UserItemPanel(u));
            }
            for (int i = 0; i <list.size(); i++) {
                searchResult.add(list.get(i));
            }
                searchResult.revalidate();
            searchResult.repaint();
        }
    }
    public static void main(String[] args) {
        new PermissionReqestFrame();
    }
}
