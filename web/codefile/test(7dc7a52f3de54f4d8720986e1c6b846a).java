package 简易扑克牌游戏;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class test {

	//这个方法用于判断控制台输入的是否是数字，因为ID之能是整形
	public int exception() throws Exception {
		Scanner kb = new Scanner(System.in);
		int obj = kb.nextInt();
		if ((int) obj - obj == 0) {
			return obj;
		} else
			throw new Exception();
	}

	public void test1() {
		List<pukepai> list = new ArrayList<pukepai>();
		Scanner kb = new Scanner(System.in);

		// 准备创建一副扑克牌，没有大小王
		List<pukepai> pailist = new ArrayList<pukepai>();
		String[] str1 = { "梅花", "黑桃", "方块", "红桃" };
		// 循环创建梅花2-红桃10
		for (int i = 2; i < 11; i++) {
			for (int j = 0; j < 4; j++) {
				String k = String.valueOf(i);
				pailist.add(new pukepai(str1[j], k));
			}
		}
		// 创建J、Q、K、A的扑克牌
		for (int j = 0; j < 4; j++) {
			pailist.add(new pukepai(str1[j], "J"));
		}
		for (int j = 0; j < 4; j++) {
			pailist.add(new pukepai(str1[j], "Q"));
		}
		for (int j = 0; j < 4; j++) {
			pailist.add(new pukepai(str1[j], "K"));
		}
		for (int j = 0; j < 4; j++) {
			pailist.add(new pukepai(str1[j], "A"));
		}
		System.out.println("-------扑克牌创建完毕---------");
		for (pukepai pai : pailist) {
			System.out.print(pai.huase + pai.shuzi + " ");
		}

		/*
		 * 这里需要把创建并且排序好的pailist集合添加到新的集合list中，因为后面需要pailist集合的索引位置比较大小
		 * 而此时list就已经打乱了顺序了
		 */
		list.addAll(pailist);

		System.out.println("\n" + "\n" + "-------游戏开始，洗牌完毕---------" + "\n");
		// 集合的这个方法用于打乱集合中的顺序
		Collections.shuffle(list);

		//准备创建游戏玩家
		System.out.println("欢迎四位玩家游戏");
		List<Student> stulist = new ArrayList<Student>();
		int m;		//接受玩家id
		String str;		//接受玩家姓名
		//定义每人两张手牌
		pukepai[] kepai = new pukepai[2];	//接受玩家手上的两张牌
		for (int i = 0; i < 4; i++) {
			System.out.println("请输入第" + (i + 1) + "位玩家ID");
			while (true) {
				try {
					//调用方法判断是否为整形，如果不是抛出异常，打印提示
					m = exception();
					break;
				} catch (Exception e) {
					System.out.println("请重新输入该位玩家ID,提示必须是整形变量");
					continue;
				}
			}
			System.out.println("请输入第" + (i + 1) + "位玩家昵称");
			str = kb.next();
			//间隔发牌，玩家得到第i张和第i+4张
			kepai[0] = list.get(i);
			kepai[1] = list.get(i + 4);
			Student stu = new Student(m, str);
			stu.shoupai.addAll(Arrays.asList(kepai));	//将发的牌传给玩家对象
			stulist.add(stu);
		}

		/*
		 * 利用索引位置比较大小,找出每位玩家手上最大的牌
		 */
		for (int i = 0; i < 4; i++) {
			if (pailist.indexOf(stulist.get(i).shoupai.get(0)) < pailist
					.indexOf(stulist.get(i).shoupai.get(1))) {
				stulist.get(i).maxpai = stulist.get(i).shoupai.get(1);
			} else {
				stulist.get(i).maxpai = stulist.get(i).shoupai.get(0);
			}
		}

		//输出玩家的所有信息，id、姓名和最大的手牌
		System.out.println("有以下玩家：" + "\n");
		for (Student student : stulist) {
			System.out.println(student.id + ": " + student.name + "  最大的牌为："
					+ student.maxpai.huase + student.maxpai.shuzi);
		}

		//在大小规则中梅花2最小
		pukepai winpai = new pukepai("梅花", "2");
		
		//利用索引位置比较大小，比较玩家手中最大的牌，找到最最大的
		for (int i = 0; i < 4; i++) {
			if (pailist.indexOf(stulist.get(i).maxpai) > pailist
					.indexOf(winpai)) {
				winpai = stulist.get(i).maxpai;
			}
		}
		
		//根据最最大的牌找到该玩家
		for (int i = 0; i < 4; i++) {
			if (stulist.get(i).maxpai == winpai) {
				System.out.println("\n" + "最后的赢家是：" + stulist.get(i).id + ": "
						+ stulist.get(i).name + "\n");
			}
		}

		//打印输出每位玩家手上的两张牌，检查前面比较的结果是否
		for (Student student : stulist) {
			System.out.println(student.id + ": " + student.name + " 两张手牌为："
					+ student.shoupai.get(0).huase
					+ student.shoupai.get(0).shuzi + "和"
					+ student.shoupai.get(1).huase
					+ student.shoupai.get(1).shuzi);
		}

		kb.close();

	}

	public static void main(String[] args) {
		test ttt = new test();
		ttt.test1();
	}

}
