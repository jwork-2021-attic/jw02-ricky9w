package S191220119.task2.src;

public class Snake {

    public static Snake theSnake;

    private Sorter sorter;

    public static Snake getTheSnake() {
        if (theSnake == null) {
            theSnake = new Snake();
        }
        return theSnake;
    }

    private Snake() {

    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public String sortYoukai(Matrix matrix) {
        String log = new String();

        if (sorter == null) {
            return null;
        }

        Element[] elements = matrix.toArray();
        int[] ranks = new int[elements.length];

        for (int i = 0; i < elements.length; i++) {
            ranks[i] = elements[i].getValue();
        }

        sorter.load(ranks);
        sorter.sort();

        String[] sortSteps = this.parsePlan(sorter.getPlan());

        for (String step : sortSteps) {
            this.execute(step);
            System.out.println(matrix.toString());
            log += matrix.toString();
            log += "\n[frame]\n";
        }

        return log;

    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(String step) {
        String[] couple = step.split("<->");
        Youkai.getYoukaiByRank(Integer.parseInt(couple[0]))
            .swapPosition(Youkai.getYoukaiByRank(Integer.parseInt(couple[1])));

    }
}
