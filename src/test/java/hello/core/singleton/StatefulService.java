package hello.core.singleton;

public class StatefulService {
    /**
     * 스프링 빈은 항상 무상태(stateless)로 설계하자.
     * 공유 필드는 정말 조심해야한다. 정말 해결하기 어려운 큰 문제들이 터지기 때문이다.
     * */

    // private int price; // 상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
       // this.price = price; // 여기가 문제!
        return price;
    }
}
