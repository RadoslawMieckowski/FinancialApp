package crossRatePackage;

import javax.swing.*;

/**
 @author Radosław Mieckowski
 */

public class Scenario3 extends Scenario1 {
    public Scenario3() {
        super();
        setTitle("Scenario3");
    }

    @Override
    public void setLabels() {
        super.setFirstPair(new JLabel(ScenarioSelection.getBaseCurrency() + "/" + ScenarioSelection.getTransitionCurrency(), JLabel.TRAILING));
        super.setSecondPair(new JLabel(ScenarioSelection.getQuoteCurrency() + "/" + ScenarioSelection.getTransitionCurrency(), JLabel.TRAILING));
        super.setThirdPair(new JLabel(ScenarioSelection.getBaseCurrency() + "/" + ScenarioSelection.getQuoteCurrency(), JLabel.TRAILING));
    }
}

