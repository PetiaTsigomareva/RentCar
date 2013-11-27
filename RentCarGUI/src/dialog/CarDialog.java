package dialog;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.Color.black;
import static java.awt.Toolkit.getDefaultToolkit;
import static java.lang.Double.parseDouble;
import static javax.swing.BorderFactory.createMatteBorder;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;

import bean.Car;
import frame.MainFrame;

public class CarDialog extends JDialog {

  private static final long serialVersionUID = 1L;

  private final Session hbSession;

  private JPanel jContentPane;

  private JPanel jPanelButtons;

  private JPanel jPanelInfo;

  private JButton jButtonOK;

  private JButton jButtonCancle;

  private JTextField jTextProducer;

  private JLabel jLableProducer; // @jve:decl-index=0:visual-constraint="517,60"

  private JTextField jTextModification;

  private JLabel jLableModification; // @jve:decl-index=0:visual-constraint="361,18"

  private JTextField jTextColor; // @jve:decl-index=0:visual-constraint="229,87"

  private JLabel jLabelColor; // @jve:decl-index=0:visual-constraint="529,21"

  private JTextField jTextManifactureDate; // @jve:decl-index=0:visual-constraint="458,43"

  private JLabel jLabelManifactureDate; // @jve:decl-index=0:visual-constraint="600,28"

  private JTextField jTextRegistrationNumber; // @jve:decl-index=0:visual-constraint="458,43"

  private JLabel jLabelRegistrationNumber; // @jve:decl-index=0:visual-constraint="600,28"

  private JTextField jTextPriceForDay; // @jve:decl-index=0:visual-constraint="111,299"

  private JLabel jLabelPriceForDay; // @jve:decl-index=0:visual-constraint="344,52"

  public CarDialog(Frame owner, Session hbSession) {
    super(owner);

    this.hbSession = hbSession;
    initialize();
  }

  private void initialize() {
    setSize(265, 283);
    setModal(true);
    setTitle("Add Car");
    setResizable(false);
    setContentPane(getJContentPane());
    Dimension screenSize = getDefaultToolkit().getScreenSize();
    setLocation((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 3);
  }

  private JPanel getJContentPane() {
    if (jContentPane == null) {
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
      jContentPane.add(getJPanelInfo(), CENTER);
      jContentPane.add(getJPanelButtons(), SOUTH);

    }
    return jContentPane;
  }

  private JPanel getJPanelButtons() {
    if (jPanelButtons == null) {
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setHgap(25);

      jPanelButtons = new JPanel();
      jPanelButtons.setBorder(createMatteBorder(1, 0, 0, 0, black));
      jPanelButtons.setLayout(flowLayout);
      jPanelButtons.setPreferredSize(new Dimension(0, 35));
      jPanelButtons.add(getJButtonOK(), null);
      jPanelButtons.add(getJButtonCancle(), null);

    }
    return jPanelButtons;

  }

  private JButton getJButtonOK() {
    if (jButtonOK == null) {
      jButtonOK = new JButton();
      jButtonOK.setText("Add");
      jButtonOK.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          addCar();
        }
      });

    }
    return jButtonOK;
  }

  private JButton getJButtonCancle() {
    if (jButtonCancle == null) {
      jButtonCancle = new JButton();
      jButtonCancle.setText("Cancel");
      jButtonCancle.setPreferredSize(new Dimension(78, 26));
      jButtonCancle.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          setVisible(false);
        }
      });
    }
    return jButtonCancle;
  }

  private JPanel getJPanelInfo() {
    if (jPanelInfo == null) {
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setAlignment(FlowLayout.LEFT);
      flowLayout.setVgap(8);
      flowLayout.setHgap(10);
      jLableProducer = new JLabel();
      jLableProducer.setText("Producer:");
      jLableProducer.setPreferredSize(new Dimension(80, 16));
      jLableModification = new JLabel();
      jLableModification.setText("Modification:");
      jLableModification.setPreferredSize(new Dimension(80, 16));
      jLabelColor = new JLabel();
      jLabelColor.setText("Color:");
      jLabelColor.setPreferredSize(new Dimension(80, 16));
      jLabelManifactureDate = new JLabel();
      jLabelManifactureDate.setText("Manifacture Date:");
      jLabelManifactureDate.setPreferredSize(new Dimension(140, 16));
      jLabelRegistrationNumber = new JLabel();
      jLabelRegistrationNumber.setText("Registration Number:");
      jLabelRegistrationNumber.setPreferredSize(new Dimension(140, 16));
      jLabelPriceForDay = new JLabel();
      jLabelPriceForDay.setText("Price For Day:");
      jLabelPriceForDay.setPreferredSize(new Dimension(140, 16));
      jPanelInfo = new JPanel();
      jPanelInfo.setLayout(flowLayout);
      jPanelInfo.add(jLableProducer, null);
      jPanelInfo.add(getJTextProducer(), null);
      jPanelInfo.add(jLableModification, null);
      jPanelInfo.add(getJTextModification(), null);
      jPanelInfo.add(jLabelColor, null);
      jPanelInfo.add(getJTextColor(), null);
      jPanelInfo.add(jLabelManifactureDate, null);
      jPanelInfo.add(getJTextManufactureDate(), null);
      jPanelInfo.add(jLabelRegistrationNumber, null);
      jPanelInfo.add(getJTextRegistrationNumber(), null);
      jPanelInfo.add(jLabelPriceForDay, null);
      jPanelInfo.add(getJTextPriceForDay(), null);

    }
    return jPanelInfo;
  }

  private JTextField getJTextProducer() {
    if (jTextProducer == null) {
      jTextProducer = new JTextField();
      jTextProducer.setPreferredSize(new Dimension(150, 20));

    }
    return jTextProducer;
  }

  private JTextField getJTextModification() {
    if (jTextModification == null) {
      jTextModification = new JTextField();
      jTextModification.setPreferredSize(new Dimension(150, 20));

    }
    return jTextModification;
  }

  /**
   * This method initializes jTextColor
   * 
   * @return javax.swing.JTextField
   */
  private JTextField getJTextColor() {
    if (jTextColor == null) {
      jTextColor = new JTextField();
      jTextColor.setPreferredSize(new Dimension(150, 20));
    }
    return jTextColor;
  }

  /**
   * This method initializes jTextManifactureDate
   * 
   * @return javax.swing.JTextField
   */
  private JTextField getJTextManufactureDate() {
    if (jTextManifactureDate == null) {
      jTextManifactureDate = new JTextField();
      jTextManifactureDate.setPreferredSize(new Dimension(90, 20));
    }
    return jTextManifactureDate;
  }

  /**
   * This method initializes jTextManifactureDate
   * 
   * @return javax.swing.JTextField
   */
  private JTextField getJTextRegistrationNumber() {
    if (jTextRegistrationNumber == null) {
      jTextRegistrationNumber = new JTextField();
      jTextRegistrationNumber.setPreferredSize(new Dimension(90, 20));
    }
    return jTextRegistrationNumber;
  }

  /**
   * This method initializes jTextPriceForDay
   * 
   * @return javax.swing.JTextField
   */
  private JTextField getJTextPriceForDay() {
    if (jTextPriceForDay == null) {
      jTextPriceForDay = new JTextField();
      jTextPriceForDay.setPreferredSize(new Dimension(90, 20));
    }
    return jTextPriceForDay;
  }

  private Date getManifactureDate() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    sdf.setLenient(false);
    String dateStr = jTextManifactureDate.getText();
    Date date = sdf.parse(dateStr);
    return date;
  }

  private double getPriceforDay() throws NumberFormatException {
    String priceStr = jTextPriceForDay.getText();
    double priceForDay = parseDouble(priceStr);
    if (priceForDay <= 0) {
      throw new NumberFormatException();
    }
    return priceForDay;
  }

  private void addCar() {
    StringBuilder error = new StringBuilder();
    Car car = new Car();

    stringValidation(jTextProducer.getText(), 30, "Producer", error);
    car.setProducer(jTextProducer.getText());

    stringValidation(jTextModification.getText(), 30, "Modification", error);
    car.setModification(jTextModification.getText());

    stringValidation(jTextColor.getText(), 30, "Color", error);
    car.setColor(jTextColor.getText());

    stringValidation(jTextRegistrationNumber.getText(), 30, "RegistrationNumber", error);
    car.setRegistrationNumber(jTextRegistrationNumber.getText());

    try {
      car.setManifactureDate(getManifactureDate());
    } catch (ParseException ex) {
      error.append("The Field \" Manifacture Date\" must be in format \"dd.MM.yyyy \"!\r\n");
    }

    try {
      car.setPriceForDay(getPriceforDay());
    } catch (NumberFormatException e) {
      error.append("The Field \"Price for Day\" must be number > 0 !\r\n");
    }

    if (error.length() == 0) {
      car.store(hbSession);
      ((MainFrame) getOwner()).getCarModel().setTableData(Car.getAllCars(hbSession));
      ((MainFrame) getOwner()).getCarModel().fireTableDataChanged();

      setVisible(false);
    } else {
      showMessageDialog(null, error, "ERROR", ERROR_MESSAGE);
    }
  }

  private void stringValidation(String str, int upperBound, String fieldAppend, StringBuilder error) {
    if (str.length() == 0) {
      error.append("Text Field \"");
      error.append(fieldAppend);
      error.append("\" is empty!\r\n");
    } else if (str.length() > upperBound) {
      error.append("Text Field \"");
      error.append(fieldAppend);
      error.append("\" should not exceed ");
      error.append(upperBound);
      error.append(" symbol!\r\n");
    }
  }
}