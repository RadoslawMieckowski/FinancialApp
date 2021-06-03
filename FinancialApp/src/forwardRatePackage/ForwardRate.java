package forwardRatePackage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForwardRate extends JFrame{
    private static int FRAME_WIDTH = 400;
    private static int FRAME_HEIGHT = 350;
    private JPanel mainPanel;
    private JTextField currencyPairTextField;
    private JTextField spotQuotationTextField;
    private JTextField forwardPointsTextField;
    private JTextField forwardRateTextField;
    private JPanel inputAndOutputPanel;
    private JLabel currencyPairLabel;
    private JLabel spotQuotationLabel;
    private JLabel forwardPointsLabel;
    private JPanel buttonPanel;
    private JPanel textAreaPanel;
    private JScrollPane scrollPane1;
    private JButton checkButton;
    private JButton clearButton;
    private JPanel titlePanel;
    private JLabel forwardRateLabel;
    private JLabel titleLabel;
    private JButton loadButton;
    private CurrencyChooserDialog chooseCurrencyDialog;
    private List<String> currencyList;
    private List<String> bidList;
    private List<String> askList;
    private static String[] comboArray;

     static String[] getComboArray() {
        return comboArray;
    }

    public ForwardRate(){
//        createUIComponents();
        setTitle("ForwardRate");
        setFrameInTheMiddle(this,FRAME_WIDTH,FRAME_HEIGHT);
        setContentPane(this.mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        currencyPairTextField.setToolTipText("Type for example: USD/EUR");
        currencyPairTextField.setText("");
        loadButton.setToolTipText("Load quotation via Internet.");
        forwardPointsTextField.setToolTipText("Type forward points");
        forwardPointsTextField.setText("");
//        timeTextField.setToolTipText("type nubmer (integer)");
//        timeTextField.setText("");
        forwardRateTextField.setEditable(false);
        forwardRateTextField.setText("");
        loadButton.addActionListener(event->{
            makeCurrencyComboList();
        });
        checkButton.addActionListener(event->{
            Pattern invalidCurrencyTextpattern = Pattern.compile("^([A-Z]||[a-z])");
            Pattern invalidSpotQuotationTextpattern = Pattern.compile("[A-Z]||[a-z]");
            Pattern invalidForwardPointsTextpattern = Pattern.compile("^[0-9]");
            Matcher matcher = invalidCurrencyTextpattern.matcher(currencyPairTextField.getText());
            Matcher matcher2 = invalidSpotQuotationTextpattern.matcher(spotQuotationTextField.getText());
            Matcher matcher3 = invalidForwardPointsTextpattern.matcher(forwardPointsTextField.getText());
           /* if (currencyPairTextField.getText().equals("")||matcher.find()){
                System.out.println(matcher.find());
                JOptionPane.showMessageDialog(null,"currency pair mustn't be a number!","Invalid data",JOptionPane.ERROR_MESSAGE);*/
/*            }else*//* if(spotQuotationTextField.getText().equals("")||(matcher2.find())){
                System.out.println(matcher2.find());
                JOptionPane.showMessageDialog(null,"incorrect spot quotation!","Invalid data",JOptionPane.ERROR_MESSAGE);
            }else *//*if(forwardPointsTextField.getText().equals("")||(matcher3.find())){
                JOptionPane.showMessageDialog(null,"forward points must be a number!","Invalid data",JOptionPane.ERROR_MESSAGE);
                System.out.println(matcher3.find());
            }
            else{*/
            if(forwardPointsTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"forward points must be a number!","Invalid data",JOptionPane.ERROR_MESSAGE);
            }else{
                double bid;
                double ask;
                String spotSelectedQuotationsTable[]=spotQuotationTextField.getText().replace(",",".").split("/");
                bid= Double.parseDouble(spotSelectedQuotationsTable[0])+Double.parseDouble(forwardPointsTextField.getText())/1000;
                bid=Math.round(bid*10000);
                bid=bid/10000;
                ask= Double.parseDouble(spotSelectedQuotationsTable[1])+Double.parseDouble(forwardPointsTextField.getText())/1000;
                ask=Math.round(ask*10000);
                ask=ask/10000;
                forwardRateTextField.setText(bid+"/"+ask);
            }

           /* }*/
        });
        clearButton.addActionListener(event->{
            currencyPairTextField.setText("");
            spotQuotationTextField.setText("");
            forwardPointsTextField.setText("");
            forwardRateTextField.setText("");
        });
    }
   /* public static void main(String[] args) {
        ForwardRate forwardRate = new ForwardRate();
    }*/

    private void createUIComponents() {

        mainPanel=new JPanel();
        currencyPairTextField=new JTextField();
        spotQuotationTextField=new JTextField();
        forwardPointsTextField=new JTextField();
//        timeTextField=new JTextField();
        forwardRateTextField=new JTextField();
        inputAndOutputPanel=new JPanel();
        inputAndOutputPanel=new JPanel();
        currencyPairLabel=new JLabel();
        spotQuotationLabel=new JLabel();
        forwardPointsLabel=new JLabel();
//        timeLabel=new JLabel();
        currencyPairLabel=new JLabel();
        buttonPanel=new JPanel();
        textAreaPanel=new JPanel();
        scrollPane1= new JScrollPane();
        checkButton =new JButton();
        clearButton =new JButton();
        titlePanel=new JPanel();
        forwardRateLabel=new JLabel();
        titleLabel=new JLabel();
        loadButton=new JButton();

    }
    public static void setFrameInTheMiddle(Window window,int frameWidth, int frameHeight){
        FRAME_WIDTH=frameWidth;
        FRAME_HEIGHT=frameHeight;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        window.setBounds(screenWidth / 2 - FRAME_WIDTH / 2, screenHeight / 2 - FRAME_HEIGHT / 2, FRAME_WIDTH, FRAME_HEIGHT);
    }
    public void makeCurrencyComboList(){
        Document doc=null;
        try {
            doc= Jsoup.connect("https://www.nbp.pl/home.aspx?f=/kursy/kursyc.html").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements currencyPairsElementsPartI=doc.getElementsByClass("bgt1 right");
        for(Element el:currencyPairsElementsPartI){
            if(el.text().equals("100 HUF")){
                el.text("HUF");
            }else el.text(el.text().replace("1 ",""));
            el.appendText(" ");
        }
        Elements currencyPairsElementsPartII=doc.getElementsByClass("bgt2 right");
        for(Element el:currencyPairsElementsPartII) {
            if (el.text().equals("100 JPY")) {
                el.text("JPY");
            }else el.text(el.text().replace("1 ",""));
            el.appendText(" ");
        }

        Elements elements= new Elements();
        elements.addAll(currencyPairsElementsPartI);
        elements.addAll(currencyPairsElementsPartII);

        currencyList =new ArrayList(13);
        bidList =new ArrayList(13);
        askList =new ArrayList(13);
        String lineOfData=elements.text();
        String [] piecesofDataTable = lineOfData.split(" ");
        System.out.println(Arrays.toString(piecesofDataTable));
        for ( int i=0;i<piecesofDataTable.length;i+=3)
        {
            currencyList.add(piecesofDataTable[i]);
        }
        for ( int i=1;i<piecesofDataTable.length;i+=3)
        {
            bidList.add(piecesofDataTable[i]);
        }
        for ( int i=2;i<piecesofDataTable.length;i+=3)
        {
            askList.add(piecesofDataTable[i]);
        }
        System.out.println(currencyList);
        System.out.println(bidList);
        System.out.println(askList);

        comboArray = new String[currencyList.size()];
        currencyList.toArray(comboArray);
        for (int i=0;i<comboArray.length;i++){
            comboArray[i]=comboArray[i]+"/PLN";
        }
        chooseCurrencyDialog=new CurrencyChooserDialog(this);
    }

    private class CurrencyChooserDialog extends JDialog{

        private JComboBox<String>currencyPairComboBox;
        private JButton okButton;
        private String choosenPair;

    public CurrencyChooserDialog(ForwardRate owner){
        super(owner,"Choose currency Pair",true);
        JPanel panel =new JPanel(new GridLayout(3,1,3,3));
        panel.add(new JLabel("Choose Currency Pair",SwingConstants.CENTER));
        currencyPairComboBox=new JComboBox<>(ForwardRate.getComboArray());
        panel.add(currencyPairComboBox);
        okButton = new JButton("ok");
        okButton.addActionListener(event->setVisible(false));
        setFrameInTheMiddle(this,250,190);
        okButton.addActionListener(event->{
            choosenPair=(String)currencyPairComboBox.getSelectedItem();
            currencyPairTextField.setText(choosenPair);
            spotQuotationTextField.setText(bidList.get(currencyPairComboBox.getSelectedIndex())+"/"+askList.get(currencyPairComboBox.getSelectedIndex()));

        });
        panel.add(okButton);
        add(panel);
        setSize(250,190);
        setVisible(true);
    }
}

}
