package p2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CountingNumbers
    /*从一个[1,2001]的正整数n开始，在n的左侧可以放另一个整数m，形成新整数mn
    * m<=n/2.如果有一个k<=m/2,你可以把k放在mn左侧形成新整数kmn,以此类推.
    * 例如，放12到30左侧形成1230，再放6变成61230，...
    * 例如，从8开始，可以形成以下整数：
    * 8，18，28，38，48，128，138，148，248，1248
    * 输入n，输出可以得到的整数个数*/
{
    public static int solution(int n){
        int c = 1;
        if (n != 0) {
            for(int i = n/2;i > 0;i--){
                c += solution(i);
            }
        }
        return c;
    }
    @Test
    public void TestMethod(){
        assertEquals(1,solution(1));
        assertEquals(2,solution(2));
        assertEquals(2,solution(3));
        assertEquals(4,solution(4));
        assertEquals(4,solution(5));
        assertEquals(6,solution(6));
        assertEquals(6,solution(7));
        assertEquals(10,solution(8));
    }
}
