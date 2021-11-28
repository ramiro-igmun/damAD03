package student.domain;

import java.util.Objects;

public class Grade {

    private String dni;
    private int code;
    private String subjectAbr;
    private int grade;

    public Grade(String dni, int code, int grade) {
        this.dni = dni;
        this.code = code;
        this.grade = grade;
    }

    public String getDni() {
        return dni;
    }

    public int getCode() {
        return code;
    }

    public String getSubjectAbr() {
        return subjectAbr;
    }

    public void setSubjectAbr(String subjectAbr) {
        this.subjectAbr = subjectAbr;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return dni.equals(grade.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "student.domain.Grade{" +
                "dni='" + dni + '\'' +
                ", code=" + code +
                ", grade=" + grade +
                '}';
    }
}
