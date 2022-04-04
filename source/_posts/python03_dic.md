---
date: 2021-12-06
title: 파이썬 딕셔너리
tags: python, dictionary
categories:
  - 파이썬
  - Python 기초
toc: true
widgets: null
---
### **Dictionary란**
---
사전이다 
우리가 평상시에 사용하는 사전에서 단어를 찾으면, 단어가 나오고 그에대한 정의가 나온다.
이처럼 파이썬의 딕셔너리도 key와 value 형태이다.
 
ex) 100번 사물함 -> 100번 key가 사용 <br>
                   200번 key 사용 불가능 <br>
=> 키에대한 중복이 허용되지 않는다

<br>

### **딕셔너리의 형태**
key와 value가 {}로 구성되어있다.

```python
{{Key1:Value1, Key2:Value2, Key3:Value3, ...}}
```
 

- value에 리스트도 넣을수 있다.
- key값으로 정수값이나, 문자열도 가능하다.

아래는 예시이다

```python
cabinet = {9:"김땡땡", 7:"박모모"}
a = {'a': [1, 2, 3]}
```

<br>

### **딕셔너리 사용해보기**
대괄호[]나, get을 통해 value를 불러올 수 있다.

```python
print(cabinet[9])
>> 김땡땡
print(cabinet.get(7))
>> 박모모
```

키의 value 값이 존재하는지 확인할 수 있다.
이때 반환값은 True와 False로 구분되어진다.

```python
print(9 in cabinet)
>> True
print(7 in cabinet)
>> False
```

<br>

### **딕셔너리 추가, 변경, 삭제**

    
아래는 딕셔너리 추가 예시이다.
```python
print(cabinet)
>> {9:"김땡땡", 7:"박모모"}
cabinet[15] = "이땡땡" #새로운 값 추가
print(cabinet)
>> {9:"김땡땡", 7:"박모모", 15:"이땡땡"}
```
<br>

아래는 딕셔너리 변경 예시이다. 추가와 같은 형태이다.
위의 예제에서 key 15의 value로 이땡땡으로 새로 추가해주었지만,
나땡땡으로 value 값을 바꾼것을 확인할 수 있다.

```python
cabinet[15]="나땡땡"
print(cabinet)
>> {9:"김땡땡", 7:"박모모", 15:"나땡땡"}
```

<br>

아래는 딕셔너리 삭제 예시이다.
딕셔너리 앞에 del을 붙여 삭제할 수 있다.
```python
del cabinet[15] 
>> {9:"김땡땡", 7:"박모모"}
```

<br>

clear() 함수를 이용해서 딕셔너리 전체 삭제를 할 수 있다.
```python
cabinet.clear()
>> 
```

<br>

### **key 리스트 만들기**
key()를 사용해 key만 모아서 dict_keys 객체를 돌려준다
```python
a = {'name': 'jw', 'phone': '010-1234-5678', 'birth': '1014'}
a.keys()
>> dict_keys(['name', 'phone', 'birth'])
```
key값을 리스트로 반환할수도 있다
```python
list(a.keys())
>> ['name', 'phone', 'birth']
```
<br>

### **value 리스트 만들기**
values()를 이용해서 value 값만 모아서 리스트로 반환할 수 있다
```python
a.values()
>> dict_values(['jw', '010-1234-567', '1014'])
```

<br>


### **Ref**
[나도코딩](https://www.youtube.com/watch?v=kWiCuklohdY)
[점프투파이썬](https://wikidocs.net/16)

<br>
<br>
<br>