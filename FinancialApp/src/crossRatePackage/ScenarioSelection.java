package crossRatePackage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 @author Radosław Mieckowski
 */

public class ScenarioSelection extends JFrame {
    private Font font;
    private JLabel title;
    private JPanel titlePanel;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel southPanel;
    private JPanel panel4;
    private JPanel currenciesPanel;
    private JPanel centerPanel;
    private JPanel transitionPanel;
    private JPanel addOrRemoveCurrencyPanel;
    private JPanel combo3Panel;
    private JComboBox<String> combo1;
    private JComboBox<String> combo2;
    private JComboBox<String> combo3;
    private final int FRAME_WIDTH=500;
    private final int FRAME_HEIGHT=450;
    private static String baseCurrency;
    private static String quoteCurrency;
    private static String transitionCurrency;
    private Scenario1 s1;
    private Scenario2 s2;
    private Scenario3 s3;
    private ButtonGroup group;
    private JRadioButton rButton1;
    private JRadioButton rButton2;
    private JRadioButton rButton3;
    private JButton addCurrencyButton;
    private JButton removeCurrencyButton;
    private JButton nextButton;

    public static String getBaseCurrency() {
        return baseCurrency;
    }

    public static String getQuoteCurrency() {
        return quoteCurrency;
    }

    public static String getTransitionCurrency() {
        return transitionCurrency;
    }

    public static void setBaseCurrency(String baseCurrency) {
        ScenarioSelection.baseCurrency = baseCurrency;
    }

    public static void setQuoteCurrency(String quoteCurrency) {
        ScenarioSelection.quoteCurrency = quoteCurrency;
    }

    public static void setTransitionCurrency(String transitionCurrency) {
        ScenarioSelection.transitionCurrency = transitionCurrency;
    }

    /*public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ScenarioSelection ss=new ScenarioSelection();
            ss.setVisible(true);
        });
    }*/


    public ScenarioSelection() {
        setLayout(new BorderLayout());
        setTitle("Cross-rate");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        setBounds(screenWidth/2-FRAME_WIDTH/2, screenHeight/2-FRAME_HEIGHT/2, FRAME_WIDTH, FRAME_HEIGHT);

        title = new JLabel("Crosss-rate", SwingConstants.CENTER);
        font = new Font("SansSerif", Font.BOLD + Font.ITALIC, 16);
        title.setFont(font);
        titlePanel = new JPanel(new GridLayout(3, 1));
        titlePanel.add(new JLabel(""));
        titlePanel.add(title);
        titlePanel.add(new JLabel(""));
        add(titlePanel, BorderLayout.NORTH);

        currenciesPanel=new JPanel(new GridLayout(4,1,0,5));
        panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,40,0));
        panel2.add(new JLabel("Base currency",SwingConstants.CENTER));
        panel2.add(new JLabel("Quote currency",SwingConstants.CENTER));
        currenciesPanel.add(panel2);

        panel4 =new JPanel(new FlowLayout(FlowLayout.CENTER,40,0));
        panel4.add((combo1 = new JComboBox<>()));
        combo1.setToolTipText("Base currency combo box");
        readCurrencies(combo1);
        combo1.addActionListener(event -> {
            baseCurrency = combo1.getItemAt(combo1.getSelectedIndex());
            System.out.println(baseCurrency);
            //JOptionPane.showMessageDialog(null, "Currency must be chosen!", "Error Message 1", JOptionPane.ERROR_MESSAGE);
        });
        panel4.add(combo2= new JComboBox<>());
        readCurrencies(combo2);
        combo2.setToolTipText("Quote currency combo box");
        combo2.addActionListener(event->{
            quoteCurrency = combo2.getItemAt(combo2.getSelectedIndex());
            System.out.println(quoteCurrency);
            //JOptionPane.showMessageDialog(null,"Currency must be chosen!","Error Message 1",JOptionPane.ERROR_MESSAGE);
        });
        currenciesPanel.add(panel4);
        transitionPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        transitionPanel.add(new JLabel("Transition through:",SwingConstants.CENTER));
        currenciesPanel.add(transitionPanel);
        combo3Panel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        combo3Panel.add(combo3= new JComboBox<>());
        readCurrencies(combo3);
        combo3.setToolTipText("Transition currency combo box");
        combo3.addActionListener(event->{
            transitionCurrency = combo3.getItemAt(combo3.getSelectedIndex());
            System.out.println(transitionCurrency);
        });
        currenciesPanel.add(combo3Panel);

        panel3=new JPanel(new GridLayout(3,1,5,5));

        group=new ButtonGroup();
        group.add(rButton1=new JRadioButton("Transition currency as the base currency in both transactions",true));
        rButton1.setActionCommand("First");
        rButton1.addActionListener(event->{
            System.out.println(rButton1.isSelected());
            System.out.println(rButton2.isSelected());
            System.out.println(rButton3.isSelected());
            System.out.println("===============");
        });
        group.add(rButton2=new JRadioButton("Transition currency as the base currency in one transaction",false));
        rButton2.setActionCommand("Second");
        rButton2.addActionListener(event->{
            System.out.println(rButton1.isSelected());
            System.out.println(rButton2.isSelected());
            System.out.println(rButton3.isSelected());
            System.out.println("===============");
        });
        group.add(rButton3=new JRadioButton("Transition currency as the quote currency in both transactions",false));
        rButton3.setActionCommand("Third");
        rButton3.addActionListener(event->{
            System.out.println(rButton1.isSelected());
            System.out.println(rButton2.isSelected());
            System.out.println(rButton3.isSelected());
            System.out.println("===============");
        });
        panel3.add(rButton1);
        panel3.add(rButton2);
        panel3.add(rButton3);
        Border etched=BorderFactory.createEtchedBorder();
        Border titled=BorderFactory.createTitledBorder(etched,"Scenario");
        panel3.setBorder(titled);

        centerPanel =new JPanel(new GridLayout(2,1));
        centerPanel.add(currenciesPanel);
        centerPanel.add(panel3);
        add(centerPanel,BorderLayout.CENTER);

        southPanel =new JPanel(new GridLayout(3,3));
        southPanel.add(new JLabel());
        southPanel.add(new JLabel());
        southPanel.add(new JLabel());
        southPanel.add(new JLabel());
        southPanel.add(nextButton=new JButton("Next"));
        southPanel.add(new JLabel());
        southPanel.add(new JLabel());
        southPanel.add(new JLabel());
        southPanel.add(new JLabel());
        add((southPanel),BorderLayout.SOUTH);
        //pack();

        nextButton.addActionListener(event-> {

            if((baseCurrency==null || baseCurrency.equals("")) || (quoteCurrency==null)||quoteCurrency.equals("")|| (transitionCurrency==null)||transitionCurrency.equals("")) {
                JOptionPane.showMessageDialog(null,"All currencies must be chosen!","Error Message 1",JOptionPane.ERROR_MESSAGE);
            }
            else if(baseCurrency.equals(quoteCurrency)||baseCurrency.equals(transitionCurrency)||quoteCurrency.equals(transitionCurrency))
            {
                JOptionPane.showMessageDialog(null,"You must choose three different currencies!","Error Message 2",JOptionPane.ERROR_MESSAGE);
            }
            else{
                this.setVisible(false);
                String scenario = group.getSelection().getActionCommand();
                if(scenario.equals("First")){
                    Scenario1 s1= new Scenario1();
                    s1.setVisible(true);
                }
                else if(scenario.equals("Second")){
                    Scenario1 s2= new Scenario2();
                    s2.setVisible(true);
                }
                else{
                    Scenario1 s3= new Scenario3();
                    s3.setVisible(true);
                }
            }

            System.out.println("_____________");
            System.out.println(baseCurrency);
            System.out.println(quoteCurrency);
            System.out.println(transitionCurrency);
            System.out.println("-------------");

        });

        addOrRemoveCurrencyPanel=new JPanel(new GridLayout(8,1,0,5));
        addOrRemoveCurrencyPanel.add(new JLabel(""));
        addOrRemoveCurrencyPanel.add(addCurrencyButton=new JButton("Add Currency."));
        addOrRemoveCurrencyPanel.add(new JLabel(""));
        addCurrencyButton.addActionListener(event->{
            addCurrencyToCombo(combo1,combo2,combo3);
        });
        addOrRemoveCurrencyPanel.add(removeCurrencyButton=new JButton("Remove Currency."));
        removeCurrencyButton.setToolTipText("Set Currency to remove in the base currency combo box");
        removeCurrencyButton.addActionListener(event->{
            removeCurrencyFromCombo(combo1,combo2,combo3);
        });
        addOrRemoveCurrencyPanel.add(new JLabel(""));
        addOrRemoveCurrencyPanel.add(new JLabel(""));
        addOrRemoveCurrencyPanel.add(new JLabel(""));
        addOrRemoveCurrencyPanel.add(new JLabel(""));
        add(addOrRemoveCurrencyPanel,BorderLayout.EAST);



    }
    private static void readCurrencies(JComboBox<String> combo){
        String currencyOut;
        try (Scanner scanner=new Scanner(new File("src/crossRatePackage/Currencies.txt")))
        {
            while(scanner.hasNext()){
                currencyOut= scanner.nextLine();
                combo.addItem(currencyOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void addCurrencyToCombo(JComboBox<String>combo1, JComboBox<String>combo2, JComboBox<String>combo3){
        String newCurrency = "";
        newCurrency = JOptionPane.showInputDialog(null,"Type your currency to add in the Input field","Currency adding",JOptionPane.PLAIN_MESSAGE);
        System.out.println(newCurrency);
        if(newCurrency==null){
            JOptionPane.showMessageDialog(null,"Input field can't be empty!","Error message nr.3",JOptionPane.ERROR_MESSAGE);
        }
        else if(newCurrency.equals("")){
            JOptionPane.showMessageDialog(null,"Input field can't be empty!","Error message nr.3",JOptionPane.ERROR_MESSAGE);
        }
        else{
            combo1.addItem(newCurrency);
            combo2.addItem(newCurrency);
            combo3.addItem(newCurrency);
            try(PrintWriter out=new PrintWriter("src\\Currencies.txt", String.valueOf(StandardCharsets.UTF_8))){
                String text="";
                for(int i=0;i<combo2.getItemCount();i++){
                    text=text+combo2.getItemAt(i)+"\n";
                }
                out.print(text);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    private static void removeCurrencyFromCombo(JComboBox<String>combo1, JComboBox<String>combo2,JComboBox<String>combo3){
        int index = combo1.getSelectedIndex();
        System.out.println(index);
        if(index!=0){
            combo1.removeItemAt(index);
            combo2.removeItemAt(index);
            combo3.removeItemAt(index);
            try(PrintWriter out=new PrintWriter("src\\Currencies.txt", String.valueOf(StandardCharsets.UTF_8))){
                String text="";
                for(int i=0;i<combo2.getItemCount();i++){
                    text=text+combo2.getItemAt(i)+"\n";
                }
                out.print(text);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        else
            JOptionPane.showMessageDialog(null,"This field can't be removed!","Error message nr.4",JOptionPane.ERROR_MESSAGE);

    }

}
