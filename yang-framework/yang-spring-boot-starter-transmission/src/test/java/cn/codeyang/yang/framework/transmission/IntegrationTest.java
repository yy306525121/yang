package cn.codeyang.yang.framework.transmission;

public class IntegrationTest {

    public void pause() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
