package p9;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

public class Problem {
    public String Translate(String str){
        String[] strs = str.split("");
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if(strs[i].equals("B")){
                ans.append("tsyedsye");
            } else if (strs[i].equals("A")) {
                ans.append("sye");
            } else if(strs[i].equals("(")){
                int k = i+1;
                String flag = strs[k];
                while(k<strs.length&&!strs[k].equals(")")){
                    k++;
                }
                int initial = i+2;
                //更新i使之不要遍历右括号,i不是k+1,这是因为循环会自动往后+1
                i = k;
                int last = k-1;
                //存放括号内容转化的字符串数组
                String[] array = new String[2*(last-initial+1)+1];
                array[0] = "e";
                for (int j = 1; j < array.length; j+=2) {
                    array[j] = strs[last];
                    array[j+1] = "e";
                    last--;
                }
                for (String s : array) {
                    ans.append(s);
                }
            } else {
                ans.append(strs[i]);
            }
        }
        return ans.toString();
    }

    /**
     *
     * @param str:输入字符串
     * @param hashMap:大写字母的翻译方法
     *        Key:大写字母
     *        Value:翻译过后的字符串
     * @return ans:翻译后的语言
     */
    public String Translate(String str, HashMap<String,String> hashMap){
        String[] strs = str.split("");
        Set<String> keys = hashMap.keySet();
        StringBuilder ans = new StringBuilder();
        boolean whetherInsert = true;
        int l = strs.length;
        for (int i = 0; i < l; i++) {
            String temp = strs[i];
            if(temp.equals("(")){
                whetherInsert = false;
                int k = i+1;
                String head = strs[i+1];
                while(k<l&&!strs[k].equals(")")){
                    k++;
                }
                int initial = i+2;
                //更新i使之不要遍历右括号,i不是k+1,这是因为循环会自动往后+1
                i = k;
                int last = k-1;
                //存放括号内容转化的字符串数组
                String[] array = new String[2*(last-initial+1)+1];
                array[0] = head;
                for (int j = 1; j < array.length; j+=2) {
                    array[j] = strs[last];
                    array[j+1] = head;
                    last--;
                }
                for (String s : array) {
                    ans.append(s);
                }
            }
            while (containsKey(temp,keys)){
                String[] tempList = temp.split("");
                for (int j = 0; j < tempList.length; j++) {
                    if(hashMap.containsKey(tempList[j])){
                        tempList[j] = hashMap.get(temp);
                    }
                }
                temp = getString(tempList);
                System.out.println(temp);
            }
            if(whetherInsert){
                ans.append(strs[i]);
            }
            System.out.println(i);
        }
        return ans.toString();
    }

    private boolean containsUpperCase(String str){
        String[] strs = str.split("");
        for (String s : strs) {
            if(s.charAt(0)>=65&&s.charAt(0)<=90){
                return true;
            }
        }
        return false;
    }
    private boolean containsKey(String str,Set<String> keySet){
        String[] strs = str.split("");
        for (String s : strs) {
            if(keySet.contains(s)){
                return true;
            }
        }
        return false;
    }
    private String getString(String[] strs){
        StringBuilder stb = new StringBuilder();
        for (String s : strs) {
            stb.append(s);
        }
        return stb.toString();
    }
    @Test
    public void Test(){
        //System.out.println(Character.isUpperCase("A".charAt(0)));
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("B","tAdA");
        hashMap.put("A","sye");
        System.out.println(Translate("B(ehnxgz)B",hashMap));
        //System.out.println(Translate("B(ehansai)A"));
        //System.out.println(Arrays.toString("A".split("")));
        //System.out.println(containsUpperCase("acA"));
    }
}
