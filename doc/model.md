# 跑得快
跑得快规则比较简单，共48张牌，没有大小王，只有1张2和3张A。

共3个玩家，每个玩家起始有16张牌。最多有48个回合（每回合仅有一个玩家出一张牌），每回合最多三次出牌，每次出牌最多16张。
### 输入数据
每张牌用一个整数表示，范围1~255，编码另行规定。

根据规则，输入数据需要包含当前手牌和历史出牌的记录，那么数据的长度为`16 + 48 * 3 * 16 = 2320`。
### 模型1
```
 neural network with 5 layers

 · · · · · · · · · ·       (input data)                         X [batch, 2320]   
 \x/x\x/x\x/x\x/x\x/    -- fully connected layer (sigmoid)      W1 [2320, 400]      B1[400]   
  · · · · · · · · ·                                             Y1 [batch, 400]   
   \x/x\x/x\x/x\x/      -- fully connected layer (sigmoid)      W2 [400, 200]      B2[200]   
    · · · · · · ·                                               Y2 [batch, 200]   
    \x/x\x/x\x/         -- fully connected layer (sigmoid)      W3 [200, 100]       B3[100]   
     · · · · ·                                                  Y3 [batch, 100]   
     \x/x\x/            -- fully connected layer (sigmoid)      W4 [100, 50]        B4[50]   
      · · ·                                                     Y4 [batch, 50]   
      \x/               -- fully connected layer (sigmoid)      W5 [50, 1]        B5[1]   
       ·                                                        Y5 [batch, 1]   
```
