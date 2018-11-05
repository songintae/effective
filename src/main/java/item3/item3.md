# private 생성자나 열거 타입으로 싱글턴임을 보증하라
-> 싱글턴이란 인스턴스를 오직 하나만 생성할 수 있는 클래스를 말한다. <br>
-> 클래스를 싱글턴으로 만들면 이를 사용하는 클라이언트를 테스트하기 어려워질 수 있다.<br>
-> 싱글턴을 만들수 있는 방법은 세가지 방법이 있다.

## pulbic static 맴버가 final 필드인 방식
```java
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() {}
    
    public void leaveTheBuilding() {}
}
```
-> private 생성자는 public static final 필드인 Elvis.Instance를 초기화 할 때 딱 한번만
호출된다. 인스턴스가 전체 시스템에서 하나 뿐임이 보장된다. <br>
-> 하지만, 권한이 있는 클라이언트는 리플렉션 API인 AccesibleObject.setAccessible을 사용해서
private 생성자를 호출할 수 있다.

### 장점
1. 해당 클래스가 싱글턴임이 API에 명백히 들어난다. <br>
2. 간결하다.

## 정적 팩터리 메서드를 public static 멤버로 제공한다.
```java
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() {}
    public static Elvis getInstance() {return INSTANCE;}
    
    public void leaveTheBuilding() {};
}
```
-> 마찬가지로 인스턴스가 전체 시스템에서 하나 뿐임이 보장된다. <br>
-> 마찬가지로 클라이언트 리플렉션 API에 의해 private 생성자를 호출할 수 있다.

### 장점
1. (마음만 바뀌면)API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다는 점. <br>
2. 원한다면 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다. <br>
3. 정적 팩터리의 매서드 참조를 공급자로 사용할 수 있다. <br>
ex) Supplier<Elvis>


## 특징
위의 두가지 방식으로 만든 싱글턴 클래스를 직렬화 하려면 단순히 Serializable을 구현한다고 선언하는
것만으로는 부족하다. 모든 인스턴스 필드를 transient라고 선언하고 readResolve 메서드를 제공해야 한다.<br>
<br>
그렇지 않으면 역직렬화할 때마다 새로운 인스턴스가 민들어진다.
```java
private Object readResolve() {
    // '진짜' 객체를 반환하고, 가짜 객체는 가비지 컬렉터에 맡긴다.
    return INSTANCE;
}
```

## 원소가 하나인 열거타입 선언
-> public 필드 방식과 비슷하지만, 더 간결하고, 추가 노력 없이 직렬화할 수 있고, 심지어는 아주 복잡한
직렬화 상황이나 리플렉션 공격에도 제2의 인스턴스가 생기는 일을 완벽히 막아준다.
```java
public enum Elvis {
    INSTANCE;
    
    public void leaveTheBuilding() {}
}
```

조금 부자연스러워 보일 수는 있으나 대부분 상황에서는 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법이다.