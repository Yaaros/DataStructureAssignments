package p5;

import org.testng.annotations.Test;

import java.util.Scanner;
public class TestClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n;
        int i;
        int Obj;
        SqList sql = new SqList();
        System.out.println("是否要对线性表进行插入和删除？（Y/N）");
        while(!(((n = sc.next()).equals("N"))||(n.equals("n")))){
            if(n.equals("Y")||n.equals("y")){
                System.out.println("进行插入还是删除？（1--插入，2--删除）");
                if(sc.next().equals("1")){
                    System.out.println("输入插入位置：");
                    i = sc.nextInt();
                    System.out.println("输入插入元素：");
                    Obj = sc.nextInt();
                    try {
                        sql.insert(i,Obj);
                    } catch (Exception e) {
                        System.out.println("插入位置有误");
                    }
                    System.out.println("当前线性表为:"+sql);
                    System.out.println("是否要对线性表进行插入和删除？（Y/N）");
                } else if (sc.next().equals("2")) {
                    System.out.println("输入删除位置：");
                    i = sc.nextInt();
                    try {
                        sql.remove(i);
                    } catch (Exception e) {
                        System.out.println("删除位置有误");
                    }
                    System.out.println("当前线性表为:"+sql);
                    System.out.println("是否要对线性表进行插入和删除？（Y/N）");
                }else{
                    System.out.println("输入不合法,请输入(1/2)");
                }
            }else{
                System.out.println("输入不合法,请输入(Y/N)");
            }
        }
        System.out.println("对线性表处理完毕");
    }
    /*不要在@Test方法里写Scanner！*/
    @Deprecated
    @Test
    public void TestMethod() {
        Scanner sc = new Scanner(System.in);
        String n;
        int i;
        int Obj;
        ArrayList<Integer> sql = new ArrayList<>();
        System.out.println("是否要对线性表进行插入和删除？（Y/N）");
        while(!(((n = sc.next()).equals("N"))||(n.equals("n")))){
            if(n.equals("Y")||n.equals("y")){
                System.out.println("进行插入还是删除？（1--插入，2--删除）");
                if(sc.next().equals("1")){
                    System.out.println("输入插入位置：");
                    i = sc.nextInt();
                    System.out.println("输入插入元素：");
                    Obj = sc.nextInt();
                    try {
                        sql.insert(i,Obj);
                    } catch (Exception e) {
                        System.out.println("插入位置有误");
                    }
                    System.out.println("当前线性表为:"+sql);
                    System.out.println("是否要对线性表进行插入和删除？（Y/N）");
                } else if (sc.next().equals("2")) {
                    System.out.println("输入删除位置：");
                    i = sc.nextInt();
                    try {
                        sql.remove(i);
                    } catch (Exception e) {
                        System.out.println("删除位置有误");
                    }
                    System.out.println("当前线性表为:"+sql);
                    System.out.println("是否要对线性表进行插入和删除？（Y/N）");
                }else{
                    System.out.println("输入不合法,请输入(1/2)");
                }
            }else{
                System.out.println("输入不合法,请输入(Y/N)");
            }
        }
        System.out.println("对线性表处理完毕");
    }
}
