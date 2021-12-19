package com.paymoon.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

   给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

 * @author stephenlii
 *
 * 自己写的
 * 1. 首先是树。比如输入 234，那么树的根结点，就是2（a，,b,c三个节点开头的树）
 * 然后 a,b,c下面各自开枝散叶。
 * 即 a 下面 叶子结节："ad", "ae", "af"
 * 然后"ad", "ae", "af"下面各自散叶，
 * 即 ad 下面 adg,adh,adi
 * b,c同理。
 * 即：
 * 						 				2
 *  					 				|
 *   		      "a",         			"b",             "c"
 *   	           |           			 |                |
 *    "ad",      "ae",       "af"
 *     |           |           |
 * adg adh adi  aeg,aeh,aei  afg,afh,afi
 * 
 * 2、想明白是树，那就要遍历了
 * 树的遍历方式为dfs和bfs。
 * 这里用了dfs。即回溯大法
 * 
 * 3、想明白用回溯，先想想回溯的套路（本道和No.46全排列非常雷同，故参考了全排列步骤)
 * 3.1 有主方法，主方法调用回溯。回溯里面是递归
 * 3.2 递归里面一般入参有：开始的0，总共长度的n，遍历的树入参(List<List<String>> list),结果入参(resultList)
 * 3.3 递归方法里面，一般步骤是使用长度0和n的关系，结束递归。使用for循环调用递归（因为和全排列雷同，所以for调用外面，然后使用append相加）
 * 3.4 用了stringbuffer ，本来想着stringbuffer可以一直append, 等递归结束后，根据digits长度循环substring 截取就可以。这样就不用传resultList
 * 后来发现不可。因为树的遍历有个很重要的一点。当遍历完叶子结点，向上一级的时候，需要删除这次叶子节点的数据。
 * 如adg后，下一个应该是adh，此时需要adg（叶子）-> ad(根) -> adh(新叶子）。所以adg需要做一次删除，不删除直接append，就成了adgh,这时候3.4只用stringbuffer
 * 就不可取了。
 * 3.5，基于3.4，必须使用resultList存入结果，stringbuffer只能当做变量了。
 * 判断当stringbuffer append到叶子结点（len=入参）时，resultList进行存入。stringbuffer删除叶子节点，进行下一次递归。
 */
/**
 * 比起上一次提交中
    在递归里面
    改了一下resultList.add(sBuffer.toString());的位置
    加了一个if-else。
    官方的这一步确实很"递归"。在结束条件里面，才可以有这种操作
 *
 */
class Leetcode017 {
	public List<String> letterCombinations(String digits) {
		Map<Character, List<String>> phoneNumberMap = new HashMap<>();
		phoneNumberMap.put('2', Arrays.asList("a", "b", "c"));
		phoneNumberMap.put('3', Arrays.asList("d", "e", "f"));
		phoneNumberMap.put('4', Arrays.asList("g", "h", "i"));
		phoneNumberMap.put('5', Arrays.asList("j", "k", "l"));
		phoneNumberMap.put('6', Arrays.asList("m", "n", "o"));
		phoneNumberMap.put('7', Arrays.asList("p", "q", "r","s"));
		phoneNumberMap.put('8', Arrays.asList("t", "u","v"));
		phoneNumberMap.put('9', Arrays.asList("w", "x", "y", "z"));

		if (digits == null) {
			return new ArrayList<>();
		}
		List<List<String>> list = new ArrayList<>();
		int digitsLen = digits.length();
		for (int i = 0; i < digitsLen; i++) {
			Character c = digits.charAt(i);
			if (phoneNumberMap.containsKey(c)) {
				list.add(phoneNumberMap.get(c));
			} else {
				System.out.println("param is not valid!");
				return new ArrayList<>();
			}
		}
		if (list.isEmpty()) {
			return new ArrayList<>();
		}
		StringBuffer sBuffer = new StringBuffer();
		List<String> resultList = new ArrayList<>();
		backtrace(0, digitsLen, list, sBuffer, resultList);
		return resultList;
	}

	private void backtrace(int index, int digitsLen, List<List<String>> list, StringBuffer sBuffer,
			List<String> resultList) {
		if (index == digitsLen) {
			resultList.add(sBuffer.toString());
		}else {
			List<String> currentList = list.get(index);
			for (int i = 0; i < currentList.size(); i++) {
				sBuffer.append(currentList.get(i));
				backtrace(index + 1, digitsLen, list, sBuffer, resultList);
				sBuffer.deleteCharAt(sBuffer.toString().length() - 1);
			}
		}
	}

	public static void main(String[] args) {

		System.out.println(new Leetcode017().letterCombinations("23"));
	}
}
