package com.lv.study.consistentHashMy;

public class ServersArray {
	
	// 2 ^ 31 - 1
	private static final long hashMod = 4294967295l;
	
	 /**  
     * 待添加入Hash环的服务器列表  
     */  
    public static String[] servers = { "192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111",  
            "192.168.0.4:111", "192.168.0.5:111" , "192.168.0.6:111" };
    
    public static long getHash(String str) {
    	final long p = 16777619;  
        long hash =  hashMod;  
        for (int i = 0; i < str.length(); i++){
        	hash = (hash ^ str.charAt(i)) * p;  
        }  
        hash += hash << 13;  
        hash ^= hash >> 7;  
        hash += hash << 3;  
        hash ^= hash >> 17;  
        hash += hash << 5;  
  
        // 如果算出来的值为负数则取其绝对值  
        if (hash < 0)  
            hash = Math.abs(hash);  
        return hash;  
    }

}
