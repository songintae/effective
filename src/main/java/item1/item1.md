# item1 생성자 대신 정적 팩터리 메서드를 고려하라


##장점

1. 이름을 가질 수 있다. <br>
-> 반환될 객체의 특성을 쉽게 묘사 할 수 있음 <br>

2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다. <br>
-> 불변 클래스는 인스턴스를 미리 만들어 놓거나 새로 생성한 인스턴스를 캐싱하여 재활용하는 식으로 불필요한 객체 생성을 피할 수 있다. <br>

3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다. <br>
-> 반환할 객체의 클래스를 자유롭게 선택할 수 있게 하는 '엄청난 유연성'을 제공. <br>

4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다. <br>
-> 반환 타입의 하위 타입이기만 하면 어떠 클래스의 객체를 반환화든 상관없다. <br>

5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.


##단점

1. 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다. <br>

2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다. <br>


## 정적 팩터리 메서드 이름 규약

1. from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드 <br>
ex) Date d = Date.from(instant); <br>

2. of : 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드 <br>
ex) Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING); <br>

3. valueOf : from과 of의 더 자세한 버전 <br>
ex) BingInteger prime = BigInteger.valueOf(Integer.MAX_VALUE); <br>

4. instace 혹은 getInstance : (매개 변수를 받는다면) 매개변수로 명시한 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지는 않는다.<br>
ex) StackWalker luke = StackWalker.getInstance(options); <br>

5. create 혹은 newInstance : instance혹은 getInstance와 같지만, 매번 새로운 인스턴스를 생성해 반환함을 보장한다. <br>
ex) Object newArray = Array.newInstance(classObject, arrayLen); <br>

6. getType : getInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다. "Type"은 팩터리 메서드가 반환할 객체의 타입이다. <br>
ex) FileStore fs = Files.getFileStore(path); <br>

7. newType : newInstance와 같으나, 생성할 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다. "Type"은 팩터리 메서드가 반환할 객체의 타입이다. <br>
ex) BufferedReader br = Files.newBufferedReader(path)<br>

8. type : getType과 newType의 간결한 버전 <br>
ex) List<Complaint> litany = Collections.list(legacyLitany); <br>


##핵심정리
정적 팩터리 메서드와 public 생성자는 각자의 쓰임새가 있으니 상대적인 장단점을 이해하고
사용하는 것이 좋다. 그렇다고 하더라도 정적 팩터리를 사용하는 게 유리한 경우가 더 많으므로
무자겅 public 생성자를 제공하던 습관이 있다면 고치자.
