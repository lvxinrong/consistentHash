# consistentHash
Study Consistent Hash Demo

---

## 一致性Hash的学习
* 一般的Hash可以看为对某个数的取模（求余数），比如HashMap中的hash值得算法，因为这种方法在计算结束后的hash值如果在计算值的数量的变化下求余数，会导致余数的
不一致，这样统计出来的服务器节点的hash值会出现变化，导致请求找不到或者找到错误的服务器的地址。那一致性Hash的做法是，规定了取模的长度，也就是hash表的长度，就是2
2 ^ 32 -1 ，也就是说已经规定好了Hash的散列大小，所有的Hash的取值都是独立的。这样就保证了在获取服务地址的时候，可以准确地取到服务器的地址。这样就解决了普通
Hash的不独立的问题。
## 虚拟节点 
* 在整个hash环上如果服务器比较少，或者Hash的不够均匀的时候，会导致某些服务器请求量大，有些服务器请求量小，不符合负载均衡的特性，那么久加入了虚拟节点，有一张图片
用来显示真实节点和虚拟节点的数量关系。虚拟节点是通过对真实服务器地址的拼接之后再次进行Hash，比如192.168.0.1:8080的服务需要添加虚拟节点，那么虚拟节点可以设置为
192.168.0.1:8080&&00001 这样的形式去计算Hash,然后在hash环上去到虚拟节点的名称后，进行截取，就可以获取到真实服务器的节点。
## Demo中的一些学习到的知识 :
* TreeMap在大数据量下的查询是最快的。所以整个环是使用TreeMap来模拟的。作者是通过tailMap(K fromKey)这种方法来获取到环中顺时针之后所有的节点，然后通过方法来获取到顺时针距离hash值最近的节点，思路很棒棒哟。
> SortedMap<K,V> tailMap(K fromKey) 返回此映射的部分视图，其键大于等于 fromKey。
   
> firstKey()  返回此映射中当前第一个（最低）键。

## 总结
* 在很多分布式，集群，负载均衡的技术文章中会看到对一致性hash的关键词，今天索性彻底学习一下，不过只是大概理解了一致性Hash到底是怎么样的，并没有学习的很深入，等待本人
的算法书籍到货，会重新开始学习一下一致性Hash

* 差点忘了，这里补上今天阅读到的两位作者的连接[ 对一致性Hash算法，Java代码实现的深入研究](http://blog.csdn.net/hemin1003/article/details/71484309),[白话解析:一致性哈希算法](http://www.zsythink.net/archives/1182)

---

**我相信乔布斯说的，只有那些疯狂到认为自己可以改变世界的人才能真正地改变世界。面对压力，我可以挑灯夜战、不眠不休；面对困难，我愿意迎难而上、永不退缩**
