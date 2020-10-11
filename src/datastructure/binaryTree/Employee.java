package datastructure.binaryTree;

import java.util.List;

public class Employee {
    private int happy ;
    private List<Employee> nexts ;

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public List<Employee> getNexts() {
        return nexts;
    }

    public void setNexts(List<Employee> nexts) {
        this.nexts = nexts;
    }
}
