package RestApi;

public class BaseRestAssured {

    int c = (int) (Math.random()*10000);
    String email1="umut"+c+"@gmail.com";
    String name="test";

    String gender="male";
    String status="active";

    String accessToken = "1db9c9b6c959682be7c96f74ca532c3cb0bd331f46b86a92602f8d319481b6f5";

    /**   4 Digits */
    int countDigit(int n) {
        int c = 0;
        while (n != 0) {
            c++;
            n = n / 10;
        }
        return c;
    }
}
