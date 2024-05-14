import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student implements Serializable {
    private int id;
    private String name;
    private String dob;
    private List<String> classes;
    private List<String> subjects;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\''+
                ", classes=" + classes +
                ", subjects=" + subjects +
                '}';
    }
}
