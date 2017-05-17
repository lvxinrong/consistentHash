package com.lv.study.consistentHashMy;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class WithVirtualNodeConsistentHashing {
	
	 /**  
     * 虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应10个虚拟节点  
     */  
    private static final int VIRTUAL_NODES = 10; 
    
    /**  
     * 真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好  
     */  
    private static List<String> realNodes = new LinkedList<String>();
    
    /**  
     * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称  
     */  
    private static SortedMap<Long, String> virtualNodes = new TreeMap<Long, String>(); 
    
    static {
        // 先把原始的服务器添加到真实结点列表中  
        for (int i = 0; i < ServersArray.servers.length; i++)  
            realNodes.add(ServersArray.servers[i]);
        
        //再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高
        for(String str : realNodes) {
        	for(int i = 0; i < VIRTUAL_NODES; i++) {
        		String virtualNodeName = str + "&&" + String.valueOf(i);
        		long hash = ServersArray.getHash(virtualNodeName);
        		virtualNodes.put(hash, virtualNodeName);  
        	}
        }
    }
    
    private static String getServer(String str) {
    	long hash = ServersArray.getHash(str);
    	SortedMap<Long,String> subMap = virtualNodes.tailMap(hash);
    	String serverName;
    	String virtualNode;
    	if(subMap.size() > 0) {
    		Long i = subMap.firstKey();
    		virtualNode = subMap.get(i); 
    	}else {
    		virtualNode = virtualNodes.get(virtualNodes.firstKey()); 
    	}
    	System.out.println("Virtual node name is " + virtualNode);
    	return virtualNode.substring(0, virtualNode.indexOf("&&")); 
    }
    
    public static void main(String[] args) {
    	String[] nodes = { "127.0.0.1:1111", "255.255.255.255:9999", "10.211.0.1:3333" };  
        for (int i = 0; i < nodes.length; i++)  
            System.out  
                    .println("[" + nodes[i] + "]的hash值为" + ", 被路由到结点[" + getServer(nodes[i]) + "]");  
	}

}
