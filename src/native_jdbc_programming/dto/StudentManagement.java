package native_jdbc_programming.dto;

import java.util.Scanner;

public class StudentManagement {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Student[] students = new Student[5];
		initial(students);

		System.out.println("학생관리 프로그램");
		int choice;
		do {
			showMenu();
			System.out.print("메뉴 > ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("학생 추가");
				break;
			case 2:
				System.out.println("학생 수정");
				break;
			case 3:
				System.out.println("학생 삭제");
				break;
			case 4:
				System.out.println("학생 목록");
				prnStudent(students);
				break;
			}
		} while (choice < 5);
		sc.close();
	}

	private static void prnStudent(Student[] students) {
		for (Student std : students) {
			if (std == null)
				break;
			System.out.println(std);
		}
		System.out.println();
	}

	private static void initial(Student[] students) {
		students[0] = new Student(1, "전수린", 90, 90, 90);
		students[1] = new Student(2, "김상건", 91, 91, 91);
		students[2] = new Student(3, "이태훈", 92, 92, 92);
	}

	private static void showMenu() {
		String[] m = new String[6];
		m[0] = "메뉴를 선택하새요.\n";
		m[1] = "1. 학생 추가\n";
		m[2] = "2. 학생 수정\n";
		m[3] = "3. 학생 삭제\n";
		m[4] = "4. 학생 목록\n";
		m[5] = "5. 종료\n";

		for (String str : m) {
			System.out.print(str);
		}
	}
}
