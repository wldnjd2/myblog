---
date: 2021-12-06
title: 파이썬 집합
tags: python, set
categories:
  - 파이썬
  - Python 기초
toc: true
---

### **집합(Set)이란**
---
집합은 파이썬 2.3부터 지원하기 시작한 자료형으로, 집합에 관련된 것을 쉽게 처리하기 위해 만든 자료형이다.
- 특징
    - 집합은 중복을 허용하지 않는다
    - 순서가 없다 (인덱싱을 지원하지 않는다)
  
<br>

### **집합 형태**
- set이라는 키워드를 사용해 만들 수 있다

```python
my_set1 = set{[1,2,3]}
my_set1
>> {1,2,3}
my_set2 = {1,2,3,4,5}
my_set2
>> {1,2,3,4,5}
my_set3 = {1,2,3,4,4,4}  
my_set3
>> {1,2,3,4}    #중복을 허용하지 않아서 짤림
```

- 문자열을 입력하여 집합을 만들 수도 있다

```python
s1 = set("jeewon")
s1
>> {'e', 'j', 'n', 'o', 'w'}    #순서가 없다
``` 

<br>

### **집합을 인덱싱 하려면?**
집합은 순서가 없기 때문에 인덱싱으로 값을 얻는게 불가능하다
하지만 이때 튜플이나 리스트로 값을 변환하면 인덱싱이 가능하다
아래는 집합을 리스트와 튜플로 변환한것이다
```python
s_li = list(my_set)
s_li
>> [1, 2, 3]
s_tu = tuple(my_set)
s_tu
>> (1, 2, 3)
```
<br>

### **교집합, 합집합, 차집합**
아래는 자바와 파이썬을 사용할줄 아는 개발자를 집합으로 정의한것이다
```python
java = {"김땡땡", "박땡땡", "이땡떙"}
python = {"김땡땡", "강땡땡"}
```

<br>

- 교집합
java와 python을 모두 할 줄 아는 개발자
```python
print(java & python)
print(java.intersection(python))
>> {'김땡땡'}
>> {'김땡땡'}
```


<br>

- 합집합
java를 할수있거나 python을 할 수 있는 개발자
```python
print(java | python)
>> {'박땡땡', '김땡땡', '강땡땡', '이땡떙'}
print(java.union(python))
>> {'박땡땡', '김땡땡', '강땡땡', '이땡떙'}
```

<br>

- 차집합
java는 할 줄 알지만 python은 할 줄 모르는 개발자
```python
print(java - python)
>> {'이땡떙', '박땡땡'}
print(java.difference(python))
>> {'이땡떙', '박땡땡'}
```

<br>

### **집합 추가와 삭제**
add를 이용해서 추가할 수 있다
python을 할 줄 아는 사람이 늘어남

```python
python.add("윤땡땡")
print(python)
>> {'윤땡땡', '김땡땡', '강땡땡'}
```
<br>

update를 이용해서 여러개를 한번에 추가할 수도 있다
이때 대괄호[]로 묶어주지 않으면 {'박', '땡'}이 추가된다
```python
c = {"김땡땡"}
c.update(["박땡땡"])
c
>> {'김땡땡', '박땡땡'}
```

<br>

remove를 이용해서 삭제할 수 있다
자바를 까먹은 김땡땡.... 은 바로 나
```python
java.remove("김땡땡")
java
>> {'박땡땡', '이땡떙'}
```

<br>



### **Ref**
[나도코딩](https://www.youtube.com/watch?v=kWiCuklohdY)
[점프투파이썬](https://wikidocs.net/1015)

<br>
<br>
<br>
