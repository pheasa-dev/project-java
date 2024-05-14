import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student implements Serializable {
    private static final int MIN_ID = 5000;
    private static final int MAX_ID = 6000;
    private int id;
    private String name;
    private Date dob;
    private String[] classes;
    private String[] subject;

    public Student(int randomId, String name, int year, int month, int day, String[] classes, String[] subjects) {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", classes=" + Arrays.toString(classes) +
                ", subject=" + Arrays.toString(subject) +
                '}';
    }
}
