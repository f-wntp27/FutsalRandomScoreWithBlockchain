package util;

import java.util.ArrayList;

import blockchain.Block;
import struct.TeamTable;

public class Constant {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    
    public static ArrayList<TeamTable> teamTables = new ArrayList<TeamTable>();
    public static int homeIndex = 0;
    public static int awayIndex = 0;
    public static int MAX_INDEX = 3;
    public static int TOTAL_ROUND = 12;
}
