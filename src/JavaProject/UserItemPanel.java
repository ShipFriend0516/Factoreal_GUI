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
    JLabel requestName;
    JPanel btnPanel;
    JButton accept;
    JButton deny;
    JButton delete;
    JButton move;
    JButton requested;
    JButton follow;

    UserItemPanel(userItem userItem) {
        UserItemPanel holder=this;
        //INFO//
        //이 패널은 요청이 들어오면 생기는 패널입니다.

        this.userItem = userItem;

        accept = new JButton("수락");
        deny = new JButton("거절");
        delete = new JButton("삭제");
        move = new JButton("이동");
        requested = new JButton("취소");
        follow = new JButton("팔로우");

        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component c= holder.getRootPane();
                btnPanel.removeAll();
                btnPanel.setLayout(new GridLayout(1,1,5,0));
                btnPanel.add(delete);
                Callretrofit.patch_follower(userItem.getFollowershipIndex(),true);
                c.revalidate();
                c.repaint();
            }
        });
        deny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component c= holder.getRootPane();
                holder.getParent().remove(holder);

                Callretrofit.delete_follower(userItem.getFollowershipIndex());
                c.revalidate();
                c.repaint();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component c= holder.getRootPane();
                holder.getParent().remove(holder);
                Callretrofit.delete_follower(userItem.getFollowershipIndex());
                c.revalidate();
                c.repaint();
            }
        });
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.userId= MainFrame.loginId;
                userItem.getUserName();//이 값을 전달
            }
        });
        requested.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component c= holder.getRootPane();
                Callretrofit.delete_follower(userItem.getFollowershipIndex());
                btnPanel.removeAll();
                btnPanel.add(follow);
                c.revalidate();
                c.repaint();
            }
        });
        follow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component c= holder.getRootPane();
                Callretrofit.post_follower(new FollowshipVO(MainFrame.loginId, userItem.getUserName()));
                btnPanel.removeAll();
                btnPanel.add(requested);
                c.revalidate();
                c.repaint();
            }
        });


        this.setLayout(new BorderLayout(5,0));

        requestName = new JLabel(userItem.getUserName());
        requestName.setHorizontalAlignment(0);
        requestName.setOpaque(true);
        requestName.setForeground(Color.BLACK);
        requestName.setSize(200,40);
        requestName.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(requestName,BorderLayout.CENTER);
        requestName.repaint();
        btnPanel = null;
        if (userItem.getContext().equals(Context.request)) {
            btnPanel= new JPanel(new GridLayout(1, 2, 5, 0));

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

            delete.setPreferredSize(new Dimension(60, 30));
            delete.setBorder(BorderFactory.createLineBorder(Color.black));
            btnPanel.add(delete);
            delete.revalidate();
            delete.repaint();
        }
        else if (userItem.getContext().equals(Context.move)){
            btnPanel = new JPanel(new GridLayout(1,1,5,0));
            move.setPreferredSize(new Dimension(60, 30));
            move.setBorder(BorderFactory.createLineBorder(Color.black));
            btnPanel.add(move);

        }
        else if (userItem.getContext().equals(Context.requested)){
            btnPanel = new JPanel(new GridLayout(1,1,5,0));
            requested.setPreferredSize(new Dimension(60, 30));
            requested.setBorder(BorderFactory.createLineBorder(Color.black));
            btnPanel.add(requested);
            requested.revalidate();
            requested.repaint();
        }
        else {
            btnPanel = new JPanel(new GridLayout(1,1,5,0));
            follow.setPreferredSize(new Dimension(60, 30));
            follow.setBorder(BorderFactory.createLineBorder(Color.black));
            btnPanel.add(follow);
            follow.revalidate();
            follow.repaint();
        }
        this.add(btnPanel,BorderLayout.EAST);



        this.setPreferredSize(new Dimension(300,40));
        this.setBorder(BorderFactory.createEmptyBorder(3,3,3,18));

        setVisible(true);
        setSize(300,40);

    }
}
