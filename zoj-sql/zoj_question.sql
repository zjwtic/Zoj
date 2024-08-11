create table question
(
    id          bigint auto_increment comment 'id'
        primary key,
    title       varchar(512)                       null comment '标题',
    content     text                               null comment '内容/题目描述',
    tags        varchar(1024)                      null comment '标签列表（json 数组）',
    answer      text                               null comment '题目答案',
    submitNum   int      default 0                 not null comment '题目提交数',
    acceptedNum int      default 0                 not null comment '题目通过数',
    judgeCase   text                               null comment '判题用例（json 数组）',
    judgeConfig text                               null comment '判题配置（json 对象）',
    thumbNum    int      default 0                 not null comment '点赞数',
    favourNum   int      default 0                 not null comment '收藏数',
    userId      bigint                             not null comment '创建用户 id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
)
    comment '题目' collate = utf8mb4_unicode_ci;

create index idx_userId
    on question (userId);

INSERT INTO zoj.question (id, title, content, tags, answer, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782224642772643841, '合并两个有序数组', '给你两个按 **非递减顺序** 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。

请你 **合并** nums2 到 nums1 中，使合并后的数组同样按 **非递减顺序** 排列。

**注意**：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。

示例 1：

**输入**：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3

**输出**：[1,2,2,3,5,6]
解释：需要合并 [1,2,3] 和 [2,5,6] 。
合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。

示例 2：

**输入**：nums1 = [1], m = 1, nums2 = [], n = 0

**输出**：[1]
解释：需要合并 [1] 和 [] 。
合并结果是 [1] 。

示例 3：

**输入**：nums1 = [0], m = 0, nums2 = [1], n = 1

**输出**：[1]
解释：需要合并的数组是 [] 和 [1] 。
合并结果是 [1] 。
注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。', '["简单","数组"]', 'class Main {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
    
        // 判断特殊情况
        int len=m+n;
        if(len==0){
            return;
        }
        for (int i = 0; i < n; i++) {
            insert(nums1,m,nums2[i],i);
        }

    }

    private void insert(int[] nums1, int m, int num,int n) {
        int index=0;
       while (index<m+n){
            if(num<nums1[index]){
                break;
            }
            index++;
        }
        move(nums1,m,index,n);
       nums1[index]=num;
    }

    private void move(int[] nums1, int m, int index,int n ) {
        for (int i = m-1+n; i >=index; i--) {
            nums1[i+1]=nums1[i];
        }
    }
}', 1, 0, '[{"input":"[1,2,3,0,0,0] 3 [2,5,6] 3","output":"[1,2,2,3,5,6]"},{"input":"[1] 1 [] 0","output":"[1]"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1782061165294747650, '2024-04-22 09:47:21', '2024-04-22 13:50:22', 0);
INSERT INTO zoj.question (id, title, content, tags, answer, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782236265553960961, '1+1', '输入 1 1  输出2', '["测试"]', 'public class Main{

    public static void main(String[] args) {
    
        System.out.println("2");
        
    }
    
    }', 21, 6, '[{"input":"1 1","output":"2"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1782061165294747650, '2024-04-22 10:33:32', '2024-05-25 09:49:11', 0);
INSERT INTO zoj.question (id, title, content, tags, answer, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782240357810544642, '判断子序列', '给定字符串 **s** 和 **t** ，判断 **s** 是否为 **t** 的子序列。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

**进阶：**

如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？', '["简单","字符串"]', 'public class Main {

    public static void main(String[] args) {
        String s=args[0];
        String t=args[1];
         int tlength=t.length();
         int findindex=0;
         int slength=s.length();
         if(s.equals(""))
        {
             System.out.println("true");
             return;
        }
          if(slength>tlength||t.equals("")){
            System.out.println("false");
             return;
         }

        for (int i = 0; i < tlength; i++) {
            if(t.charAt(i)!=s.charAt(findindex)){
                continue;
            }
            findindex++;
            if(findindex==slength){
                break;
            }
        }
         System.out.println(findindex==slength ? "true" :"false");
         
        return ;
    }
}', 6, 1, '[{"input":"abc ahbgdc","output":"true"},{"input":"axc ahbgdc","output":"false"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1782061165294747650, '2024-04-22 10:49:48', '2024-04-22 10:59:10', 0);
INSERT INTO zoj.question (id, title, content, tags, answer, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782244128879910914, '两数之和', '给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 **和为目标值 target**  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]', '["简单","数字操作"]', 'import java.util.HashMap;

public class Main {

 public  static void  main(String[] args) {
 
      int[] nums=transfer(args[0]);
      int target= Integer.parseInt(args[1]);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 System.out.println(map.get(target - nums[i])+""+i);
                return;
            }
               map.put(nums[i], i);
        }
    }
    
  public static int[] transfer(String str){
  
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            arr[i] = Integer.parseInt(s);
        }
        return arr;
  }  
}', 23, 4, '[{"input":"324 6","output":"12"},{"input":"33 6","output":"01"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1782061165294747650, '2024-04-22 11:04:47', '2024-04-22 16:32:20', 0);
INSERT INTO zoj.question (id, title, content, tags, answer, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782249777370079234, '两数相乘', '给两个数 返回他们的乘积', '["数字操作"]', 'public class Main {

 public  static void  main(String[] args) {
 
    int a= Integer.parseInt(args[0]);
      int b= Integer.parseInt(args[1]);
                 System.out.println(a*b);
                 
        }
    }', 7, 4, '[{"input":"1 1","output":"1"},{"input":"2 4","output":"8"},{"input":"8 9","output":"72"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1782061165294747650, '2024-04-22 11:27:14', '2024-05-01 17:35:19', 0);
INSERT INTO zoj.question (id, title, content, tags, answer, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782250709189238786, '爬楼梯', '假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

示例 1：

**输入**：2
**输出**：2

解释：有两种方法可以爬到楼顶。
1. 1 阶 + 1 阶
2. 2 阶
示例 2：

**输入**：3
**输出**：3

解释：有三种方法可以爬到楼顶。
1. 1 阶 + 1 阶 + 1 阶
2. 1 阶 + 2 阶
3. 2 阶 + 1 阶', '["简单"]', 'public class Main {

    public static void main(String[] args) {
        int n= Integer.parseInt(args[0]);
    if(n<=3){
    System.out.println(n);
    return;
    }
    int f1=2;
    int f2=3;
    for(int i=4;i<=n;i++){
    f2+=f1;
    f1=f2-f1;
    }

    System.out.println(f2);

    }
}', 13, 4, '[{"input":"2","output":"2"},{"input":"3","output":"3"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1782061165294747650, '2024-04-22 11:30:56', '2024-04-22 14:02:39', 0);
INSERT INTO zoj.question (id, title, content, tags, answer, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782252665483927554, '某个数的相反数', '返回所给的数的相反数', '["数字操作"]', 'public class Main {

    public static void main(String[] args) {
        int n= Integer.parseInt(args[0]);
  System.out.println(-n);

}
}', 2, 1, '[{"input":"1","output":"-1"},{"input":"2","output":"-2"},{"input":"3","output":"-3"},{"input":"-5","output":"5"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1782061165294747650, '2024-04-22 11:38:42', '2024-04-22 11:39:55', 0);
INSERT INTO zoj.question (id, title, content, tags, answer, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782263404173041665, '除自身以外数组的乘积', '给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
题目数据 **保证** 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  **32 位** 整数范围内。

请 **不要使用除法**，且在 O(n) 时间复杂度内完成此题。

示例 1:

**输入:** nums = 1234

**输出:** 241286

示例 2:

**输入:** nums = -110-33

**输出:** 00900', '["中等","数组"]', 'import java.util.ArrayList;  
import java.util.List;  
    
    public class Main {
    public static void main(String[] args) {
        int[] nums=convertStringToIntArray(args[0]);
         int length=nums.length;
         int[] answer=new int[length];
         int temp=1;
         answer[0]=1;
         for(int i=1;i<length;i++){
            answer[i]=answer[i-1]*nums[i-1];
         }
                  for(int i=length-1;i>=0;i--){
                 answer[i]=answer[i]*temp;
                 temp*=nums[i];
         }
    String result="";
    for(int i=0;i<answer.length;i++){
        result+=answer[i];
    }
    System.out.print(result);  


    }
         public static int[] convertStringToIntArray(String input) {
            List<Integer> nums = new ArrayList<>();
            Boolean istransfer=false;
            for (char c : input.toCharArray()) {
                if (Character.isDigit(c)) {
                    if (istransfer){
                        nums.add(-(c-''0''));
                        istransfer=false;
                    }
                    else nums.add(c-''0'');
                } else {
                    istransfer=true;
                }
            }

            // 将List转换为数组
            return nums.stream().mapToInt(Integer::intValue).toArray();
        }
}', 13, 6, '[{"input":"1234","output":"241286"},{"input":"-110-33","output":"00900"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1782061165294747650, '2024-04-22 12:21:23', '2024-04-22 14:05:14', 0);
INSERT INTO zoj.question (id, title, content, tags, answer, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782270640039501826, '同构字符串', '给定两个字符串 s 和 t ，判断它们是否是同构的。

如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。

每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。

示例 1:

输入：s = "egg", t = "add"

输出：true

示例 2：

输入：s = "foo", t = "bar"

输出：false

示例 3：

输入：s = "paper", t = "title"

输出：true
 ', '["简单"]', 'public class Main {

       public static void main(String[] args) {
        String s=args[0];
         String t=args[1];
    Character[] arr=new Character[128];
    int slen=s.length();
    int tlen=t.length();
    boolean []isfind=new boolean[128];
    if(slen!=tlen){
        System.out.print("false");  
        return ;
    }
    for (int i = 0; i <slen ; i++) {
        Character sc=s.charAt(i);
        Character tc=t.charAt(i);
        if(arr[sc]==null)
        { 
            if(isfind[tc]){
             System.out.print("false");  
                            return ;
            }
            arr[sc]=tc;
            isfind[tc]=true;
                }
                else {
                    if(!arr[sc].equals(tc)){
                    System.out.print("false");  
            return ;

                }
            }
        }
    System.out.print("true");  
    return ;
    }
}', 1, 1, '[{"input":"egg add","output":"true"},{"input":"foo bar","output":"false"},{"input":"paper title","output":"true"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1782061165294747650, '2024-04-22 12:50:08', '2024-04-22 12:54:26', 0);
INSERT INTO zoj.question (id, title, content, tags, answer, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782272440981364738, '有效的括号', '给定一个只包括 ''(''，'')''，''{''，''}''，''[''，'']'' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

1、
左括号必须用相同类型的右括号闭合。

2、左括号必须以正确的顺序闭合。

3、每个右括号都有一个对应的相同类型的左括号。

示例 1：

**输入**：s = "()"

**输出**：true

示例 2：

**输入**：s = "()[]{}"

**输出**：true

示例 3：

**输入**：s = "(]"

**输出**：false', '["简单","栈"]', 'import java.util.*;

public class Main {

    public static void  main(String[] args) {
        String s=args[0];
        HashMap<Character,Character>hashMap=new HashMap();
        hashMap.put(''('','')'');
        hashMap.put(''{'',''}'');
        hashMap.put(''['','']'');
        Stack<Character>stack=new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if(!stack.isEmpty()&&c.equals(hashMap.get(stack.peek()))){
                stack.pop();
            }
            else {
                stack.push(c);
            }
        }
            System.out.print(stack.isEmpty()?"true":"false");  
    }
}', 8, 6, '[{"input":"()","output":"true"},{"input":"()[]{}","output":"true"},{"input":"(]","output":"false"}]', '{"timeLimit":1000,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1782061165294747650, '2024-04-22 12:57:17', '2024-04-22 14:04:37', 0);
INSERT INTO zoj.question (id, title, content, tags, answer, submitNum, acceptedNum, judgeCase, judgeConfig, thumbNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1785604430040215553, '测试创建题目', '得瑟得瑟', '["使得"]', '***> 上传速度是多少***
', 1, 0, '[{"input":" 1","output":"1"},{"input":"2","output":"2"}]', '{"timeLimit":1001,"memoryLimit":1000,"stackLimit":1000}', 0, 0, 1782061165294747650, '2024-05-01 17:37:25', '2024-05-06 20:24:56', 0);
