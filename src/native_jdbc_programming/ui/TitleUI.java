package native_jdbc_programming.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class TitleUI extends JFrame {

	private JPanel contentPane;
	private JPanel pTitle;
	private JPanel pBtn;
	private JPanel pList;
	private JTextField tfTno;
	private JTextField tfTname;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TitleUI frame = new TitleUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TitleUI() {
		setTitle("직책정보");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pTitle = new JPanel();
		contentPane.add(pTitle);
		pTitle.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lblTno = new JLabel("직책번호");
		lblTno.setHorizontalAlignment(SwingConstants.RIGHT);
		pTitle.add(lblTno);
		
		tfTno = new JTextField();
		pTitle.add(tfTno);
		tfTno.setColumns(10);
		
		JLabel lblTname = new JLabel("직책명");
		lblTname.setHorizontalAlignment(SwingConstants.RIGHT);
		pTitle.add(lblTname);
		
		tfTname = new JTextField();
		tfTname.setColumns(10);
		pTitle.add(tfTname);
		
		pBtn = new JPanel();
		contentPane.add(pBtn);
		
		JButton btnAdd = new JButton("추가");
		pBtn.add(btnAdd);
		
		JButton btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
		
		pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pList.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{1, "사장"},
			},
			new String[] {
				"직책번호", "직책명"
			}
		));
		table.setToolTipText("");
		scrollPane.setViewportView(table);
	}

}
