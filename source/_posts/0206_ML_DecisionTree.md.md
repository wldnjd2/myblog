---
title: 머신러닝 Decision Tree
date: 2021-11-04
toc : true
tags: machine learning, decision tree
categories: 
- machine learning
---
<br>


### **Decision Tree란**
---
- 머신러닝에 사용되는 예측 모델링 접근 방식 중 하나이다.
여러 입력 변수를 기반으로 대상 변수의 값을 예측하는 모델을 만드는 것이다.
분류와 회귀 모두 가능하며, 스무고개 하듯이 Y/N으로 질문을 이어가며 학습한다.

- Deicision Tree는 데이터에서 if-else 문을 이용하여 sine 곡선에 가까운 데이터를 학습한다.
트리가 깊어질수록 모델이 더 복잡해진다.

<br>

### **알고리즘 이해하기**
---
1. Rood node (뿌리 마디) 
처음에 root node에서 문제의 질문이 입력되면 Y/N로 데이터가 분류된다.
2. Intermediate node (중간 마디) 
Y로 분류된 데이터는 다시 질문이 입력되어 Y/N으로 데이터가 분류된다.
3. Terminal node (끝 마디)
끝마디에서는 데이터가 가장 섞이지 않은 상태로 완전히 분류되어 Entropy가 낮아진다


- 초기 지점은 root node이고 분기가 거듭될 수록 데이터의 개수는 줄어든다
- terminal node에 속하는 데이터의 개수를 합하면 root node의 데이터 수와 일치한다

<br>

### **Impurity(불순도)란** 
---
해당 범주 안에서 서로 다른 데이터가 얼마나 섞여 있는지 뜻한다
<br>

### **Entropy란**
---
불순도를 수치적으로 나타낸 척도이다
 
Entropy가 높으면 불순도가 높고
Entropy가 낮으면 불순도가 낮다

예를들면 Entropy가 높으면 정리되지 않은 방, 낮으면 정리된 방 이라고 생각하면 된다.

Decision Tree는 불순도를 최소화 하는 방향으로 학습을 하게 된다.

<br>

### **전체 흐름**
---
1. Define Problem, Collect training data

2. Build a Decision Tree (Extract Data, Build a tree)

3. Deploy machine

4. Test with test data

<br>

### **장점**
---
- 데이터의 전처리를 하지 않아도 된다.
- 수치형과 범주형 변수를 한번에 다룰 수 있다.

<br>

### **한계**
---
- 샘플 사이즈가 크면 효율성 및 가독성이 떨어진다.
- 과적합으로 알고리즘 성능이 떨어질 수 있다.
- 한번에 하나의변수만을 고려하므로 변수간 상호작용을 파악하기 어렵다.
- 약간의 차이에 따라 트리의 모양이 많이 달라질 수 있다.


<br>

### **예제**
- iris data set을 이용한 deicision tree 만들기
Scikitlearn 사이트의 iris 데이터셋을 이용한 예제이다.
코드 출처: https://scikit-learn.org/stable/auto_examples/tree/plot_iris_dtc.html
---
**데이터 불러오기, 그래프그리기 위한 설정**
- plot_colors = 'ryb' 
blue red yello 색을 나타내기 위해 사용
- plot_step
축의 단위를 설정

```python
import numpy as np
import matplotlib.pyplot as plt

from sklearn.datasets import load_iris
from sklearn.tree import DecisionTreeClassifier, plot_tree

# Parameters
n_classes = 3
plot_colors = "ryb"
plot_step = 0.02

# Load data
iris = load_iris()
```
<br>

- enumerate는 입력값으로 시퀀스 자료형(리스트, 튜플, 문자열)을 입력받아,
enumerate 객체를 리턴한다.
- enumerate 객체는 첫번째로 그 순서값, 두번째로 그 순서값에 해당되는 시퀀스 자료형의 실제값을 갖는 객체이다
- X = iris.data[:, pair] 
하나의 pair에 들어가는 값이 [0,2]라면, 첫번째 세번째만 선택해서 X에 할당
```python
for pairidx, pair in enumerate([[0, 1], [0, 2], [0, 3], [1, 2], [1, 3], [2, 3]]):
    # We only take the two corresponding features
    X = iris.data[:, pair]
    y = iris.target
```
<br>

```python
    # Train
    clf = DecisionTreeClassifier().fit(X, y)

    # Plot the decision boundary
    plt.subplot(2, 3, pairidx + 1)

    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    xx, yy = np.meshgrid(
        np.arange(x_min, x_max, plot_step), np.arange(y_min, y_max, plot_step)
    )
    plt.tight_layout(h_pad=0.5, w_pad=0.5, pad=2.5)

    Z = clf.predict(np.c_[xx.ravel(), yy.ravel()])
    Z = Z.reshape(xx.shape)
    cs = plt.contourf(xx, yy, Z, cmap=plt.cm.RdYlBu)
```
<br>


```python
    plt.xlabel(iris.feature_names[pair[0]])
    plt.ylabel(iris.feature_names[pair[1]])

    # Plot the training points
    for i, color in zip(range(n_classes), plot_colors):
        idx = np.where(y == i)
        plt.scatter(
            X[idx, 0],
            X[idx, 1],
            c=color,
            label=iris.target_names[i],
            cmap=plt.cm.RdYlBu,
            edgecolor="black",
            s=15,
        )

plt.suptitle("Decision surface of a decision tree using paired features")
plt.legend(loc="lower right", borderpad=0, handletextpad=0)
plt.axis("tight")

plt.figure()
clf = DecisionTreeClassifier().fit(iris.data, iris.target)
plot_tree(clf, filled=True)
plt.show()
```
    
![](/images/iris1.png)
![](/images/iris2.png)

<br>
<br>
<br>
<br>

참고 사이트: 
---
[위키백과](https://ko.wikipedia.org/wiki/%EA%B2%B0%EC%A0%95_%ED%8A%B8%EB%A6%AC)
[Scikitlearn](https://scikit-learn.org/stable/modules/tree.html#regression)
[블로그1](https://velog.io/@changhtun1/Python-Decision-Tree-%EC%9D%B4%EB%A1%A0-%EB%B0%8F-%EC%8B%A4%EC%8A%B5)
[블로그2](https://bpas.tistory.com/21)
[유튜브](https://www.youtube.com/watch?v=n0p0120Gxqk)