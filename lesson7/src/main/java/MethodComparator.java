import java.lang.reflect.Method;
import java.util.Comparator;

public class MethodComparator implements Comparator<Method> {
    public int compare(Method a, Method b) {
        Integer priorityMethodA = a.getAnnotation(Test.class).priority();
        Integer priorityMethodB = b.getAnnotation(Test.class).priority();
        return priorityMethodA.compareTo(priorityMethodB);
    }
}
