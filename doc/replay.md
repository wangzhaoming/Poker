# 牌谱
每局游戏服务器需要记录牌谱。

使用按行记录的方式，方便阅读和解析。
### 格式
```
id1,id2,id3   //玩家的id，按照出牌的顺序记录，id1的玩家第一个出牌。可以支持超过3个玩家。下面的记录不再使用玩家的id，而是使用序号来标识玩家
1:int,int,int...  //16个整数，对应玩家1的起始手牌
2:int,int,int...  //玩家2的起始手牌
3:int,int,int...  //玩家3的起始手牌
1:int...  //1-16个整数，对应玩家1的出牌
2:int...  //玩家2的出牌，以此类推
3:int...
1:pass  //表示该玩家空过
...
...
```