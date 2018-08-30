public class Cat {
    private String name;
    private String color;
    private int age;

    Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @BeforeSuite
    public void doBefore() {
        System.out.println("Doing before suite!");
    }

    @Test
    public void test1() {
        System.out.println("Test1 passed!");
    }

    @Test(priority = 10)
    public void test2() {
        System.out.println("Test2 passed!");
    }

    @Test(priority = 8)
    public void test3() {
        System.out.println("Test3 passed!");
    }

    @Test(priority = 4)
    public void test4() {
        System.out.println("Test4 passed!");
    }

    @Test(priority = 1)
    public void test5() {
        System.out.println("Test5 passed!");
    }

    @AfterSuite
    public void doAfter() {
        System.out.println("Doing after suite");
    }
}
