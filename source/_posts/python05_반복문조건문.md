---
date: 2021-12-07
title: 파이썬 조건문과 반복문 (if, while, for)
tags: python
  - 파이썬
  - Python 기초
toc: true
---
  

### **들여쓰기**
파이썬은 자바나 다른 프로그래밍 언어와는 다른게 들여쓰기를 꼭 해주어야 한다
들여쓰기를 무시할 경우 에러가 남

<br>

### **콜론(:)**
파이썬은 세미콜론(;)을 사용하는 자바와는 다르게
문장 끝에 항상 콜론(:)을 사용한다

<br>

### **if문**
- if - else 문
여태까지 배웠던 if문과 크게 다를게 없다

else if아닌 elif를 사용한다
```python
weather = input("오늘 날씨는 어때요?")                   
if weather == "비"or"눈":          
    print("우산을 챙기세요")       
elif weather == "미세먼지":     
    print("마스크를 챙기세요")      
else:                       
    print("준비물 필요 없어요")     
```
                            
- input
자바에서 Scanner라고 생각하면 된다
값을 입력할 수 있다.
![](/images/python05/input.PNG)

<br>

### **in을 사용한 조건문**
영어로 in이 ~안에라는 뜻을 가지고 있는데, 파이썬에서도 같은 의미로 사용되어진다
아래 코드에서 1이 [1, 2, 3, 4]안에 있으면 True 없으면, False로 반환한다 
not in 은 in의 반대이다

```python
1 in [1, 2, 3, 4]
>> True
```
- pass
조건문에서 아무 일도 하지 않게 설정 할 수 있다

```python
weather = input("오늘의 날씨는?")
if '비' in weather:
  print("우산을 준비하세요")
elif '눈' in weather:
  print("우산을 준비하세요")
elif '해' in weather:
  print("준비물이 없습니다")
else:
  pass
```

<br>

### **while**

java에서 while문을 사용하는 방법과 크게 다르지 않다  

```python
a = 0
while a < 10:
  a = a + 1```
  print(a)
  if a == 10:<br>
    pass
```
          
아래는 커피 주문 자판기를 구현한것이다
```python
coffee = 100

while True:
  order = input("주문할 커피 개수를 입력하세요. ")
  coffee = coffee - int(order)
  if coffee > 0:
    print("남은 커피는 %d잔입니다. " %coffee)
  elif coffee == 0:
    print("남은 커피는 %d잔입니다. " %coffee)
    break
  else:
    coffee = coffee + int(order)
    print("재고가 부족합니다. 다시 입력하세요.")  
```
      
 ```python
customer = "손님"
i = 5
while i >= 1:
    print("{0}, 커피가 준비되었습니다. {1}번 남았어요.".format(customer, i))
    i -= 1
    if i == 0:
        print("커피는 폐기처분 되었습니다")
``` 
    손님, 커피가 준비되었습니다. 5번 남았어요.
    손님, 커피가 준비되었습니다. 4번 남았어요.
    손님, 커피가 준비되었습니다. 3번 남았어요.
    손님, 커피가 준비되었습니다. 2번 남았어요.
    손님, 커피가 준비되었습니다. 1번 남았어요.
    커피는 폐기처분 되었습니다

### **for문의 기본구조**
> for 변수 in 리스트(또는 튜플, 문자열):
>  수행할 문장1
>  수행할 문장2

```python
test = ['a', 'b', 'c']
for i in test:
  print(i)
```
    a
    b
    c


```python
score = [100, 90, 60, 50, 40, 30]
number = 0
for i in score:
  number += 1
  if i < 60:
    print("{0}번 학생은 불합격입니다.".format(number))
  else:
    print("{0}번 학생은 합격입니다.".format(number))
```
        1번 학생은 합격입니다.
        2번 학생은 합격입니다.
        3번 학생은 합격입니다.
        4번 학생은 불합격입니다.
        5번 학생은 불합격입니다.
        6번 학생은 불합격입니다.
<br>

### ****   
<br>       

### ****   
<br>       
       
### **Ref**  
[나도코딩](https://www.youtube.com/watch?v=kWiCuklohdY)
[점프투파이썬](https://wikidocs.net/1015)

<br>
<br>
<br>
