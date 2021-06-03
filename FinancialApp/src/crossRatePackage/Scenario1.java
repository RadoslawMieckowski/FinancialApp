package crossRatePackage;

import javax.swing.*;
import java.awt.*;
/**
@author Radosław Mieckowski
 */

public class Scenario1 extends JFrame {
    private JLabel title;
    private JLabel firstPair;
    private JLabel secondPair;
    private JLabel thirdPair;
    private Font font;
    private JPanel titlePanel;
    private final int FRAME_WIDTH=400;
    private final int FRAME_HEIGHT=350;
    private JPanel centralPanel;
    private JPanel southPanel;
    private JPanel checkClearPanel;
    private JPanel returnPanel;
    private JButton checkButton;
    private JButton clearButton;
    private JButton previousPageButton;
    private JTextField data1;
    private JTextField data2;
    private JTextField data3;
    private JTextField data4;
    private JTextField data5;
    private JTextField data6;
    private double quotation1;
    private double quotation2;
    private double quotation3;
    private double quotation4;
    private double quotation5;
    private double quotation6;

    public JTextField getData1() {
        return data1;
    }

    public JTextField getData2() {
        return data2;
    }

    public JTextField getData3() {
        return data3;
    }

    public JTextField getData4() {
        return data4;
    }


    public void setFirstPair(JLabel firstPair) {
        this.firstPair = firstPair;
    }

    public void setSecondPair(JLabel secondPair) {
        this.secondPair = secondPair;
    }

    public void setThirdPair(JLabel thirdPair) {
        this.thirdPair = thirdPair;
    }

/*    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            Scenario1 s1=new Scenario1();
            s1.setVisible(true);
        });

    }*/

    public Scenario1(){
        setLayout(new BorderLayout());
        setTitle("Scenario1");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        setBounds(screenWidth/2-FRAME_WIDTH/2, screenHeight/2-FRAME_HEIGHT/2, FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

        title = new JLabel("Crosss-rate", SwingConstants.CENTER);
        font = new Font("SansSerif", Font.BOLD + Font.ITALIC, 16);
        title.setFont(font);
        titlePanel = new JPanel(new GridLayout(3, 1));
        titlePanel.add(new JLabel(""));
        titlePanel.add(title);
        titlePanel.add(new JLabel(""));
        add(titlePanel, BorderLayout.NORTH);
        centralPanel=new JPanel(new GridLayout(3,3,5,10));
        setLabels();
        System.out.println(firstPair.getText());

        centralPanel.add(firstPair);
        centralPanel.add(data1 = new JTextField("",6));
        centralPanel.add(data2 = new JTextField("",6));
        centralPanel.add(secondPair);
        centralPanel.add(data3 = new JTextField("",6));
        centralPanel.add(data4 = new JTextField("",6));
        centralPanel.add(thirdPair);
        centralPanel.add(data5 = new JTextField(6));
        data5.setEditable(false);
        centralPanel.add(data6 = new JTextField(6));
        data6.setEditable(false);
        add(centralPanel,BorderLayout.CENTER);
        southPanel = new JPanel(new GridLayout(4,1,0,10));
        southPanel.add(new JLabel(""));
        checkClearPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,40,5));
        checkClearPanel.add(checkButton=new JButton("check cross-rate"));
        checkButton.addActionListener(event->{
            if(data1.getText().equals("")||data2.getText().equals("")||data3.getText().equals("")||data4.getText().equals(""))
                JOptionPane.showMessageDialog(null,"All input fields must be filled!","Error Message 5",JOptionPane.ERROR_MESSAGE);
            else
                computeCrossCourse();
        });
        checkClearPanel.add(clearButton=new JButton("clear quotations"));
        clearButton.addActionListener(event->{
            data1.setText("");
            data2.setText("");
            data3.setText("");
            data4.setText("");
            data5.setText("");
            data6.setText("");
        });
        southPanel.add(checkClearPanel);
        previousPageButton=new JButton("return to the previous page");
        previousPageButton.addActionListener(event->{
            this.setVisible(false);
            ScenarioSelection ss=new ScenarioSelection();
            ScenarioSelection.setBaseCurrency("");
            ScenarioSelection.setQuoteCurrency("");
            ScenarioSelection.setTransitionCurrency("");
            ss.setVisible(true);

        });
        returnPanel= new JPanel(new FlowLayout(FlowLayout.CENTER));
        returnPanel.add(previousPageButton);
        southPanel.add(returnPanel);
        southPanel.add(new JLabel(""));
        add(southPanel, BorderLayout.SOUTH);
    }

    public void computeCrossCourse(){
        try{
            quotation1=Double.parseDouble(data1.getText().trim().replace(",","."));
            quotation2=Double.parseDouble(data2.getText().trim().replace(",","."));
            quotation3=Double.parseDouble(data3.getText().trim().replace(",","."));
            quotation4=Double.parseDouble(data4.getText().trim().replace(",","."));
            quotation5=Math.round(quotation1/quotation4*10000);
            quotation5=quotation5/10000;
            quotation6=Math.round(quotation2/quotation3*10000);
            quotation6=quotation6/10000;
            System.out.println(quotation5);
            System.out.println(quotation6);
            data5.setText(String.valueOf(quotation5));
            data6.setText(String.valueOf(quotation6));
        }catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Incorrect data in input fields!","Error Message 6",JOptionPane.ERROR_MESSAGE);
        }

    }
    public void setLabels(){
        firstPair = new JLabel(ScenarioSelection.getTransitionCurrency()+"/"+ ScenarioSelection.getBaseCurrency(),JLabel.TRAILING);
        secondPair = new JLabel(ScenarioSelection.getTransitionCurrency()+"/"+ ScenarioSelection.getQuoteCurrency(),JLabel.TRAILING);
        thirdPair = new JLabel(ScenarioSelection.getBaseCurrency()+"/"+ ScenarioSelection.getQuoteCurrency(),JLabel.TRAILING);
    }
}
