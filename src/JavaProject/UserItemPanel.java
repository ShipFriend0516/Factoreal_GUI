package JavaProject;

import JavaProject.network.Callretrofit;
import JavaProject.network.VO.FollowshipVO;
import JavaProject.network.VO.userItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserItemPanel extends JPanel {
    private userItem userItem;
    UserItemPanel(userItem userItem) {

        //INFO//
        //이 패널은 요청이 들어오면 생기는 패널입니다.

        this.userItem = userItem;
        this.setLayout(new BorderLayout(5,0));

        JLabel requestName = new JLabel(userItem.getUserName());
        requestName.setHorizontalAlignment(0);
        requestName.setOpaque(true);
        requestName.setForeground(Color.BLACK);
        requestName.setSize(200,40);
        requestName.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(requestName,BorderLayout.CENTER);
        requestName.repaint();
        JPanel btnPanel = null;
        if (userItem.getContext().equals(Context.request)) {
            btnPanel= new JPanel(new GridLayout(1, 2, 5, 0));
            JButton accept = new JButton("수락");
            JButton deny = new JButton("거절");
            accept.setPreferredSize(new Dimension(40, 30));
            deny.setPreferredSize(new Dimension(60, 30));
            accept.setBorder(BorderFactory.createLineBorder(Color.black));
            deny.setBorder(BorderFactory.createLineBorder(Color.black));
            btnPanel.add(accept);
            btnPanel.add(deny);
            accept.revalidate();
            accept.repaint();
            deny.revalidate();
            deny.repaint();
        }
        else if (userItem.getContext().equals(Context.delete)){
            btnPanel = new JPanel(new GridLayout(1,1,5,0));
            JButton delete = new JButton(userItem.getContext());
            delete.setPreferredSize(new Dimension(60, 30));
            delete.setBorder(BorderFactory.createLineBorder(Color.black));
            btnPanel.add(delete);
            delete.revalidate();
            delete.repaint();
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Callretrofit.delete_follower(userItem.getFollowershipIndex());
                }
            });
        }
        else if (userItem.getContext().equals(Context.move)){
            btnPanel = new JPanel(new GridLayout(1,1,5,0));
            JButton move = new JButton(userItem.getContext());
            move.setPreferredSize(new Dimension(60, 30));
            move.setBorder(BorderFactory.createLineBorder(Color.black));
            btnPanel.add(move);
            move.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //todo 이 창을 닫고 메인 프레임의 userid를 내 팔로워 인덱스의 이름으로 변경
                    userItem.getUserName();//이 값을 전달
                }
            });

        }
        else if (userItem.getContext().equals(Context.requested)){
            btnPanel = new JPanel(new GridLayout(1,1,5,0));
            JButton requested = new JButton(userItem.getContext());
            requested.setPreferredSize(new Dimension(60, 30));
            requested.setBorder(BorderFactory.createLineBorder(Color.black));
            btnPanel.add(requested);
            requested.revalidate();
            requested.repaint();
            requested.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Callretrofit.delete_follower(userItem.getFollowershipIndex());
                }
            });
        }
        else {
            btnPanel = new JPanel(new GridLayout(1,1,5,0));
            JButton follow = new JButton(userItem.getContext());
            follow.setPreferredSize(new Dimension(60, 30));
            follow.setBorder(BorderFactory.createLineBorder(Color.black));
            btnPanel.add(follow);
            follow.revalidate();
            follow.repaint();
            follow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Callretrofit.post_follower(new FollowshipVO(MainFrame.loginId, userItem.getUserName()));
                }
            });
        }
        this.add(btnPanel,BorderLayout.EAST);



        this.setPreferredSize(new Dimension(300,40));
        this.setBorder(BorderFactory.createEmptyBorder(3,3,3,18));

        setVisible(true);
        setSize(300,40);

    }
}
