---
title: Python 데이터 시각화 (5)
date: 2021-11-03
tags: markdown, python, pycharm, visualization
toc: true
categories: 
- study
- 파이썬
---
<br>
<br>


### **Matplotlib.pyplot란**
---
Matplotlib는 데이터를 시각화 하는데 사용하는 대표적인 파이썬 라이브러리이다.
MATLAB과 비슷한 형태를 가지고 있고, numpy나 pandas에서 사용되는 자료구조를 쉽게 시각화 할 수 있다.
<br>

### **시작하기**
---
- import matplotlib.pyplot as plt
라이브러리 사용하기 위한 import문 추가 (plt라는 이름으로 사용)

- fig, ax = plt.subplots()
fig는 그림, ax는 그려질 그래프를 의미
괄호 안에는 그래프 크기를 정의 할 수 있음

- ax.plot(dates, min_temperature, label = "Min Temp")     
- ax.plot(dates, max_temperature, label = "Max Temp")
두개의 그래프를 의미

- ax.legend() 
범례를 추가할때 사용하는 메소드
그래프에 데이터 위치 표시 (예제에서는 왼쪽 맨위에 표시)

- plt.show()  
마무리 

```python
import matplotlib.pyplot as plt

dates = [
    '2021\n01-01', '2021\n01-02', '2021\n01-03', '2021\n01-04', '2021\n01-05',
    '2021\n01-06', '2021\n01-07', '2021\n01-08', '2021\n01-09', '2021\n01-10'
]
min_temperature = [20.7, 17.9, 18.8, 14.6, 15.8, 15.8, 15.8, 17.4, 21.8, 20.0]
max_temperature = [34.7, 28.9, 31.8, 25.6, 28.8, 21.8, 22.8, 28.4, 30.8, 32.0]

fig, ax = plt.subplots()  #그래프 생성  ()안에 사이즈 설정 가능
ax.plot(dates, min_temperature, label = "Min Temp")     
ax.plot(dates, max_temperature, label = "Max Temp")
ax.legend()   
plt.show()    #마무리! 꼭 해주기!
```


    
![](/images/output_1_0.png)
    



```python
import matplotlib.pyplot as plt

dates = [
    '2021\n01-01', '2021\n01-02', '2021\n01-03', '2021\n01-04', '2021\n01-05',
    '2021\n01-06', '2021\n01-07', '2021\n01-08', '2021\n01-09', '2021\n01-10'
]
min_temperature = [20.7, 17.9, 18.8, 14.6, 15.8, 15.8, 15.8, 17.4, 21.8, 20.0]
max_temperature = [34.7, 28.9, 31.8, 25.6, 28.8, 21.8, 22.8, 28.4, 30.8, 32.0]

fig, axes = plt.subplots(nrows=1, ncols=1, figsize=(10,6)) #그래프 사이즈
axes.plot(dates, min_temperature, label = 'Min Temperature')
axes.plot(dates, max_temperature, label = 'Max Temperature')
axes.legend()
plt.show()
```


    
![](/images/output_2_0.png)
    



```python
print(fig)
print(axes)
```

    Figure(720x432)
    AxesSubplot(0.125,0.125;0.775x0.755)
    

<br>

### **선 그래프**
---
**방법 1. Pyplot API**(비추천)
- 참조: https://pypi.org/project/fix-yahoo-finance/

- yfinance란 
오픈소스 API로, Yahoo Finance에서 제공하는 데이터에 접근 할 수 있다.
아래 예제에서는 주가 데이터를 받아 오기 위해서 사용하였다.

- yfinance 함수를 사용하기 위한 패키지 다운로드

```python
!pip install yfinance --upgrade --no-cache-dir
```

```python
#주가 정보 가져오기
import yfinance as yf
data = yf.download('AAPL', '2019-08-01', '2020-08-01')
data.info()
```

    [*********************100%***********************]  1 of 1 completed
    <class 'pandas.core.frame.DataFrame'>
    DatetimeIndex: 253 entries, 2019-08-01 to 2020-07-31
    Data columns (total 6 columns):
     #   Column     Non-Null Count  Dtype  
    ---  ------     --------------  -----  
     0   Open       253 non-null    float64
     1   High       253 non-null    float64
     2   Low        253 non-null    float64
     3   Close      253 non-null    float64
     4   Adj Close  253 non-null    float64
     5   Volume     253 non-null    int64  
    dtypes: float64(5), int64(1)
    memory usage: 13.8 KB
    


```python
ts = data['Open']
print(ts.head())
```

    Date
    2019-08-01    53.474998
    2019-08-02    51.382500
    2019-08-05    49.497501
    2019-08-06    49.077499
    2019-08-07    48.852501
    Name: Open, dtype: float64
    
<br>

**- plt.legend(loc='best')**
범례 위치를 best로 설정
best는 디폴트 값을 의미

```python
data = yf.download('AAPL', '2019-08-01', '2020-08-01')
ts = data['Open']
plt.figure(figsize=(10,6))
plt.plot(ts)
plt.legend(labels=['Price'], loc='best')
plt.title('Stock Market fluctuation of AAPL') 
plt.xlabel('Date') 
plt.ylabel('Stock Market Open Price') 
plt.show()
```

    [*********************100%***********************]  1 of 1 completed
    


    
![](/images/output_10_1.png)
    
<br>

**방법2. 객체 지향 API**
- 먼저 컴퓨터 프로그램에서의 랜덤값은 무작위 수가 아니라,
특정 시작 숫자값을 정해주면 정해진 알고리즘에 따라 마치 난수처럼 보이는 수열을 생성하는 것이다.
이때 특정 시작 숫자가 바로 **시드(seed)**이다
- 시드 값은 현재 시각 등을 이용해 자동으로 정하기도 하지만,
사람이 수동으로 설정 할 수도 있다
- 따라서 특정 시드값이 사용될 경우 이후에 발생되는 난수를 알고리즘에 따라 직접 예측이 가능하다

<br>

- np.random.random(20000)
numpy를 이용해서 20000개의 난수를 생성한다
- random.seed()
seed 설정, 괄호 안에 0이상의 정수 값을 넣어주면 된다
- fig = Figure()
figure 객체 생성
- savefig('파일이름') 
그래프를 이미지 파일로 저장할 수 있다
- ax = fig.add_subplot(111)
- ax.hist(x, 100)
이거 모르게따@_2
```python
from matplotlib.backends.backend_agg import FigureCanvasAgg as FigureCanvas
from matplotlib.figure import Figure
import matplotlib.pyplot as plt

fig = Figure()

import numpy as np
np.random.seed(6)
x = np.random.random(20000)

ax = fig.add_subplot(111)
ax.hist(x, 100)
ax.set_title('Artist Layer Histogram')
#fig.savefig('Matplotlib_histogram.png')
plt.show()
```

방법3 Pyplot API + 객체지향 API


```python
data = yf.download('AAPL','2019-08-01','2020-08-01')
ts = data['Open']

fig, ax = plt.subplots(figsize=(10,6)) 
ax.plot(ts)
ax.set_title('Stock Market fluctuation of AAPL')
ax.set_xlabel('Date')
ax.set_ylabel('Stock Market Open Price')
plt.show()
```

    [*********************100%***********************]  1 of 1 completed
    


    
![](/images/output_14_1.png)
    


### **막대그래프**

- Tick 이란 
그래프의 축에 간격을 구분하기 위해 표시하는 눈금이다
ex) xticks(), yticks()
```python
import numpy as np
import calendar

month_list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
sold_list = [300, 400, 550, 900, 600, 960, 900, 910, 800, 700, 550, 450]

fig, ax = plt.subplots(figsize=(10,6))
plt.xticks(month_list, calendar.month_name[1:13], rotation=90)
plot = ax.bar(month_list, sold_list)

for rect in plot:
  print("graph:", rect) 
  height = rect.get_height()
  ax.text(rect.get_x() + rect.get_width()/2., 1.002*height,'%d' % int(height), ha='center', va='bottom')

plt.show()
```

    graph: Rectangle(xy=(0.6, 0), width=0.8, height=300, angle=0)
    graph: Rectangle(xy=(1.6, 0), width=0.8, height=400, angle=0)
    graph: Rectangle(xy=(2.6, 0), width=0.8, height=550, angle=0)
    graph: Rectangle(xy=(3.6, 0), width=0.8, height=900, angle=0)
    graph: Rectangle(xy=(4.6, 0), width=0.8, height=600, angle=0)
    graph: Rectangle(xy=(5.6, 0), width=0.8, height=960, angle=0)
    graph: Rectangle(xy=(6.6, 0), width=0.8, height=900, angle=0)
    graph: Rectangle(xy=(7.6, 0), width=0.8, height=910, angle=0)
    graph: Rectangle(xy=(8.6, 0), width=0.8, height=800, angle=0)
    graph: Rectangle(xy=(9.6, 0), width=0.8, height=700, angle=0)
    graph: Rectangle(xy=(10.6, 0), width=0.8, height=550, angle=0)
    graph: Rectangle(xy=(11.6, 0), width=0.8, height=450, angle=0)
    


    
![](/images/output_16_1.png)
    


선점도 그래프
- 두개의 연속형 변수 (키, 몸무게)
- 상관관계 != 인과관계


```python
#내장 데이터
import seaborn as sns

tips = sns.load_dataset("tips")
x = tips['total_bill']
y = tips['tip']

fig, ax = plt.subplots(figsize=(10,6))
ax.scatter(x, y)
ax.set_xlabel('Totla Bill')
ax.set_ylabel('Tip')
ax.set_title('Tip ~ Total Bill')

fig.show()
```


    
![png](/images/output_18_0.png)
    



```python
label, data = tips.groupby('sex')
tips['sex_color'] = tips['sex'].map({"Female" : '#0000FF',"Male" : "#00FF00"})

fig, ax = plt.subplots(figsize=(10,6))

for label, data in tips.groupby('sex'):
  ax.scatter(data['total_bill'], data['tip'], label=label, color=data['sex_color'], alpha=0.5)
  
  ax.set_xlabel('Total Bill')
  ax.set_ylabel('Tip')
  ax.set_title('Tip ~ Total Bill by Gender')

ax.legend()
fig.show()
```


    
![png](/images/output_19_0.png)
    


히스토그램

수치형 변수


```python
import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns

titanic = sns.load_dataset('titanic')
age = titanic['age']

nbins = 21
fig, ax = plt.subplots(figsize=(10,6))
ax.hist(age, bins = nbins)
ax.set_xlabel("Age")
ax.set_ylabel("Frequency")
ax.set_title("Distribution of Aae in  Titanic")
ax.axvline(x = age.mean(),linewidth = 2, color = 'r')
fig.show()
```


    
![png](/images/output_22_0.png)
    


박스 플롯

- x축 변수: 범주형 변수, 그룹과 관련있는 변수, 문자열
- y축 변수: 수치형 변수


```python
iris = sns.load_dataset('iris')

data = [iris[iris['species']=="setosa"]['petal_width'],
        iris[iris['species']=="versicolor"]['petal_width'],
        iris[iris['species']=="virginica"]['petal_width']]

fig, ax = plt.subplots(figsize=(10, 6))
ax.boxplot(data, labels=['setosa', 'versicolor', 'virginica'])

fig.show()
```


    
![png](/images/output_25_0.png)
    


히트맵


```python
import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns

flights = sns.load_dataset("flights")     #내장 데이터
flights = flights.pivot("month", "year","passengers")

fig, ax = plt.subplots(figsize = (12,6))

im = ax.imshow(flights, cmap = 'YlGnBu') #cmap은 colormap, YlGnBu은 색상

ax.set_xticklabels(flights.columns, rotation = 20)
ax.set_yticklabels(flights.index, rotation = 10)
fig.colorbar(im)

fig.show()
```


    
![png](/images/output_27_0.png)
    


Seaborn

산점도와 회귀선이 있는 산점도


```python
%matplotlib inline 

import matplotlib.pyplot as plt
import seaborn as sns

tips = sns.load_dataset("tips")
#print(tips)
sns.scatterplot(x = "total_bill", y = "tip", data = tips)
plt.show()
```


    
![png](/images/output_30_0.png)
    

- fig, ax = plt.subplots(ncols=2)
세로로 2개의 그래프를 그림
nrows=2이면 가로로 그래프를 2개 그림
nrows=2, nols=3이면 2행 3열로 그래프를 그림
- 
```python
fig, ax = plt.subplots(nrows = 1, ncols = 2, figsize=(15, 5))  #이런식으로 그래프 그리는방법을 각인시키기
sns.regplot(x = "total_bill", 
            y = "tip", 
            data = tips, 
            ax = ax[0], 
            fit_reg = True)

sns.regplot(x = "total_bill", 
            y = "tip", 
            data = tips, 
            ax = ax[1], 
            fit_reg = False)

plt.show()
```


    
![png](/images/output_31_0.png)
    


히스토그램/커널 밀도 그래프


```python
import matplotlib.pyplot as plt
import seaborn as sns

tips = sns.load_dataset("tips")   # 이렇게 하지 말깅!
sns.displot(x = "tip", data = tips)
plt.figure(figsize=(10,6))
plt.show()
```


    
![png](/images/output_33_0.png)
    



    <Figure size 720x432 with 0 Axes>



```python
sns.displot(x="tip",kind ="kde", data=tips)
plt.show()
```


    
![png](/images/output_34_0.png)
    



```python
sns.displot(x="tip",kde=True, data=tips)
plt.show()
```


    
![png](/images/output_35_0.png)
    


박스플롯


```python
sns.boxplot(x = "day", y = "total_bill", data =tips)
sns.swarmplot(x = "day", y = "total_bill", data = tips, alpha= .25)
```




    <matplotlib.axes._subplots.AxesSubplot at 0x7face917d410>




    
![png](/images/output_37_1.png)
    


막대 그래프


```python
sns.countplot (x="day", data = tips)
plt.show()
```


    
![png](/images/output_39_0.png)
    



```python
print(tips['day'].value_counts())
print("index: ", tips['day'].value_counts().index)
print("values: ", tips['day'].value_counts().values)
```

    Sat     87
    Sun     76
    Thur    62
    Fri     19
    Name: day, dtype: int64
    index:  CategoricalIndex(['Sat', 'Sun', 'Thur', 'Fri'], categories=['Thur', 'Fri', 'Sat', 'Sun'], ordered=False, dtype='category')
    values:  [87 76 62 19]
    


```python
print(tips['day'].value_counts(ascending=True))
```

    Fri     19
    Thur    62
    Sun     76
    Sat     87
    Name: day, dtype: int64
    


```python
plt.show()
```


```python
ax = sns.countplot(x = "day", data = tips, order = tips['day'].value_counts().index)
for p in ax.patches:
  height = p.get_height()
  ax.text(p.get_x() + p.get_width()/2., height+3, height, ha = 'center', size=9)
ax.set_ylim(-5, 100)
plt.show()
```


    
![png](/images/output_43_0.png)
    



```python
ax = sns.countplot(x = "day", data = tips, hue = "sex", dodge = True,
              order = tips['day'].value_counts().index)
for p in ax.patches:
  height = p.get_height()
  ax.text(p.get_x() + p.get_width()/2., height+3, height, ha = 'center', size=9)
ax.set_ylim(-5, 100)

plt.show()
```


    
![png](/images/output_44_0.png)
    


상관관계 그래프


```python
import pandas as pd 
import numpy as np 
import seaborn as sns
import matplotlib.pyplot as plt

mpg = sns.load_dataset("mpg")
print(mpg.shape) # 398 행, 9개 열

num_mpg = mpg.select_dtypes(include = np.number)
print(num_mpg.shape) # 398 행, 7개 열
```

    (398, 9)
    (398, 7)
    


```python
num_mpg.info()
```

    <class 'pandas.core.frame.DataFrame'>
    RangeIndex: 398 entries, 0 to 397
    Data columns (total 7 columns):
     #   Column        Non-Null Count  Dtype  
    ---  ------        --------------  -----  
     0   mpg           398 non-null    float64
     1   cylinders     398 non-null    int64  
     2   displacement  398 non-null    float64
     3   horsepower    392 non-null    float64
     4   weight        398 non-null    int64  
     5   acceleration  398 non-null    float64
     6   model_year    398 non-null    int64  
    dtypes: float64(4), int64(3)
    memory usage: 21.9 KB
    


```python
num_mpg.corr()
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>mpg</th>
      <th>cylinders</th>
      <th>displacement</th>
      <th>horsepower</th>
      <th>weight</th>
      <th>acceleration</th>
      <th>model_year</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>mpg</th>
      <td>1.000000</td>
      <td>-0.775396</td>
      <td>-0.804203</td>
      <td>-0.778427</td>
      <td>-0.831741</td>
      <td>0.420289</td>
      <td>0.579267</td>
    </tr>
    <tr>
      <th>cylinders</th>
      <td>-0.775396</td>
      <td>1.000000</td>
      <td>0.950721</td>
      <td>0.842983</td>
      <td>0.896017</td>
      <td>-0.505419</td>
      <td>-0.348746</td>
    </tr>
    <tr>
      <th>displacement</th>
      <td>-0.804203</td>
      <td>0.950721</td>
      <td>1.000000</td>
      <td>0.897257</td>
      <td>0.932824</td>
      <td>-0.543684</td>
      <td>-0.370164</td>
    </tr>
    <tr>
      <th>horsepower</th>
      <td>-0.778427</td>
      <td>0.842983</td>
      <td>0.897257</td>
      <td>1.000000</td>
      <td>0.864538</td>
      <td>-0.689196</td>
      <td>-0.416361</td>
    </tr>
    <tr>
      <th>weight</th>
      <td>-0.831741</td>
      <td>0.896017</td>
      <td>0.932824</td>
      <td>0.864538</td>
      <td>1.000000</td>
      <td>-0.417457</td>
      <td>-0.306564</td>
    </tr>
    <tr>
      <th>acceleration</th>
      <td>0.420289</td>
      <td>-0.505419</td>
      <td>-0.543684</td>
      <td>-0.689196</td>
      <td>-0.417457</td>
      <td>1.000000</td>
      <td>0.288137</td>
    </tr>
    <tr>
      <th>model_year</th>
      <td>0.579267</td>
      <td>-0.348746</td>
      <td>-0.370164</td>
      <td>-0.416361</td>
      <td>-0.306564</td>
      <td>0.288137</td>
      <td>1.000000</td>
    </tr>
  </tbody>
</table>
</div>




```python
fig, ax = plt.subplots(nrows = 1, ncols = 2, figsize=(16, 5))

#  기본 그래프 [Basic Correlation Heatmap]
sns.heatmap(num_mpg.corr(), ax=ax[0])
ax[0].set_title('Basic Correlation Heatmap', pad = 12)

# 상관관계 수치 그래프 [Correlation Heatmap with Number]
sns.heatmap(num_mpg.corr(), vmin=-1, vmax=1, annot=True, ax=ax[1])
ax[1].set_title('Correlation Heatmap with Number', pad = 12)

plt.show()
```


    
![png](/images/output_49_0.png)
    



```python
print(int(True))
np.triu(np.ones_like(num_mpg.corr()))
```

    1
    




    array([[1., 1., 1., 1., 1., 1., 1.],
           [0., 1., 1., 1., 1., 1., 1.],
           [0., 0., 1., 1., 1., 1., 1.],
           [0., 0., 0., 1., 1., 1., 1.],
           [0., 0., 0., 0., 1., 1., 1.],
           [0., 0., 0., 0., 0., 1., 1.],
           [0., 0., 0., 0., 0., 0., 1.]])




```python
mask = np.triu(np.ones_like(num_mpg.corr(), dtype=np.bool))
print(mask)
```

    [[ True  True  True  True  True  True  True]
     [False  True  True  True  True  True  True]
     [False False  True  True  True  True  True]
     [False False False  True  True  True  True]
     [False False False False  True  True  True]
     [False False False False False  True  True]
     [False False False False False False  True]]
    


```python
fig, ax = plt.subplots(figsize=(16, 5))  

#  기본 그래프 [Basic Correlation Heatmap]
ax = sns.heatmap(num_mpg.corr(), mask=mask, 
                 vmin=-1, vmax = 1, 
                 annot=True, 
                 cmap="BrBG", cbar = True)
ax.set_title('Triangle Correlation Heatmap', pad = 16, size = 16)
fig.show()
```


    
![png](/images/output_52_0.png)
    


Intermediate

페가 블로그 코드

https://jehyunlee.github.io/2020/08/27/Python-DS-28-mpl_spines_grids/


```python
import matplotlib.pyplot as plt
from matplotlib.ticker import (MultipleLocator, AutoMinorLocator, FuncFormatter)
import seaborn as sns
import numpy as np
```


```python
def plot_example(ax, zorder=0):
    ax.bar(tips_day["day"], tips_day["tip"], color="lightgray", zorder=zorder)
    ax.set_title("tip (mean)", fontsize=16, pad=12)

    # Values
    h_pad = 0.1
    for i in range(4):
        fontweight = "normal"
        color = "k"
        if i == 3:
            fontweight = "bold"
            color = "darkred"

        ax.text(i, tips_day["tip"].loc[i] + h_pad, f"{tips_day['tip'].loc[i]:0.2f}", 
                horizontalalignment='center', fontsize=12, fontweight=fontweight, color=color)

    # Sunday
    ax.patches[3].set_facecolor("darkred")
    ax.patches[3].set_edgecolor("black")

    # set_range
    ax.set_ylim(0, 4)
    return ax

def major_formatter(x, pos):
    return "{%.2f}" % x
formatter = FuncFormatter(major_formatter)
```


```python
tips = sns.load_dataset("tips")
tips_day = tips.groupby("day").mean().reset_index()
print(tips_day)
```

        day  total_bill       tip      size
    0  Thur   17.682742  2.771452  2.451613
    1   Fri   17.151579  2.734737  2.105263
    2   Sat   20.441379  2.993103  2.517241
    3   Sun   21.410000  3.255132  2.842105
    


```python
fig, ax = plt.subplots(figsize=(10, 6))
ax = plot_example(ax, zorder=2)
```


    
![png](/images/output_59_0.png)
    



```python
fig, ax = plt.subplots(figsize=(10, 6))
ax = plot_example(ax, zorder=2)

ax.spines["top"].set_visible(False)
ax.spines["right"].set_visible(False)
ax.spines["left"].set_visible(False)
```


    
![png](/images/output_60_0.png)
    



```python
fig, ax = plt.subplots()
ax = plot_example(ax, zorder=2)

ax.spines["top"].set_visible(False)
ax.spines["right"].set_visible(False)
ax.spines["left"].set_visible(False)

ax.yaxis.set_major_locator(MultipleLocator(1))
ax.yaxis.set_major_formatter(formatter)
ax.yaxis.set_minor_locator(MultipleLocator(0.5))
```


    
![png](/images/output_61_0.png)
    



```python
fig, ax = plt.subplots()
ax = plot_example(ax, zorder=2)

ax.spines["top"].set_visible(False)
ax.spines["right"].set_visible(False)
ax.spines["left"].set_visible(False)

ax.yaxis.set_major_locator(MultipleLocator(1))
ax.yaxis.set_major_formatter(formatter)
ax.yaxis.set_minor_locator(MultipleLocator(0.5))
    
ax.grid(axis="y", which="major", color="lightgray")
ax.grid(axis="y", which="minor", ls=":")
```


    
![png](/images/output_62_0.png)
    


책 코드


```python
import matplotlib.pyplot as plt
from matplotlib.ticker import (MultipleLocator, AutoMinorLocator, FuncFormatter)
import seaborn as sns
import numpy as np

tips = sns.load_dataset("tips")
fig, ax = plt.subplots(nrows = 1, ncols = 2, figsize=(16, 5))

def major_formatter(x, pos):
    return "%.2f$" % x
formatter = FuncFormatter(major_formatter)

# Ideal Bar Graph
ax0 = sns.barplot(x = "day", y = 'total_bill', data = tips, 
                  ci=None, color='lightgray', alpha=0.85, zorder=2, 
                  ax=ax[0])
```


    
![png](/images/output_64_0.png)
    



```python
group_mean = tips.groupby(['day'])['total_bill'].agg('mean')
h_day = group_mean.sort_values(ascending=False).index[0]
h_mean = np.round(group_mean.sort_values(ascending=False)[0], 2)
print("The Best Day:", h_day)
print("The Highest Avg. Total Biil:", h_mean)
```

    The Best Day: Sun
    The Highest Avg. Total Biil: 21.41
    


```python
tips = sns.load_dataset("tips")
fig, ax = plt.subplots(nrows = 1, ncols = 2, figsize=(16, 5))

# Ideal Bar Graph
ax0 = sns.barplot(x = "day", y = 'total_bill', data = tips, 
                  ci=None, color='lightgray', alpha=0.85, zorder=2, 
                  ax=ax[0])

group_mean = tips.groupby(['day'])['total_bill'].agg('mean')
h_day = group_mean.sort_values(ascending=False).index[0]
h_mean = np.round(group_mean.sort_values(ascending=False)[0], 2)
for p in ax0.patches:
  fontweight = "normal"
  color = "k"
  height = np.round(p.get_height(), 2)
  if h_mean == height:
    fontweight="bold"
    color="darkred"
    p.set_facecolor(color)
    p.set_edgecolor("black")
  ax0.text(p.get_x() + p.get_width()/2., height+1, height, ha = 'center', size=12, fontweight=fontweight, color=color)

fig.show()
```


    
![png](/images/output_66_0.png)
    



```python
import matplotlib.pyplot as plt
from matplotlib.ticker import (MultipleLocator, AutoMinorLocator, FuncFormatter)
import seaborn as sns
import numpy as np

tips = sns.load_dataset("tips")
fig, ax = plt.subplots(nrows = 1, ncols = 2, figsize=(16, 5))

def major_formatter(x, pos):
    return "%.2f$" % x
formatter = FuncFormatter(major_formatter)

# Ideal Bar Graph
ax0 = sns.barplot(x = "day", y = 'total_bill', data = tips, 
                  ci=None, color='lightgray', alpha=0.85, zorder=2, 
                  ax=ax[0])

group_mean = tips.groupby(['day'])['total_bill'].agg('mean')
h_day = group_mean.sort_values(ascending=False).index[0]
h_mean = np.round(group_mean.sort_values(ascending=False)[0], 2)
for p in ax0.patches:
  fontweight = "normal"
  color = "k"
  height = np.round(p.get_height(), 2)
  if h_mean == height:
    fontweight="bold"
    color="darkred"
    p.set_facecolor(color)
    p.set_edgecolor("black")
  ax0.text(p.get_x() + p.get_width()/2., height+1, height, ha = 'center', size=12, fontweight=fontweight, color=color)

ax0.set_ylim(-3, 30)
ax0.set_title("Ideal Bar Graph", size = 16)

ax0.spines['top'].set_visible(False)
ax0.spines['left'].set_position(("outward", 20))
ax0.spines['left'].set_visible(False)
ax0.spines['right'].set_visible(False)

ax0.yaxis.set_major_locator(MultipleLocator(10))
ax0.yaxis.set_major_formatter(formatter)
ax0.yaxis.set_minor_locator(MultipleLocator(5))

ax0.set_ylabel("Avg. Total Bill($)", fontsize=14)

ax0.grid(axis="y", which="major", color="lightgray")
ax0.grid(axis="y", which="minor", ls=":")

ax0.set_xlabel("Weekday", fontsize=14)
for xtick in ax0.get_xticklabels():
  print(xtick)
  if xtick.get_text() == h_day:
    xtick.set_color("darkred")
    xtick.set_fontweight("demibold")
ax0.set_xticklabels(['Thursday', 'Friday', 'Saturday', 'Sunday'], size=12)

ax1 = sns.barplot(x = "day", y = 'total_bill', data = tips, 
                  ci=None, alpha=0.85, 
                  ax=ax[1])
for p in ax1.patches:
  height = np.round(p.get_height(), 2)
  ax1.text(p.get_x() + p.get_width()/2., height+1, height, ha = 'center', size=12)
ax1.set_ylim(-3, 30)
ax1.set_title("Just Bar Graph")

plt.show()
```

    Text(0, 0, 'Thur')
    Text(0, 0, 'Fri')
    Text(0, 0, 'Sat')
    Text(0, 0, 'Sun')
    


    
![png](/images/output_67_1.png)
    

<br>
<br>
<br>

Reference
[블로그](https://yjs-program.tistory.com/177)