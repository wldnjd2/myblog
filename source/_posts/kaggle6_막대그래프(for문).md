---
title: 막대그래프(for문, 수평)
date: 2021-11-09
tags: kaggle, plotly
toc: true
thumbnail: /images/0306_3-2/4.PNG
categories: 
- kaggle 필사
widgets: null
---

### **3-2. 막대그래프 (수평)**
---
**라이브러리 임포트 해주기 & 캐글 데이터 불러오기 생략!**
<br>

### **startswith()**
---
메소드는 어떤 문자열이 특정 문자로 시작하는지 확인하여 결과를 true 혹은 false로 반환합니다.
<br>
- python for문 문
>for 카운터변수 in range(반복횟수):
>    반복해서 실행할 명령

- algorithms_cols = [col for col in df if col.startswith('Q17')]
첫번째 컬럼부터 df 끝까지 뒤의 if문이 반복된다
if문은 컬럼 값이 문자열 Q17로 시작하는지 확인하여 true일때만 데이터 가져온다
- col(뒤)
카운터 변수
- df
반복하는 범위
- col(앞) => List Comprehension
반복문인 for문의 결과값을 받아주는 역할을 한다

```python
algorithms_cols = [col for col in df if col.startswith('Q17')]
algorithms = df[algorithms_cols]
print(algorithms)
```
![](/images/0306_3-2/1.PNG)
<br>

### **List Comprehension**
[참고 블로그 링크_1](https://yjs-program.tistory.com/177)
[참고링크](https://dojang.io/mod/page/view.php?id=2285)
---
- 표현식
리스트 안에 식 for문을 지정한다.
[식 for 변수 in 리스트]
list(식 for 변수 in 리스트)


<br>

### **columns 이름 바꿔주기**
---
```python
algorithms.columns = ['Linear or Logistic Regression', 'Decision Trees or Random Forests', 
                     'Gradient Boosting Machines', 'Bayesian Approaches', 'Evolutionary Approaches', 
                     'Dense Neural Networks', 'Convolutional Neural Networks', 'Generative Adversarial Networks',
                     'Recurrent Neural Networks', 'Transformer Networks', 'None', 'Other']
print(algorithms)
```
![](/images/0306_3-2/2.PNG)
<br>

### **algorithms 객체 생성**
---

```python
algorithms = (
    algorithms
    .count()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Algorithms', 0:'Count'})
    .sort_values(by=['Count'], ascending=False)
    )
print(algorithms)
```
![](/images/0306_3-2/3.PNG)
<br>

### **percent 컬럼 추가**
---
```python
algorithms['percent'] = ((algorithms['Count'] / len(df))*100).round(2).astype(str) + '%'
print(algorithms)
```
![](/images/0306_3-2/5.PNG)
<br>

```python
colors = ['#033351',] * 12
colors[0] = '#5abbf9'
colors[1] = '#5abbf9'
colors[2] = '#066eb0'
colors[3] = '#066eb0'
colors[4] = '#044a77'
colors[5] = '#044a77'
colors[6] = '#044a77'
fig = go.Figure(go.Bar(
            x=algorithms['Count'],
            y=algorithms['Algorithms'],
            text=algorithms['percent'],
            orientation='h',
            marker_color=colors
                        ))

fig.update_traces(texttemplate='%{text}', 
                  textposition='outside',
                  cliponaxis = False,
                  hovertemplate='<b>Algorithm</b>: %{y}<br><extra></extra>'+
                                '<b>Count</b>: %{x}',
                  textfont_size=12)
                  
fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
 
fig.update_layout(showlegend=False, 
                  plot_bgcolor='#F7F7F7', 
                  margin=dict(pad=20),
                  paper_bgcolor='#F7F7F7',
                  xaxis={'showticklabels': False},
                  yaxis_title=None,
                  height = 600,
                  xaxis_title=None,
                  yaxis={'categoryorder':'total ascending'},
                  title_text="Most Commonly Used <b>Algorithms</b>",
                  title_x=0.5,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=15, color='#000000'),
                  title_font_size=35)

fig.add_annotation(dict(font=dict(size=14),
                                    x=0.98,
                                    y=-0.17,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.add_annotation(dict(font=dict(size=12),
                                    x=0,
                                    y=-0.17,
                                    showarrow=False,
                                    text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))


fig.show()
```
![](/images/0306_3-2/4.PNG)
<br>
<br>
<br>