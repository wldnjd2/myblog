---
title: Python Pandas (4)
date: 2021-11-02
tags: markdown, python, pycharm, pandas
toc: true
categories: 
- study
- 파이썬
---
<br>


### **Pandas란**
---
파이썬 언어로 작성된 데이터를 분석 및 조작하기 위한 소프트웨어 라이브러리이다.
팬더스는 R에서 사용되던 data.frame 구조를 본뜬 DataFrame이라는 구조를 사용하기 때문에, 
R의 data.frame에서 사용하던 기능 상당수를 무리없이 사용할 수 있도록 만들었다. 

- 사용하는 이유
데이터 전처리 하기 위함
index 1개와 column 1개 ---> series
index 1개와 column 2개 ---> dataframe
<br>

Pandas 참고 사이트
https://pandas.pydata.org/docs/reference/index.html
<br>





### **라이브러리 불러오기**
---


```python
import pandas as pd
print(pd.__version__)
```

    1.1.5
    
<br>

### **테스트**
---

```python
df = pd.DataFrame({'col1': [1,2], 'col2': [3,4]})
print(type(df))
```

    <class 'pandas.core.frame.DataFrame'>
    
<br>

### **구글 드라이브 연동 (colab이랑 연결)**
---

```python
from google.colab import drive
drive.mount('/content/drive')
```

    Mounted at /content/drive
    


```python
DATA_PATH = "경로를 입력하시기를 바랍니다."
DATA_PATH = '/content/drive/MyDrive/Colab Notebooks/lectures_211101/PART_I_Intro/data'
lemonade = pd.read_csv(DATA_PATH + '/Lemonade2016.csv')
lemonade.info()
```

    <class 'pandas.core.frame.DataFrame'>
    RangeIndex: 32 entries, 0 to 31
    Data columns (total 7 columns):
     #   Column       Non-Null Count  Dtype  
    ---  ------       --------------  -----  
     0   Date         31 non-null     object 
     1   Location     32 non-null     object 
     2   Lemon        32 non-null     int64  
     3   Orange       32 non-null     int64  
     4   Temperature  32 non-null     int64  
     5   Leaflets     31 non-null     float64
     6   Price        32 non-null     float64
    dtypes: float64(2), int64(3), object(2)
    memory usage: 1.9+ KB
    

### **데이터 둘러보기**
---

(lemonade 파일은 가게 포스기라고 생각하자)

**- head**
```python
#상위 5개 행 출력
#0부터 4까지 행 출력
lemonade.head(5)
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
      <th>Date</th>
      <th>Location</th>
      <th>Lemon</th>
      <th>Orange</th>
      <th>Temperature</th>
      <th>Leaflets</th>
      <th>Price</th>
      <th>Sold</th>
      <th>Revenue</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>7/1/2016</td>
      <td>Park</td>
      <td>97</td>
      <td>67</td>
      <td>70</td>
      <td>90.0</td>
      <td>0.25</td>
      <td>164</td>
      <td>41.00</td>
    </tr>
    <tr>
      <th>1</th>
      <td>7/2/2016</td>
      <td>Park</td>
      <td>98</td>
      <td>67</td>
      <td>72</td>
      <td>90.0</td>
      <td>0.25</td>
      <td>165</td>
      <td>41.25</td>
    </tr>
    <tr>
      <th>2</th>
      <td>7/3/2016</td>
      <td>Park</td>
      <td>110</td>
      <td>77</td>
      <td>71</td>
      <td>104.0</td>
      <td>0.25</td>
      <td>187</td>
      <td>46.75</td>
    </tr>
    <tr>
      <th>3</th>
      <td>7/4/2016</td>
      <td>Beach</td>
      <td>134</td>
      <td>99</td>
      <td>76</td>
      <td>98.0</td>
      <td>0.25</td>
      <td>233</td>
      <td>58.25</td>
    </tr>
    <tr>
      <th>4</th>
      <td>7/5/2016</td>
      <td>Beach</td>
      <td>159</td>
      <td>118</td>
      <td>78</td>
      <td>135.0</td>
      <td>0.25</td>
      <td>277</td>
      <td>69.25</td>
    </tr>
  </tbody>
</table>
</div>

<br>

**- tail**
```python
#끝에 3개 행 출력
lemonade.tail(3)
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
      <th>Date</th>
      <th>Location</th>
      <th>Lemon</th>
      <th>Orange</th>
      <th>Temperature</th>
      <th>Leaflets</th>
      <th>Price</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>29</th>
      <td>7/29/2016</td>
      <td>Park</td>
      <td>100</td>
      <td>66</td>
      <td>81</td>
      <td>95.0</td>
      <td>0.35</td>
    </tr>
    <tr>
      <th>30</th>
      <td>7/30/2016</td>
      <td>Beach</td>
      <td>88</td>
      <td>57</td>
      <td>82</td>
      <td>81.0</td>
      <td>0.35</td>
    </tr>
    <tr>
      <th>31</th>
      <td>7/31/2016</td>
      <td>Beach</td>
      <td>76</td>
      <td>47</td>
      <td>82</td>
      <td>68.0</td>
      <td>0.35</td>
    </tr>
  </tbody>
</table>
</div>

<br>

**- info() 메소드**
데이터에 대한 전반적인 정보
df를 구성하는 행과 열의 크기, 컬럼명, 컬럼을 구성하는 자료형을 출력

```python
print(lemonade.info())
```

    <class 'pandas.core.frame.DataFrame'>
    RangeIndex: 32 entries, 0 to 31
    Data columns (total 7 columns):
     #   Column       Non-Null Count  Dtype  
    ---  ------       --------------  -----  
     0   Date         31 non-null     object 
     1   Location     32 non-null     object 
     2   Lemon        32 non-null     int64  
     3   Orange       32 non-null     int64  
     4   Temperature  32 non-null     int64  
     5   Leaflets     31 non-null     float64
     6   Price        32 non-null     float64
    dtypes: float64(2), int64(3), object(2)
    memory usage: 1.9+ KB
    None
    
<br> 

**- describe() 메소드**
다양한 통계량을 요약해주는 메소드

```python
lemonade.describe()
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
      <th>Lemon</th>
      <th>Orange</th>
      <th>Temperature</th>
      <th>Leaflets</th>
      <th>Price</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>count</th>
      <td>32.000000</td>
      <td>32.000000</td>
      <td>32.000000</td>
      <td>31.000000</td>
      <td>32.000000</td>
    </tr>
    <tr>
      <th>mean</th>
      <td>116.156250</td>
      <td>80.000000</td>
      <td>78.968750</td>
      <td>108.548387</td>
      <td>0.354687</td>
    </tr>
    <tr>
      <th>std</th>
      <td>25.823357</td>
      <td>21.863211</td>
      <td>4.067847</td>
      <td>20.117718</td>
      <td>0.113137</td>
    </tr>
    <tr>
      <th>min</th>
      <td>71.000000</td>
      <td>42.000000</td>
      <td>70.000000</td>
      <td>68.000000</td>
      <td>0.250000</td>
    </tr>
    <tr>
      <th>25%</th>
      <td>98.000000</td>
      <td>66.750000</td>
      <td>77.000000</td>
      <td>90.000000</td>
      <td>0.250000</td>
    </tr>
    <tr>
      <th>50%</th>
      <td>113.500000</td>
      <td>76.500000</td>
      <td>80.500000</td>
      <td>108.000000</td>
      <td>0.350000</td>
    </tr>
    <tr>
      <th>75%</th>
      <td>131.750000</td>
      <td>95.000000</td>
      <td>82.000000</td>
      <td>124.000000</td>
      <td>0.500000</td>
    </tr>
    <tr>
      <th>max</th>
      <td>176.000000</td>
      <td>129.000000</td>
      <td>84.000000</td>
      <td>158.000000</td>
      <td>0.500000</td>
    </tr>
  </tbody>
</table>
</div>

<br>

**- .value_counts() 메소드**
개별 컬럼 내에 각각의 값이 나온 횟수를 셀 수 있다


```python
lemonade['Location'].value_counts()
```




    Beach    17
    Park     15
    Name: Location, dtype: int64


<br> 

### **데이터 다뤄보기**
---


```python
#Sold라는 컬럼을 만들고 값을 0으로 지정
lemonade['Sold'] = 0
print(lemonade.head(3))
```

           Date Location  Lemon  Orange  Temperature  Leaflets  Price  Sold
    0  7/1/2016     Park     97      67           70      90.0   0.25     0
    1  7/2/2016     Park     98      67           72      90.0   0.25     0
    2  7/3/2016     Park    110      77           71     104.0   0.25     0
    


```python
lemonade['Sold'] = lemonade['Lemon'] + lemonade['Orange']
print(lemonade.head(3))
```

           Date Location  Lemon  Orange  Temperature  Leaflets  Price  Sold
    0  7/1/2016     Park     97      67           70      90.0   0.25   164
    1  7/2/2016     Park     98      67           72      90.0   0.25   165
    2  7/3/2016     Park    110      77           71     104.0   0.25   187
    


```python
lemonade['Revenue'] = lemonade['Price']*lemonade['Sold']
print(lemonade.head(3))
```

           Date Location  Lemon  Orange  ...  Leaflets  Price  Sold  Revenue
    0  7/1/2016     Park     97      67  ...      90.0   0.25   164    41.00
    1  7/2/2016     Park     98      67  ...      90.0   0.25   165    41.25
    2  7/3/2016     Park    110      77  ...     104.0   0.25   187    46.75
    
    [3 rows x 9 columns]
    

<br>

**- Out으로 출력하는 최대 칼럼의 개수**
: display.max_columns

**- 옵션 설정**
: pd.set_option()

**- pd.set_option('display.max_columns',None)**
: 열 전체를 출력한다는 의미

```python
pd.set_option('display.max_columns',None)

lemonade['Revenue'] = lemonade['Price'] * lemonade['Sold']
print(lemonade.head(3))
```

           Date Location  Lemon  Orange  Temperature  Leaflets  Price  Sold  \
    0  7/1/2016     Park     97      67           70      90.0   0.25   164   
    1  7/2/2016     Park     98      67           72      90.0   0.25   165   
    2  7/3/2016     Park    110      77           71     104.0   0.25   187   
    
       Revenue  
    0    41.00  
    1    41.25  
    2    46.75  
 
<br> 

```python
pd.set_option('display.max_columns',0)

lemonade['Revenue']= lemonade['Price'] * lemonade['Sold']
print(lemonade.head(3))
```

           Date Location  Lemon  Orange  ...  Leaflets  Price  Sold  Revenue
    0  7/1/2016     Park     97      67  ...      90.0   0.25   164    41.00
    1  7/2/2016     Park     98      67  ...      90.0   0.25   165    41.25
    2  7/3/2016     Park    110      77  ...     104.0   0.25   187    46.75
    
    [3 rows x 9 columns]
    
<br> 

axis=1은 열방향으로 동작 -> columns
axis=0은 행방향으로 동작 -> index

```python
#Sold column(열)을 삭제
lemonade_column_drop = lemonade.drop('Sold', axis=1)
print(lemonade_column_drop.head(3))
```

           Date Location  Lemon  Orange  Temperature  Leaflets  Price  Revenue
    0  7/1/2016     Park     97      67           70      90.0   0.25    41.00
    1  7/2/2016     Park     98      67           72      90.0   0.25    41.25
    2  7/3/2016     Park    110      77           71     104.0   0.25    46.75
    


```python
#0번 행 삭제
lemonade_row_drop = lemonade_column_drop.drop(0, axis=0)
print(lemonade_row_drop.head(3))
```

           Date Location  Lemon  Orange  Temperature  Leaflets  Price  Revenue
    1  7/2/2016     Park     98      67           72      90.0   0.25    41.25
    2  7/3/2016     Park    110      77           71     104.0   0.25    46.75
    3  7/4/2016    Beach    134      99           76      98.0   0.25    58.25
    
<br>

### **데이터 인덱싱**
---


```python
# 0번부터 4번까지 행 출력
print(lemonade[0:5])
```

           Date Location  Lemon  Orange  Temperature  Leaflets  Price  Sold  \
    0  7/1/2016     Park     97      67           70      90.0   0.25   164   
    1  7/2/2016     Park     98      67           72      90.0   0.25   165   
    2  7/3/2016     Park    110      77           71     104.0   0.25   187   
    3  7/4/2016    Beach    134      99           76      98.0   0.25   233   
    4  7/5/2016    Beach    159     118           78     135.0   0.25   277   
    
       Revenue  
    0    41.00  
    1    41.25  
    2    46.75  
    3    58.25  
    4    69.25  
    


```python
lemonade['Location'] == 'Beach'
```




    0     False
    1     False
    2     False
    3      True
    4      True
    5      True
    6      True
    7      True
    8      True
    9      True
    10     True
    11     True
    12     True
    13     True
    14     True
    15     True
    16     True
    17     True
    18    False
    19    False
    20    False
    21    False
    22    False
    23    False
    24    False
    25    False
    26    False
    27    False
    28    False
    29    False
    30     True
    31     True
    Name: Location, dtype: bool




```python
#true값만 반환
print(lemonade[lemonade['Location'] == 'Beach'].head(3))
```

           Date Location  Lemon  Orange  Temperature  Leaflets  Price  Sold  \
    3  7/4/2016    Beach    134      99           76      98.0   0.25   233   
    4  7/5/2016    Beach    159     118           78     135.0   0.25   277   
    5  7/6/2016    Beach    103      69           82      90.0   0.25   172   
    
       Revenue  
    3    58.25  
    4    69.25  
    5    43.00  
    
<br>
iloc (integer-location based): 행 번호로 선택하는 방법 <br>
loc (Labels): 조건 표현으로 선택함 <br>
<br>

ex) <br>
df.loc[[행],[열]]
df.iloc[[행],[열]]
행, 열 조건은 똑같다

```python
print(lemonade.iloc[0:3, 0:2])    #첫 3개 행과 0,1,2번째 행 출력하기
```

           Date Location
    0  7/1/2016     Park
    1  7/2/2016     Park
    2  7/3/2016     Park
    


```python
print(lemonade.loc[0:2, ['Date','Location']])   #열 
```

           Date Location
    0  7/1/2016     Park
    1  7/2/2016     Park
    2  7/3/2016     Park
    
<br>

### **기본 데이터 전처리**
---

**- sort_values()**
by 옵션에 기준으로 데이터를 정렬

```python
print(lemonade.sort_values(by=['Temperature']).head(5))
```

             Date Location  Lemon  Orange  Temperature  Leaflets  Price  Sold
    0    7/1/2016     Park     97      67           70      90.0   0.25     0
    20  7/20/2016     Park     71      42           70       NaN   0.50     0
    2    7/3/2016     Park    110      77           71     104.0   0.25     0
    1    7/2/2016     Park     98      67           72      90.0   0.25     0
    16  7/16/2016    Beach     81      50           74      90.0   0.50     0
    
<br>
 
**- Groupby()**
전체 데이터를 그룹별로 분할하여
mean(), sum(), count()와 같은 메소드를 사용해 연산하고
연산 결과를 다시 합치는 과정을 거친다

```python
print(lemonade.groupby(by='Location').count())
```

              Date  Lemon  Orange  Temperature  Leaflets  Price  Sold
    Location                                                         
    Beach       16     17      17           17        17     17    17
    Park        15     15      15           15        14     15    15
    


<br>
<br>
<br>