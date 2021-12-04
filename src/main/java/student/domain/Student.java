package student.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Student {
    private String dni;
    private String fullName;
    private String city;
    private String telephone;
    private final List<Grade> grades = new ArrayList<>();

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void addGrade(int code, int grade, String subjectAbr) {
        Grade gr = new Grade(dni, code, grade);
        gr.setSubjectAbr(subjectAbr);
        grades.add(gr);
    }

    /**
     * Returns the grades of the student as an unmodifiableList.
     * This method should be used as readonly
     * @return an unmodifiableList of the grades
     */
    public List<Grade> getGrades() {
        return Collections.unmodifiableList(grades);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return dni.equals(student.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Student{" +
                "dni='" + dni + '\'' +
                ", fullName='" + fullName + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
