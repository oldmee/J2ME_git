package tetrisGame_01;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class spriteArray {
    private int[][][] spriteArry=new int[28][4][4];
    public spriteArray() {
        //1.png的数字表示
        spriteArry[0]=spriteArry[2]=new int[][]{{1,1,1,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        spriteArry[1]=spriteArry[3]=new int[][]{{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,0,0}};
       //2.png的数字表示
        spriteArry[4]=spriteArry[5]=spriteArry[6]=spriteArry[7]=new int[][]
                {{1,1,0,0},{1,1,0,0},{0,0,0,0},{0,0,0,0}};
       //3.png的数字表示
        spriteArry[8]=new int[][]{{0,1,0,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}};
        spriteArry[9]=new int[][]{{0,1,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}};
        spriteArry[10]=new int[][]{{1,1,1,0},{0,1,0,0},{0,0,0,0},{0,0,0,0}};
        spriteArry[11]=new int[][]{{1,0,0,0},{1,1,0,0},{1,0,0,0},{0,0,0,0}};
       //4.png的数字表示
        spriteArry[12]=new int[][]{{0,0,1,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}};
        spriteArry[13]=new int[][]{{1,1,0,0},{0,1,0,0},{0,1,0,0},{0,0,0,0}};
        spriteArry[14]=new int[][]{{1,1,1,0},{1,0,0,0},{0,0,0,0},{0,0,0,0}};
        spriteArry[15]=new int[][]{{1,0,0,0},{1,0,0,0},{1,1,0,0},{0,0,0,0}};
        //5.png的数字表示
        spriteArry[16]=new int[][]{{1,0,0,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}};
        spriteArry[17]=new int[][]{{0,1,0,0},{0,1,0,0},{1,1,0,0},{0,0,0,0}};
        spriteArry[18]=new int[][]{{1,1,1,0},{0,0,1,0},{0,0,0,0},{0,0,0,0}};
        spriteArry[19]=new int[][]{{1,1,0,0},{1,0,0,0},{1,0,0,0},{0,0,0,0}};
        //6.png的数字表示
        spriteArry[20]=spriteArry[22]=new int[][]{{1,1,0,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}};
        spriteArry[21]=spriteArry[23]=new int[][]{{0,1,0,0},{1,1,0,0},{1,0,0,0},{0,0,0,0}};
        //7.png的数字表示
        spriteArry[24]=spriteArry[26]=new int[][]{{0,1,1,0},{1,1,0,0},{0,0,0,0},{0,0,0,0}};
        spriteArry[25]=spriteArry[27]=new int[][]{{1,0,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}};

    }
    public int[][] getSpriteArray(int whichshape){
     return spriteArry[whichshape];
    }
}
