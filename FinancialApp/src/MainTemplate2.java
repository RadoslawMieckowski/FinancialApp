import crossRatePackage.ScenarioSelection;
import exchangeCurrencyPackage.ExchangeCurrency;
import forwardRatePackage.ForwardRate;
import reverseCoursePackage.ReverseCourse;

import javax.swing.*;
import java.awt.*;

public class MainTemplate2 extends JFrame{
    private final int FRAME_WIDTH=600;
    private final int FRAME_HEIGHT=300;
 /*   private ScenarioSelection scenarioSelection;
    private ExchangeCurrency exchangeCurrency;
    private ReverseCourse reverseCourse;
*/
    private JButton exitButton;
    private JButton exchangeCurrencyButton;
    private JButton crossRateButton;
    private JButton reverseCurrencyButton;
    private JButton forwardRateButton;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel buttonsPanel;
    private JPanel exitPanel;

    public MainTemplate2(){

        setTitle("Financial App");
        setFrameMiddle();
        setContentPane(mainPanel);
//        createUIComponents();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        exchangeCurrencyButton.addActionListener(event->{
             new ExchangeCurrency();
        });
        crossRateButton.addActionListener(event->{
            new ScenarioSelection();
        });
        reverseCurrencyButton.addActionListener(event->{
            new ReverseCourse();
        });
        exitButton.addActionListener(event->{
            System.exit(0);
        });
        forwardRateButton.addActionListener(event->{
            new ForwardRate();
        });
    }
    protected void setFrameMiddle() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setBounds(screenWidth / 2 - FRAME_WIDTH / 2, screenHeight / 2 - FRAME_HEIGHT / 2, FRAME_WIDTH, FRAME_HEIGHT);
    }
    public static void main(String[] args) {
        var mainTemplate2=new MainTemplate2();
    }

    private void createUIComponents() {
          exitButton=new JButton();
          exchangeCurrencyButton=new JButton();
          crossRateButton=new JButton();
          reverseCurrencyButton=new JButton();
          forwardRateButton=new JButton();
          mainPanel=new JPanel();
          titlePanel=new JPanel();
          titleLabel=new JLabel();
          buttonsPanel=new JPanel();
          exitPanel=new JPanel();
    }
}


