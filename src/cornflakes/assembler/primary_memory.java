package assembler;

import java.util.*;

public class primary_memory {

    //
    Map<Integer, String> mem;
    //private  String[] memory;
    final int stack_start = 268435;//2^28-3-1
    final int heap_start = 168435;//2^28
    final int instruction_start = 0;//no reserved,obvio !!
    public int[] register;
    public String ir, irt;
    public int ra, rb, rm, rx, ry, rz, pc, iv;
    public int rat, rbt, rmt, rxt, ryt, rzt, pct, ivt;
    String n = "00000000000000000000000000000000";
    int cache_size = 2;
    int block_size = 2;
    int cache_type = 3;
    int no_sets = 2;
    Cache cache;

    public static int binlog(int bits) // returns 0 for bits=0
    {
        int log = 0;
        if ((bits & 0xffff0000) != 0) {
            bits >>>= 16;
            log = 16;
        }
        if (bits >= 256) {
            bits >>>= 8;
            log += 8;
        }
        if (bits >= 16) {
            bits >>>= 4;
            log += 4;
        }
        if (bits >= 4) {
            bits >>>= 2;
            log += 2;
        }
        return log + (bits >>> 1);
    }

    public int parseint(String binaryInt, int t) {
        //Check if the number is negative.
        //We know it's negative if it starts with a 1
        if (binaryInt.charAt(0) == '1') {
            //Call our invert digits method
            String invertedInt = invertDigits(binaryInt);
            //Change this to decimal format.
            int decimalValue = Integer.parseInt(invertedInt, 2);
            //Add 1 to the curernt decimal and multiply it by -1
            //because we know it's a negative number
            decimalValue = (decimalValue + 1) * -1;
            //return the final result
            return decimalValue;
        } else {
            //Else we know it's a positive number, so just convert
            //the number to decimal base.
            return Integer.parseInt(binaryInt, 2);
        }
    }

    public String invertDigits(String binaryInt) {
        String result = binaryInt;
        result = result.replace("0", " "); //temp replace 0s
        result = result.replace("1", "0"); //replace 1s with 0s
        result = result.replace(" ", "1"); //put the 1s back in
        return result;
    }

    //the above values are chosen arbitrarily
    public primary_memory() {
        /*memory=new String[268435]; //max-memory=2^28;
        for(int i=0;i< 268435;i++) memory[i]="00000000";*/
        register = new int[32];
        mem = new HashMap<>();
        cache = new Cache();
        for (int i = 0; i < 32; i++) {
            register[i] = 0;
        }

        register[2] = stack_start;
        register[3] = heap_start;
        pc = ra = rb = rx = ry = rz = 0;
        ///////////////////////////////
        //memory[heap_start]="00000001";
        ///////////////////////////////
    }

    public class Cache {

        public class block {

            int tag;
            boolean valid;
            String[] bytes;

            block() {
                tag = 0;
                valid = false;
                bytes = new String[block_size * 4];
            }
        }
        block[] cache_dm = new block[cache_size];
        Deque<block> dq_fas = new LinkedList();
        HashSet<Integer> tag_fas;

        Cache() {
            this.tag_fas = new HashSet<>();
            for (int i = 0; i < cache_size; i++) {
                cache_dm[i] = new block();
            }
        }
        int off, tag, index;

        public void calculate_addr(int num) {
            String bin_line = Integer.toBinaryString(num);
            String temp = "";
            for (int i = 0; i < 32 - bin_line.length(); i++) {
                if (num >= 0) {
                    temp = temp + "0";
                } else {
                    temp = temp + "1";
                }
            }
            bin_line = temp + bin_line;
            //checking type of cache
            if (cache_type == 0) {
                //int block_addr = addr / (block_size * 4);
                //int index = block_addr % cache_size;

                int n = binlog(block_size * 4);
                int m = binlog(cache_size);
                off = Integer.parseInt(bin_line.substring(32 - n, 32), 2);
                index = Integer.parseInt(bin_line.substring(32 - n - m, 32 - n), 2);
                tag = Integer.parseInt(bin_line.substring(0, 32 - n - m), 2);

            } else if (cache_type == 1) {
                int n = binlog(block_size * 4);
                off = Integer.parseInt(bin_line.substring(32 - n, 32), 2);
            }
        }

        public block f_LRU() {
            if (!tag_fas.contains(tag)) {
                if (dq_fas.size() == cache_size) {
                    block temp = dq_fas.removeLast();
                    tag_fas.remove(temp.tag);
                }
                return null;
            } else {
                int i = 0;
                block temp = new block();
                Iterator<block> itr = dq_fas.iterator();
                while (itr.hasNext()) {
                    temp = itr.next();
                    if (temp.tag == tag) {
                        break;
                    }
                    i++;
                }
                dq_fas.remove(temp);
                return temp;
            }
        }

        public void storebytestr(int addr, String byte_in) {
            calculate_addr(addr);
            //mem.put(addr, byte_in.substring(0, 8));
            if (cache_type == 0) {
                if (tag == cache_dm[index].tag && cache_dm[index].valid) {
                    cache_dm[index].bytes[off] = byte_in.substring(0, 8);
                } else {
                    int n = binlog(block_size * 4);
                    int bloc_addr = addr << (32 - n);
                    bloc_addr = addr >> (32 - n);
                    for (int i = 0; i < block_size * 4; i++) {
                        cache_dm[index].bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        cache_dm[index].tag = tag;
                        cache_dm[index].valid = true;
                    }
                }
            } else if (cache_type == 1) {
                block temp ;
                temp = f_LRU();
                if (temp != null) {
                    temp.bytes[off] = byte_in.substring(0, 8);
                } else {
                    temp=new block();
                    for (int i = 0; i < block_size * 4; i++) {
                        temp.bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        temp.tag = tag;
                        temp.valid = true;
                    }
                }

            }
        }

        public void storewordstr(int addr, String word_in) {
            calculate_addr(addr);
            if (cache_type == 0) {
                if (tag == cache_dm[index].tag && cache_dm[index].valid) {
                    cache_dm[index].bytes[off] = word_in.substring(24, 32);
                    cache_dm[index].bytes[off + 1] = word_in.substring(16, 24);
                    cache_dm[index].bytes[off + 2] = word_in.substring(8, 16);
                    cache_dm[index].bytes[off + 3] = word_in.substring(0, 8);
                } else {
                    int n = binlog(block_size * 4);
                    int bloc_addr = addr << (32 - n);
                    bloc_addr = addr >> (32 - n);
                    for (int i = 0; i < block_size * 4; i++) {
                        cache_dm[index].bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        cache_dm[index].tag = tag;
                        cache_dm[index].valid = true;
                    }
                }
            } else if (cache_type == 1) {
                block temp = f_LRU();
                if (temp != null) {
                    temp.bytes[off] = word_in.substring(24, 32);
                    temp.bytes[off + 1] = word_in.substring(16, 24);
                    temp.bytes[off + 2] = word_in.substring(8, 16);
                    temp.bytes[off + 3] = word_in.substring(0, 8);
                } else {
                    temp=new block();
                    for (int i = 0; i < block_size * 4; i++) {
                        temp.bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        temp.tag = tag;
                        temp.valid = true;
                    }
                }
            }
            //System.out.println(memory[addr + 3] + memory[addr + 2] + memory[addr + 1] + memory[addr]+"-");
        }

        public void storehalfstr(int addr, String half_in) {
            calculate_addr(addr);
            if (cache_type == 0) {
                if (tag == cache_dm[index].tag && cache_dm[index].valid) {
                    cache_dm[index].bytes[off + 1] = half_in.substring(0, 8);
                    cache_dm[index].bytes[off] = half_in.substring(8, 16);
                } else {
                    int n = binlog(block_size * 4);
                    int bloc_addr = addr << (32 - n);
                    bloc_addr = addr >> (32 - n);
                    for (int i = 0; i < block_size * 4; i++) {
                        cache_dm[index].bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        cache_dm[index].tag = tag;
                        cache_dm[index].valid = true;
                    }
                }
            } else if (cache_type == 1) {
                block temp = f_LRU();
                if (temp != null) {
                    temp.bytes[off] = half_in.substring(8, 16);
                    temp.bytes[off + 1] = half_in.substring(0, 8);

                } else {
                    temp=new block();
                    for (int i = 0; i < block_size * 4; i++) {
                        temp.bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        temp.tag = tag;
                        temp.valid = true;
                    }
                }
            }

        }

        public String loadbytestr(int addr) {
            calculate_addr(addr);
            if (cache_type == 0) {
                if (tag == cache_dm[index].tag && cache_dm[index].valid) {
                    return cache_dm[index].bytes[off];
                } else {
                    int n = binlog(block_size * 4);
                    int bloc_addr = addr << (32 - n);
                    bloc_addr = addr >> (32 - n);
                    for (int i = 0; i < block_size * 4; i++) {
                        cache_dm[index].bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        cache_dm[index].tag = tag;
                        cache_dm[index].valid = true;
                    }
                }
            } else if (cache_type == 1) {
                block temp ;
                temp = f_LRU();
                if (temp != null) {
                    return temp.bytes[off];
                } else {
                    temp=new block();
                    for (int i = 0; i < block_size * 4; i++) {
                        temp.bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        temp.tag = tag;
                        temp.valid = true;
                    }
                }

            }

            return mem.getOrDefault(addr, n);
        }

        public String loadhalfstr(int addr) {
            calculate_addr(addr);
            if (cache_type == 0) {
                if (tag == cache_dm[index].tag && cache_dm[index].valid) {
                    return cache_dm[index].bytes[off + 1] + cache_dm[index].bytes[off + 1];
                } else {
                    int n = binlog(block_size * 4);
                    int bloc_addr = addr << (32 - n);
                    bloc_addr = addr >> (32 - n);
                    for (int i = 0; i < block_size * 4; i++) {
                        cache_dm[index].bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        cache_dm[index].tag = tag;
                        cache_dm[index].valid = true;
                    }
                }
            }
            else if (cache_type == 1) {
                block temp ;
                temp = f_LRU();
                if (temp != null) {
                    return temp.bytes[off]+temp.bytes[off+1];
                } else {
                    temp=new block();
                    for (int i = 0; i < block_size * 4; i++) {
                        temp.bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        temp.tag = tag;
                        temp.valid = true;
                    }
                }

            }
            if (!mem.containsKey(addr)) {
                return n;
            }
            return mem.get(addr + 1) + mem.get(addr);
        }

        public String loadwordstr(int addr) {

            calculate_addr(addr);
            if (cache_type == 0) {
                if (tag == cache_dm[index].tag && cache_dm[index].valid) {
                    return cache_dm[index].bytes[off + 3] + cache_dm[index].bytes[off + 2] + cache_dm[index].bytes[off + 1] + cache_dm[index].bytes[off];
                } else {
                    int n = binlog(block_size * 4);
                    int bloc_addr = addr << (32 - n);
                    bloc_addr = addr >> (32 - n);
                    for (int i = 0; i < block_size * 4; i++) {
                        cache_dm[index].bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        cache_dm[index].tag = tag;
                        cache_dm[index].valid = true;
                    }

                }
            }
            else if (cache_type == 1) {
                block temp ;
                temp = f_LRU();
                if (temp != null) {
                    return temp.bytes[off]+temp.bytes[off+1]+temp.bytes[off+2]+temp.bytes[off+3];
                } else {
                    temp=new block();
                    for (int i = 0; i < block_size * 4; i++) {
                        temp.bytes[i] = mem.getOrDefault(addr + i, "00000000");
                        calculate_addr(addr + i);
                        temp.tag = tag;
                        temp.valid = true;
                    }
                }

            }
            if (!mem.containsKey(addr)) {
                return n;
            }
            boolean d = mem.containsKey(addr);
            String a = mem.get(addr + 3) + mem.get(addr + 2) + mem.get(addr + 1) + mem.get(addr);
            return a;
        }

    }

    public void set_primary_memory() {
        /*memory=new String[268435]; //max-memory=2^28;
        for(int i=0;i< 268435;i++) memory[i]="00000000";*/
        //register = new int[32];
        mem = new HashMap<>();
        cache = new Cache();
        for (int i = 0; i < 32; i++) {
            register[i] = 0;
        }

        register[2] = stack_start;
        register[3] = heap_start;
        pc = ra = rb = rx = ry = rz = 0;
        //mem.clear();
        ///////////////////////////////
        //memory[heap_start]="00000001";
        ///////////////////////////////
    }
    //////////////////////////////////////////////////////////////////
    //  input is binary string  //

    public String loadbytestr(int addr) {

//        return mem.getOrDefault(addr, n);
        return cache.loadbytestr(addr);

    }

    public String loadhalfstr(int addr) {
//        if (!mem.containsKey(addr)) {
//            return n;
//        }
//        return mem.get(addr + 1) + mem.get(addr);
        return cache.loadhalfstr(addr);
    }

    public String loadwordstr(int addr) {

        // System.out.println(addr+memory[addr+3]+memory[addr+2]+memory[addr+1]+memory[addr]);
//        if (!mem.containsKey(addr)) {
//            return n;
//        }
//        boolean d = mem.containsKey(addr);
//        String a = mem.get(addr + 3) + mem.get(addr + 2) + mem.get(addr + 1) + mem.get(addr);
//        return a;
        return cache.loadwordstr(addr);
    }

    public void storebytestr(int addr, String byte_in) {
        //cache.calculate_addr(addr, byte_in);

        mem.put(addr, byte_in.substring(0, 8));
        cache.storebytestr(addr, byte_in);
        //memory[addr]=byte_in.substring(0,8);

    }

    public void storewordstr(int addr, String word_in) {
        //little endian//
        //System.out.println(word_in);
//        memory[addr]  =word_in.substring(24,32);
//        memory[addr+1]=word_in.substring(16,24);
//        memory[addr+2]=word_in.substring(8 ,16);
//        memory[addr+3]=word_in.substring(0 , 8);
        mem.put(addr + 3, word_in.substring(0, 8));
        mem.put(addr + 2, word_in.substring(8, 16));
        mem.put(addr + 1, word_in.substring(16, 24));
        mem.put(addr + 0, word_in.substring(24, 32));
        cache.storewordstr(addr, word_in);

        //System.out.println(memory[addr + 3] + memory[addr + 2] + memory[addr + 1] + memory[addr]+"-");
    }

    public void storehalfstr(int addr, String half_in) {
        mem.put(addr + 1, half_in.substring(0, 8));
        mem.put(addr, half_in.substring(8, 16));
//        memory[addr]=half_in.substring(8 ,16);
//        memory[addr+1]=half_in.substring(0 , 8);
        cache.storehalfstr(addr, half_in);
    }

    ///////////////////////////// output is binary string /////////
    public int loadbyte(int addr) {

        int itemp = parseint(cache.loadbytestr(addr), 2);
        return itemp;
    }

    public int loadhalf(int addr) {

//        String rets = mem.get(addr + 1) + mem.get(addr);
        int itemp = parseint(cache.loadhalfstr(addr), 2);
        return itemp;
    }

    public int loadword(int addr) {

        //System.out.println(addr+memory[addr+3]+memory[addr+2]+memory[addr+1]+memory[addr]);
//        String rets = mem.get(addr + 3) + mem.get(addr + 2) + mem.get(addr + 1) + mem.get(addr);
        int itemp = parseint(cache.loadwordstr(addr), 2);
        ////////////////////////////////////
        //System.out.println(itemp+":"+addr+":"+rets);
        ////////////////////////////////////
        return itemp;
    }

    public void storebyte(int addr, int num) {
        String bin_line = Integer.toBinaryString(num);
        String temp = "";
        for (int i = 0; i < 8 - bin_line.length(); i++) {
            if (num >= 0) {
                temp = temp + "0";
            } else {
                temp = temp + "1";
            }
        }
        bin_line = temp + bin_line;
        bin_line = bin_line.substring(bin_line.length() - 8, bin_line.length());//15
        mem.put(addr, bin_line.substring(0, 8));
        cache.storebytestr(addr, bin_line);

    }

    public void storeword(int addr, int num) {
        // little endian//
        // System.out.println(word_in);
        String bin_line = Integer.toBinaryString(num);
        String temp = "";
        for (int i = 0; i < 32 - bin_line.length(); i++) {
            if (num >= 0) {
                temp = temp + "0";
            } else {
                temp = temp + "1";
            }
        }
        bin_line = temp + bin_line;
        bin_line = bin_line.substring(bin_line.length() - 32, bin_line.length());
        mem.put(addr + 3, bin_line.substring(0, 8));
        mem.put(addr + 2, bin_line.substring(8, 16));
        mem.put(addr + 1, bin_line.substring(16, 24));
        mem.put(addr + 0, bin_line.substring(24, 32));
        // System.out.println(memory[addr + 3] + memory[addr + 2] + memory[addr + 1] +
        // memory[addr]+"-");
        cache.storewordstr(addr, bin_line);

    }

    public void storehalf(int addr, int num) {
        String bin_line = Integer.toBinaryString(num);
        String temp = "";
        for (int i = 0; i < 16 - bin_line.length(); i++) {
            if (num >= 0) {
                temp = temp + "0";
            } else {
                temp = temp + "1";
            }
        }
        bin_line = temp + bin_line;
        bin_line = bin_line.substring(bin_line.length() - 16, bin_line.length());

        mem.put(addr + 1, bin_line.substring(0, 8));
        mem.put(addr, bin_line.substring(8, 16));
        cache.storehalfstr(addr, bin_line);
    }

    ///////////////////////////// output is binary string /////////
}
