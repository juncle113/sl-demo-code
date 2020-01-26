package sl.demo.code.base.reflect;

public class Robot {

    private String name;

    private void sayHi(String helloSentence) {
        System.out.println(helloSentence + " " + name);
    }

    public String throwHello(String tag) {
        return "Hello " + tag;
    }
}
