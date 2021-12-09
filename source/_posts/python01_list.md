---
date: 2021-12-06
title: 파이썬 List
tags: python, list
categories: 
 - Python Basic
toc: true
---
### **리스트**
---

리스트는 대괄호[] 안에 문자나 숫자를 저장할수 있는 자료형이다.

아래처럼 리스트는 다양한 형태 이다

```python
a = []
b = [1, 2, 3]
c = ['Life', 'is', 'too', 'short']
d = [1, 2, 'Life', 'is']
e = [1, 2, ['Life', 'is']]
```

### **리스트 인덱싱**
리스트는 자바의 배열처럼 인덱스를 가지고 있다.
위에 만들어놓은 e를 아래에서 활용해 보았다.
```python
e[0] 
>> 1
e[1]
>> 2
e[0]+e[1]
>> 3
```

e[-1]에서 -1은 마지막 요소값을 나타낸다.
```python
e[-1]
>> ['Life', 'is']
```

위에 만들어놓은 e리스트 안에는 리스트 ['Life', 'is']리스트가 있다
이때 Life 값과 is 값을 아래와 같이 가져올 수 있다

```python
e[2][0]
>> 'Life'
e[-1][1]
>> 'is'
```

### **리스트의 슬라이싱**
```python
e[0:2]
>> [1, 2, ['Life', 'is']]
e[:2]
>> [1, 2, ['Life', 'is']]
e[1:]
>> [2, ['Life', 'is']]
e[2][:1]
>> ['Life', 'is']
```

### **리스트의 연산**
문자열과 숫자를 더하는것은 불가능하다.
```python
b + c
>> [1, 2, 3, 'Life', 'is', 'too', 'short']
b * 3
>> [1, 2, 3, 1, 2, 3, 1, 2, 3]
```

### **리스트의 길이**
```python
len(b)
>> 3
```

### **리스트 값 수정**
```python
b[1] = 5
b
>> [1, 5, 3]

del b[1:]
b
>> [1]
```

### **리스트 관련 메서드**
- append 요소 추가
```python
b.append(5)
>> [1,2,3,5]
```
- sort 정렬
```python
b.sort()
>> [1,2,3]
```

- reverse 뒤집기
```python
b.reverse()
b
>> [3, 2, 1]
```
- index
b 리스트 안에 2가 있으면 2의 인덱스 값을 반환
```python
b.index(2)
b
>> 1
```
- insert
(인덱스, 삽입할 값)
```python
b.insert(0,6)
b
>> [6, 1, 2, 3]
```
- remove 삭제
```python
b.remove(1)
b
>> [1, 3]
```
- pop 맨 마지막 값 반환하고 삭제

```python
b.pop 
>> 3
b 
>> [1, 2]
```
- count 리스트의 요소 개수
리스트 b안에 1의 갯수
```python
b.count(1)
>> 1
```
- extend 
```python
a = [1,2,3]
a.extend([4,5])
a
>> [1, 2, 3, 4, 5]
b = [6, 7]
a.extend(b)
a
>> [1, 2, 3, 4, 5, 6, 7]
```





### Ref
https://wikidocs.net/14