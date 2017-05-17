package com.lv.study.consistentHashMy;

import java.util.SortedMap;
import java.util.TreeMap;

public class WithoutVirtualNodeConsistentHash {
	
	/**
	 * 因为一致性hash是一个环状，并且在查找对应节点的场景中，大部分不会直接命中节点，是按照顺时针方向取最接近的节点
	 * 所以需要对计算过得hash值进行排序和查找，查找效率快的一般是数组，在400000w数据的情况下，TreeMap的查找效率永远是最快的
	 * 但是因为TreeMap在插入的时候比较麻烦，但是一致性Hash的查找频率远远大于新增，
	 */
	
	private static SortedMap<Long,String> hashNodeMap = new TreeMap<Long,String>();
	
	//先将server的节点填充到map中
	static {
		for(int i = 0 ; i < ServersArray.servers.length; i++) {
			String name = ServersArray.servers[i];
			long hash = ServersArray.getHash(name);
			hashNodeMap.put(hash, name);
			System.out.println("将服务 :" + name + "填充到Map中 hash值是 :" + hash);
		}
	}
	/**
	 * 这里就是一种模拟，通过计算传入node的值，计算出来Hash 值，通过Hash 值查找距离这个Hash值最近的服务节点
	 */
	private static String getServer(String node) {
		long hash = ServersArray.getHash(node);
		//因为没有考虑到虚拟节点，所以直接查找即可,取到所有比Hash大的节点
		SortedMap<Long,String> subMap = hashNodeMap.tailMap(hash);
		//取到第一个节点
		//这里有问题哟，如果说某个映射的值大于所有存在节点的hash值，这里会返回空，那么应该是取整个TreeMap的第一位就好
		long i;
		if(subMap.size() > 0) {
			i = subMap.firstKey();
		}else {
			i = hashNodeMap.firstKey();
		}
		
		return hashNodeMap.get(i);
	}
	
	
	public static void main(String[] args) {
		String[] nodes = { "127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333" };  
        for (int i = 0; i < nodes.length; i++)  
        	
            System.out  
                    .println(", 被路由到结点[" + getServer(nodes[i]) + "]");  
   }  
}
