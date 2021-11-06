---
title: Python Numpy (3)
date: 2021-11-02
tags: markdown, python, pycharm, numpy
toc: true
categories: 
- study
- 파이썬
---
<br>

### **Numpy란**
---
행렬이나 일반적으로 대규모 다차원 배열을 쉽게 처리 할 수 있도록 지원하는 파이썬의 라이브러리이다.
<br>
Numpy 참고 사이트
https://numpy.org/doc/stable/reference/generated/numpy.reshape.html
<br>

### **List와 Numpy의 차이점**
연산에서의 차이점이 있다
**- List**
 A = [1,2,3]
 B = [4,5,6]
 A + B 일때 결과는 [1,2,3,4,5,6]
<br>

**- Numpy**
import numpy as np
 A = [1,2,3]
 B = [4,5,6]
np_A = np.array(A)
np_B = np.array(B)
np_A + np_B 의 결과는 array([5,7,9])
<br>

### **라이브러리 불러오기**
---
```python
import numpy as np
print (np.__version__)
```

    1.19.5
    
<br>


### **테스트**
---

```python
#배열 생성
temp = np.array([1, 2, 3])
print(type(temp))
```

    <class 'numpy.ndarray'>
    

<br>

### **Numpy 배열 생성 및 둘러보기**
---

```python
data1 = [1,2,3]     #python list를 이용함
data1
```




    [1, 1, 2, 2, 3, 4]




```python
data2 = [1,1,2,2,3,4]
data2
```




    [1, 1, 2, 2, 3, 4]




```python
my_array1 = np.array(data1)   #numpy를 이용하여 array 정의
print(my_array1)
print(my_array1.shape)    #my_array1의 형태를 확인
```

    [1 2 3]
    (3,)
    


```python
my_array2 = np.array(data2)
print(my_array2)
print(my_array2.shape)
```

    [1 1 2 2 3 4]
    (6,)
    


```python
my_array3 = np.array([3,6,9,12])
print(my_array3)
print(my_array3.shape)
print(my_array3.dtype)    #my_array3의 데이터타입 확인
```

    [ 3  6  9 12]
    (4,)
    int64
    


```python
my_array4 = np.array([[2,4,6,],[8,10,12],[14,16,18],[20,22,24]])
print(my_array4)
print(my_array4.shape)
```

    [[ 2  4  6]
     [ 8 10 12]
     [14 16 18]
     [20 22 24]]
    (4, 3)
    


```python
my_array5 = np.array([[[1,2], [3,4]], [[5,6],[7,8]]])
print(my_array5)
my_array5.shape
```

    [[[1 2]
      [3 4]]
    
     [[5 6]
      [7 8]]]
    




    (2, 2, 2)


<br>

### **Numpy 기본 함수들**
---

**1. arange 메소드**
파라미터로 받은 리스트를 반환해주는 메소드
( )괄호 안의 값이 1개일때와 여러개일때의 의미가 조금씩 다르다.

```python
arrange_array = np.arange(5)      #0부터 4까지 정수값 반환
arrange_array
```




    array([0, 1, 2, 3, 4])




```python
arrange_array3 = np.arange(1,9)   #1부터 9까지 정수값 반환
arrange_array3
```




    array([1, 2, 3, 4, 5, 6, 7, 8])




```python
arrange_array2 = np.arange(1,9,3)   #1부터 8까지 3씩 띄어서 정수값 반환
arrange_array2
```




    array([1, 4, 7])


<br>

**2. zeroes, ones 메소드**
- zeros() 메소드
0으로 초기화된  배열 객체를 반환하는 메소드
- ones() 메소드
함수는 1로 초기화된 배열 객체를 반환하는 메소드

```python
zeros_array = np.zeros((3,2))
print(zeros_array)
print("Data Type is: ", zeros_array.dtype)      #실수형이라서 0뒤에 .이 붙음
print("Data Shape is: ", zeros_array.shape)
```

    [[0. 0.]
     [0. 0.]
     [0. 0.]]
    Data Type is:  float64
    Data Shape is:  (3, 2)
    


```python
ones_array = np.ones((3,4), dtype='int32')
print(ones_array)
print("Data Type is: ", ones_array.dtype)
print("Data Shape is: ", ones_array.shape)
```

    [[1 1 1 1]
     [1 1 1 1]
     [1 1 1 1]]
    Data Type is:  int32
    Data Shape is:  (3, 4)
    
<br>

**3. reshape**
배열을 재구조화 및 변경하고자 할때 사용하는 메소드

```python
after_reshape = ones_array.reshape(6,2)
print(after_reshape)
print("Data Shape is: ", after_reshape.shape)
```

    [[1 1]
     [1 1]
     [1 1]
     [1 1]
     [1 1]
     [1 1]]
    Data Shape is:  (6, 2)
    


```python
after_reshape = ones_array.reshape(5,3)     #크기가 15랑 12랑 안맞아서 Error
after_reshape
```


    ---------------------------------------------------------------------------

    ValueError                                Traceback (most recent call last)

    <ipython-input-31-4f21dee813f3> in <module>()
    ----> 1 after_reshape = ones_array.reshape(5,3)
          2 after_reshape
    

    ValueError: cannot reshape array of size 12 into shape (5,3)



```python
#3차원 배열도 가능
# 3 x 4 = 12 --> 2 x 3 x 2 =12
after_reshape = ones_array.reshape(2,3,2)
print(after_reshape)
print("Data Shape is: ", after_reshape.shape)
```

    [[[1 1]
      [1 1]
      [1 1]]
    
     [[1 1]
      [1 1]
      [1 1]]]
    Data Shape is:  (2, 3, 2)
    


```python
after_reshape2 = ones_array.reshape(-1,6)
print("reshape(-1,6)?", after_reshape2.shape)
print(after_reshape2)
```

    reshape(-1,6)? (2, 6)
    [[1 1 1 1 1 1]
     [1 1 1 1 1 1]]
    


```python
after_reshape3 = ones_array.reshape(3,-1)
print("reshape(3,-1)?",after_reshape3.shape)
print(after_reshape3)
print("Data Shape is: ",after_reshape3.shape)
```

    reshape(3,-1)?
    [[1 1 1 1]
     [1 1 1 1]
     [1 1 1 1]]
    Data Shape is:  (3, 4)
    
<br>

### **Numpy 인덱싱과 슬라이딩**
---

```python
my_array = np.arange(start=0, stop=4)
print(my_array)
```

    [0 1 2 3]
    


```python
print("my_array의 1번째 요소, 즉 위치값이 0인 것은: ", my_array[0])
```

    my_array의 1번째 요소, 즉 위치값이 0인 것은:  0
    


```python
my_array2 = np.arange(start=3,stop=30,step=3)
my_array2 = my_array2.reshape(3,3)
my_array2
```




    array([[ 3,  6,  9],
           [12, 15, 18],
           [21, 24, 27]])




```python
my_array2[0:2,0:2]
```




    array([[ 3,  6],
           [12, 15]])




```python
my_array2[1:3,:]
```




    array([[12, 15, 18],
           [21, 24, 27]])




```python
my_array2[:,:]
```




    array([[ 3,  6,  9],
           [12, 15, 18],
           [21, 24, 27]])


<br>

### **Numpy 정렬**
---

**1. sort()**


```python
height_arr = np.array([174,165,180,182,168])
sorted_height_arr = np.sort(height_arr)

print('정렬 전: ',height_arr)
print('키가 작은 순으로 정렬: ',sorted_height_arr)
```

    정렬 전:  [174 165 180 182 168]
    키 큰 순으로 정렬 후:  [165 168 174 180 182]
    


```python
#[::-1]
desc_sorted_height_arr = np.sort(height_arr)[::-1]
print('키가 큰 순으로 정렬: ' ,desc_sorted_height_arr)
```

    키가 큰 순으로 정렬:  [182 180 174 168 165]
    
<br>

**2. argsort()**
정렬된 배열의 인덱스를 반환

```python
fives = np.array([10,5,15,20])
fives_order = fives.argsort()
print(fives)
print(fives_order)
print(fives[fives_order])
```

    [10  5 15 20]
    [1 0 2 3]
    [ 5 10 15 20]
    

<br>
<br>
<br>


도움 될만한 사이트
https://doorbw.tistory.com/171