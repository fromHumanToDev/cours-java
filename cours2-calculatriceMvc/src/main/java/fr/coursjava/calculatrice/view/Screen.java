package fr.coursjava.calculatrice.view;

import fr.coursjava.calculatrice.Controller;
import fr.coursjava.calculatrice.model.Calculatrice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Screen extends JFrame {

    private Controller controller;

    private JTextField premierNombre = new JTextField("12");;
    private JComboBox<String> typesOperation = new JComboBox<>(new String[]{"+", "-", "*", "/"});
    private JTextField deuxiemeNombre = new JTextField("66");
    private JLabel resultat = new JLabel();

    public Screen(){
        super.setSize(600, 150);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addButtons();
        super.setVisible(true);
    }

    private void addButtons(){
        JPanel panel = new JPanel();
        GridLayout gridlayout = new GridLayout(1,5);
        panel.setLayout(gridlayout);
        add(panel);

        KeyAdapter enterListener = new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == keyEvent.VK_ENTER){
                    calculer();
                }
            }
        };


        this.premierNombre.addKeyListener(enterListener);
        this.deuxiemeNombre.addKeyListener(enterListener);

        panel.add(this.premierNombre);
        panel.add(this.typesOperation);
        panel.add(this.deuxiemeNombre);

        JLabel egal = new JLabel("=");
        panel.add(egal);

        panel.add(resultat);



        egal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                calculer();
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void calculer(){
        this.controller.calculer(Integer.valueOf(premierNombre.getText()), typesOperation.getSelectedItem().toString(), Integer.valueOf(deuxiemeNombre.getText()));
    }

    public void setResultat(String value){
        this.resultat.setText(value);
    }
}
