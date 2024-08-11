create table question_submit
(
    id         bigint auto_increment comment 'id'
        primary key,
    language   varchar(128)                       not null comment '编程语言',
    code       text                               not null comment '用户代码',
    judgeInfo  text                               null comment '判题信息（json 对象）',
    status     int      default 0                 not null comment '判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）',
    questionId bigint                             not null comment '题目 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '题目提交';

create index idx_questionId
    on question_submit (questionId);

create index idx_userId
    on question_submit (userId);

INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782237188825128961, 'java', 'class Main {
    public void main(String[] args) {
    
 System.out.println(args[0]+args[1]);

    }
}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782236265553960961, 1782061165294747650, '2024-04-22 10:37:12', '2024-04-22 10:37:14', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782237354231701506, 'java', 'class Main {
    public void main(String[] args) {
    
 System.out.println(args[1]+args[2]);

    }
}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782236265553960961, 1782061165294747650, '2024-04-22 10:37:52', '2024-04-22 10:37:52', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782237420233269249, 'java', 'class Main {
    public void main(String[] args) {
    
 System.out.println("2");

    }
}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782236265553960961, 1782061165294747650, '2024-04-22 10:38:08', '2024-04-22 10:38:08', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782238856916606977, 'java', 'public class Main{
    public static void main(String[] args) {
        int result=0;
        for(int i=0;i<args.length;i++){
            result+=Integer.parseInt(args[i]);     
        }          
        System.out.println(result);
    }}', '{"message":"成功","time":92,"memory":0}', 2, 1782236265553960961, 1782061165294747650, '2024-04-22 10:43:50', '2024-04-22 10:43:51', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782239027817717761, 'java', 'public class Main{
    public static void main(String[] args) {
        // int result=0;
        // for(int i=0;i<args.length;i++){
        //     result+=Integer.parseInt(args[i]);     
        // }          
        System.out.println("2");
    }}', '{"message":"成功","time":92,"memory":0}', 2, 1782236265553960961, 1782061165294747650, '2024-04-22 10:44:31', '2024-04-22 10:44:31', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782239239747510273, 'java', 'class Main {
    public void main(String[] args) {
    
 System.out.println("2");

    }
}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782236265553960961, 1782061165294747650, '2024-04-22 10:45:21', '2024-04-22 10:45:22', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782239299361153025, 'java', 'class Main {
    public void main(String[] args) {
    s
 System.out.println("2");

    }
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782236265553960961, 1782061165294747650, '2024-04-22 10:45:36', '2024-04-22 10:45:36', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782239403019182081, 'java', 'public class Main{
    public static void main(String[] args) {   
        System.out.println("2");
    }}', '{"message":"成功","time":91,"memory":0}', 2, 1782236265553960961, 1782061165294747650, '2024-04-22 10:46:00', '2024-04-22 10:46:01', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782240944056803329, 'java', 'class Main {
    public boolean main(String[] args) {
        s=args[0]
        t=args[1]
         int tlength=t.length();
         int findindex=0;
         int slength=s.length();
         if(s.equals(""))
        {
            return true;
        }
          if(slength>tlength||t.equals("")){
             return false;
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
        return findindex==slength;
    }
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782240357810544642, 1782061165294747650, '2024-04-22 10:52:08', '2024-04-22 10:52:08', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782241049975562242, 'java', 'public class Main {
    public static boolean main(String[] args) {
        s=args[0]
        t=args[1]
         int tlength=t.length();
         int findindex=0;
         int slength=s.length();
         if(s.equals(""))
        {
            return true;
        }
          if(slength>tlength||t.equals("")){
             return false;
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
        return findindex==slength;
    }
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782240357810544642, 1782061165294747650, '2024-04-22 10:52:33', '2024-04-22 10:52:33', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782241127662460930, 'java', 'public class Main {
    public static boolean main(String[] args) {
        s=args[0];
        t=args[1];
         int tlength=t.length();
         int findindex=0;
         int slength=s.length();
         if(s.equals(""))
        {
            return true;
        }
          if(slength>tlength||t.equals("")){
             return false;
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
        return findindex==slength;
    }
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782240357810544642, 1782061165294747650, '2024-04-22 10:52:51', '2024-04-22 10:52:52', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782241282025431042, 'java', 'public class Main {
    public static boolean main(String[] args) {
        String s=args[0];
        String t=args[1];
         int tlength=t.length();
         int findindex=0;
         int slength=s.length();
         if(s.equals(""))
        {
            return true;
        }
          if(slength>tlength||t.equals("")){
             return false;
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
        return findindex==slength;
    }
}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782240357810544642, 1782061165294747650, '2024-04-22 10:53:28', '2024-04-22 10:53:29', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782241615665537025, 'java', 'public class Main {
    public static String main(String[] args) {
        String s=args[0];
        String t=args[1];
         int tlength=t.length();
         int findindex=0;
         int slength=s.length();
         if(s.equals(""))
        {
            return "true";
        }
          if(slength>tlength||t.equals("")){
             return "false";
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
        return findindex==slength ? "true" :"false";
    }
}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782240357810544642, 1782061165294747650, '2024-04-22 10:54:48', '2024-04-22 10:54:48', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782242309181120513, 'java', 'public class Main {
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
}', '{"message":"成功","time":95,"memory":0}', 2, 1782240357810544642, 1782061165294747650, '2024-04-22 10:57:33', '2024-04-22 10:57:34', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782245660702646273, 'java', 'public class Main {
 public void  main(String[] args) {
      int[] nums=transfer(args[0])
      int target= Integer.parseInt(args[1])
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 System.out.println(i+""+map.get(target - nums[i]));
                return;
            }
               map.put(nums[i], i);
        }
        return null;
    }
  public int[] transfer(String str){
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            arr[i] = Integer.parseInt(s);
        }
        return arr;
  }  
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782244128879910914, 1782061165294747650, '2024-04-22 11:10:52', '2024-04-22 11:10:53', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782245849979002881, 'java', 'public class Main {
 public void static main(String[] args) {
      int[] nums=transfer(args[0]);
      int target= Integer.parseInt(args[1]);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 System.out.println(i+""+map.get(target - nums[i]));
                return;
            }
               map.put(nums[i], i);
        }
        return null;
    }
  public int[] transfer(String str){
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            arr[i] = Integer.parseInt(s);
        }
        return arr;
  }  
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782244128879910914, 1782061165294747650, '2024-04-22 11:11:37', '2024-04-22 11:11:38', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782246053864120322, 'java', 'public class Main {
 public static void  main(String[] args) {
      int[] nums=transfer(args[0]);
      int target= Integer.parseInt(args[1]);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 System.out.println(i+""+map.get(target - nums[i]));
                return;
            }
               map.put(nums[i], i);
        }
        return null;
    }
  public int[] transfer(String str){
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            arr[i] = Integer.parseInt(s);
        }
        return arr;
  }  
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782244128879910914, 1782061165294747650, '2024-04-22 11:12:26', '2024-04-22 11:12:26', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782246126865981442, 'java', 'public class Main {
 public static void  main(String[] args) {
      int[] nums=transfer(args[0]);
      int target= Integer.parseInt(args[1]);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 System.out.println(i+""+map.get(target - nums[i]));
                return;
            }
               map.put(nums[i], i);
        }
    }
  public int[] transfer(String str){
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            arr[i] = Integer.parseInt(s);
        }
        return arr;
  }  
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782244128879910914, 1782061165294747650, '2024-04-22 11:12:43', '2024-04-22 11:12:44', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782246467539935233, 'java', 'import java.util.HashMap;

public class Main {
 public static void  main(String[] args) {
      int[] nums=transfer(args[0]);
      int target= Integer.parseInt(args[1]);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 System.out.println(i+""+map.get(target - nums[i]));
                return;
            }
               map.put(nums[i], i);
        }
    }
  public int[] transfer(String str){
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            arr[i] = Integer.parseInt(s);
        }
        return arr;
  }  
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782244128879910914, 1782061165294747650, '2024-04-22 11:14:05', '2024-04-22 11:14:05', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782246901038030850, 'java', 'import java.util.HashMap;

public class Main {
 public  void  main(String[] args) {
      int[] nums=transfer(args[0]);
      int target= Integer.parseInt(args[1]);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 System.out.println(i+""+map.get(target - nums[i]));
                return;
            }
               map.put(nums[i], i);
        }
    }
  public int[] transfer(String str){
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            arr[i] = Integer.parseInt(s);
        }
        return arr;
  }  
}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782244128879910914, 1782061165294747650, '2024-04-22 11:15:48', '2024-04-22 11:15:49', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782247038435041281, 'java', 'import java.util.HashMap;

public class Main {
 public  static void  main(String[] args) {
      int[] nums=transfer(args[0]);
      int target= Integer.parseInt(args[1]);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 System.out.println(i+""+map.get(target - nums[i]));
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
}', '{"message":"答案错误","time":95,"memory":0}', 2, 1782244128879910914, 1782061165294747650, '2024-04-22 11:16:21', '2024-04-22 11:16:21', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782247638493143041, 'java', 'import java.util.HashMap;

public class Main {
 public  static void  main(String[] args) {
      int[] nums=transfer(args[0]);
      int target= Integer.parseInt(args[1]);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 System.out.println(i+""+map.get(target - nums[i]));
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
}', '{"message":"答案错误","time":98,"memory":0}', 2, 1782244128879910914, 1782061165294747650, '2024-04-22 11:18:44', '2024-04-22 11:18:44', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782248637790265346, 'java', 'import java.util.HashMap;

public class Main {
 public  static void  main(String[] args) {
      int[] nums=transfer(args[0]);
      int target= Integer.parseInt(args[1]);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 System.out.println(i+""+map.get(target - nums[i]));
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
}', '{"message":"答案错误","time":93,"memory":0}', 2, 1782244128879910914, 1782061165294747650, '2024-04-22 11:22:42', '2024-04-22 11:23:47', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782248930452021249, 'java', 'import java.util.HashMap;

public class Main {
 public  static void  main(String[] args) {
      int[] nums=transfer(args[0]);
      int target= Integer.parseInt(args[1]);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                 System.out.println(map.get(target - nums[i]+""+i));
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
}', '{"message":"答案错误","time":174,"memory":0}', 2, 1782244128879910914, 1782061165294747650, '2024-04-22 11:23:52', '2024-04-22 11:24:02', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782249094264758274, 'java', 'import java.util.HashMap;

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
}', '{"message":"成功","time":170,"memory":0}', 2, 1782244128879910914, 1782061165294747650, '2024-04-22 11:24:31', '2024-04-22 11:24:34', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782250067418783745, 'java', 'public class Main {
 public  static void  main(String[] args) {
    int a= Integer.parseInt(args[0]);
      int b= Integer.parseInt(args[1]);
                 System.out.println(a*b);
        }
    }', '{"message":"成功","time":91,"memory":0}', 2, 1782249777370079234, 1782061165294747650, '2024-04-22 11:28:23', '2024-04-22 11:28:28', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782251238317465602, 'java', 'public class Main {
    public void main(String[] args) {
        int n= Integer.parseInt(args[0]);
if(n<=3){
    return n;
}
int f1=2;
int f2=3;
for(int i=4;i<=n;i++){
  f2+=f1;
 f1=f2-f1;
}

System.out.println(f2);

}
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782250709189238786, 1782061165294747650, '2024-04-22 11:33:02', '2024-04-22 11:33:05', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782251419704336385, 'java', 'public class Main {
    public void main(String[] args) {
        int n= Integer.parseInt(args[0]);
if(n<=3){
    System.out.println(n);
}
int f1=2;
int f2=3;
for(int i=4;i<=n;i++){
  f2+=f1;
 f1=f2-f1;
}

System.out.println(f2);

}
}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782250709189238786, 1782061165294747650, '2024-04-22 11:33:45', '2024-04-22 11:33:50', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782251525111390210, 'java', 'public class Main {
    public void main(String[] args) {
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
}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782250709189238786, 1782061165294747650, '2024-04-22 11:34:10', '2024-04-22 11:34:14', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782251780208959490, 'java', 'public class Main {
    public void main(String[] args) {
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
}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782250709189238786, 1782061165294747650, '2024-04-22 11:35:11', '2024-04-22 11:35:26', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782251875482574850, 'java', 'public class Main {
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
}', '{"message":"成功","time":88,"memory":0}', 2, 1782250709189238786, 1782061165294747650, '2024-04-22 11:35:34', '2024-04-22 11:35:40', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782252777589284865, 'java', 'public class Main {
    public static void main(String[] args) {
        int n= Integer.parseInt(args[0]);
System.out.println(-n);

}
}n', '{"message":"编译错误","time":0,"memory":0}', 3, 1782252665483927554, 1782061165294747650, '2024-04-22 11:39:09', '2024-04-22 11:39:13', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782252858774233089, 'java', 'public class Main {
    public static void main(String[] args) {
        int n= Integer.parseInt(args[0]);
System.out.println(-n);

}
}', '{"message":"成功","time":93,"memory":0}', 2, 1782252665483927554, 1782061165294747650, '2024-04-22 11:39:28', '2024-04-22 11:39:32', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782265621105975298, 'java', 'import java.util.ArrayList;  
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
String result=""
for(int i=0;i<answer.length;i++){
    result+=answer[i];
}
System.out.print(result);  


}
 public static int[] convertStringToIntArray(String input) {  
        List<Integer> nums = new ArrayList<>();  
        StringBuilder sb = new StringBuilder();  
          
        for (char c : input.toCharArray()) {  
            if (Character.isDigit(c)) {  
                sb.append(c);  
            } else if (sb.length() > 0) {  
                nums.add(Integer.parseInt(sb.toString()));  
                sb.setLength(0); // 清空StringBuilder，为下一个数字做准备  
                if (c == ''-'') {  
                    sb.append(c); // 如果遇到负号，先记录下来  
                }  
            }  
        }  
          
        // 处理最后一个数字（如果有的话）  
        if (sb.length() > 0) {  
            nums.add(Integer.parseInt(sb.toString()));  
        }  
          
        // 将List转换为数组  
        return nums.stream().mapToInt(Integer::intValue).toArray();  
    } 
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782263404173041665, 1782061165294747650, '2024-04-22 12:30:11', '2024-04-22 12:30:14', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782265939717890049, 'java', 'import java.util.ArrayList;  
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
String result=""
for(int i=0;i<answer.length;i++){
    result+=answer[i];
}
System.out.print(result);  


}
 public static int[] convertStringToIntArray(String input) {  
        List<Integer> nums = new ArrayList<>();  
        StringBuilder sb = new StringBuilder();  
          
        for (char c : input.toCharArray()) {  
            if (Character.isDigit(c)) {  
                sb.append(c);  
            } else if (sb.length() > 0) {  
                nums.add(Integer.parseInt(sb.toString()));  
                sb.setLength(0); // 清空StringBuilder，为下一个数字做准备  
                if (c == ''-'') {  
                    sb.append(c); // 如果遇到负号，先记录下来  
                }  
            }  
        }  
          
        // 处理最后一个数字（如果有的话）  
        if (sb.length() > 0) {  
            nums.add(Integer.parseInt(sb.toString()));  
        }  
          
        // 将List转换为数组  
        return nums.stream().mapToInt(Integer::intValue).toArray();  
    } 
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782263404173041665, 1782061165294747650, '2024-04-22 12:31:27', '2024-04-22 12:31:30', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782266024904204289, 'java', 'import java.util.ArrayList;  
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
        StringBuilder sb = new StringBuilder();  
          
        for (char c : input.toCharArray()) {  
            if (Character.isDigit(c)) {  
                sb.append(c);  
            } else if (sb.length() > 0) {  
                nums.add(Integer.parseInt(sb.toString()));  
                sb.setLength(0); // 清空StringBuilder，为下一个数字做准备  
                if (c == ''-'') {  
                    sb.append(c); // 如果遇到负号，先记录下来  
                }  
            }  
        }  
          
        // 处理最后一个数字（如果有的话）  
        if (sb.length() > 0) {  
            nums.add(Integer.parseInt(sb.toString()));  
        }  
          
        // 将List转换为数组  
        return nums.stream().mapToInt(Integer::intValue).toArray();  
    } 
}', '{"message":"答案错误","time":148,"memory":0}', 2, 1782263404173041665, 1782061165294747650, '2024-04-22 12:31:47', '2024-04-22 12:31:52', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782266157872029697, 'java', 'import java.util.ArrayList;  
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
        StringBuilder sb = new StringBuilder();  
          
        for (char c : input.toCharArray()) {  
            if (Character.isDigit(c)) {  
                sb.append(c);  
            } else if (sb.length() > 0) {  
                nums.add(Integer.parseInt(sb.toString()));  
                sb.setLength(0); // 清空StringBuilder，为下一个数字做准备  
                if (c == ''-'') {  
                    sb.append(c); // 如果遇到负号，先记录下来  
                }  
            }  
        }  
          
        // 处理最后一个数字（如果有的话）  
        if (sb.length() > 0) {  
            nums.add(Integer.parseInt(sb.toString()));  
        }  
          
        // 将List转换为数组  
        return nums.stream().mapToInt(Integer::intValue).toArray();  
    } 
}', '{}', 1, 1782263404173041665, 1782061165294747650, '2024-04-22 12:32:19', '2024-04-22 12:32:19', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782269727484780545, 'java', 'import java.util.ArrayList;  
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
}', '{"message":"成功","time":236,"memory":0}', 2, 1782263404173041665, 1782061165294747650, '2024-04-22 12:46:30', '2024-04-22 12:46:52', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782271286755041281, 'java', 'public class Main {
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
}', '{"message":"成功","time":93,"memory":0}', 2, 1782270640039501826, 1782061165294747650, '2024-04-22 12:52:42', '2024-04-22 12:52:47', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782273098031669249, 'java', 'import java.util.HashMap;

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
            System.out.print(stack.isEmpty():"true":"false");  
    }
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782272440981364738, 1782061165294747650, '2024-04-22 12:59:54', '2024-04-22 12:59:57', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782273150552743937, 'java', 'import java.util.HashMap;

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
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782272440981364738, 1782061165294747650, '2024-04-22 13:00:06', '2024-04-22 13:00:09', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782273552568393730, 'java', 'import java.util.*;

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
}', '{"message":"成功","time":103,"memory":0}', 2, 1782272440981364738, 1782061165294747650, '2024-04-22 13:01:42', '2024-04-22 13:01:46', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782285455235588097, 'java', 'public class Main {

public static void main(String[] args) {

int a= Integer.parseInt(args[0]);
  int b= Integer.parseInt(args[1]);
             System.out.println(a*b);
             
    }
}', '{"message":"成功","time":92,"memory":0}', 2, 1782249777370079234, 1782061672771977217, '2024-04-22 13:49:00', '2024-04-22 13:49:05', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782285755149295617, 'java', 'import java.util.ArrayList;
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
}', '{"message":"成功","time":159,"memory":0}', 2, 1782263404173041665, 1782061672771977217, '2024-04-22 13:50:11', '2024-04-22 13:50:12', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782285797310439425, 'java', 'import java.util.ArrayList;
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
}', '{"message":"答案错误","time":137,"memory":0}', 2, 1782224642772643841, 1782061672771977217, '2024-04-22 13:50:22', '2024-04-22 13:50:22', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782285984577724418, 'java', 'import java.util.*;

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
}', '{"message":"成功","time":92,"memory":0}', 2, 1782272440981364738, 1782061672771977217, '2024-04-22 13:51:06', '2024-04-22 13:51:07', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782286198487228417, 'java', 'public class Main{

public static void main(String[] args) {

    System.out.println("2");
    
}

}', '{"message":"成功","time":96,"memory":0}', 2, 1782236265553960961, 1782061672771977217, '2024-04-22 13:51:57', '2024-04-22 13:51:58', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782286323884335106, 'java', 'import java.util.HashMap;

public class Main {

public static void main(String[] args) {

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
}', '{"message":"成功","time":94,"memory":0}', 2, 1782244128879910914, 1782061672771977217, '2024-04-22 13:52:27', '2024-04-22 13:52:28', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782286636053798913, 'java', 'public class Main {

public static void main(String[] args) {

int a= Integer.parseInt(args[0]);
  int b= Integer.parseInt(args[1]);
             System.out.println(a*b);
             
    }
}', '{"message":"成功","time":96,"memory":0}', 2, 1782249777370079234, 1782061954323021825, '2024-04-22 13:53:41', '2024-04-22 13:53:42', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782286712436269058, 'java', 'import java.util.*;

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
}', '{"message":"成功","time":96,"memory":0}', 2, 1782272440981364738, 1782061954323021825, '2024-04-22 13:54:00', '2024-04-22 13:54:00', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782286749639745538, 'java', 'import java.util.*;

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
}', '{"message":"答案错误","time":96,"memory":0}', 2, 1782263404173041665, 1782061954323021825, '2024-04-22 13:54:09', '2024-04-22 13:54:09', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782286786620923906, 'java', 'import java.util.*;

public class Main {

public static void  main(String[] args) {
    String s=args[0];

}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782263404173041665, 1782061954323021825, '2024-04-22 13:54:17', '2024-04-22 13:54:18', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782286906594795522, 'java', 'import java.util.ArrayList;
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
}', '{"message":"成功","time":139,"memory":0}', 2, 1782263404173041665, 1782061954323021825, '2024-04-22 13:54:46', '2024-04-22 13:54:47', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782287059087106050, 'java', 'public class Main{

public static void main(String[] args) {

    System.out.println("21");
    
}

}', '{"message":"答案错误","time":93,"memory":0}', 2, 1782236265553960961, 1782061954323021825, '2024-04-22 13:55:22', '2024-04-22 13:55:23', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782287080205426690, 'java', 'public class Main{

public static void main(String[] args) {

    System.out.println("2");
    
}

}', '{"message":"成功","time":87,"memory":0}', 2, 1782236265553960961, 1782061954323021825, '2024-04-22 13:55:27', '2024-04-22 13:55:28', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782287388721651714, 'java', 'public class Main {

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
ada
}
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782244128879910914, 1782061954323021825, '2024-04-22 13:56:41', '2024-04-22 13:56:41', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782287425400840194, 'java', 'public class Main {

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
s
}
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782244128879910914, 1782061954323021825, '2024-04-22 13:56:50', '2024-04-22 13:56:50', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782287444635918338, 'java', 'public class Main {

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
}', '{"message":"答案错误","time":95,"memory":0}', 2, 1782244128879910914, 1782061954323021825, '2024-04-22 13:56:54', '2024-04-22 13:56:55', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782287495160504321, 'java', 'public class Main {

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
}', '{"message":"成功","time":91,"memory":0}', 2, 1782250709189238786, 1782061954323021825, '2024-04-22 13:57:06', '2024-04-22 13:57:07', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782287600571752450, 'java', 'import java.util.HashMap;

public class Main {

public static void main(String[] args) {

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
}', '{"message":"成功","time":92,"memory":0}', 2, 1782244128879910914, 1782061954323021825, '2024-04-22 13:57:31', '2024-04-22 13:57:32', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782287923352805377, 'java', 'import java.util.ArrayList;
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
}', '{"message":"成功","time":151,"memory":0}', 2, 1782263404173041665, 1782061852149776385, '2024-04-22 13:58:48', '2024-04-22 13:58:49', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782287962858954753, 'java', 'import java.util.*;

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
}', '{"message":"成功","time":96,"memory":0}', 2, 1782272440981364738, 1782061852149776385, '2024-04-22 13:58:58', '2024-04-22 13:58:59', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288041397297154, 'java', 'public class Main{

public static void main(String[] args) {

    System.out.println("2");
    
}

}', '{"message":"成功","time":95,"memory":0}', 2, 1782236265553960961, 1782061852149776385, '2024-04-22 13:59:17', '2024-04-22 13:59:17', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288161673158658, 'java', 'import java.util.HashMap;

public class Main {

public static void main(String[] args) {

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
}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782263404173041665, 1782061917853548546, '2024-04-22 13:59:45', '2024-04-22 13:59:46', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288234821820418, 'java', 'import java.util.*;

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
}', '{"message":"成功","time":113,"memory":0}', 2, 1782272440981364738, 1782061917853548546, '2024-04-22 14:00:03', '2024-04-22 14:00:03', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288280699117569, 'java', 'public class Main {

public static void main(String[] args) {

int a= Integer.parseInt(args[0]);
  int b= Integer.parseInt(args[1]);
             System.out.println(a*b);
             
    }
}', '{"message":"成功","time":91,"memory":0}', 2, 1782249777370079234, 1782061917853548546, '2024-04-22 14:00:14', '2024-04-22 14:00:14', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288369563836417, 'java', 'import java.util.ArrayList;
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
}', '{"message":"成功","time":151,"memory":0}', 2, 1782263404173041665, 1782061917853548546, '2024-04-22 14:00:35', '2024-04-22 14:00:36', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288425968836610, 'java', 'public class Main{

public static void main(String[] args) {

    System.out.println("2");
    
}

}', '{"message":"成功","time":89,"memory":0}', 2, 1782236265553960961, 1782061917853548546, '2024-04-22 14:00:48', '2024-04-22 14:00:49', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288479387492353, 'java', 'public class Main {

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
f1=f2-f1;1
}

System.out.println(f2);

}
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782250709189238786, 1782061917853548546, '2024-04-22 14:01:01', '2024-04-22 14:01:01', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288497137786882, 'java', 'public class Main {

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
}', '{"message":"成功","time":90,"memory":0}', 2, 1782250709189238786, 1782061917853548546, '2024-04-22 14:01:05', '2024-04-22 14:01:06', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288723437264898, 'java', 'public class Main{

public static void main(String[] args) {

    System.out.println("2");
    1
}

}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782236265553960961, 1782061982609408002, '2024-04-22 14:01:59', '2024-04-22 14:01:59', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288741753790465, 'java', 'public class Main{

public static void main(String[] args) {

    System.out.println("2");
}

}', '{"message":"成功","time":95,"memory":0}', 2, 1782236265553960961, 1782061982609408002, '2024-04-22 14:02:04', '2024-04-22 14:02:04', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288813103095810, 'java', 'public class Main {

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
}2

System.out.println(f2);

}
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782250709189238786, 1782061982609408002, '2024-04-22 14:02:21', '2024-04-22 14:02:21', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288830673035266, 'java', 'public class Main {

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
}24

System.out.println(f2);

}
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782250709189238786, 1782061982609408002, '2024-04-22 14:02:25', '2024-04-22 14:02:25', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288847160844289, 'java', 'public class Main {

public static void main(String[] args) {
    int n= Integer.parseInt(args[0]);


System.out.println(f2);

}
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782250709189238786, 1782061982609408002, '2024-04-22 14:02:29', '2024-04-22 14:02:29', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288867087982594, 'java', 'public class Main {

}', '{"message":"运行错误","time":0,"memory":0}', 3, 1782250709189238786, 1782061982609408002, '2024-04-22 14:02:33', '2024-04-22 14:02:34', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288888252440578, 'java', 'public class Main {

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
}', '{"message":"成功","time":96,"memory":0}', 2, 1782250709189238786, 1782061982609408002, '2024-04-22 14:02:38', '2024-04-22 14:02:39', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288924340232193, 'java', 'c', '{"message":"编译错误","time":0,"memory":0}', 3, 1782244128879910914, 1782061982609408002, '2024-04-22 14:02:47', '2024-04-22 14:02:47', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288948524589058, 'java', 'cimport java.util.*;

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
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782244128879910914, 1782061982609408002, '2024-04-22 14:02:53', '2024-04-22 14:02:53', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782288976227966977, 'java', 'import java.util.ArrayList;
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
}', '{"message":"答案错误","time":152,"memory":0}', 2, 1782244128879910914, 1782061982609408002, '2024-04-22 14:02:59', '2024-04-22 14:03:00', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782289004690513921, 'java', 'public class Main {

public static void main(String[] args) {

int a= Integer.parseInt(args[0]);
  int b= Integer.parseInt(args[1]);
             System.out.println(a*b);
             
    }
}', '{"message":"答案错误","time":91,"memory":0}', 2, 1782244128879910914, 1782061982609408002, '2024-04-22 14:03:06', '2024-04-22 14:03:07', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782289031433396226, 'java', 'import java.util.*;

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
}', '{"message":"答案错误","time":91,"memory":0}', 2, 1782244128879910914, 1782061982609408002, '2024-04-22 14:03:13', '2024-04-22 14:03:13', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782289116523241474, 'java', 'import java.util.HashMap;

public class Main {

public static void main(String[] args) {

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
}', '{"message":"答案错误","time":93,"memory":0}', 2, 1782236265553960961, 1782061982609408002, '2024-04-22 14:03:33', '2024-04-22 14:03:33', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782289148592889857, 'java', 'import java.util.HashMap;

public class Main {

public static void main(String[] args) {

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
}', '{"message":"成功","time":96,"memory":0}', 2, 1782244128879910914, 1782061982609408002, '2024-04-22 14:03:41', '2024-04-22 14:03:41', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782289381821358082, 'java', 'import java.util.*;

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
}', '{"message":"成功","time":91,"memory":0}', 2, 1782272440981364738, 1782061982609408002, '2024-04-22 14:04:36', '2024-04-22 14:04:37', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782289420304097281, 'java', 'import java.util.*;

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
}', '{"message":"答案错误","time":89,"memory":0}', 2, 1782249777370079234, 1782061982609408002, '2024-04-22 14:04:45', '2024-04-22 14:04:46', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782289458027667457, 'java', 'import java.util.ArrayList;
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
            } else {1
                istransfer=true;
            }
        }

        // 将List转换为数组
        return nums.stream().mapToInt(Integer::intValue).toArray();
    }
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782249777370079234, 1782061982609408002, '2024-04-22 14:04:54', '2024-04-22 14:04:55', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782289508392869889, 'java', 'import java.util.ArrayList;
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
                istransfer=t1rue;
            }
        }

        // 将List转换为数组
        return nums.stream().mapToInt(Integer::intValue).toArray();
    }
}', '{"message":"编译错误","time":0,"memory":0}', 3, 1782263404173041665, 1782061982609408002, '2024-04-22 14:05:06', '2024-04-22 14:05:07', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782289538310840322, 'java', 'import java.util.ArrayList;
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
}', '{"message":"成功","time":137,"memory":0}', 2, 1782263404173041665, 1782061982609408002, '2024-04-22 14:05:13', '2024-04-22 14:05:14', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1782326552955559937, 'java', 'import java.util.HashMap;

public class Main {

public static void main(String[] args) {

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
}', '{"message":"成功","time":102,"memory":0}', 2, 1782244128879910914, 1782061165294747650, '2024-04-22 16:32:18', '2024-04-22 16:32:20', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1785603890199736321, 'java', 'public class Main {

public static void main(String[] args) {

int a= Integer.parseInt(args[0]);
  int b= Integer.parseInt(args[1]);
             System.out.println(a*b);
             
    }
}', '{"message":"成功","time":121,"memory":0}', 2, 1782249777370079234, 1782061165294747650, '2024-05-01 17:35:17', '2024-05-01 17:35:19', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1787458524837126146, 'java', 'c''c''c''c''c''c''c''c''c''c''c''c''c''c', '{"message":"编译错误","time":0,"memory":0}', 3, 1785604430040215553, 1782061165294747650, '2024-05-06 20:24:56', '2024-05-06 20:24:56', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1793533213506273282, 'java', 'public class Main{

public static void main(String[] args) {

    System.out.println("2");
    
}

}', '{"message":"成功","time":196,"memory":0}', 2, 1782236265553960961, 1782061165294747650, '2024-05-23 14:43:35', '2024-05-23 14:43:36', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1793534363789950978, 'python', 'import sys

result=0
for i in range(1,len(sys.argv)):
   result+=int(sys.argv[i])
print(result)
', '{"message":"成功","time":524,"memory":0}', 2, 1782236265553960961, 1782061165294747650, '2024-05-23 14:48:09', '2024-05-23 14:48:09', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1793534518471688194, 'cpp', '        #include <iostream>
        #include <cstdlib>

        int main(int argc, char **argv)
        {

        	int sum = 0;
        	for (int i = 1; i < argc; ++i)
        	{
        		int num_i = atoi(argv[i]);
        		sum += num_i;
        	}
        	std::cout << sum;

        	return 0;
        }', '{"message":"成功","time":1330,"memory":0}', 2, 1782236265553960961, 1782061165294747650, '2024-05-23 14:48:46', '2024-05-23 14:49:00', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1793534600684240897, 'cpp', '        #include <iostream>
        #include <cstdlib>

        int main(int argc, char **argv)
        {

        	int sum = 0;
        	for (int i = 1; i < argc; ++i)
        	{
        		int num_i = atoi(argv[i]);
        		sum += num_i;
        	}
        	std::cout << sum;

        	return 0;
        }', '{"message":"成功","time":1288,"memory":0}', 2, 1782236265553960961, 1782061165294747650, '2024-05-23 14:49:05', '2024-05-23 14:49:08', 0);
INSERT INTO zoj.question_submit (id, language, code, judgeInfo, status, questionId, userId, createTime, updateTime, isDelete) VALUES (1794183896056258561, 'java', 'import java.util.*;

public class Main {

public static void  main(String[] args) {

        System.out.print("2");  
}
}', '{"message":"成功","time":121,"memory":0}', 2, 1782236265553960961, 1782061165294747650, '2024-05-25 09:49:09', '2024-05-25 09:49:11', 0);
