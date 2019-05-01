/*���˽�k-shell�㷨����ڵ���Ҫ�Ե�ԭ��󣬳���ʵ��k-shell�㷨���ڵ������Ϊ  id1,id2 ,����id1,id2��Ϊ����*/
package hashmap;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.IntBuffer;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class NetWork {
	// ���ж�ȡ�ļ����ݲ��Ҽ��ļ����ݼ��뵽һ����̬������
	public static ArrayList<Integer[]> readFileByLines(String fileName) throws IOException {
		File file = new File(fileName);
		BufferedReader reader = null;
		ArrayList<Integer[]> mylist = new ArrayList<Integer[]>();
		Integer[] temp_array;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {

				String str = tempString.toString().trim();
				temp_array = str_to_array(str);
				mylist.add(temp_array);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return mylist;
	}

	// �÷������ַ����ָ���ת����Integer���󣬷���������
	public static Integer[] str_to_array(String temp_str) {
		String[] str_array = temp_str.split(",");
		Integer id1 = Integer.parseInt(str_array[0]);
		Integer id2 = Integer.parseInt(str_array[1]);
		Integer[] int_array = new Integer[] { id1, id2 };
		return int_array;
	}

	// ��һ��HashMap�з���Ԫ�أ�id1����key�����HashMap�����и�key����id2������Ϊvalue��HashSet��ȥ
	public static void put_map(HashMap<Integer, HashSet<Integer>> map, Integer id1, Integer id2) {
		if (map.containsKey(id1)) {
			map.get(id1).add(id2);
		} else {
			HashSet<Integer> temp_set = new HashSet<Integer>();
			temp_set.add(id2);
			map.put(id1, temp_set);
		}
	}

	// �Ӷ�̬�����ж�ȡԪ����Ϣ������put_map�������Դ˷���HashMap��
	public static HashMap<Integer, HashSet<Integer>> form_net(ArrayList<Integer[]> mylist) {
		HashMap<Integer, HashSet<Integer>> mymap = new HashMap<Integer, HashSet<Integer>>();
		Integer[] temp_array = null;
		Integer id1 = 0;
		Integer id2 = 0;
		for (int i = 0; i < mylist.size(); i++) {
			temp_array = mylist.get(i);
			id1 = temp_array[0];
			id2 = temp_array[1];
			put_map(mymap, id1, id2);
			put_map(mymap, id2, id1);
		}
		return mymap;
	}

	// �÷������ڷ��ʶ�̬�����е�����
	private static void visit_list(ArrayList<Integer[]> mylist) {
		int i = 0;
		int j = 0;
		for (i = 0; i < mylist.size(); i++) {
			for (j = 0; j < mylist.get(i).length; j++) {
				System.out.print(mylist.get(i)[j] + ",");
			}
			System.out.println();
		}
	}

	// ���ڷ���HashSet����
	private static void visit_set(HashSet<Integer> myset) {
		Iterator<Integer> it = myset.iterator();
		Integer key = null;
		while (it.hasNext()) {
			key = it.next();
			System.out.println(key);
		}
	}

	// ���ڷ��������ڵ�����
	private static void visit_map(HashMap<Integer, HashSet<Integer>> mymap) {
		Iterator<Integer> it = mymap.keySet().iterator();
		Integer key = null;
		while (it.hasNext()) {
			key = it.next();
			System.out.println(mymap.get(key));
		}
	}

	// ���ڴ�ӡMap�е�key��Ӧ��HashSet�����е�Ԫ��
	public static void print_value_set(HashMap<Integer, HashSet<Integer>> mymap, Integer id) {
		HashSet<Integer> temp_set = mymap.get(id);
		Integer temp_num;
		Iterator<Integer> it = temp_set.iterator();
		while (it.hasNext()) {
			temp_num = it.next();
			System.out.println(temp_num);
		}
	}

	// ��������HashMap,�ҵ�һ��id�������Ľڵ��value��HashSet���ϣ��г��ֵĴ�������Ϊkey
	public static HashMap<Integer, Integer> degree_count(HashMap<Integer, HashSet<Integer>> mymap) {
		HashMap<Integer, Integer> degree_map = new HashMap<Integer, Integer>();
		Integer id = null;
		Integer id2 = null;
		Iterator<Integer> it = null;
		Iterator temp_it = mymap.keySet().iterator();
		while (temp_it.hasNext()) {
			id = (Integer) temp_it.next();
			it = mymap.get(id).iterator();
			while (it.hasNext()) {
				id2 = it.next();
				if (!degree_map.containsKey(id2)) {
					degree_map.put(id2, 1);
				} else {
					degree_map.put(id2, (degree_map.get(id2) + 1));
				}
			}
		}
		return degree_map;
	}

	// HashSet��size��ʵ����ÿ���ڵ��degree
	// public static HashMap<Integer, Integer>
	// degree_count_overwrite(HashMap<Integer, HashSet<Integer>> mymap) {
	// HashMap<Integer, Integer> degree_map = new HashMap<Integer, Integer>();
	// Integer id = null;
	// Integer temp_size = null;
	// Iterator it = mymap.keySet().iterator();
	// while (it.hasNext()) {
	// id = (Integer) it.next();
	// temp_size = mymap.get(id).size();
	// degree_map.put(id, temp_size);
	// }
	// return degree_map;
	// }

	// ��ӡ�ڵ������
	public static void get_degree(HashMap<Integer, Integer> degree_map, Integer id) {
		System.out.println(degree_map.get(id));
	}
//�ϣ�����
//	public static HashMap<Integer, Integer> count_k_shell(HashMap<Integer, Integer> degree_map,
//			HashMap<Integer, HashSet<Integer>> relation_map) {
//		Integer ks = 0;
//		Integer id = null;
//		Integer degree = null;
//		Iterator<Integer> it = null;
//		HashMap<Integer, Integer> k_shell_map = new HashMap<Integer, Integer>();
//		while (relation_map.size() != 0) {
//			Iterator temp_it = degree_map.keySet().iterator();
//			while (temp_it.hasNext()) {
//				id = (Integer) temp_it.next();
//				degree = degree_map.get(id);
//				if (degree == ks) {
//					k_shell_map.put(id, ks);
//					deleteSingleNode(relation_map,id);
//					degree_map = degree_count(relation_map);
//				}
//			}
//			ks = ks + 1;
//		}
//
//		return k_shell_map;
//	}


	// �������õ���degree_map���ҳ���С��degree��Ӧ�Ľڵ�,���ҷ���һ��HashSet�б���
	public static Integer get_lowest_degree(HashMap<Integer, Integer> degree_map) {
		Integer lowest_degree = null;
		Integer id = null;
		Integer degree = null;
		Iterator<Integer> it = degree_map.keySet().iterator();
		id = it.next();
		lowest_degree = degree_map.get(id);
		while (it.hasNext()) {
			id = it.next();
			degree = degree_map.get(id);
			if (degree < lowest_degree) {
				lowest_degree = degree;
			}
		}
		return lowest_degree;
	}

	// �õ�����͵��Ǹ��ڵ�
	public static HashSet<Integer> getLowestNodes(HashMap<Integer, Integer> degree_map,
			HashMap<Integer, HashSet<Integer>> relation_map) {
		HashSet<Integer> tempSet = new HashSet<Integer>();
		Integer lowest_degree = null;
		Integer id = null;
		Integer size = null;
		lowest_degree = get_lowest_degree(degree_map);
		Iterator<Integer> it = relation_map.keySet().iterator();
		while (it.hasNext()) {
			id = it.next();
			size = relation_map.get(id).size();
			if (size.equals(lowest_degree)) {
				tempSet.add(id);
			}
		}
		return tempSet;
	}

	// ɾ��ӵ�����degree��node,��һ���ڵ��key��ö�Ӧ��HashSet��HashSet�б��������ýڵ������Ľڵ���Ϣ
	public static void deleteSingleNode(HashMap<Integer, HashSet<Integer>> relation_map, Integer key) {
		Integer id = null;
		HashSet<Integer> temp_set = relation_map.get(key);
		Iterator<Integer> it = temp_set.iterator();
		while (it.hasNext()) {
			id = it.next();
			relation_map.get(id).remove(key);
		}
		relation_map.remove(key);
	}

	// �γ�ks_map
	public static HashMap<Integer, Integer> form_ks_map(HashMap<Integer, Integer> degree_map,
			HashMap<Integer, HashSet<Integer>> relation_map) {
		Integer lowestDegree = null;
		HashSet<Integer> tempSet = null;
		Iterator<Integer> it = null;
		Integer id = null;
		HashMap<Integer, Integer> ksMap = new HashMap<Integer, Integer>();
		while (relation_map.size() != 0) {
			degree_map = degree_count(relation_map);
			lowestDegree = get_lowest_degree(degree_map);
			tempSet = getLowestNodes(degree_map, relation_map);
			it = tempSet.iterator();
			while (it.hasNext()) {
				id = it.next();
				deleteSingleNode(relation_map, id);
				ksMap.put(id, lowestDegree);
			}
		}
		return ksMap;
	}

	// ��ӡks����
	public static void print_ks_map(HashMap<Integer, Integer> ksMap) {
		Integer id = null;
		Iterator<Integer> it = ksMap.keySet().iterator();
		while (it.hasNext()) {
			id = it.next();
			System.out.print(id + ":" + ksMap.get(id));
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		String fileName = "C://Users//MR.CHEN//Desktop//data.txt";
		ArrayList<Integer[]> mylist = readFileByLines(fileName);
		// visit_list(mylist);
		HashMap<Integer, HashSet<Integer>> relation_map = form_net(mylist);
		HashMap<Integer, Integer> degree_map = new HashMap<Integer, Integer>();
		// HashMap <Integer,Integer>map2 = new HashMap<Integer,Integer>();
		degree_map = degree_count(relation_map);
		// map2=degree_count_overwrite(mymap);
		// get_degree(degree_map,9);
		// get_degree(map2,9);
		// HashMap <Integer,Integer>k_shell_map = new HashMap<Integer,Integer>();
		// k_shell_map = count_k_shell(map,mymap);
		// System.out.println();
		// get_k_shell(k_shell_map,1);
		// Integer lowest_degree = null;
		// lowest_degree = get_lowest_degree(degree_map);
		// System.out.println(lowest_degree);
		HashMap<Integer, Integer> ksMap = new HashMap<Integer, Integer>();
		ksMap = form_ks_map(degree_map, relation_map);
		print_ks_map(ksMap);
	}
}
