# LRU缓存

- 哈希表 + 双向链表(哈希表O(1)查找，双向链表O(1)增删改，并且双向链表维护节点生命周期)
- head、tail节点是否为哨兵节点，哨兵节点不存储数据，只是作为链表的头和尾
- put时是否更新节点生命周期

## 自己实现方案关键点
- 双向链表（头旧尾新）：①头部增加、②尾部移除、③任意节点删除
- LRU缓存：①增加节点、②更新节点、③删除节点、③移除最旧节点
- 注意分层设计，每一层关注自己的职责

## 借用JDK自带框架类 LinkedHashMap
- accessOrder = true，get操作后，会将节点移到链表尾部
- removeEldestEntry 方法，当缓存满时，会移除最旧节点
- 或者put的时候，判断是否满了，满了就移除最旧节点，通过keySet().iterator().next()获取最旧节点的key，然后remove()移除
- 学习HashMap和LinkedHashMap的实现原理(Template Method)