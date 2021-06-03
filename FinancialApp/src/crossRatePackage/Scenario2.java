package crossRatePackage;

import javax.swing.*;

/**
 @author Radosław Mieckowski
 */

public class Scenario2 extends Scenario1{
    public Scenario2(){
        super();
        setTitle("Scenario2");
    }

    /*public static void main(String[] args) {
        Scenario2 s2=new Scenario2();
        s2.computeCrossCourse();
    }*/
    @Override
    public void computeCrossCourse(){
        try{
            double q1=Double.parseDouble(super.getData1().getText().trim().replace(",","."));
            double q2=Double.parseDouble(super.getData2().getText().trim().replace(",","."));
            double q3=Double.parseDouble(super.getData3().getText().trim().replace(",","."));
            double q4=Double.parseDouble(super.getData4().getText().trim().replace(",","."));
            double q5=Math.round(q1*q3*10000);
            q5=q5/10000;
            double q6=Math.round(q2*q4*10000);
            q6=q6/10000;
            System.out.println(q5);
            System.out.println(q6);
        } catch(NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Incorrect data in input fields!","Error Message 6",JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public void setLabels(){
        super.setFirstPair(new JLabel(ScenarioSelection.getBaseCurrency()+"/"+ ScenarioSelection.getTransitionCurrency(),JLabel.TRAILING));
        super.setSecondPair(new JLabel(ScenarioSelection.getTransitionCurrency()+"/"+ ScenarioSelection.getQuoteCurrency(),JLabel.TRAILING));
        super.setThirdPair(new JLabel(ScenarioSelection.getBaseCurrency()+"/"+ ScenarioSelection.getQuoteCurrency(),JLabel.TRAILING));

    }

}

