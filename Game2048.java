/*
VERSION 1.0
 GAME OF 2048 -CUSTOM GAME LOGIC
CONTAINS THE GUI FOR THE GAME BOARD, KEY LOGIC
NEED TO MAKE SCORE, BEST SCORE, END GAME LOGIC AND ALSO THE LOGIC FOR WHEN NEW NUMBERS ARE ADDED
*/
package gamesfinal;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.*;
public class Game2048 extends JFrame implements KeyListener
{
    JPanel jp=new JPanel();
    JLabel[][] jl=new JLabel[4][4];
    int[][] num;//={{16,0,0,0},{16,0,0,0},{16,0,0,0},{16,0,0,0}};
    ArrayList<Integer> ar;
    HashMap<Integer,Color> color;
    
   private static final Color COLOR_EMPTY = new Color(204, 192, 179);
   private static final Color COLOR_2 = new Color(238, 228, 218);
   private static final Color COLOR_4 = new Color(237, 224, 200);
   private static final Color COLOR_8 = new Color(242, 177, 121);
   private static final Color COLOR_16 = new Color(245, 149, 99);
   private static final Color COLOR_32 = new Color(246, 124, 95);
   private static final Color COLOR_64 = new Color(246, 94, 59);
   private static final Color COLOR_128 = new Color(237, 207, 114);
   private static final Color COLOR_256 = new Color(237, 204, 97);
   private static final Color COLOR_512 =new  Color(237, 200, 80);
   private static final Color COLOR_1024 = new Color(237, 197, 63);
   private static final Color COLOR_2048 = new Color(237, 194, 46);
   private static final Color COLOR_OTHER = Color.BLACK;
    Game2048()
    {
        //to initialize and create the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
        setSize(new Dimension(417,440));
        setLayout(null);
        this.addKeyListener(this);
        this.setLocation(100, 100);
        
        //to fill in the values of ar
        ar=new  ArrayList();
        for(int i=1;i<=16;i++)
        {
            ar.add(i);
        }
        //to create num
        num=new int[4][4]; //CONFIGURE CODE       
        setNum(num,ar);
        
        //to set the color map
        color=new HashMap();
        setColor();
        //to create the jpanel
        jp.setLayout(new GridLayout(4,4,0,0));
        jp.setBounds(0,0,400,400);
        setLabel(jp,jl);
        print();
        //finilize the frame
        setVisible(true);
    }
    public void setColor()
    {
        color.put(0, COLOR_EMPTY);
        color.put(2, COLOR_2);
        color.put(4, COLOR_4);
        color.put(8, COLOR_8);
        color.put(16, COLOR_16);
        color.put(32, COLOR_32);
        color.put(64, COLOR_64);
        color.put(128, COLOR_128);
        color.put(256, COLOR_256);
        color.put(512, COLOR_512);
        color.put(1024, COLOR_1024);
        color.put(2048, COLOR_2048);
    }
     public void setNum(int[][] jb, ArrayList<Integer> ar)//to initialize the starting board
    {
        Collections.shuffle(ar);
        int a=ar.get(0);//CONFIGURE CODE
        int b=ar.get(1);
        int c=ar.get(2);
        for(int i=0;i<4;i++)//creating boxes
        {
            for(int j=0;j<4;j++)
            {
                int num=i*4+j;
                if(num==a || num==b || num==c)
                {
                    jb[i][j]=2;
                }
                else
                {
                    jb[i][j]=0;
                }
            }
        }
    }
    public void setLabel(JPanel jp, JLabel[][] jl)
    {
         this.remove(jp);
         jp.removeAll();
         for(int i=0;i<4;i++)
         {
             for(int j=0;j<4;j++)
             {
                 jl[i][j]=configLabel(i,j);
                 jp.add(jl[i][j]);
             }
         }
         this.add(jp);
         this.revalidate();
     }
    public JLabel configLabel(int x,int y)
    {
        JLabel jb=new JLabel(" ",JLabel.CENTER);
        jb.setBackground(color.get(0));
        if(num[x][y]>2048)
        {
            jb.setBackground(Color.BLACK);
        }
        if(num[x][y]>0)
        {
            jb.setText(Integer.toString(num[x][y]));
            jb.setBackground(color.get(num[x][y]));
        }
        else
        {
            //do nothing
        }
        jb.setOpaque(true);
        jb.setForeground(Color.WHITE);
        jb.setFont(new Font("Ariel",Font.BOLD,30));
        jb.setBorder(BorderFactory.createLineBorder(Color.black));
        jb.setSize(100,100);
        return jb;
    }
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                Game2048 gg=new Game2048();
            }
        });
    }
    public void print()
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                System.out.print(num[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void keyPressed(KeyEvent arg0) 
    {
        int key=arg0.getKeyCode();
        //System.out.println(key);
        switch(key)
        {
            case KeyEvent.VK_LEFT:
            { 
                boolean done=false;
                for(int i=0;i<4;i++)//to get each row of the num and work with each induivisually
                {
                    int temp[]=new int[4];
                    temp[0]=num[i][0];
                    temp[1]=num[i][1];
                    temp[2]=num[i][2];
                    temp[3]=num[i][3];
                    print();
                    int num1=0;//to get the first assigned value
                    int num2=0;//to get the second assigned value
                    int checker=0;//to iteralte through the row
                    int next=0;//to use as the place to assign values
                    OUTER:
                    while(checker<4)
                    {
                        
                        if(num[i][checker]>0)//to get the first number
                        {
                            num1=num[i][checker];
                            ++checker;
                            while(checker<4)
                            {
                                if(num[i][checker]>0)//to get the second number
                                {
                                    num2=num[i][checker];
                                    ++checker;
                                    if(num1==num2)//to check if numbers are equal
                                    {
                                        num[i][next]=num1*2;
                                        num1=0;
                                        num2=0;
                                        next++;
                                        continue OUTER;
                                    }
                                    else
                                    {
                                        num[i][next]=num1;
                                        num[i][++next]=num2;
                                        num1=num2;
                                        num2=0;
                                    }
                                }
                                else
                                {
                                    ++checker;
                                }
                            }
                            if(num2==0 )//the code is going here because num 2 has become 0
                            {
                                num[i][next]=num1;
                                ++next;
                            }
                        }
                        else
                        {
                            ++checker;
                        }
                    }
                    while(next<4)
                    {
                        num[i][next]=0;
                        ++next;
                    }
                    if(temp!=num[i])
                    {
                        done=true;
                    }
                    
                }
                ArrayList<Integer> ak=new ArrayList();;
                for(int i=0;i<4;i++)
                {
                    if(num[i][3]==0)
                    {
                        ak.add(i);
                    }
                }
                if(!ar.isEmpty() && done==true)
                {
                    Collections.shuffle(ak);
                    num[ak.get(0)][3]=2;
                }
                setLabel(jp,jl);
                print();
                break;
            }
            case KeyEvent.VK_RIGHT:
            {
                for(int i=0;i<4;i++)//to get each row of the num and work with each induivisually
                {
                    int num1=0;//to get the first assigned value
                    int num2=0;//to get the second assigned value
                    int checker=3;//to iteralte through the row
                    int next=3;//to use as the place to assign values
                    boolean done=false;
                    OUTER:
                    while(checker>=0)
                    {
                        
                        if(num[i][checker]>0)//to get the first number
                        {
                            num1=num[i][checker];
                            --checker;
                            while(checker>=0)
                            {
                                if(num[i][checker]>0)//to get the second number
                                {
                                    num2=num[i][checker];
                                    --checker;
                                    if(num1==num2)//to check if numbers are equal
                                    {
                                        done=true;
                                        num[i][next]=num1*2;
                                        num1=0;
                                        num2=0;
                                        next--;
                                        continue OUTER;
                                    }
                                    else
                                    {
                                        num[i][next]=num1;
                                        num[i][--next]=num2;
                                        num1=num2;
                                        num2=0;
                                    }
                                }
                                else
                                {
                                    --checker;
                                }
                            }
                            if(num2==0 )//the code is going here because num 2 has become 0
                            {
                                num[i][next]=num1;
                                --next;
                            }
                        }
                        else
                        {
                            --checker;
                        }
                    }
                    while(next>=0)
                    {
                        num[i][next]=0;
                        --next;
                    }
                }
                ArrayList<Integer> ak=new ArrayList();;
                for(int i=0;i<4;i++)
                {
                    if(num[i][0]==0)
                    {
                        ak.add(i);
                    }
                }
                if(!ar.isEmpty())
                {
                    Collections.shuffle(ak);
                    num[ak.get(0)][0]=2;
                }
                setLabel(jp,jl);
                print();
                break;
            }
            case KeyEvent.VK_UP:
            {
                for(int i=0;i<4;i++)//to get each row of the num and work with each induivisually
                {
                    int num1=0;//to get the first assigned value
                    int num2=0;//to get the second assigned value
                    int checker=0;//to iteralte through the row
                    int next=0;//to use as the place to assign values
                    boolean done=false;
                    OUTER:
                    while(checker<4)
                    {
                        
                        if(num[checker][i]>0)//to get the first number
                        {
                            num1=num[checker][i];
                            ++checker;
                            while(checker<4)
                            {
                                if(num[checker][i]>0)//to get the second number
                                {
                                    num2=num[checker][i];
                                    ++checker;
                                    if(num1==num2)//to check if numbers are equal
                                    {
                                        done=true;
                                        num[next][i]=num1*2;
                                        num1=0;
                                        num2=0;
                                        next++;
                                        continue OUTER;
                                    }
                                    else
                                    {
                                        num[next][i]=num1;
                                        num[++next][i]=num2;
                                        num1=num2;
                                        num2=0;
                                    }
                                }
                                else
                                {
                                    ++checker;
                                }
                            }
                            if(num2==0 )//the code is going here because num 2 has become 0
                            {
                                num[next][i]=num1;
                                ++next;
                            }
                        }
                        else
                        {
                            ++checker;
                        }
                    }
                    while(next<4)
                    {
                        num[next][i]=0;
                        ++next;
                    }
                }
                ArrayList<Integer> ak=new ArrayList();;
                for(int i=0;i<4;i++)
                {
                    if(num[3][i]==0)
                    {
                        ak.add(i);
                    }
                }
                if(!ar.isEmpty())
                {
                    Collections.shuffle(ak);
                    num[3][ak.get(0)]=2;
                }
                setLabel(jp,jl);
                print();
                break;
            }
            case KeyEvent.VK_DOWN:
            {
                for(int i=0;i<4;i++)//to get each row of the num and work with each induivisually
                {
                    int num1=0;//to get the first assigned value
                    int num2=0;//to get the second assigned value
                    int checker=3;//to iteralte through the row
                    int next=3;//to use as the place to assign values
                    boolean done=false;
                    OUTER:
                    while(checker>=0)
                    {
                        
                        if(num[checker][i]>0)//to get the first number
                        {
                            num1=num[checker][i];
                            --checker;
                            while(checker>=0)
                            {
                                if(num[checker][i]>0)//to get the second number
                                {
                                    num2=num[checker][i];
                                    --checker;
                                    if(num1==num2)//to check if numbers are equal
                                    {
                                        done=true;
                                        num[next][i]=num1*2;
                                        num1=0;
                                        num2=0;
                                        next--;
                                        continue OUTER;
                                    }
                                    else
                                    {
                                        num[next][i]=num1;
                                        num[--next][i]=num2;
                                        num1=num2;
                                        num2=0;
                                    }
                                }
                                else
                                {
                                    --checker;
                                }
                            }
                            if(num2==0 )//the code is going here because num 2 has become 0
                            {
                                num[next][i]=num1;
                                --next;
                            }
                        }
                        else
                        {
                            --checker;
                        }
                    }
                    while(next>=0)
                    {
                        num[next][i]=0;
                        --next;
                    }
                }
                ArrayList<Integer> ak=new ArrayList();;
                for(int i=0;i<4;i++)
                {
                    if(num[0][i]==0)
                    {
                        ak.add(i);
                    }
                }
                if(!ar.isEmpty())
                {
                    Collections.shuffle(ak);
                    num[0][ak.get(0)]=2;
                }
                setLabel(jp,jl);
                print();
                break;
            }
            default:
            {
                //do nothing
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        //do nothing
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
       //do nothing
    }
}
