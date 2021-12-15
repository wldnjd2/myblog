---
date: 2021-12-08
title: 파이썬 List comprehension
tags: python
  - 파이썬
  - Python 기초
toc: true
---
### **List comprehension**
---
리스트를 쉽게 생성하기 위한 방법으로 아래와 같은 형식을 갖는다

    [출력표현식 for 요소 in 입력Sequence [if 조건식]]


```python
oldlist = [1,2,'A',False,3]
newlist = [i*i for i in oldlist if type(i)==int]
print(newlist)
>> [1, 4, 9]
```

위의 예시를 해석해보자.
oldlist의 리스트 값이 조건문을 만족한다면, 만족한 리스트 값만 i에 순차 적으로 대입한다.
대입된 i 값으로, 연산식인 "i*i"를 실행하여 계산하고, 그에 대한 결과를 newlist인 리스트로 얻게 된다

<br>

```python
a = [1,2,3,4]
result = [num * 3 for num in a]
print(result)
>> [3, 6, 9, 12]

```

<br>


### **Ref**
---
[블로그](http://pythonstudy.xyz/python/article/22-Python-Comprehension)
[점프투파이썬](https://wikidocs.net/22)

<br>
<br>
<br>