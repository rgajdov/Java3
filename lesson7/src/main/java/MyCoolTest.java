import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TreeSet;

class MyCoolTest {

    static void start(Class cl, Object name) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        int countBeforeSuite = 0;
        int countAfterSuite = 0;

        Class.forName(cl.getName());

        MethodComparator methodComp = new MethodComparator();
        TreeSet<Method> mTreeSet = new TreeSet<>(methodComp);

        Method[] method = cl.getDeclaredMethods();

        for (Method md : method) {
            if (md.isAnnotationPresent(BeforeSuite.class)) {
                countBeforeSuite++;
                if (countBeforeSuite > 1)
                    throw new RuntimeException("BeforeSuite repeated!");
                md.invoke(name);
            }
        }

        for (Method md : method) {
            if (md.isAnnotationPresent(Test.class)) {
                mTreeSet.add(md);
            }
        }

        for (Method m : mTreeSet) {
            if (m.isAnnotationPresent(Test.class)) {
                m.invoke(name);
            }
        }

        for (Method md : method) {
            if (md.isAnnotationPresent(AfterSuite.class)) {
                countAfterSuite++;
                if (countAfterSuite > 1)
                    throw new RuntimeException("AfterSuite repeated!");
                md.invoke(name);
            }
        }
    }
}
