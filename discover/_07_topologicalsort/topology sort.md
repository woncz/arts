### 拓扑排序

##### 天敌
```
环
类比DAG(Directed Acyclic Graph)，有向无环图
```

##### 度量
```
入度出度 input degree and output degree
```

##### 优先级
```
input degree = 0 is the 1st priority 
```

##### 根结点
```
may have multi-roots
可能存在多入口
```

##### 表示
```
2 presentation ways
1 - adjacent matrix
2 - adjacent list[one array and one array list ]
```

##### 流程
```
0 - init graph structure
1 - build graph
2 - find the roots of input degree = 0
3 - dfs or bfs
4 - check if every vertex is visited
```

