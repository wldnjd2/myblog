---
date: 2021-12-09
title: 파이썬 Class
tags: python
categories:
  - 파이썬
  - Python 기초
toc: true
---
  

TEST 입니다
### **클래스와 객체**
---
클래스는 붕어빵 틀에 비유를 할 수 있다. 
붕어빵 틀로 수백개의 붕어빵을 만들어 낼 수 있듯이 클래스로 많은 객체를 만들 수 있다.
스타크래프트 게임의 예로 들면 클래스를 이용해서 수백개의 유닛을 만들 수 있는것이다.

또 한가지 예를 들면 클래스는 설계 도면이고, 객체는 클래스(설계 도면)을 이용해 만든 어떠한 피조물이다. 따라서 클래스를 실체화 한것이다

클래스를 사용하는 이유
- 글로벌 변수를 없애고, 모든 변수를 어떠한 스코프에 소속시킨다
- 몇번이고 재사용 가능하다
- 코드의 수정을 최소화한다
- 함수 실행중에, 함수 자신을 다시 호출하는 처리 등이 가능하게 한다
<br>

### **스타크래프트 예시**
---
```python
class Unit:
    def __init__(self, name, hp, damage):
        self.name = name
        self.hp = hp
        self.damage = damage
        print("{0}유닛이 생성 되었습니다.".format(self.name))
        print("체력 {0}, 공격력 {1}".format(self.hp, self.damage))

marine1 = Unit("마린", 40, 5)
marine2 = Unit("마린", 40, 5)
tank = Unit("탱크", 150, 3)
```
위의 예시에서는 marine1, marine2, tank라는 객체를 생성한 것이다.
각각의 객체는 각자의 특징을 가졌다.
-> 매개 변수 값이 다름
marine1, marine2, tank 는 Unit 클래스의 인스턴스이다.
인스턴스와 객체의 차이는 인스턴스는 클래스와 객체의 관계를 위주로 설명할때 사용된다.
'Unit 클래스의 객체' 라는 표현보다는 'Unit 클래스의 인스턴스' 라는 표현을 쓴다.


<br>

### **__init__**
---

__init__란 자바에서 생성자 역할을 하는 메서드이다. 
- 객체가 만들어질때 자동으로 호출되어진다. <br>
(생성자란 객체가 생성될때 자동으로 호출되는 메서드를 의미한다)
- 인스턴스를 초기화 해준다고 생각

self
- self는 자기 자신을 의미하고, 즉 인스턴스를 가리킨다
- self는 자바에서 this와 같다.

<br>

### **메소드**
---
아래는 attak, damaged 라는 메소드를 만들었고
객체에서 메소드를 불러와 사용해보았다

```python
class AttackUnit:
    def __init__(self, name, hp, damage):
        self.name = name
        self.hp = hp
        self.damage = damage

    def attack(self, location):
        print("{0} : {1} 방향으로 적군을 공겨갑니다. [공격력 {2}]"\
              .format(self.name, location, self.damage))

    def damaged(self, damage):
        print("{0} : {1} 데미지를 입었습니다.".format(self.name, damage))
        self.hp -= damage
        print("{0} : 현재 체력은 {1} 입니다. ".format(self.name, self.hp))
        if self.hp <= 0:
            print("{0} : 파괴되었습니다. ".format(self.name))

firebat1 = AttackUnit("파이어뱃", 50, 16)
firebat1.attack("5시")

firebat1.damaged(25)
firebat1.damaged(25)
```
    파이어뱃 : 5시 방향으로 적군을 공겨갑니다. [공격력 16]
    파이어뱃 : 25 데미지를 입었습니다.
    파이어뱃 : 현재 체력은 25 입니다. 
    파이어뱃 : 25 데미지를 입었습니다.
    파이어뱃 : 현재 체력은 0 입니다. 
    파이어뱃 : 파괴되었습니다. 

<br>


### **상속**
---
클래스 AttackUnit은 Unit을 상속 받았다.

```python
class Unit:
    def __init__(self, name, hp):
        self.name = name
        self.hp = hp
        
class AttackUnit(Unit):   #상속 
    def __init__(self, name, hp, damage):
        #self.name = name
        #self.hp = hp
        Unit.__init__(self, name, hp)
        self.damage = damage

```

<br>


### **다중상속**
---

부모가 둘이여서 자식이 여러곳에서 상속을 받는다고 생각하면 된다.
아래 예시에서 FlyableAttackUnit은 AttackUnit과 Flyable을 상속받는다.
```python
class Unit:
    def __init__(self, name, hp):
        self.name = name
        self.hp = hp
        
class AttackUnit(Unit):   #상속 
    def __init__(self, name, hp, damage):
        #self.name = name
        #self.hp = hp
        Unit.__init__(self, name, hp)
        self.damage = damage
        
    def attack(self, location):
        print("{0} : {1} 방향으로 적군을 공격합니다. [공격력 {2}]"\
              .format(self.name, location, self.damage))

    def damaged(self, damage):
        print("{0} : {1} 데미지를 입었습니다.".format(self.name, damage))
        self.hp -= damage
        print("{0} : 현재 체력은 {1} 입니다. ".format(self.name, self.hp))
        if self.hp <= 0:
            print("{0} : 파괴되었습니다. ".format(self.name))

class Flyable:
    def __init__(self, flying_speed):
        self.flying_speed = flying_speed
        
    def fly(self, name, location):
        print("{0} : {1} 방향으로 날아갑니다. [속도 {2}]" \
              .format(name, location, self.flying_speed))

class FlyableAttackUnit(AttackUnit, Flyable):
    def __init__(self, name, hp, damage, flying_speed):
        AttackUnit.__init__(self, name, hp, damage)
        Flyable.__init__(self, flying_speed)
        
            
valkyrie = FlyableAttackUnit("발키리",200, 6, 5)
valkyrie.fly(valkyrie.name, "3시")
```
<br>

### **Super**
---
원래는 상속 받을때 아래와 같이 적었지만,
    
    Unit.__init__(self, name, hp)

super를 이용해서 더 간단하게 사용할 수 있다
이때 self를 제거하고 사용할 수 있다
    
    super().__init__(name, hp, 0)

```python
class Unit:
    def __init__(self, name, hp):
        self.name = name
        self.hp = hp
        
class AttackUnit(Unit):   #상속 
    def __init__(self, name, hp, damage):
        #Unit.__init__(self, name, hp)
        super().__init__(name, hp, 0)
        self.damage = damage
```
<br>

하지만 다중 상속에서는 문제가 발생한다.
                          
아래 예제에서는 FlyableUnit이 Unit과 Flyable을 다중 상속받는다
이때 아래와 같이 super()를 통해 상속을 해주게 되면
"class FlyableUnit(**Flyable**, Unit):"   Flyable을 먼저 선언해줬기 때문에   
Flyable 생성자라고 실행 결과가 나오게 된다 (Unit 생성자가 호출이 안됨)

따라서   Unit.__init__(self), Flyable.__init__(self) 로 상속을 해줄 수 있다.


```python
class Unit:
    def __init__(self):
        print("Unit 생성자")
    
class Flyable:
    def __init__(self):
        print("Flyable 생성자")
        
class FlyableUnit(Flyable, Unit):
    def __init__(self):
        #super().__init__()
        Unit.__init__(self)
        Flyable.__init__(self)
        
dropship = FlyableUnit()
```


### **Ref**  
[나도코딩](https://www.youtube.com/watch?v=kWiCuklohdY)
[점프투파이썬](https://wikidocs.net/1015)
(https://engineer-mole.tistory.com/190)
<br>
<br>
<br>
