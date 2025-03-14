package Presentation;

import Model.Bill;

import javax.swing.*;

/**
 * The GUI which implements all the heirs of the {@link Presentation.Pane} class into a tabbed pane structure.
 */
public class GUI {
    public GUI() {
        JFrame frame = new JFrame();
        frame.setSize(800,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(800, 700);

        tabbedPane.add("Client", new ClientPane());
        tabbedPane.add("Product", new ProductPane());
        tabbedPane.add("Orders", new OrdersPane());
        tabbedPane.add("Bill", new BillPane());

        frame.setContentPane(tabbedPane);
        frame.setVisible(true);
    }
}
