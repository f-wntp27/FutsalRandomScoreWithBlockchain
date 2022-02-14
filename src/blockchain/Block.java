package blockchain;

import java.util.Date;

import struct.League;

public class Block {
    private int index;
    private String hash;
    private String previousHash;
    private League data;
    private long timeStamp;

    public Block() {
    
    }
    public Block(int index, League data, String previousHash) {
        this.index = index;
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public Block(Block block) {
        this(block.index, block.data, block.previousHash);
    }

    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
    public String getPreviousHash() {
        return previousHash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    public String getHash() {
        return hash;
    }

    public League getData() {
        return data;
    }
    public void setData(League data) {
        this.data = data;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public long getTimeStamp() {
        return timeStamp;
    }

    public String calculateHash() {
        String calculatedHash = CryptoUtil.sha256(previousHash + Long.toString(timeStamp) + data);
        return calculatedHash;
    }

    public void printBlock() {
        System.out.println("=======================");
        System.out.println("index: " + index);
        // System.out.println("data: " + data);
        this.data.printLeague();
        System.out.println("previousHash: " + previousHash);
        System.out.println("timeStamp: " + timeStamp);
        System.out.println("hash: " + hash);
        System.out.println();
    }
}
