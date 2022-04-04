---
title: Plotly를 이용해 Sunburst 그래프 그리기
categories:
- 파이썬
- Python Plotly
date: 2021-11-28
tags: plotly, pie graph, pie, sunburst
thumbnail: /images/plotlysunburst/newplot.png
---
  
### **Ref**
---
https://plotly.com/python/sunburst-charts/

<br>

### **Sunburst Graph2**
---
![](/images/plotlysunburst/newplot.png)
처음 이 그래프를 보고는 신기해서 꼭 그려봐야지 다짐했다
근데 사실 정말 단순하다..
정말 그리기 쉬움 (사실 그리기 어려운 그래프는 없다 ..ㅋㅋ)

![](/images/plotlysunburst/datapre.PNG)

df21_Ea_degree_yearly의 데이터 셋인데
sunburst를 그리기 위해서 데이터 전처리를 통해서 다듬은 것!

plotly는 그래프를 그릴수 있는 방법으로 px(express)와 go(graph_objects) 로 두가지가 있는데 
px는 빠르고 쉽게 그래프를 그리는 방법
go는 하나하나 세부 설정으로 그래프를 그릴수 있다.

위의 그래프는 px로 그렸다.
go로 그릴수도 있지만 데이터를 하나하나 정리해서 직접 넣어줘야하는 번거로움 때문에
내가 그린그래프처럼 데이터 값이 많고 복잡해지면 px로 그려야한다.

- path
path=['year','degree']
내가 나눠줄 구간설정 할 수 있다

- values
데이터 값을 넣어줬다 
순서는 그다지 중요한것 같지 않다 
값이 맞게 잘 그려졌음!

<br>

```python

fig = px.sunburst(df21_Ea_degree_yearly, path=['year','degree'], values=df21_Ea_degree_yearly['value'].tolist())
fig.update_layout( margin = dict(t=10, l=10, r=10, b=10),colorway=("#F2798F","#88BFBA","#CDD9A3",'#F28705','#D9946C'))

fig.update_layout(title='<b>         Degree</b>',title_font_size=25,
                  margin = dict(t=100, l=100, r=50, b=100),
                  height=700, width=700)
fig.update_traces(hovertemplate='<b>Name</b>: %{id}<br>'+
                                '<b>Count</b>: %{value}<br>'+
                                '<b>Parent</b>: %{parent}') 
fig.add_annotation(dict(font=dict(size=14),
                                    x=0.8,
                                    y=-0.2,
                                    showarrow=False,
                                    text="@green_yhjw",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))
fig.show()
```

<br>
<br>
<br>
