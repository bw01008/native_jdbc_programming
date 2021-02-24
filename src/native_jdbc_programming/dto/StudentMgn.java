package native_jdbc_programming.dto;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import native_jdbc_programming.dao.impl.StudentDaoImpl;

public class StudentMgn extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tfStdNo;
	private JTextField tfStdName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMath;
	private JTable stdTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMgn frame = new StudentMgn();
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
	public StudentMgn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel stdPanel = new JPanel();
		contentPane.add(stdPanel);
		stdPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblStdNo = new JLabel("학생 번호");
		lblStdNo.setHorizontalAlignment(SwingConstants.RIGHT);
		stdPanel.add(lblStdNo);
		
		tfStdNo = new JTextField();
		stdPanel.add(tfStdNo);
		tfStdNo.setColumns(10);
		
		JLabel lblStdName = new JLabel("학생명");
		lblStdName.setHorizontalAlignment(SwingConstants.RIGHT);
		stdPanel.add(lblStdName);
		
		tfStdName = new JTextField();
		tfStdName.setColumns(10);
		stdPanel.add(tfStdName);
		
		JLabel lblKor = new JLabel("국어");
		lblKor.setHorizontalAlignment(SwingConstants.RIGHT);
		stdPanel.add(lblKor);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		stdPanel.add(tfKor);
		
		JLabel lblEng = new JLabel("영어");
		lblEng.setHorizontalAlignment(SwingConstants.RIGHT);
		stdPanel.add(lblEng);
		
		tfEng = new JTextField();
		tfEng.setColumns(10);
		stdPanel.add(tfEng);
		
		JLabel lblMath = new JLabel("수학");
		lblMath.setHorizontalAlignment(SwingConstants.RIGHT);
		stdPanel.add(lblMath);
		
		tfMath = new JTextField();
		tfMath.setColumns(10);
		stdPanel.add(tfMath);
		
		JPanel btnPanel = new JPanel();
		contentPane.add(btnPanel);
		
		JButton btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		btnPanel.add(btnAdd);
		
		JButton btnCancel = new JButton("취소");
		btnPanel.add(btnCancel);
		
		JPanel listPanel = new JPanel();
		contentPane.add(listPanel);
		listPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		listPanel.add(scrollPane, BorderLayout.CENTER);
		
		stdTable = new JTable();
		stdTable.setModel(getModel() );
		scrollPane.setViewportView(stdTable);
	}

	public DefaultTableModel getModel() {
		return new DefaultTableModel( getStudents(),getColumnNames());
	}

	public String[] getColumnNames() {
		return new String[] {
			"학생번호", "학생명", "국어", "영어", "수학", "총점", "평균"
		};
	}

	public Object[][] getStudents() {
		List<Student> list = StudentDaoImpl.getInstance().selectStudentByAll();
		Object[][] arr = new Object[list.size()][];
		for(int i=0; i< list.size(); i++) {
			Student std = list.get(i);
			arr[i] = new Object[]{
					std.getStdNo(), 
					std.getstdName(), 
					std.getKor(), 
					std.getEng(), 
					std.getMath(), 
					std.total(), 
					String.format("%.1f",std.avg())
					};
		}
		return arr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("버튼 누름");
		Student newStd = getStudent();
		StudentDaoImpl.getInstance().insertStudent(newStd);
		stdTable.setModel(getModel() );
		JOptionPane.showMessageDialog(null, "추가 완료");
		clearTf();
	}

	private void clearTf() {
		tfStdNo.setText("");
		tfStdName.setText("");
		tfKor.setText("");
		tfEng.setText("");
		tfMath.setText("");
	}

	private Student getStudent() {
		int stdNo = Integer.parseInt(tfStdNo.getText().trim());
		String stdname = tfStdName.getText().trim();
		int kor = Integer.parseInt(tfKor.getText().trim());
		int eng = Integer.parseInt(tfEng.getText().trim());
		int math = Integer.parseInt(tfMath.getText().trim());
		return new Student(stdNo, stdname, kor, eng, math);
	}

}
