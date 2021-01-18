package cf.scenecho.algorithm.encode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@EqualsAndHashCode(of = "run")
@ToString(of = {"run", "code"})
public class Run implements Comparable<Run> {

    private final String run;
    private int frequency;

    private Run left;
    private Run right;

    @Setter
    private String code;


    public Run(String run) {
        this.run = run;
        this.frequency = 1;
    }

    public Run(Run left, Run right) {
        if (left == null || right == null) throw new IllegalStateException();

        this.run = null;
        this.left = left;
        this.right = right;
        this.frequency = left.frequency + right.frequency;
    }


    public void increaseFrequency() {
        frequency++;
    }

    @Override
    public int compareTo(Run run) {
        return Integer.compare(frequency, run.frequency);
    }

}
