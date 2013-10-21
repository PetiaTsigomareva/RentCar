package frame;

import static java.awt.Color.black;
import static javax.swing.BorderFactory.createMatteBorder;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.CarsModel;

import org.hibernate.Session;

import session.SessionManager;
import bean.Car;
import dialog.CarDialog;

public class MainFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  private Session hbSession;

  private CarsModel carModel;

  private JPanel jContentPane;

  private JPanel jPanelActionsCar;

  private JButton jAddButton;

  private JButton jDeleteButton;

  private JLabel jLabelCar; // @jve:decl-index=0:visual-constraint="578,48"

  private JScrollPane jScrollPaneCars; // @jve:decl-index=0:visual-constraint="553,78"

  private JTable jTableCars; // @jve:decl-index=0:visual-constraint="620,263"

  /**
   * This is the default constructor
   */
  public MainFrame() {
    super();
    initialize();
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    hbSession = SessionManager.openSession();

    setSize(443, 400);
    setTitle("\"Tsigomarevi Rent a Car\"");
    setContentPane(getJContentPane());
  }

  /**
   * This method initializes jContentPane
   * 
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane() {
    if (jContentPane == null) {
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());

      jContentPane.add(getJLabelCar(), BorderLayout.NORTH);
      jContentPane.add(getJScrollPaneCars(), BorderLayout.CENTER);
      jContentPane.add(getJPanelButtons(), BorderLayout.SOUTH);
    }
    return jContentPane;
  }

  private JLabel getJLabelCar() {
    if (jLabelCar == null) {
      jLabelCar = new JLabel();
      jLabelCar.setText(" Free Cars List ");
    }
    return jLabelCar;
  }

  private JPanel getJPanelButtons() {
    if (jPanelActionsCar == null) {
      jPanelActionsCar = new JPanel();
      jPanelActionsCar.setLayout(new FlowLayout());
      jPanelActionsCar.setPreferredSize(new Dimension(0, 35));
      jPanelActionsCar.setBorder(createMatteBorder(1, 0, 1, 0, black));
      jPanelActionsCar.add(getJAddButton(), null);
      jPanelActionsCar.add(getJDeleteButton(), null);
    }
    return jPanelActionsCar;
  }

  /**
   * This method initializes jAddButton
   * 
   * @return javax.swing.JButton
   */
  private JButton getJAddButton() {
    if (jAddButton == null) {
      jAddButton = new JButton();
      jAddButton.setText("Add Car");
      jAddButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          getCarDialog(hbSession).setVisible(true);
        }
      });
    }
    return jAddButton;
  }

  /**
   * This method initializes jDeleteButton
   * 
   * @return javax.swing.JButton
   */
  private JButton getJDeleteButton() {
    if (jDeleteButton == null) {
      jDeleteButton = new JButton();
      jDeleteButton.setText("Delete Car");
      jDeleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          deleteCar();
        }
      });
    }
    return jDeleteButton;
  }

  /**
   * This method initializes jScrollPaneCar
   * 
   * @return javax.swing.JScrollPane
   */
  private JScrollPane getJScrollPaneCars() {
    if (jScrollPaneCars == null) {
      jScrollPaneCars = new JScrollPane();
      jScrollPaneCars.setPreferredSize(new Dimension(453, 150));
      jScrollPaneCars.setViewportView(getJTableCars());
    }
    return jScrollPaneCars;
  }

  /**
   * This method initializes jTableCars
   * 
   * @return javax.swing.JTable
   */
  public JTable getJTableCars() {
    if (jTableCars == null) {
      jTableCars = new JTable(getCarModel());
      getCarModel().setTableData(Car.getAllCars(hbSession));
      carModel.fireTableDataChanged();

      jTableCars.setModel(getCarModel());
      jTableCars.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      jTableCars.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
          int selectedRow = jTableCars.getSelectedRow();
          if (selectedRow != -1) {
            jDeleteButton.setEnabled(true);
          } else {
            jDeleteButton.setEnabled(false);
          }
        }

      });
    }
    return jTableCars;
  }

  public CarsModel getCarModel() {
    if (carModel == null) {
      carModel = new CarsModel();
      // carModel.addTableModelListener(this);
    }
    return carModel;
  }

  private CarDialog getCarDialog(Session hbSession) {
    CarDialog dialog = new CarDialog(this, hbSession);
    return dialog;
  }

  private void deleteCar() {
    Car car = new Car();
    int selectedRow = jTableCars.getSelectedRow();
    car = getCarModel().getRow(selectedRow);
    try {
      car.delete(hbSession);
      getCarModel().setTableData(Car.getAllCars(hbSession));
      carModel.fireTableDataChanged();
    } catch (Exception e) {
      showMessageDialog(this, "The delete car failed!\r\n Please try again!", "Error", ERROR_MESSAGE);
      this.jTableCars.setEditingRow(-1);
    }
  }

}
