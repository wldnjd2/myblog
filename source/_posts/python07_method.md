---
date: 2021-12-08
title: 파이썬 Method
tags: python
categories:
  - 파이썬
  - Python 기초
toc: true
---
### **파이썬 함수**
---
파이썬에서는 def로 함수를 만들 수 있다.
그 다음 함수이름을 적고 콜론(:)으로 마무리 해준다.
함수를 실행하려면 '함수이름()' 방법으로 실행할 수 있다.


    def 함수명(매개변수):
        <수행할 문장1>
        <수행할 문장2>
        ...

<br>

```python
def multiply(a, b):
  return a*b

a = 3
b = 4
c = multiply(a,b)
print(c)
>> 12
```

<br>

```python
def deposit(balance, money):
  print("입금이 완료되었습니다. 잔액은 {0}원 입니다.".format(balance + money))
  return balance + money

def withdraw(balance, money):
  if balance >= money:
    print("출금이 완료되었습니다. 잔액은 {0}원 입니다. ".format(balance - money))
    return balance - money
  else:
    print("출금이 완료되지 않았습니다. 잔액은 {0}원 입니다.".format(balance))
    return balance

def withdraw_night(balance, money):
  commission = 100 
  return commission, balance - money - commission  #여러개의 값도 한번에 반환 가능    

balance = 0
balance = deposit(balance, 1000)
balance = withdraw(balance, 2000)
commission, balance = withdraw_night(balance, 500)

print("수수료 {0}원이며, 잔액은 {1}원 입니다.".format(commission, balance))
```
    입금이 완료되었습니다. 잔액은 1000원 입니다.
    출금이 완료되지 않았습니다. 잔액은 1000원 입니다.
    수수료 100원이며, 잔액은 400원 입니다.

### **가변인자**
---
```python
def profile(name, age, *language):
    print("이름: {0}\t나이: {1}\t".format(name, age),end=" ")
    for lang in language:
        print(lang, end=" ")
    print()
    
profile("유재석", 20, "Python", "Java", "C", "C++")
profile("김태호", 25, "Kotlin", "Swift")
```

    이름: 유재석	나이: 20	 Python Java C C++ 
    이름: 김태호	나이: 25	 Kotlin Swift 

<br>



### **Ref**  
[나도코딩](https://www.youtube.com/watch?v=kWiCuklohdY)
[점프투파이썬](https://wikidocs.net/1015)

<br>
<br>
<br>
