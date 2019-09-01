package com.rusefi.ui.etb;

import com.rusefi.ETBPane;
import com.rusefi.core.Sensor;
import com.rusefi.ldmp.generated.ElectronicThrottleMeta;
import com.rusefi.ui.livedocs.LiveDocPanel;
import com.rusefi.ui.storage.Node;
import com.rusefi.ui.util.UiUtils;
import com.rusefi.ui.widgets.AnyCommand;
import com.rusefi.ui.widgets.DetachedSensor;
import org.putgemin.VerticalFlowLayout;

import javax.swing.*;

/**
 * Panel for ETB bench testing & research.
 */
public class EtbCommandsPanel {
    private final JPanel content = new JPanel(new VerticalFlowLayout());

    public EtbCommandsPanel() {
        content.add(new DirectDrivePanel().getContent());

        JPanel spotsPane = new JPanel(new VerticalFlowLayout());
        spotsPane.setBorder(BorderFactory.createTitledBorder("Magic Spots"));
        MagicSpotsFinder magicSpotsFinder = new MagicSpotsFinder();
        spotsPane.add(UiUtils.wrap(magicSpotsFinder.getButton()));
        spotsPane.add(magicSpotsFinder.getPoints());

        JPanel testParameters = new JPanel(new VerticalFlowLayout());
        testParameters.setBorder(BorderFactory.createTitledBorder("Try PID settings"));
        EtbTestSequence etbTestSequence = new EtbTestSequence();
        testParameters.add(etbTestSequence.getButton());
        testParameters.add(UiUtils.wrap(etbTestSequence.getCancelButton()));
        testParameters.add(etbTestSequence.getResult());
        testParameters.add(new JLabel("To change setting use following commands:"));
        testParameters.add(new JLabel("set etb_p X"));
        testParameters.add(new JLabel("set etb_i X"));
        testParameters.add(new JLabel("set etb_d X"));
        testParameters.add(new JLabel("set etb_o X"));
        testParameters.add(new JLabel("For example:"));
        testParameters.add(new JLabel("set etb_p 1.1"));

        content.setBorder(BorderFactory.createTitledBorder("Commands"));

        content.add(testParameters);
        content.add(spotsPane);
        content.add(UiUtils.wrap(new EtbMonteCarloSequence().getButton()));

        content.add(AnyCommand.createArea(new Node(), ETBPane.SET_ETB + "10", false, false).getContent());


        content.add(DetachedSensor.createMockVoltageSlider(Sensor.PPS));

        content.add(LiveDocPanel.createPanel("ETB", ElectronicThrottleMeta.CONTENT));
    }

    public JComponent getContent() {
        return new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}