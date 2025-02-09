package Arena;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Postavy.Postava;


public class Shop extends JFrame {


    public void shoping(Postava postava) {

        setTitle("Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(700, 200);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
setResizable(false);
setAlwaysOnTop(true);
        components(postava);

        setVisible(true);


    }

//Buttons upgrade
    public void components(Postava postava) {


        JPanel panel = new JPanel();
        panel.setBounds(0, 50, 700, 200);
        panel.setBackground(Color.BLACK);

        JLabel label = new JLabel("Coins: " + postava.getCoins());
        label.setFont(new Font("Dialog", Font.ITALIC, 30));
        label.setForeground(Color.RED);
        label.setBounds(290, 0, 200, 30);
        add(label);


        JButton upgradeInteligence = new JButton("Upgrade Inteligence");

        upgradeInteligence.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(postava.getCoins()>0) {
                    System.out.println("-----------------------");
                    System.out.println("postava pred:" + postava);
                    postava.upgradeInteligence(postava);
                    System.out.println("-----------------------");
                    System.out.println("postava po:" + postava);
                    System.out.println("UPGRADED");
                }else {
                    System.out.println("-----------------------");
                    System.out.println("nedostatek penez");
                }
            }
        });


        JButton upgradeSila = new JButton("Upgrade Sila");

        upgradeSila.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(postava.getCoins()>0) {
                    System.out.println("-----------------------");
                    System.out.println("postava pred:" + postava);
                    postava.upgradeSila(postava);
                    System.out.println("-----------------------");
                    System.out.println("postava po:" + postava);
                    System.out.println("UPGRADED");
                }else {
                    System.out.println("-----------------------");
                    System.out.println("nedostatek penez");
                }
            }
        });


        JButton upgradeOdolnost = new JButton("Upgrade Odolnost");

        upgradeOdolnost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(postava.getCoins()>0) {
                    System.out.println("-----------------------");
                    System.out.println("postava pred:" + postava);
                    postava.upgradeOdolnost(postava);
                    System.out.println("-----------------------");
                    System.out.println("postava po:" + postava);
                    System.out.println("UPGRADED");
                }else {
                    System.out.println("-----------------------");
                    System.out.println("nedostatek penez");
                }
            }
        });


        JButton upgradeObratnost = new JButton("Upgrade Obratnost");

        upgradeObratnost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(postava.getCoins()>0) {
                    System.out.println("-----------------------");
                    System.out.println("postava pred:" + postava);
                    postava.upgradeObratnost(postava);
                    System.out.println("-----------------------");
                    System.out.println("postava po:" + postava);
                    System.out.println("UPGRADED");
                }else {
                    System.out.println("----------------------");
                    System.out.println("nedostatek penez");
                }
            }
        });


        panel.add(upgradeInteligence);
        panel.add(upgradeSila);
        panel.add(upgradeOdolnost);
        panel.add(upgradeObratnost);

        add(panel);
    }


}
