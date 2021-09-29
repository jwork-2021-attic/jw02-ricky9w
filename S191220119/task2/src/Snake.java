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

        // TODO: 完成排序逻辑
        return log;
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(String step) {
        // TODO: 完成执行逻辑
        String[] couple = step.split("<->");

    }
}
