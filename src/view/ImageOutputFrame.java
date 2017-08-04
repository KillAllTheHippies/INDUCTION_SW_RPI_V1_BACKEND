package view;

import controller.InductionSWController;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * Created by Jamie on 30/07/2017.
 */
public class ImageOutputFrame extends JFrame {

    // Constructor
    public ImageOutputFrame() throws HeadlessException {

        getContentPane().setBackground(Color.white);
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        getContentPane().setBackground(Color.white);
//        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        mainPanel.add(createCenterPanel());
        this.add(mainPanel);

        this.pack();
        this.setVisible(true);
        createImageFromFrame();
        InductionSWController.getInstance().printDetails();
        this.dispose();

    }
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new MigLayout("wrap 1"));

        getContentPane().setBackground(Color.white);
        Date d = new Date(InductionSWController.getInstance().getCurrentInductee().getDateOfInduction());
        JLabel lblDateTime = new JLabel(d.toString() );
        lblDateTime.setFont(new Font("Times New Roman", 0, 30));
        centerPanel.add(lblDateTime,BorderLayout.CENTER);
//        ImagePanel imagePanel = new ImagePanel(InductionSWController.getInstance().getCurrentInductee().getPhoto());
//        imagePanel.setSize(InductionSWController.getInstance().getCurrentInductee().getPhoto().getWidth(),InductionSWController.getInstance().getCurrentInductee().getPhoto().getHeight());
        JPanel imagePanel = new JPanel();
        imagePanel.setOpaque(false);

        BufferedImage img = InductionSWController.getInstance().getCurrentInductee().getPhoto();

        ImageIcon icon = new ImageIcon(img);
        JLabel label = new JLabel(icon);
        imagePanel.add(label);

        centerPanel.add(imagePanel,BorderLayout.CENTER);
        centerPanel.add(createDetailsPanel(),BorderLayout.EAST);
        return centerPanel;
    }

    private JPanel createDetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setOpaque(false);

        getContentPane().setBackground(Color.white);
        detailsPanel.setLayout(new MigLayout("wrap 1"));
        //public Inductee(String name, String phoneNum, String email, String jobTitle, String carReg, Boolean firstAidTrained, long dateOfInduction )
        JLabel lblName = new JLabel("Name:" + InductionSWController.getInstance().getCurrentInductee().getName());
        lblName.setFont(new Font("Times New Roman", 0, 30));
        JLabel lblPhoneNum = new JLabel("Phone: " + InductionSWController.getInstance().getCurrentInductee().getName());
        lblPhoneNum.setFont(new Font("Times New Roman", 0, 30));
        JLabel lblEmail = new JLabel("Email: " + InductionSWController.getInstance().getCurrentInductee().getEmail());
        lblEmail.setFont(new Font("Times New Roman", 0, 30));
        JLabel lblJobTitle = new JLabel("Job Title: " + InductionSWController.getInstance().getCurrentInductee().getJobTitle());
        lblJobTitle.setFont(new Font("Times New Roman", 0, 30));
        JLabel lblCarReg = new JLabel("Veh. Reg: " + InductionSWController.getInstance().getCurrentInductee().getCarReg());
        lblCarReg.setFont(new Font("Times New Roman", 0, 30));
        String s;
        if (InductionSWController.getInstance().getCurrentInductee().getFirstAidTrained()) {
            s = "Yes";
        } else {
            s = "No";
        }
        JLabel lblFirstAidTrained = new JLabel("First Aid Trained: " + s);
        lblFirstAidTrained.setFont(new Font("Times New Roman", 0, 30));
        Date d = new Date(InductionSWController.getInstance().getCurrentInductee().getDateOfInduction());
        JLabel lblDateTime = new JLabel(d.toString() );
        lblDateTime.setFont(new Font("Times New Roman", 0, 30));
        detailsPanel.add(lblName);
        detailsPanel.add(lblPhoneNum);
        detailsPanel.add(lblEmail);
        detailsPanel.add(lblJobTitle);
        detailsPanel.add(lblCarReg);
        detailsPanel.add(lblFirstAidTrained);
        detailsPanel.add(lblDateTime);

        return detailsPanel;

    }

    private void createImageFromFrame() {
        BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        this.paint(g);  //this == JComponent
        g.dispose();
        try{ImageIO.write(bi,"png",new File(InductionSWController.PRINTABLE_IMAGE_FILENAME));}catch (Exception e) {}
    }
}
