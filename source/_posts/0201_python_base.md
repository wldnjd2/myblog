---
title: Python 기초문법
date: 2021-11-01 
tags: python, coding, study
categories:
  - 파이썬
  - Python 기초
---
<br>

### **Python**
---
실행환경 : 구글 colab
구글 colab은 브라우저에서 Python을 작성하고 실행 할 수 있다.

### **1. Print**
---
```python
print("Hello World!")

```
    Hello World!

### **2. 주석처리**
---
```python
# 한 줄 주석처리
"""여러줄
주석처리 입니다"""
```

### **3. 변수의 종류**
---
```python 
num_int = 1
print(type(num_int))
>>> <class 'int'>

num_float = 0.2
print(type(num_float))
>>> <class 'float'>

bool_true = True
print(type(bool_true))
>>> <class 'bool'>

none_x = None
print(type(none_x))
>>> <class 'NoneType'>

```

### **4. 사칙연산**
---
```python
a = 3
b = 2
print('a + b = ', a+b)
print('a - b = ', a-b)
print('a * b = ', a*b)
print('a / b = ', a/b)
print('a // b = ', a//b)
print('a % b = ', a%b)
print('a ** b = ', a**b)

```
    a + b =  5
    a - b =  1
    a * b =  6
    a / b =  1.5             #실수형
    a // b =  1              #정수형
    a % b =  1               #나머지
    a ** b =  9              #좌항을 우항으로 거듭제곱

### **5. 논리형 연산자**
---
```python
#AND 연산
print(True and True)
print(True and False)
print(False and True)
print(False and False)

>>  True
    False
    False
    False

#OR 연산
print(True or True)
print(True or False)
print(False or True)
print(False or False)
```

    True
    True
    True
    False

### **6. 비교 연산자**
---
```python
print(4>3)


```
    True
### **7. 형변환**
---
```python
#input ("숫자를 입력하세요")
data =input ("숫자를 입력하세요")
#print(type(data))  문자형으로 출력됨
data2 =int(data)


```
     숫자를 입력하세요100
    <class 'int'>
### **8. String Operators**
---
```python
str1 = "kim "
str2 = "jeewon "
print(str1 + str2)
name = str1 + str2
print (name * 3)

```
    kim jeewon
    kim jeewon kim jeewon kim jeewon
### **9. index**
---
```python
greeting = "Hello Kaggle"
print(greeting[:])   #모든 데이터 출력
print(greeting[6:])
print(greeting[:6])
print(greeting[3:8])
print(greeting[0:9:2])  #2만큼 건너뜀
 
```
    Hello Kaggle
    Kaggle
    Hello 
    lo Ka
    HloKg
### **10. 리스트**
---
```python
a = [] # 빈 리스트
a_func = list() #list()함수로도 빈 리스트를 만들 수 있다.
b = [1] # 숫자도 요소가 될 수 있다.
c = ['apple'] # 문자열도 요소가 될 수 있다
d = [1, 2, ['apple']] # 리스트 안에 리스트를 요소로 넣을 수 있다.

print(a)
print(a_func)
print(b)
print(c)
print(d)


```
    []
    []
    [1]
    ['apple']
    [1, 2, ['apple']]
---
```python
a =  [ ['apple', 'cherry', 'watermelon'], 5]
print(a)
print(a[0])
print(a[1])
print(a[0][0])
print(a[0][0][0])
print(a[0][1])
print(a[0][2])
print(a[0][2][3])


```
    [['apple', 'cherry', 'watermelon'], 5]
    ['apple', 'cherry', 'watermelon']
    5
    apple
    a
    cherry
    watermelon
    e
---
```python
a = [ [1, 2, 3], 5]
# index [[0], [1], [2]]
print(a[0]) 
print(a[1]) 
print(a[0][1]) 
print(a[0][2]) 
print(a[-1])
```
    [1, 2, 3]
    5
    2
    3
    5
---
```python
a = [1,2,3,4,5,6,7,8,9,10]

b = a[:4]  # 인덱스 0부터 3까지
c = a[1:4] # 인덱스 1부터 3까지
d = a[0:7:2] # 인덱스 0부터 6까지 인덱스 2씩 건너 띄우기
e = a[::-1] # 리스트 a의 역순
f = a[::2] # 리스트 전체구간에서 인덱스 2씩 건너띄우기

print("a[:4]", b)
print("a[1:4]", c)
print("a[0:7:2]", d)
print("a[::-1]", e)
print("a[::2]", f)
```
    a[:4] [1, 2, 3, 4]
    a[1:4] [2, 3, 4]
    a[0:7:2] [1, 3, 5, 7]
    a[::-1] [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
    a[::2] [1, 3, 5, 7, 9]
---
```python
a = ['alice', 'bob', 'cat']
b = ['apple', 'banana', 'cherry']
c = a+b

print(c)
```
    a = ['a','b','c']
    b = a*3
    c = a*0
    print("a * 3:", b)
    print("a * 0:", c)
### **11. 리스트값 수정하기**
---
```python
a = [0,1,2]
a[1] = "b"

print(a)

```
    [0, 'b', 2]
### **12. 리스트 값 추가하기**
```python
#append 하나씩 추가하기
a = [100, 200, 300]
a.append(400)         
print(a)

a.append([500,600])
print(a)

```
    [100, 200, 300, 400]
    [100, 200, 300, 400, [500, 600]]

```python
#extend 한번에 추가하기
a = [1,2,3]
a.extend([40,500])
print('a.extend([40,500]) result')
print(a)    


```
    a.extend([40,500]) result
    [1, 2, 3, 40, 500]
```python
#insert 
a = [0,1,2]

a.insert(1,100)
print(a)
```
    [0, 100, 1, 2]
```python
a = [0,1,2,3]
a[2:2] = [100,200]
print(a)

# 시작과 끝의 범위보다 큰 수를 덮어쓰는 예시
b = [0,1,2,3]
b[1:2] = [100,200,300,400] 
print(b)

# 시작과 끝의 범위가 작을때의 예시
c = [0,1,2,3]
c[1:3] = [100]
print(c)


```
    [0, 1, 100, 200, 2, 3]
    [0, 100, 200, 300, 400, 2, 3]
    [0, 100, 3]
### **13. 리스트 값 삭제하기**
---
```python
a =[1,2,1,2]

#리스트의 첫번째 1이 삭제
a.remove(1)
print(a)

#리스트의 두번째 1이 삭제
a.remove(1)
print(a)

```
    [2, 1, 2]
    [2, 2]
```python
a = [0,1,2,3,4,5,6,7,8,9]

# 1 삭제
del a[1]
print(a)

b = [0,1,2,3,4,5,6,7,8,9]
# 범위로 삭제
del b[1:3] #list는 항상 시작하는 index부터, 종료하는 n의 n-1까지의 범위를 잡아줍니다.
print(b)

```
    [0, 2, 3, 4, 5, 6, 7, 8, 9]
    [0, 3, 4, 5, 6, 7, 8, 9]
### **14. 튜플**
---
```python
tuple1 = (0) # 끝에 콤마(,)를 붙이지 않았을 때
tuple2 = (0,) # 끝에 콤마(,)를 붙여줬을 때
tuple3 = 0,1,2

print(tuple1)
print(tuple2)
print(tuple3)

print(type(tuple1)) # 콤마(,)를 붙여주지 않으면 튜플이 아닙니다.
print(type(tuple2)) # 콤마(,)를 붙여주어야 튜플 자료형 입니다.
print(type(tuple3)) # 여러개의 값 일경우 괄호를 없애주어도 튜플 자료형 입니다.
```
    0
    (0,)
    (0, 1, 2)
    <class 'int'>
    <class 'tuple'>
    <class 'tuple'>
### **15. 딕셔너리**
---
```python
dic = {'teacher':'alice', 'class': 5, 'studentid': '15', 'list':[1,2,3]}

print(dic['teacher'])
print(dic['class'])
print(dic['list'])

```
    alice
    5
    [1, 2, 3]
### **16. if 조건문**
---
```python
grade = int(input("점수를 입력하세요"))

if grade > 90:
  print("A")
elif grade > 80:
  print("B")
elif grade >70:
  print("C")
else:
  print("D")
```
    점수를 입력하세요100
    A
### **17. 반복문**
---
```python
for i in range(10):
  print("Hello World")

```
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World
    Hello World 
```python
a ="Kaggle"
for x in a:
  print(x)

  if x =='l':
    break

```
    K
    a
    g
    g
    l
<br>
<br>

파이썬 공부하는 사이트
https://dojang.io/course/view.php?id=7

메소드 찾아 볼때 사이트
https://docs.python.org/3/tutorial/datastructures.html