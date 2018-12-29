import javafx.scene.layout.Priority;
import org.junit.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestNG  {

    @BeforeMethod

    public void beforeMethod() {
        System.out.println("open the browser");

    }
       @Test
        public void test1 () {
            System.out.println("test case1");

        }
        @Test
        public void test2 () {
            System.out.println("test case2");
        }

        @AfterMethod
    public void afterMethod(){
            System.out.println("closing browser");
        }
    }
