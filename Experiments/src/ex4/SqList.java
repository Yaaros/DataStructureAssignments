package ex4;

public class SqList {
    private Object[]  list;
    private int curLen;
    private boolean isChangeable = false;
    public SqList(){
        isChangeable = true;
        list = new Object[65536];
    }
    public SqList(int maxSize){
        curLen = 0;
        list = new Object[maxSize];
    }
    public SqList(Object[] arr){
        curLen = arr.length;
        list = arr;
    }
    /*清空当前表
    注意：因为书上想把线性表做改变的方法只有唯一一个，就是插入操作insert
    curLen是唯一长度标识
    因此当我把curLen置为0后，外面得到的就是空表信息*/
    public void clear(){
        curLen = 0;
    }
    public boolean isEmpty(){
        return curLen == 0;
    }
    public int length(){
        return curLen;
    }
    private Exception Illegal1(int index){
        return new Exception(String.format("第 %d 个元素不存在",index));
    }
    public Object get(int index) throws Exception{
        if(index<0||index>curLen - 1){
            throw Illegal1(index);
        }
        return list[index];
    }
    //插入
    public void insert(int index,Object x) throws Exception{
        if(curLen== list.length){
            throw new Exception("顺序表已满");
        }
        if(index<0||index>curLen){
            throw new Exception("插入位置不合法");
        }
        /*这里这个手动数组复制可以改成C写成的System.arraycopy()方法*/
        for(int j = curLen;j>index;j--){
            list[j]= list[j-1];
        }
        list[index] = x;
        curLen++;
    }
    public void remove(int index) throws Exception{
        if(index<0||index>curLen-1){
            throw new Exception("删除位置不合法");
        }
        for(int j = index;j<=curLen;j++){
            list[j] = list[j+1];
        }
        curLen--;
    }
    public int indexOf(Object x){
        int j = 0;
        while(j<curLen&&!list[j].equals(x)){
            j++;
        }
        return j<curLen ? j : -1;
    }
    public boolean contains(Object x){
        return !(indexOf(x)==-1);
    }
    public String toString(){
        StringBuilder stb = new StringBuilder();
        stb.append("(");
        if(!isEmpty()){
            for(int i = 0;i<curLen;i++){
                stb.append(list[i]);
                if(!(i==curLen-1)){
                    stb.append(",");
                }
            }
        }
        stb.append(")");
        return stb.toString();
    }
}
