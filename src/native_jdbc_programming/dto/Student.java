package native_jdbc_programming.dto;

public class Student  implements Comparable<Student>{ //기본정렬을 학번으로 한다는뜻 

	private int stdNo; // 학번
	private String stdname; // 성명
	private int kor;
	private int eng;
	private int math;

	
	public Student() {
		
		// TODO Auto-generated constructor stub
	}

	public Student(int stdNo, String stdname, int kor, int eng, int math) {
		this.stdNo = stdNo;
		this.stdname = stdname;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public int getStdNo() {
		return stdNo;
	}

	public void setStdNo(int stdNo) {
		this.stdNo = stdNo;
	}

	public String getstdName() {
		return stdname;
	}

	public void setName(String stdName) {
		this.stdname = stdName;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public Student(int stdNo) { // 매개변수가 있는 생성자를 만들경우 이걸 넣어줘야한다.
		this.stdNo = stdNo; // super는 빼도된다.

	}
//여기도 겟셋??
	
	public int total() {
		return kor + eng + math;
	}

	public double avg() {
		return total() / 3D;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stdNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (stdNo != other.stdNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%3d %5s %3d %3d %3d %3d %.1f", stdNo, stdname, kor, eng, math, total(), avg());
	}

	@Override
	public int compareTo(Student o) {
		
		return this.stdNo- o.stdNo;
	}

}
